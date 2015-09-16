package com.wzh.photogallery;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.GpsStatus;
import android.media.session.MediaSession;
import android.net.sip.SipAudioCall;
import android.os.HandlerThread;
import android.os.Message;
import android.test.suitebuilder.annotation.Suppress;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Administrator on 2015/9/15.
 */
public class ThumbnailDownloader<Token> extends HandlerThread {
    private static final String TAG="ThimbnailDownloader";
    private static final int MESSAGE_DOWNLOAD=0;
    android.os.Handler mResponseHandler;
    Listener<Token> mListener;

    public interface Listener<Token>{
        void onThunbnaiDownload(Token token,Bitmap thumbnail);
    }

    public void setmListener(Listener<Token> mListener) {
        this.mListener = mListener;
    }
    public ThumbnailDownloader(android.os.Handler responseHandler){
        super(TAG);
        mResponseHandler=responseHandler;
    }

    android.os.Handler mHandler;
    Map<Token,String> requesMap=
            Collections.synchronizedMap(new HashMap<Token,String>());
    public ThumbnailDownloader(){
        super(TAG);
    }
    public void queueThumbnail(Token token,String url){
        System.out.println("Got an URL:"+url);
        requesMap.put(token,url);
        mHandler.obtainMessage(MESSAGE_DOWNLOAD,token)
                .sendToTarget();
    }
    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        mHandler=new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==MESSAGE_DOWNLOAD){
                    @SuppressWarnings("unchecked")
                    Token token= (Token) msg.obj;
                    System.out.println("获得一个请求");
                    try {
                        handleRequest(token);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
    private void handleRequest(final Token token) throws IOException {
        final String url=requesMap.get(token);
        if (url==null)
            return;
        byte[] bitmapBytes=new FlickrFetchr().grtUrlBytes(url);
        final Bitmap bitmap= BitmapFactory.decodeByteArray(bitmapBytes,0,bitmapBytes.length);
        mResponseHandler.post(new Runnable() {
            @Override
            public void run() {
                if (requesMap.get(token)!=url)
                    return;
                requesMap.remove(token);
                mListener.onThunbnaiDownload(token,bitmap);
            }
        });
    }
    public void clearQueue(){
        mHandler.removeMessages(MESSAGE_DOWNLOAD);
        requesMap.clear();
    }
}
