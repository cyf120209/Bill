package com.example.aa.main.present;

import com.example.aa.entity.Orders;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public interface MainPresenter {

    List<Orders> getOrderList();

    List<String> getUserList(Long orderId);

    void clearAll();

    void update(Orders orders);

    void delete(Orders orders);
}
