package com.zpreston.my12306.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpreston.my12306.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 小m on 2016/7/22.
 */
public class MyContactAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> mData;
    public MyContactAdapter(Context context,List<Map<String, Object>> mData){
        this.context=context;
        this.mData=mData;
    }
    private class ViewHolder{
        public TextView tvContactName;
        public TextView tvIdCard;
        public TextView tvPhone;
        public ImageView imForward;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context, R.layout.contact_lvmain_item,null);

            viewHolder.tvContactName=(TextView)convertView.findViewById(R.id.tvContactName);
            viewHolder.tvIdCard=(TextView)convertView.findViewById(R.id.tvIdCard);
            viewHolder.tvPhone=(TextView)convertView.findViewById(R.id.tvPhone);
            viewHolder.imForward=(ImageView) convertView.findViewById(R.id.imForward);


            viewHolder.tvContactName.setText(mData.get(position).get("tvContactName").toString());
            viewHolder.tvIdCard.setText(mData.get(position).get("tvIdCard").toString());
            viewHolder.tvPhone.setText(mData.get(position).get("tvPhone").toString());
            viewHolder.imForward.setImageResource((Integer) mData.get(position).get("imForward"));

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }
}
