package com.aulas.mobile.trabalhodois.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aulas.mobile.trabalhodois.model.db.DocRequestSearchModel;
import com.aulas.mobile.trabalhodois.model.db.ItemArtistModel;

import java.util.ArrayList;
import java.util.List;

public class DaoItemArtist extends SQLiteOpenHelper {

    private final static String TABLE = "ItemArtist";
    private final static int VERSION = 4;
    private final String ID = "id";
    private final String URL = "url";
    private final String DESC = "desc";

    public DaoItemArtist(@Nullable Context context) {
        super(context, TABLE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query
                .append("CREATE TABLE ")
                .append(TABLE).append(" ( ")
                .append(ID).append(" TEXT, ")
                .append(URL)
                .append(" TEXT, ")
                .append(DESC)
                .append(" TEXT ); ");
        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertData(ItemArtistModel model) {
        getWritableDatabase().insert(TABLE, null, getContentValue(model));
    }

    public List<ItemArtistModel> getAllById(String id) {
        StringBuilder query = new StringBuilder();
        query
                .append("SELECT * FROM ")
                .append(TABLE)
                .append(" WHERE ")
                .append(ID)
                .append(" = ? ;");
        String [] arguments = { id };
        Cursor cursor = getReadableDatabase().rawQuery(query.toString(), arguments);
        List<ItemArtistModel> list = new ArrayList<>();

        while(cursor.moveToNext()){
            ItemArtistModel model = new ItemArtistModel();
            model.setId(cursor.getString(cursor.getColumnIndex(ID)));
            model.setDesc(cursor.getString(cursor.getColumnIndex(DESC)));
            model.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
            list.add(model);
        }

        cursor.close();
        return list;
    }

    private ContentValues getContentValue(ItemArtistModel model){
        ContentValues cv = new ContentValues();
        cv.put(URL, model.getUrl());
        cv.put(DESC, model.getDesc());
        cv.put(ID, model.getId());
        return cv;
    }
}
