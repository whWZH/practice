package com.example.wang.crime;

import android.content.Context;

/**
 * Created by Administrator on 2015/9/6.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab=null;
    private Context mAppcontext;
    private CrimeLab(Context context){
        mAppcontext=context;
    }

    public static CrimeLab getsCrimeLab(Context c) {
        if (sCrimeLab==null){
            sCrimeLab=new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }
}
