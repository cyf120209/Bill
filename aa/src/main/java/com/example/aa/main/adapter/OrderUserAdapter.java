package com.example.aa.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aa.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class OrderUserAdapter extends RecyclerView.Adapter<OrderUserAdapter.MyViewHolder>{

    List<String> userList=new ArrayList<>();

    public void setUser(List<String> user) {
        this.userList = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_userName.setText(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_userName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_userName=(TextView)itemView.findViewById(R.id.tv_userName);
        }
    }
}
