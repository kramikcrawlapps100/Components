package com.example.components.model;

import java.util.List;

public class MarvelApiResponse<Character> {

    private String status;
    private String message;
    private int code;
    private MarvelApiData<Character> data;

    // Getters and setters omitted for brevity

    public static class MarvelApiData<Character> {
        private int offset;
        private int limit;
        private int total;
        private int count;
        private List<Character> results;

        // Getters and setters omitted for brevity
    }
}

