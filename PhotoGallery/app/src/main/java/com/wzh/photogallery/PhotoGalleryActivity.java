package com.wzh.photogallery;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PhotoGalleryActivity extends SingleFragmentActivity {


    @Override
    protected Fragment creatFragment() {
        return new PhotoGalleryFragment();
    }
}
