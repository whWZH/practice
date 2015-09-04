package com.wzh.learnanimat01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main4Activity extends AppCompatActivity {

    private FrameLayout Rroot;
    private ImageView imageView;
    float LastL =-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        imageView= (ImageView) findViewById(R.id.M4IMG);
        Rroot= (FrameLayout)findViewById(R.id.M4);
        Rroot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (event.getPointerCount()>=2) {
                            float dx = event.getX(0) - event.getX(1);
                            float dy = event.getY(0) - event.getY(1);
                            float currentL = (float) Math.sqrt(dx * dx + dy * dy);
                            if (LastL == -1) {
                                LastL = currentL;
                            } else {
                                if ((currentL - LastL) > 5) {
                                    FrameLayout.LayoutParams fl= (FrameLayout.LayoutParams) imageView.getLayoutParams();
                                    fl.width=(int)(1.1f*imageView.getWidth());
                                    fl.height=(int)(1.1f*imageView.getHeight());
                                    imageView.setLayoutParams(fl);
                                    LastL = currentL;
                                }
                                if ((LastL - currentL) > 5) {
                                    FrameLayout.LayoutParams fl= (FrameLayout.LayoutParams) imageView.getLayoutParams();
                                    fl.width=(int)(0.9f*imageView.getWidth());
                                    fl.height=(int)(0.9f*imageView.getHeight());
                                    imageView.setLayoutParams(fl);
                                    LastL = currentL;
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main4, menu);
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
