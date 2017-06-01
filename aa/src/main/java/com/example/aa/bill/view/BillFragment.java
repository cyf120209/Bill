package com.example.aa.bill.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.bill.adapter.BillAdapter;
import com.example.aa.bill.presenter.BillPresenterImpl;
import com.example.aa.entity.Orders;
import com.example.aa.entity.OrderUser;
import com.example.aa.entity.User;
import com.example.aa.utils.RxBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillFragment extends BaseFragment<BillView,BillPresenterImpl> implements View.OnClickListener {


    private RecyclerView rcl_bill_user;
    private EditText et_total_money;
    private EditText et_discount;

    private List<User> mUserList=new ArrayList<>();
    private Map<Long,User> mUserMap=new HashMap<>();
    private BillAdapter billAdapter;
    private Subscription subscribe;
    private Orders mOrders;
    private int tag=-1;

    public BillFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bill;
    }

    @Override
    public BillPresenterImpl initPresenter() {
        return new BillPresenterImpl();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        List<User> userList = mPresenter.getUser();
        if(userList==null){
            return;
        }
        mUserList.addAll(userList);
        for (User user:mUserList){
            mUserMap.put(user.getId(),user);
        }
        billAdapter.notifyDataSetChanged();
        subscribe = RxBus.getInstance().toObservableSticky(Orders.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Orders>() {
                    @Override
                    public void call(Orders orders) {
                        tag=1;
                        mOrders=orders;
                        Long id = orders.getId();
                        double totalPrice = orders.getTotalPrice();
                        double discount = orders.getDiscount();
                        et_total_money.setText("" + totalPrice);
                        et_discount.setText("" + discount);
                        List<User> list = mOrders.getUserList();
                        List<Long> users=new ArrayList<>();
                        for (User user:list){
                            users.add(user.getId());
                        }
                        billAdapter.setUsers(users);
                        billAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void initView() {
        et_total_money = (EditText)view.findViewById(R.id.et_total_money);
        et_discount = (EditText)view.findViewById(R.id.et_discount);
        rcl_bill_user = (RecyclerView)view.findViewById(R.id.rcl_bill_user);
        billAdapter = new BillAdapter(getActivity(), mUserList);
        rcl_bill_user.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcl_bill_user.setAdapter(billAdapter);
        view.findViewById(R.id.btn_bill_finish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bill_finish:
                finish();
                break;
        }
    }

    private void finish() {
        List<Long> users = billAdapter.getUsers();
        if(tag==-1){
            Orders order = new Orders();
            order.setId(null);
            mOrders=order;
        }
        mOrders.setRestaurant("曾掌柜");
        mOrders.setTotalPrice(Double.valueOf(et_total_money.getText().toString()));
        mOrders.setDiscount(Double.valueOf(et_discount.getText().toString()));
        mOrders.setCount(users.size());
        mOrders.setOrderTime(System.currentTimeMillis());
        Long orderId = mPresenter.saveOrder(mOrders);
        mPresenter.deleteDetails(orderId);
        mOrders.resetUserList();
        List<OrderUser> orderUserList=new ArrayList<>();
        for (Long userId :users){
            orderUserList.add(new OrderUser(null,orderId,userId));
        }
        Boolean aBoolean = mPresenter.saveDetails(orderUserList);
        if(aBoolean){
            Toast.makeText(getActivity(),"保存成功",Toast.LENGTH_SHORT).show();
            popFragment(true);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        subscribe.unsubscribe();
        RxBus.getInstance().removeStickyEvent(Orders.class);
    }
}
