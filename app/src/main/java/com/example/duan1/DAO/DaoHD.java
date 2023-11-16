package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class DaoHD {

    private SQLiteDatabase db;

    public DaoHD(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHD",obj.getMaHD());
        contentValues.put("maNV",obj.getMaNV());
        contentValues.put("maKH",obj.getMaKH());
        contentValues.put("phanLoai",obj.getPhanLoai());
        contentValues.put("ngay",obj.getNgay());
        contentValues.put("trangThai",obj.getTrangThai());

        return db.insert("HoaDon",null,contentValues);
    }

    public long update(HoaDon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHD",obj.getMaHD());
        contentValues.put("maNV",obj.getMaNV());
        contentValues.put("maKH",obj.getMaKH());
        contentValues.put("phanLoai",obj.getPhanLoai());
        contentValues.put("ngay",obj.getNgay());
        contentValues.put("trangThai",obj.getTrangThai());

        return db.update("HoaDon",contentValues,"maHD = ?",new String[]{String.valueOf(obj.getMaHD())});
    }

    public int delete(String id) {
        return db.delete("HoaDon","maHD = ?",new String[]{String.valueOf(id)});
    }

    private List<HoaDon> getData(String sql, String ... selectionArgs) {
        List<HoaDon> lstPM = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstPM.add(new HoaDon(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)),
                    cursor.getString(4),
                    cursor.getString(5)

            ));
        }
        return lstPM;
    }

    public HoaDon getID (String id) {
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
        List<HoaDon> lstTV = getData(sql,id);
        return lstTV.get(0);

    }

    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM HOADON";
        return getData(sql);
    }
    public boolean checkID(String id,String value) {
        String Query = "Select * from HoaDon where " + id +  " = " + value;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
