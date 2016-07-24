package com.zpreston.my12306.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.daoImpl.OrderDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by i on 2016/7/21.
 */
public class optactAdapter extends BaseAdapter {
        private List<Map<String, Object>> mData;
        private Context context;
    private String orderNo;
        public class ViewHolder {
            public TextView orderTime;
            public TextView trainNo;
            public TextView contactName;
            public TextView orderSeat;
            public TextView change;
            public TextView cancel;
        }
        public optactAdapter(List < Map < String, Object >> mData, Context context,String orderNo) {
        this.mData = mData;
        this.context = context;
            this.orderNo=orderNo;
    }
    public void setmData(List<Map<String, Object>> mData) {
        this.mData = mData;
        Log.e("TAG-setData","***");
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    //声明一个ViewHolder
    ViewHolder viewHolder;
    if(convertView==null)
    {
        viewHolder = new ViewHolder();
        //将转换的view对象存入到缓存中
        convertView = View.inflate(context, R.layout.order_contact_item, null);
        //通过convertView对象来获得控件，将其保存到ViewHolder中
        viewHolder.orderTime = (TextView) convertView.findViewById(R.id.orderTime);
        viewHolder.trainNo = (TextView) convertView.findViewById(R.id.trainNo);
        viewHolder.orderSeat = (TextView) convertView.findViewById(R.id.orderSeat);
        viewHolder.contactName = (TextView) convertView.findViewById(R.id.contactName);
        viewHolder.change=(TextView)convertView.findViewById(R.id.change);
        viewHolder.cancel=(TextView)convertView.findViewById(R.id.cancel);
        //设置标志
        convertView.setTag(viewHolder);
    }
    else
    {
        //如果convertView不为空时取出View
        viewHolder = (ViewHolder) convertView.getTag();
    }
    //为控件设置值
    viewHolder.orderTime.setText((String)mData.get(position).get("orderTime"));
    viewHolder.trainNo.setText((String)mData.get(position).get("trainNo"));
    viewHolder.contactName.setText((String)mData.get(position).get("contactName"));
    viewHolder.orderSeat.setText((String)mData.get(position).get("orderSeat"));
        viewHolder.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("确认改签吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("不要", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("不如跳舞", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
                });

        viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String,Object>> data=getData();
                OrderDao orderDao=new OrderDaoImpl(context);
                orderDao.returnTicket("775079852@qq.com",orderNo,(int)data.get(position).get("contactId"));
                data=getData();
                setmData(data);
                notifyDataSetChanged();
                Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show();
            }
        });
    return convertView;
}

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    private List<Map<String, Object>> getData() {//实现Map的数据构造
        //创建一个ArrayList来存放Map
        List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
        //创建Map来存放数据
        OrderDao orderDao = new OrderDaoImpl(context);
        ContactDao contactDao = new ContactDaoImpl(context);
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
}
