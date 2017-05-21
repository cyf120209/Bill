package com.example.aa.bill.presenter;

import com.example.aa.entity.Orders;
import com.example.aa.entity.OrderUser;
import com.example.aa.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public interface BillPresenter {

    List<User> getUser();

    Long saveOrder(Orders order);

    Boolean saveDetails(List<OrderUser> orderUserList);
}
