package com.zpreston.my12306.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtRuser;
    private EditText edtRpwdF;
    private EditText edtRpwdS;
    private EditText edtRname;
    private EditText edtRgender;
    private EditText edtRidType;
    private EditText edtRid;
    private EditText edtRphone;
    private EditText edtRpersonType;
    private Button btnRwork;
    private Button btnRback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRuser = (EditText) findViewById(R.id.edtRuser);
        edtRpwdF = (EditText) findViewById(R.id.edtRpwdF);
        edtRpwdS = (EditText) findViewById(R.id.edtRpwdS);
        edtRname = (EditText) findViewById(R.id.edtRname);
        edtRgender = (EditText) findViewById(R.id.edtRgender);
        edtRidType = (EditText) findViewById(R.id.edtRidType);
        edtRid = (EditText) findViewById(R.id.edtRid);
        edtRphone = (EditText) findViewById(R.id.edtRphone);
        edtRpersonType = (EditText) findViewById(R.id.edtRpersonType);

        btnRwork= (Button) findViewById(R.id.btnRwork);
        btnRback= (Button) findViewById(R.id.btnRback);

        btnRwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        btnRback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void submit(){
        UserDao userDao=new UserDaoImpl(RegisterActivity.this);
        User loginUser=new User();

        int gender;
        int idType;
        int personType;


        /* 匹配密码 */
        String pwd1=edtRpwdF.getText().toString();
        String pwd2=edtRpwdS.getText().toString();


        edtRgender.setEnabled(true);
        edtRgender.setFocusable(false);
        edtRgender.setFocusableInTouchMode(false);
        /* 性别选择 */
        edtRgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items1[]={"男","女"};
                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("请选择性别");
                builder.setItems(items1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtRgender.setText(items1[which].toString());
                    }
                });
            }
        });
        if(edtRgender.getText().toString().equals("女")){
            gender=0;
        }else gender=1;

        /* 证件类型选择 */
        edtRidType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items2[]={"二代身份证","港澳通行证","台湾通行证","护照"};
                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("请选择证件类型");
                builder.setItems(items2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtRgender.setText(items2[which].toString());
                    }
                });
            }
        });
        if(edtRidType.getText().toString().equals("二代身份证")){
            idType=0;
        }else idType=1;

        /* 旅客类型选择 */
        edtRpersonType.setEnabled(true);
        edtRpersonType.setFocusable(false);
        edtRpersonType.setFocusableInTouchMode(false);
        edtRpersonType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items3[]={"成人","儿童","学生","伤残军人"};
                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("请选择旅客类型");
                builder.setItems(items3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtRgender.setText(items3[which].toString());
                    }
                });
            }
        });
        if(edtRpersonType.getText().toString().equals("成人")){
            personType=0;
        }else personType=1;


        edtRidType.setEnabled(true);
        edtRidType.setFocusable(false);
        edtRidType.setFocusableInTouchMode(false);

        if(TextUtils.isEmpty(edtRuser.getText().toString())){
            edtRuser.setError("用户名不能为空！");
            edtRuser.requestFocus();
        }
        else if(TextUtils.isEmpty(pwd1)){
            edtRpwdF.setError("密码不能为空！");
            edtRpwdF.requestFocus();
        }
        else if(TextUtils.isEmpty(pwd2)){
            edtRpwdS.setError("密码不能为空！");
            edtRpwdS.requestFocus();
        }else if(!pwd1.equals(pwd2)){
            edtRpwdS.setError("两次密码输入不一致，请重新输入！");
            edtRpwdS.requestFocus();
        }
        else if(TextUtils.isEmpty(edtRgender.getText().toString())){
            edtRgender.setError("请选择性别！");
            edtRgender.requestFocus();
        }
        else if(TextUtils.isEmpty(edtRidType.getText().toString())) {
            edtRidType.setError("请选择证件类型！");
            edtRidType.requestFocus();
        }
        else if(TextUtils.isEmpty(edtRid.getText().toString())) {
            edtRid.setError("证件号不能为空！");
            edtRid.requestFocus();
        }
        else if(TextUtils.isEmpty(edtRphone.getText().toString())) {
            edtRphone.setError("电话号码不能为空！");
            edtRphone.requestFocus();
        }
        else if(TextUtils.isEmpty(edtRpersonType.getText().toString())) {
            edtRpersonType.setError("请选择乘客类型！");
            edtRpersonType.requestFocus();
        }else{
            /* 初始化用户 */
            loginUser.setUid(0);
            loginUser.setEmail(edtRuser.getText().toString());
            loginUser.setPassword(edtRpwdF.getText().toString());
            loginUser.setUserName(edtRname.getText().toString());
            loginUser.setGender(gender);
            loginUser.setCertificateType(idType);
            loginUser.setIdCard(edtRid.getText().toString());
            loginUser.setPhone(edtRphone.getText().toString());
            loginUser.setPassengerType(personType);
            loginUser.setLastLoginTime(null);
            loginUser.setUserStatus(0);

        /* 增加注册用户 */
            userDao.register(loginUser);
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(RegisterActivity.this,"正在提交....",Toast.LENGTH_SHORT);
            Toast.makeText(RegisterActivity.this,"请先登录再进行选票",Toast.LENGTH_SHORT);
        }
    }
}
