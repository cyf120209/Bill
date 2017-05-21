package com.example.aa.settlement.presenter;

import com.example.aa.base.BasePresenter;
import com.example.aa.entity.Orders;
import com.example.aa.settlement.model.SettlementModelImpl;
import com.example.aa.settlement.view.SettlementView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */

public class SettlementPresenterImpl extends BasePresenter<SettlementView> implements SettlementPresenter{

    private final SettlementModelImpl mSettlementModel;

    public SettlementPresenterImpl() {
        mSettlementModel = new SettlementModelImpl();

    }

    @Override
    public List<Orders> getOrderList() {
        return mSettlementModel.getOrderList();
    }

    @Override
    public Map<String,Double> getData() {
        return mSettlementModel.getData();
    }
}
