package com.cagnosolutions.wombat.db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public final class Store {

	private final ConcurrentMap<String, Object> store = new ConcurrentHashMap<>(8, 0.85f, 2);

	public String set(String k, String v) {
		store.put(k, v);
		return (store.containsKey(k)) ? "OK" : "NIL";
	}

	public String get(String k) {
		return (store.containsKey(k)) ? (String) store.get(k) : "NIL";
	}

	public String del(String k) {
		store.remove(k);
		return (!store.containsKey(k)) ? "OK" : "NIL";
	}
}
