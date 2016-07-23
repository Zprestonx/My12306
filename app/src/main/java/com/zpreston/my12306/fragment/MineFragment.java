package com.zpreston.my12306.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.mine.MyAccountActivity;
import com.zpreston.my12306.activity.mine.MyContactActivity;
import com.zpreston.my12306.activity.mine.MyPasswordActivity;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    private ListView lvSimple;
    private  SimpleAdapter simpleAdapter;
    List<Map<String,Object>> mData;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mData=getData();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvSimple= (ListView) getActivity().findViewById(R.id.lvSimple);

        simpleAdapter=new SimpleAdapter(getActivity(),mData,R.layout.mine_lvsimple_item,new String[]{"icon","title"},new int[]{R.id.simple_im,R.id.simple_tv});
        lvSimple.setAdapter(simpleAdapter);
        /* 响应条目的点击事件 */
        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent1=new Intent().setClass(getActivity(),MyContactActivity.class);
                        startActivity(intent1);
                        break;

                    case 1:
                        Intent intent2=new Intent().setClass(getActivity(),MyAccountActivity.class);
                        startActivity(intent2);
                        break;

                    case 2:
                        pwd_dialog();
                        break;
                }
            }
        });
    }

    private List<Map<String,Object>> getData(){//实现Map的数据构造
        /* 创建一个ArrayList来存放Map */
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String,Object>();

        map.put("icon",R.drawable.my_contact);
        map.put("title","我的联系人");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("icon",R.drawable.my_account);
        map.put("title","我的账户");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("icon",R.drawable.my_password);
        map.put("title","修改密码");
        data.add(map);

        return data;
    }

    private void pwd_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请输入原密码");
        builder.setIcon(android.R.drawable.btn_star);

        final EditText edtPwdO=new EditText(getActivity());
        final UserDao pwdDialog=new UserDaoImpl(getActivity());

        builder.setView(edtPwdO);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String pwd=edtPwdO.getText().toString().trim();
                int rt = pwdDialog.verifyPassword("775079852@qq.com", pwd);

                /* 输入原密码正确，进入修改密码页面 */
                Toast.makeText(getActivity(), "返回值为:" + rt, Toast.LENGTH_SHORT).show();
                if (rt == 1) {
                    try {
                    Toast.makeText(getActivity(), "返回值为:" + rt, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MyPasswordActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (rt == 0) {
                    try {
                        edtPwdO.setError("原密码输入不正确，请重新输入！");
                        edtPwdO.requestFocus();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /* 点击取消，返回当前页面 */
                dialog.dismiss();
            }
        });

        builder.create().show();

    }
}
