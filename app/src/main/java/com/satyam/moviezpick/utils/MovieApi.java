package com.satyam.moviezpick.utils;

import com.satyam.moviezpick.models.MovieModel;
import com.satyam.moviezpick.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    //making search movie with id
    //https://api.themoviedb.org/3/movie/343611?api_key={api_key}
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,

            @Query("api_key") String api_key
    );

    //Get popular movies
    //https://api.themoviedb.org /3/movie/popular ?api_key=39c4a7edf1c085ac2385ce2e31cb8bbd&page=2
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page
    );


}
