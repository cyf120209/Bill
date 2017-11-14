package com.uangel.suishouji.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by padmate on 2017/11/10.
 */

public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {

    protected T mPresenter;

    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getContentViewId(), null);
        mPresenter = initPresenter();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.attach((V) view);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.detach();
    }

    /**
     * 获取到ContentView的id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化pressenter
     * @return
     */
    public abstract T initPresenter();
}
