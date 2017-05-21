package com.example.aa.settlement.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aa.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/5/17.
 */

public class UserBillAdapter extends RecyclerView.Adapter<UserBillAdapter.UserBillViewHolder>{

    private Map<String,Double> userBillMap=new HashMap<>();
    private List<String> userList=new ArrayList<>();

    public UserBillAdapter(Map<String, Double> userBillMap) {
        this.userBillMap = userBillMap;
        initUser(userBillMap);
    }

    public void setUserBillMap(Map<String, Double> userBillMap) {
        this.userBillMap = userBillMap;
        initUser(userBillMap);
    }

    private void initUser(Map<String, Double> userBillMap) {
        userList.clear();
        Iterator<Map.Entry<String, Double>> iterator = userBillMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Double> entry = iterator.next();
            userList.add(entry.getKey());
        }
    }

    @Override
    public UserBillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_bill, parent, false);
        return new UserBillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserBillViewHolder holder, int position) {
        String userName = userList.get(position);
        Double userBill = userBillMap.get(userName);
        holder.tv_userName.setText(userName);
        holder.tv_userBill.setText(String.format("%.2f",userBill));
    }

    @Override
    public int getItemCount() {
        return userBillMap.size();
    }

    class UserBillViewHolder extends RecyclerView.ViewHolder{

        TextView tv_userName;
        TextView tv_userBill;
        public UserBillViewHolder(View itemView) {
            super(itemView);
            tv_userName=(TextView)itemView.findViewById(R.id.tv_userName);
            tv_userBill=(TextView)itemView.findViewById(R.id.tv_userBill);
        }
    }
}
