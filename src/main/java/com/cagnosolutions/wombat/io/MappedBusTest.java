package com.cagnosolutions.wombat.io;

import com.cagnosolutions.wombat.db.Document;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusWriter;

import java.io.IOException;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class MappedBusTest {

	private static final long MB = 1000000;
	private static final int KB = 1024;

	public static void main(String[] args) throws IOException, InterruptedException {

		// Setup a writer
		MappedBusWriter writer = new MappedBusWriter("/tmp/document-data", 15*MB, 4*KB, true);
		writer.open();

		Document doc1 = new Document("this is document number 1".getBytes());
		Document doc2 = new Document("doc number 2".getBytes());
		Document doc3 = new Document("and finally, we have document number three. whew.".getBytes());
		writer.write(doc1);
		writer.write(doc2);
		writer.write(doc3);
		writer.close();

	}

}
