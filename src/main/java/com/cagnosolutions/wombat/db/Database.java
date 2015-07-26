package com.cagnosolutions.wombat.db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public final class Database {

	private final ConcurrentMap<String, Hash> hashsets = new ConcurrentHashMap<>(8, 0.85f, 2);

	public void set(String k, String f, String v) {
		hashsets.compute(k, (key, hash) -> (hash == null) ? new Hash(f, v) : hash.set(f, v));
	}

	public Hash get(String k) {
		return hashsets.compute(k, (key, hash) -> (hash == null) ? null : hash);
	}

	public String get(String k, String f) {
		Hash hash = this.get(k);
		return (hash == null) ? null : hash.get(f);
	}

	public void del(String k) {
		if(hashsets.containsKey(k))
			hashsets.remove(k);
	}

	public void del(String k, String f) {
		Hash hash = this.get(k);
		if(hash != null)
			hash.del(f);
	}
}
