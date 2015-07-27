package com.cagnosolutions.wombat.io.mappedbus;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public interface MappedBusMessage {
	public void write(MemoryMappedFile mem, long pos);
	public void read(MemoryMappedFile mem, long pos);
	public int type();
}
