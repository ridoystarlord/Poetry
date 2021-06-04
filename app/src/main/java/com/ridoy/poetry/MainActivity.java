package com.ridoy.poetry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ridoy.poetry.API.ApiClient;
import com.ridoy.poetry.Adapters.PoetryAdapter;
import com.ridoy.poetry.Models.Poetry;
import com.ridoy.poetry.Responses.GetPoetry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView main_RV;
    Toolbar toolbar;
    PoetryAdapter adapter;
    List<Poetry> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        toolbar = findViewById(R.id.main_toolbar);
        main_RV = findViewById(R.id.poetry_RV);

        adapter = new PoetryAdapter(this, list);
        main_RV.setAdapter(adapter);

        ApiClient.getClient().getPoetry().enqueue(new Callback<GetPoetry>() {
            @Override
            public void onResponse(Call<GetPoetry> call, Response<GetPoetry> response) {
                if (response != null) {
                    if (response.body().getStatus().equals("1")) {
                        list.addAll(response.body().getData());
                    } else {
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GetPoetry> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}