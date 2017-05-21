package com.example.aa.user.model;

import com.example.aa.entity.User;
import com.example.aa.gen.DaoSession;
import com.example.aa.utils.DBManager;

/**
 * Created by Administrator on 2017/4/16.
 */

public class AddUserModelImpl implements AddUserModel {

    @Override
    public boolean addUser(String userName) {
        User user = new User();
        user.setId(null);
        user.setUserName(userName);
        DaoSession daoSession = DBManager.getInstance().getWDaoSession();
        daoSession.insert(user);
        return true;
    }
}
