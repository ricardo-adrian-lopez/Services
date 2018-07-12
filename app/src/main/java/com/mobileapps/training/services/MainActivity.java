package com.mobileapps.training.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyBoundService boundService;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartedServices(View view) {
        //Service
        Intent serviceIntent = new Intent(getApplicationContext(),MyService.class);
        serviceIntent.putExtra("data","data");

        //IntentService
        Intent intentService = new Intent(MainActivity.this, MyIntentService.class);
        switch (view.getId()){
            case R.id.btnStartService:
                startService(serviceIntent);
                break;
            case R.id.btnStopService:
                stopService(serviceIntent);
                break;
            case R.id.btnStartIntent:
                startService(intentService);
                     break;
        }
    }

    public void onBoundservice(View view) {
        Intent boundIntent = new Intent(getApplicationContext(),MyBoundService.class);
        switch (view.getId()){
            case R.id.btnBoundService:
                if(!isBound){
                    bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                    isBound=true;
                }
                break;
            case R.id.btnGetData:
                if(isBound){
                    String data = boundService.getDataFromServer();
                    Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnUnBind:

                if(isBound){
                    unbindService(serviceConnection);
                    isBound=false;
                }
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) service;
            boundService = myBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
