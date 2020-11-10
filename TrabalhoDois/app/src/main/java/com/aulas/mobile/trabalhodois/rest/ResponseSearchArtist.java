package com.aulas.mobile.trabalhodois.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ResponseSearchArtist {

    @SerializedName("response")
    @Expose
    private ResponseRequest response;
}
