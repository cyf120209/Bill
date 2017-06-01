package com.example.aa.bill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aa.R;
import com.example.aa.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/16.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder>{

    private final Context context;
    private List<User> userList;
    private List<Long> users=new ArrayList<>();

    public BillAdapter(Context context,List<User> userList) {
        this.context=context;
        this.userList = userList;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tv_userName.setText(user.getUserName());
        if(users.contains(user.getId())){
            holder.ck_bill_user.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView tv_userName;
        private final EditText et_count;
        private final CheckBox ck_bill_user;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_userName = (TextView)itemView.findViewById(R.id.tv_userName);
            et_count = (EditText)itemView.findViewById(R.id.et_count);
            ck_bill_user = (CheckBox)itemView.findViewById(R.id.ck_bill_user);
            ck_bill_user.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            User user = userList.get(position);
            if (!users.contains(user.getId())) {
                users.add(user.getId());
            }else {
                users.remove(user.getId());
            }
        }
    }

}
