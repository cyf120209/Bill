package com.example.aa.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.example.aa.gen.DaoSession;
import com.example.aa.gen.OrdersDao;
import com.example.aa.gen.UserDao;

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

    @ToMany
    @JoinEntity(entity = OrderUser.class,
            sourceProperty = "orderId",
    targetProperty = "userId")
    private List<User> userList;

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1517531020)
    public synchronized void resetUserList() {
        userList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1157771689)
    public List<User> getUserList() {
        if (userList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            List<User> userListNew = targetDao._queryOrders_UserList(id);
            synchronized (this) {
                if(userList == null) {
                    userList = userListNew;
                }
            }
        }
        return userList;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 121826766)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrdersDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 1717339351)
    private transient OrdersDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

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
