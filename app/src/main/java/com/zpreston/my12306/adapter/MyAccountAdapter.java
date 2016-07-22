package com.zpreston.my12306.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import com.zpreston.my12306.R;

import org.w3c.dom.Text;

/**
 * Created by 小m on 2016/7/21.
 */
public class MyAccountAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> mData;
    public MyAccountAdapter(Context context,List<Map<String, Object>> mData){
        this.context=context;
        this.mData=mData;
    }
    private class ViewHolder{
        public TextView tvAccount;
        public EditText edtAccount;
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
            convertView=View.inflate(context,R.layout.mine_lvaccount_item,null);
            viewHolder.tvAccount=(TextView)convertView.findViewById(R.id.tvAccount);
            viewHolder.edtAccount=(EditText)convertView.findViewById(R.id.edtAccount);
            viewHolder.tvAccount.setText(mData.get(position).get("label").toString());
            viewHolder.edtAccount.setText(mData.get(position).get("content").toString());
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }
}
