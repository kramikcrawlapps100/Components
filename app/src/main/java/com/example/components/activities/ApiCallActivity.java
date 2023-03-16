package com.example.components.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.components.R;
import com.example.components.RetrofitClient;
import com.example.components.databinding.ActivityApiCallBinding;
import com.example.components.interfaces.JsonPlaceHolderApi;
import com.example.components.interfaces.MarvelApiService;
import com.example.components.interfaces.WeatherApiService;
import com.example.components.model.Comment;
import com.example.components.model.MarvelApiResponse;
import com.example.components.model.Weather;
import com.example.components.repository.WeatherRepository;
import com.example.components.viewmodel.WeatherViewModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCallActivity extends AppCompatActivity {

    private ActivityApiCallBinding binding;
    private static final String API_KEY = "62eda372d77de40ebf672981ed747bb7";
    private WeatherViewModel weatherViewModel;
    private WeatherApiService weatherApiService;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApiCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        weatherViewModel = new WeatherViewModel();
//        weatherApiService = RetrofitClient.getRetrofitInstance().create(WeatherApiService.class);



        binding.parse.setOnClickListener(v->{
//            Call<Weather> call = weatherApiService.getWeather("Surat", API_KEY);
//            call.enqueue(new Callback<Weather>() {
//                @Override
//                public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
//                    if (response.isSuccessful() && response.body() != null) {
//                        weather = response.body();
//                        String temp = String.valueOf(weather.getMain().getTemp());
//                        Log.d("kramik", temp);
//                        binding.textView2.setText(temp);
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
//                    Log.e("kramik", "Failed to get weather data: " + t.getMessage());
//                }
//            });

            Weather weather = weatherViewModel.getWeather("Surat",API_KEY);
            if (weather != null){
                binding.textView2.setText(String.valueOf(weather.getMain().getTemp()));
            }

//            double temp = weatherViewModel.getWeather("London",API_KEY);
//            binding.textView2.setText(String.valueOf(temp));

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//        Call<List<Comment>> call = jsonPlaceHolderApi.getAllComments();
//        call.enqueue(new Callback<List<Comment>>() {
//            @Override
//            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
//                if (!response.isSuccessful()) {
//                    binding.textView2.setText("Response:- " + response.code());
//                    return;
//                }
//                List<Comment> comments = response.body();
//                for (Comment comment : comments){
//                    String commentText = comment.getId() + "\n" + comment.getPostId() + "\n" + comment.getName() + "\n" + comment.getEmail() +"\n" + comment.getDescription() + "\n\n";
//                    binding.textView2.append(commentText);
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Comment>> call, Throwable t) {
//                binding.textView2.setText(t.getMessage());
//            }
//        });
        });
    }
}