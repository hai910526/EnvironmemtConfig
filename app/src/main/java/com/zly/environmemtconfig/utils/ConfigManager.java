package com.zly.environmemtconfig.utils;

import android.text.TextUtils;
import android.util.Log;

import com.zly.environmemtconfig.BuildConfig;


/**
 * 环境配置管理类
 */
public class ConfigManager {

    //当前环境
    private EnvironmentType mCurrentEnvType;

    private static final String APP_ENV = "appEnv";

    private ConfigManager() {
    }

    public static ConfigManager getDefault() {
        return HOLDER.INSTANCE;
    }

    private static class HOLDER {
        static ConfigManager INSTANCE = new ConfigManager();
    }

    /***
     * 保存环境：指在切换环境时调用一次
     */
    public void saveAppEnv(EnvironmentType type) {
        SpUtils.putString(APP_ENV, type.configType);
    }


    /***
     * 获取环境类型
     */
    public EnvironmentType getAppEnv() {

        if (mCurrentEnvType == null) {
           // Log.e("xyh", "FLAVOR: " + BuildConfig.FLAVOR);
            String env;
            if (Constants.AUTO.equals(BuildConfig.FLAVOR)) {
                env = SpUtils.getString(APP_ENV, EnvironmentType.DEFAULT.configType);
                if (TextUtils.isEmpty(env)) {
                    env = EnvironmentType.DEFAULT.configType;
                }
            } else {
                env = EnvironmentType.DEFAULT.configType;
            }
            mCurrentEnvType = EnvironmentType.map(env);
        }
        return mCurrentEnvType;
    }


    //环境类型
    public enum EnvironmentType {

        /***
         * 默认环境   config:环境配置文件名
         */
        DEFAULT("config"),

        /***
         * test环境
         */
        TEST("configTest"),

        /***
         * 预发布环境
         */
        PRE("configPre"),

        /***
         * 线上环境
         */
        PRODUCT("configProduct");

        String configType;

        EnvironmentType(String configType) {
            this.configType = configType;
        }

        public static EnvironmentType map(String configType) {
            if (TextUtils.equals(EnvironmentType.TEST.configType, configType)) {
                return EnvironmentType.TEST;
            } else if (TextUtils.equals(EnvironmentType.PRE.configType, configType)) {
                return EnvironmentType.PRE;
            } else if (TextUtils.equals(EnvironmentType.PRODUCT.configType, configType)) {
                return EnvironmentType.PRODUCT;
            } else {
                return EnvironmentType.DEFAULT;
            }
        }
    }

}
