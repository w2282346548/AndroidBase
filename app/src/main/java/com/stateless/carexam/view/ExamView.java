package com.stateless.carexam.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.stateless.carexam.R;

/**
 * Created by stateless on 2017/3/23.
 */

public class ExamView extends RelativeLayout {

    Context mContext;
    private ViewPager viewPager;
    FragmentManager fragmentManager;
    private ExamFragmentAdapter adapter;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    ExamModel data;

    public void setData(ExamModel data) {
        this.data = data;
        update();
    }

    private void update() {
        adapter = new ExamFragmentAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
    }

    public ExamView(Context context) {
        this(context,null);
    }

    public ExamView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExamView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_exam,this);

        viewPager = (ViewPager) findViewById(R.id.vp_viewpager);



    }


   public static class ExamModel{
        private String name;
        private int count;
        private int type;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

     class ExamFragmentAdapter extends FragmentPagerAdapter {

        public ExamFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return  ExamFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return data.getCount();
        }
    }
}
