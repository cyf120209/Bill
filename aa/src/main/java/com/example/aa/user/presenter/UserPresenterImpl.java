package com.example.aa.user.presenter;

import com.example.aa.base.BasePresenter;
import com.example.aa.entity.User;
import com.example.aa.user.model.UserModelImpl;
import com.example.aa.user.view.UserView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public class UserPresenterImpl extends BasePresenter<UserView> implements UserPresenter {
    @Override
    public List<User> getUser() {
        return new UserModelImpl().getUser();
    }

    @Override
    public void clearAll() {
        new UserModelImpl().clearAll();
    }
}
