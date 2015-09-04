package com.wzh.learnfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Administrator on 2015/8/19.
 */
public class blibli extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.blibliwebview,container,false);
        WebView wbv= (WebView) rootview.findViewById(R.id.bliWebView);
        wbv.loadUrl("http://www.bilibili.com/");
        return rootview;
    }
}
