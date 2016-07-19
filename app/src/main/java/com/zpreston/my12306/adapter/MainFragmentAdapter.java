package com.zpreston.my12306.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by preston on 2016/7/19.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> list;

    public MainFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public MainFragmentAdapter(FragmentManager fragmentManager,List<Fragment> list){
        super(fragmentManager);
        this.list=list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
