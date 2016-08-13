package com.wms.httpserver.http.workers.simple;


/**
 *
 * An interface for simple workers
 *
 *
 */
public interface SimpleWorkerInterface {

	/**
	 *
	 * Take a request in the form of a work package and process it, resulting in a response with the 
	 * appropriate details, such as the data and the content type.
	 *
	 * @param pPackage
	 * @return
	 */
	public SimpleResponse handlePackage(SimpleRequest pPackage) throws SimpleWorkerException;
	
}
