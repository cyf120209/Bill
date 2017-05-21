package com.example.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbManager dbManager = DbManager.getInstance(this);
        for (int i=0; i<5;i++){
            User user = new User();
            user.setId((long) i);
            user.setAge(i*2);
            user.setName("第"+i+"人");
            dbManager.insert(user);
        }
        List<User> users = dbManager.queryUserList();
        for (User user : users) {
            Log.e("TAG", "queryUserList--before-->" + user.getId() + "--" + user.getName() +"--"+user.getAge());
            if (user.getId() == 0) {
                dbManager.delete(user);
            }
        }
        users = dbManager.queryUserList();
        for (User user : users) {
            Log.e("TAG", "queryUserList--after--->" + user.getId() + "---" + user.getName()+"--"+user.getAge());
        }
    }
}
