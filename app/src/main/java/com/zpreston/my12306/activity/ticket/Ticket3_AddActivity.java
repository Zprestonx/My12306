package com.zpreston.my12306.activity.ticket;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.Ticket3Adapter;
import com.zpreston.my12306.adapter.Ticket3AddAdapter;
import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Passenger;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket3_AddActivity extends AppCompatActivity {
    public static ArrayList<Activity> activityD = new ArrayList<Activity>();
    Util util=new Util(this);
    private ListView lvContact;
    List<Map<String, Object>> mData;

    private Button q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket3_add);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        mData=getData();
        lvContact = (ListView) findViewById(R.id.listView5);
        final Ticket3AddAdapter ticket3AddAdapter=new Ticket3AddAdapter(this,mData);
        lvContact.setAdapter(ticket3AddAdapter);

        q = (Button) this.findViewById(R.id.button6);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {         //点击添加联系人功能实现
                List<Map<String,Object>> list=ticket3AddAdapter.getList();
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putInt("size",list.size());
                for (int i=0;i<list.size();i++){
                    Map map=list.get(i);
                    Passenger passenger=new Passenger();
                    passenger.setTvContactName(map.get("1").toString());
                    passenger.setTvIdCard(map.get("2").toString());
                    passenger.setTvPhone(map.get("3").toString());
                    bundle.putSerializable(String.valueOf(i),passenger);
                }


                intent.putExtra("ticket",bundle);
                Ticket3_AddActivity.this.setResult(RESULT_OK,intent);
                /*for (Activity activity : Ticket3Activity.activityD) { //将多出的车票预定3的Activity Finish掉
                    activity.finish();
                }*/
                finish();
            }
        });
    }

    private List<Map<String,Object>> getData(){
        /* 实现Map的数据构造 */
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();

        ContactDao contactDao=new ContactDaoImpl(Ticket3_AddActivity.this);
        Util util=new Util(this);
        List<Contact> contactList=contactDao.queryMyContacts(util.getEmail());
        Log.e("email",util.getEmail());

        String contactType = null;

        for(Contact contact:contactList){
            if(contact.getContactState()==0){
                contactType = "成人";
            }else if(contact.getContactState()==1){
                contactType = "学生";
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("tvContactName",contact.getContactName()+"("+contactType+")");
            map.put("tvIdCard",contact.getContactCardId());
            map.put("tvPhone",contact.getContactPhone());
            data.add(map);
        }
        return data;
    }


}
