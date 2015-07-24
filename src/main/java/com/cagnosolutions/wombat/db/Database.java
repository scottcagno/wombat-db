package com.cagnosolutions.wombat.db;

import java.util.ArrayList;
import java.util.List;

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

	public String get(String k) {
		return stores.get(STORE).get(k);
	}

	public String del(String k) {
		return stores.get(STORE).del(k);
	}
}
