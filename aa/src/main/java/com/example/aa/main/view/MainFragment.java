package com.example.aa.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.aa.R;
import com.example.aa.base.BaseFragment;
import com.example.aa.bill.view.BillFragment;
import com.example.aa.entity.Orders;
import com.example.aa.main.adapter.OrderAdapter;
import com.example.aa.main.present.MainPresenter;
import com.example.aa.main.present.MainPresenterImpl;
import com.example.aa.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MainFragment extends BaseFragment<MainView,MainPresenterImpl> implements MainView,
        View.OnClickListener,OrderAdapter.OnLoadUserClickListener{

    private final String TAG=MainFragment.class.getSimpleName();
    private RecyclerView rcl_bill;

    List<Orders> mOrderList=new ArrayList<>();
    private OrderAdapter orderAdapter;
    private Subscription subscribe;

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
//        setHasOptionsMenu(true);
        rcl_bill.setOnCreateContextMenuListener(this);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1,0,1,"修改");
        menu.add(1, 1, 1, "删除");
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.add(2,2,2,"dddd");
//    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = orderAdapter.getPosition();
        Log.e(TAG, "onContextItemSelected: "+position);
        switch (item.getItemId()){
            case 0:
                edit(position);
                break;
            case 1:
                delete(position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void edit(int position) {
        Orders orders = mOrderList.get(position);
        RxBus.getInstance().postSticky(orders);
        replaceFragment(R.id.content_main,new BillFragment(),true);
        update();
    }

    private void update() {
        subscribe = RxBus.getInstance().toObservable(Orders.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Orders>() {
                    @Override
                    public void call(Orders orders) {
                        mPresenter.update(orders);
                        orderAdapter.notifyItemChanged(orderAdapter.getPosition());
                    }
                });

    }

    private void delete(int position) {
        mPresenter.delete(mOrderList.get(position));
        mOrderList.remove(position);
        orderAdapter.notifyItemRemoved(orderAdapter.getPosition());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(subscribe!=null && !subscribe.isUnsubscribed()){
            subscribe.unsubscribe();
        }
    }
}
