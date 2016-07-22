package com.zpreston.my12306.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.MyAccountAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAccountActivity extends AppCompatActivity {
    private ListView lvAccount;
    List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accout);
        mData=getData();
        lvAccount = (ListView) findViewById(R.id.lvAccount);
        lvAccount.setDivider(null);
        lvAccount.setAdapter(new MyAccountAdapter(this,mData));
    }

    private List<Map<String,Object>> getData(){//实现Map的数据构造
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

        map.put("label","用户名");
        map.put("content","mlh");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","姓名");
        map.put("content","马丽豪");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件类型");
        map.put("content","身份证");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件号码");
        map.put("content","123456");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","乘客类型");
        map.put("content","学生");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","电话");
        map.put("content","123456");
        data.add(map);

        return data;
    }

}







