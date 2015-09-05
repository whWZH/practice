package com.example.wang.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    private boolean running=false;
    private String data="这是默认信息";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }
    public class Binder extends android.os.Binder{
        public void setData(String data){
            MyService.this.data=data;
        }
    }
    @Override
    public void onCreate() {
        running=true;
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (running){
                    System.out.println(data);
                    try {
                        sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running=false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        data=intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);

    }
}
