package com.example.wang.crime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2015/9/12.
 */
public class ImageFragment extends DialogFragment {
public static final String EXTRA_IMAGE_PATH="the_image_path";
private ImageView mImageView;
    public static ImageFragment newInstance(String image_path) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_IMAGE_PATH,image_path);
        ImageFragment  fragment = new ImageFragment ();
        fragment.setArguments(args);
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mImageView=new ImageView(getActivity());
        String path= (String) getArguments().getSerializable(EXTRA_IMAGE_PATH);
        Uri uri=Uri.parse(path);
        try {
            Bitmap image= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
            mImageView.setImageBitmap(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mImageView;
    }
}
