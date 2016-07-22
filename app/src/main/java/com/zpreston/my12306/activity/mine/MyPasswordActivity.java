package com.zpreston.my12306.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.MainActivity;
import com.zpreston.my12306.fragment.MineFragment;

public class MyPasswordActivity extends AppCompatActivity {
    private EditText edtPwdO;
    private EditText edtPwdF;
    private EditText edtPwdS;
    private Button btnPst;
    private Button btnNgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_password);

        edtPwdO= (EditText) findViewById(R.id.edtPwdO);
        edtPwdF= (EditText) findViewById(R.id.edtPwdF);
        edtPwdS= (EditText) findViewById(R.id.edtPwdS);
        btnPst= (Button) findViewById(R.id.btnPst);
        btnNgt= (Button) findViewById(R.id.btnNgt);

        /* 输入两次的密码一样则可以点击按钮 */
        btnPst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd=edtPwdO.getText().toString();
                String pwd1=edtPwdF.getText().toString();
                String pwd2=edtPwdS.getText().toString();

                if(TextUtils.isEmpty(pwd)){
                    edtPwdO.setError("密码不能空");
                    edtPwdO.requestFocus();
                }
                else if(TextUtils.isEmpty(pwd1)){
                    edtPwdF.setError("密码不能空");
                    edtPwdF.requestFocus();
                }
                else if(TextUtils.isEmpty(pwd2)){
                    edtPwdS.setError("密码不能空");
                    edtPwdS.requestFocus();
                }
                else if(!pwd1.equals(pwd2)){
                    Toast.makeText(MyPasswordActivity.this,"两次输入的密码不相同，请重新输入！",Toast.LENGTH_LONG).show();
                }
                else if(pwd1.equals(pwd2)){
                    /* 如果密码相同，点击确定则保存密码 */
                    Toast.makeText(MyPasswordActivity.this,"保存新密码....",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        btnNgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 点击取消修改，返回当前fragement */
                Toast.makeText(MyPasswordActivity.this,"取消修改密码",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
