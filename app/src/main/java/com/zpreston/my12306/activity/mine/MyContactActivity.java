package com.zpreston.my12306.activity.mine;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.MyAccountAdapter;
import com.zpreston.my12306.adapter.MyContactAdapter;
import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.daoImpl.UserDaoImpl;
import com.zpreston.my12306.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MyContactActivity extends AppCompatActivity {
    private ListView lvContact;
    List<Map<String, Object>> mData;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact);

        mData=getData();

        lvContact = (ListView) findViewById(R.id.lvContact);
        final MyContactAdapter myContactAdapter=new MyContactAdapter(this,mData);
        lvContact.setAdapter(myContactAdapter);


        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ContactDao contactDao=new ContactDaoImpl(MyContactActivity.this);
                Toast.makeText(MyContactActivity.this, "点击了联系人" + position, Toast.LENGTH_SHORT).show();
                String userEmail=new Util(MyContactActivity.this).getEmail();
                Contact contact=contactDao.querySingleContact(userEmail,
                        myContactAdapter.getContactName(position));
                Log.e("tag",contact.toString());
                String contactType=null;
                Log.e("TAG",String.valueOf(contact.getContactState()));

                if(contact.getContactState()==0){
                    contactType="成人";
                }else if(contact.getContactState()==1){
                    contactType="学生";
                }

                Intent intent=new Intent(MyContactActivity.this,ContactShowActivity.class);
                intent.putExtra("contactName",contact.getContactName());
                intent.putExtra("idType","二代身份证");
                intent.putExtra("contactCardId",contact.getContactCardId());
                intent.putExtra("contactType",contactType);
                intent.putExtra("contactPhone",contact.getContactPhone());
                startActivity(intent);
                finish();
            }
        });
    }

    private List<Map<String,Object>> getData(){
        /* 实现Map的数据构造 */
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();

        ContactDao contactDao=new ContactDaoImpl(MyContactActivity.this);
        List<Contact> contactList=contactDao.queryMyContacts("775079852@qq.com");

        String contactType = null;

        for(Contact contact:contactList){
            if(contact.getContactState()==0){
                contactType = "成人";
            }else if(contact.getContactState()==1){
                contactType = "学生";
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("contactType",contactType);
            map.put("tvContactName",contact.getContactName());
            map.put("tvIdCard","身份证："+contact.getContactCardId());
            map.put("tvPhone","电话："+contact.getContactPhone());
            map.put("imForward",R.drawable.forward_icon);
            data.add(map);
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* 使用inflate方法把布局文件中定义的菜单加载给第二个参数对应的menu对象 */
        getMenuInflater().inflate(R.menu.mine_contact_menu1,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* 菜单栏选项点击事件 */

        switch (item.getItemId()){
            case R.id.add_item:
                Intent intent=new Intent(MyContactActivity.this,ContactAddActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(MyContactActivity.this,"you clicked Add", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MyContactActivity.this,"Error!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
