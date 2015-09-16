package com.wzh.photogallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by Administrator on 2015/9/6.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity{
    protected abstract android.app.Fragment creatFragment();

    protected int getLayoutResId(){
        return R.layout.activity_fragment;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if (savedInstanceState==null){
            android.app.Fragment fragment=creatFragment();
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer,creatFragment()).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
