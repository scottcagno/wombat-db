package com.cagnosolutions.wombat.io.mappedbus.util.bytearray;

import com.cagnosolutions.wombat.io.mappedbus.MappedBusWriter;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class ByteArrayWriter {

	public static void main(String[] args) {
		ByteArrayWriter writer = new ByteArrayWriter();
		writer.run(new Random().nextInt(10));
	}

	public void run(int source) {
		try {
			MappedBusWriter writer = new MappedBusWriter("/tmp/test-bytearray", 2000000L, 30, true);
			writer.open();
			byte[] buffer = new byte[30];
			for (int i = 0; i < 1000; i++) {
				Arrays.fill(buffer, (byte) source);
				writer.write(buffer, 0, buffer.length);
				Thread.sleep(1000);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
