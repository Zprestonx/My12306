package com.zpreston.my12306.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.MainFragmentAdapter;
import com.zpreston.my12306.fragment.MineFragment;
import com.zpreston.my12306.fragment.OrderFragment;
import com.zpreston.my12306.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,
        TabHost.OnTabChangeListener{

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[]={TicketFragment.class, OrderFragment.class, MineFragment.class};
    private int imageViewArray[]={R.drawable.find_user_25,R.drawable.lock_25,R.drawable.remove_user_25};
    private String textViewArray[]={"火车票","车票预定","我的12306"};
    private List<Fragment> list=new ArrayList<Fragment>();
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPage();
    }

    private void initView(){
        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(this);
        layoutInflater=LayoutInflater.from(this);
        mTabHost=(FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.pager);
        mTabHost.setOnTabChangedListener(this);
        int count=textViewArray.length;
        for(int i=0;i<count;i++){
            TabHost.TabSpec tabSpec=mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec,fragmentArray[i],null);
            mTabHost.setTag(i);
        }
    }

    private void initPage(){
        TicketFragment ticketFragment=new TicketFragment();
        OrderFragment orderFragment=new OrderFragment();
        MineFragment mineFragment=new MineFragment();
        list.add(ticketFragment);
        list.add(orderFragment);
        list.add(mineFragment);
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),list));
    }

    private View getTabItemView(int position){
        View view=layoutInflater.inflate(R.layout.tab_content,null);
        ImageView tabImageView=(ImageView)view.findViewById(R.id.tabImageView);
        TextView tabTextView=(TextView)view.findViewById(R.id.tabTextView);
        tabImageView.setBackgroundResource(imageViewArray[position]);
        tabTextView.setText(textViewArray[position]);
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabWidget widget=mTabHost.getTabWidget();
        int oldFocusability=widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mTabHost.setCurrentTab(position);
        widget.setDescendantFocusability(oldFocusability);
        mTabHost.getTabWidget().getChildAt(position).setBackgroundResource(R.drawable.tab_background);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int position=mTabHost.getCurrentTab();
        viewPager.setCurrentItem(position);
    }

}
