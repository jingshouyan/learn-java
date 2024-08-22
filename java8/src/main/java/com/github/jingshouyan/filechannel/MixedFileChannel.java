package com.github.jingshouyan.filechannel;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author jingshouyan
 * 2022-08-26 14:47
 */
@AllArgsConstructor
public class MixedFileChannel extends FileChannel {

    private FileChannel base;

    @Override
    public int read(ByteBuffer dst) throws IOException {

        ByteBuffer dst2 = ByteBuffer.allocate(dst.remaining());
        int size = base.read(dst2);
        byte[] bytes = dst2.array();

        dst.put(bytes, 0, size);
        return size;
    }

    @Override
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        return 0;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        return 0;
    }

    @Override
    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
        return 0;
    }

    @Override
    public long position() throws IOException {
        return 0;
    }

    @Override
    public FileChannel position(long newPosition) throws IOException {
        return null;
    }

    @Override
    public long size() throws IOException {
        return 0;
    }

    @Override
    public FileChannel truncate(long size) throws IOException {
        return null;
    }

    @Override
    public void force(boolean metaData) throws IOException {

    }

    @Override
    public long transferTo(long position, long count, WritableByteChannel target) throws IOException {
        return 0;
    }

    @Override
    public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException {
        return 0;
    }

    @Override
    public int read(ByteBuffer dst, long position) throws IOException {
        return 0;
    }

    @Override
    public int write(ByteBuffer src, long position) throws IOException {
        return 0;
    }

    @Override
    public MappedByteBuffer map(MapMode mode, long position, long size) throws IOException {
        return null;
    }

    @Override
    public FileLock lock(long position, long size, boolean shared) throws IOException {
        return null;
    }

    @Override
    public FileLock tryLock(long position, long size, boolean shared) throws IOException {
        return null;
    }

    @Override
    protected void implCloseChannel() throws IOException {

    }
}
