package com.zpreston.my12306.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zpreston.my12306.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在加载布局文件前判断是否是首次打开
        setContentView(R.layout.activity_splash);//文件自带，是否要删？
        SharedPreferences appInfo=getSharedPreferences("appIinfo",MODE_WORLD_READABLE);
        boolean isFirst=appInfo.getBoolean("isFirst",true);
        if(isFirst){
            SharedPreferences.Editor editor=appInfo.edit();

        }
    }
}
