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
public class ContactShowAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> mData;
    public ContactShowAdapter(Context context,List<Map<String, Object>> mData) {
        this.context = context;
        this.mData = mData;
    }

    private class ViewHolder{
        public TextView tvContactShow;
        public EditText edtContactShow;
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
            convertView=View.inflate(context, R.layout.contact_lvshow_item,null);

            viewHolder.tvContactShow=(TextView)convertView.findViewById(R.id.tvContactShow);
            viewHolder.edtContactShow=(EditText)convertView.findViewById(R.id.edtContactShow);

            viewHolder.tvContactShow.setText(mData.get(position).get("label").toString());
            viewHolder.edtContactShow.setText(mData.get(position).get("content").toString());

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }



}
