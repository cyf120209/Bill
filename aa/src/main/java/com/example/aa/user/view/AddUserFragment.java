package com.example.aa.user.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.user.presenter.AddUserPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends BaseFragment<View,AddUserPresenterImpl> implements View.OnClickListener{

    private EditText et_userName;

    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_add_user;
    }

    @Override
    public AddUserPresenterImpl initPresenter() {
        return new AddUserPresenterImpl();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        et_userName = (EditText)view.findViewById(R.id.et_userName);
        view.findViewById(R.id.btn_finish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finish:
                addUser();
                break;
        }
    }

    private void addUser() {
        String userName = et_userName.getText().toString();
        boolean addUser = mPresenter.addUser(userName);
        if(addUser){
            popFragment(true);
        }else {
            Toast.makeText(getContext(),"添加失败",Toast.LENGTH_SHORT).show();
        }
    }
}
