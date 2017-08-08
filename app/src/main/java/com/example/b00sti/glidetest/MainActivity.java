package com.example.b00sti.glidetest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.b00sti.glidetest.model.Hit;
import com.example.b00sti.glidetest.model.Pictures;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Hit> images = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        context = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoService service = retrofit.create(PhotoService.class);
        service.getPhotos().enqueue(new Callback<Pictures>() {
            @Override
            public void onResponse(Call<Pictures> call, Response<Pictures> response) {
                int statusCode = response.code();
                Log.d(TAG, "onResponse: " + statusCode);
                Pictures photo = response.body();
                Log.d("", "onResponse: " + photo.getHits());
                images = photo.getHits();
                mAdapter = new PhotoAdapter(images, context);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Pictures> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }
}
