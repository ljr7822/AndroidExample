package cn.iwenddg.androidexample.work.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.iwenddg.androidexample.R;
import cn.iwenddg.androidexample.basemvvm.activity.BaseBDActivity;
import cn.iwenddg.androidexample.basemvvm.fragment.SupperFragment;
import cn.iwenddg.androidexample.databinding.ActivityNavTabBinding;
import cn.iwenddg.androidexample.ui.nav.QLNavigationTabView;
import cn.iwenddg.androidexample.ui.nav.QLTabContainerView;
import cn.iwenddg.androidexample.work.fragment.nav.CommunityFragment;
import cn.iwenddg.androidexample.work.vm.NavTabVM;

/**
 * lottie实现底部导航的Activity
 */
public class NavTabActivity extends BaseBDActivity<ActivityNavTabBinding, NavTabVM> {

    private static final String TAG = "NavTabActivity";

    public static final int DISCOVER_TAB = 0;
    public static final int IM_TAB = 1;
    public static final int CAR_CONTROL_TAB = 2;
    public static final int SERVICE_TAB = 3;
    public static final int MINE_TAB = 4;
    // 底部导航的数量
    private static final int TAB_COUNT = 5;
    // 保存fragment的数组
    private SupperFragment[] mFragments = new SupperFragment[TAB_COUNT];
    public int mLastPos = 0;
    public int mNextPos = 0;
    // 保存着几个页面的数组
    private List<Class<? extends SupperFragment>> mFragmentList =
            Arrays.asList(
                    CommunityFragment.class,
                    CommunityFragment.class,
                    CommunityFragment.class,
                    CommunityFragment.class,
                    CommunityFragment.class);

    private String[] mExtraAnimations;

    // 内容填充区
    private FrameLayout mContainerView;
    // 底部导航区
    private QLTabContainerView mQlTabContainerView;
    // 动画
    private LinearLayout mTabExtraView;
    // 分割线
    private View divider;

    @Override
    public int getLayoutId() {
        return R.layout.activity_nav_tab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        divider = binding.divider;
        mQlTabContainerView = binding.tabContainerView;
        mContainerView = binding.containerView;
        mTabExtraView = binding.tabExtras;
        mQlTabContainerView.setDividerView(divider);
        mQlTabContainerView.setTabClickListener(view -> {
            if (view instanceof QLNavigationTabView) {
                int pos = ((QLNavigationTabView) view).getPosition();
                // 双击刷新Tab
                if (pos == DISCOVER_TAB && pos == mLastPos) {
                    // TODO 双击刷新
                    Toast.makeText(this, "社区触发双击刷新啦...", Toast.LENGTH_SHORT).show();
                } else if (pos == IM_TAB && pos == mLastPos) {
                    // TODO 双击刷新
                    Toast.makeText(this, "消息触发双击刷新啦...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mQlTabContainerView.setTabChangeListener(showPos -> {
            Log.i(TAG, "currentTab = " + mLastPos + ", changeToTab = " + showPos);
            mNextPos = showPos;
            // 显示隐藏Fragment
            showHideFragment(showPos);
            // showExtraAnimation
            showExtraAnimation(showPos);
            return true;
        });

        QLTabContainerView.OnTabReadyListener listener = loadedTheme -> {
            adaptChangeToTab();
            if (loadedTheme != null) {
                mExtraAnimations = new String[]{
                        loadedTheme.tab1.extraAnimJson,
                        loadedTheme.tab2.extraAnimJson,
                        loadedTheme.tab3.extraAnimJson,
                        loadedTheme.tab4.extraAnimJson,
                        loadedTheme.tab5.extraAnimJson,
                };

                for (int pos = 0, l = mExtraAnimations.length; pos < l; pos++) {
                    if (!TextUtils.isEmpty(mExtraAnimations[pos])) {
                        LottieAnimationView lottieView = (LottieAnimationView) mTabExtraView.getChildAt(pos);
                        lottieView.setAnimationFromJson(mExtraAnimations[pos]);
                        lottieView.addAnimatorUpdateListener(animation -> {
                            if (animation.getAnimatedFraction() == 1f) {
                                lottieView.setVisibility(View.INVISIBLE);
                            }
                        });
                        lottieView.setRepeatCount(0);
                    }
                }
            }
        };

        // 使用默认的tab
        mQlTabContainerView.createDefaultMainTab(NavTabActivity.this, listener);

        mContainerView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mContainerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    private void adaptChangeToTab() {
        changeToTab(mLastPos);
    }

    public void changeToTab(int pos) {
        if (mQlTabContainerView != null) {
            mQlTabContainerView.changeToTab(pos);
        }
    }

    /**
     * 根据下标显示和隐藏fragment
     *
     * @param showPos 下标
     */
    private synchronized void showHideFragment(int showPos) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Class<? extends SupperFragment> clazz = mFragmentList.get(showPos);
        Log.i(TAG, "showPos = " + clazz.getSimpleName());
        try {
            SupperFragment fragment = mFragments[showPos];
            if (fragment == null) {
                fragment = findFragment(clazz);
            }
            if (fragment == null) {
                mFragments[showPos] = fragment = clazz.newInstance();
                fragmentTransaction.add(R.id.container_view, mFragments[showPos], String.valueOf(showPos));
                fragmentTransaction.commitAllowingStateLoss();
                Log.i(TAG, "new fragment " + fragment);
            } else {
                mFragments[showPos] = fragment;
            }
            showFragment(showPos);
            //setFragmentStatusBar(fragment);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "MainActivity e == " + e.toString());
        }
        Log.i(TAG, "showPos = " + showPos);
    }

    /**
     * 根据showPos显示对应的fragment
     *
     * @param showPos int
     */
    private void showFragment(int showPos) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.length; i++) {
            Fragment fragment = mFragments[i];
            if (fragment != null && i != showPos && !fragment.isHidden()) {
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.show(mFragments[showPos]);
        mLastPos = showPos;
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 显示额外的动画
     *
     * @param pos 下标
     */
    private void showExtraAnimation(int pos) {
        if (mExtraAnimations != null && mExtraAnimations.length > pos) {
            if (!TextUtils.isEmpty(mExtraAnimations[pos])) {
                LottieAnimationView lottieView = (LottieAnimationView) mTabExtraView.getChildAt(pos);
                lottieView.setVisibility(View.VISIBLE);
                lottieView.playAnimation();
            }
        }
    }

    public QLTabContainerView getTabContainerView() {
        return mQlTabContainerView;
    }
}