package com.zpreston.my12306.activity.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.optactAdapter;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.daoImpl.OrderDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllOrderActivity extends AppCompatActivity {
    private ListView lvOpt;
    private String orderNo;
    private List<Map<String, Object>> mData;
    private List<Map<String, Object>> getData() {//实现Map的数据构造
        //创建一个ArrayList来存放Map
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        OrderDao orderDao = new OrderDaoImpl(this);
        List<Order> list = orderDao.queryAlreadyPaidOrders("15627860619@qq.com");//查询已支付订单
        for (Order order : list) {
            Log.e("Tag-order",order.toString());
            if (order.getOrderNo().equals(orderNo)) {//通过从点击条目获取的orderNo匹配list中的信息
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("orderNo", order.getOrderNo());
                map.put("contactId", order.getContactId());
                map.put("trainNo", order.getTrainNo());
                map.put("orderTime",order.getOrderTime());
                data.add(map);
            }
        }
        return data;
    }
        //创建Map来存放数据
        //第一个条目
//        Map<String, Object> map=new HashMap<String,Object>();
//
////        map.put("startDate","2016-07-08");
////        map.put("trainNo","G108");
////        map.put("trainSeat","1车2号");
////        map.put("contactId","五五开");
////        data.add(map);
////        //第二个条目
////        map=new HashMap<String,Object>();
////        map.put("startDate","2016-08-08");
////        map.put("trainSeat","1车1号");
////        map.put("trainNo","G108");
////        map.put("contactId","周杰伦");
////        data.add(map);
////        return data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        lvOpt= (ListView) this.findViewById(R.id.lvOpt);
        Intent intent =getIntent();
        orderNo=intent.getStringExtra("orderNo");
        final TextView bar_code=(TextView)this.findViewById(R.id.bar_code);
        //获得数据
        mData = getData();
        lvOpt.setAdapter(new optactAdapter(mData,this));


                bar_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bar_code.setBackgroundColor(AllOrderActivity.this.getResources().getColor(R.color.lightblue));
                        Intent intent = new Intent(AllOrderActivity.this, QRCodeActivity.class);
                        startActivity(intent);
                    }
                });

        lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AllOrderActivity.this);
                builder.setTitle("请选择操作");
                final String items[]={"退票","改签"};
                final OrderDao orderDao = new OrderDaoImpl(AllOrderActivity.this);
                //items使用全局的finalCharSequenece数组声明
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        String select_item = items[which].toString();
                        if(select_item=="0") {
                            orderDao.returnTicket("15627860619@qq.com",(String)(mData.get(position).get("orederNo")),(int)mData.get(position).get("contactId"));

                        }//switch,0的话returnticket,1的话ordertckets

                        Toast.makeText(AllOrderActivity.this,"选择了---》" + select_item, Toast.LENGTH_SHORT).show();
                        }
                   });
                builder.setCancelable(false);
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                }
        });

    }
}
