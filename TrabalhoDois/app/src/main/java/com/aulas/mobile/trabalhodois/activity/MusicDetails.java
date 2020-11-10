package com.aulas.mobile.trabalhodois.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.aulas.mobile.trabalhodois.R;
import com.aulas.mobile.trabalhodois.common.URL;
import com.aulas.mobile.trabalhodois.rest.IRequestArtist;
import com.aulas.mobile.trabalhodois.rest.ResponseArtist;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicDetails extends AppCompatActivity {

    private WebView webViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        webViewResult = findViewById(R.id.webViewResult);
        Intent intent = getIntent();
        loadDataInWebView(intent.getStringExtra("url"));
    }

    private void loadDataInWebView(String url){
        webViewResult.loadUrl(url);
        WebSettings webSettings = webViewResult.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}