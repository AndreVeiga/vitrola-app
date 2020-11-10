package com.aulas.mobile.trabalhodois.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class TopLyrics implements Serializable {
    private List<ItemArtist> item;
}
