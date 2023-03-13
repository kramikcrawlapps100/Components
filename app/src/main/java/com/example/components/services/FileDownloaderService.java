package com.example.components.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import androidx.annotation.Nullable;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class FileDownloaderService extends Service {
    private String url;
    private String outputFilePath;
    private boolean downloadComplete;
    private Timer timer;

    public FileDownloaderService(String url, String outputFilePath) {
        this.url = url;
        this.outputFilePath = outputFilePath;
        this.downloadComplete = false;
        this.timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    URL downloadUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream inputStream = connection.getInputStream();
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.close();
                    inputStream.close();
                    downloadComplete = true;
                    timer.cancel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 0, 1000);

        Log.d("kramik", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mediaPlayer.stop();
        timer.cancel();
        Log.d("kramik", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void start() {
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    URL downloadUrl = new URL(url);
//                    HttpsURLConnection connection = (HttpsURLConnection) downloadUrl.openConnection();
//                    connection.setRequestMethod("GET");
//                    InputStream inputStream = connection.getInputStream();
//                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));
//                    byte[] buffer = new byte[4096];
//                    int bytesRead;
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                    }
//                    outputStream.close();
//                    inputStream.close();
//                    downloadComplete = true;
//                    timer.cancel();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                try {

                    FileOutputStream f = new FileOutputStream(outputFilePath);
                    URL u = new URL(url);
                    HttpsURLConnection c = (HttpsURLConnection) u.openConnection();
                    c.setRequestMethod("GET");
                    c.setDoOutput(true);
                    c.connect();

                    InputStream in = c.getInputStream();

                    byte[] buffer = new byte[1024];
                    int len1 = 0;
                    while ((len1 = in.read(buffer)) > 0) {
                        f.write(buffer, 0, len1);
                        Log.d("downloader", "downloading");
                    }

                    f.close();
                    in.close();
                    downloadComplete = true;
                    timer.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("downloader", e.toString());
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }

    public void stop() {
        timer.cancel();
    }

    public boolean isDownloadComplete() {
        return downloadComplete;
    }
}

