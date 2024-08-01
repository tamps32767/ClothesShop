package com.example.clothesshop.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clothesshop.DbHelper;

public class UserDao {
    DbHelper dbHelper;
    public UserDao(Context context){
        dbHelper = new DbHelper(context);

    }

    public boolean checkDangNhap(String email,String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE email = ? AND password = ? ", new String[]{email,password});
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
}
