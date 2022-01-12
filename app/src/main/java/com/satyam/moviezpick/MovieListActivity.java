package com.satyam.moviezpick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.satyam.moviezpick.adapters.MovieRecyclerView;
import com.satyam.moviezpick.adapters.OnMovieListener;
import com.satyam.moviezpick.models.MovieModel;
import com.satyam.moviezpick.request.Servicey;
import com.satyam.moviezpick.response.MovieSearchResponse;
import com.satyam.moviezpick.utils.Credentials;
import com.satyam.moviezpick.utils.MovieApi;
import com.satyam.moviezpick.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {
    private RecyclerView recyclerView;
    //View Model
    private MovieListViewModel movieListViewModel;
    private MovieRecyclerView movieRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        ConfigureRecyclerView();
        observeAnyChange();
        searchMovieApi("iron",1);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchMovieApi("Fast",10);
//            }
//        });

    }

    private void observeAnyChange() {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //Observing for Any Data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        //get the data in log
                        Log.v("tagy", "on changed: " + movieModel.getTitle());
                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    //calling method in Main Activity

    public void searchMovieApi(String query, int pageNumber) {
        movieListViewModel.searchMovieApi(query, pageNumber);
    }

    private void GetRetrofitResponse() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(
                Credentials.Api_key,
                "Sing 2",
                1
        );
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    // Log.v("pra", "The response is: " + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for (MovieModel movie : movies) {
                        Log.v("pra", "The release date :" + movie.getRelease_date() + "\n movie name" + movie.getTitle());
                    }
                } else {
                    Log.v("pra", "Error Message" + response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Toast.makeText(MovieListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetRetrofitResponseAccordingToId() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                550,
                Credentials.Api_key
        );
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200) {
                    MovieModel movie = response.body();
                    Log.v("pra", "Movie Poster: " + movie.getTitle());
                } else {
                    Log.v("pra", "Error Message" + response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    private void ConfigureRecyclerView() {
        movieRecyclerAdapter = new MovieRecyclerView(this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}