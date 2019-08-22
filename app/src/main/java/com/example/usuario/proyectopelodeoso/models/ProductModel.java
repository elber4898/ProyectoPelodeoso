package com.example.usuario.proyectopelodeoso.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.proyectopelodeoso.helpers.DatabaseHelper;

import java.util.ArrayList;

public class ProductModel {
    private int ID;
    private String TITULO;
    private double PRECIO;
    private double VENDIDOS;
    private String IMAGEN;
    public static DatabaseHelper dbInstance;


    public ProductModel(int ID, String TITULO, double PRECIO, double VENDIDOS, String IMAGEN) {
        this.ID = ID;
        this.TITULO = TITULO;
        this.PRECIO = PRECIO;
        this.VENDIDOS = VENDIDOS;
        this.IMAGEN = IMAGEN;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public double getVENDIDOS() {
        return VENDIDOS;
    }

    public void setVENDIDOS(double VENDIDOS) {
        this.VENDIDOS = VENDIDOS;
    }

    public String getIMAGEN() {
        return IMAGEN;
    }

    public void setIMAGEN(String IMAGEN) {
        this.IMAGEN = IMAGEN;
    }

    public DatabaseHelper getDbInstance(Context _context) {
        if ( ProductModel.dbInstance == null ) {
            ProductModel.dbInstance = new DatabaseHelper(_context);
            return ProductModel.dbInstance;
        }
        return ProductModel.dbInstance;
    }


    public ArrayList<ProductModel> getAll(Context _context) {
        ArrayList<ProductModel> rows = new ArrayList<>();
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        String[] fields = new String[] {DatabaseHelper.Columns._ID,
                DatabaseHelper.Columns.COLUMN_NAME_TITULO,
                DatabaseHelper.Columns.COLUMN_NAME_IMAGEN,
                DatabaseHelper.Columns.COLUMN_NAME_PRECIO,
                DatabaseHelper.Columns.COLUMN_NAME_VENDIDOS};
        String[] args = new String[] {this.getID() + ""};

        Cursor c = db.query(DatabaseHelper.PRODUCTS_TABLE_NAME, fields,
                null, null,  null, null,
                DatabaseHelper.Columns._ID+" DESC");

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            do {
                ProductModel n = new ProductModel(c.getInt(0), c.getString(1), c.getDouble(2), c.getDouble(3), c.getString(4));
        /*        n.setID(c.getInt(0));
                n.setTITULO(c.getString(1));
                n.setPRECIO(c.getDouble(2));
                n.setVENDIDOS(c.getDouble(3));
                n.setIMAGEN(c.getString(4));*/
                rows.add(n);
            } while(c.moveToNext());
        }
        return rows;
    }


    public void setLocal(Context _context) {
        SQLiteDatabase db = this.getDbInstance(_context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Columns.COLUMN_NAME_TITULO, this.getTITULO());
        values.put(DatabaseHelper.Columns.COLUMN_NAME_PRECIO, this.getPRECIO());
        values.put(DatabaseHelper.Columns.COLUMN_NAME_VENDIDOS, this.getVENDIDOS());
        values.put(DatabaseHelper.Columns.COLUMN_NAME_IMAGEN, this.getIMAGEN());

        String[] args = new String[] {this.getID() + ""};

        if (this.ID == 0) {
            this.ID = this.getAll(_context).size() + 1;
        }
        long newRowId = db.insert(DatabaseHelper.PRODUCTS_TABLE_NAME, null, values);
    }


    public void getOne(Context _context) {
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        String[] fields = new String[] {DatabaseHelper.Columns._ID,
                DatabaseHelper.Columns.COLUMN_NAME_TITULO,
                DatabaseHelper.Columns.COLUMN_NAME_PRECIO,
                DatabaseHelper.Columns.COLUMN_NAME_VENDIDOS,
                DatabaseHelper.Columns.COLUMN_NAME_IMAGEN};
        String[] args = new String[] {this.getID() + ""};

        Cursor c = db.query(DatabaseHelper.PRODUCTS_TABLE_NAME, fields,
                null, null,  null, null, null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            do {
                this.setID(c.getInt(0));
                this.setTITULO(c.getString(1));
                this.setPRECIO(c.getDouble(2));
                this.setVENDIDOS(c.getDouble(3));
                this.setIMAGEN(c.getString(4));

            } while(c.moveToNext());
        }
    }



}
