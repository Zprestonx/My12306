package com.zpreston.my12306.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.dao.TrainDao;
import com.zpreston.my12306.daoImpl.OrderDaoImpl;
import com.zpreston.my12306.daoImpl.TrainDaoImpl;
import com.zpreston.my12306.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 */
public class OrderFragment extends Fragment {
    private ListView lvOpt;
    private List<Map<String, Object>> mData, nData;
     Util util=new Util(getActivity());
    final optAdapter opt = new optAdapter(mData, getActivity());
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
        //initView();
    }
    @Override
    public void onResume() {
        super.onResume();
        initView();
    }
    //响应条目的点击事件
    private void initView() {
        lvOpt = (ListView) getActivity().findViewById(R.id.lvOpt);
        mData=getData();
        //获得数据
        final TextView tv1 = (TextView) getActivity().findViewById(R.id.tv1);
        final TextView tv3 = (TextView) getActivity().findViewById(R.id.tv3);
        final optAdapter opt = new optAdapter(mData, getActivity());
        tv3.setBackgroundColor(getContext().getResources().getColor(R.color.lightblue));
        tv1.setBackgroundColor(getContext().getResources().getColor(android.R.color.darker_gray));
        lvOpt.setAdapter(opt);
        //点击待支付
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//待支付
                mData=getData1();
                opt.setmData(mData);
                opt.notifyDataSetChanged();
                //修改颜色
                tv1.setBackgroundColor(getContext().getResources().getColor(R.color.lightblue));
                tv3.setBackgroundColor(getContext().getResources().getColor(R.color.gray));

            }
        });
        //点击全部订单
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//全部订单
                mData=getData();
                opt.setmData(mData);
                opt.notifyDataSetChanged();
                tv1.setBackgroundColor(getContext().getResources().getColor(R.color.gray));
                tv3.setBackgroundColor(getContext().getResources().getColor(R.color.lightblue));
            }
        });
//响应条目的点击事件
        lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (((String.valueOf(mData.get(position).get("orderState"))).equals("0"))) {//如果未支付

                    String orderNo=(String) (mData.get(position).get("orderNo"));
                    Intent intent = new Intent(getActivity(), OrderActivity.class);
                    intent.putExtra("orderNo",orderNo);
                    //启动activity
                    startActivityForResult(intent,1);
                } else {
                    String orderNo=(String) (mData.get(position).get("orderNo"));
                    Intent intent = new Intent(getActivity(), AllOrderActivity.class);
                    //启动activity
                    intent.putExtra("orderNo",orderNo);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //重写onActivityResult方法
            switch (requestCode){
                case 1:
                    if(resultCode==Activity.RESULT_OK){
                        Log.e("Tag","***************");
                        opt.setmData(getData1());
                        opt.notifyDataSetChanged();
                    }
                    break;
                default:
        }
    }

    private List<Map<String, Object>> getData() {//实现Map的数据构造，获取已支付订单
        //创建一个ArrayList来存放Map
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Util util = new Util(getContext());
        //创建Map来存放数据
        OrderDao orderDao = new OrderDaoImpl(getActivity());
        List<Order> list = orderDao.queryAllOrders(util.getEmail());
        Set set = new HashSet();
        for (Order order : list) {
            set.add(order.getOrderNo());
        }
        List list1 = new ArrayList(set);
        int count = set.size();
        int Num[] = new int[count];
        String orderTime[] = new String[count];
        int orderState[] = new int[count];
        String trainNo[] = new String[count];
        Double Price[] = new Double[count];
        String startStation[]=new String[count];
        String endStation[]=new String[count];
        TrainDao trainDao = new TrainDaoImpl(getActivity());
       for(int i=0;i<list1.size();i++){
           Num[i]=0;
           Price[i]=0.0;
       }
        for (Order order : list) {
            for (int i = 0; i < list1.size(); i++) {
                if (order.getOrderNo().equals( list1.get(i))) {
                    List<String> stationList = new ArrayList<>();
                    stationList=trainDao.getStartEndStationByTrainNo(order.getTrainNo());
                    Num[i]++;
                    Price[i] += order.getOrderPrice();
                    orderTime[i] = order.getOrderTime();
                    orderState[i] = order.getOrderState();
                    trainNo[i] = order.getTrainNo();
                    Log.e("Tag", String.valueOf(Num[i]));
                    startStation[i]=stationList.get(0);
                    endStation[i]=stationList.get(1);

                }
            }
        }
        for (int i = 0; i < list1.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("im", R.drawable.forward_icon);
            map.put("orderNo", list1.get(i));
            map.put("orderTime", orderTime[i]);
            map.put("orderState", orderState[i]);
            map.put("trainNo", trainNo[i]);
            map.put("trainMes", startStation[i]+"->"+endStation[i]);
            map.put("contactNum", Num[i]+"人");
            map.put("orderPrice", Price[i]+"元");
            data.add(map);
        }
                return data;
            }
        private List<Map<String, Object>> getData1 () {//实现Map的数据构造，获取待支付订单
            //创建一个ArrayList来存放Map
            //创建一个ArrayList来存放Map
            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            //创建Map来存放数据
            OrderDao orderDao = new OrderDaoImpl(getActivity());
            TrainDao trainDao = new TrainDaoImpl(getActivity());
            Util util = new Util(getContext());
            List<Order> list = orderDao.queryNotPaidOrders(util.getEmail());

            Set set = new HashSet();
            for (Order order : list) {
                set.add(order.getOrderNo());
            }
            List list1 = new ArrayList(set);
            int count = set.size();
            int Num[] = new int[count];
            String orderTime[] = new String[count];
            int orderState[] = new int[count];
            String trainNo[] = new String[count];
            Double Price[] = new Double[count];
            String startStation[]=new String[count];
            String endStation[]=new String[count];
            for(int i=0;i<list1.size();i++){
                Num[i]=0;
                Price[i]=0.0;
            }
            for (Order order : list) {
                for (int i = 0; i < list1.size(); i++) {
                    if (order.getOrderNo().equals( list1.get(i))) {
                        List<String> stationList = new ArrayList<>();
                        stationList=trainDao.getStartEndStationByTrainNo(order.getTrainNo());
                        Num[i]++;
                        Price[i] += order.getOrderPrice();
                        orderTime[i] = order.getOrderTime();
                        orderState[i] = order.getOrderState();
                        trainNo[i] = order.getTrainNo();
                        startStation[i]=stationList.get(0);
                        endStation[i]=stationList.get(1);
                    }
                }
            }
            for (int i = 0; i < list1.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("im", R.drawable.forward_icon);
                map.put("orderNo", list1.get(i));
                map.put("orderTime", orderTime[i]);
                map.put("orderState", orderState[i]);
                map.put("trainNo", trainNo[i]);
                map.put("trainMes", startStation[i]+"->"+endStation[i]);
                map.put("contactNum", Num[i]+"人");
                map.put("orderPrice", Price[i]+"元");
                data.add(map);
            }
            return data;
        }
    }



