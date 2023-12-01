package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.Hang;

import java.util.ArrayList;
import java.util.List;

public class DaoHang {
    private SQLiteDatabase db;

    public DaoHang(Context context) {
        DbHelper DbHelper = new DbHelper(context);
        db = DbHelper.getWritableDatabase();
    }

    public long insert(Hang obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",obj.getMaHang());
        contentValues.put("tenLoai",obj.getTenHang());


        return db.insert("Hang",null,contentValues);
    }

    public long update(Hang obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHang",obj.getMaHang());
        contentValues.put("tenHang",obj.getTenHang());

        return db.update("Hang",contentValues,"mahang = ?",new String[]{String.valueOf(obj.getMaHang())});
    }

    public int delete(String id) {
        return db.delete("Hang","maHang = ?",new String[]{String.valueOf(id)});
    }

    private List<Hang> getData(String sql, String ... selectionArgs) {
        List<Hang> lstLoai = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstLoai.add(new Hang(
                    cursor.getString(0),
                    cursor.getString(1)
            ));
        }
        return lstLoai;
    }

    public Hang getID (String id) {
        String sql = "SELECT * FROM Hang WHERE maHang = ?";
        List<Hang> lstLS = getData(sql,id);
        return lstLS.get(0);
    }

    public List<Hang> getAll() {
        String sql = "SELECT * FROM Hang";
        return getData(sql);
    }

    public boolean checkID(String fieldValue) {
        String Query = "Select * from Hang where maHang = " + fieldValue;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
