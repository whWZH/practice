package com.wzh.learnservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                while (true) {
//                    System.out.println("服务正在运行....");
//                    try {
//                        sleep(1000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("服务创建...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("服务停止...");
    }
}
