package com.zpreston.my12306.activity.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.optAdapter;
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

public class AllOrderActivity extends AppCompatActivity {
    private ListView lvOpt;
    private String orderNo;
    private List<Map<String, Object>> mData;
    private List<Map<String, Object>> getData() {//实现Map的数据构造
        //创建一个ArrayList来存放Map
        List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
        //创建Map来存放数据
        OrderDao orderDao = new OrderDaoImpl(this);
        ContactDao contactDao = new ContactDaoImpl(this);
        List<Order> list = orderDao.queryAllOrders("775079852@qq.com");//查询全部
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
                map.put("contactId",order.getContactId());
                data.add(map);
            }
        }
        return data;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        lvOpt= (ListView) this.findViewById(R.id.lvOpt);
        Intent intent =getIntent();
        final OrderDao orderDao = new OrderDaoImpl(AllOrderActivity.this);
        orderNo=intent.getStringExtra("orderNo");
        final TextView bar_code=(TextView)this.findViewById(R.id.bar_code);
        //获得数据
        mData = getData();
        TextView orderNo1=(TextView)this.findViewById(R.id.orderNo);
        orderNo1.setText(orderNo);
        final optactAdapter opt = new optactAdapter(mData, AllOrderActivity.this,orderNo);
        lvOpt.setAdapter(opt);
        bar_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllOrderActivity.this, QRCodeActivity.class);
                startActivity(intent);
            }
        });
//        lvOpt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(AllOrderActivity.this);
//                builder.setTitle("请选择操作");
//                final String items[]={"退票","改签"};
//                //items使用全局的finalCharSequenece数组声明
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        String select_item = items[which].toString();
//                        if(which==0) {
//                            orderDao.returnTicket("775079852@qq.com",orderNo,(int)mData.get(position).get("contactId"));
//                            mData=getData();
//                            opt.setmData(getData());
//                            opt.notifyDataSetChanged();
//                            /*Message message=new Message();
//                            message.what=1;
//                            handler.sendMessage(message);*/
//                        }//switch,0的话returnticket,1的话ordertckets
//
//                        Toast.makeText(AllOrderActivity.this,"选择了---》" + select_item, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setCancelable(false);
//                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                builder.create().show();
//            }
//        });
    }
    public static void getdata(){

    }
}
