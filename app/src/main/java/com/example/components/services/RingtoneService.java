package com.example.components.services;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.components.broadcastReceivers.BluetoothReceiver;
import com.example.components.broadcastReceivers.CallReceiver;

public class RingtoneService extends Service {

    private MediaPlayer mediaPlayer;
    CallReceiver incomingCallReceiver = new CallReceiver();
    private BluetoothReceiver bluetoothReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mediaPlayer.start();

//        Notification.Builder builder =
//                null; //this is notification message
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            builder = new Notification.Builder(this,"channel_id")
//                    .setSmallIcon(R.drawable.ic_launcher_background)
//                    .setContentTitle("Notifications Example") //set title of notification
//                    .setAutoCancel(true) //
//                    .setContentText("This is a notification message");
//        }
//
//        Intent notificationIntent = new Intent(this, AnotherActivity.class);
//        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        //notification message will get at NotificationView
//        notificationIntent.putExtra("message", "This is a notification message");
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                PendingIntent.FLAG_IMMUTABLE);
//        assert builder != null;
//        builder.setContentIntent(pendingIntent);
//
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//        {
//            String channelId = "Your_channel_id";
//            NotificationChannel channel = new NotificationChannel(
//                    channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_HIGH);
//            manager.createNotificationChannel(channel);
//            builder.setChannelId(channelId);
//        }
//        manager.notify(0, builder.build());

        IntentFilter callFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(incomingCallReceiver, callFilter);
        Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();

        bluetoothReceiver = new BluetoothReceiver();
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothReceiver, filter);

        Log.d("kramik", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mediaPlayer.stop();
        unregisterReceiver(incomingCallReceiver);
        unregisterReceiver(bluetoothReceiver);
        Toast.makeText(this, "UnRegister", Toast.LENGTH_SHORT).show();

        Log.d("kramik", "onDestroy");
    }
}
