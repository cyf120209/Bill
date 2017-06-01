package com.example.aa.bill.presenter;

import com.example.aa.base.BasePresenter;
import com.example.aa.bill.model.BillModel;
import com.example.aa.bill.model.BillModelImpl;
import com.example.aa.bill.view.BillView;
import com.example.aa.entity.Orders;
import com.example.aa.entity.OrderUser;
import com.example.aa.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public class BillPresenterImpl extends BasePresenter<BillView> implements BillPresenter{

    private BillModel billModel;

    public BillPresenterImpl() {
        this.billModel = new BillModelImpl();
    }

    @Override
    public List<User> getUser() {
        return billModel.getUser();
    }

    @Override
    public List<User> getUser(Long id) {
        return null;
    }

    @Override
    public Long saveOrder(Orders order) {
        return billModel.saveOrder(order);
    }

    @Override
    public Boolean saveDetails(List<OrderUser> orderUserList) {
        return billModel.saveDetails(orderUserList);
    }

    @Override
    public void deleteDetails(Long orderId) {
        billModel.deleteDetails(orderId);
    }
}
