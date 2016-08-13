package com.wms.utils;

import com.socks.library.KLog;

/**
 * Created by wms1993 on 2016/6/28 0028.
 * log utils
 */
public class LogUtils {

    public static void init(boolean isShowLog) {
        KLog.init(isShowLog);
    }

    public static void init(boolean isShowLog, String tag) {
        KLog.init(isShowLog,tag);
    }

    public static void v() {
        KLog.v();
    }

    public static void v(Object msg) {
        KLog.v(msg);
    }

    public static void v(String tag, Object... objects) {
        KLog.v(tag, objects);
    }

    public static void d() {
        KLog.d();
    }

    public static void d(Object msg) {
        KLog.d(msg);
    }

    public static void d(String tag, Object... objects) {
        KLog.d(tag, objects);
    }

    public static void i() {
        KLog.i();
    }

    public static void i(Object msg) {
        KLog.i(msg);
    }

    public static void i(String tag, Object... objects) {
        KLog.i(tag, objects);
    }

    public static void w() {
        KLog.w();
    }

    public static void w(Object msg) {
        KLog.w(msg);
    }

    public static void w(String tag, Object... objects) {
        KLog.w(tag, objects);
    }

    /**
     * print error
     */
    public static void e() {
        KLog.e();
    }

    public static void e(Object msg) {
        KLog.e(msg);
    }

    public static void e(String tag, Object... objects) {
        KLog.e(tag, objects);
    }

    public static void a() {
        KLog.a();
    }

    public static void a(Object msg) {
        KLog.a(msg);
    }

    public static void a(String tag, Object... objects) {
        KLog.a(tag, objects);
    }

    /**
     * print json
     *
     * @param jsonFormat
     */
    public static void json(String jsonFormat) {
        KLog.json(jsonFormat);
    }

    /**
     * print json with tag
     *
     * @param jsonFormat
     */
    public static void json(String tag, String jsonFormat) {
        KLog.json(tag, jsonFormat);
    }

    /**
     * print xml
     *
     * @param xml
     */
    public static void xml(String xml) {
        KLog.xml(xml);
    }

    /**
     * print xml with tag
     *
     * @param xml
     */
    public static void xml(String tag, String xml) {
        KLog.xml(tag, xml);
    }
}
