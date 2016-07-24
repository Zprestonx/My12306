package com.zpreston.my12306.activity.ticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.Ticket1Adapter;
import com.zpreston.my12306.adapter.Ticket2Adapter;
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket2Activity extends AppCompatActivity {
    private TextView trainNo;
    private TextView Time;
    private TextView cTime;
    private ListView lvOpt;
    private List<Map<String, Object>> mData;
    private Button q;

    private TextView t1;
    private TextView t2;
    public Button pre_day;
    public Button next_day;
    String startTime;
    String startCity;
    String endCity;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket2);
        TicketFragment.activityS.add(this); //加入到Activity队列中

        t1 = (TextView) this.findViewById(R.id.date2);
        t2 = (TextView) this.findViewById(R.id.textView2_3);
        pre_day = (Button) this.findViewById(R.id.button3);
        next_day = (Button) this.findViewById(R.id.button4);

        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ticket1");
        startCity = bundle.getString("beginCity");
        endCity = bundle.getString("endCity");
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        if (month < 10) {
            startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        } else {
            startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        }
        t1.setText(startTime);                       //传日期
        t2.setText(startCity + "->" + endCity);

        String trainNo_ = bundle.getString("trainNo");  //设置对应车次信息
        String startTime_ = bundle.getString("startTime");
        String arriveTime_ = bundle.getString("arriveTime");

        trainNo = (TextView) findViewById(R.id.textView4);
        Time = (TextView) findViewById(R.id.textView5);
        lvOpt = (ListView) findViewById(R.id.listView3);

        trainNo.setText(trainNo_);
        Time.setText("出发时间:"+startTime_ + ",到达时间:" + arriveTime_);


        lvOpt= (ListView) this.findViewById(R.id.listView3);
        //获得数据
        mData = getData(intent);
        final Ticket2Adapter ticket2Adapter = new Ticket2Adapter(getData(intent), Ticket2Activity.this);
        lvOpt.setAdapter(ticket2Adapter);
        ticket2Adapter.setYear(year);
        ticket2Adapter.setMonth(month);
        ticket2Adapter.setDay(day);
        ticket2Adapter.setTrainNo(trainNo_);
        ticket2Adapter.setBeginCity(startCity);
        ticket2Adapter.setEndCity(endCity);
        ticket2Adapter.setStartTime(startTime_);
        ticket2Adapter.setArriveTime(arriveTime_);



        //前一天与后一天按钮功能实现
        pre_day.setOnClickListener(new View.OnClickListener() {  //前一天
            @Override
            public void onClick(View v) {
                Toast.makeText(Ticket2Activity.this, "click 前一天", Toast.LENGTH_LONG).show();
                day = day - 1;
                if (month < 10) {
                    startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                } else {
                    startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                }
                t1.setText(startTime);                       //传日期
                mData = getData(intent);
                ticket2Adapter.setmData(mData);
                ticket2Adapter.notifyDataSetChanged();
            }
        });
        next_day.setOnClickListener(new View.OnClickListener() {  //后一天
            @Override
            public void onClick(View v) {
                Toast.makeText(Ticket2Activity.this, "click 后一天", Toast.LENGTH_LONG).show();
                day = day + 1;
                if (month < 10) {
                    startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                } else {
                    startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                }
                t1.setText(startTime);                       //传日期
                mData = getData(intent);
                ticket2Adapter.setmData(mData);
                ticket2Adapter.notifyDataSetChanged();
            }
        });
        //响应条目的点击事件
        /*lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(Ticket2Activity.this, "点击了我的账户" + position, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(Ticket2Activity.this,"点击了我的联系人"+position,Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(Ticket2Activity.this,"点击了我的密码"+position,Toast.LENGTH_LONG).show();
                        break;
                }
                //将bundle存放到意图并跳转
                putData();
            }
        });*/
        //将bundle存放到意图并跳转
        putData();
    }

    private List<Map<String, Object>> getData(Intent intent) {//实现Map的数据构造
        Bundle bundle = intent.getBundleExtra("ticket1");
        String seatMes1_ = bundle.getString("seatMes1");
        String seatNum1_ = bundle.getString("seatNum1");
        String seatPri1_ = bundle.getString("seatPri1");
        String seatMes2_ = bundle.getString("seatMes2");
        String seatNum2_ = bundle.getString("seatNum2");
        String seatPri2_ = bundle.getString("seatPri3");
        String seatMes3_ = bundle.getString("seatMes3");
        String seatNum3_ = bundle.getString("seatNum3");
        String seatPri3_ = bundle.getString("seatPri3");
        String seatMes4_ = bundle.getString("seatMes4");
        String seatNum4_ = bundle.getString("seatNum4");
        String seatPri4_ = bundle.getString("seatPri4");
        //创建一个ArrayList来存放Map
        List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
        //创建Map来存放数据
        //第一个条目
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("seatMes",seatMes1_);
        map.put("seatNum",seatNum1_);
        map.put("seatPri","154");
        data.add(map);

        //第二个条目
        map=new HashMap<String,Object>();
        map.put("seatMes",seatMes2_);
        map.put("seatNum",seatNum2_);
        map.put("seatPri","152");
        data.add(map);

        //第三个条目
        map=new HashMap<String,Object>();
        map.put("seatMes",seatMes3_);
        map.put("seatNum",seatNum3_);
        map.put("seatPri","268");
        data.add(map);

        //第四个条目
        map=new HashMap<String,Object>();
        map.put("seatMes",seatMes4_);
        map.put("seatNum",seatNum4_);
        map.put("seatPri","216");
        data.add(map);
        return data;
    }

    private void putData() {
        /*Bundle bundle=new Bundle();
        *//*bundle.putString("trainNo",(String)mData.get(position).get("trainNo"));
        bundle.putString("startTime",(String)mData.get(position).get("startTime"));
        bundle.putString("arriveTime",(String)mData.get(position).get("arriveTime"));
        bundle.putString("seatMes1",(String)mData.get(position).get("seatMes1"));
        bundle.putString("seatNum1",(String)mData.get(position).get("seatNum1"));
        bundle.putString("seatPri1",(String)mData.get(position).get("seatPri1"));
        bundle.putString("seatMes2",(String)mData.get(position).get("seatMes2"));
        bundle.putString("seatNum2",(String)mData.get(position).get("seatNum2"));
        bundle.putString("seatPri2",(String)mData.get(position).get("seatPri2"));
        bundle.putString("seatMes3",(String)mData.get(position).get("seatMes3"));
        bundle.putString("seatNum3",(String)mData.get(position).get("seatNum3"));
        bundle.putString("seatPri3",(String)mData.get(position).get("seatPri3"));
        bundle.putString("seatMes4",(String)mData.get(position).get("seatMes4"));
        bundle.putString("seatNum4",(String)mData.get(position).get("seatNum4"));
        bundle.putString("seatPri4",(String)mData.get(position).get("seatPri4"));*//*

        bundle.putString("beginCity",startCity);
        bundle.putString("endCity",endCity);
        bundle.putInt("year",year);
        bundle.putInt("month",month);
        bundle.putInt("day",day);

        Intent intent = new Intent(Ticket2Activity.this, Ticket3Activity.class);
        intent.putExtra("ticket1", bundle);
        //startActivity(intent);*/
    }
}
