package com.wms.httpserver.http.utils.headers;

/**
 *
 * Standard Server HTTP header response
 *
 */
public class ServerHttpHeader extends HttpHeader {

	public String toString() {
		return "Server: AndroidHTTPServer (android/linux)" + HEADER_LINE_SEPARATOR;
	}
	
}
