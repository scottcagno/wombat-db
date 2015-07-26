package com.cagnosolutions.wombat;

import com.cagnosolutions.wombat.db.Database;
import com.cagnosolutions.wombat.db.Hash;

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
		db.set("user:1", "name", 	"Scott Cagno");
		db.set("user:1", "fname", 	"Scott");
		db.set("user:1", "lname", 	"Cagno");
		db.set("user:1", "email", 	"scottiecagno@gmail.com");
		db.set("user:1", "age", "28");
		db.set("user:1", "active", "true");

		Hash hash1 = db.get("user:1");
		System.out.printf("hash1: %s\n", hash1);

		String name1 = db.get("user:1", "name");
		String email1 = db.get("user:1", "email");
		System.out.printf("name: %s, email: %s\n", name1, email1);

		db.del("user:1", "email");
		hash1 = db.get("user:1");
		System.out.printf("hash1: %s\n", hash1);

		db.del("user:1");
		hash1 = db.get("user:1");
		System.out.printf("hash1: %s\n", hash1);

	}

}
