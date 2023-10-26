package com.androidtutz.anushka.postapp.service;

import com.androidtutz.anushka.postapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by K. A. ANUSHKA MADUSANKA on 6/25/2018.
 */
public interface PostAppService {


    @POST("posts")
    Call<User> getResult(@Body User user);
}
