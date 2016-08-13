package com.wms.httpserver.utils;

import android.util.Log;

/**
 *
 * Facade for standard logger with a given tag
 *
 * @author Matt
 *
 */
public class Logger {

	/** Logger tag */
	private static final String LOGGING_TAG = "matt1.http";
	
	/**
	 *
	 * Logs a debug message
	 *
	 * @param pMessage
	 */
	public static void debug(String pMessage) {
		Log.d(LOGGING_TAG, pMessage);
	}
	
	/**
	 *
	 * Logs an info message
	 *
	 * @param pMessage
	 */
	public static void info(String pMessage) {
		Log.i(LOGGING_TAG, pMessage);
	}
	
	/**
	 *
	 * Logs an error message
	 *
	 * @param pMessage
	 */
	public static void error(String pMessage) {
		Log.e(LOGGING_TAG, pMessage);
	}
	
	/**
	 *
	 * Logs a warning message
	 *
	 * @param pMessage
	 */
	public static void warn(String pMessage) {
		Log.w(LOGGING_TAG, pMessage);
	}
	
}
