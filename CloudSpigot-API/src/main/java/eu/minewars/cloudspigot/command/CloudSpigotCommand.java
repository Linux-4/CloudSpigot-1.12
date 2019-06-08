package eu.minewars.cloudspigot.command;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import eu.minewars.cloudspigot.util.Versioning;

public class CloudSpigotCommand extends BukkitCommand {

	public CloudSpigotCommand(String name) {
		super(name);

		this.description = "Display CloudSpigot Information";
		this.usageMessage = "/cloudspigot";
		this.setPermission("cloudspigot.command.cloudspigot");
		this.setAliases(Arrays.asList("cs"));
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!testPermission(sender)) {
			return true;
		}

		sender.sendMessage("§9Server Information: ");
		sender.sendMessage("§9Version: §a" + CloudSpigotCommand.class.getPackage().getImplementationVersion());
		sender.sendMessage("§9API-Version: §a" + CloudSpigotCommand.class.getPackage().getSpecificationVersion());
		sender.sendMessage("§9Author: §a" + CloudSpigotCommand.class.getPackage().getImplementationVendor());
		sender.sendMessage("§9Java Information: ");
		sender.sendMessage("§9Java-Version: §a" + Versioning.getJavaVersion());
		sender.sendMessage("§9Threads: §a" + Thread.activeCount());
		sender.sendMessage("§9Memory (max/used/free): §a" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "§9/§a"
				+ (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "§9/§a"
				+ Runtime.getRuntime().freeMemory() / 1024 / 1024 + " MB");
		sender.sendMessage("§9CPU Count: §a" + Runtime.getRuntime().availableProcessors());

		return true;
	}

}