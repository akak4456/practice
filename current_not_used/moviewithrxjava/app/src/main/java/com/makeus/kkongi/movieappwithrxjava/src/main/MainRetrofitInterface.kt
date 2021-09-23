package com.makeus.kkongi.movieappwithrxjava.src.main

import com.makeus.kkongi.movieappwithrxjava.src.main.domain.OneMovie
import com.makeus.kkongi.movieappwithrxjava.src.main.domain.Results
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainRetrofitInterface {
    @GET("/3/discover/movie")
    fun getMovieListByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") withGenres: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<Results>

    @GET("/3/movie/{movie_id}")
    fun getMovieOne(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<OneMovie>

    @GET("/3/trending/movie/day")
    fun getTodayTrend(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Results>
}