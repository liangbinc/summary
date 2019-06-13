package com.lbc.mo.utils;

public class StrUtil {
    private StrUtil() {
    }

    public static String appIdToUser(String appId) {
        return appId.substring(appId.indexOf("_") + 1, appId.lastIndexOf("_"));
    }
}
