package com.example.wang.startservicefromanotherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AppService extends Service {
    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return new IAppServiceInterface.Stub(){
           @Override
           public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

           }

           @Override
           public void setData(String data) throws RemoteException {
               
           }
       };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("服务启动");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("服务销毁");
    }
}
