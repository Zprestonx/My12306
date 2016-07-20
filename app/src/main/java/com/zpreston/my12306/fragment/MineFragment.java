package com.zpreston.my12306.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.mine.MyAccountActivity;
import com.zpreston.my12306.activity.mine.MyContactActivity;
import com.zpreston.my12306.activity.mine.MyPasswordActivity;

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

        simpleAdapter=new SimpleAdapter(getActivity(),mData,R.layout.listview_simple_mine,new String[]{"icon","title"},new int[]{R.id.simple_im,R.id.simple_tv});
        lvSimple.setAdapter(simpleAdapter);
        /* 响应条目的点击事件 */
        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent1=new Intent().setClass(getActivity(),MyContactActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getActivity(), "点击了我的联系人" + position, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Intent intent2=new Intent().setClass(getActivity(),MyAccountActivity.class);
                        startActivity(intent2);
                        Toast.makeText(getActivity(), "点击了我的账户" + position, Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Intent intent3=new Intent().setClass(getActivity(),MyPasswordActivity.class);
                        startActivity(intent3);
                        Toast.makeText(getActivity(), "点击了我的密码" + position, Toast.LENGTH_LONG).show();
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
        map.put("title","我的密码");
        data.add(map);

        return data;
    }
}
