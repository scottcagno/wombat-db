package com.cagnosolutions.wombat;

import com.cagnosolutions.wombat.db.Database;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class Main {

	public static void main(String[] args) {

		/**
		 *
		 * 	DB db = new DB("localhost:9191");
		 *	String value = db.get("key");
		 *	System.out.printf("Got value: %s\n", value);
		 *	db.close();
		 *
		 * 	TRY WITH RESOURCES...
		 *
		 * 	try(DB db = new DB("localhost:9191")) {
		 * 		String value = db.get("key");
		 * 		System.out.printf("Got value: %s\n", value);
		 * 	}
		 * 	// will automatically close
		 *
		 */

		Database db = new Database();
		db.set("user:1:name", "Scott Cagno");
		db.set("user:1:email", "scottiecagno@gmail.com");
		db.set("user:1:age", "28");

		String val = db.get("user:1:name");
		System.out.printf("value: %s\n", val);

		db.use(1);
		db.set("foo", "bar");
		String val2 = db.get("foo");
		System.out.printf("value2: %s\n", val2);

		db.use(0);
		String val3 = db.get("user:1:email");
		System.out.printf("value3: %s\n", val3);

		String val4 = db.get("user:1:age");
		System.out.printf("value4: %s\n", val4);

		db.del("user:1:age");

		String val5 = db.get("user:1:age");
		System.out.printf("value5: %s\n", val5);
	}

}
