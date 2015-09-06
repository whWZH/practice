package com.example.wang.crime;

import android.app.ListFragment;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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
}
