package com.mobileapps.training.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Service created ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: "+ intent.getStringExtra("data"));
        Log.d(TAG, "onStartCommand: Thread: " + Thread.currentThread().getName());

        //Perform a task
        Log.d(TAG, "onStartCommand: Performing a task");

        //Stop after task completed
        Log.d(TAG, "onStartCommand: Task complete");
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind:");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
