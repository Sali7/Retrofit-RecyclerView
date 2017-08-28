package com.swift.sight.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("image")
    private String mImageURL;

    @SerializedName("year")
    private String mProductionYear;

    public Movie(String title, String imageURL, String year) {
        this.mTitle = title;
        this.mImageURL = imageURL;
        this.mProductionYear = year;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        this.mImageURL = imageURL;
    }

    public String getProductionYear() {
        return mProductionYear;
    }

    public void setProductionYear(String productionYear) {
        this.mProductionYear = productionYear;
    }

    @Override
    public String toString() {
        return "Movie{" +
                ", Title='" + mTitle + '\'' +
                ", Image_URL='" + mImageURL + '\'' +
                ", Production_Year='" + mProductionYear + '\'' +
                '}';
    }
}
