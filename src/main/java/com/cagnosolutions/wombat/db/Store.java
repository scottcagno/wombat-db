package com.cagnosolutions.wombat.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public final class Store {

	private final ConcurrentMap<String, Object> store = new ConcurrentHashMap<>(8, 0.85f, 2);

	public String set(String k, Object v) {
		store.put(k, v);
		if(store.containsKey(k))
			return "OK";
		return "NIL";
	}

	public Object get(String k) {
		if(store.containsKey(k))
			return store.get(k);
		return "NIL";
	}

	public String del(String k) {
		store.remove(k);
		if(!store.containsKey(k))
			return "OK";
		return "NIL";
	}

	public String hset(String k, String f, Object v) {
		store.put(k, hash(f, v));
		if(store.containsKey(k))
			return "OK";
		return "NIL";
	}

	public Object hget(String k, String f) {
		if(!store.containsKey(k))
			return "NIL";
		Map hm = (Map) store.get(k);
		return hm.get(f);
	}

	public String hdel(String k, String f) {
		if(!store.containsKey(k))
			return "OK";
		Map hm = (Map) store.get(k);
		hm.remove(f);
		if(!store.containsKey(f))
			return "OK";
		return "NIL";
	}

	public Set hkeys(String k) {
		if(!store.containsKey(k))
			return null;
		Map hm = (Map) store.get(k);
		return hm.keySet();
	}

	public Collection hvals(String k) {
		if(!store.containsKey(k))
			return null;
		Map hm = (Map) store.get(k);
		return hm.values();
	}

	private Map<String, Object> hash(String f, Object v) {
		Map<String, Object> hm = new HashMap<>();
		hm.put(f, v);
		return hm;
	}
}
