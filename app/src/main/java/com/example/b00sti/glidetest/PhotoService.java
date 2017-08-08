package com.example.b00sti.glidetest;

import com.example.b00sti.glidetest.model.Pictures;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by b00sti on 08.08.2017
 */

public interface PhotoService {

    @GET("?key=4379520-3814eb95c81ae3ed5901032de&q=chelsea&image_type=photo")
    Call<Pictures> getPhotos();
}
