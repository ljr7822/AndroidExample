package cn.iwenddg.androidexample.work.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;

import cn.iwenddg.androidexample.R;
import cn.iwenddg.androidexample.basemvvm.activity.BaseBDActivity;
import cn.iwenddg.androidexample.databinding.ActivityTabLayoutBinding;
import cn.iwenddg.tablayouts.listener.OnTabSelectListener;
import cn.iwenddg.tablayouts.ui.MsgView;
import cn.iwenddg.tablayouts.SegmentTabLayout;
import cn.iwenddg.androidexample.work.fragment.tab.SimpleCardFragment;
import cn.iwenddg.androidexample.work.vm.TabLayoutVM;

/**
 * TabLayout例子
 */
public class TabLayoutActivity extends BaseBDActivity<ActivityTabLayoutBinding, TabLayoutVM> {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles_3 = {"关注", "推荐", "周围"};

    private SegmentTabLayout mTabLayout;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    private void initView() {
        for (String title : mTitles_3) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
        }
        mTabLayout = binding.segmentTabLayout;


        mViewPager = binding.viewPager;
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mTabLayout.setTabData(mTitles_3);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 初始页面
        mViewPager.setCurrentItem(1);

        // 显示未读红点
        mTabLayout.showDot(1);

        // 设置未读消息红点颜色
        mTabLayout.showDot(2);
        MsgView rtv_3_2 = mTabLayout.getMsgView(2);
        if (rtv_3_2 != null) {
            rtv_3_2.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
        // 显示未读消息数量
        mTabLayout.showMsg(0, 3);
        // 设置显示位置
        //mTabLayout.setMsgMargin(0, 1f, 8f);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles_3[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}