package com.zpreston.my12306.activity.mine;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.MyAccountAdapter;
import com.zpreston.my12306.adapter.MyContactAdapter;
import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyContactActivity extends AppCompatActivity {
    private ListView lvContact;
    List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact);

        mData=getData();
        lvContact = (ListView) findViewById(R.id.lvContact);
        lvContact.setAdapter(new MyContactAdapter(this,mData));



        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(MyContactActivity.this, "点击了联系人" + position, Toast.LENGTH_LONG).show();
                        Intent intent1=new Intent().setClass(MyContactActivity.this,ContactShowActivity.class);
                        startActivity(intent1);
                        break;

                    case 1:
                        Intent intent2=new Intent().setClass(MyContactActivity.this,ContactShowActivity.class);
                        startActivity(intent2);
                        Toast.makeText(MyContactActivity.this, "点击了联系人" + position, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    private List<Map<String,Object>> getData(){
        /* 实现Map的数据构造 */
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

        map.put("tvUser","陈伟飞（成人）");
        map.put("tvId","身份证：123456");
        map.put("tvPhone","电话：12345");
        map.put("imForward",R.drawable.forward_icon);
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("tvUser","艾米米");
        map.put("tvId","身份证：1234567");
        map.put("tvPhone","电话：123456");
        map.put("imForward",R.drawable.forward_icon);
        data.add(map);

        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* 使用inflate方法把布局文件中定义的菜单加载给第二个参数对应的menu对象 */
        getMenuInflater().inflate(R.menu.mine_contact_menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* 菜单栏选项点击事件 */

        switch (item.getItemId()){
            case R.id.add_item:

                Intent intent=new Intent(MyContactActivity.this,ContactAddActivity.class);
                startActivity(intent);
                Toast.makeText(MyContactActivity.this,"you clicked Add", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MyContactActivity.this,"Error!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
