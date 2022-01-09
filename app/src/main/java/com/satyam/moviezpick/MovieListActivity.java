package com.satyam.moviezpick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.satyam.moviezpick.models.MovieModel;
import com.satyam.moviezpick.request.Servicey;
import com.satyam.moviezpick.response.MovieSearchResponse;
import com.satyam.moviezpick.utils.Credentials;
import com.satyam.moviezpick.utils.MovieApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponse();
            }
        });
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
                        Log.v("pra", "The release date :" + movie.getRelease_date()+"\n movie name"+movie.getTitle());
                    }
                } else {
                    Log.v("pra", "Error Message" + response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Toast.makeText(MovieListActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}