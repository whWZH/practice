package com.example.wang.crime;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2015/9/6.
 */
public class ItemActivity extends SingleFragmentActivity{
    @Override
    protected Fragment creatFragment() {
        return new CrameItem();
    }
}
