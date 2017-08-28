package com.swift.sight.retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swift.sight.retrofit.R;
import com.swift.sight.retrofit.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private int mLastPosition = -1;

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private TextView movieTitle;
        private TextView productionYear;
        private ImageView movieThumbnail;

        //Used to clear animation.
        private View mRootView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        private MovieViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any MovieViewHolder instance.
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.movie_title_text_view);
            productionYear = (TextView) itemView.findViewById(R.id.movie_year_production);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.movie_thumbnail);

            // Make sure you are applying getRootView() method on a first level child in view hierarchy.
            mRootView = movieTitle.getRootView();
        }

        private void clearAnimation()
        {
            mRootView.clearAnimation();
        }
    }

    private List<Movie> movieList;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public MoviesAdapter(Context context, List<Movie> movies) {
        movieList = movies;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.movie_list_item, parent, false);

        // Return a new holder instance
        return new MovieViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MoviesAdapter.MovieViewHolder viewHolder, int position) {
        // Get the data model based on position
        Movie movie = movieList.get(position);

        // Set item views based on your views and data model
        TextView movieTextView = viewHolder.movieTitle;
        movieTextView.setText(movie.getTitle());

        TextView yearProductionTextView = viewHolder.productionYear;
        yearProductionTextView.setText(movie.getProductionYear());

        Picasso.with(getContext()).load(movie.getImageURL()).error(android.R.drawable.stat_notify_error).fit().into(viewHolder.movieThumbnail);

        setAnimation(viewHolder.itemView, position);
    }

    @Override
    public void onViewDetachedFromWindow(MovieViewHolder holder) {
        holder.clearAnimation();
        super.onViewDetachedFromWindow(holder);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > mLastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }
}