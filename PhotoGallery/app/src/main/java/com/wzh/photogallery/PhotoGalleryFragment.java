package com.wzh.photogallery;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
public class PhotoGalleryFragment extends Fragment {
    GridView mGridView;
    private static  final String TAG="PhotoGalleryFragment";
    ArrayList<GalleryItem> items;
    ThumbnailDownloader<ImageView> mThumbnailDownloader;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
        mThumbnailDownloader=new ThumbnailDownloader<ImageView>(new Handler());
        mThumbnailDownloader.setmListener(new ThumbnailDownloader.Listener<ImageView>() {
            @Override
            public void onThunbnaiDownload(ImageView imageView, Bitmap thumbnail) {
                if (isVisible()){
                    imageView.setImageBitmap(thumbnail);
                }
            }
        });
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();
        System.out.println("后台线程启动");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
        System.out.println("后台线程销毁");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_photo_gallery,container,false);
        mGridView= (GridView) v.findViewById(R.id.gridView);
        setupAdapter();
        return v;
    }
    private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<GalleryItem>>{
        @Override
        protected ArrayList<GalleryItem> doInBackground(Void... params) {
            try {
                new FlickrFetchr().fetchItems();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                return new FlickrFetchr().fetchItems();
            } catch (JSONException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<GalleryItem> mitems) {
            items=mitems;
            setupAdapter();
        }
    }
    void setupAdapter(){
        if (getActivity()==null||mGridView==null)return;
        if (items!=null){
            mGridView.setAdapter(new GallertItemAdapter(items) );
        }else {
            mGridView.setAdapter(null);
        }
    }
    private class GallertItemAdapter extends ArrayAdapter<GalleryItem>{

        public GallertItemAdapter(ArrayList<GalleryItem> items) {
            super(getActivity(),0,items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.gallert_item,parent,false);
            }
            ImageView imageView= (ImageView) convertView.findViewById(R.id.gallery_item_imageView);
            imageView.setImageResource(R.drawable.abc_ab_share_pack_mtrl_alpha);
            GalleryItem item=getItem(position);
            mThumbnailDownloader.queueThumbnail(imageView,item.getmPicUrl());
            return convertView;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailDownloader.clearQueue();
    }
}
