package com.wzh.viewpage;

import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public class GuidPage extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager vp;
    private AdaptView av;
    private List<View> lv;
    private ImageView[] dots;
    private int[] ids={R.id.pot1,R.id.pot2,R.id.pot3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guid_layout);
        initGuid();
        initdos();
    }
    public void initGuid(){
        LayoutInflater la=LayoutInflater.from(this);

        lv=new ArrayList<>();
        lv.add(la.inflate(R.layout.oneimg,null));
        lv.add(la.inflate(R.layout.twoimg,null));
        lv.add(la.inflate(R.layout.threeimg,null));

       av=new AdaptView(lv,this);
       vp=(ViewPager)findViewById(R.id.guidPage);
        vp.setAdapter(av);
        vp.setOnPageChangeListener(this);
    }
    public void initdos(){
        dots=new ImageView[ids.length];
        for (int i=0;i<ids.length;i++)
        {
           dots[i]= (ImageView) findViewById(ids[i]);
        }
    }
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int k) {
        for (int i=0;i<ids.length;i++){
            if (i==k){
                dots[i].setImageResource(R.drawable.login_point_selected);
            } else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int k) {
    }
}
