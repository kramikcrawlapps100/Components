package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.example.components.broadcastReceivers.BluetoothReceiver;
import com.example.components.broadcastReceivers.CallReceiver;
import com.example.components.R;
import com.example.components.broadcastReceivers.AirplaneModeChangeReceiver;
import com.example.components.broadcastReceivers.NetworkConnectivityReceiver;

public class BroadcastActivity extends AppCompatActivity {

    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();
    NetworkConnectivityReceiver networkConnectivityReceiver = new NetworkConnectivityReceiver();

    CallReceiver incomingCallReceiver = new CallReceiver();

    private BluetoothReceiver bluetoothReceiver;

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        startDownload("https://www.youtube.com/watch?v=gNtJ4HdMavo&ab_channel=FilmSpotTrailer","kramik_video.mp4");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        registerReceiver(airplaneModeChangeReceiver, filter);

        IntentFilter connectivityFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkConnectivityReceiver, connectivityFilter);

//        IntentFilter callFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
//        registerReceiver(incomingCallReceiver, callFilter);

//        bluetoothReceiver = new BluetoothReceiver();
//        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
//        registerReceiver(bluetoothReceiver, filter);

//        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
//        startActivity(intent);


    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(airplaneModeChangeReceiver);
        unregisterReceiver(networkConnectivityReceiver);
//        unregisterReceiver(incomingCallReceiver);
//        unregisterReceiver(bluetoothReceiver);


    }

    public void startDownload(String url, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(fileName);
        request.setDescription("Downloading file...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        long downloadId = downloadManager.enqueue(request);
    }

}