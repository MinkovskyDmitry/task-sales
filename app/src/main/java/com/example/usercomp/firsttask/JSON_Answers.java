package com.example.usercomp.firsttask;

import android.app.Activity;
import android.content.res.AssetManager;

import com.example.usercomp.firsttask.ClassesForRequests.Answer;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by UserComp on 01.05.2017.
 */



public class JSON_Answers {
    final private static String LOGIN_OK="LoginOk.json";
    final private static String ERROR="Error.json";
    final private static String REGISTRATION_OK="RegistrationOk.json";
    final private static String GET_ALL_PRODUCTS="AllProducts.json";

    public static Answer getAnswer_Login_Ok(Activity activity) {
        String json = loadJSONFromAsset(LOGIN_OK,activity);
        Gson gson = new Gson();
        return gson.fromJson(json, Answer.class);
    }

    public static Answer getAnswer_Error(Activity activity) {
        String json = loadJSONFromAsset(ERROR,activity);
        Gson gson = new Gson();
        return gson.fromJson(json, Answer.class);
    }

    public static Answer getAnswer_Registration(Activity activity) {
        String json = loadJSONFromAsset(REGISTRATION_OK, activity);
        Gson gson = new Gson();
        return gson.fromJson(json, Answer.class);
    }

    public static Answer getAnswer_AllProdugts(Activity activity) {
        String json = loadJSONFromAsset(GET_ALL_PRODUCTS, activity);
        Gson gson = new Gson();
        return gson.fromJson(json, Answer.class);
    }

    private static String loadJSONFromAsset(String file_name, Activity activity) {
        String json = null;
        try {
            AssetManager assetManager =  activity.getAssets();

            InputStream is = assetManager.open(file_name);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
