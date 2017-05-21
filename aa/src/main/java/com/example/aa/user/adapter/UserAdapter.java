package com.example.aa.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aa.R;
import com.example.aa.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);
        if(user.getIsExist()) {
            holder.tv_userName.setText(user.getUserName());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_userName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_userName = (TextView)itemView.findViewById(R.id.tv_userName);
        }
    }
}
