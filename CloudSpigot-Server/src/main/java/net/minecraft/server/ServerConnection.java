package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

@SuppressWarnings("deprecation")
public class ServerConnection {

	private static final Logger e = LogManager.getLogger();
	public static final LazyInitVar<NioEventLoopGroup> a = new LazyInitVar<NioEventLoopGroup>() {
		protected NioEventLoopGroup a() {
			return new NioEventLoopGroup();
		}

		protected NioEventLoopGroup init() {
			return this.a();
		}
	};
	public static final LazyInitVar<NioEventLoopGroup> b = new LazyInitVar<NioEventLoopGroup>() {
		protected NioEventLoopGroup a() {
			return new NioEventLoopGroup();
		}

		protected NioEventLoopGroup init() {
			return this.a();
		}
	};
	public static final LazyInitVar<LocalEventLoopGroup> c = new LazyInitVar<LocalEventLoopGroup>() {
		protected LocalEventLoopGroup a() {
			return new LocalEventLoopGroup();
		}

		protected LocalEventLoopGroup init() {
			return this.a();
		}
	};
	private final MinecraftServer f;
	public volatile boolean d;
	private final List<ChannelFuture> g = Collections.synchronizedList(Lists.newArrayList());
	private final List<NetworkManager> h = Collections.synchronizedList(Lists.newArrayList());
	// Paper start - prevent blocking on adding a new network manager while the
	// server is ticking
	private final List<NetworkManager> pending = Collections.synchronizedList(Lists.<NetworkManager>newArrayList());

	private void addPending() {
		synchronized (pending) {
			this.h.addAll(pending); // Paper - OBFHELPER - List of network managers
			pending.clear();
		}
	}
	// Paper end

	public ServerConnection(MinecraftServer minecraftserver) {
		this.f = minecraftserver;
		this.d = true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void a(InetAddress inetaddress, int i) throws IOException {
		synchronized (this.g) {
			Class oclass;
			LazyInitVar lazyinitvar;

			oclass = NioServerSocketChannel.class;
			lazyinitvar = ServerConnection.a;
			ServerConnection.e.info("Using default channel type");

			this.g.add((new ServerBootstrap()).channel(oclass).childHandler(new ChannelInitializer() {
				protected void initChannel(Channel channel) throws Exception {
					try {
						channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
					} catch (ChannelException channelexception) {
						;
					}

					channel.pipeline().addLast("timeout", new ReadTimeoutHandler(30))
							.addLast("legacy_query", new LegacyPingHandler(ServerConnection.this))
							.addLast("splitter", new PacketSplitter())
							.addLast("decoder", new PacketDecoder(EnumProtocolDirection.SERVERBOUND))
							.addLast("prepender", new PacketPrepender())
							.addLast("encoder", new PacketEncoder(EnumProtocolDirection.CLIENTBOUND));
					NetworkManager networkmanager = new NetworkManager(EnumProtocolDirection.SERVERBOUND);

					pending.add(networkmanager); // Paper
					channel.pipeline().addLast("packet_handler", networkmanager);
					networkmanager.setPacketListener(new HandshakeListener(ServerConnection.this.f, networkmanager));
				}
			}).group((EventLoopGroup) lazyinitvar.c()).localAddress(inetaddress, i).bind().syncUninterruptibly());
		}
	}

	@SuppressWarnings("rawtypes")
	public void b() {
		this.d = false;
		Iterator iterator = this.g.iterator();

		while (iterator.hasNext()) {
			ChannelFuture channelfuture = (ChannelFuture) iterator.next();

			try {
				channelfuture.channel().close().sync();
			} catch (InterruptedException interruptedexception) {
				ServerConnection.e.error("Interrupted whilst closing channel");
			}
		}

	}

	public void c() {
		synchronized (this.h) {
			// Spigot Start
			addPending(); // Paper
			// This prevents players from 'gaming' the server, and strategically relogging
			// to increase their position in the tick order
			if (org.spigotmc.SpigotConfig.playerShuffle > 0
					&& MinecraftServer.currentTick % org.spigotmc.SpigotConfig.playerShuffle == 0) {
				Collections.shuffle(this.h);
			}
			// Spigot End
			Iterator<NetworkManager> iterator = this.h.iterator();

			while (iterator.hasNext()) {
				final NetworkManager networkmanager = iterator.next();

				if (!networkmanager.h()) {
					if (networkmanager.isConnected()) {
						try {
							networkmanager.a();
						} catch (Exception exception) {
							if (networkmanager.isLocal()) {
								CrashReport crashreport = CrashReport.a(exception, "Ticking memory connection");
								CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Ticking connection");

								crashreportsystemdetails.a("Connection", new CrashReportCallable() {
									public String a() throws Exception {
										return networkmanager.toString();
									}

									public Object call() throws Exception {
										return this.a();
									}
								});
								throw new ReportedException(crashreport);
							}

							ServerConnection.e.warn("Failed to handle packet for {}", networkmanager.getSocketAddress(),
									exception);
							final ChatComponentText chatcomponenttext = new ChatComponentText("Internal server error");

							networkmanager.sendPacket(new PacketPlayOutKickDisconnect(chatcomponenttext),
									new GenericFutureListener() {
										public void operationComplete(Future future) throws Exception {
											networkmanager.close(chatcomponenttext);
										}
									}, new GenericFutureListener[0]);
							networkmanager.stopReading();
						}
					} else {
						// Spigot Start
						// Fix a race condition where a NetworkManager could be unregistered just before
						// connection.
						if (networkmanager.preparing)
							continue;
						// Spigot End
						iterator.remove();
						networkmanager.handleDisconnection();
					}
				}
			}

		}
	}

	public MinecraftServer d() {
		return this.f;
	}
}
