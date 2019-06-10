package net.minecraft.server;

import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.inventory.CraftItemStack; // CraftBukkit

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufProcessor;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import io.netty.util.ByteProcessor;

public class PacketDataSerializer extends ByteBuf {

    public boolean isReadOnly() {
		return a.isReadOnly();
	}

	public ByteBuf asReadOnly() {
		return a.asReadOnly();
	}

	public short getShortLE(int index) {
		return a.getShortLE(index);
	}

	public int getUnsignedShortLE(int index) {
		return a.getUnsignedShortLE(index);
	}

	public int getMediumLE(int index) {
		return a.getMediumLE(index);
	}

	public int getUnsignedMediumLE(int index) {
		return a.getUnsignedMediumLE(index);
	}

	public int getIntLE(int index) {
		return a.getIntLE(index);
	}

	public long getUnsignedIntLE(int index) {
		return a.getUnsignedIntLE(index);
	}

	public long getLongLE(int index) {
		return a.getLongLE(index);
	}

	public float getFloatLE(int index) {
		return a.getFloatLE(index);
	}

	public double getDoubleLE(int index) {
		return a.getDoubleLE(index);
	}

	public int getBytes(int index, FileChannel out, long position, int length) throws IOException {
		return a.getBytes(index, out, position, length);
	}

	public CharSequence getCharSequence(int index, int length, Charset charset) {
		return a.getCharSequence(index, length, charset);
	}

	public ByteBuf setShortLE(int index, int value) {
		return a.setShortLE(index, value);
	}

	public ByteBuf setMediumLE(int index, int value) {
		return a.setMediumLE(index, value);
	}

	public ByteBuf setIntLE(int index, int value) {
		return a.setIntLE(index, value);
	}

	public ByteBuf setLongLE(int index, long value) {
		return a.setLongLE(index, value);
	}

	public ByteBuf setFloatLE(int index, float value) {
		return a.setFloatLE(index, value);
	}

	public ByteBuf setDoubleLE(int index, double value) {
		return a.setDoubleLE(index, value);
	}

	public int setBytes(int index, FileChannel in, long position, int length) throws IOException {
		return a.setBytes(index, in, position, length);
	}

	public int setCharSequence(int index, CharSequence sequence, Charset charset) {
		return a.setCharSequence(index, sequence, charset);
	}

	public short readShortLE() {
		return a.readShortLE();
	}

	public int readUnsignedShortLE() {
		return a.readUnsignedShortLE();
	}

	public int readMediumLE() {
		return a.readMediumLE();
	}

	public int readUnsignedMediumLE() {
		return a.readUnsignedMediumLE();
	}

	public int readIntLE() {
		return a.readIntLE();
	}

	public long readUnsignedIntLE() {
		return a.readUnsignedIntLE();
	}

	public long readLongLE() {
		return a.readLongLE();
	}

	public float readFloatLE() {
		return a.readFloatLE();
	}

	public double readDoubleLE() {
		return a.readDoubleLE();
	}

	public ByteBuf readRetainedSlice(int length) {
		return a.readRetainedSlice(length);
	}

	public CharSequence readCharSequence(int length, Charset charset) {
		return a.readCharSequence(length, charset);
	}

	public int readBytes(FileChannel out, long position, int length) throws IOException {
		return a.readBytes(out, position, length);
	}

	public ByteBuf writeShortLE(int value) {
		return a.writeShortLE(value);
	}

	public ByteBuf writeMediumLE(int value) {
		return a.writeMediumLE(value);
	}

	public ByteBuf writeIntLE(int value) {
		return a.writeIntLE(value);
	}

	public ByteBuf writeLongLE(long value) {
		return a.writeLongLE(value);
	}

	public ByteBuf writeFloatLE(float value) {
		return a.writeFloatLE(value);
	}

	public ByteBuf writeDoubleLE(double value) {
		return a.writeDoubleLE(value);
	}

	public int writeBytes(FileChannel in, long position, int length) throws IOException {
		return a.writeBytes(in, position, length);
	}

	public int writeCharSequence(CharSequence sequence, Charset charset) {
		return a.writeCharSequence(sequence, charset);
	}

	public int forEachByte(ByteProcessor processor) {
		return a.forEachByte(processor);
	}

	public int forEachByte(int index, int length, ByteProcessor processor) {
		return a.forEachByte(index, length, processor);
	}

	public int forEachByteDesc(ByteProcessor processor) {
		return a.forEachByteDesc(processor);
	}

	public int forEachByteDesc(int index, int length, ByteProcessor processor) {
		return a.forEachByteDesc(index, length, processor);
	}

	public ByteBuf retainedSlice() {
		return a.retainedSlice();
	}

	public ByteBuf retainedSlice(int index, int length) {
		return a.retainedSlice(index, length);
	}

	public ByteBuf retainedDuplicate() {
		return a.retainedDuplicate();
	}

	private final ByteBuf a;

    public int refCnt() {
		return a.refCnt();
	}

	public boolean release() {
		return a.release();
	}

	public boolean release(int decrement) {
		return a.release(decrement);
	}

	public int capacity() {
		return a.capacity();
	}

	public ByteBuf capacity(int newCapacity) {
		return a.capacity(newCapacity);
	}

	public int maxCapacity() {
		return a.maxCapacity();
	}

	public ByteBufAllocator alloc() {
		return a.alloc();
	}

	public ByteOrder order() {
		return a.order();
	}

	public ByteBuf order(ByteOrder endianness) {
		return a.order(endianness);
	}

	public ByteBuf unwrap() {
		return a.unwrap();
	}

	public boolean isDirect() {
		return a.isDirect();
	}

	public int readerIndex() {
		return a.readerIndex();
	}

	public ByteBuf readerIndex(int readerIndex) {
		return a.readerIndex(readerIndex);
	}

	public int writerIndex() {
		return a.writerIndex();
	}

	public ByteBuf writerIndex(int writerIndex) {
		return a.writerIndex(writerIndex);
	}

	public ByteBuf setIndex(int readerIndex, int writerIndex) {
		return a.setIndex(readerIndex, writerIndex);
	}

	public int readableBytes() {
		return a.readableBytes();
	}

	public int writableBytes() {
		return a.writableBytes();
	}

	public int maxWritableBytes() {
		return a.maxWritableBytes();
	}

	public boolean isReadable() {
		return a.isReadable();
	}

	public boolean isReadable(int size) {
		return a.isReadable(size);
	}

	public boolean isWritable() {
		return a.isWritable();
	}

	public boolean isWritable(int size) {
		return a.isWritable(size);
	}

	public ByteBuf clear() {
		return a.clear();
	}

	public ByteBuf markReaderIndex() {
		return a.markReaderIndex();
	}

	public ByteBuf resetReaderIndex() {
		return a.resetReaderIndex();
	}

	public ByteBuf markWriterIndex() {
		return a.markWriterIndex();
	}

	public ByteBuf resetWriterIndex() {
		return a.resetWriterIndex();
	}

	public ByteBuf discardReadBytes() {
		return a.discardReadBytes();
	}

	public ByteBuf discardSomeReadBytes() {
		return a.discardSomeReadBytes();
	}

	public ByteBuf ensureWritable(int minWritableBytes) {
		return a.ensureWritable(minWritableBytes);
	}

	public int ensureWritable(int minWritableBytes, boolean force) {
		return a.ensureWritable(minWritableBytes, force);
	}

	public boolean getBoolean(int index) {
		return a.getBoolean(index);
	}

	public byte getByte(int index) {
		return a.getByte(index);
	}

	public short getUnsignedByte(int index) {
		return a.getUnsignedByte(index);
	}

	public short getShort(int index) {
		return a.getShort(index);
	}

	public int getUnsignedShort(int index) {
		return a.getUnsignedShort(index);
	}

	public int getMedium(int index) {
		return a.getMedium(index);
	}

	public int getUnsignedMedium(int index) {
		return a.getUnsignedMedium(index);
	}

	public int getInt(int index) {
		return a.getInt(index);
	}

	public long getUnsignedInt(int index) {
		return a.getUnsignedInt(index);
	}

	public long getLong(int index) {
		return a.getLong(index);
	}

	public char getChar(int index) {
		return a.getChar(index);
	}

	public float getFloat(int index) {
		return a.getFloat(index);
	}

	public double getDouble(int index) {
		return a.getDouble(index);
	}

	public ByteBuf getBytes(int index, ByteBuf dst) {
		return a.getBytes(index, dst);
	}

	public ByteBuf getBytes(int index, ByteBuf dst, int length) {
		return a.getBytes(index, dst, length);
	}

	public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
		return a.getBytes(index, dst, dstIndex, length);
	}

	public ByteBuf getBytes(int index, byte[] dst) {
		return a.getBytes(index, dst);
	}

	public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
		return a.getBytes(index, dst, dstIndex, length);
	}

	public ByteBuf getBytes(int index, ByteBuffer dst) {
		return a.getBytes(index, dst);
	}

	public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
		return a.getBytes(index, out, length);
	}

	public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
		return a.getBytes(index, out, length);
	}

	public ByteBuf setBoolean(int index, boolean value) {
		return a.setBoolean(index, value);
	}

	public ByteBuf setByte(int index, int value) {
		return a.setByte(index, value);
	}

	public ByteBuf setShort(int index, int value) {
		return a.setShort(index, value);
	}

	public ByteBuf setMedium(int index, int value) {
		return a.setMedium(index, value);
	}

	public ByteBuf setInt(int index, int value) {
		return a.setInt(index, value);
	}

	public ByteBuf setLong(int index, long value) {
		return a.setLong(index, value);
	}

	public ByteBuf setChar(int index, int value) {
		return a.setChar(index, value);
	}

	public ByteBuf setFloat(int index, float value) {
		return a.setFloat(index, value);
	}

	public ByteBuf setDouble(int index, double value) {
		return a.setDouble(index, value);
	}

	public ByteBuf setBytes(int index, ByteBuf src) {
		return a.setBytes(index, src);
	}

	public ByteBuf setBytes(int index, ByteBuf src, int length) {
		return a.setBytes(index, src, length);
	}

	public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
		return a.setBytes(index, src, srcIndex, length);
	}

	public ByteBuf setBytes(int index, byte[] src) {
		return a.setBytes(index, src);
	}

	public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
		return a.setBytes(index, src, srcIndex, length);
	}

	public ByteBuf setBytes(int index, ByteBuffer src) {
		return a.setBytes(index, src);
	}

	public int setBytes(int index, InputStream in, int length) throws IOException {
		return a.setBytes(index, in, length);
	}

	public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
		return a.setBytes(index, in, length);
	}

	public ByteBuf setZero(int index, int length) {
		return a.setZero(index, length);
	}

	public boolean readBoolean() {
		return a.readBoolean();
	}

	public byte readByte() {
		return a.readByte();
	}

	public short readUnsignedByte() {
		return a.readUnsignedByte();
	}

	public short readShort() {
		return a.readShort();
	}

	public int readUnsignedShort() {
		return a.readUnsignedShort();
	}

	public int readMedium() {
		return a.readMedium();
	}

	public int readUnsignedMedium() {
		return a.readUnsignedMedium();
	}

	public int readInt() {
		return a.readInt();
	}

	public long readUnsignedInt() {
		return a.readUnsignedInt();
	}

	public long readLong() {
		return a.readLong();
	}

	public char readChar() {
		return a.readChar();
	}

	public float readFloat() {
		return a.readFloat();
	}

	public double readDouble() {
		return a.readDouble();
	}

	public ByteBuf readBytes(int length) {
		return a.readBytes(length);
	}

	public ByteBuf readSlice(int length) {
		return a.readSlice(length);
	}

	public ByteBuf readBytes(ByteBuf dst) {
		return a.readBytes(dst);
	}

	public ByteBuf readBytes(ByteBuf dst, int length) {
		return a.readBytes(dst, length);
	}

	public ByteBuf readBytes(ByteBuf dst, int dstIndex, int length) {
		return a.readBytes(dst, dstIndex, length);
	}

	public ByteBuf readBytes(byte[] dst) {
		return a.readBytes(dst);
	}

	public ByteBuf readBytes(byte[] dst, int dstIndex, int length) {
		return a.readBytes(dst, dstIndex, length);
	}

	public ByteBuf readBytes(ByteBuffer dst) {
		return a.readBytes(dst);
	}

	public ByteBuf readBytes(OutputStream out, int length) throws IOException {
		return a.readBytes(out, length);
	}

	public int readBytes(GatheringByteChannel out, int length) throws IOException {
		return a.readBytes(out, length);
	}

	public ByteBuf skipBytes(int length) {
		return a.skipBytes(length);
	}

	public ByteBuf writeBoolean(boolean value) {
		return a.writeBoolean(value);
	}

	public ByteBuf writeByte(int value) {
		return a.writeByte(value);
	}

	public ByteBuf writeShort(int value) {
		return a.writeShort(value);
	}

	public ByteBuf writeMedium(int value) {
		return a.writeMedium(value);
	}

	public ByteBuf writeInt(int value) {
		return a.writeInt(value);
	}

	public ByteBuf writeLong(long value) {
		return a.writeLong(value);
	}

	public ByteBuf writeChar(int value) {
		return a.writeChar(value);
	}

	public ByteBuf writeFloat(float value) {
		return a.writeFloat(value);
	}

	public ByteBuf writeDouble(double value) {
		return a.writeDouble(value);
	}

	public ByteBuf writeBytes(ByteBuf src) {
		return a.writeBytes(src);
	}

	public ByteBuf writeBytes(ByteBuf src, int length) {
		return a.writeBytes(src, length);
	}

	public ByteBuf writeBytes(ByteBuf src, int srcIndex, int length) {
		return a.writeBytes(src, srcIndex, length);
	}

	public ByteBuf writeBytes(byte[] src) {
		return a.writeBytes(src);
	}

	public ByteBuf writeBytes(byte[] src, int srcIndex, int length) {
		return a.writeBytes(src, srcIndex, length);
	}

	public ByteBuf writeBytes(ByteBuffer src) {
		return a.writeBytes(src);
	}

	public int writeBytes(InputStream in, int length) throws IOException {
		return a.writeBytes(in, length);
	}

	public int writeBytes(ScatteringByteChannel in, int length) throws IOException {
		return a.writeBytes(in, length);
	}

	public ByteBuf writeZero(int length) {
		return a.writeZero(length);
	}

	public int indexOf(int fromIndex, int toIndex, byte value) {
		return a.indexOf(fromIndex, toIndex, value);
	}

	public int bytesBefore(byte value) {
		return a.bytesBefore(value);
	}

	public int bytesBefore(int length, byte value) {
		return a.bytesBefore(length, value);
	}

	public int bytesBefore(int index, int length, byte value) {
		return a.bytesBefore(index, length, value);
	}

	public int forEachByte(ByteBufProcessor processor) {
		return a.forEachByte(processor);
	}

	public int forEachByte(int index, int length, ByteBufProcessor processor) {
		return a.forEachByte(index, length, processor);
	}

	public int forEachByteDesc(ByteBufProcessor processor) {
		return a.forEachByteDesc(processor);
	}

	public int forEachByteDesc(int index, int length, ByteBufProcessor processor) {
		return a.forEachByteDesc(index, length, processor);
	}

	public ByteBuf copy() {
		return a.copy();
	}

	public ByteBuf copy(int index, int length) {
		return a.copy(index, length);
	}

	public ByteBuf slice() {
		return a.slice();
	}

	public ByteBuf slice(int index, int length) {
		return a.slice(index, length);
	}

	public ByteBuf duplicate() {
		return a.duplicate();
	}

	public int nioBufferCount() {
		return a.nioBufferCount();
	}

	public ByteBuffer nioBuffer() {
		return a.nioBuffer();
	}

	public ByteBuffer nioBuffer(int index, int length) {
		return a.nioBuffer(index, length);
	}

	public ByteBuffer internalNioBuffer(int index, int length) {
		return a.internalNioBuffer(index, length);
	}

	public ByteBuffer[] nioBuffers() {
		return a.nioBuffers();
	}

	public ByteBuffer[] nioBuffers(int index, int length) {
		return a.nioBuffers(index, length);
	}

	public boolean hasArray() {
		return a.hasArray();
	}

	public byte[] array() {
		return a.array();
	}

	public int arrayOffset() {
		return a.arrayOffset();
	}

	public boolean hasMemoryAddress() {
		return a.hasMemoryAddress();
	}

	public long memoryAddress() {
		return a.memoryAddress();
	}

	public String toString(Charset charset) {
		return a.toString(charset);
	}

	public String toString(int index, int length, Charset charset) {
		return a.toString(index, length, charset);
	}

	public int hashCode() {
		return a.hashCode();
	}

	public boolean equals(Object obj) {
		return a.equals(obj);
	}

	public int compareTo(ByteBuf buffer) {
		return a.compareTo(buffer);
	}

	public String toString() {
		return a.toString();
	}

	public ByteBuf retain(int increment) {
		return a.retain(increment);
	}

	public ByteBuf retain() {
		return a.retain();
	}

	public ByteBuf touch() {
		return a.touch();
	}

	public ByteBuf touch(Object hint) {
		return a.touch(hint);
	}

	public PacketDataSerializer(ByteBuf bytebuf) {
        this.a = bytebuf;
    }

    public static int countBytes(int i) { return PacketDataSerializer.a(i); } // Paper - Anti-Xray - OBFHELPER
    public static int a(int i) {
        for (int j = 1; j < 5; ++j) {
            if ((i & -1 << j * 7) == 0) {
                return j;
            }
        }

        return 5;
    }

    public PacketDataSerializer a(byte[] abyte) {
        this.d(abyte.length);
        this.writeBytes(abyte);
        return this;
    }

    public byte[] a() {
        return this.b(this.readableBytes());
    }

    public byte[] b(int i) {
        int j = this.g();

        if (j > i) {
            throw new DecoderException("ByteArray with size " + j + " is bigger than allowed " + i);
        } else {
            byte[] abyte = new byte[j];

            this.readBytes(abyte);
            return abyte;
        }
    }

    public PacketDataSerializer a(int[] aint) {
        this.d(aint.length);
        int[] aint1 = aint;
        int i = aint.length;

        for (int j = 0; j < i; ++j) {
            int k = aint1[j];

            this.d(k);
        }

        return this;
    }

    public int[] b() {
        return this.c(this.readableBytes());
    }

    public int[] c(int i) {
        int j = this.g();

        if (j > i) {
            throw new DecoderException("VarIntArray with size " + j + " is bigger than allowed " + i);
        } else {
            int[] aint = new int[j];

            for (int k = 0; k < aint.length; ++k) {
                aint[k] = this.g();
            }

            return aint;
        }
    }

    public PacketDataSerializer a(long[] along) {
        this.d(along.length);
        long[] along1 = along;
        int i = along.length;

        for (int j = 0; j < i; ++j) {
            long k = along1[j];

            this.writeLong(k);
        }

        return this;
    }

    public BlockPosition e() {
        return BlockPosition.fromLong(this.readLong());
    }

    public PacketDataSerializer a(BlockPosition blockposition) {
        this.writeLong(blockposition.asLong());
        return this;
    }

    public IChatBaseComponent f() {
        return IChatBaseComponent.ChatSerializer.a(this.e(32767));
    }

    public PacketDataSerializer a(IChatBaseComponent ichatbasecomponent) {
        return this.a(IChatBaseComponent.ChatSerializer.a(ichatbasecomponent));
    }

	public <T extends Enum<T>> T a(Class<T> oclass) {
        return oclass.getEnumConstants()[this.g()]; // CraftBukkit - fix decompile error
    }

    public PacketDataSerializer a(Enum<?> oenum) {
        return this.d(oenum.ordinal());
    }

    public int g() {
        int i = 0;
        int j = 0;

        byte b0;

        do {
            b0 = this.readByte();
            i |= (b0 & 127) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((b0 & 128) == 128);

        return i;
    }

    public long h() {
        long i = 0L;
        int j = 0;

        byte b0;

        do {
            b0 = this.readByte();
            i |= (long) (b0 & 127) << j++ * 7;
            if (j > 10) {
                throw new RuntimeException("VarLong too big");
            }
        } while ((b0 & 128) == 128);

        return i;
    }

    public PacketDataSerializer a(UUID uuid) {
        this.writeLong(uuid.getMostSignificantBits());
        this.writeLong(uuid.getLeastSignificantBits());
        return this;
    }

    public UUID i() {
        return new UUID(this.readLong(), this.readLong());
    }

    public PacketDataSerializer d(int i) {
        while ((i & -128) != 0) {
            this.writeByte(i & 127 | 128);
            i >>>= 7;
        }

        this.writeByte(i);
        return this;
    }

    public PacketDataSerializer b(long i) {
        while ((i & -128L) != 0L) {
            this.writeByte((int) (i & 127L) | 128);
            i >>>= 7;
        }

        this.writeByte((int) i);
        return this;
    }

    public PacketDataSerializer a(@Nullable NBTTagCompound nbttagcompound) {
        if (nbttagcompound == null) {
            this.writeByte(0);
        } else {
            try {
                NBTCompressedStreamTools.a(nbttagcompound, (DataOutput) (new ByteBufOutputStream(this)));
            } catch (Exception ioexception) { // CraftBukkit - IOException -> Exception
                throw new EncoderException(ioexception);
            }
        }

        return this;
    }

    @Nullable
    public NBTTagCompound j() {
        int i = this.readerIndex();
        byte b0 = this.readByte();

        if (b0 == 0) {
            return null;
        } else {
            this.readerIndex(i);

            try {
                return NBTCompressedStreamTools.a((new ByteBufInputStream(this)), new NBTReadLimiter(2097152L));
            } catch (IOException ioexception) {
                throw new EncoderException(ioexception);
            }
        }
    }

    public PacketDataSerializer a(ItemStack itemstack) {
        if (itemstack.isEmpty() || itemstack.getItem() == null) { // CraftBukkit - NPE fix itemstack.getItem()
            this.writeShort(-1);
        } else {
            this.writeShort(Item.getId(itemstack.getItem()));
            this.writeByte(itemstack.getCount());
            this.writeShort(itemstack.getData());
            NBTTagCompound nbttagcompound = null;

            if (itemstack.getItem().usesDurability() || itemstack.getItem().p()) {
                // Spigot start - filter
                itemstack = itemstack.cloneItemStack();
                CraftItemStack.setItemMeta(itemstack, CraftItemStack.getItemMeta(itemstack));
                // Spigot end
                nbttagcompound = itemstack.getTag();
            }

            this.a(nbttagcompound);
        }

        return this;
    }

    public ItemStack k() {
        short short0 = this.readShort();

        if (short0 < 0) {
            return ItemStack.a;
        } else {
            byte b0 = this.readByte();
            short short1 = this.readShort();
            ItemStack itemstack = new ItemStack(Item.getById(short0), b0, short1);

            itemstack.setTag(this.j());
            // CraftBukkit start
            if (itemstack.getTag() != null) {
                CraftItemStack.setItemMeta(itemstack, CraftItemStack.getItemMeta(itemstack));
            }
            // CraftBukkit end
            return itemstack;
        }
    }

    public String e(int i) {
        int j = this.g();

        if (j > i * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + i * 4 + ")");
        } else if (j < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        } else {
            String s = this.toString(this.readerIndex(), j, StandardCharsets.UTF_8);

            this.readerIndex(this.readerIndex() + j);
            if (s.length() > i) {
                throw new DecoderException("The received string length is longer than maximum allowed (" + j + " > " + i + ")");
            } else {
                return s;
            }
        }
    }

    public PacketDataSerializer a(String s) {
        byte[] abyte = s.getBytes(StandardCharsets.UTF_8);

        if (abyte.length > 44767) { // Paper - raise limit a bit more as normal means can trigger this
            throw new EncoderException("String too big (was " + s.length() + " bytes encoded, max " + 44767 + ")"); // Paper
        } else {
            this.d(abyte.length);
            this.writeBytes(abyte);
            return this;
        }
    }

    public MinecraftKey l() {
        return new MinecraftKey(this.e(32767));
    }

    public PacketDataSerializer a(MinecraftKey minecraftkey) {
        this.a(minecraftkey.toString());
        return this;
    }

    public Date m() {
        return new Date(this.readLong());
    }

    public PacketDataSerializer a(Date date) {
        this.writeLong(date.getTime());
        return this;
    }
    
}
