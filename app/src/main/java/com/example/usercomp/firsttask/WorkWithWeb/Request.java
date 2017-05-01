package com.example.usercomp.firsttask.WorkWithWeb;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by UserComp on 01.05.2017.
 */

public class Request extends Application {

    private static RequestInterfeces requestApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("Server URL") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        requestApi = retrofit.create(RequestInterfeces.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static RequestInterfeces getApi() {
        return requestApi;
    }
}
