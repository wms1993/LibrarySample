package com.wms.httpserver.http.utils.headers;

/**
 *
 * Represents a HTTP Header, e.g. "Content-type: text/html"
 *
 *
 */
public class HttpHeader {

	private String mKey;	
	private String mValue;
	
	/** Constants for Android string optimisations */
	private static final String HEADER_SEPARATOR = ": ";
	protected static final String HEADER_LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 *
	 * Creates a new HTTP Header element.
	 *
	 */
	public HttpHeader() {

	}
	
	/**
	 *
	 * Creates a new HTTP Header element.
	 *
	 * @param pKey The key to use (do not include the colon character)
	 * @param pValue The value to use
	 */
	public HttpHeader(String pKey, String pValue) {
		mKey = pKey;
		mValue = pValue;
	}
	
	/**
	 *
	 * Get the header value
	 *
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(mKey).append(HEADER_SEPARATOR).append(mValue).append(HEADER_LINE_SEPARATOR);
		return buffer.toString();
	}
	
	/**
	 *
	 * Get the header value as a byte array
	 *
	 */
	public byte[] getBytes() {
		return getBytesFromString(toString());
	}

	public byte[] getBytesFromString(String pInput) {

		byte[] result = new byte[pInput.length()];
		char[] characters = pInput.toCharArray();
		int position = 0;

		for (char c : characters)  {
			result[position] = (byte) c;
			position++;
		}

		return result;
	}
	
	public String getKey() {
		return mKey;
	}

	public void setKey(String pKey) {
		mKey = pKey;
	}

	public String getValue() {
		return mValue;
	}

	public void setValue(String pValue) {
		mValue = pValue;
	}
	
	
	
}
