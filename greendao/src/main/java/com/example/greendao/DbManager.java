package com.example.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.DaoSession;
import com.example.greendao.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class DbManager {

    private DaoMaster.DevOpenHelper openHelper;
    private final Context context;

    private String dbName="bill";

    private static DbManager mInstance;

    private DbManager(Context context){
        this.context=context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    public static DbManager getInstance(Context context){
        if(mInstance==null){
            synchronized (DbManager.class){
                if(mInstance==null){
                    mInstance=new DbManager(context);
                }
            }
        }
        return mInstance;
    }

    private SQLiteDatabase getReadableDatabase(){
        if(openHelper==null){
            openHelper=new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        return readableDatabase;
    }

    private SQLiteDatabase getWriteableDatabase(){
        if(openHelper==null){
            openHelper=new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        return writableDatabase;
    }

    public void insert(User user){
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insert(user);
    }

    public void delete(User user){
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);
    }

    public List<User> queryUserList(){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();
        return list;
    }

    public List<User> queryUserList(int age){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        List<User> list = qb.list();
        return list;
    }
}
