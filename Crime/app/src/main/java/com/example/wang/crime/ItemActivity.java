package com.example.wang.crime;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

/**
 * Created by Administrator on 2015/9/6.
 */
public class ItemActivity extends SingleFragmentActivity{
    @Override
    protected Fragment creatFragment() {
        UUID mid= (UUID) getIntent().getSerializableExtra("ID");
        return CrameItem.newInstance(mid);
    }
}
