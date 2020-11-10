package com.aulas.mobile.trabalhodois.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ItemArtist implements Serializable {
    private String id;
    private String desc;
    private String url;

    @Override
    public String toString(){
        return desc + " - " + url;
    }
}
