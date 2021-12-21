package cn.iwenddg.androidexample.work.vm;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import cn.iwenddg.androidexample.basemvvm.application.BaseApplication;
import cn.iwenddg.androidexample.basemvvm.model.BaseModel;
import cn.iwenddg.androidexample.basemvvm.vm.BaseVM;
import cn.iwenddg.androidexample.work.activity.NavTabActivity;
import cn.iwenddg.androidexample.work.activity.NavViewPagerActivity;
import cn.iwenddg.androidexample.work.activity.TabLayoutActivity;
import cn.iwenddg.androidexample.work.model.MainViewModel;

/**
 * 主页的ViewModel
 *
 * @author iwen大大怪
 * @create 2021/12/09 15:24
 */
public class MainVM extends BaseVM {
    private static final String TAG = "MainVM";

    public void onToNavLottieClick(View view){
        Log.i(TAG,"onTestClick >>> skip to lottie nav tab <<<");
        Intent intent = new Intent(BaseApplication.getInstance(),NavTabActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getInstance().startActivity(intent);
    }

    public void onToNavViewPagerClick(View view){
        Log.i(TAG,"onTestClick >>> skip to ViewPager nav tab <<<");
        Intent intent = new Intent(BaseApplication.getInstance(), NavViewPagerActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getInstance().startActivity(intent);
    }


    public void onToNavTabLayouClick(View view){
        Log.i(TAG,"onTestClick >>> skip to ViewPager nav tab <<<");
        Intent intent = new Intent(BaseApplication.getInstance(), TabLayoutActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getInstance().startActivity(intent);
    }



}
