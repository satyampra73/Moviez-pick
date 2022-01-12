package com.satyam.moviezpick.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.satyam.moviezpick.models.MovieModel;
import com.satyam.moviezpick.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    //this class is used for View Model
    MovieRepository movieRepository;
    public MovieListViewModel() {
       movieRepository=MovieRepository.getInstance();
    }
    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }
    public void searchMovieApi(String query, int pageNumber) {
        movieRepository.searchMovieApi(query, pageNumber);
    }
}
