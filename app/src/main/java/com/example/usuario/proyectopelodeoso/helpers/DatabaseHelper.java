package com.example.usuario.proyectopelodeoso.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.usuario.proyectopelodeoso.conf.Settings;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static class Columns implements BaseColumns {
        public static final String COLUMN_NAME_TITULO = "TITULO";
        public static final String COLUMN_NAME_PRECIO = "PRECIO";
        public static final String COLUMN_NAME_VENDIDOS = "VENDIDOS";
        public static final String COLUMN_NAME_IMAGEN = "IMAGEN";
    }

    public static final String PRODUCTS_TABLE_NAME = "PRODUCTS";
    private static final String PRODUCTS_SQL_CREATE_TABLE =
            "CREATE TABLE " + PRODUCTS_TABLE_NAME + " (" +
                    Columns._ID + " INTEGER PRIMARY KEY," +
                    Columns.COLUMN_NAME_TITULO + " TEXT, " +
                    Columns.COLUMN_NAME_PRECIO + " REAL, " +
                    Columns.COLUMN_NAME_IMAGEN + " TEXT, " +
                    Columns.COLUMN_NAME_VENDIDOS + " REAL)";
    private static final String PRODUCTS_SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME;



    public DatabaseHelper(Context context) {
        super(context, Settings.DATABASE_NAME, null, Settings.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(PRODUCTS_SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PRODUCTS_SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
