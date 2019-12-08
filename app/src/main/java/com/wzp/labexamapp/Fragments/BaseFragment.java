package com.wzp.labexamapp.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    private View rootview=null;
    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
       rootview=getSubView(inflater,container);
       setHasOptionsMenu(true);
       setSubListenter();
        return rootview;
    }

    protected abstract void setSubListenter();
    protected abstract View getSubView(LayoutInflater inflater, ViewGroup container);

}