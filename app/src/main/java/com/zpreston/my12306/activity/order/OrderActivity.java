package com.zpreston.my12306.activity.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.optactAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    private ListView lvOpt;
    private List<Map<String, Object>> mData;
    private List<Map<String, Object>> getData() {//实现Map的数据构造
        //创建一个ArrayList来存放Map
        List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
        //创建Map来存放数据
        //第一个条目
        Map<String, Object> map=new HashMap<String,Object>();

        map.put("startDate","2016-07-08");
        map.put("trainNo","G108");
        map.put("trainSeat","1车2号");
        map.put("contactId","五五开");
        data.add(map);
        //第二个条目
        map=new HashMap<String,Object>();
        map.put("startDate","2016-08-08");
        map.put("trainSeat","1车1号");
        map.put("trainNo","G108");
        map.put("contactId","周杰伦");
        data.add(map);
        return data;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
            lvOpt= (ListView) this.findViewById(R.id.lvOpt);
        TextView cancelOrder=(TextView)this.findViewById(R.id.cancelOrder);
        TextView con_firmOrder=(TextView)this.findViewById(R.id.con_firmOrder);
        Log.e("Tag","dsadsadsa");
        //获得数据
        mData = getData();
        lvOpt.setAdapter(new optactAdapter(getData(),this));
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
        con_firmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "click tv1", Toast.LENGTH_LONG).show();
            }
        });
    }
}
