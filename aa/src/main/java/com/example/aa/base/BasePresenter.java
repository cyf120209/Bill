package com.example.aa.base;

/**
 * Created by Administrator on 2017/4/16.
 */

public class BasePresenter<V> {

    private V mView;

    public void attach(V v){
        this.mView=v;
    }

    public void dettach(){
        this.mView=null;
    }
}
