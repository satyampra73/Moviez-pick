package com.satyam.moviezpick.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.satyam.moviezpick.models.MovieModel;

//This class is for single movie request
public class MovieResponse {
    //finding the movie object
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
