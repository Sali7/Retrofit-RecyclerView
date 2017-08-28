package com.swift.sight.retrofit.network;

import com.swift.sight.retrofit.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    /**
     *
     * @param whichList string value of the list of movies we want to get.
     * @return List of movies.
     */
    @GET("movies/{which_list}")
    Call<MoviesResponse> getMovies(@Path("which_list") String whichList);
}
