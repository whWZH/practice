package com.example.wang.crime;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/9/12.
 */
public class Photo {
    private static final String JSON_FILENAME="filename";
    private String mFilename;

    public Photo(String Filename){
        mFilename=Filename;
    }
    public Photo(JSONObject json)throws JSONException{
        mFilename=json.getString(JSON_FILENAME);
    }
    public JSONObject toJSON() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_FILENAME,mFilename);
        return jsonObject;
    }
    public String getFilename(){
        return mFilename;
    }

}
