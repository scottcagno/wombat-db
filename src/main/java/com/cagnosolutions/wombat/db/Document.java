package com.cagnosolutions.wombat.db;

import com.cagnosolutions.wombat.db.util.UUID1;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusMessage;
import com.cagnosolutions.wombat.io.mappedbus.MemoryMappedFile;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class Document implements MappedBusMessage {

	private int size;
	private byte[] id;
	private byte[] modified;
	private byte[] data;

	public Document() {}

	public Document(byte[] data) {
		byte[] time = UUID1.getTimeUUIDBytes();
		this.id = time;
		this.modified = time;
		this.size = this.size(data.length);
		this.data = data;
	}

	private int size(int size) {
		int blocks = 0;
		for(int i = size; i > 0; i-=1024)
			blocks++;
		return blocks;
	}

	public void setData(byte[] data) {
		this.modified = UUID1.getTimeUUIDBytes();
		this.size = this.size(data.length);
		this.data = data;
	}

	public void write(MemoryMappedFile mem, long pos) {
		mem.putInt(pos, size);
		mem.setBytes(pos + 4, id, 0, 36);
		mem.setBytes(pos + 4 + 36, modified, 0, 36);
		mem.setBytes(pos + 4 + 36 + 36, data, 0, size*1024);
	}

	public void read(MemoryMappedFile mem, long pos) {
		size = mem.getInt(pos);
		System.out.printf("size: %d (%d)\n", size, size*1024);
		id = new byte[36];
		mem.getBytes(pos + 4, id, 0, 36);
		modified = new byte[36];
		mem.getBytes(pos + 4 + 36, modified, 0, 36);
		data = new byte[size*1024];
		mem.getBytes(pos + 4 + 36 + 36, data, 0, data.length);
	}

	public int type() {
		return 0;
	}

	public String toString() {
		return "Document{id: " + new String(id) +
				", modified: " + new String(modified) +
				", size: " + size +
				", data: " + new String(data) +
				"}\n";
	}
}
