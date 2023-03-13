package com.example.components.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyBoundService extends Service {
    private final IBinder binder = new MyBinder();

    private final Handler handler = new Handler();
    private Runnable runnable;
    private int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        runnable = new Runnable() {
            @Override
            public void run() {
                counter++;
                // Schedule the runnable to run again in one second
                handler.postDelayed(this, 80);
            }
        };

    }

    public class MyBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        handler.postDelayed(runnable, 300);
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        handler.removeCallbacks(runnable);
        return super.onUnbind(intent);
    }

    public String currentTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }

    public int getRandomNumber() {
        return new Random().nextInt(100);
    }

    public int getCount() {
        return counter;
    }


}
