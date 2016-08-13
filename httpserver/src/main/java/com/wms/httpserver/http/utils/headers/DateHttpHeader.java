package com.wms.httpserver.http.utils.headers;


import java.util.Date;

/**
 *
 * Standard date HTTP header response
 *
 *
 */
public class DateHttpHeader extends HttpHeader {

	public String toString() {
		return "Date: " + String.valueOf(new Date()) + HEADER_LINE_SEPARATOR;
	}
	
}
