package com.example.aa.user.presenter;

import com.example.aa.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public interface UserPresenter {

    List<User> getUser();

    void clearAll();
}
