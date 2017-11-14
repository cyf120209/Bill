package com.uangel.suishouji.base;

/**
 * Created by padmate on 2017/11/10.
 */

public class BasePresenter<V> {

    private V mView;

    public void attach(V view){
        this.mView=view;
    }

    public void detach(){
        this.mView=null;
    }
}
