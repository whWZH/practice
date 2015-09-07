package com.example.wang.crime;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/6.
 */
public class ItemListFragment extends ListFragment {
    private ArrayList<Item> mItems;
    private mAdapter mad;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime_title);
        mItems=CrimeLab.getsCrimeLab(getActivity()).getmItems();
        mad=new mAdapter(mItems,getActivity().getApplicationContext());
        setListAdapter(mad);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Item i= mItems.get(position);
        Intent intent=new Intent(getActivity(),ItemActivity.class);
        intent.putExtra("ID", i.getmId());
//        startActivity(intent);
        startActivityForResult(intent,0);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((mAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0){

        }
    }
}
