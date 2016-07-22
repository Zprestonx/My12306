package com.zpreston.my12306.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.MyAccountAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactShowActivity extends AppCompatActivity {
    private ListView lvContactShow;
    List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_show);

        mData=getData();
        lvContactShow = (ListView) findViewById(R.id.lvContactShow);
        lvContactShow.setDivider(null);
        lvContactShow.setAdapter(new MyAccountAdapter(this,mData));
    }

    private List<Map<String,Object>> getData(){//实现Map的数据构造
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

        map=new HashMap<String,Object>();
        map.put("label","姓名");
        map.put("content","马丽豪");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件类型");
        map.put("content","身份证");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件号码");
        map.put("content","123456");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","乘客类型");
        map.put("content","学生");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","电话");
        map.put("content","123456");
        data.add(map);

        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* 使用inflate方法把布局文件中定义的菜单加载给第二个参数对应的menu对象 */
        getMenuInflater().inflate(R.menu.mine_contact_menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* 菜单栏选项点击事件 */
        switch(item.getItemId()){
            case R.id.edit_item:
                Toast.makeText(this,"you clicked Edit", Toast.LENGTH_SHORT).show();
                /* 编辑联系人——原先的编辑框均变为可编辑 */

                break;
            case R.id.remove_item:
                Toast.makeText(this,"you clicked Remove",Toast.LENGTH_SHORT).show();
                /* 删除联系人——从数据库删除并更新联系人列表 */
                Intent intent=new Intent().setClass(ContactShowActivity.this,ContactAddActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(ContactShowActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
