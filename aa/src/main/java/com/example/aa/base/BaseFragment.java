package com.example.aa.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aa.R;

/**
 * Created by Administrator on 2017/4/9.
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
    public void onResume() {
        super.onResume();
        mPresenter.attach((V) view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.dettach();
    }

    /**
     * 切换fragment
     * @param fragment
     * @param addToBackStack 是否可返回
     */
    public void replaceFragment(int content_main,Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
//        transaction.setCustomAnimations(R.anim.option_translate_in,
//                R.anim.option_translate_out);
        transaction.replace(content_main, fragment);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }

    /**
     * 切换fragment
     * @param fragment
     * @param addToBackStack 是否可返回
     */
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
//        transaction.setCustomAnimations(R.anim.option_translate_in,
//                R.anim.option_translate_out);
        transaction.replace(R.id.content_main, fragment);
        transaction.commit();
        getChildFragmentManager().executePendingTransactions();
    }

    /**
     * 弹出fragment
     * @return
     */
    public boolean popFragment() {
        Log.e("test", "pop fragment: "
                + getChildFragmentManager().getBackStackEntryCount());
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

    /**
     * 弹出fragment
     * @return
     */
    public boolean popFragment(boolean b) {
        Log.e("test", "pop fragment: "
                + getFragmentManager().getBackStackEntryCount());
        boolean isPop = false;
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getFragmentManager().popBackStack();
        }
        return isPop;
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
