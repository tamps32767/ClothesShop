package com.example.clothesshop.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clothesshop.DbHelper;

public class UserDao {
    DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    public UserDao(Context context){
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
    }

    public boolean checkDangNhap(String email,String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE email = ? AND password = ? ", new String[]{email,password});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", cursor.getString(2));
            editor.putString("username", cursor.getString(1));
            editor.putString("loaitaikhoan", cursor.getString(4));
            editor.commit();
            
            return true;
        }else {
            return false;
        }
    }

    public boolean checkDangKy(String email, String password, String name){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("username", name);

        long check = sqLiteDatabase.insert("USER", null, contentValues);
        return  check != -1;
    }
}
