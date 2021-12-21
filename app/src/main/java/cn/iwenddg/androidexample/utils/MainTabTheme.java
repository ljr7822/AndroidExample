package cn.iwenddg.androidexample.utils;

import android.graphics.drawable.Drawable;

/**
 * @author iwen大大怪
 * @create 2021/11/05 15:46
 */
public class MainTabTheme {

    public TabConfig tab1;
    public TabConfig tab2;
    public TabConfig tab3;
    public TabConfig tab4;
    public TabConfig tab5;

    public static class TabConfig {
        public String label;
        public Drawable icon;
        public Drawable activeIcon;
        public String activeAnimJson;
        public String extraAnimJson;
        public int extraAnimWidth;
        public int extraAnimHeight;
        public String color;
        public String activeColor;
    }
}

