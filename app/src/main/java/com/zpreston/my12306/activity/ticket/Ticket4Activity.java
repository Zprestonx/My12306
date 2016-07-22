package com.zpreston.my12306.activity.ticket;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.MainActivity;
import com.zpreston.my12306.fragment.OrderFragment;
import com.zpreston.my12306.fragment.TicketFragment;

public class Ticket4Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket4);
        TicketFragment.activityS.add(this);
    }

    public void no(View view) { //暂不支付按钮跳转
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

        for (Activity activity : TicketFragment.activityS) { //将车票预定1到3的Activity Finish掉
            activity.finish();
        }
        finish();
    }

    public void yes(View view) { //确认支付按钮跳转
        Intent intent=new Intent(this,Ticket5Activity.class);
        startActivity(intent);
    }
}
