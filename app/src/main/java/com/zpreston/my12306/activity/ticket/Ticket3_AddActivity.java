package com.zpreston.my12306.activity.ticket;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.mine.ContactAddActivity;
import com.zpreston.my12306.fragment.TicketFragment;

public class Ticket3_AddActivity extends AppCompatActivity {
    private Button q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket3__add);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        q = (Button) this.findViewById(R.id.button6);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket3_AddActivity.this, Ticket3Activity.class);
                startActivity(intent);
                for (Activity activity : Ticket3Activity.activityD) { //将多出的车票预定3的Activity Finish掉
                    activity.finish();
                }
                finish();
            }
        });

    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 使用inflate方法把布局文件中定义的菜单加载给第二个参数对应的menu对象
        getMenuInflater().inflate(R.menu.mine_contact_menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 菜单栏选项点击事件
        switch(item.getItemId()){
            case R.id.edit_item:
                Toast.makeText(this,"you clicked Edit", Toast.LENGTH_SHORT).show();
                //编辑联系人——原先的编辑框均变为可编辑 *//**//*

                break;
            case R.id.remove_item:
                Toast.makeText(this,"you clicked Remove",Toast.LENGTH_SHORT).show();
                // 删除联系人——从数据库删除并更新联系人列表
                Intent intent=new Intent().setClass(Ticket3_AddActivity.this,Ticket3Activity.class);
                startActivity(intent);
                break;

            case R.id.add_item:
                Toast.makeText(this,"you clicked Add", Toast.LENGTH_SHORT).show();

            default:
                Toast.makeText(Ticket3_AddActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }*/
}
