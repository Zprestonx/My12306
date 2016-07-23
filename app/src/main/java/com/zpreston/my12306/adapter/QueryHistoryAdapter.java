package com.zpreston.my12306.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zpreston.my12306.R;

import java.util.List;

/**
 * Created by preston on 2016/7/22.
 */
public class QueryHistoryAdapter extends BaseAdapter {
    List<String> data;
    Context context;

    private class ViewHolder{
        TextView textView;
    }

    public QueryHistoryAdapter(Context context, List<String> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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
            convertView=View.inflate(context, R.layout.query_history_item,null);
            viewHolder.textView=(TextView)convertView.findViewById(R.id.history);
            viewHolder.textView.setText(data.get(position));
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }
}
