package com.zpreston.my12306.activity.order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.optactAdapter;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.daoImpl.OrderDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    private ListView lvOpt;
    private String orderNo;
    private List<Map<String, Object>> mData;
    private List<Map<String, Object>> getData() {//实现Map的数据构造
        //创建一个ArrayList来存放Map，获取点击的条目的信息，日期，车次？，座位，联系人 for i=0 i<=n i++ contactId[i]赋值给contactId
        List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
        //创建Map来存放数据
        OrderDao orderDao = new OrderDaoImpl(this);
        ContactDao contactDao = new ContactDaoImpl(this);
        List<Order> list = orderDao.queryNotPaidOrders("775079852@qq.com");//查询未支付订单
        for (Order order : list) {
            if (order.getOrderNo().equals(orderNo)) {//通过从点击条目获取的orderNo匹配list中的信息
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("orderNo", order.getOrderNo());
                String contactName;
                contactName = (contactDao.querySingleContactById("775079852@qq.com",order.getContactId())).getContactName();
                map.put("contactName",contactName);
                map.put("trainNo", order.getTrainNo());
                map.put("orderTime",order.getOrderTime());
                map.put("orderState",order.getOrderState());
                data.add(map);
            }
        }
        return data;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
            lvOpt= (ListView) this.findViewById(R.id.lvOpt);
        Intent intent =getIntent();
        orderNo=intent.getStringExtra("orderNo");
        TextView orderNo1=(TextView)this.findViewById(R.id.orderNo);
        orderNo1.setText(orderNo);
        final TextView cancelOrder=(TextView)this.findViewById(R.id.cancelOrder);
        final TextView con_firmOrder=(TextView)this.findViewById(R.id.con_firmOrder);
        final OrderDao orderDao = new OrderDaoImpl(OrderActivity.this);
        //获得数据
        mData = getData();
        lvOpt.setAdapter(new optactAdapter(getData(),this,orderNo));

        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击取消订单,从数据库删除订单
                orderDao.cancelOrder("775079852@qq.com",orderNo);
                cancelOrder.setBackgroundColor(OrderActivity.this.getResources().getColor(R.color.lightblue));
                Intent intent = new Intent();
                OrderActivity.this.setResult(RESULT_OK,intent);
                OrderActivity.this.finish();
            }
        });
        con_firmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击确认订单，订单状态改成1，已支付，finish
                orderDao.payForOrder("775079852@qq.com",orderNo);
                con_firmOrder.setBackgroundColor(OrderActivity.this.getResources().getColor(R.color.lightblue));
                Intent intent = new Intent();
                OrderActivity.this.setResult(RESULT_OK,intent);
                OrderActivity.this.finish();
            }
        });
    }
}
