package com.zly.environmemtconfig.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Cerated by xiaoyehai
 * Create date : 2020/9/16 16:11
 * description :
 */
public class PropertyUtils {

    private static Properties mProps = new Properties();
    private static boolean mHasLoadProps = false;
    private static final Object mLock = new Object();
    private static final String TAG = "PropertyUtils";

    public PropertyUtils() {
    }

    /**
     * 在AppApplication中初始化
     */
    public static void init(Context context) {
        if (!mHasLoadProps) {
            synchronized (mLock) {
                if (!mHasLoadProps) {
                    try {
                        //获取环境类型
                        ConfigManager.EnvironmentType environmentType = ConfigManager.getDefault().getAppEnv();
                        //Log.e("xyh", "init: " + environmentType.configType + ".properties");
                        InputStream is = context.getAssets().open(environmentType.configType + ".properties");
                        mProps.load(is);
                        mHasLoadProps = true;
                        Log.e(TAG, "load config.properties successfully!");
                    } catch (IOException var4) {
                        Log.e(TAG, "load config.properties error!", var4);
                    }

                }
            }
        }
    }

    public static String getApiBaseUrl() {
        if (mProps == null) {
            throw new IllegalArgumentException("must call #UtilsManager.init(context) in application");
        } else {
            return mProps.getProperty(PropertyKey.BASE_URL, "");
        }
    }

    public static boolean isProduct() {
        return mProps.getProperty(PropertyKey.IS_PRODUCT, "false").equals("true");
    }


    public static String getEnvironmentName() {
        return mProps.getProperty(PropertyKey.NAME, "");
    }

    public static ConfigManager.EnvironmentType environmentMap() {
        String envName = getEnvironmentName();
        switch (envName) {
            case "test":
                return ConfigManager.EnvironmentType.TEST;
            case "pre":
                return ConfigManager.EnvironmentType.PRE;
            case "product":
                return ConfigManager.EnvironmentType.PRODUCT;
            default:
                return ConfigManager.EnvironmentType.DEFAULT;
        }
    }
}
