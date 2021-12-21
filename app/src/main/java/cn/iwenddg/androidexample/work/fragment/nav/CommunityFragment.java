package cn.iwenddg.androidexample.work.fragment.nav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.iwenddg.androidexample.R;
import cn.iwenddg.androidexample.basemvvm.fragment.BaseDBFragment;

/**
 * 社区fragment
 */
public class CommunityFragment extends BaseDBFragment {

    public CommunityFragment() {
        // Required empty public constructor
    }


    public static CommunityFragment newInstance(String param1, String param2) {
        return new CommunityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }
}