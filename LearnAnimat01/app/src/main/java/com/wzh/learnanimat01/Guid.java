package com.wzh.learnanimat01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2015/8/24.
 */
public class Guid extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private List<View> viewList;
    private AdapaV av;
    private ViewPager vp;
    private ImageView[] pots;
    private int[] ids={R.id.pot1,R.id.pot2,R.id.pot3};
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guid_layout);
        initView();
        initpots();
        btn=(Button)viewList.get(2).findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Guid.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
    public void initpots(){
        pots=new ImageView[viewList.size()];
        for (int i=0;i<viewList.size();i++){
            pots[i]=(ImageView)findViewById(ids[i]);
        }
    }
    public void initView(){

        viewList=new ArrayList<>();
        LayoutInflater la=LayoutInflater.from(this);
        viewList.add(la.inflate(R.layout.oneimg,null));
        viewList.add(la.inflate(R.layout.twoimg,null));
        viewList.add(la.inflate(R.layout.threeimg,null));

        av=new AdapaV(viewList,this);
        vp= (ViewPager) findViewById(R.id.guid);
        vp.setAdapter(av);
        vp.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i=0;i<viewList.size();i++){
            if (i==position){
                pots[i].setImageResource(R.drawable.login_point_selected);
            }else {
                pots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
