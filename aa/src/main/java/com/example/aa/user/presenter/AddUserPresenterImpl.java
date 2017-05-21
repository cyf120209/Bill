package com.example.aa.user.presenter;

import android.view.View;

import com.example.aa.base.BasePresenter;
import com.example.aa.user.model.AddUserModelImpl;

/**
 * Created by Administrator on 2017/4/16.
 */

public class AddUserPresenterImpl extends BasePresenter<View> implements AddUserPresenter {

    @Override
    public boolean addUser(String userName) {
        return new AddUserModelImpl().addUser(userName);
    }
}
