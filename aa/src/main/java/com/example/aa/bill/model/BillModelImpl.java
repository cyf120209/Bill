package com.example.aa.bill.model;

import com.example.aa.entity.Orders;
import com.example.aa.entity.OrderUser;
import com.example.aa.entity.User;
import com.example.aa.gen.DaoSession;
import com.example.aa.gen.OrderUserDao;
import com.example.aa.gen.UserDao;
import com.example.aa.utils.DBManager;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public class BillModelImpl implements BillModel {

    private DaoSession wDaoSession;

    @Override
    public List<User> getUser() {
        DaoSession daoSession = DBManager.getInstance().getRDaoSession();
        List<User> userList = daoSession.getUserDao()
                .queryBuilder()
                .where(UserDao.Properties.IsExist.eq(true))
                .list();
        return userList;
    }

    @Override
    public Long saveOrder(Orders order) {
        wDaoSession = DBManager.getInstance().getWDaoSession();
        long orderId = wDaoSession.getOrdersDao().insert(order);
        return orderId;
    }

    @Override
    public Boolean saveDetails(List<OrderUser> orderUserList) {
        OrderUserDao orderUserDao = wDaoSession.getOrderUserDao();
        orderUserDao.insertInTx(orderUserList);
        return true;
    }
}