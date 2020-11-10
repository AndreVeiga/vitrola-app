package com.aulas.mobile.trabalhodois.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ResponseRequest {

    @SerializedName("docs")
    @Expose
    private List<DocRequestSearch> docs;
}
