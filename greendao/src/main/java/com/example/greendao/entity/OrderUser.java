package com.example.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2017/5/27.
 */
@Entity
public class OrderUser {

    @Id
    private Long id;

    private Long orderId;

    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 466232977)
    public OrderUser(Long id, Long orderId, Long userId) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
    }

    @Generated(hash = 624599725)
    public OrderUser() {
    }
}
