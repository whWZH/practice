package com.wzh.learnanimat01;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.DrawableContainer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private Animation aa;
    private Animation.AnimationListener al=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(MainActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View root=LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
            LinearLayout rootview=(LinearLayout)root;


        ScaleAnimation sa=new ScaleAnimation(0,1,0,1);
        sa.setDuration(2000);
        LayoutAnimationController la=new LayoutAnimationController(sa,0.2f);
        la.setOrder(LayoutAnimationController.ORDER_RANDOM);
        rootview.setLayoutAnimation(la);

       setContentView(rootview);

        findViewById(R.id.btnAlpha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                aa.setAnimationListener(al);
                v.startAnimation(aa);
//                AlphaAnimation aa=new AlphaAnimation(0,1);
//                aa.setDuration(1000);
//                v.startAnimation(aa);
            }
        });
        findViewById(R.id.btnRotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa=AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
                aa.setAnimationListener(al);
                v.startAnimation(aa);
            }
        });
        findViewById(R.id.btnTranslate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa=  AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
                aa.setAnimationListener(al);
                v.startAnimation(aa);
            }
        });
        findViewById(R.id.btnScale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa=  AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
                aa.setAnimationListener(al);
                v.startAnimation(aa);
            }
        });
        findViewById(R.id.btnAnmi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa= AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim);
                aa.setAnimationListener(al);
                v.startAnimation(aa);
            }
        });
        findViewById(R.id.btnUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAnim ca=new CustomAnim();
                v.startAnimation(ca);
            }
        });
        findViewById(R.id.btnLayoutCA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        findViewById(R.id.btnLayoutLCA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inttt=new Intent("com.wzh.learnanimat01.Main3Activity.1");
                startActivity(inttt);
            }
        });
        findViewById(R.id.btnIMG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main4Activity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
