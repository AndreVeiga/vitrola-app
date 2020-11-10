package com.aulas.mobile.trabalhodois.model.db;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocRequestSearchModel implements Serializable {
    private String id;
    private String url;
    private String band;
}
