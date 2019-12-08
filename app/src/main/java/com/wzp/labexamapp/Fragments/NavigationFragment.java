package com.wzp.labexamapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzp.labexamapp.R;

public class NavigationFragment extends BaseFragment{
    private View rootView;
    @Override
    protected void setSubListenter() {

    }

    @Override
    protected View getSubView(LayoutInflater inflater, ViewGroup container) {
        rootView=inflater.inflate(R.layout.fragment_navigation,container,false);
        initView();
        return rootView;
    }

    private void initView() {
    }
}
