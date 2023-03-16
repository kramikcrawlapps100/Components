package com.example.components.interfaces;

import com.example.components.model.MarvelApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApiService {
    @GET("v1/public/characters")
    Call<MarvelApiResponse<Character>> getCharacters(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("limit") int limit
    );
}

