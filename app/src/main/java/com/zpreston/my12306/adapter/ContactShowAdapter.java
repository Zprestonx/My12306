package com.zpreston.my12306.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    private boolean editFlag=false;
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

    /* 设置编辑标签 */
    public void setEditFlag(boolean editFlag){
        this.editFlag=editFlag;
    }

    /* 获取数据 */
    public void setData(List<Map<String,Object>> data){
        this.mData=data;
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
            convertView=View.inflate(context, R.layout.contact_lvshow_item,null);


            viewHolder.tvContactShow=(TextView)convertView.findViewById(R.id.tvContactShow);
            viewHolder.edtContactShow=(EditText)convertView.findViewById(R.id.edtContactShow);

            viewHolder.edtContactShow.setClickable(false);
            viewHolder.edtContactShow.setEnabled(false);

            viewHolder.tvContactShow.setText(mData.get(position).get("label").toString());
            viewHolder.edtContactShow.setText(mData.get(position).get("content").toString());

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();


            /* 用户名，乘客类型，电话可编辑 */
            if(editFlag && (position==0 || position==3 || position==4)){
                if(position==3){
                    viewHolder.edtContactShow.setEnabled(true);
                    viewHolder.edtContactShow.setFocusable(false);
                    viewHolder.edtContactShow.setFocusableInTouchMode(false);

                    viewHolder.edtContactShow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final String items[]={"成人","儿童","学生","伤残军人"};
                            AlertDialog.Builder builder=new AlertDialog.Builder(context);
                            builder.setTitle("请选择旅客类型");
                            builder.setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    viewHolder.edtContactShow.setText(items[which].toString());
                                }
                            });
                            builder.create().show();
                        }
                    });
                }
                /* 其他不可更改 */
                else{
                    viewHolder.edtContactShow.setEnabled(true);
                    viewHolder.edtContactShow.setFocusable(true);
                    viewHolder.edtContactShow.setFocusableInTouchMode(true);
                }
            }
        }
        return convertView;
    }
}
