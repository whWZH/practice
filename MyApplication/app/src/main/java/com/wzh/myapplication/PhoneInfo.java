package com.wzh.myapplication;

/**
 * Created by Administrator on 2015/8/27.
 */
public class PhoneInfo {
    private String Phnum;
    private String Phname;

    public PhoneInfo(String num,String name){
        this.Phname=name;
        this.Phnum=num;
    }
    public void setPhname(String phname) {
        Phname = phname;
    }

    public String getPhname() {
        return Phname;
    }

    public void setPhnum(String phnum) {
        Phnum = phnum;
    }

    public String getPhnum() {
        return Phnum;
    }
}
