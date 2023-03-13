package com.example.components.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.components.databinding.ActivityServiceBinding;
import com.example.components.services.DownloadService;
import com.example.components.services.MyBoundService;

public class ServiceActivity extends AppCompatActivity {

    ActivityServiceBinding binding;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String string = bundle.getString(DownloadService.FILEPATH);
                int resultCode = bundle.getInt(DownloadService.RESULT);
                if (resultCode == RESULT_OK) {
                    Toast.makeText(ServiceActivity.this, "Download complete. Download URI: " + string, Toast.LENGTH_LONG).show();
                    Log.d("kramik","Download complete. Download URI: " + string);
                } else {
                    Toast.makeText(ServiceActivity.this, "Download failed", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    private MyBoundService myService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder) iBinder;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.start.setOnClickListener(v -> {

//            String downloadsDirectoryPath = Environment.getExternalStorageDirectory() + "/Documents";
//
//            FileDownloaderService downloader = new FileDownloaderService("https://example-files.online-convert.com/document/txt/example.txt", downloadsDirectoryPath);
//            downloader.start();
////            startService(new Intent(this, FileDownloaderService.class));
//            while (!downloader.isDownloadComplete()) {
//                System.out.println("Downloading...");
//                System.out.println(downloadsDirectoryPath);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            System.out.println("Download complete!");

//            Intent intent = new Intent(this, DownloadService.class);
//            // add infos for the service which file to download and where to store
//            intent.putExtra(DownloadService.FILENAME, "/Download/sample_video.mp4");
//            intent.putExtra(DownloadService.URL, "https://freetestdata.com/wp-content/uploads/2022/02/Free_Test_Data_15MB_MP4.mp4");
//            startService(intent);

//            startService(new Intent(this, RingtoneService.class));
            Intent intent = new Intent(this, MyBoundService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        });

        binding.tap.setOnClickListener(v->{
            if (myService != null) {
                binding.textView.setText(String.valueOf(myService.getCount()));
            }
        });

        binding.stop.setOnClickListener(v -> {
//            stopService(new Intent(this, RingtoneService.class));
//          stopService(new Intent(this, FileDownloaderService.class));
            if (myService != null) {
                unbindService(serviceConnection);
                binding.textView.setText("Count");
                myService = null;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                DownloadService.NOTIFICATION));

    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);

    }
}