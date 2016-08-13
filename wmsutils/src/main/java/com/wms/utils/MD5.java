/**
 * ****************************************************************************
 * Copyright (C) 2009-2010 eoeMobile.
 * All rights reserved.
 * http://www.eoeMobile.com/
 * <p>
 * CHANGE LOG:
 * DATE			AUTHOR			COMMENTS
 * =============================================================================
 * 2010MAY11		Waznheng Ma		Refine for Constructor and error handler.
 * <p>
 * *****************************************************************************
 */

package com.wms.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wms1993 on 2015/12/19 0019.
 */
public final class MD5 {
    private static final String LOG_TAG = "MD5";
    private static final String ALGORITHM = "MD5";

    private static char sHexDigits[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static MessageDigest sDigest;

    static {
        try {
            sDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            Log.e(LOG_TAG, "Get MD5 Digest failed.");
        }
    }

    private MD5() {
    }

    /**
     * get strings’s md5 value
     *
     * @param string string
     * @return string‘s md5 value
     */
    public static String getMD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toLowerCase();
    }
}
