package com.bhuwan.main.debug;

import java.util.Date;

public class debugMessage {
	private static boolean init = false;
	private static long init_time;

	public static void output(String str) {
		Date d = new Date();
		if (!init) {
			init = true;
			init_time = d.getTime();
		}
		final Throwable t = new Throwable();
		final StackTraceElement methodCaller = t.getStackTrace()[1];

		System.out.println(d.getTime() + " " + (d.getTime() - init_time) + " ("
				+ methodCaller.getClassName() + "):\t" + str);

	}
}
