package com.aulas.mobile.trabalhodois.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aulas.mobile.trabalhodois.R;
import com.aulas.mobile.trabalhodois.rest.DocRequestSearch;
import com.aulas.mobile.trabalhodois.service.RequestServiceDocRequestSearch;

import java.lang.reflect.Method;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private List<DocRequestSearch> artists;
    private Context context;
    private Method method;
    private RequestServiceDocRequestSearch requestServiceDocRequestSearch;

    public ArtistAdapter(List<DocRequestSearch> artists, Context context, Method method) {
        this.artists = artists;
        this.context = context;
        this.method = method;
        this.requestServiceDocRequestSearch = new RequestServiceDocRequestSearch(context);
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        DocRequestSearch artist = this.artists.get(position);
        holder.bind(artist);
        holder.itemView.setOnClickListener(v -> {
            requestSearchArtist(artist);
        });
    }

    private void requestSearchArtist(DocRequestSearch docRequestSearch){ requestServiceDocRequestSearch.request(docRequestSearch, method, context); }

    @Override
    public int getItemCount() { return this.artists.size(); }

    class ArtistViewHolder extends RecyclerView.ViewHolder {
        private TextView desc;
        private TextView url;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.artist_desc);
            url = itemView.findViewById(R.id.artist_url);
        }

        public void bind(DocRequestSearch artist) {
            desc.setText("Banda: " + artist.getBand());
            url.setText("URL: " + artist.getUrl());
        }
    }
}
