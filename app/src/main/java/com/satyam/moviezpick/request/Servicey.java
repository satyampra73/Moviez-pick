package com.satyam.moviezpick.request;

import static com.satyam.moviezpick.utils.Credentials.BaseUrl;

import com.satyam.moviezpick.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {
    private static Retrofit.Builder retrofitBuilder=new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit=retrofitBuilder.build();
    private static MovieApi movieApi=retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi(){
        return movieApi;
    }
}
