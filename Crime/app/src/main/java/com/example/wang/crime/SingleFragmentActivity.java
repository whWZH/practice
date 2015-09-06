package com.example.wang.crime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2015/9/6.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract android.app.Fragment creatFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (savedInstanceState==null){
            android.app.Fragment fragment=creatFragment();
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer,creatFragment()).commit();
        }
    }
}
