package com.example.clothesshop.dao;

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
                list.add(new Product(cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}
