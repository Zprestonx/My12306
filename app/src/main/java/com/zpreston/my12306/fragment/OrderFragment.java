package com.zpreston.my12306.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.order.AllOrderActivity;
import com.zpreston.my12306.activity.order.OrderActivity;
import com.zpreston.my12306.adapter.optAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 */
public class OrderFragment extends Fragment {
    private ListView lvOpt;
    private List<Map<String, Object>> mData, nData;
    public OrderFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    //响应条目的点击事件
    private void initView() {
        lvOpt = (ListView) getActivity().findViewById(R.id.lvOpt);
        //获得数据
        mData = getData();
        nData = getData1();
        final TextView tv1 = (TextView) getActivity().findViewById(R.id.tv1);
        final TextView tv2 = (TextView) getActivity().findViewById(R.id.tv2);
        final optAdapter opt = new optAdapter(mData, getActivity());
        lvOpt.setAdapter(opt);
        //点击待支付
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opt.setmData(nData);
                opt.notifyDataSetChanged();
                Toast.makeText(getActivity(), "click tv1", Toast.LENGTH_LONG).show();
                //修改颜色
                tv1.setBackgroundColor(getContext().getResources().getColor(R.color.lightblue));
                tv2.setBackgroundColor(getContext().getResources().getColor(R.color.gray));
            }
        });
        //点击全部订单
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opt.setmData(mData);
                opt.notifyDataSetChanged();
                Toast.makeText(getActivity(), "click tv2", Toast.LENGTH_LONG).show();
                tv1.setBackgroundColor(getContext().getResources().getColor(R.color.gray));
                tv2.setBackgroundColor(getContext().getResources().getColor(R.color.lightblue));
            }
        });
//响应条目的点击事件
        lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mData.get(position).get("orderState") == "0") {
                    Intent intent = new Intent(getActivity(), OrderActivity.class);
                    //启动activity
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), AllOrderActivity.class);
                    //启动activity
                    startActivity(intent);
                }
            }
        });
    }
        private List<Map<String, Object>> getData(){//实现Map的数据构造
            //创建一个ArrayList来存放Map
            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            //创建Map来存放数据
            //第一个条目
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("im", R.drawable.order_tes);
            map.put("orderNo", "123");
            map.put("startDate", "2016-07-08");
            map.put("orderState", "0");
            map.put("trainNo", "G108");
            map.put("trainMes", "广州->北京 2人");
            map.put("orderPrice", "500");
            data.add(map);
            //第二个条目
            map = new HashMap<String, Object>();
            map.put("im", R.drawable.order_tes);
            map.put("orderNo", "234");
            map.put("startDate", "2016-08-08");
            map.put("orderState", "1");
            map.put("trainNo", "G108");
            map.put("trainMes", "广州->上海 2人");
            map.put("orderPrice", "300");
            data.add(map);
            //第三个条目
            map = new HashMap<String, Object>();
            map.put("im", R.drawable.order_tes);
            map.put("orderNo", "3");
            map.put("startDate", "2016-08-08");
            map.put("orderState", "0");
            map.put("trainNo", "G108");
            map.put("trainMes", "广州->南京 2人");
            map.put("orderPrice", "600");
            data.add(map);
            return data;
        }

        private List<Map<String, Object>> getData1(){//实现Map的数据构造
            //创建一个ArrayList来存放Map
            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            //创建Map来存放数据
            //第一个条目
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("im", R.drawable.order_tes);
            map.put("orderNo", "123");
            map.put("startDate", "2016-07-08");
            map.put("orderState", "0");
            map.put("trainNo", "G108");
            map.put("trainMes", "广州->北京 2人");
            map.put("orderPrice", "500");
            data.add(map);
            //第二个条目
            map = new HashMap<String, Object>();
            map.put("im", R.drawable.order_tes);
            map.put("orderNo", "234");
            map.put("startDate", "2016-08-08");
            map.put("orderState", "0");
            map.put("trainNo", "G108");
            map.put("trainMes", "广州->上海 2人");
            map.put("orderPrice", "300");
            data.add(map);
            return data;
        }
}


