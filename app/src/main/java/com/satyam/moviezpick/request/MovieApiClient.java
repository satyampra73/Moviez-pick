package com.satyam.moviezpick.request;

import androidx.lifecycle.MutableLiveData;

import com.satyam.moviezpick.AppExecuters;
import com.satyam.moviezpick.models.MovieModel;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies;
    private static MovieApiClient instance;

    public static MovieApiClient getInstance() {
        if (instance==null){
            instance=new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies=new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public void movieSearchApi(){
        final Future myHandler= AppExecuters.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
            //retrive data from Api
            }
        });

        AppExecuters.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling for retrofit call
                myHandler.cancel(true);
            }
        },5000,TimeUnit.MICROSECONDS);
    }



}
