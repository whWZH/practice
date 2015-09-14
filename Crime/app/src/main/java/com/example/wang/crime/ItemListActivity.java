package com.example.wang.crime;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import java.io.InputStream;

/**
 * Created by Administrator on 2015/9/6.
 */
public class ItemListActivity extends SingleFragmentActivity implements ItemListFragment.Callbacks {
    @Override
    protected Fragment creatFragment() {
        return new ItemListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeselected(Item item) {
        if (findViewById(R.id.detailFragmentContainer)==null){
            Intent i=new Intent(this,CrimePagerActivity.class);
            i.putExtra("ID",item.getmId());
            startActivity(i);
        }else {
            FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.Fragment oldDetail=fm.findFragmentById(R.id.detailFragmentContainer);
            android.support.v4.app.Fragment newDetail=CrameItem.newInstance(item.getmId());
            if (oldDetail!=null){
                fm.beginTransaction().remove(oldDetail);
            }
            fm.beginTransaction().add(R.id.detailFragmentContainer,newDetail).commit();
        }
    }
}
