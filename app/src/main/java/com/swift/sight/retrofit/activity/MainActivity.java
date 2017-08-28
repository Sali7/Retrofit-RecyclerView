package com.swift.sight.retrofit.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.List;
import com.swift.sight.retrofit.R;
import com.swift.sight.retrofit.adapter.MoviesAdapter;
import com.swift.sight.retrofit.model.Movie;
import com.swift.sight.retrofit.model.MoviesResponse;
import com.swift.sight.retrofit.network.ApiInterface;
import com.swift.sight.retrofit.network.MovieAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Movie> mListMovies;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Showing a progress bar while data are downloaded.
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(this.getResources().getString(R.string.progress_bar_text));
        pDialog.show();

        initData();
    }

    /**
     * Initiate all view component.
     */
    private void initView() {
        RecyclerView moviesRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);

        // Create adapter passing in the sample movie data
        MoviesAdapter adapter = new MoviesAdapter(this, mListMovies);
        // Attach the adapter to the recyclerview to populate items
        moviesRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setting divider for the recycler view.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(moviesRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        moviesRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void initData() {

        ApiInterface apiService =
                MovieAPI.getClient().create(ApiInterface.class);

        //Making the retrofit call
        //Passing "top" as parameter so we can get top movies list.
        Call<MoviesResponse> call = apiService.getMovies("top");
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                // response.code return the HTTP code result of the request.
                // You can test on this code in case of error to show the appropriate
                // error message for the user if any.
                int responseCode = response.code();

                mListMovies = response.body().getMovies();
                mListMovies.addAll(mListMovies);
                mListMovies.addAll(mListMovies);
                mListMovies.addAll(mListMovies);
                mListMovies.addAll(mListMovies);
                mListMovies.addAll(mListMovies);
                mListMovies.addAll(mListMovies);
                mListMovies.addAll(mListMovies);
                mListMovies.addAll(mListMovies);

                initView();

                //Dismiss progress dialog after downloading data and preparing the view.
                if (pDialog != null) {
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

                //Dismissing Dialog
                if (pDialog != null) {
                    pDialog.dismiss();
                }

                //TODO: We can add an info dialog to let the user know what's going wrong !
            }
        });
    }
}
