package com.zpreston.my12306.activity.ticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.Ticket1Adapter;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.TrainDao;
import com.zpreston.my12306.daoImpl.TrainDaoImpl;
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Ticket1Activity extends AppCompatActivity {
    private ImageView q;
    private ListView lvOpt;
    private TextView t1;
    private TextView t2;
    public Button pre_day;
    public Button next_day;

    private String a;
    private String b;
    private String c;
    private String d;
    String startCity;
    String endCity;
    int year;
    int month;
    int day;
    private List<Map<String, Object>> mData;
    String startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket1);
        //Log.e("***","aaa");
        TicketFragment.activityS.add(this);

        t1 = (TextView) this.findViewById(R.id.date1);
        t2 = (TextView) this.findViewById(R.id.textView3);
        pre_day = (Button) this.findViewById(R.id.button);
        next_day = (Button) this.findViewById(R.id.button2);

        Intent intent = getIntent();
        startCity = intent.getStringExtra("beginCity");
        endCity = intent.getStringExtra("endCity");
        year = intent.getIntExtra("year", 0);
        month = intent.getIntExtra("month", 0);
        day = intent.getIntExtra("day", 0);
        if (month < 10) {
            startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        } else {
            startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        }
        t1.setText(startTime);                       //传日期
        t2.setText(startCity + "->" + endCity);

        lvOpt = (ListView) this.findViewById(R.id.listView2);
        //获得数据
        mData = getData();
        final Ticket1Adapter ticket1Adapter = new Ticket1Adapter(getData(), Ticket1Activity.this);
        lvOpt.setAdapter(ticket1Adapter);

        //前一天与后一天按钮功能实现
        pre_day.setOnClickListener(new View.OnClickListener() {  //前一天
            @Override
            public void onClick(View v) {//待支付
                Toast.makeText(Ticket1Activity.this, "click 前一天", Toast.LENGTH_LONG).show();
                day = day - 1;
                if (month < 10) {
                    startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                } else {
                    startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                }
                t1.setText(startTime);                       //传日期
                mData = getData();
                ticket1Adapter.setmData(mData);
                ticket1Adapter.notifyDataSetChanged();
            }
        });
        next_day.setOnClickListener(new View.OnClickListener() {  //后一天
            @Override
            public void onClick(View v) {//待支付
                Toast.makeText(Ticket1Activity.this, "click 后一天", Toast.LENGTH_LONG).show();
                day = day + 1;
                if (month < 10) {
                    startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                } else {
                    startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
                }
                t1.setText(startTime);                       //传日期
                mData = getData();
                ticket1Adapter.setmData(mData);
                ticket1Adapter.notifyDataSetChanged();
            }
        });
        //响应条目的点击事件
        lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将bundle存放到意图并跳转,点击条目进行跳转
                putData(position);
            }
        });
        //Log.e("***","2");
        /*q = (ImageView) this.findViewById(R.id.im1_2);

        q.setOnClickListener(new View.OnClickListener() {   //页面跳转
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket1Activity.this, Ticket2Activity.class);
                startActivity(intent);
            }
        });*/
    }

    private List<Map<String, Object>> getData() {//实现Map的数据构造
        //创建一个ArrayList来存放Map
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        //创建Map来存放数据

        TrainDao trainDao = new TrainDaoImpl(this);
        List<Train> train = trainDao.queryTrain(startCity, endCity, startTime);
        for (Train train1 : train) {
            Random random = new Random();
            //第一个条目
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("trainNo", train1.getTrainNo());
            map.put("startTime", train1.getStartTime());
            map.put("arriveTime", train1.getArriveTime());
            map.put("seatMes1", "高级软卧");
            map.put("p1", ":");
            a = String.valueOf(random.nextInt(35)+5);
            map.put("seatNum1", a);
            map.put("seatMes2", "硬座");
            map.put("p2", ":");
            b = String.valueOf(random.nextInt(35)+5);
            map.put("seatNum2", b);
            map.put("seatMes3", "一等座");
            map.put("p3", ":");
            c = String.valueOf(random.nextInt(35)+5);
            map.put("seatNum3", c);
            map.put("seatMes4", "特等座");
            map.put("p4", ":");
            d = String.valueOf(random.nextInt(35)+5);
            map.put("seatNum4", d);
            map.put("tranState", R.drawable.s_end);
            map.put("im1_2", R.drawable.forward_icon);
            data.add(map);

            /*//第二个条目
            map = new HashMap<String, Object>();
            map.put("trainNo", "D29");
            map.put("startTime", "07:00");
            map.put("arriveTime", "11:48(0日)");
            map.put("seatMes1", "特等座");
            map.put("p1", ":");
            map.put("seatNum1", "4");
            map.put("seatMes2", "硬座");
            map.put("p1", ":");
            map.put("seatNum2", "20");
            map.put("seatMes3", "软座");
            map.put("p1", ":");
            map.put("seatNum3", "7");
            map.put("seatMes4", "硬卧");
            map.put("p1", ":");
            map.put("seatNum4", "19");
            map.put("tranState", R.drawable.s_pro);
            map.put("im1_2", R.drawable.forward_icon);
            data.add(map);*/

        }

        return data;
    }

    private void putData(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("beginCity",startCity);
        bundle.putString("endCity",endCity);
        bundle.putInt("year",year);
        bundle.putInt("month",month);
        bundle.putInt("day",day);
        bundle.putString("trainNo", (String) mData.get(position).get("trainNo"));
        bundle.putString("startTime", (String) mData.get(position).get("startTime"));
        bundle.putString("arriveTime", (String) mData.get(position).get("arriveTime"));
        bundle.putString("seatMes1", (String) mData.get(position).get("seatMes1"));
        bundle.putString("seatNum1", a);
        bundle.putString("seatMes2", (String) mData.get(position).get("seatMes2"));
        bundle.putString("seatNum2", b);
        bundle.putString("seatMes3", (String) mData.get(position).get("seatMes3"));
        bundle.putString("seatNum3", c);
        bundle.putString("seatMes4", (String) mData.get(position).get("seatMes4"));
        bundle.putString("seatNum4", d);

        Intent intent = new Intent(Ticket1Activity.this, Ticket2Activity.class);
        intent.putExtra("ticket1", bundle);
        startActivity(intent);
    }
}
