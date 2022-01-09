package com.satyam.moviezpick.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.satyam.moviezpick.models.MovieModel;

import java.util.List;

//This class is for getting multiple movies (movie list)
public class MovieSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private  int total_Count;
    @SerializedName("results")
    @Expose()

    private List<MovieModel> movies;
    public int getCount(){
        return total_Count;
    }
public List<MovieModel> getMovies(){
        return movies;
}

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_Count=" + total_Count +
                ", movies=" + movies +
                '}';
    }
}
