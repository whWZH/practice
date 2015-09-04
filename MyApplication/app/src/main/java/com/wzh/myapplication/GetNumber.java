package com.wzh.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/27.
 */
public class GetNumber {
    public static List<PhoneInfo> phlist=new ArrayList<PhoneInfo>();
    public static String getnumber(Context context){
        Cursor cursor=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        String Pnum;
        String Pname;
        while (cursor.moveToNext()){
            Pnum=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Pname=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            PhoneInfo PI=new PhoneInfo(Pnum,Pname);
            phlist.add(PI);
            System.out.println(Pname+Pnum);
        }
        return null;

    }
}
