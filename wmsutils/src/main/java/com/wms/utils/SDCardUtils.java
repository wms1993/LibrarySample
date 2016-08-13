package com.wms.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * SD card related auxiliary class
 */
public class SDCardUtils {

    private SDCardUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Check whether sdcard is available.
     *
     * @return true is available , false is not
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * Get the path of sdcard
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * Get the total size of the SD card
     *
     * @return unit is byte
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * Get the rest of the SD card unit byte
     *
     * @param filePath the path of file
     * @return unit is byte
     */
    public static long getFreeBytes(String filePath) {
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

}
