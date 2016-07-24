package com.zpreston.my12306.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.ticket.Ticket2Activity;
import com.zpreston.my12306.activity.ticket.Ticket3Activity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-7-23.
 */
public class Ticket2Adapter extends BaseAdapter{
    int year;
    int month;
    int day;
    String endCity;
    String beginCity;
    String trainNo;
    String startTime;
    String arriveTime;
    private List<Map<String, Object>> mData;
    private Context context;

    public class ViewHolder {
        public Button button10;
        public TextView seatMes;
        public TextView seatNum;
        public TextView seatPri;
    }
    public Ticket2Adapter(List < Map < String, Object >> mData, Context context) {
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
    public void setYear(int year) {
        this.year=year;
    }
    public void setMonth(int month) {
        this.month=month;
    }
    public void setDay(int day) {
        this.day=day;
    }
    public void setTrainNo(String trainNo) {
        this.trainNo=trainNo;
    }
    public void setStartTime(String startTime) {
        this.startTime=startTime;
    }
    public void setArriveTime(String arriveTime) {
        this.arriveTime=arriveTime;
    }
    public void setBeginCity(String beginCity) {
        this.beginCity=beginCity;
    }
    public void setEndCity(String endCity) {
        this.endCity=endCity;
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
            convertView = View.inflate(context, R.layout.ticket2_item, null);
            //通过convertView对象来获得控件，将其保存到ViewHolder中
            viewHolder.seatMes = (TextView) convertView.findViewById(R.id.seatMes);
            viewHolder.seatNum = (TextView) convertView.findViewById(R.id.seatNum);
            viewHolder.seatPri = (TextView) convertView.findViewById(R.id.seatPri);
            viewHolder.button10 = (Button) convertView.findViewById(R.id.button10);
            //设置标志
            convertView.setTag(viewHolder);
        }
        else
        {
            //如果convertView不为空时取出View
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //为控件设置值
        viewHolder.seatMes.setText((String)mData.get(position).get("seatMes"));
        viewHolder.seatNum.setText((String)mData.get(position).get("seatNum"));
        viewHolder.seatPri.setText((String)mData.get(position).get("seatPri"));
        //Button button10=(Button)convertView.findViewById(R.id.button10);
        viewHolder.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn(position); //跳转
            }
        });


        return convertView;
    }

    private void turn(int position) {
        Bundle bundle=new Bundle();

        bundle.putString("trainNo",trainNo);
        bundle.putString("startTime",startTime);
        bundle.putString("arriveTime",arriveTime);
        bundle.putString("beginCity",beginCity);
        bundle.putString("endCity",endCity);
        bundle.putInt("year",year);
        bundle.putInt("month",month);
        bundle.putInt("day",day);

        bundle.putString("seatMes",(String)mData.get(position).get("seatMes"));
        bundle.putString("seatNum",(String)mData.get(position).get("seatNum"));
        bundle.putString("seatPri",(String)mData.get(position).get("seatPri"));

        /*bundle.putString("seatMes",(String)mData.get(position).get("seatMes1"));
        bundle.putString("seatNum",(String)mData.get(position).get("seatNum1"));
        bundle.putString("seatPri",(String)mData.get(position).get("seatPri1"));
        bundle.putString("seatMes2",(String)mData.get(position).get("seatMes2"));
        bundle.putString("seatNum2",(String)mData.get(position).get("seatNum2"));
        bundle.putString("seatPri2",(String)mData.get(position).get("seatPri2"));
        bundle.putString("seatMes3",(String)mData.get(position).get("seatMes3"));
        bundle.putString("seatNum3",(String)mData.get(position).get("seatNum3"));
        bundle.putString("seatPri3",(String)mData.get(position).get("seatPri3"));
        bundle.putString("seatMes4",(String)mData.get(position).get("seatMes4"));
        bundle.putString("seatNum4",(String)mData.get(position).get("seatNum4"));
        bundle.putString("seatPri4",(String)mData.get(position).get("seatPri4"));*/
        Intent intent = new Intent(context, Ticket3Activity.class);
        intent.putExtra("ticket1", bundle);


        context.startActivity(intent);
    }
}
