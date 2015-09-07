package com.example.wang.crime;

import android.app.Fragment;

/**
 * Created by Administrator on 2015/9/6.
 */
public class ItemListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        return new ItemListFragment();
    }

}
