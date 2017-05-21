package com.example.aa.settlement.model;

import android.database.Cursor;
import android.util.Log;

import com.example.aa.entity.Orders;
import com.example.aa.gen.DaoSession;
import com.example.aa.gen.OrdersDao;
import com.example.aa.utils.DBManager;

import org.greenrobot.greendao.database.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */

public class SettlementModelImpl implements SettlementModel{

    private static final String TAG = SettlementModelImpl.class.getSimpleName();
    private DaoSession wDaoSession;

    public Map<String,Double> getData(){
        Map<String,Double> map=new HashMap<>();
        wDaoSession = DBManager.getInstance().getWDaoSession();
        Database database = wDaoSession.getDatabase();
        String sql="select orders._id,total_price,discount,count,restaurant,user_name from ORDERS,ORDER_USER,USER where (ORDERS._id =ORDER_USER.ORDER_ID and ORDER_USER.[USER_ID]=USER.[_id])";
//        String sql="select * from orders";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String user_name = cursor.getString(cursor.getColumnIndex("USER_NAME"));
            double total_price = cursor.getDouble(cursor.getColumnIndex("TOTAL_PRICE"));
            double discount = cursor.getDouble(cursor.getColumnIndex("DISCOUNT"));
            int count = cursor.getInt(cursor.getColumnIndex("COUNT"));
            double value = total_price*discount / (count*1.0);
            Double aDouble = map.get(user_name);
            if(aDouble!=null && aDouble!=0){
                map.put(user_name,value+aDouble);
            }else {
                map.put(user_name, value);
            }
            Log.e(TAG, "getData: "+user_name+"---"+map.get(user_name) );

        }
        return map;
    }

    @Override
    public List<Orders> getOrderList() {
        DaoSession rDaoSession = DBManager.getInstance().getRDaoSession();
        OrdersDao ordersDao = rDaoSession.getOrdersDao();
        List<Orders> orderList = ordersDao.queryBuilder()
                .orderDesc(OrdersDao.Properties.Id)
                .list();
        return orderList;
    }
}
