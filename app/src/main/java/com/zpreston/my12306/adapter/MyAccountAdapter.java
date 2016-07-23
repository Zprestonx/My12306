package com.zpreston.my12306.adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import com.zpreston.my12306.R;

import org.w3c.dom.Text;

/**
 * Created by 小m on 2016/7/21.
 */
public class MyAccountAdapter extends BaseAdapter {
    private boolean editFlag=false;
    private Context context;
    private List<Map<String, Object>> mData;
    public MyAccountAdapter(Context context,List<Map<String, Object>> mData){
        this.context=context;
        this.mData=mData;
    }

    public void setEditFlag(boolean editFlag){
        this.editFlag=editFlag;
    }
    public void setData(List<Map<String,Object>> data){
        this.mData=data;
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
        final ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context,R.layout.mine_lvaccount_item,null);
            viewHolder.tvAccount=(TextView)convertView.findViewById(R.id.tvAccount);
            viewHolder.edtAccount=(EditText)convertView.findViewById(R.id.edtAccount);
            viewHolder.edtAccount.setClickable(false);
            viewHolder.edtAccount.setEnabled(false);
            viewHolder.tvAccount.setText(mData.get(position).get("label").toString());
            viewHolder.edtAccount.setText(mData.get(position).get("content").toString());
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
            /* 用户名，乘客类型，电话可编辑 */
            if(editFlag && (position==0 || position==4 || position==5)){
                if(position==4){
                    viewHolder.edtAccount.setEnabled(true);
                    viewHolder.edtAccount.setFocusable(false);
                    viewHolder.edtAccount.setFocusableInTouchMode(false);
                    viewHolder.edtAccount.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final String items[]={"成人","儿童","学生","伤残军人"};
                            AlertDialog.Builder builder=new AlertDialog.Builder(context);
                            builder.setTitle("请选择旅客类型");
                            builder.setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    viewHolder.edtAccount.setText(items[which].toString());
                                }
                            });
                            builder.create().show();
                        }
                    });
                }
                /* 其他不可更改 */
                else{
                    viewHolder.edtAccount.setEnabled(true);
                    viewHolder.edtAccount.setFocusable(true);
                    viewHolder.edtAccount.setFocusableInTouchMode(true);
                }
            }
        }
        return convertView;
    }


}
