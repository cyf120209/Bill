package com.example.aa.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/9.
 */

@Entity
public class Orders {

    @Id(autoincrement = true)
    private Long id;

    private double totalPrice;

    private double discount;

    private int count;

    private String restaurant;

    private Long orderTime;

    public Long getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public String getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1851619910)
    public Orders(Long id, double totalPrice, double discount, int count,
            String restaurant, Long orderTime) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.count = count;
        this.restaurant = restaurant;
        this.orderTime = orderTime;
    }

    @Generated(hash = 1753857294)
    public Orders() {
    }


}
