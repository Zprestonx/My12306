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
        lvContactShow.setDivider(null);

        mData=getData(getIntent());
        adapter=new ContactShowAdapter(this,mData);
        lvContactShow.setAdapter(adapter);

        btnSaveContact = (Button) findViewById(R.id.btnSaveContact);
        btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactShowActivity.this, "正在保存....", Toast.LENGTH_LONG).show();
                Map<String,Object> map=adapter.getMap();
                String contactName=map.get("0").toString();
                Log.e("Map",contactName);
                String contactType=map.get("3").toString();
                Log.e("Map",contactType);
                int i=0;
                if(contactType.equals("学生")) i=1;
                String contactPhone=map.get("4").toString();
                Log.e("Map",contactPhone);
                ContactDao contactDao=new ContactDaoImpl(ContactShowActivity.this);
                contactDao.updateContact(contactName,i,contactPhone);
                Intent intent=new Intent(ContactShowActivity.this,MyContactActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private List<Map<String,Object>> getData(Intent intent){//实现Map的数据构造
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

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


        switch(item.getItemId()){
            case R.id.edit_item:
                adapter.setEditFlag(true);
                adapter.setData(getData(getIntent()));
                adapter.notifyDataSetChanged();
                btnSaveContact.setVisibility(View.VISIBLE);
                Toast.makeText(this,"you clicked Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:

                ContactDao contactDao=new ContactDaoImpl(ContactShowActivity.this);
                //Toast.makeText(ContactShowActivity.this,"you clicked Remove",Toast.LENGTH_SHORT).show();
                Log.e("before","***");
                Map<String,Object> map=adapter.getMap();
                Log.e("deleteBefore",map.get("0").toString());
                contactDao.deleteContact("775079852@qq.com",map.get("0").toString());
                Log.e("deleteAfter",map.get("0").toString());
                Intent intent=new Intent(ContactShowActivity.this,MyContactActivity.class);
                startActivity(intent);
                //finish();
                break;
            default:
                Toast.makeText(ContactShowActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
