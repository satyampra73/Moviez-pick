package com.satyam.moviezpick.request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.satyam.moviezpick.AppExecuters;
import com.satyam.moviezpick.models.MovieModel;
import com.satyam.moviezpick.response.MovieSearchResponse;
import com.satyam.moviezpick.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies;
    private static MovieApiClient instance;
    //Making global Runnable
    private RetrieveMoviesRunnable retrieveMoviesRunnable;


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

    //This method will going to be called as step in classes
    public void searchMovieApi(String query, int pageNumber){
        if(retrieveMoviesRunnable!=null){
            retrieveMoviesRunnable=null;
        }
        retrieveMoviesRunnable=new RetrieveMoviesRunnable(query,pageNumber);
        final Future myHandler= AppExecuters.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecuters.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling for retrofit call
                myHandler.cancel(true);
            }
        },3000,TimeUnit.MILLISECONDS);
    }

    //Retrieving data from Api Through Runnalble Class
    private class RetrieveMoviesRunnable implements Runnable{

        private final String query;
        private final int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }


        @Override
        public void run() {
            //Getting the response objects

            try {
                Response response=getMovies(query,pageNumber).execute();
                if (cancelRequest){
                    return;
                }

                if(response.code()==200){
                    List<MovieModel> list=new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber != 0){
                        //sending data to live data
                        //post value:used for background thread
                        mMovies.postValue(list);
                    }
                    else {
                        List<MovieModel> currentMovies=mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }
                else {
                    String error=response.errorBody().toString();
                    Log.e("pra","error is: "+error);
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }


        }
        private Call<MovieSearchResponse> getMovies(String query,int pageNumber){
            return Servicey.getMovieApi().searchMovie(
                    Credentials.Api_key,
                    query,
                    pageNumber
            );
        }

        private void cancelRequest(){
            Log.v("pra","Cancelling Request");
            cancelRequest=true;
        }

    }





}
