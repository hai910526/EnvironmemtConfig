package com.zly.environmemtconfig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zly.environmemtconfig.utils.Constants;
import com.zly.environmemtconfig.utils.PropertyUtils;

/**
 * 环境切换
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnSwitchEnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mBtnSwitchEnv = (Button) findViewById(R.id.btn_switch_env);

        Log.e("xyh", "getEnvironmentName: " + PropertyUtils.getEnvironmentName());
        Log.e("xyh", "getApiBaseUrl: " + PropertyUtils.getApiBaseUrl());
        Log.e("xyh", "isProduct: " + PropertyUtils.isProduct());

        //开发版本才可以切换环境
        if (Constants.AUTO.equals(BuildConfig.FLAVOR)) {
            mBtnSwitchEnv.setOnClickListener(v -> {
                startActivity(new Intent(this, UrlEnvironmentActivity.class));

            });
        }
    }
}