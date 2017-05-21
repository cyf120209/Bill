package com.example.aa.settlement.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.entity.Orders;
import com.example.aa.settlement.adapter.UserBillAdapter;
import com.example.aa.settlement.model.SettlementModelImpl;
import com.example.aa.settlement.presenter.SettlementPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettlementFragment extends BaseFragment<SettlementView,SettlementPresenterImpl> {


    private RecyclerView rcl_month_bill;
    private RecyclerView rcl_day_bill;
    private TextView tv_total_price;
    private TextView tv_discount_total_price;

    private double totalPrice=0;
    private double disTotalPrice=0;


    Map<String, Double> data=new HashMap<>();
    List<Orders> orderList=new ArrayList<>();
    private UserBillAdapter userBillAdapter;

    public SettlementFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_settlement;
    }

    @Override
    public SettlementPresenterImpl initPresenter() {
        return new SettlementPresenterImpl();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        orderList = mPresenter.getOrderList();
        for (Orders orders:orderList){
            totalPrice+=orders.getTotalPrice();
            double v = orders.getTotalPrice() * orders.getDiscount();
            disTotalPrice+=v;
        }
        tv_total_price.setText(String.format("%.2f",totalPrice));
        tv_discount_total_price.setText(String.format("%.2f",disTotalPrice));
        data = mPresenter.getData();
        userBillAdapter.setUserBillMap(data);
        userBillAdapter.notifyDataSetChanged();

    }

    private void initView() {
        tv_total_price=(TextView)view.findViewById(R.id.tv_total_price);
        tv_discount_total_price=(TextView)view.findViewById(R.id.tv_discount_total_price);
        rcl_month_bill = (RecyclerView) view.findViewById(R.id.rcl_month_bill);
        userBillAdapter = new UserBillAdapter(data);
        rcl_month_bill.setLayoutManager(new LinearLayoutManager(getContext()));
        rcl_month_bill.setAdapter(userBillAdapter);
        rcl_day_bill = (RecyclerView) view.findViewById(R.id.rcl_day_bill);
    }
}
