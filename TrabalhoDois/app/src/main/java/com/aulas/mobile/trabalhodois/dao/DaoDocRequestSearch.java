package com.aulas.mobile.trabalhodois.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.aulas.mobile.trabalhodois.model.db.DocRequestSearchModel;

import java.util.ArrayList;
import java.util.List;

public class DaoDocRequestSearch extends SQLiteOpenHelper {

    private final static String TABLE = "DocRequestSearch";
    private final static int VERSION = 4;
    private final String ID = "id";
    private final String URL = "url";
    private final String BAND = "band";
    Context context;

    public DaoDocRequestSearch(@Nullable Context context) {
        super(context, TABLE, null, VERSION);
        this.context = context;
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
                .append(BAND)
                .append(" TEXT ); ");
        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(DocRequestSearchModel model) {
        getWritableDatabase().insert(TABLE, null, getContentValue(model));
    }

    public DocRequestSearchModel getById(String id){
        StringBuilder query = new StringBuilder();
        query
                .append("SELECT * FROM ")
                .append(TABLE)
                .append(" WHERE ")
                .append(ID)
                .append(" = ? ;");
        String [] arguments = { id };
        Cursor cursor = getReadableDatabase().rawQuery(query.toString(), arguments);
        DocRequestSearchModel model = null;
        while(cursor.moveToFirst()){
            model = new DocRequestSearchModel();
            model.setId(cursor.getString(cursor.getColumnIndex(ID)));
            model.setBand(cursor.getString(cursor.getColumnIndex(BAND)));
            model.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
        }
        cursor.close();
        return model;
    }

    public List<DocRequestSearchModel> getAllByName(String name) {
        /*
        StringBuilder query = new StringBuilder();
        query
                .append("SELECT * FROM ")
                .append(TABLE)
                .append(" WHERE ")
                .append(BAND)
                .append(" LIKE ? ; ");

        String [] arguments = { "%" + name + "%" };

        Cursor cursor = getReadableDatabase().rawQuery(query.toString(), arguments);
        */

        String[] projection = { BAND, ID, URL };
        String selection =  BAND + " LIKE ? ";
        String[] selectionArgs = { "%" + name + "%" };

        Cursor cursor = getReadableDatabase().query(
                TABLE,
                projection,
                selection,
                selectionArgs,
                null,null,null );

        List<DocRequestSearchModel> list = new ArrayList<>();

        while(cursor.moveToNext()){
            DocRequestSearchModel model = new DocRequestSearchModel();
            model.setId(cursor.getString(cursor.getColumnIndex(ID)));
            model.setBand(cursor.getString(cursor.getColumnIndex(BAND)));
            model.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
            list.add(model);
        }

        cursor.close();
        return list;
    }

    public List<DocRequestSearchModel> getAll() {
        String sql = "SELECT * FROM DocRequestSearch; ";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        List<DocRequestSearchModel> list = new ArrayList<>();

        while(cursor.moveToNext()){
            DocRequestSearchModel model = new DocRequestSearchModel();
            model.setId(cursor.getString(cursor.getColumnIndex(ID)));
            model.setBand(cursor.getString(cursor.getColumnIndex(BAND)));
            model.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
            list.add(model);
        }

        cursor.close();
        return list;
    }

    private ContentValues getContentValue(DocRequestSearchModel model){
        ContentValues cv = new ContentValues();
        cv.put(URL, model.getUrl());
        cv.put(BAND, model.getBand());
        cv.put(ID, model.getId());
        return cv;
    }
}
