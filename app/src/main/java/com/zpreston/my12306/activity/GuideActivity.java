package com.zpreston.my12306.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpreston.my12306.R;
import com.zpreston.my12306.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class GuideActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    //ViewPager用来显示每张图片
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private ArrayList<View> views = new ArrayList<View>();
    //引导页显示的图片资源id
    private static final int[] images = {R.drawable.home, R.drawable.travel,
            R.drawable.go, R.drawable.one};
    private ImageView[] dots;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG-before","***********before");
        setContentView(R.layout.activity_guide);
        Log.e("TAG-after","***********after");
        Button start=(Button)findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startUse();
            }
        });
        //为引导页动态添加布局，每个布局包含一个ImageView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageResource(images[i]);
            views.add(imageView);
        }
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        //底部小圆点的初始化
        initDots();
    }
    //开始使用，进入主页面
    private void startUse(){
        Intent intent=new Intent(GuideActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    //设置底部小圆点的点击事件以及Tag标志
    private void initDots() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dotsView);
        dots=new ImageView[images.length];
        for(int i=0;i<images.length;i++){
            dots[i]=(ImageView)linearLayout.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);
        }
        currentIndex=0;
        dots[currentIndex].setEnabled(false);
    }
    //设置当前选中的视图
    private void setCurrentView(int position){
        if(position<0 || position>=images.length){
            return;
        }else{
            viewPager.setCurrentItem(position);
        }
    }
    //设置当前选中的小圆点
    private void setCurrentDot(int position){
        if(position<0 || position>images.length-1 || currentIndex==position){
            return;
        }else{
            dots[position].setEnabled(false);
            dots[currentIndex].setEnabled(true);
            currentIndex=position;
        }
    }
    //获取被点击页的Tag，设置当前选中的视图以及当前选中的小圆点
    @Override
    public void onClick(View view) {
        int position=(Integer)view.getTag();
        setCurrentView(position);
        setCurrentDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }
    //选中页的处理操作，设置选中的小圆点
    @Override
    public void onPageSelected(int arg0) {
        setCurrentDot(arg0);
    }
}