package com.zpreston.my12306.activity.ticket;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.Ticket3Adapter;
import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Passenger;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket3Activity extends AppCompatActivity {
    Ticket3Adapter ticket3Adapter;
    public static ArrayList<Activity> activityD = new ArrayList<Activity>();
    private ListView lvContact;
    List<Map<String, Object>> mData;

    private TextView q;
    private Button w;

    private TextView seat;
    private TextView seatPri;
    private TextView title;
    private TextView t;
    private TextView t1;
    private TextView t2;
    private TextView c1;
    private TextView c2;
    String seatMes;
    String seatNum;
    String startTime;
    String startCity;
    String endCity;
    String trainNo_;
    int seatPrice;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket3);
        TicketFragment.activityS.add(this);
        Ticket3Activity.activityD.add(this);

        c1 = (TextView) this.findViewById(R.id.textView7);
        c2 = (TextView) this.findViewById(R.id.textView12);
        t1 = (TextView) this.findViewById(R.id.textView8);
        t2 = (TextView) this.findViewById(R.id.textView13);
        t = (TextView) this.findViewById(R.id.textView11);
        title = (TextView) this.findViewById(R.id.textView10);
        seat = (TextView) this.findViewById(R.id.textView9);
        seatPri = (TextView) this.findViewById(R.id.textView14);

        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ticket1");
        seatMes = bundle.getString("seatMes");
        seatNum = bundle.getString("seatNum");
        seatPrice= bundle.getInt("seatPri");
        startCity = bundle.getString("beginCity");
        endCity = bundle.getString("endCity");
        trainNo_ = bundle.getString("trainNo");  //设置对应车次信息
        String startTime_ = bundle.getString("startTime");
        String arriveTime_ = bundle.getString("arriveTime");
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        if (month < 10) {
            startTime = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        } else {
            startTime = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day); //记录规格化日期
        }
        t.setText(startTime + "(0日)");                       //传日期,设置TextView
        c1.setText(startCity);
        c2.setText(endCity);
        t1.setText(startTime_);
        t2.setText(arriveTime_);
        title.setText(trainNo_);
        seatPri.setText("seatPrice");
        seat.setText(seatMes + "(" + seatNum + "张)");

        q = (TextView) this.findViewById(R.id.textView15);
        // w = (Button) this.findViewById(R.id.button5);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //点击添加联系人跳转
                Intent intent = new Intent(Ticket3Activity.this, Ticket3_AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        mData = getData();
        lvContact = (ListView) findViewById(R.id.listView4);

    }

    public void submit(View view) { //提交按钮跳转
        Intent intent = new Intent(this, Ticket4Activity.class);
        startActivity(intent);
    }

    private List<Map<String, Object>> getData() {
        /* 实现Map的数据构造 */
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        ContactDao contactDao = new ContactDaoImpl(Ticket3Activity.this);
        List<Contact> contactList = contactDao.queryMyContacts("775079852@qq.com");

        String contactType = null;

        for (Contact contact : contactList) {
            if (contact.getContactState() == 0) {
                contactType = "成人";
            } else if (contact.getContactState() == 1) {
                contactType = "学生";
            }

            map.put("tvContactName", contact.getContactName() + "(" + contactType + ")");
            map.put("tvIdCard", "身份证：" + contact.getContactCardId());
            map.put("tvPhone", "电话：" + contact.getContactPhone());
            map.put("imForward", R.drawable.delete_contact);
            data.add(map);
        }
        return data;
    }

    /*public void simplelist(View view) { //删除按钮跳转
        Intent intent=new Intent(this,SListViewActivity.class);
        startActivity(intent);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getBundleExtra("ticket");
                    Log.e("*add", "11");
                    int size = bundle.getInt("size", 0);
                    List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
                    if (size != 0) {
                        for (int j = 0; j < size; j++) {
                            Passenger passenger = (Passenger) bundle.getSerializable(String.valueOf(j));
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("tvContactName", passenger.getTvContactName());
                            map.put("tvIdCard", "身份证：" + passenger.getTvIdCard());
                            map.put("tvPhone", "电话：" + passenger.getTvPhone());

                            data1.add(map);

                        }
                        ticket3Adapter = new Ticket3Adapter(this, data1, lvContact);
                        lvContact.setAdapter(ticket3Adapter);
                    }
                    //Log.d("FirstActivity", returnedData);
                }
                break;
            default:
        }
    }
}
