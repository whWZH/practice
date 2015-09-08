package com.example.wang.crime;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2015/9/8.
 */
public class CrimePagerActivity extends FragmentActivity{
    private ViewPager mViewPager;
    private ArrayList<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager=new ViewPager(this);
        mViewPager.setId(R.id.viewgaer);
        setContentView(mViewPager);

        mItems=CrimeLab.getsCrimeLab(this).getmItems();
        FragmentManager fm=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Item item=mItems.get(position);
                return CrameItem.newInstance(item.getmId());
            }

            @Override
            public int getCount() {
                return mItems.size();
            }
        });
        UUID crimeId=(UUID)getIntent().getSerializableExtra("ID");
        for (int i=0;i<mItems.size();i++ ){
            if (mItems.get(i).getmId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
