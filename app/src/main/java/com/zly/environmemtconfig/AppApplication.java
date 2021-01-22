package com.zly.environmemtconfig;

import android.app.Application;
import com.zly.environmemtconfig.utils.PropertyUtils;
import com.zly.environmemtconfig.utils.SpUtils;


/**
 * Cerated by xiaoyehai
 * Create date : 2020/9/16 16:48
 * description :
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SpUtils.init(this);

        PropertyUtils.init(this);

    }
}
