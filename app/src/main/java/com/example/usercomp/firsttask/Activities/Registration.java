package com.example.usercomp.firsttask.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usercomp.firsttask.ChangedViews.MaskedEditText;
import com.example.usercomp.firsttask.ClassesForRequests.Answer;
import com.example.usercomp.firsttask.JSON_Answers;
import com.example.usercomp.firsttask.R;
import com.example.usercomp.firsttask.WorkWithWeb.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    private EditText editTextNameOfUser;
    private EditText editTextPassword;
    private EditText editTextRepeatPassword;
    private MaskedEditText editTextPhoneNumber;
    private Button buttonRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextNameOfUser = (EditText) findViewById(R.id.activity_registration_editText_nameOfUser);
        editTextPassword = (EditText) findViewById(R.id.activity_registration_editText_password);
        editTextRepeatPassword = (EditText) findViewById(R.id.activity_registration_editText_repeatPassword);
        editTextPhoneNumber = (MaskedEditText) findViewById(R.id.activity_registration_maskedEditText);
        buttonRegistration = (Button) findViewById(R.id.activity_registration_buttonRegistration);

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String login = editTextNameOfUser.getText().toString();
                String password = editTextPassword.getText().toString();
                String repPassword = editTextRepeatPassword.getText().toString();
                String phone = editTextPhoneNumber.getText().toString();

                if(checkForm(login,password,repPassword,phone)){

                    Answer answer = JSON_Answers.getAnswer_Registration(Registration.this);


                    if (answer.getError() == null && answer.getData() != null){

                        Intent intent = new Intent(Registration.this, AllProducts.class);
                        intent.putExtra("sessionKey", answer.getData().getSessionKey().toString());
                        startActivity(intent);
                    }
                    else{
                        if (answer.getError() != null){
                            Toast.makeText(Registration.this, answer.getError().getErrorMessage()
                                    , Toast.LENGTH_SHORT).show();

                        } else{
                            Toast.makeText(Registration.this, "Проверьте подключение к интернету"
                                    , Toast.LENGTH_SHORT).show();

                        }
                    }
//                    Request.getApi().registration(login,password,phone).enqueue(new Callback<Answer>() {
//                        @Override
//                        public void onResponse(Call<Answer> call, Response<Answer> response) {
//
//                            if (response.body().getError() == null
//                                    && response.body().getData() != null){
//                                Intent intent = new Intent(Registration.this,AllProducts.class);
//                                intent.putExtra("sessionKey",response.body().getData().getSessionKey().toString());
//                                startActivity(intent);
//                            }
//                            else{
//                                if (response.body().getError() != null)
//                                    Toast.makeText(Registration.this, response.body().getError().getErrorMessage()
//                                            , Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Answer> call, Throwable t) {
//                            Toast.makeText(Registration.this, "Проверьте подключение к интернету"
//                                    , Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
                }
            }
        });

    }
    private boolean checkForm(String login,String password, String repPassword,String phone){
        boolean isCorrect = true;
        String toastMessedge = "";

        if(login.isEmpty() || password.isEmpty() || repPassword.isEmpty() || phone.isEmpty() ){
            toastMessedge += "Заполните все поля";
            isCorrect = false;
        }
        if (password.equals(repPassword) != true){
            if (isCorrect == false) toastMessedge += "\n";
            toastMessedge += "Пароли должны совпадать";
            isCorrect = false;
        }
        String myphone = phone.substring(2,5)+phone.substring(6,8)+phone.substring(10);
        try
        {
            long c=Long.parseLong(myphone);
        }
        catch (Exception e)
        {
            if (isCorrect == false) toastMessedge += "\n";
            toastMessedge += "Введите правильный номер";
            isCorrect = false;

        }


        if (isCorrect == false) Toast.makeText(Registration.this, toastMessedge, Toast.LENGTH_SHORT).show();
        return isCorrect;
    }
}
