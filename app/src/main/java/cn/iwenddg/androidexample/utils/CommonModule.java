package cn.iwenddg.androidexample.utils;

import android.content.Context;

/**
 * @author iwen大大怪
 * @create 2021/11/05 17:46
 */
public class CommonModule {
    public static String TAG = "CommonModule";

    public static AppLifeCycle gAppLifeCycle;


    public static void init(Context context) {
        gAppLifeCycle = new AppLifeCycle();
    }
}
