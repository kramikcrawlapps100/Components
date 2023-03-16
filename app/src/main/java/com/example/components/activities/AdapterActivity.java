package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.components.R;
import com.example.components.adapter.CustomCursorAdapter;
import com.example.components.adapter.MyFragmentPagerAdapter;
import com.example.components.adapter.MyFragmentStatePagerAdapter;
import com.example.components.database.FeedReaderDbHelper;
import com.example.components.interfaces.OnCommunicationFragmentListner;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterActivity extends AppCompatActivity implements OnCommunicationFragmentListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
//        cursorAdapter();
//        simpleAdapter();
//        fragmentPagerAdapter();
//        fragmentStatePagerAdapter();
//        MergeAdapter();
    }
    private void fragmentStatePagerAdapter() {
        MyFragmentStatePagerAdapter adapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
    }

    private void fragmentPagerAdapter() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
    }

    private void simpleAdapter() {
        String fruitNames[] = {"Banana", "Grape", "Guava", "Mango", "Orange", "Watermelon"};
        int fruitImageIds[] = {R.drawable.banana,
                R.drawable.grape,
                R.drawable.guava,
                R.drawable.mango,
                R.drawable.orange,
                R.drawable.watermelon};
        ListView listView = findViewById(R.id.listview);
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < fruitNames.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("fruitName", fruitNames[i]);
            map.put("fruitImage", fruitImageIds[i]);
            list.add(map);
        }
        String[] from = {"fruitName", "fruitImage"};
        int to[] = {R.id.textView, R.id.imageView};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), list, R.layout.simple_adapter_item, from, to);
        listView.setAdapter(simpleAdapter);
    }

    private void cursorAdapter() {
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
        Cursor cursor = dbHelper.getEntryCursor();
        ListView listView = findViewById(R.id.listview);
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this,cursor);
        listView.setAdapter(customCursorAdapter);
    }

    @Override
    public void msgFromFragment(String msg) {

    }
}