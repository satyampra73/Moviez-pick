package com.satyam.moviezpick.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.moviezpick.R;

public class Popular_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView title2,duration2, release_date2;
    ImageView imageView2;
    RatingBar ratingBar2;
    //click Listener
    OnMovieListener onMovieListener;

    public Popular_View_Holder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener=onMovieListener;

        title2=itemView.findViewById(R.id.movieTitle2);
        duration2=itemView.findViewById(R.id.movie_duration2);
        release_date2 =itemView.findViewById(R.id.movie_category2);
        imageView2=itemView.findViewById(R.id.movie_img2);
        ratingBar2=itemView.findViewById(R.id.ratingbar2);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());

    }
}
