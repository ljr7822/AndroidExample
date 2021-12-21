package cn.iwenddg.androidexample.work.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import cn.iwenddg.androidexample.R;
import cn.iwenddg.androidexample.basemvvm.activity.BaseBDActivity;
import cn.iwenddg.androidexample.databinding.ActivityNavViewPagerBinding;
import cn.iwenddg.androidexample.work.adapter.MyFragmentPagerAdapter;
import cn.iwenddg.androidexample.work.fragment.nav.CommunityFragment;
import cn.iwenddg.androidexample.work.vm.NavViewPagerTabVM;

/**
 * viewpager实现底部导航的Activity
 */
public class NavViewPagerActivity extends BaseBDActivity<ActivityNavViewPagerBinding, NavViewPagerTabVM> implements RadioGroup.OnCheckedChangeListener {

    List<Fragment> fragmentList = new ArrayList<>();
    MyFragmentPagerAdapter adapter;
    ViewPager viewPager;
    RadioGroup rg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_nav_view_pager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化控件
        initView();
        // 绑定RadioButton
        initViewPager();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_wallet:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_treasure:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_home:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        rg.getChildAt(0).performClick();
    }

    private void initViewPager() {
        // 添加碎片
        fragmentList.add(new CommunityFragment());
        fragmentList.add(new CommunityFragment());
        fragmentList.add(new CommunityFragment());

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        rg.check(R.id.rb_wallet);
                        break;
                    case 1:
                        rg.check(R.id.rb_treasure);
                        break;
                    case 2:
                        rg.check(R.id.rb_home);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}