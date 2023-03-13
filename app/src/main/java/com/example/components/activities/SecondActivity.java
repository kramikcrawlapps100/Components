package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.components.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding activitySecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(activitySecondBinding.getRoot());

//        Person person1 = getIntent().getParcelableExtra("person1");
//        Person person2 = getIntent().getParcelableExtra("person2");
//
//        Log.d("kramik", "Name: " + person1.getName()+ " age: " + person1.getAge() + " " + person2.getName() + " age: " + person2.getAge());

        
        activitySecondBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Kramik Nakrani");
                sendIntent.setType("text/plain");

                try {
//                    startActivity(sendIntent);
                    Intent chooser = Intent.createChooser(sendIntent, "send to ....");
                    if (sendIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(chooser);
                    }
                } catch (ActivityNotFoundException e) {
                    // Define what your app should do if no activity can handle the intent.
                }
            }
        });

    }
}