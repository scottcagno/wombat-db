package com.cagnosolutions.wombat.io.mappedbus;

import com.cagnosolutions.wombat.io.mappedbus.MappedBusConstants.Commit;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusConstants.Length;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusConstants.Rollback;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusConstants.Structure;

import java.io.EOFException;
import java.io.IOException;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class MappedBusReader {

	protected static final long MAX_TIMEOUT_COUNT = 100;
	private final String fileName;
	private final long fileSize;
	private final int recordSize;
	private MemoryMappedFile mem;
	private long limit = Structure.Data;
	private long initialLimit;
	private int maxTimeout = 2000;
	protected long timerStart;
	protected long timeoutCounter;
	private boolean typeRead;

	/**
	 * Constructs a new reader.
	 */
	public MappedBusReader(String fileName, long fileSize, int recordSize) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.recordSize = recordSize;
	}

	/**
	 * Opens the reader.
	 */
	public void open() throws IOException {
		try {
			mem = new MemoryMappedFile(fileName, fileSize);
		} catch(Exception e) {
			throw new IOException("Unable to open the file: " + fileName, e);
		}
		initialLimit = mem.getLongVolatile(Structure.Limit);
	}

	/**
	 * Sets the time for a reader to wait for a record to be committed.
	 *
	 * When the timeout occurs the reader will mark the record as "rolled back" and
	 * the record is ignored.
	 *
	 */
	public void setTimeout(int timeout) {
		this.maxTimeout = timeout;
	}

	/**
	 * Steps forward to the next record if there's one available.
	 *
	 * The method has a timeout for how long it will wait for the commit field to be set. When the timeout is
	 * reached it will set the roll back field and skip over the record.
	 *
	 * Return true, if there's a new record available, otherwise false
	 */
	public boolean next() throws EOFException {
		if (limit >= fileSize) {
			throw new EOFException("End of file was reached");
		}
		if (mem.getLongVolatile(Structure.Limit) <= limit) {
			return false;
		}
		byte commit = mem.getByteVolatile(limit);
		byte rollback = mem.getByteVolatile(limit + Length.Commit);
		if (rollback == Rollback.Set) {
			limit += Length.RecordHeader + recordSize;
			timeoutCounter = 0;
			timerStart = 0;
			return false;
		}
		if (commit == Commit.Set) {
			timeoutCounter = 0;
			timerStart = 0;
			return true;
		}
		timeoutCounter++;
		if (timeoutCounter >= MAX_TIMEOUT_COUNT) {
			if (timerStart == 0) {
				timerStart = System.currentTimeMillis();
			} else {
				if (System.currentTimeMillis() - timerStart >= maxTimeout) {
					mem.putByteVolatile(limit + Length.Commit, Rollback.Set);
					limit += Length.RecordHeader + recordSize;
					timeoutCounter = 0;
					timerStart = 0;
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Reads the message type.
	 */
	public int readType() {
		typeRead = true;
		limit += Length.StatusFlags;
		int type = mem.getInt(limit);
		limit += Length.Metadata;
		return type;
	}

	/**
	 * Reads the next message.
	 */
	public MappedBusMessage readMessage(MappedBusMessage message) {
		if (!typeRead) {
			readType();
		}
		typeRead = false;
		message.read(mem, limit);
		limit += recordSize;
		return message;
	}

	/**
	 * Reads the next buffer of data.
	 */
	public int readBuffer(byte[] dst, int offset) {
		limit += Length.StatusFlags;
		int length = mem.getInt(limit);
		limit += Length.Metadata;
		mem.getBytes(limit, dst, offset, length);
		limit += recordSize;
		return length;
	}

	/**
	 * Indicates whether all records available when the reader was created have been read.
	 */
	public boolean hasRecovered() {
		return limit >= initialLimit;
	}

	/**
	 * Closes the reader.
	 */
	public void close() throws IOException {
		try {
			mem.unmap();
		} catch(Exception e) {
			throw new IOException("Unable to close the file", e);
		}
	}
}
