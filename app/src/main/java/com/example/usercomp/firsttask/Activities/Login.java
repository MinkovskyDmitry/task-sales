package com.example.usercomp.firsttask.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usercomp.firsttask.ClassesForRequests.Answer;
import com.example.usercomp.firsttask.JSON_Answers;
import com.example.usercomp.firsttask.R;
import com.example.usercomp.firsttask.WorkWithWeb.Request;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

   private TextView registration;
   private EditText login;
   private EditText password;
   private Button signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registration = (TextView) findViewById(R.id.activity_login_Registration);
        login = (EditText) findViewById(R.id.activity_login_editText_nameOfUser);
        password = (EditText) findViewById(R.id.activity_login_editText_password);
        signIn = (Button) findViewById(R.id.activity_login_button_SignIn);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myLogin = login.getText().toString();
                String myPassword = password.getText().toString();

                if(checkForm(myLogin,myPassword)) {

                    if (isNetworkAvailable()){
                        Answer answer = JSON_Answers.getAnswer_Login_Ok(Login.this);


                        if (answer.getError() == null && answer.getData() != null){

                            Intent intent = new Intent(Login.this, AllProducts.class);
                            intent.putExtra("sessionKey", answer.getData().getSessionKey().toString());
                            startActivity(intent);
                        }
                        else{
                            if (answer.getError() != null){
                                Toast.makeText(Login.this, answer.getError().getErrorMessage()
                                        , Toast.LENGTH_SHORT).show();

                            }

                            }
                        }
                        else{
                            Toast.makeText(Login.this, "Проверьте подключение к интернету"
                                    , Toast.LENGTH_SHORT).show();

                        }




//                    Request.getApi().login(myLogin, myPassword).enqueue(new Callback<Answer>() {
//                        @Override
//                        public void onResponse(Call<Answer> call, Response<Answer> response) {
//
//                            if (response.body().getError() == null
//                                    && response.body().getData() != null) {
//                                Intent intent = new Intent(Login.this, AllProducts.class);
//                                intent.putExtra("sessionKey", response.body().getData().getSessionKey().toString());
//                                startActivity(intent);
//                            } else {
//                                if (response.body().getError() != null) {
//                                    Toast.makeText(Login.this, response.body().getError().getErrorMessage()
//                                            , Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Answer> call, Throwable t) {
//                            Toast.makeText(Login.this, "Проверьте подключение к интернету"
//                                    , Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private  String loadJSONFromAsset(String file_name) {
        String json = null;
        try {

            InputStream is = getAssets().open(file_name);

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

    private boolean checkForm(String login,String password){
        boolean isCorrect = true;
        String toastMessedge = "";

        if(login == null || password == null || login.isEmpty() || password.isEmpty()){
            toastMessedge += "Заполните все поля";
            isCorrect = false;
        }

        if (isCorrect == false) Toast.makeText(Login.this, toastMessedge, Toast.LENGTH_SHORT).show();
        return isCorrect;
    }
}
