package com.zpreston.my12306.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zpreston.my12306.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在加载布局文件前判断是否是首次打开
        SharedPreferences appInfo=getSharedPreferences("appInfo",MODE_WORLD_READABLE);
        boolean isFirst=appInfo.getBoolean("isFirst",true);
        if(isFirst){
            SharedPreferences.Editor editor=appInfo.edit();
            editor.putBoolean("isFirst",false);
            editor.commit();
            //首次打开则跳转至引导页面GuideActivity
            Intent intent=new Intent(SplashActivity.this,GuideActivity.class);
            startActivity(intent);
            finish();
        }else{
            //非首次打开，让启动页面SplashActivity显示2秒后启动主页面
            setContentView(R.layout.activity_splash);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run(){
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);
        }
    }
}