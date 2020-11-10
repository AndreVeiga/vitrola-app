package com.aulas.mobile.trabalhodois.rest;

import com.aulas.mobile.trabalhodois.model.Artist;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseArtist implements Serializable {
    private Artist artist;
}
