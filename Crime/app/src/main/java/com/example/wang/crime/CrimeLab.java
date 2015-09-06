package com.example.wang.crime;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2015/9/6.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab=null;
    private Context mAppcontext;
    private ArrayList<Item> mItems;
    private CrimeLab(Context context){
        mItems=new ArrayList<Item>();
        mAppcontext=context;
        for (int i=0;i<100;i++){
            Item c=new Item();
            c.setmTitle("Crime #" +i);
            c.setmSolved(i%2==0);
            mItems.add(c);
        }
    }


    public static CrimeLab getsCrimeLab(Context c) {
        if (sCrimeLab==null){
            sCrimeLab=new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Item> getmItems() {
        return mItems;
    }
    public Item getItem(UUID id){
        for(Item c:mItems){
            if (c.getmId().equals(id)){
                return c;
            }
        }
        return null;
    }
}
