package com.example.wang.crime;

import java.util.UUID;

/**
 * Created by wang on 2015/9/5.
 */
public class Item {
    private UUID mId;
    private String mTitle;
    public Item(){
        mId=UUID.randomUUID();
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
}
