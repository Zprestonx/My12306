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
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket1Activity extends AppCompatActivity {
    private ImageView q;
    private ListView lvOpt;
    private List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket1);
        //Log.e("***","aaa");
        TicketFragment.activityS.add(this);

        lvOpt= (ListView) this.findViewById(R.id.listView2);
        //获得数据
        mData = getData();
        lvOpt.setAdapter(new Ticket1Adapter(getData(),Ticket1Activity.this));
        //响应条目的点击事件
        lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将bundle存放到意图并跳转
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
        List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
        //创建Map来存放数据
        //第一个条目
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("trainNo","G108");
        map.put("startTime","04:47");
        map.put("arriveTime","14:46(0日)");
        map.put("seatMes1","高级软卧");
        map.put("p1",":");
        map.put("seatNum1","42");
        map.put("seatMes2","硬座");
        map.put("p2",":");
        map.put("seatNum2","45");
        map.put("seatMes3","一等座");
        map.put("p3",":");
        map.put("seatNum3","8");
        map.put("seatMes4","");
        map.put("p4","");
        map.put("seatNum4","");
        map.put("tranState",R.drawable.s_end);
        map.put("im1_2",R.drawable.forward_icon);
        data.add(map);

        //第二个条目
        map=new HashMap<String,Object>();
        map.put("trainNo","D29");
        map.put("startTime","07:00");
        map.put("arriveTime","11:48(0日)");
        map.put("seatMes1","特等座");
        map.put("p1",":");
        map.put("seatNum1","4");
        map.put("seatMes2","硬座");
        map.put("p1",":");
        map.put("seatNum2","20");
        map.put("seatMes3","软座");
        map.put("p1",":");
        map.put("seatNum3","7");
        map.put("seatMes4","硬卧");
        map.put("p1",":");
        map.put("seatNum4","19");
        map.put("tranState",R.drawable.s_pro);
        map.put("im1_2",R.drawable.forward_icon);
        data.add(map);
        return data;
    }

    private void putData(int position) {
        Bundle bundle=new Bundle();
        bundle.putString("trainNo",(String)mData.get(position).get("trainNo"));
        bundle.putString("startTime",(String)mData.get(position).get("startTime"));
        bundle.putString("arriveTime",(String)mData.get(position).get("arriveTime"));
        bundle.putString("seatMes1",(String)mData.get(position).get("seatMes1"));
        bundle.putString("seatNum1",(String)mData.get(position).get("seatNum1"));
        bundle.putString("seatMes2",(String)mData.get(position).get("seatMes2"));
        bundle.putString("seatNum2",(String)mData.get(position).get("seatNum2"));
        bundle.putString("seatMes3",(String)mData.get(position).get("seatMes3"));
        bundle.putString("seatNum3",(String)mData.get(position).get("seatNum3"));
        bundle.putString("seatMes4",(String)mData.get(position).get("seatMes4"));
        bundle.putString("seatNum4",(String)mData.get(position).get("seatNum4"));
        Intent intent = new Intent(Ticket1Activity.this, Ticket2Activity.class);
        intent.putExtra("ticket1", bundle);
        startActivity(intent);
    }
}
