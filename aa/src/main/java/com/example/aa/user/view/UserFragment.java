package com.example.aa.user.view;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.aa.MainActivity;
import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.entity.User;
import com.example.aa.user.adapter.UserAdapter;
import com.example.aa.user.presenter.UserPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment<UserView,UserPresenterImpl> implements View.OnClickListener{

    private String TAG=UserFragment.class.getSimpleName();
    private List<User> userList=new ArrayList<>();
    private RecyclerView rcl_user;
    private UserAdapter userAdapter;
    private MainActivity activity;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_user;
    }

    @Override
    public UserPresenterImpl initPresenter() {
        return new UserPresenterImpl();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
        activity = (MainActivity)getActivity();
        initData();
        initView();
    }

    private void initData() {
        List<User> user = mPresenter.getUser();
        userList.clear();
        userList.addAll(user);
    }

    private void initView() {
        view.findViewById(R.id.btn_add_user).setOnClickListener(this);
        view.findViewById(R.id.btn_clear_user_all).setOnClickListener(this);
        rcl_user = (RecyclerView) view.findViewById(R.id.rcl_user);
        rcl_user.setLayoutManager(new LinearLayoutManager(getContext()));
        userAdapter = new UserAdapter(getContext(), userList);
        rcl_user.setAdapter(userAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_user:
                addUser();
                break;
            case R.id.btn_clear_user_all:
                clearAll();
                break;
        }
    }

    private void clearAll() {
        mPresenter.clearAll();
        userList.clear();
        userAdapter.notifyDataSetChanged();
    }

    private void addUser() {
        replaceFragment(R.id.content_user,new AddUserFragment(),true);
    }
}
