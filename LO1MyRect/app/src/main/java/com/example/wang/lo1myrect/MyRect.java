package com.example.wang.lo1myrect;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.TypeVariable;

/**
 * Created by wang on 2015/8/22.
 */
public class MyRect extends View {
    public MyRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.MyRect);
        int color=ta.getColor(R.styleable.MyRect_rec_color,0xffff0000);
       setBackgroundColor(color);
        ta.recycle();
    }

    public MyRect(Context context) {
        super(context);
    }
}
