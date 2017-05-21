package com.example.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.greendao.Test;
import com.example.greendao.User;

import com.example.greendao.gen.TestDao;
import com.example.greendao.gen.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig testDaoConfig;
    private final DaoConfig userDaoConfig;

    private final TestDao testDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        testDaoConfig = daoConfigMap.get(TestDao.class).clone();
        testDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        testDao = new TestDao(testDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Test.class, testDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        testDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
    }

    public TestDao getTestDao() {
        return testDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
