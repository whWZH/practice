package com.wzh.tuling;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2015/8/27.
 */
public class TestAdapta extends BaseAdapter {
    private List<ListData> listDatas;
    private Context context;
    private RelativeLayout layout;

    public TestAdapta(List<ListData> listDatas,Context context){
        this.listDatas=listDatas;
        this.context=context;

    }
    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        if (listDatas.get(position).getFlag()==ListData.RECEVER){
            layout= (RelativeLayout) layoutInflater.inflate(R.layout.left,null);
        }
        if (listDatas.get(position).getFlag()==ListData.SEND){
            layout= (RelativeLayout) layoutInflater.inflate(R.layout.right,null);
        }
        TextView tv= (TextView) layout.findViewById(R.id.tv);
        tv.setText(listDatas.get(position).getData());
        return layout;
    }
}
