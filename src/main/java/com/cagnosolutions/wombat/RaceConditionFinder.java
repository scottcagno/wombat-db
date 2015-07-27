package com.cagnosolutions.wombat;

import com.cagnosolutions.wombat.io.mappedbus.util.object.ObjectWriter;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

public class RaceConditionFinder {

	private Executor e = Executors.newFixedThreadPool(2);
	private final ObjectWriter objectWriter;

	public RaceConditionFinder(final ObjectWriter objectWriter) {
		this.objectWriter = objectWriter;
		init();
	}

	private void init() {
		System.out.println("RaceConditionFinder.init() -> RUNNING... ");
		for(int i = 0; i < 1000; i++) {
			int x = new Random().nextInt(5);
			e.execute(() -> objectWriter.run(x));
		}
		System.out.println("DONE.");
	}
}