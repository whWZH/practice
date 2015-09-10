package com.example.wang.crime;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2015/9/6.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab=null;
    private static final String TAG="CrimeLab";
    private static final String FILENAME="crimes.json";
    private ItemTentToJSON mTurn;
    private Context mAppcontext;
    private ArrayList<Item> mItems;
    private CrimeLab(Context context){
        mAppcontext=context;
        mTurn =new ItemTentToJSON(context,FILENAME);
        try{
            mItems=mTurn.loadCrimes();
        }catch (Exception e){
            mItems=new ArrayList<>();
            Toast.makeText(context,"保存出错",Toast.LENGTH_SHORT).show();
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
    public void addCrime(Item item){
        mItems.add(item);
    }
    public boolean sveItems(){
        try{
            mTurn.saveCrimeS(mItems);
            Toast.makeText(mAppcontext,"保存成功",Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(mAppcontext,"保存出错",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
