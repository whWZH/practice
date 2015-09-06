package com.example.wang.crime;

import android.provider.ContactsContract;

import java.util.Date;
import java.util.UUID;

/**
 * Created by wang on 2015/9/5.
 */
public class Item {
    private UUID mId;
    private String mTitle;
    private Date mData;
    private boolean mSolved;
    public Item(){
        mId=UUID.randomUUID();
        mData=new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmData(Date mData) {
        this.mData = mData;
    }

    public Date getmData() {
        return mData;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
    public boolean getmSolved(){
        return mSolved;
    }
}
