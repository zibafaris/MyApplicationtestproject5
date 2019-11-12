package com.example.myapplicationdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

class sqlhelper extends SQLiteOpenHelper {

    String TABLE_NAME = "FilmFinder";
    String Col2="Title";
    String Col3="Year";
    String Col4="Genre";
    String Col5="Director";
    String Col6="Writer";
    String Col7="Actors";
    String Col8="Plot";
    String Col9="Lang";
    String Col10="Country";
    String Col11="Poster";
    String Col12="Awards";
    String Co113="Rating";
    String Co114="Website";

    SQLiteDatabase sqLDatabase;
    public sqlhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Col2+" TEXT,"
                + Col3+" TEXT,"
                + Col4+" TEXT,"
                + Col5+" TEXT,"
                + Col6+" TEXT,"
                + Col7+" TEXT,"
                + Col8+" TEXT,"
                + Col9+" TEXT,"
                + Col10+" TEXT,"
                + Col11+" TEXT,"
                + Col12+" TEXT,"
                + Co113+" TEXT,"
                + Co114+" TEXT"

                + ")";
        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertFilm(String title, String year, String genre, String director, String writer, String actors, String plot, String lang, String country, String poster, String awards, String rating, String website){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2, title);
        contentValues.put(Col3, year);
        contentValues.put(Col4, genre);
        contentValues.put(Col5, director);
        contentValues.put(Col6, writer);
        contentValues.put(Col7, actors);
        contentValues.put(Col8, plot);
        contentValues.put(Col9, lang);
        contentValues.put(Col10, country);
        contentValues.put(Col11, poster);
        contentValues.put(Col12, awards);
        contentValues.put(Co113, rating);
        contentValues.put(Co114, website);

        database.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public List<DbModel> getFilmData(){
        List<DbModel> dic = new ArrayList<>();

        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String Title = cursor.getString(1);
                String Year = cursor.getString(2);
                String Genre = cursor.getString(3);
                String Director = cursor.getString(4);
                String Writer = cursor.getString(5);
                String Actors = cursor.getString(6);
                String Plot = cursor.getString(7);
                String Lang = cursor.getString(8);
                String Country = cursor.getString(9);
                String Poster = cursor.getString(10);
                String Awards = cursor.getString(11);
                String Rating = cursor.getString(12);
                String Website = cursor.getString(13);

                dic.add(new DbModel(id, Title, Year, Genre, Director, Writer, Actors, Plot, Lang, Country, Poster, Awards, Rating,Website));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dic;
    }
}
