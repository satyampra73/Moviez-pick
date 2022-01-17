package com.satyam.moviezpick.repositories;

import androidx.lifecycle.MutableLiveData;

import com.satyam.moviezpick.models.MovieModel;
import com.satyam.moviezpick.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    //The class is acting as repository
    private static MovieRepository instance;
    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public MutableLiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }
    public MutableLiveData<List<MovieModel>> getMoviesPop() {
        return movieApiClient.getMoviesPop();
    }
    public void searchMovieApi(String query, int pageNumber) {
        movieApiClient.searchMovieApi(query, pageNumber);
    }
    public void searchMovieApiPop( int pageNumber) {
        movieApiClient.searchMovieApiPop(pageNumber);
    }
}
