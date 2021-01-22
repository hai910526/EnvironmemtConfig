package com.zly.environmemtconfig;

import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zly.environmemtconfig.utils.ConfigManager;
import com.zly.environmemtconfig.utils.PropertyUtils;

/**
 * Cerated by xiaoyehai
 * Create date : 2021/1/21 14:44
 * description :
 */
public class UrlEnvironmentActivity extends AppCompatActivity {

    private TextView mTvEnvShow;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_environment);

        mTvEnvShow = (TextView) findViewById(R.id.tvEnvShow);
        mRadioGroup = (RadioGroup) findViewById(R.id.group);

        mTvEnvShow.setText("环境：" + PropertyUtils.getEnvironmentName() + "\n" +
                "url：" + PropertyUtils.getApiBaseUrl());

        ConfigManager.EnvironmentType environmentType = PropertyUtils.environmentMap();

        switch (environmentType) {
            case TEST:
                mRadioGroup.check(R.id.rb_test);
                break;
            case PRE:
                mRadioGroup.check(R.id.rb_pre);
                break;
            case PRODUCT:
                mRadioGroup.check(R.id.rb_product);
                break;
            default:
                mRadioGroup.check(R.id.rb_test);
                break;
        }

        //点击切换环境
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_test:
                    if (ConfigManager.getDefault().getAppEnv() != ConfigManager.EnvironmentType.TEST) {
                        ConfigManager.getDefault().saveAppEnv(ConfigManager.EnvironmentType.TEST);
                    }
                    break;
                case R.id.rb_pre:
                    if (ConfigManager.getDefault().getAppEnv() != ConfigManager.EnvironmentType.PRE) {
                        ConfigManager.getDefault().saveAppEnv(ConfigManager.EnvironmentType.PRE);
                    }
                    break;
                case R.id.rb_product:
                    if (ConfigManager.getDefault().getAppEnv() != ConfigManager.EnvironmentType.PRODUCT) {
                        ConfigManager.getDefault().saveAppEnv(ConfigManager.EnvironmentType.PRODUCT);
                    }
                    break;
            }

            Toast.makeText(this, "1s后关闭App，重启生效", Toast.LENGTH_SHORT).show();

            //退出app要进行退出登录和去除数据相关

            // system.exit(0)、finish、android.os.Process.killProcess(android.os.Process.myPid())区别:
            //可以杀死当前应用活动的进程，这一操作将会把所有该进程内的资源（包括线程全部清理掉）。
            //当然，由于ActivityManager时刻监听着进程，一旦发现进程被非正常Kill，它将会试图去重启这个进程。这就是为什么，有时候当我们试图这样去结束掉应用时，发现它又自动重新启动的原因。
            //1. System.exit(0) 表示是正常退出。
            //2. System.exit(1) 表示是非正常退出，通常这种退出方式应该放在catch块中。
            //3. Process.killProcess 或 System.exit(0)当前进程确实也被 kill 掉了，但 app 会重新启动。

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Process.killProcess(Process.myPid());
                }
            }, 1000);

        });
    }
}
