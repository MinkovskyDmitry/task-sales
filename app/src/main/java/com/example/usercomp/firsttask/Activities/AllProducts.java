package com.example.usercomp.firsttask.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usercomp.firsttask.Adapter.ProductAdapter;
import com.example.usercomp.firsttask.ClassesForRequests.Answer;
import com.example.usercomp.firsttask.ClassesForRequests.Category;
import com.example.usercomp.firsttask.JSON_Answers;
import com.example.usercomp.firsttask.R;
import com.example.usercomp.firsttask.WorkWithWeb.Request;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProducts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Category> listData;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        recyclerView = (RecyclerView) findViewById(R.id.activity_all_product_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_all_products_toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Answer answer = JSON_Answers.getAnswer_AllProdugts(AllProducts.this);

        if (answer.getError() == null && answer.getData() != null){

            listData = answer.getData().getCategories();
            adapter = new ProductAdapter(listData,AllProducts.this);
            recyclerView.setAdapter(adapter);
        }
        else{
            if (answer.getError() != null){
                Toast.makeText(AllProducts.this, answer.getError().getErrorMessage()
                        , Toast.LENGTH_SHORT).show();

            } else{
                Toast.makeText(AllProducts.this, "Проверьте подключение к интернету"
                        , Toast.LENGTH_SHORT).show();

            }
        }

//        listData.add(new Category( "http://www.edu-domoi.ru/wp-content/uploads/010042.jpg","Молоко"));
//        listData.add(new Category( "http://www.edu-domoi.ru/wp-content/uploads/010042.jpg","Кефир"));

       // listData = response.body().getData().getCategories();



//        Request.getApi().getAllProducts().enqueue(new Callback<Answer>() {
//            @Override
//            public void onResponse(Call<Answer> call, Response<Answer> response) {
//                if(response.body().getError() == null
//                        && response.body().getData() != null){
//                    listData = response.body().getData().getCategories();
//                    adapter = new ProductAdapter(listData,AllProducts.this);
//                    recyclerView.setAdapter(adapter);
//                }
//                else{
//                    if (response.body().getError() != null) {
//                        TextView tv = (TextView) findViewById(R.id.activity_all_products_TextView);
//                        tv.setText(response.body().getError().getErrorMessage());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Answer> call, Throwable t) {
//                TextView tv = (TextView) findViewById(R.id.activity_all_products_TextView);
//                tv.setText("Проверьте подключение к интернету");
//            }
//        });


    }
}
