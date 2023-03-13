package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.components.R;
import com.example.components.databinding.ActivityPrefBinding;

public class PrefActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private ActivityPrefBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Float fontSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityPrefBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        editor = sharedPreferences.edit();
        fontSize = sharedPreferences.getFloat("font_size", 10);
        binding.click.setTextSize(fontSize);

        registerForContextMenu(binding.click);
        binding.click.setOnClickListener(view -> {

            PopupMenu popup = new PopupMenu(PrefActivity.this, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.font_size, popup.getMenu());
            popup.setOnMenuItemClickListener(this::mainMenuItemClick);
            popup.show();
        });
    }

    public boolean mainMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.size_ten:  {
                changeFontSize(10.0f);
                return true;
            }
            case R.id.size_fifteen: {
                changeFontSize(15.0f);
                return true;
            }
            case R.id.size_twenty:  {
                changeFontSize(20.0f);
                return true;
            }
            case R.id.size_thirty:  {
                changeFontSize(30.0f);
                return true;
            }
            default:
                return false;
        }
    }

    private void changeFontSize(Float FontSize) {
        editor.putFloat("font_size", FontSize).apply();
        fontSize = sharedPreferences.getFloat("font_size", 10);
        binding.click.setTextSize(fontSize);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("font_size")) {
            Toast.makeText(this, "font size changed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}