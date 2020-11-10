package com.aulas.mobile.trabalhodois.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aulas.mobile.trabalhodois.R;
import com.aulas.mobile.trabalhodois.common.URL;
import com.aulas.mobile.trabalhodois.model.ItemArtist;
import com.aulas.mobile.trabalhodois.rest.ResponseArtist;
import com.aulas.mobile.trabalhodois.service.ServiceItemArtist;

import java.util.List;
import java.util.zip.Inflater;

public class ArtistMusics extends AppCompatActivity {

    private ResponseArtist responseArtist;
    private ListView listMusic;
    private List<ItemArtist> item;
    private ServiceItemArtist serviceItemArtist;
    private boolean isOpenInGoogleChrome = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_musics);

        listMusic = findViewById(R.id.list_music);
        serviceItemArtist = new ServiceItemArtist(this);

        loadData();
        loadMusic();
        loadClick();
    }

    private void loadClick(){
        listMusic.setOnItemClickListener((parent, view, position, id) -> {
            ItemArtist itemArtist = item.get(position);
            String url = itemArtist.getUrl();
            StringBuilder newUrl = new StringBuilder();
            newUrl.append(URL.baseMusic);
            newUrl.append(url);

            // Altere para false essa variavel ou use !
            // ver aquele erro.
            if(isOpenInGoogleChrome){
                Intent intentNav = new Intent(Intent.ACTION_VIEW);
                intentNav.setData(Uri.parse(newUrl.toString()));
                startActivity(intentNav);
            } else {
                Intent intent = new Intent(ArtistMusics.this, MusicDetails.class);
                intent.putExtra("url", newUrl.toString());
                startActivity(intent);
            }
        });
    }

    private void loadData(){
        Intent intent = getIntent();
        responseArtist = (ResponseArtist) intent.getSerializableExtra("responseArtist");
    }

    private void loadMusic(){
        if(responseArtist != null && responseArtist.getArtist() != null && responseArtist.getArtist().getToplyrics() != null){
            this.item = responseArtist.getArtist().getToplyrics().getItem();
            ArrayAdapter<ItemArtist> stringArrayAdapter = new ArrayAdapter<ItemArtist>(this, android.R.layout.simple_list_item_1, item) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    ItemArtist itemArtist = item.get(position);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.artist_item, parent, false);
                    TextView desc = view.findViewById(R.id.artist_desc);
                    desc.setText(itemArtist.getDesc());

                    TextView info = view.findViewById(R.id.artist_url);
                    info.setText(itemArtist.getUrl());

                    return view;
                }
            };

            serviceItemArtist.saveListItemArtist(this.item);
            listMusic.setAdapter(stringArrayAdapter);
        }
    }
}