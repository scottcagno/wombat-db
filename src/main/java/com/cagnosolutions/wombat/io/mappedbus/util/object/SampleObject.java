package com.cagnosolutions.wombat.io.mappedbus.util.object;

import com.cagnosolutions.wombat.io.mappedbus.MappedBusMessage;
import com.cagnosolutions.wombat.io.mappedbus.MemoryMappedFile;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class SampleObject implements MappedBusMessage {

	public static final int TYPE = 0;
	private int source;
	private int price;
	private int quantity;

	public SampleObject() {
	}

	public SampleObject(int source, int price, int quantity) {
		this.source = source;
		this.price = price;
		this.quantity = quantity;
	}

	public void write(MemoryMappedFile mem, long pos) {
		mem.putInt(pos, source);
		mem.putInt(pos + 4, price);
		mem.putInt(pos + 8, quantity);
	}

	public void read(MemoryMappedFile mem, long pos) {
		source = mem.getInt(pos);
		price = mem.getInt(pos + 4);
		quantity = mem.getInt(pos + 8);
	}

	public int type() {
		return TYPE;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return "PriceUpdate [source=" + source + ", price=" + price + ", quantity=" + quantity + "]";
	}

}
