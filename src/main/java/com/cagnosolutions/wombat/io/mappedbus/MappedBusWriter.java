package com.cagnosolutions.wombat.io.mappedbus;

import com.cagnosolutions.wombat.io.mappedbus.MappedBusConstants.Commit;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusConstants.Length;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusConstants.Structure;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class MappedBusWriter {

	private MemoryMappedFile mem;
	private final String fileName;
	private final long fileSize;
	private final int entrySize;
	private final boolean append;

	public MappedBusWriter(String fileName, long fileSize, int recordSize, boolean append) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.entrySize = recordSize + Length.RecordHeader;
		this.append = append;
	}

	/**
	 * Opens the writer.
	 */
	public void open() throws IOException {
		if (!append) {
			new File(fileName).delete();
		}
		try {
			mem = new MemoryMappedFile(fileName, fileSize);
		} catch(Exception e) {
			throw new IOException("Unable to open the file: " + fileName, e);
		}
		if (append) {
			mem.compareAndSwapLong(Structure.Limit, 0, Structure.Data);
		} else {
			mem.putLongVolatile(Structure.Limit, Structure.Data);
		}
	}

	/**
	 * Writes a message.
	 */
	public void write(MappedBusMessage message) throws EOFException {
		long limit = allocate();
		long commitPos = limit;
		limit += Length.StatusFlags;
		mem.putInt(limit, message.type());
		limit += Length.Metadata;
		message.write(mem, limit);
		commit(commitPos);
	}

	/**
	 * Writes a buffer of data.
	 */
	public void write(byte[] src, int offset, int length) throws EOFException {
		long limit = allocate();
		long commitPos = limit;
		limit += Length.StatusFlags;
		mem.putInt(limit, length);
		limit += Length.Metadata;
		mem.setBytes(limit, src, offset, length);
		commit(commitPos);
	}

	private long allocate() throws EOFException {
		long limit = mem.getAndAddLong(Structure.Limit, entrySize);
		if (limit + entrySize > fileSize) {
			throw new EOFException("End of file was reached");
		}
		return limit;
	}

	private void commit(long commitPos) {
		mem.putByteVolatile(commitPos, Commit.Set);
	}

	/**
	 * Closes the writer.
	 */
	public void close() throws IOException {
		try {
			mem.unmap();
		} catch(Exception e) {
			throw new IOException("Unable to close the file", e);
		}
	}
}
