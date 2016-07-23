package com.zpreston.my12306.activity.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.ContactShowAdapter;
import com.zpreston.my12306.adapter.MyAccountAdapter;
import com.zpreston.my12306.adapter.MyContactAdapter;
import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactShowActivity extends AppCompatActivity {
    private ListView lvContactShow;
    List<Map<String, Object>> mData;
    private ContactShowAdapter adapter;
    private Button btnSaveContact;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("TAG","show");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_show);
        Log.e("TAG","show-after");

        lvContactShow = (ListView) findViewById(R.id.lvContactShow);
        btnSaveContact = (Button) findViewById(R.id.btnSaveContact);
        lvContactShow.setDivider(null);

        btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactShowActivity.this, "正在保存....", Toast.LENGTH_LONG).show();
                Intent intent=new Intent().setClass(ContactShowActivity.this,MyContactActivity.class);

                startActivity(intent);
            }
        });
        mData=getData();
        adapter=new ContactShowAdapter(this,mData);
        lvContactShow.setAdapter(adapter);
    }

    private List<Map<String,Object>> getData(){//实现Map的数据构造
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

        Intent intent=getIntent();

        map.put("label","姓名");
        map.put("content",intent.getStringExtra("contactName"));

        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件类型");
        map.put("content",intent.getStringExtra("idType"));

        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件号码");
        map.put("content",intent.getStringExtra("contactCardId"));

        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","乘客类型");
        map.put("content",intent.getStringExtra("contactType"));

        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","电话");
        map.put("content",intent.getStringExtra("contactPhone"));

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

        ////////////////////
        ContactDao contactDao=new ContactDaoImpl(ContactShowActivity.this);

        switch(item.getItemId()){
            case R.id.edit_item:
                adapter.setEditFlag(true);
                adapter.setData(getData());
                adapter.notifyDataSetChanged();
                Toast.makeText(this,"you clicked Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"you clicked Remove",Toast.LENGTH_SHORT).show();
                /*contactDao.deleteContact()*/
                /* 删除联系人——从数据库删除并更新联系人列表 */
                /*
                Intent intent=new Intent().setClass(ContactShowActivity.this,ContactAddActivity.class);
                startActivity(intent);*/
                break;
            default:
                Toast.makeText(ContactShowActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
