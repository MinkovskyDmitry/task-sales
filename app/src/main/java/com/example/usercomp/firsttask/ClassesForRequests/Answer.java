package com.example.usercomp.firsttask.ClassesForRequests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("error")
    @Expose
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
