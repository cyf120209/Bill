package com.example.aa.bill.model;

import com.example.aa.entity.Orders;
import com.example.aa.entity.OrderUser;
import com.example.aa.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public interface BillModel {

    List<User> getUser();

    List<User> getUser(Long id);

    Long saveOrder(Orders order);

    Boolean saveDetails(List<OrderUser> orderUserList);

    void deleteDetails(Long orderId);
}
