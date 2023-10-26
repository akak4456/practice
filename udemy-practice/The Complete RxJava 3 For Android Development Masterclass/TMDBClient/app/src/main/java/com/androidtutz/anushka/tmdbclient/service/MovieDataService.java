package com.androidtutz.anushka.tmdbclient.service;

import com.androidtutz.anushka.tmdbclient.model.MovieDBResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by K. A. ANUSHKA MADUSANKA on 7/9/2018.
 */
public interface MovieDataService {
    @GET("movie/popular")
    Observable<MovieDBResponse> getPopularMoviesWithRx(@Query("api_key") String apiKey);
}
