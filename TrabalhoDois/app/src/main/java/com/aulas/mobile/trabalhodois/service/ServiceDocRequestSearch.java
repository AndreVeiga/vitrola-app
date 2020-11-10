package com.aulas.mobile.trabalhodois.service;

import android.content.Context;

import com.aulas.mobile.trabalhodois.dao.DaoDocRequestSearch;
import com.aulas.mobile.trabalhodois.model.db.DocRequestSearchModel;
import com.aulas.mobile.trabalhodois.rest.DocRequestSearch;

import java.util.ArrayList;
import java.util.List;

public class ServiceDocRequestSearch {

    private DaoDocRequestSearch daoDocRequestSearch;

    public ServiceDocRequestSearch(Context context){
        this.daoDocRequestSearch = new DaoDocRequestSearch(context);
    }

    public void saveListDocRequestSearch(List<DocRequestSearch> docs){
        for (DocRequestSearch doc: docs){
            DocRequestSearchModel byId = this.daoDocRequestSearch.getById(doc.getId());
            if(byId == null) {
                this.daoDocRequestSearch.insertData(convertModel(doc));
            }
        }
    }

    public List<DocRequestSearch> getAllDocRequestSearch(){
        List<DocRequestSearchModel> all = this.daoDocRequestSearch.getAll();
        List<DocRequestSearch> drsList = new ArrayList<>();
        if(all != null && !all.isEmpty()){
            for (DocRequestSearchModel model: all){
                drsList.add(convertRequest(model));
            }
        }

        return drsList;
    }

    public List<DocRequestSearch> getAllDocRequestSearch(String name){
        List<DocRequestSearchModel> all = this.daoDocRequestSearch.getAllByName(name);
        List<DocRequestSearch> drsList = new ArrayList<>();
        if(all != null && !all.isEmpty()){
            for (DocRequestSearchModel model: all){
                drsList.add(convertRequest(model));
            }
        }
        return drsList;

    }

    private DocRequestSearch convertRequest(DocRequestSearchModel model){
        DocRequestSearch drs = new DocRequestSearch();
        drs.setId(model.getId());
        drs.setBand(model.getBand());
        drs.setUrl(model.getUrl());
        return drs;
    }

    private DocRequestSearchModel convertModel(DocRequestSearch drs){
        DocRequestSearchModel model = new DocRequestSearchModel();
        model.setId(drs.getId());
        model.setBand(drs.getBand());
        model.setUrl(drs.getUrl());
        return model;
    }
}
