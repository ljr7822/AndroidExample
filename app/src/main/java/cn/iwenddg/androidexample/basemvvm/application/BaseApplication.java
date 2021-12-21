package cn.iwenddg.androidexample.basemvvm.application;

import android.app.Application;
import android.content.Context;

import cn.iwenddg.androidexample.utils.CommonModule;

/**
 * Application全局只有一个，它本身就已经是单例了，无需再用单例模式去为它做多重实例保护了。
 *
 * @author iwen大大怪
 * @create 2021/12/09 15:19
 */
public class BaseApplication extends Application {

    public static BaseApplication mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = BaseApplication.this;
        CommonModule.init(this);
    }

    public static Context getInstance() {
        return mContext;
    }
}
