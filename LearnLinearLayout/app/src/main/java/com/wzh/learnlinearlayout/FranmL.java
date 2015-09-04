package com.wzh.learnlinearlayout;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class FranmL extends AppCompatActivity {

    private ImageView iv1,iv2;
    private FrameLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franm_l);
        root= (FrameLayout) findViewById(R.id.frm);
        iv1= (ImageView) findViewById(R.id.img1);
        iv2= (ImageView) findViewById(R.id.img2);
        show1();
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv1.getVisibility()==View.VISIBLE){
                    show2();
                }else {
                    show1();
                }
            }
        });

    }
    private void show1(){
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.INVISIBLE);
    }
    private void show2(){
        iv1.setVisibility(View.INVISIBLE);
        iv2.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_franm_l, menu);
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
