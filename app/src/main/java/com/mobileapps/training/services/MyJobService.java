package com.mobileapps.training.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";
    
    public MyJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        //Schedule
        Log.d(TAG, "onStartJob: ");
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
