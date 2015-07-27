package com.cagnosolutions.wombat.io.mappedbus.util.bytearray;

import com.cagnosolutions.wombat.io.mappedbus.MappedBusReader;

import java.util.Arrays;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class ByteArrayReader {

	public static void main(String[] args) {
		ByteArrayReader reader = new ByteArrayReader();
		reader.run();
	}

	public void run() {
		try {
			MappedBusReader reader = new MappedBusReader("/tmp/test-bytearray", 2000000L, 10);
			reader.open();

			byte[] buffer = new byte[10];

			while (true) {
				if (reader.next()) {
					int length = reader.readBuffer(buffer, 0);
					System.out.println("Read: length = " + length + ", data= "+ Arrays.toString(buffer));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
