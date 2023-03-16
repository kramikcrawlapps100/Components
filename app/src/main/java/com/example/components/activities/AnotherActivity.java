package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.components.fragments.FirstFragment;
import com.example.components.fragments.SecondFragment;
import com.example.components.interfaces.OnCommunicationFragmentListner;
import com.example.components.R;

public class AnotherActivity extends AppCompatActivity implements OnCommunicationFragmentListner {

    public static final String FIRST_TAG = "first";
    public static final String SECOND_TAG = "second";
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    TextView anotherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Log.d("lifecycle", "onCreate: AnotherActivity");
        anotherTextView = findViewById(R.id.anotherTextView);

//        Person person1 = new Person("Kramik",20);
//        Person person2 = new Person("Gohil",21);
//        Intent  intent = new Intent(AnotherActivity.this,SecondActivity.class);
//        intent.putExtra("person1",person1);
//        intent.putExtra("person2",person2);
//        startActivity(intent);

        FragmentManager fragmentManager = getSupportFragmentManager();
        firstFragment = (FirstFragment) fragmentManager.findFragmentByTag(FIRST_TAG);
        secondFragment = (SecondFragment) fragmentManager.findFragmentByTag(SECOND_TAG);
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
            fragmentManager.beginTransaction().add(R.id.firstFragment, firstFragment, FIRST_TAG).commit();
        }
        if (secondFragment == null) {
            secondFragment = new SecondFragment();
            fragmentManager.beginTransaction().add(R.id.secondFragment, secondFragment, SECOND_TAG).commit();
        }

//        Intent myIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//        PendingIntent pIntent = PendingIntent.getActivity(this, 0, myIntent, PendingIntent.FLAG_IMMUTABLE);//FLAG_ONE_SHOT - only once pIntent can use the myIntent
//
//        Person ip = new Person(pIntent);
//
//        Intent sndApp = new Intent();
//        sndApp.setComponent(new ComponentName("com.example.components", "com.example.components.Activities.AnotherActivity"));
//        sndApp.putExtra("obj",ip);
//        startActivity(sndApp);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart: AnotherActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume: AnotherActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart: AnotherActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause: AnotherActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop: AnotherActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy: AnotherActivity");
    }

    @Override
    public void msgFromFragment(String msg) {
        secondFragment.setMessage(msg);
        firstFragment.setMessage(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        anotherTextView.setText(msg);
    }
}