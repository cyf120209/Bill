package com.example.aa.user.model;

import com.example.aa.entity.User;
import com.example.aa.gen.DaoSession;
import com.example.aa.gen.UserDao;
import com.example.aa.utils.DBManager;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public class UserModelImpl implements UserModel {
    @Override
    public List<User> getUser() {
        DaoSession daoSession = DBManager.getInstance().getRDaoSession();
        List<User> users = daoSession.getUserDao()
                .queryBuilder()
                .where(UserDao.Properties.IsExist.eq(true)).list();
        return users;
    }

    @Override
    public void clearAll() {
        DaoSession daoSession = DBManager.getInstance().getWDaoSession();
        daoSession.getUserDao().deleteAll();
    }
}
