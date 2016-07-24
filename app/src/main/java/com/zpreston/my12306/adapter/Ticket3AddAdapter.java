package com.zpreston.my12306.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpreston.my12306.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-7-24.
 */
public class Ticket3AddAdapter extends BaseAdapter {
    private Context context;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> mData;

    public List<Map<String, Object>> getList() {
        return list;
    }

    public Ticket3AddAdapter(Context context, List<Map<String, Object>> mData) {
        this.context = context;
        this.mData = mData;
    }

    private class ViewHolder {
        public TextView tvContactName;
        public TextView tvIdCard;
        public TextView tvPhone;
        public CheckBox checkBox;

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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.ticket3add_item, null);

            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            viewHolder.tvContactName = (TextView) convertView.findViewById(R.id.tvContactName);
            viewHolder.tvIdCard = (TextView) convertView.findViewById(R.id.tvIdCard);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("1", mData.get(position).get("tvContactName").toString());
                    map.put("2", mData.get(position).get("tvIdCard").toString());
                    map.put("3", mData.get(position).get("tvPhone").toString());
                    if (isChecked) {
                        //if (!list.contains(map)) {
                            list.add(map);
                        //}
                    } else {
                        //if (list.contains(map))
                            list.remove(map);
                    }
                }
            });
            viewHolder.tvContactName.setText(mData.get(position).get("tvContactName").toString());
            viewHolder.tvIdCard.setText(mData.get(position).get("tvIdCard").toString());
            viewHolder.tvPhone.setText(mData.get(position).get("tvPhone").toString());

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
