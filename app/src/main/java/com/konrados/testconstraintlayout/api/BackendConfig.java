package com.konrados.testconstraintlayout.api;

import android.util.Log;

final class BackendConfig {

    private static final String TAG = "BackendConfig";
    private static final String BUILD_CONFIG_CLASS = "com.konrados.testconstraintlayout.BuildConfig";
    private static final String BASE_URL_FIELD = "BASE_URL";
    private static final String DEFAULT_BASE_URL = "https://api.konradcode.pl/api";


    private BackendConfig() {
    }

    static String getBaseUrl() {
        String baseUrl = DEFAULT_BASE_URL;
        try {
            Class<?> buildConfig = Class.forName(BUILD_CONFIG_CLASS);
            Object value = buildConfig.getField(BASE_URL_FIELD).get(null);
            if (value instanceof String url && !url.isEmpty()) {
                baseUrl = url;
            }
        } catch (ClassNotFoundException e) {
            Log.w(TAG, "BuildConfig class not found, using default backend URL");
        } catch (NoSuchFieldException e) {
            Log.w(TAG, "BASE_URL not defined in BuildConfig, using default backend URL");
        } catch (IllegalAccessException e) {
            Log.w(TAG, "No access to BuildConfig.BASE_URL, using default backend URL", e);
        }
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        return baseUrl;
    }
}
