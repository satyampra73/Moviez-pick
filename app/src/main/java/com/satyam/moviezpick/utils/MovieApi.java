package com.satyam.moviezpick.utils;

import com.satyam.moviezpick.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    //Search for movies
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );
}
