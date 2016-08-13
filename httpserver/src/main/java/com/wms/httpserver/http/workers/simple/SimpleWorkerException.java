package com.wms.httpserver.http.workers.simple;


import com.wms.httpserver.http.utils.HttpStatus;

/**
 *
 * Exception used to communicate HTTP errors during pack worker failures
 *
 *
 */
public class SimpleWorkerException extends Exception {

	/** Auto-generated serial ID */
	private static final long serialVersionUID = 6399757752615173636L;
	
	/** HTTP status code related to this exception (e.g. 404 not found, 500 server error etc) */
	private HttpStatus mStatus;
	
	/**
	 *
	 * Gets the HTTP status code assocaited with this exception
	 *
	 * @return
	 */
	public HttpStatus getStatus() {
		return mStatus;
	}
	
	/**
	 *
	 * Creates a new simple worker exception.
	 *
	 * @param pStatus
	 */
	public SimpleWorkerException(HttpStatus pStatus) {
		super();
		mStatus = pStatus;
	}
	
	/**
	 *
	 * Creates a new simple worker exception.
	 *
	 * @param pStatus
	 */
	public SimpleWorkerException(HttpStatus pStatus, String pMessage) {
		super(pMessage);
		mStatus = pStatus;
	}
	
	/**
	 *
	 * Creates a new simple worker exception.
	 *
	 * @param pStatus
	 */
	public SimpleWorkerException(HttpStatus pStatus, Exception pCause) {
		super(pCause);
		mStatus = pStatus;
	}
	
	/**
	 *
	 * Creates a new simple worker exception.
	 *
	 * @param pStatus
	 */
	public SimpleWorkerException(HttpStatus pStatus, String pMessage, Exception pCause) {
		super(pMessage, pCause);
		mStatus = pStatus;
	}
	
}
