package com.cagnosolutions.wombat.io.mappedbus.util.object;

import com.cagnosolutions.wombat.io.mappedbus.MappedBusWriter;

import java.util.Random;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class ObjectWriter {

	public static void main(String[] args) {
		ObjectWriter writer = new ObjectWriter();
		writer.run(new Random().nextInt(10));
	}

	public void run(int source) {
		try {
			MappedBusWriter writer = new MappedBusWriter("/tmp/test-message", 2000000L, 12, true);
			writer.open();

			SampleObject sampleObject = new SampleObject();

			for (int i = 0; i < 1000; i++) {
				sampleObject.setSource(source);
				sampleObject.setPrice(i * 2);
				sampleObject.setQuantity(i * 4);
				writer.write(sampleObject);
				Thread.sleep(1000);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
