package com.example.aa.main.present;

import com.example.aa.base.BasePresenter;
import com.example.aa.entity.Orders;
import com.example.aa.main.model.MainModel;
import com.example.aa.main.model.MainModelImpl;
import com.example.aa.main.view.MainView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    private MainModel mainModel;

    public MainPresenterImpl() {
        mainModel=new MainModelImpl();
    }

    @Override
    public List<Orders> getOrderList() {
        List<Orders> orderList = mainModel.getOrderList();
        return orderList;
    }

    @Override
    public List<String> getUserList(Long orderId) {
        return mainModel.getUserList(orderId);
    }

    @Override
    public void clearAll() {
        mainModel.clearAll();
    }

    @Override
    public void update(Orders orders) {
        mainModel.update(orders);
    }

    @Override
    public void delete(Orders orders) {
        mainModel.delete(orders);
    }
}
