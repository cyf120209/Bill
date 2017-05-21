package com.example.aa.main.model;

import android.database.Cursor;
import android.util.Log;

import com.example.aa.entity.Orders;
import com.example.aa.gen.DaoSession;
import com.example.aa.gen.OrdersDao;
import com.example.aa.utils.DBManager;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public class MainModelImpl implements MainModel {

    private static final String TAG = MainModelImpl.class.getSimpleName();

    @Override
    public List<Orders> getOrderList() {
        DaoSession rDaoSession = DBManager.getInstance().getRDaoSession();
        OrdersDao ordersDao = rDaoSession.getOrdersDao();
        List<Orders> orderList = ordersDao.queryBuilder()
                .orderDesc(OrdersDao.Properties.Id)
                .list();
        return orderList;
    }

    @Override
    public List<String> getUserList(Long orderId){
        List<String> userList=new ArrayList<>();
        DaoSession rDaoSession = DBManager.getInstance().getRDaoSession();
        Database database = rDaoSession.getDatabase();
        String sql="select * from ORDER_USER as ou,user as u where ou.order_id = "+orderId+" and ou.user_id =u._id";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String user_name = cursor.getString(cursor.getColumnIndex("USER_NAME"));
            userList.add(user_name);
            Log.e(TAG, "getUserList: "+user_name );
        }
        return userList;
    }

    @Override
    public void clearAll() {
        DaoSession wDaoSession = DBManager.getInstance().getWDaoSession();
        wDaoSession.getOrderUserDao().deleteAll();
        wDaoSession.getOrdersDao().deleteAll();
    }
}
