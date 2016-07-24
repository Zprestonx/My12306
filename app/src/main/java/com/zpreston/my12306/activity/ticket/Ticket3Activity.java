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

    private TextView seat;
    private TextView seatPri;
    private TextView title;
    private TextView t;
    private TextView t1;
    private TextView t2;
    private TextView c1;
    private TextView c2;
    String seatMes;
    String seatNum;
    String seatPrice;
    String startTime;
    String startCity;
    String endCity;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket3);
        TicketFragment.activityS.add(this);
        Ticket3Activity.activityD.add(this);

        c1 = (TextView) this.findViewById(R.id.textView7);
        c2 = (TextView) this.findViewById(R.id.textView12);
        t1 = (TextView) this.findViewById(R.id.textView8);
        t2 = (TextView) this.findViewById(R.id.textView13);
        t = (TextView) this.findViewById(R.id.textView11);
        title = (TextView) this.findViewById(R.id.textView10);
        seat = (TextView) this.findViewById(R.id.textView9);
        seatPri = (TextView) this.findViewById(R.id.textView14);

        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ticket1");
        seatMes =bundle.getString("seatMes");
        seatNum =bundle.getString("seatNum");
        seatPrice=bundle.getString("seatPri");
        startCity = bundle.getString("beginCity");
        endCity = bundle.getString("endCity");
        String trainNo_ = bundle.getString("trainNo");  //设置对应车次信息
        String startTime_ = bundle.getString("startTime");
        String arriveTime_ = bundle.getString("arriveTime");
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        if (month < 10) {
            startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        } else {
            startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        }
        t.setText(startTime+"(0日)");                       //传日期,设置TextView
        c1.setText(startCity);
        c2.setText(endCity);
        t1.setText(startTime_);
        t2.setText(arriveTime_);
        title.setText(trainNo_);
        seatPri.setText(seatPrice);
        seat.setText(seatMes+"("+seatNum+")");

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
