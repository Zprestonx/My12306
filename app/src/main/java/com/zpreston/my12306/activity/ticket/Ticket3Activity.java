package com.zpreston.my12306.activity.ticket;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zpreston.my12306.R;
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.ArrayList;

public class Ticket3Activity extends AppCompatActivity {
    public static ArrayList<Activity> activityD = new ArrayList<Activity>();
    private TextView q;
    private Button w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket3);
        TicketFragment.activityS.add(this);
        Ticket3Activity.activityD.add(this);

        q = (TextView) this.findViewById(R.id.textView15);
       // w = (Button) this.findViewById(R.id.button5);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket3Activity.this, Ticket3_AddActivity.class);
                startActivity(intent);
            }
        });
        /*w = (Button) this.findViewById(R.id.button4);
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket3Activity.this, Ticket4Activity.class);
                startActivity(intent);
            }
        });*/
    }

    public void submit(View view) { //提交按钮跳转
        Intent intent=new Intent(this,Ticket4Activity.class);
        startActivity(intent);
    }
}
