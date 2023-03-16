package com.example.components.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    int postId;
    int id;
    String name;
    String email;
    @SerializedName("body")
    String description;

    public Comment(int postId, int id, String name, String email, String description) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
