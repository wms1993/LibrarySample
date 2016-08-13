package com.wms.httpserver.http.workers.simple;

/**
 *
 * Response to a simple request package.  Contains the mimetype and the actual data.
 *
 *
 */
public class SimpleResponse {

	/** The actual response that will be sent to the client */
	private byte[] mResponse;
	
	/** The mime type of the data that will be sent to the client, e.g. "text/html" etc */
	private String mMimeType;
	
	/**
	 *
	 * Creates a new simple response object
	 *
	 * @param pMimeType
	 * @param pResponse
	 */
	public SimpleResponse(String pMimeType, byte[] pResponse) {
		mResponse = pResponse;
		mMimeType = pMimeType;
	}

	/**
	 *
	 * Gets the response data that is to be sent to the client
	 *
	 * 
	 * @return
	 */
	public byte[] getResponse() {
		return mResponse;
	}

	/**
	 *
	 * Sets the response data to be sent to the client
	 *
	 * 
	 * @param pResponse
	 */
	public void setResponse(byte[] pResponse) {
		mResponse = pResponse;
	}

	/**
	 *
	 * Gets the mime type
	 *
	 * 
	 * @return
	 */
	public String getMimeType() {
		return mMimeType;
	}

	/**
	 *
	 * Sets the mime type
	 *
	 * 
	 * @param pMimeType
	 */
	public void setMimeType(String pMimeType) {
		mMimeType = pMimeType;
	}

}
