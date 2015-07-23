package com.cagnosolutions.wombat.db;

import java.util.*;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class Value {

	private Object structure = null;

	public Value(Type type) {
		switch (type) {
			case LIST:
				this.structure = new ArrayList<>();
				break;
			case SET:
				this.structure = new HashSet<>();
				break;
			case SORTED_SET:
				this.structure = new TreeSet<>();
				break;
			case HASH:
				this.structure = new HashMap<>();
				break;
		}
	}

}
