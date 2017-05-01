package com.example.usercomp.firsttask.WorkWithWeb;

import com.example.usercomp.firsttask.ClassesForRequests.Answer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by UserComp on 01.05.2017.
 */

public interface RequestInterfeces {

    @GET("/getAllCategories")
    Call<Answer> getAllProducts();

    @POST("/login")
    Call<Answer> login( @Query("login") String login,
                        @Query("password") String password);

    @POST("/registration")
    Call<Answer> registration( @Query("login") String login,
                                      @Query("password") String password,
                                      @Query("phone") String phone);


}
