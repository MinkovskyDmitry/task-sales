package com.example.usercomp.firsttask.ClassesForRequests;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("sessionKey")
    @Expose
    private String sessionKey;

    @SerializedName("alive")
    @Expose
    private Integer alive;

    public Integer getAlive() {
        return alive;
    }

    public void setAlive(Integer alive) {
        this.alive = alive;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
