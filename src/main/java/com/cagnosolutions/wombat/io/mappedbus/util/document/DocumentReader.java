package com.cagnosolutions.wombat.io.mappedbus.util.document;

import com.cagnosolutions.wombat.db.Document;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusReader;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class DocumentReader {

	private static final long MB = 1000000;
	private static final int KB = 1024;

	public static void main(String[] args) {
		DocumentReader reader = new DocumentReader();
		reader.run();
	}

	public void run() {
		try {
			MappedBusReader reader = new MappedBusReader("/tmp/document-data", 15*MB, 4*KB);
			reader.open();

			Document document = new Document();

			while (true) {
				if (reader.next()) {
					boolean recovered = reader.hasRecovered();
					reader.readMessage(document);
					System.out.println("Read: " + document + ", hasRecovered=" + recovered);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
