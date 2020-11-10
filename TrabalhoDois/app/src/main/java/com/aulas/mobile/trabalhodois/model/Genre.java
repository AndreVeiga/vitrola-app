package com.aulas.mobile.trabalhodois.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Genre implements Serializable {
    private String name;
    private String url;
}
