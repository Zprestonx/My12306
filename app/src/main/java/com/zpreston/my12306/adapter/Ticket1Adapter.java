package com.zpreston.my12306.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpreston.my12306.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-7-23.
 */
public class Ticket1Adapter extends BaseAdapter{
    private List<Map<String, Object>> mData;
    private Context context;
    public class ViewHolder {
        public ImageView tranState;
        public ImageView im1_2;
        public TextView trainNo;
        public TextView startTime;
        public TextView arriveTime;
        public TextView seatMes1;
        public TextView p1;
        public TextView seatNum1;
        public TextView seatMes2;
        public TextView p2;
        public TextView seatNum2;
        public TextView seatMes3;
        public TextView p3;
        public TextView seatNum3;
        public TextView seatMes4;
        public TextView p4;
        public TextView seatNum4;
    }
    public Ticket1Adapter(List <Map<String,Object>> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }
    public void setmData(List<Map<String, Object>> mData) {
        this.mData = mData;
    }

    /*@Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }*/
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
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明一个ViewHolder
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            //将转换的view对象存入到缓存中
            convertView = View.inflate(context, R.layout.ticket1_item, null);
            //通过convertView对象来获得控件，将其保存到ViewHolder中
            viewHolder.startTime = (TextView) convertView.findViewById(R.id.startTime);
            viewHolder.trainNo = (TextView) convertView.findViewById(R.id.trainNo);
            viewHolder.arriveTime = (TextView) convertView.findViewById(R.id.arriveTime);
            viewHolder.seatMes1 = (TextView) convertView.findViewById(R.id.seatMes1);
            viewHolder.seatNum1 = (TextView) convertView.findViewById(R.id.seatNum1);
            viewHolder.p1 = (TextView) convertView.findViewById(R.id.p1);
            viewHolder.seatMes2 = (TextView) convertView.findViewById(R.id.seatMes2);
            viewHolder.seatNum2 = (TextView) convertView.findViewById(R.id.seatNum2);
            viewHolder.p2 = (TextView) convertView.findViewById(R.id.p2);
            viewHolder.seatMes3 = (TextView) convertView.findViewById(R.id.seatMes3);
            viewHolder.seatNum3 = (TextView) convertView.findViewById(R.id.seatNum3);
            viewHolder.p3 = (TextView) convertView.findViewById(R.id.p3);
            viewHolder.seatMes4 = (TextView) convertView.findViewById(R.id.seatMes4);
            viewHolder.seatNum4 = (TextView) convertView.findViewById(R.id.seatNum4);
            viewHolder.p4 = (TextView) convertView.findViewById(R.id.p4);
            viewHolder.tranState= (ImageView) convertView.findViewById(R.id.tranState);
            //viewHolder.im1_2= (ImageView) convertView.findViewById(R.id.im1_2);
            //设置标志
            convertView.setTag(viewHolder);
        }
        else
        {
            //如果convertView不为空时取出View
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //为控件设置值
        viewHolder.startTime.setText((String)mData.get(position).get("startTime"));
        viewHolder.trainNo.setText((String)mData.get(position).get("trainNo"));
        viewHolder.arriveTime.setText((String)mData.get(position).get("arriveTime"));
        viewHolder.seatMes1.setText((String)mData.get(position).get("seatMes1"));
        viewHolder.seatNum1.setText((String)mData.get(position).get("seatNum1"));
        viewHolder.p1.setText((String)mData.get(position).get("p1"));
        viewHolder.seatMes2.setText((String)mData.get(position).get("seatMes2"));
        viewHolder.seatNum2.setText((String)mData.get(position).get("seatNum2"));
        viewHolder.p2.setText((String)mData.get(position).get("p2"));
        viewHolder.seatMes3.setText((String)mData.get(position).get("seatMes3"));
        viewHolder.seatNum3.setText((String)mData.get(position).get("seatNum3"));
        viewHolder.p3.setText((String)mData.get(position).get("p3"));
        viewHolder.seatMes4.setText((String)mData.get(position).get("seatMes4"));
        viewHolder.seatNum4.setText((String)mData.get(position).get("seatNum4"));
        viewHolder.p4.setText((String)mData.get(position).get("p4"));
        viewHolder.tranState.setImageResource((Integer)mData.get(position).get("tranState"));
        //viewHolder.im1_2.setImageResource((Integer)mData.get(position).get("im1_2"));
        return convertView;
    }
}
