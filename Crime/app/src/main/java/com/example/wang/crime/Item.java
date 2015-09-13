package com.example.wang.crime;

import android.provider.ContactsContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.PhantomReference;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.UUID;

/**
 * Created by wang on 2015/9/5.
 */
public class Item {
    private static final String JSON_ID="id";
    private static final String JSON_TITLE="title";
    private static final String JSON_SOLVED="solved";
    private static final String JSON_DATE="date";
    private static final String JSON_PHOTO="photo";
    private static final String JSON_SUSPECT="suspect";
    private static final String JSON_NUMBER="number";
    private UUID mId;
    private String mSuspect;
    private String mTitle;
    private Date mData=new Date();
    private boolean mSolved;
    private Photo mPhoto;
    private String mNumber;

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmNumber() {
        return mNumber;
    }

    public Item(){
        mId=UUID.randomUUID();
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json=new JSONObject();
        json.put(JSON_ID,mId.toString());
        json.put(JSON_TITLE,mTitle);
        json.put(JSON_DATE,mData.getTime());
        json.put(JSON_SOLVED,mSolved);
        if (mPhoto!=null){
            json.put(JSON_PHOTO,mPhoto.toJSON());
        }
        if (mNumber!=null){
            json.put(JSON_NUMBER,mNumber);
        }
        json.put(JSON_SUSPECT,mSuspect);
        return json;
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
    public Item(JSONObject json) throws JSONException{
        mId=UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_TITLE)){
            mTitle=json.getString(JSON_TITLE);
        }
        mSolved=json.getBoolean(JSON_SOLVED);
        mData=new Date(json.getLong(JSON_DATE));
        if (json.has(JSON_PHOTO)){
            mPhoto=new Photo(json.getJSONObject(JSON_PHOTO));
        }
        if (json.has(JSON_SUSPECT)){
            mSuspect=json.getString(JSON_SUSPECT);
        }
        if (json.has(JSON_NUMBER)){
            mNumber=json.getString(JSON_NUMBER);
        }
    }
    public Photo getmPhoto(){
        return mPhoto;
    }
    public void setPhoto(Photo p){
        mPhoto=p;
    }
    public String getSuspect(){
        return mSuspect;
    }

    public void setmSuspect(String mSuspect) {
        this.mSuspect = mSuspect;
    }
}
