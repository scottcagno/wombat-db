package com.cagnosolutions.wombat.db;

import java.util.stream.IntStream;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class Key implements CharSequence {

	private CharSequence key;

	public Key(CharSequence key) {
		this.key = key;
	}

	public int length() {
		return key.length();
	}

	public char charAt(int index) {
		return key.charAt(index);
	}

	public CharSequence subSequence(int start, int end) {
		return subSequence(start, end);
	}

	public IntStream chars() {
		return key.chars();
	}

	public IntStream codePoints() {
		return key.codePoints();
	}
}
