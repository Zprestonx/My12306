package com.zpreston.my12306.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUser;
    private EditText edtPwd;

    private Button btnLogin;
    private Button btnRegister;

    private CheckBox ckAuto;
    private TextView tvLost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPwd = (EditText) findViewById(R.id.edtPwd);

        btnLogin= (Button) findViewById(R.id.btnLogin);
        btnRegister= (Button) findViewById(R.id.btnRegister);

        ckAuto= (CheckBox) findViewById(R.id.ckAuto);
        tvLost= (TextView) findViewById(R.id.tvLost);

        /* 忘记密码 */
        tvLost.setText(Html.fromHtml("<a href=\"http://www.my12306.cn\">忘记密码？</a>"));
        tvLost.setMovementMethod(LinkMovementMethod.getInstance());

        /* 登录和自动登录 */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pwd = edtPwd.getText().toString();

                UserDao userDao=new UserDaoImpl(LoginActivity.this);
                int flag=userDao.loginVerify(user,pwd);

                /* 用户验证 */
                if(TextUtils.isEmpty(user)){
                    edtUser.setError("用户名不能空");
                    edtUser.requestFocus();
                }
                else if(TextUtils.isEmpty(pwd)){
                    edtPwd.setError("密码不能空");
                    edtPwd.requestFocus();
                }
                else if (flag==1) {
                    if(ckAuto.isChecked()){
                        SharedPreferences sp=getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();

                        editor.putString("name",user);
                        editor.putString("pwd",pwd);
                        editor.commit();
                    }else{
                        SharedPreferences sp=getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("name",user);
                        editor.remove("pwd");
                        editor.commit();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();

                } else {
                    Toast.makeText(LoginActivity.this, "用户或密码不正确", Toast.LENGTH_LONG).show();
                }
            }
        });

        /* 用户注册 */
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
