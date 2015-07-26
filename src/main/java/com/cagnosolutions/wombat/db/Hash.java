package com.cagnosolutions.wombat.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public final class Hash {

	private final ConcurrentMap<String, Object> hashset = new ConcurrentHashMap<>(8, 0.85f, 2);

	public Hash(String f, String v) {
		hashset.put(f, v);
	}

	public Hash set(String f, String v) {
		hashset.put(f, v);
		return this;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{ ");
		int n = 0;
		int setSize = hashset.size()-1;
		for(Map.Entry entry : hashset.entrySet()) {
			sb.append(entry.getKey()).append(":").append(entry.getValue());
			if(n < setSize)
				sb.append(", ");
			n++;
		}

		sb.append(" }");
		return sb.toString();
	}
	
	public String get(String f) {
		return (String) hashset.get(f);
	}
	
	public void del(String f) {
		hashset.remove(f);
	}
}
