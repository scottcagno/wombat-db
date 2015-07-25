package com.cagnosolutions.wombat.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public final class Database {

	private final List<Store> stores = new ArrayList<>();
	private int STORE = 0;

	public Database() {
		this.stores.add(new Store());
	}

	public int use(int store) {
		if(stores.size() >= store) {
			this.STORE = store;
			stores.add(new Store());
		}
		return this.STORE;
	}

	public String set(String k, String v) {
		return stores.get(STORE).set(k, v);
	}

	public Object get(String k) {
		return stores.get(STORE).get(k);
	}

	public String del(String k) {
		return stores.get(STORE).del(k);
	}

	public String hset(String k, String f, Object v) {
		return stores.get(STORE).hset(k, f, v);
	}

	public Object hget(String k, String f) {
		return stores.get(STORE).hget(k, f);
	}

	public String hdel(String k, String f) {
		return stores.get(STORE).hdel(k, f);
	}

	public Set hkeys(String k) {
		return stores.get(STORE).hkeys(k);
	}

	public Collection hvals(String k) {
		return stores.get(STORE).hvals(k);
	}

}
