package com.aulas.mobile.trabalhodois.service;

import android.content.Context;

import com.aulas.mobile.trabalhodois.dao.DaoItemArtist;
import com.aulas.mobile.trabalhodois.model.ItemArtist;
import com.aulas.mobile.trabalhodois.model.db.ItemArtistModel;

import java.util.ArrayList;
import java.util.List;


public class ServiceItemArtist {

    private DaoItemArtist daoItemArtist;

    public ServiceItemArtist(Context context){
        this.daoItemArtist = new DaoItemArtist(context);
    }

    public List<ItemArtist> getAllItemArtist(String id){
        List<ItemArtist> list = new ArrayList<>();
        List<ItemArtistModel> all = this.daoItemArtist.getAllById(id);
        for(ItemArtistModel model: all){
            list.add(convertRequest(model));
        }
        return list;
    }

    public void saveListItemArtist(List<ItemArtist> list){
        if(!list.isEmpty()) {
            List<ItemArtistModel> allById = this.daoItemArtist.getAllById(list.get(0).getId());
            if (allById.isEmpty()) {
                for (ItemArtist ia : list) {
                    this.daoItemArtist.insertData(convertModel(ia));
                }
            }
        }
    }

    private ItemArtist convertRequest(ItemArtistModel model){
        ItemArtist ia = new ItemArtist();
        ia.setId(model.getId());
        ia.setDesc(model.getDesc());
        ia.setUrl(model.getUrl());
        return ia;
    }

    private ItemArtistModel convertModel(ItemArtist ia){
        ItemArtistModel model = new ItemArtistModel();
        model.setId(ia.getId());
        model.setDesc(ia.getDesc());
        model.setUrl(ia.getUrl());
        return model;
    }
}
