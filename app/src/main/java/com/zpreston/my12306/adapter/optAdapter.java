package com.zpreston.my12306.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpreston.my12306.R;

import java.util.List;
import java.util.Map;

/**
 * Created by i on 2016/7/21.
 */

public class optAdapter extends BaseAdapter {
    private List<Map<String, Object>> mData;
    private Context context;
    public class ViewHolder {
        public ImageView im;
        public TextView orderNo;
        public TextView trainNo;
        public TextView startDate;
        public TextView orderState;
        public TextView trainMes;
        public TextView orderPrice;

    }
    public optAdapter(List<Map<String, Object>> mData,Context context){
        this.mData=mData;
        this.context=context;
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
    public View getView(final  int position, View convertView, ViewGroup parent) {

//声明一个ViewHolder
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            //将转换的view对象存入到缓存中
            convertView=View.inflate(context, R.layout.order_item,null);
            //通过convertView对象来获得控件，将其保存到ViewHolder中
            viewHolder.im= (ImageView) convertView.findViewById(R.id.im);
            viewHolder.orderNo= (TextView) convertView.findViewById(R.id.orderNo);
            viewHolder.startDate= (TextView) convertView.findViewById(R.id.startDate);
            viewHolder.orderState= (TextView) convertView.findViewById(R.id.orderState);
            viewHolder.trainNo= (TextView) convertView.findViewById(R.id.trainNo);
            viewHolder.trainMes= (TextView) convertView.findViewById(R.id.trainMes);
            viewHolder.orderPrice= (TextView) convertView.findViewById(R.id.orderPrice);
            //设置标志
            convertView.setTag(viewHolder);
        }else{
            //如果convertView不为空时取出View
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //为控件设置值
        viewHolder.im.setImageResource((Integer) mData.get(position).get("im"));
        viewHolder.orderNo.setText((String)mData.get(position).get("orderNo"));
        viewHolder.startDate.setText((String) mData.get(position).get("startDate"));
        viewHolder.trainNo.setText((String) mData.get(position).get("trainNo"));
        viewHolder.orderState.setText((String) mData.get(position).get("orderState"));
        viewHolder.trainMes.setText((String) mData.get(position).get("trainMes"));
        viewHolder.orderPrice.setText((String) mData.get(position).get("orderPrice"));

        return convertView;
    }
    public void setmData(List<Map<String, Object>> mData){
        this.mData=mData;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
