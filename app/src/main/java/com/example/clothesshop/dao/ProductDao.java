package com.example.clothesshop.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clothesshop.DbHelper;
import com.example.clothesshop.model.Product;

import java.util.ArrayList;

public class ProductDao {
    DbHelper dbHelper;
    public ProductDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Product> getProduct(){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean themSP(Product product){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("price", product.getPrice());

        long check = sqLiteDatabase.insert("PRODUCT", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }

    public boolean suaSP(Product product){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("price", product.getPrice());

        int check = sqLiteDatabase.update("PRODUCT",  contentValues,"productid = ?", new String[]{String.valueOf(product.getProductid())});
        if (check == 0){
            return false;
        }
        return true;
    }

    public boolean xoaSP (Integer productid){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        int check = sqLiteDatabase.delete("PRODUCT","productid = ?", new String[]{String.valueOf(productid)});
        if (check <= 0){
            return false;
        }
        return true;
    }
}
