package com.example.wang.learncontext;

import android.app.Application;

/**
 * Created by wang on 2015/8/12.
 */
public class App extends Application {
    private String textData="default";

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getTextData() {
        return textData;
    }
}
