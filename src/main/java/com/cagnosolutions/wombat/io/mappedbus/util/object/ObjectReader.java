package com.cagnosolutions.wombat.io.mappedbus.util.object;

import com.cagnosolutions.wombat.io.mappedbus.MappedBusMessage;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusReader;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class ObjectReader {

	public static void main(String[] args) {
		ObjectReader reader = new ObjectReader();
		reader.run();
	}

	public void run() {
		try {
			MappedBusReader reader = new MappedBusReader("/tmp/test-message", 2000000L, 12);
			reader.open();

			SampleObject sampleObject = new SampleObject();

			MappedBusMessage message = null;

			while (true) {
				if (reader.next()) {
					boolean recovered = reader.hasRecovered();
					int type = reader.readType();
					switch (type) {
						case SampleObject.TYPE:
							message = sampleObject;
							break;
						default:
							throw new RuntimeException("Unknown type: " + type);
					}
					reader.readMessage(message);
					System.out.println("Read: " + message + ", hasRecovered=" + recovered);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}