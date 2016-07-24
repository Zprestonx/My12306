package com.zpreston.my12306.activity.ticket;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.MainActivity;
import com.zpreston.my12306.activity.order.OrderActivity;
import com.zpreston.my12306.adapter.optactAdapter;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.daoImpl.OrderDaoImpl;
import com.zpreston.my12306.fragment.OrderFragment;
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket4Activity extends AppCompatActivity {
    String startTime;
    String trainNo_;

    private ListView lvOpt;                                 //Order未支付ListView展示
    private String orderNo;
    private List<Map<String, Object>> mData;                //Order未支付ListView展示



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket4);
        TicketFragment.activityS.add(this);

        lvOpt= (ListView) this.findViewById(R.id.listView6);
        Intent intent =getIntent();
        Bundle bundle = intent.getBundleExtra("ticket");
        orderNo=bundle.getString("orderNo");
        Log.e("orderNo:",orderNo);
        TextView orderNo1=(TextView)this.findViewById(R.id.orderNo1);
        orderNo1.setText(orderNo);
        /*final TextView cancelOrder=(TextView)this.findViewById(R.id.cancelOrder);
        final TextView con_firmOrder=(TextView)this.findViewById(R.id.con_firmOrder);
        final OrderDao orderDao = new OrderDaoImpl(this);*/
        //获得数据
        mData = getData();
        lvOpt.setAdapter(new optactAdapter(getData(),this,orderNo));

    }

    public void no(View view) { //暂不支付按钮跳转
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

        for (Activity activity : TicketFragment.activityS) { //将车票预定1到3的Activity Finish掉
            activity.finish();
        }
        finish();
    }

    public void yes(View view) { //确认支付按钮跳转
        OrderDao orderDao = new OrderDaoImpl(this);
        orderDao.payForOrder("775079852@qq.com",orderNo);
        Intent intent=new Intent(this,Ticket5Activity.class);
        startActivity(intent);
    }


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
}
