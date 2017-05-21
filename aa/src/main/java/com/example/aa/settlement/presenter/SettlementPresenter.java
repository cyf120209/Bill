package com.example.aa.settlement.presenter;

import com.example.aa.entity.Orders;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */

public interface SettlementPresenter {

    List<Orders> getOrderList();

    Map<String,Double> getData();
}
