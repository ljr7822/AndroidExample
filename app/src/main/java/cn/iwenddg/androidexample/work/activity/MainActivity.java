package cn.iwenddg.androidexample.work.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import cn.iwenddg.androidexample.R;
import cn.iwenddg.androidexample.basemvvm.activity.BaseBDActivity;
import cn.iwenddg.androidexample.databinding.ActivityMainBinding;
import cn.iwenddg.androidexample.work.vm.MainVM;


public class MainActivity extends BaseBDActivity<ActivityMainBinding, MainVM> {

    private static final String TAG = "MainActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}