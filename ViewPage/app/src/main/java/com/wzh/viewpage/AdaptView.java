package com.wzh.viewpage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public class AdaptView extends PagerAdapter{

    private List<View> viewList;
    private Context context;
    public AdaptView (List<View> viewList,Context context){
        this.context=context;
        this.viewList=viewList;
    }
    @Override
    public int getCount() {
        return this.viewList.size();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view==o);
    }
}
