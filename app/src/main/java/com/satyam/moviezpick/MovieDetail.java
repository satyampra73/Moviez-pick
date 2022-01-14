package com.satyam.moviezpick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.satyam.moviezpick.models.MovieModel;

public class MovieDetail extends AppCompatActivity {
    private ImageView imageViewDetails;
    private TextView titleTextDetails,descDetails;
    private RatingBar ratingBarDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        imageViewDetails=findViewById(R.id.imageViewDetails);
        titleTextDetails=findViewById(R.id.textviewTitleDetails);
        descDetails=findViewById(R.id.textviewDescDetails);
        ratingBarDetails=findViewById(R.id.ratingbar_details);
        
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")){
            MovieModel movieModel=getIntent().getParcelableExtra("movie");
            Log.v("toty","response gained"+movieModel.getTitle());
            titleTextDetails.setText(movieModel.getTitle());
            descDetails.setText(movieModel.getOverview());
            ratingBarDetails.setRating(movieModel.getVote_average()/2);

            Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movieModel.getPoster_path()).into(imageViewDetails);

        }
    }
}