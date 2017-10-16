package com.example.android.recyclerviewexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class CountdownService extends Service {
    private static final String TAG = CountdownService.class.getSimpleName();
    private static CountDownTimer mCountDownTimer;
    public CountdownService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCountDownTimer = new CountDownTimer(100000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "on tick: " + millisUntilFinished);
                Intent intent = new Intent();
                if(100 - Math.round(millisUntilFinished / 1000) <= 100) {
                    intent.setAction("updateProgress");
                    intent.putExtra("progress", 100 - Math.round(millisUntilFinished / 1000));
                    sendBroadcast(intent);
                }else{
                    intent.setAction("dismiss");
                    sendBroadcast(intent);
                    CountdownService.this.stopSelf();
                }
                
            }

            public void onFinish() {

            }
        };
       
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCountDownTimer.start();
        return START_NOT_STICKY;
    }
}
