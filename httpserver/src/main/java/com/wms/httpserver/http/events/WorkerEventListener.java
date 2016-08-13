package com.wms.httpserver.http.events;

/**
 *
 * Listener interface for Worker events.  The Server type will listen for these events.
 *
 */
public interface WorkerEventListener {

	/**
	 *
	 * A resource was successfully served
	 *
	 * @param pResource
	 */
	void onRequestServed(String pResource);
	
	/**
	 *
	 * An error was encountered when trying to serve a resource
	 *
	 * @param pResource
	 */
	void onRequestError(String pResource);
}
