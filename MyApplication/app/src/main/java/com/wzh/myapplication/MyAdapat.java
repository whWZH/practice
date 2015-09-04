package com.wzh.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.ref.PhantomReference;
import java.util.List;

/**
 * Created by Administrator on 2015/8/27.
 */
public class MyAdapat extends BaseAdapter{
    private Context context;
    private List<PhoneInfo>  listP;
    private LinearLayout linearLayout;
    public MyAdapat(Context context,List<PhoneInfo>  listP){
        this.context=context;
        this.listP=listP;
    }
    @Override
    public int getCount() {
        return listP.size();
    }

    @Override
    public Object getItem(int position) {
        return listP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ViewHolder viewHolder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            linearLayout= (LinearLayout) inflater.inflate(R.layout.phino,null);
            viewHolder=new ViewHolder();
            viewHolder.nameTV= (TextView) linearLayout.findViewById(R.id.Pname);
            viewHolder.numTV= (TextView) linearLayout.findViewById(R.id.Pnum);
            viewHolder.nameTV.setText(listP.get(position).getPhname());
            viewHolder.numTV.setText(listP.get(position).getPhnum());
            convertView=linearLayout;
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
            viewHolder.nameTV.setText(listP.get(position).getPhname());
            viewHolder.numTV.setText(listP.get(position).getPhnum());
        }


        return convertView;
    }
    private class ViewHolder{
         TextView nameTV;
         TextView numTV;
    }
}
