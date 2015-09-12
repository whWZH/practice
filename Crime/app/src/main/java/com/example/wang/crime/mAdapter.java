package com.example.wang.crime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by wang on 2015/9/6.
 */
public class mAdapter extends BaseAdapter {
    private ArrayList<Item> mList;
    private Context context;
    public mAdapter(ArrayList<Item> mList,Context context){
        setmList(mList);
        setContext(context);
    }

    public void setmList(ArrayList<Item> mList) {
        this.mList = mList;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.itemlist,null);
        }
        TextView title= (TextView) convertView.findViewById(R.id.itemlist_text);
        title.setText(mList.get(position).getmTitle().toString());
        CheckBox checkBox= (CheckBox) convertView.findViewById(R.id.itenlist_check);
        checkBox.setChecked(mList.get(position).getmSolved());
        TextView data= (TextView) convertView.findViewById(R.id.itemlist_data);
        data.setText(DateFormat.getDateTimeInstance().format(mList.get(position).getmData()));
        return convertView;
    }
}
