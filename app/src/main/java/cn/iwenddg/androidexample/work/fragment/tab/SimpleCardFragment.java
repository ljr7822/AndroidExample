package cn.iwenddg.androidexample.work.fragment.tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.iwenddg.androidexample.R;
import cn.iwenddg.androidexample.basemvvm.fragment.BaseDBFragment;

/**
 * TabLayout
 */
public class SimpleCardFragment extends BaseDBFragment {

    private String mTitle;

    private TextView tvTitle;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_simple_card, container, false);
        tvTitle = root.findViewById(R.id.tv_title);
        tvTitle.setText(mTitle);
        return root;
    }
}