package com.example.aa.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.bill.view.BillFragment;
import com.example.aa.entity.Orders;
import com.example.aa.main.adapter.OrderAdapter;
import com.example.aa.main.present.MainPresenter;
import com.example.aa.main.present.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MainFragment extends BaseFragment<MainView,MainPresenterImpl> implements MainView,
        View.OnClickListener,OrderAdapter.OnLoadUserClickListener{

    private RecyclerView rcl_bill;

    List<Orders> mOrderList=new ArrayList<>();
    private OrderAdapter orderAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    public MainPresenterImpl initPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        List<Orders> orderList = mPresenter.getOrderList();
        mOrderList.clear();
        if(orderList!=null){
            mOrderList.addAll(orderList);
            orderAdapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        rcl_bill = (RecyclerView)view.findViewById(R.id.rcl_bill);
        rcl_bill.setLayoutManager(new LinearLayoutManager(getContext()));
        orderAdapter = new OrderAdapter(getContext(), mOrderList);
        orderAdapter.setOnLoadUserClickListener(this);
        rcl_bill.setAdapter(orderAdapter);
        view.findViewById(R.id.btn_add_bill).setOnClickListener(this);
        view.findViewById(R.id.btn_clear_all).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_bill:
                addBill();
                break;
            case R.id.btn_clear_all:
                clearAll();
                break;
        }
    }

    private void clearAll() {
        mPresenter.clearAll();
    }

    @Override
    public void addBill() {
        replaceFragment(R.id.content_main,new BillFragment(),true);
    }

    public MainPresenter getPresenter(){
        return mPresenter;
    }

    @Override
    public List<String> loadUser(Long orderId) {
        return mPresenter.getUserList(orderId);
    }
}
