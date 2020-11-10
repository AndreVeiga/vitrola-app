package com.aulas.mobile.trabalhodois.model.db;

import java.io.Serializable;

import lombok.Data;

@Data
public class ItemArtistModel implements Serializable {
    private String id;
    private String desc;
    private String url;
}
