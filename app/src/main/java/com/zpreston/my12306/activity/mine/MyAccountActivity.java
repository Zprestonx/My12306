package com.zpreston.my12306.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.LoginActivity;
import com.zpreston.my12306.adapter.MyAccountAdapter;
import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;
import com.zpreston.my12306.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAccountActivity extends AppCompatActivity {
    private ListView lvAccount;
    private Button btnExitAccount;
    private Button btnSaveAccount;
    List<Map<String, Object>> mData;
    private MyAccountAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accout);

        mData=getData();
        lvAccount = (ListView) findViewById(R.id.lvAccount);
        btnExitAccount= (Button) findViewById(R.id.btnExitAccount);
        btnSaveAccount= (Button) findViewById(R.id.btnSaveAccount);

        lvAccount.setDivider(null);
        adapter=new MyAccountAdapter(this,mData);
        lvAccount.setAdapter(adapter);

        btnSaveAccount= (Button) findViewById(R.id.btnSaveAccount);
        btnSaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyAccountActivity.this,"请点击右上角进行修改",Toast.LENGTH_SHORT).show();
            }
        });



        btnExitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyAccountActivity.this,"正在退出....",Toast.LENGTH_LONG).show();
                Intent intent=new Intent().setClass(MyAccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private List<Map<String,Object>> getData(){//实现Map的数据构造
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

        String userType = null;
        UserDao userDao=new UserDaoImpl(MyAccountActivity.this);
        User user=userDao.getUserInfo(new Util(MyAccountActivity.this).getEmail());

        if(user.getPassengerType()==0){
            userType="成人";
        }else if(user.getPassengerType()==1){
            userType="学生";
        }

        map.put("label","用户名");
        map.put("content","775079852@qq.com");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","姓名");
        map.put("content",user.getUserName());
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件类型");
        map.put("content","身份证");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","证件号码");
        map.put("content",user.getIdCard());
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","乘客类型");
        map.put("content",userType);
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("label","电话");
        map.put("content",user.getPhone());
        data.add(map);

        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* 使用inflate方法把布局文件中定义的菜单加载给第二个参数对应的menu对象 */
        getMenuInflater().inflate(R.menu.mine_account_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* 菜单栏选项点击事件 */
        switch(item.getItemId()){
            case R.id.account_item:
                adapter.setEditFlag(true);
                adapter.setData(getData());
                adapter.notifyDataSetChanged();

                btnSaveAccount= (Button) findViewById(R.id.btnSaveAccount);
                btnSaveAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MyAccountActivity.this,"保存修改....",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
            default:
                Toast.makeText(MyAccountActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}







