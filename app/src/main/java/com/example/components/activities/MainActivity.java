package com.example.components.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.components.fragments.FirstFragment;
import com.example.components.fragments.SecondFragment;
import com.example.components.R;

@RequiresApi(api = Build.VERSION_CODES.S)
public class MainActivity extends AppCompatActivity{

    TextView textView;
    String value = "";
    public static final String ROOT_FRAGMENT_TAG = "ROOT_FRAGMENT_TAG";
    Button btn1,btn2;
    FrameLayout flFragment;
    FrameLayout flFragment2;

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    Bundle bundle = new Bundle();

    SharedPreferences sharedPreferences;


//    private static final int PERMISSION_REQUEST_CODE = 200;

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        flFragment = findViewById(R.id.flFragment);
        flFragment2 = findViewById(R.id.flFragment2);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        bundle.putString("activity", "Activity");

//        if(savedInstanceState == null){
//            openFragment(firstFragment,true);
//        }
//
//        btn1.setOnClickListener(v->{
//            openFragment(firstFragment,true);
//        });
//
//        btn2.setOnClickListener(v->{
//           openFragment(secondFragment,false);
//        });

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.flFragment, new FirstFragment())
//                .add(R.id.flFragment2, new SecondFragment())
//                .commit();

        if (savedInstanceState != null){
            value = savedInstanceState.getString("MicroTik");
        }

        Log.d("lifecycle", "onCreate: ");

//        if (checkPermission()) {
//            //main logic or main code
//
//            // . write your main code to execute, It will execute if the permission is already given.
//
//        } else {
//            requestPermission();
//        }

        boolean allPermissionsGranted = true;
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (!allPermissionsGranted) {

            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        } else {
            Toast.makeText(this, "do something", Toast.LENGTH_SHORT).show();
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            // Request BLUETOOTH_CONNECT permission at runtime
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
//                    != PackageManager.PERMISSION_GRANTED) {
//                // Permission has not been granted yet
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.BLUETOOTH_CONNECT},
//                        MY_PERMISSIONS_REQUEST_BLUETOOTH_CONNECT);
//            } else {
//                // Permission already granted
//                // Do something that requires BLUETOOTH_CONNECT permission
//            }
//        } else {
//            // Request BLUETOOTH_CONNECT permission in the manifest
//            // Do something that requires BLUETOOTH_CONNECT permission
//        }

        registerForContextMenu(btn1);
        btn2.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.main_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return mainMenuItemClick(item);
                }
            });

            popup.show();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            boolean allPermissionsGranted = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    // Handle individual permission denied as appropriate
                    if (TextUtils.equals(permissions[i], Manifest.permission.CAMERA)) {
                        Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.equals(permissions[i], Manifest.permission.READ_PHONE_STATE)) {
                        Toast.makeText(this, "Read phone state permission denied", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.equals(permissions[i], Manifest.permission.READ_CALL_LOG)) {
                        Toast.makeText(this, "Call log denied", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.equals(permissions[i], Manifest.permission.POST_NOTIFICATIONS)) {
                        Toast.makeText(this, "notification denied", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.equals(permissions[i], Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        Toast.makeText(this, "nearby device denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            if (allPermissionsGranted) {
                // All required permissions have been granted, do something
                Toast.makeText(this, "do something", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private boolean checkPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission is not granted
//            return false;
//        }
//        return true;
//    }
//
//    private void requestPermission() {
//
//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.READ_PHONE_STATE},
//                PERMISSION_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
//
//                    // main logic
//                } else {
//                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
//                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
//                            != PackageManager.PERMISSION_GRANTED) {
//                        showMessageOKCancel("You need to allow access permissions",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        requestPermission();
//                                    }
//                                });
//                    }
//                }
//                break;
//        }
//    }
//
//    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
//        new AlertDialog.Builder(MainActivity.this)
//                .setMessage(message)
//                .setPositiveButton("OK", okListener)
//                .setNegativeButton("Cancel", null)
//                .create()
//                .show();
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return mainMenuItemClick(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return mainMenuItemClick(item);
    }

    public boolean mainMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dialog_open:  {
                startActivity(new Intent(this, DialogActivity.class));
                return true;
            }
            case R.id.fragment_open: {
                return true;
            }
            case R.id.second_open:  {
                startActivity(new Intent(this, SecondActivity.class));
                return true;
            }
            case R.id.another_open:  {
                startActivity(new Intent(this, AnotherActivity.class));
                return true;
            }
            case R.id.service_open:  {
                startActivity(new Intent(this, ServiceActivity.class));
                return true;
            }
            case R.id.broad_open:  {
                startActivity(new Intent(this, BroadcastActivity.class));
                return true;
            }
            default:
                return false;
        }
    }

//    private void openFragment(final Fragment fragment, boolean flag)   {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setReorderingAllowed(true);
//        transaction.setCustomAnimations(
//                R.anim.slide_in,
//                R.anim.fade_out,
//                R.anim.fade_in,
//                R.anim.slide_out
//        );
//        if (flag){
//            transaction.add(R.id.flFragment, fragment);
//            fragmentManager.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            transaction.addToBackStack(ROOT_FRAGMENT_TAG);
//        }
//        else {
//            transaction.replace(R.id.flFragment, fragment);
//            transaction.addToBackStack(null);
//        }
//        transaction.commit();
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("MicroTik","nirvana");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy: ");
    }
}