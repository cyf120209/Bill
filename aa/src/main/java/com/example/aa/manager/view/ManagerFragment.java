package com.example.aa.manager.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.manager.presenter.ManagerPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerFragment extends BaseFragment<ManagerView,ManagerPresenterImpl> {


    public ManagerFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_manager;
    }

    @Override
    public ManagerPresenterImpl initPresenter() {
        return new ManagerPresenterImpl();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {

    }
}
