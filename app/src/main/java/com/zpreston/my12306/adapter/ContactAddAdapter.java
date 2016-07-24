package com.zpreston.my12306.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.zpreston.my12306.R;
import com.zpreston.my12306.bean.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 小m on 2016/7/22.
 */
public class ContactAddAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> mData;

    public ContactAddAdapter(Context context, List<Map<String, Object>> mData) {
        this.context = context;
        this.mData = mData;
    }

    private Map<String, Object> map = new HashMap<String, Object>();

    public Map<String, Object> getMap() {
        return map;
    }


    private class ViewHolder{
        public TextView tvContactAdd;
        public EditText edtContactAdd;
    }

    /* 获取数据 */
    public void setData(List<Map<String, Object>> data) {
        this.mData = data;
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

        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context, R.layout.contact_lvadd_item,null);

            viewHolder.tvContactAdd=(TextView)convertView.findViewById(R.id.tvContactAdd);
            viewHolder.edtContactAdd=(EditText)convertView.findViewById(R.id.edtContactAdd);

            viewHolder.tvContactAdd.setText(mData.get(position).get("label").toString());
            viewHolder.edtContactAdd.setText(mData.get(position).get("content").toString());

             //用户名，乘客类型，电话可编辑
            if(position==1){
                viewHolder.edtContactAdd.setEnabled(true);
                viewHolder.edtContactAdd.setFocusable(false);
                viewHolder.edtContactAdd.setFocusableInTouchMode(false);

                viewHolder.edtContactAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String items[]={"二代身份证","港澳通行证","台湾通行证","护照"};
                        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("请选择证件类型");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewHolder.edtContactAdd.setText(items[which].toString());
                            }
                        });
                        builder.create().show();
                    }
                });

            }
            else if(position==3){
                viewHolder.edtContactAdd.setEnabled(true);
                viewHolder.edtContactAdd.setFocusable(false);
                viewHolder.edtContactAdd.setFocusableInTouchMode(false);

                viewHolder.edtContactAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String items[]={"成人","儿童","学生","伤残军人"};
                        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("请选择旅客类型");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewHolder.edtContactAdd.setText(items[which].toString());
                            }
                        });
                        builder.create().show();
                    }
                });
            }
            viewHolder.edtContactAdd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    map.put(String.valueOf(position),viewHolder.edtContactAdd.getText().toString());
                }
            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
            viewHolder.tvContactAdd.setText(mData.get(position).get("label").toString());
        }
        return convertView;
    }
}
