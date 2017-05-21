package com.example.aa.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/9.
 */

@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;

    private String userName;

    private boolean isEat;

    private boolean isExist=true;

    public boolean getIsExist() {
        return this.isExist;
    }

    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }

    public boolean getIsEat() {
        return this.isEat;
    }

    public void setIsEat(boolean isEat) {
        this.isEat = isEat;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1871143142)
    public User(Long id, String userName, boolean isEat, boolean isExist) {
        this.id = id;
        this.userName = userName;
        this.isEat = isEat;
        this.isExist = isExist;
    }

    @Generated(hash = 586692638)
    public User() {
    }

}
