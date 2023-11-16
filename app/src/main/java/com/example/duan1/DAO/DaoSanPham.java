package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DaoSanPham {
    private SQLiteDatabase db;

    public DaoSanPham(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSP",obj.getMaSP());
        contentValues.put("maHang",obj.getMaHang());
        contentValues.put("tenSP",obj.getTenSP());
        contentValues.put("phanLoai",obj.getPhanLoai());
        contentValues.put("tinhTrang",obj.getTinhTrang());
        contentValues.put("giaTien",obj.getGiaTien());
        contentValues.put("trangThai",obj.getTrangThai());

        return db.insert("SanPham",null,contentValues);
    }

    public long update(SanPham obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSP",obj.getMaSP());
        contentValues.put("maHang",obj.getMaHang());
        contentValues.put("tenSP",obj.getTenSP());
        contentValues.put("phanLoai",obj.getPhanLoai());
        contentValues.put("tinhTrang",obj.getTinhTrang());
        contentValues.put("giaTien",obj.getGiaTien());
        contentValues.put("trangThai",obj.getTrangThai());


        return db.update("SanPham",contentValues,"maSP = ?",new String[]{String.valueOf(obj.getMaSP())});
    }

    public int delete(String id) {
        return db.delete("SanPham","maSP = ?",new String[]{String.valueOf(id)});
    }

    private List<SanPham> getData(String sql, String ... selectionArgs) {
        List<SanPham> lstSach = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstSach.add(new SanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    Integer.parseInt(cursor.getString(5)),
                    cursor.getString(6)
            ));
        }
        return lstSach;
    }
    public SanPham getID (String id) {
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        List<SanPham> lstTT = getData(sql,id);
        return lstTT.get(0);
    }

    public List<SanPham> getAll() {
        String sql = "SELECT * FROM SANPHAM";
        return getData(sql);
    }

    public boolean checkID(String fieldValue) {
        String Query = "Select * from SanPham where maSP = " + fieldValue;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
