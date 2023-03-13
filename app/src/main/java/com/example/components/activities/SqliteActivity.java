package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.components.database.FeedReaderDbHelper;
import com.example.components.databinding.ActivitySqlLiteBinding;

public class SqliteActivity extends AppCompatActivity {

    ActivitySqlLiteBinding binding;
    private FeedReaderDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqlLiteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new FeedReaderDbHelper(this);
//        dbHelper.insertEntry("title","subtitle");

//        Toast.makeText(this, dbHelper.getEntry(1), Toast.LENGTH_SHORT).show();
//        dbHelper.insertPerson("Kramik","Nakrani");

        dbHelper.deleteEntry(1);

    }
}