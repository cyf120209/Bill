package com.example.aa.main.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aa.R;
import com.example.aa.entity.Orders;
import com.example.aa.main.view.MainFragment;
import com.example.aa.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{

    private Context context;
    private List<Orders> orderList=new ArrayList<>();
//    List<String> userList=new ArrayList<>();
    private OnLoadUserClickListener loadUserClickListener;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setOnLoadUserClickListener(OnLoadUserClickListener loadUserClickListener) {
        this.loadUserClickListener = loadUserClickListener;
    }

    public OrderAdapter(Context context, List<Orders> orderList) {
        this.context=context;
        this.orderList = orderList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Orders order = orderList.get(position);
        holder.tv_restaurant.setText(order.getRestaurant());
        holder.tv_total_price.setText(""+order.getTotalPrice());
        holder.tv_discount.setText(""+order.getDiscount());
        holder.tv_count.setText(""+order.getCount());
        holder.tv_order_time.setText(DateUtils.getYMD(order.getOrderTime()));
        holder.tv_avg.setText(String.format("%.02f",order.getTotalPrice()/(order.getCount()*1.0)));
        if(loadUserClickListener==null){
            return;
        }
        List<String> userList = loadUserClickListener.loadUser(order.getId());
        OrderUserAdapter orderUserAdapter = new OrderUserAdapter();
        orderUserAdapter.setUser(userList);
        holder.rcl_bill_user.setAdapter(orderUserAdapter);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        private final TextView tv_restaurant;
        private final TextView tv_total_price;
        private final TextView tv_discount;
        private final TextView tv_count;
        private final TextView tv_order_time;
        private final TextView tv_avg;
        private final RecyclerView rcl_bill_user;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_restaurant=(TextView)itemView.findViewById(R.id.tv_restaurant);
            tv_total_price=(TextView)itemView.findViewById(R.id.tv_total_price);
            tv_discount=(TextView)itemView.findViewById(R.id.tv_discount);
            tv_count=(TextView)itemView.findViewById(R.id.tv_count);
            tv_order_time=(TextView)itemView.findViewById(R.id.tv_order_time);
            tv_avg=(TextView)itemView.findViewById(R.id.tv_avg);
            rcl_bill_user=(RecyclerView)itemView.findViewById(R.id.rcl_bill_user);
            LinearLayoutManager layoutManager = new LinearLayoutManager(OrderAdapter.this.context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rcl_bill_user.setLayoutManager(layoutManager);
            itemView.setLongClickable(true);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            position=getAdapterPosition();
            return false;
        }
    }

    public interface OnLoadUserClickListener{
        List<String> loadUser(Long orderId);
    }
}
