package com.cagnosolutions.wombat.db;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class Database {

	private ConcurrentMap<Key,Value> database = new ConcurrentHashMap<>(16, 0.75f, 2);



}
