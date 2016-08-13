package com.wms.httpserver.http.workers.simple;


import com.wms.httpserver.http.utils.HttpMethod;

/**
 *
 * A "request" package used to pass details of a request down to a worker for processing. 
 *
 *
 */
public class SimpleRequest {

	/** The method for this request, e.g. "GET" etc */
	private HttpMethod mMethod;
	
	/** The requested URI for this request - might or might not be an actual file! */
	private String mResource;
	
	/**
	 *
	 * Creates a new Work Package for a worker to use to process the request.
	 *
	 * @param pMethod
	 * @param pResource
	 */
	public SimpleRequest(HttpMethod pMethod, String pResource) {
		mMethod = pMethod;
		mResource = pResource;
	}

	/**
	 *
	 * Gets the HTTP method for this request
	 *
	 * @return
	 */
	public HttpMethod getMethod() {
		return mMethod;
	}

	/**
	 *
	 * Sets the HTTP method used for this request
	 *
	 * 
	 * @param pResponse
	 */
	public void setMethod(HttpMethod pMethod) {
		mMethod = pMethod;
	}

	/**
	 *
	 * Gets the resource requested for this request
	 *
	 * @return
	 */
	public String getResource() {
		return mResource;
	}

	/**
	 *
	 * Sets the resource requested
	 *
	 * 
	 * @param pResponse
	 */
	public void setResource(String pResource) {
		mResource = pResource;
	}
	
	
}
