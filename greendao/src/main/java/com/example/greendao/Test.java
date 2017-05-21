package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/9.
 */
@Entity
public class Test {
    @Id
    private long id;

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Generated(hash = 986923955)
    public Test(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 372557997)
    public Test() {
    }
    

}
