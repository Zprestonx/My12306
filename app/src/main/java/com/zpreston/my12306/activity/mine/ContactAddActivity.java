package com.zpreston.my12306.activity.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.ContactAddAdapter;
import com.zpreston.my12306.adapter.ContactShowAdapter;
import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactAddActivity extends AppCompatActivity {
    private ListView lvContactAdd;
    List<Map<String, Object>> mData;
    private ContactAddAdapter adapter;
    private Button btnAddContact;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);

        mData=getData();
        lvContactAdd = (ListView) findViewById(R.id.lvContactAdd);
        btnAddContact= (Button) findViewById(R.id.btnAddContact);
        btnReturn= (Button) findViewById(R.id.btnReturn);

        lvContactAdd.setDivider(null);

        adapter=new ContactAddAdapter(this,mData);
        lvContactAdd.setAdapter(adapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent=new Intent(ContactAddActivity.this,MyContactActivity.class);
                startActivity(intent);*/
                finish();
            }
        });

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 添加温馨提示对话框 */
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactAddActivity.this);
                builder.setTitle("温馨提示");
                builder.setMessage("    为避免对乘客造成不必要的困扰，请务必填写真实信息！");
                builder.setIcon(R.drawable.tip);
                /*builder.setCancelable(false);*/
                builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map map=adapter.getMap();
                        int i=1;
                        if(map.get("3").toString().equals("0")) i=0;
                        Contact contact=new Contact(0,0,map.get("0").toString(),map.get("2").toString(),
                                map.get("4").toString(),i);
                        ContactDao contactDao=new ContactDaoImpl(ContactAddActivity.this);
                        contactDao.addContact("775079852@qq.com",contact);
                        Intent intent=new Intent(ContactAddActivity.this,MyContactActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("再确认一下", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();

                /*Contact contact=new Contact();
                ContactDao contactDao=new ContactDaoImpl(ContactAddActivity.this);
                contactDao.addContact(contact);*/
            }
        });
    }


    private List<Map<String,Object>> getData(){//实现Map的数据构造
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

        map=new HashMap<String,Object>();
        map.put("label","姓名");
        map.put("content","");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件类型");
        map.put("content","");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件号码");
        map.put("content","");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","乘客类型");
        map.put("content","");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","电话");
        map.put("content","");
        data.add(map);

        return data;
    }
}
