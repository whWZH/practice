package com.wzh.tuling;

/**
 * Created by Administrator on 2015/8/27.
 */
public class ListData {
    private String data;
    public static final int SEND=1;
    public static final int RECEVER=2;
    public int flag;
    public ListData(String data,int flag){
        setData(data);
        setFlag(flag);
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
