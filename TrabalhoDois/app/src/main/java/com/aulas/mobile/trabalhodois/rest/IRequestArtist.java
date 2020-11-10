package com.aulas.mobile.trabalhodois.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRequestArtist {

    @GET("{artist}/index.js")
    Call<ResponseArtist> listArtist(@Path("artist") String artist);

    @GET("search.art")
    Call<ResponseSearchArtist> searchArtists(@Query("q") String name, @Query("limit") int limit);

    @GET("{url}")
    Call<String> searchMusic(@Path("url") String url);
}
