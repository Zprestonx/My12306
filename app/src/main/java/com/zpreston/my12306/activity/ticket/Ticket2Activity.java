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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket2);
        TicketFragment.activityS.add(this); //加入到Activity队列中

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ticket1");
        String trainNo_ = bundle.getString("trainNo");
        String startTime_ = bundle.getString("startTime");
        String arriveTime_ = bundle.getString("arriveTime");

        trainNo = (TextView) findViewById(R.id.textView4);
        Time = (TextView) findViewById(R.id.textView5);
        cTime = (TextView) findViewById(R.id.textView6);
        lvOpt = (ListView) findViewById(R.id.listView3);

        trainNo.setText(trainNo_);
        Time.setText(startTime_ + "-" + arriveTime_);

        Log.e("***","2");

        lvOpt= (ListView) this.findViewById(R.id.listView3);
        //获得数据
        mData = getData(intent);
        lvOpt.setAdapter(new Ticket2Adapter(mData,Ticket2Activity.this));
        Log.e("***","1");
        //响应条目的点击事件
        lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将bundle存放到意图并跳转
                putData(position);
            }
        });

/*
        mData = getData();
        lvOpt.setAdapter(new Ticket1Adapter(getData(),Ticket2Activity.this));*/



        /*q = (Button) this.findViewById(R.id.button4); //按钮跳转
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket2Activity.this, Ticket3Activity.class);
                startActivity(intent);
            }
        });*/
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
        map.put("seatPri","￥154.0");
        data.add(map);

        //第二个条目
        map=new HashMap<String,Object>();
        map.put("seatMes",seatMes2_);
        map.put("seatNum",seatNum2_);
        map.put("seatPri","￥152.0");
        data.add(map);

        //第三个条目
        map=new HashMap<String,Object>();
        map.put("seatMes",seatMes3_);
        map.put("seatNum",seatNum3_);
        map.put("seatPri","￥269.0");
        data.add(map);
/*
        //第四个条目
        map=new HashMap<String,Object>();
        map.put("seatMes",seatMes4_);
        map.put("seatNum",seatNum4_);
        map.put("seatPri","￥215.0");
        data.add(map);*/
        return data;
    }

    private void putData(int position) {
        Bundle bundle=new Bundle();
        bundle.putString("trainNo",(String)mData.get(position).get("trainNo"));
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
        bundle.putString("seatPri4",(String)mData.get(position).get("seatPri4"));
        Intent intent = new Intent(Ticket2Activity.this, Ticket3Activity.class);
        intent.putExtra("ticket1", bundle);


        startActivity(intent);
    }
}
