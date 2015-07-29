package com.cagnosolutions.wombat.io.mappedbus.util.document;

import com.cagnosolutions.wombat.db.Document;
import com.cagnosolutions.wombat.io.mappedbus.MappedBusWriter;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class DocumentWriter {

	private static final long MB = 1000000;
	private static final int KB = 1024;

	public static void main(String[] args) {
		DocumentWriter writer = new DocumentWriter();
		writer.run(0);
	}

	public void run(int source) {
		try {
			MappedBusWriter writer = new MappedBusWriter("/tmp/document-data", 15*MB, 4*KB, true);
			writer.open();

			for (int i = 0; i < 1000; i++) {
				Document document = new Document(String.format("%d: jfkjda jdakk tijkdas jfj fadjkl j dfajskljklad " +
						"fjkl adsfjklj kjklasjdf j kljaklf jklj ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl " +
						"jkljkalsjfklasjfkljasklf j ajfdkjla sfjl sajfklsjfkl asjflj asklfjdklsjflka dfj afj " +
						"dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf lasdjklf jsdal fjsdajfklsdjklajsfklf d " +
						"klfsajfkjda jdakk tijkdas jfj fadjkl j dfajskljklad fjkl adsfjklj kjklasjdf j kljaklf jklj " +
						"ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl jkljkalsjfklasjfkljasklf j ajfdkjla sfjl " +
						"sajfklsjfkl asjflj asklfjdklsjflka dfj afj dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf " +
						"lasdjklf jsdal fjsdajfklsdjklajsfklf d klfsajfkjda jdakk tijkdas jfj fadjkl j dfajskljklad " +
						"fjkl adsfjklj kjklasjdf j kljaklf jklj ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl " +
						"jkljkalsjfklasjfkljasklf j ajfdkjla sfjl sajfklsjfkl asjflj asklfjdklsjflka dfj afj " +
						"dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf lasdjklf jsdal fjsdajfklsdjklajsfklf d " +
						"klfsajfkjda jdakk tijkdas jfj fadjkl j dfajskljklad fjkl adsfjklj kjklasjdf j kljaklf jklj " +
						"ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl jkljkalsjfklasjfkljasklf j ajfdkjla sfjl " +
						"sajfklsjfkl asjflj asklfjdklsjflka dfj afj dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf " +
						"lasdjklf jsdal fjsdajfklsdjklajsfklf d klfsajfkjda jdakk tijkdas jfj fadjkl j dfajskljklad " +
						"fjkl adsfjklj kjklasjdf j kljaklf jklj ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl " +
						"jkljkalsjfklasjfkljasklf j ajfdkjla sfjl sajfklsjfkl asjflj asklfjdklsjflka dfj afj " +
						"dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf lasdjklf jsdal fjsdajfklsdjklajsfklf d " +
						"klfsajfkjda jdakk tijkdas jfj fadjkl j dfajskljklad fjkl adsfjklj kjklasjdf j kljaklf jklj " +
						"ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl jkljkalsjfklasjfkljasklf j ajfdkjla sfjl " +
						"sajfklsjfkl asjflj asklfjdklsjflka dfj afj dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf " +
						"lasdjklf jsdal fjsdajfklsdjklajsfklf d klfsajfkjda jdakk tijkdas jfj fadjkl j dfajskljklad " +
						"fjkl adsfjklj kjklasjdf j kljaklf jklj ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl " +
						"jkljkalsjfklasjfkljasklf j ajfdkjla sfjl sajfklsjfkl asjflj asklfjdklsjflka dfj afj " +
						"dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf lasdjklf jsdal fjsdajfklsdjklajsfklf d " +
						"klfsajfkjda jdakk tijkdas jfj fadjkl j dfajskljklad fjkl adsfjklj kjklasjdf j kljaklf jklj " +
						"ajskdfjkljklj afs jdkla jkla sdjfkl jla jsfdjakl jkljkalsjfklasjfkljasklf j ajfdkjla sfjl " +
						"sajfklsjfkl asjflj asklfjdklsjflka dfj afj dklafsjlakdsjf lsdajf lasdjf j dslakjfkladsjf " +
						"lasdjklf jsdal fjsdajfklsdjklajsfklf d klfsa", i).getBytes());
				System.out.printf("Writing: %s\n", document);
				writer.write(document);
				Thread.sleep(1000);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
