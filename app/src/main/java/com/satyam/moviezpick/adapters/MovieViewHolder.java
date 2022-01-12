package com.satyam.moviezpick.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satyam.moviezpick.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title,duration, release_date;
    ImageView imageView;
    RatingBar ratingBar;
    //click Listener
    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView,OnMovieListener onMovieListener) {
        super(itemView);
        title=itemView.findViewById(R.id.movieTitle);
        duration=itemView.findViewById(R.id.movie_duration);
        release_date =itemView.findViewById(R.id.movie_category);

        imageView=itemView.findViewById(R.id.movie_img);
        ratingBar=itemView.findViewById(R.id.ratingbar);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());

    }
}
