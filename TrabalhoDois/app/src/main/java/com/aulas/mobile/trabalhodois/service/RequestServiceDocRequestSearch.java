package com.aulas.mobile.trabalhodois.service;

import android.content.Context;

import com.aulas.mobile.trabalhodois.common.URL;
import com.aulas.mobile.trabalhodois.dao.DaoDocRequestSearch;
import com.aulas.mobile.trabalhodois.model.db.DocRequestSearchModel;
import com.aulas.mobile.trabalhodois.rest.DocRequestSearch;
import com.aulas.mobile.trabalhodois.rest.IRequestArtist;
import com.aulas.mobile.trabalhodois.rest.ResponseArtist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestServiceDocRequestSearch {

    private ResponseArtist responseArtist;
    private DaoDocRequestSearch daoDocRequestSearch;

    public RequestServiceDocRequestSearch(Context context){
        this.daoDocRequestSearch = new DaoDocRequestSearch(context);
    }

    public void request(DocRequestSearch docRequestSearch, Method method, Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base)
                .addConverterFactory(GsonConverterFactory.create()).build();

        IRequestArtist service = retrofit.create(IRequestArtist.class);

        String url = docRequestSearch.getUrl().replace("/", "");
        Call<ResponseArtist> artistService = service.listArtist(url);

        artistService.enqueue(new Callback<ResponseArtist>() {
            public void onResponse(Call<ResponseArtist> call, Response<ResponseArtist> response) {
                if (response.isSuccessful()) {
                    responseArtist = response.body();
                    try {
                        method.invoke(context, responseArtist);
                    }
                    catch (IllegalAccessException e) { e.printStackTrace(); }
                    catch (InvocationTargetException e) { e.printStackTrace(); }
                }
            }
            public void onFailure(Call<ResponseArtist> call, Throwable exception) {
                exception.printStackTrace();
            }
        });
    }
}
