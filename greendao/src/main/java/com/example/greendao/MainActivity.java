package com.example.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.greendao.entity.OrderUser;
import com.example.greendao.entity.Orders;
import com.example.greendao.entity.User;
import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.DaoSession;
import com.example.greendao.gen.OrderUserDao;
import com.example.greendao.gen.OrdersDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbManager dbManager = DbManager.getInstance(this);
        for (int i=0; i<5;i++){
            User user = new User();
            user.setId(null);
            user.setAge(i*2);
            user.setName("第"+i+"人");
            dbManager.insert(user);
        }
        SQLiteDatabase writeableDatabase = dbManager.getWriteableDatabase();
        DaoMaster daoMaster = new DaoMaster(writeableDatabase);
        DaoSession daoSession = daoMaster.newSession();
        OrdersDao ordersDao = daoSession.getOrdersDao();
        ordersDao.insert(new Orders(null,(long)1));
        OrderUserDao orderUserDao = daoSession.getOrderUserDao();
        orderUserDao.insert(new OrderUser(null,(long)1,(long)1 ));
        orderUserDao.insert(new OrderUser(null,(long)1,(long)2 ));
        orderUserDao.insert(new OrderUser(null,(long)2,(long)1 ));
        orderUserDao.insert(new OrderUser(null,(long)2,(long)2 ));
        orderUserDao.insert(new OrderUser(null,(long)2,(long)3 ));

        OrdersDao ordersDao1 = daoSession.getOrdersDao();
        QueryBuilder<Orders> builder = ordersDao1.queryBuilder();
        List<Orders> list = builder.build().list();
        List<User> userList = list.get(0).getUserList();
        int age = userList.get(0).getAge();
        Log.e("cond", "onCreate: "+userList.size() );
        ordersDao1.delete(list.get(0));
        list.remove(0);

    }
}
