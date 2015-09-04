package com.wzh.learnanimat01;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2015/8/23.
 */
public class CustomAnim extends Animation {
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        t.getMatrix().setTranslate((float)(Math.sin(interpolatedTime*20)*50),0);
    }
}
