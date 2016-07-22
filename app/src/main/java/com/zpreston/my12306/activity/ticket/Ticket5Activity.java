package com.zpreston.my12306.activity.ticket;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.MainActivity;
import com.zpreston.my12306.fragment.OrderFragment;
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ticket5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket5);
        Toast.makeText(Ticket5Activity.this, "订单支付成功！", Toast.LENGTH_LONG).show();
    }

    public void back(View view) { //返回按钮跳转
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

        for (Activity activity : TicketFragment.activityS) { //将车票预定1到4的Activity Finish掉
            activity.finish();
        }

        finish();
    }



    /*public class ActivityManagerApplication extends Application { //finish指定Activity

        private *//*static *//*Map<String,Activity> destoryMap = new HashMap<>();

        private ActivityManagerApplication() {
        }

        *//**
         * 添加到销毁队列
         *
         * @param activity 要销毁的activity
         *//*

        @param activity 要销毁的activit

        public*//* static *//*void addDestoryActivity(Activity activity,String activityName) {
//            destoryMap.put(activityName,activity);
            destoryMap.put(Ticket4Activity.class,activity);
        }
        *//**
         *销毁指定Activity
         *//*
        public *//*static *//* void destoryActivity(String activityName) {
            Set<String> keySet=destoryMap.keySet();
            for (String key:keySet){
                destoryMap.get(key).finish();
            }
        }
    }*/
}
