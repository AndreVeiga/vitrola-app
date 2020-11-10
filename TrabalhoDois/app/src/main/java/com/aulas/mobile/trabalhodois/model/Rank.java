package com.aulas.mobile.trabalhodois.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Rank implements Serializable {
   private Long pos;
   private String period;
   private Long views;
   private Long uniques;
   private String points;
}
