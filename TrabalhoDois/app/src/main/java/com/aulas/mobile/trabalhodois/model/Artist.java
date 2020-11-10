package com.aulas.mobile.trabalhodois.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Artist implements Serializable {
    private Long identify;
    private String id;
    private String desc;
    private String url;
    private Rank rank;
    private List<Genre> genre;
    private TopLyrics toplyrics;
}
