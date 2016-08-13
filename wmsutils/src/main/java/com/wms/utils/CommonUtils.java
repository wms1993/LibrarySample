package com.wms.utils;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wms1993 on 2015/12/19 0019.
 * some frequently used function which can help you improve your work efficiency.
 */
public class CommonUtils {

    /**
     * some frequently used MIME Type
     */
    private static final String[][] MIME_MapTable = {
            // {后缀名， MIME类型}
            {".png", "image/png"},
            {".jpg", "image/jpeg"},
            {".jpeg", "image/jpeg"},
            {".gif", "image/gif"},
            {".jpe", "image/jpeg"},
            {".mp3", "audio/mpeg"},
            {".wav", "audio/x-wav"},
            {".flac", "audio/flac"},
            {".ape", "audio/ape"},
            {".rmvb", "video/vnd.rn-realvideo"},
            {".mov", "video/quicktime"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".pdf", "application/pdf"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".txt", "text/plain"}, {".wps", "application/vnd.ms-works"},
            {"", "*/*"}};
    private static long lastClickTime;

    /**
     * prevent double click in short time
     *
     * @return is fastDoubleClick
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < ViewConfiguration.getDoubleTapTimeout()) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * get MIME type according to File's path
     *
     * @param filePath file's path
     * @return mime type
     */
    public static String getMIMEType(String filePath) {
        File file = new File(filePath);
        String type = "*/*";
        String fName = file.getName();
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if ("".equals(end))
            return type;
        for (String[] aMIME_MapTable : MIME_MapTable) {
            if (end.equals(aMIME_MapTable[0])) {
                type = aMIME_MapTable[1];
            }
        }
        return type;
    }

    /**
     * if you use URLEncode to encode string,the space will be encode to be + plus.
     *
     * @param str String to be encoded
     * @return encoded String
     */
    public static String encodeString(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                str = URLEncoder.encode(str, "utf-8");
                str = str.replaceAll("\\+", "%20");
                return str;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * get version code
     *
     * @param context context
     * @return version code
     */
    public static String getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * get app's name
     *
     * @param context context
     * @return app name
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get IMEI code
     *
     * @param context context
     * @return imei code
     */
    public static String getDeviceImei(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imeiCode = manager.getDeviceId();
        if (TextUtils.isEmpty(imeiCode)) {
            return getMacAddress(context);
        }
        return imeiCode;
    }

    /**
     * get mac address
     *
     * @param context context
     * @return mac address's md5 value
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAddress = info.getMacAddress();
        if (TextUtils.isEmpty(macAddress)) {
            macAddress = "12345";
        }
        return MD5.getMD5(macAddress);
    }

    /**
     * is phone number
     *
     * @param mobiles which will be check
     * @return true is phone number,false is not phone number!
     */
    public static boolean isMobileNO(String mobiles) {
        if (!TextUtils.isEmpty(mobiles) && mobiles.length() == 11) {
            Pattern p = Pattern.compile("[1][3758]\\d{9}");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } else {
            return false;
        }
    }

    /**
     * Determine whether the application is in the foreground
     *
     * @param context context
     * @return true is foreground , false is not.
     */
    public static boolean isAppOnForeground(Context context) {
        String topPackageName;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
                long time = System.currentTimeMillis();
                // We get usage stats for the last 10 seconds
                List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10, time);
                // Sort the stats by the last time used
                if (stats != null) {
                    SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                    for (UsageStats usageStats : stats) {
                        mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                    }
                    if (mySortedMap != null && !mySortedMap.isEmpty()) {
                        topPackageName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                        Log.e("TopPackage Name", topPackageName);
                        if (context.getPackageName().equals(
                                topPackageName)) {
                            return true;
                        }
                    }
                }
            } else {


                ActivityManager mActivityManager = ((ActivityManager) context
                        .getSystemService(Context.ACTIVITY_SERVICE));
                List<ActivityManager.RunningTaskInfo> tasksInfo = mActivityManager
                        .getRunningTasks(1);
                if (tasksInfo.size() > 0) {
                    if (context.getPackageName().equals(
                            tasksInfo.get(0).topActivity.getPackageName())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * check whether the list is empty
     *
     * @param list list
     * @return true is empty , false is not
     */
    public static boolean listIsEmpty(List list) {
        return !(list != null && !list.isEmpty());
    }

    /**
     * convert inputstream to string
     *
     * @param in inputStream
     * @return coverted string
     */
    public static String inputStream2String(InputStream in) {

        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int count = -1;
            while ((count = in.read(data, 0, 1024)) != -1)
                outStream.write(data, 0, count);

            data = null;
            return new String(outStream.toByteArray(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Transform sixteen binary code into a string
     *
     * @param string String that needs to be converted
     * @return Converted String
     */
    public static String hexToString(String string) {
        byte[] baKeyword = new byte[string.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(string.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            string = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return string;
    }

    /**
     * open soft keyboard
     *
     * @param editText editText
     * @param mContext context
     */
    public static void openKeybord(EditText editText, Context mContext) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    /**
     * close soft keyboard
     *
     * @param editText editText
     * @param mContext context
     */
    public static void closeKeybord(EditText editText, Context mContext) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * get md5 of file
     *
     * @param FilePath the path of file
     * @return the md5 of file
     */
    public static String getFileMd5(String FilePath) {
        char hexdigits[] =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                        'e', 'f'};
        FileInputStream fis;
        String sString;
        char str[] = new char[16 * 2];
        int k = 0;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(FilePath);
            byte[] buffer = new byte[2048];
            int length = -1;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] b = md.digest();

            for (int i = 0; i < 16; i++) {
                byte byte0 = b[i];
                str[k++] = hexdigits[byte0 >>> 4 & 0xf];
                str[k++] = hexdigits[byte0 & 0xf];
            }
            fis.close();
            sString = new String(str);

            return sString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * get weeks array
     *
     * @return the array contains weeks
     */
    public static String[] getWeeks() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        String dayOfWeek = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        return new String[]{dayOfWeek};
    }

    /**
     * get today's week
     * 0 represend Sunday ...
     *
     * @return today's number
     */
    public static int getTodayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * get the width and height of file
     *
     * @param filePath the path of file
     * @return float array contains width and height
     */
    public static float[] getPicWidthAndHeight(String filePath) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile((filePath), opts);
        // 获取到bitmap的高度和宽度
        float[] arr = new float[2];
        arr[0] = opts.outWidth;
        arr[1] = opts.outHeight;
        if (bitmap != null) {
            bitmap.recycle();
        }
        return arr;
    }

    /**
     * check whether the file is image according to suffixes
     *
     * @param fileName
     * @return true is image , false is not .
     */
    public static boolean isImage(String fileName) {
        String fileType = null;
        if (fileName != null) {
            int typeIndex = fileName.lastIndexOf(".");
            if (typeIndex != -1) {
                fileType = fileName.substring(typeIndex + 1).toLowerCase();
            }
        }
        return fileType != null
                && (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("gif")
                || fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("jpeg")
                || fileType.equalsIgnoreCase("bmp") || fileType.equalsIgnoreCase("wbmp")
                || fileType.equalsIgnoreCase("psd") || fileType.equalsIgnoreCase("cdr")
                || fileType.equalsIgnoreCase("ico") || fileType.equalsIgnoreCase("jpe"));
    }

    /**
     * get the name of file
     *
     * @param filePath filepath
     * @return the name of file
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        int start = filePath.lastIndexOf("/");
        int end = filePath.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return filePath.substring(start + 1, end);
        } else {
            return "";
        }
    }
}
