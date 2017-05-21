package com.example.aa.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.aa.gen.DaoMaster;
import com.example.aa.gen.DaoSession;

/**
 * Created by Administrator on 2017/4/16.
 */

public class DBManager {

    private static DBManager instance;
    private final DaoSession wdDaoSession;
    private final DaoSession rdDaoSession;

    private DBManager(Context context){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, "bill", null);
        SQLiteDatabase wd = openHelper.getWritableDatabase();
        DaoMaster wdDaoMaster = new DaoMaster(wd);
        wdDaoSession = wdDaoMaster.newSession();
        SQLiteDatabase rd = openHelper.getReadableDatabase();
        DaoMaster rdDaoMaster = new DaoMaster(rd);
        rdDaoSession = rdDaoMaster.newSession();
    }

    public static DBManager getInstance(){
        return instance;
    }

    public static DBManager getInstance(Context context){
        if(instance==null){
            synchronized (DBManager.class){
                if(instance==null){
                    instance=new DBManager(context);
                }
            }
        }
        return instance;
    }

    public DaoSession getWDaoSession(){
        return wdDaoSession;
    }

    public DaoSession getRDaoSession(){
        return rdDaoSession;
    }
}
