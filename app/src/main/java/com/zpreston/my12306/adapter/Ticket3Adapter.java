package com.zpreston.my12306.adapter;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zpreston.my12306.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-7-24.
 */
public class Ticket3Adapter extends BaseAdapter {
    ListView listView;
    private Context context;
    private List<Map<String, Object>> mData;

    public Ticket3Adapter(Context context, List<Map<String, Object>> mData, ListView listView) {
        this.context = context;
        this.mData = mData;
        this.listView = listView;
    }

    public void setData(List<Map<String, Object>> mData) {
        this.mData = mData;
    }

    private class ViewHolder {
        public TextView tvContactName;
        public TextView tvIdCard;
        public TextView tvPhone;
        public ImageButton remove;

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
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.ticket3_item, null);

            viewHolder.tvContactName = (TextView) convertView.findViewById(R.id.tvContactName);
            viewHolder.tvIdCard = (TextView) convertView.findViewById(R.id.tvIdCard);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            viewHolder.remove = (ImageButton) convertView.findViewById(R.id.remove);


            viewHolder.tvContactName.setText(mData.get(position).get("tvContactName").toString());
            viewHolder.tvIdCard.setText(mData.get(position).get("tvIdCard").toString());
            viewHolder.tvPhone.setText(mData.get(position).get("tvPhone").toString());
            viewHolder.remove.setOnTouchListener(new ImageButton.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        viewHolder.remove.setImageResource(R.drawable.delete_contact_pressed);
                    }
                    else if(event.getAction() == MotionEvent.ACTION_UP){
                        viewHolder.remove.setImageResource(R.drawable.delete_contact);
                    }
                    return false;
                }
            });

            viewHolder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //viewHolder.remove.setImageResource(R.drawable.delete_contact_pressed);
                    mData.remove(position);
                    notifyDataSetChanged();
                    //跳转
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            /*viewHolder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //viewHolder.remove.setImageResource(R.drawable.delete_contact_pressed);
                    mData.remove(position);
                    notifyDataSetChanged();
                    //跳转
                }
            });*/
        }
        return convertView;
    }
}
