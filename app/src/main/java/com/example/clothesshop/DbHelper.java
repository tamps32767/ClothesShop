package com.example.clothesshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "ClothesShop", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbUser = "CREATE TABLE USER(userid text primary key, username text, email text, password text, phone text, adress text)";
        sqLiteDatabase.execSQL(dbUser);

        String dbAdmin = "CREATE TABLE ADMIN(adminid text primary key, username text, email text, password text, phone text)";
        sqLiteDatabase.execSQL(dbAdmin);

        String dbCategory = "CREATE TABLE CATEGORY(categoryid text primary key, name text)";
        sqLiteDatabase.execSQL(dbCategory);

        String dbProduct = "CREATE TABLE PRODUCT(productid text primary key, name text, description text, price text, image integer, quality text)";
        sqLiteDatabase.execSQL(dbProduct);


        //data
        sqLiteDatabase.execSQL("INSERT INTO PRODUCT VALUES (1, 'Váy', '', '50000', '', '30'),(2, 'Váy', '', '50000', '', '30'),(3, 'Váy', '', '50000', '', '30'),(4, 'Váy', '', '50000', '', '30')");
        sqLiteDatabase.execSQL("INSERT INTO USER VALUES(1, 'Tam', 'tam@gmail.com', '123', '1234567890', '123 Address St')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABlE IF EXISTS USER");
            sqLiteDatabase.execSQL("DROP TABlE IF EXISTS ADMIN");
            sqLiteDatabase.execSQL("DROP TABlE IF EXISTS CATEGORY");
            sqLiteDatabase.execSQL("DROP TABlE IF EXISTS PRODUCT");
            onCreate(sqLiteDatabase);
        }
    }
}
