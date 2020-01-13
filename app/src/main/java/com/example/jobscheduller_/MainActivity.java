package com.example.jobscheduller_;

import android.Manifest;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermissions(MainActivity.this);
        StartBackgroundTask();
    }

    private JobScheduler jobScheduler;
    private ComponentName componentName;
    private JobInfo jobInfo;

    public void StartBackgroundTask() {
        jobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        componentName = new ComponentName(getApplicationContext(), MyService.class);
        jobInfo = new JobInfo.Builder(1, componentName)
                .setMinimumLatency(10000) //10 sec interval
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setRequiresCharging(false).build();
        jobScheduler.schedule(jobInfo);
    }

    public  boolean checkAndRequestPermissions(Context context) {

        List<String> listPermissionsNeeded = new ArrayList();
        int locationFine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int locationAccess = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        //    int writefilePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        //    int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (locationFine != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (locationAccess != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        //    if (writefilePermission != PackageManager.PERMISSION_GRANTED) {
        //        listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        //    }
        //     if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
        //         listPermissionsNeeded.add(Manifest.permission.CAMERA);
        //     }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity)context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 6969);
            return false;
        }
        return true;
    }
}
