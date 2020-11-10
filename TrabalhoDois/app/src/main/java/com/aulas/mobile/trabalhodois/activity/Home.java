package com.aulas.mobile.trabalhodois.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aulas.mobile.trabalhodois.R;
import com.aulas.mobile.trabalhodois.common.URL;
import com.aulas.mobile.trabalhodois.common.adapter.ArtistAdapter;
import com.aulas.mobile.trabalhodois.rest.ConnectionUtils;
import com.aulas.mobile.trabalhodois.rest.DocRequestSearch;
import com.aulas.mobile.trabalhodois.rest.IRequestArtist;
import com.aulas.mobile.trabalhodois.rest.ResponseArtist;
import com.aulas.mobile.trabalhodois.rest.ResponseSearchArtist;
import com.aulas.mobile.trabalhodois.service.ServiceDocRequestSearch;

import java.lang.reflect.Method;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {

    private RecyclerView listArtists;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private EditText txtSearch;

    private ResponseSearchArtist responseSearchArtist;
    private ServiceDocRequestSearch serviceDocRequestSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadComponents();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView username = findViewById(R.id.username);
        username.setText("Olá, " + name);

        layoutManager = new LinearLayoutManager(this);
        listArtists.setLayoutManager(layoutManager);

        responseSearchArtist = new ResponseSearchArtist();
        serviceDocRequestSearch = new ServiceDocRequestSearch(this);
        loadDataFromDB();
    }

    private void loadComponents() {
        listArtists = findViewById(R.id.list_artists);
        txtSearch = findViewById(R.id.txt_search);
    }

    private void loadDataFromDB(){
        List<DocRequestSearch> all = serviceDocRequestSearch.getAllDocRequestSearch();
        if(all != null && !all.isEmpty()){
            changeListArtist(all);
        }
    }

    private void changeListArtist(List<DocRequestSearch> list) {
        adapter = new ArtistAdapter(list, this, getMethod("openScreenArtistMusics"));
        listArtists.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void requestArtist(View view){
        if(txtSearch.getText() != null || !txtSearch.getText().toString().isEmpty()){
            getArtist(txtSearch.getText().toString());
        }
    }

    private void onSuccessRequest(){
        txtSearch.setText("");
        if(responseSearchArtist != null && responseSearchArtist.getResponse() != null &&
                responseSearchArtist.getResponse().getDocs() != null){
            changeListArtist(responseSearchArtist.getResponse().getDocs());
            saveListArtisit(responseSearchArtist.getResponse().getDocs());
        }
    }

    private void saveListArtisit(List<DocRequestSearch> docs) {
        serviceDocRequestSearch.saveListDocRequestSearch(docs);
    }

    private void getArtist(String name){

        if(!ConnectionUtils.isConnected(this)){
            Toast.makeText(this, "Sem Net", Toast.LENGTH_LONG).show();
            changeListArtist(serviceDocRequestSearch.getAllDocRequestSearch(name));
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.baseSearch)
                .addConverterFactory(GsonConverterFactory.create()).build();

        IRequestArtist service = retrofit.create(IRequestArtist.class);
        Call<ResponseSearchArtist> artistService = service.searchArtists(name, URL.LIMIT);

        artistService.enqueue(new Callback<ResponseSearchArtist>() {
            public void onResponse(Call<ResponseSearchArtist> call, Response<ResponseSearchArtist> response) {
                if (response.isSuccessful()) {
                    responseSearchArtist = response.body();
                    onSuccessRequest();
                }
            }
            public void onFailure(Call<ResponseSearchArtist> call, Throwable exception) {
                exception.printStackTrace();
            }
        });
    }

    public void openScreenArtistMusics(ResponseArtist responseArtist){
        Intent intent = new Intent(Home.this, ArtistMusics.class);
        intent.putExtra("responseArtist", responseArtist);
        startActivity(intent);
    }

    private Method getMethod(String methodName){
        Method method = null;
        try { method = Home.class.getDeclaredMethod(methodName, ResponseArtist.class); }
        catch (NoSuchMethodException e) { e.printStackTrace(); }
        return method;
    }
}
