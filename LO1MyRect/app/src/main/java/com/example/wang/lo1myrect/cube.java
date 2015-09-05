package com.example.wang.lo1myrect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wang on 2015/8/22.
 */
public class cube extends View {

    public cube(Context context) {
        super(context);
        init();
    }

    public cube(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public cube(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        p = new Paint();
        p.setColor(Color.RED);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();
        canvas.translate(200, 200);
        canvas.rotate(degrees, 50, 50);
        canvas.drawRect(0, 0, 100, 100, p);
        degrees++;
        canvas.restore();
        invalidate();

    }
    private Paint p;
    private float degrees=0;
}
