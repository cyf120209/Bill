package com.example.aa.user.model;

import com.example.aa.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public interface UserModel {

    List<User> getUser();

    void clearAll();
}
