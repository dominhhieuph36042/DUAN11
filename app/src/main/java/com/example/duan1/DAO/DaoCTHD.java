package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.ChiTietHoaDon;

import java.util.ArrayList;
import java.util.List;

public class DaoCTHD {

    private SQLiteDatabase db;

    public DaoCTHD(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ChiTietHoaDon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CTHD",obj.getCTHD());
        contentValues.put("maHD",obj.getMaHD());
        contentValues.put("maSP",obj.getMaSP());
        contentValues.put("soLuong",obj.getSoLuong());
        contentValues.put("donGia",obj.getDonGia());
        contentValues.put("giamGia",obj.getGiamGia());
        contentValues.put("baoHanh",obj.getBaoHanh());

        return db.insert("ChiTietHoaDon",null,contentValues);
    }

    public long update(ChiTietHoaDon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CTHD",obj.getCTHD());
        contentValues.put("maHD",obj.getMaHD());
        contentValues.put("maSP",obj.getMaSP());
        contentValues.put("soLuong",obj.getSoLuong());
        contentValues.put("donGia",obj.getDonGia());
        contentValues.put("giamGia",obj.getGiamGia());
        contentValues.put("baoHanh",obj.getBaoHanh());

        return db.update("ChiTietHoaDon",contentValues,"CTHD= ?",new String[]{String.valueOf(obj.getMaHD())});
    }

    public int delete(String id) {
        return db.delete("ChiTietHoaDon","CTHD = ?",new String[]{String.valueOf(id)});
    }

    private List<ChiTietHoaDon> getData(String sql, String ... selectionArgs) {
        List<ChiTietHoaDon> lstPM = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstPM.add(new ChiTietHoaDon(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6))


            ));
        }

        return lstPM;
    }

    public ChiTietHoaDon getID (String id) {
        String sql = "SELECT * FROM ChiTietHoaDon WHERE CTHD = ?";
        List<ChiTietHoaDon> lstTV = getData(sql,id);
        return lstTV.get(0);

    }

    public List<ChiTietHoaDon> getAll() {
        String sql = "SELECT * FROM CHITIETHOADON";
        return getData(sql);
    }
    public boolean checkID(String id,String value) {
        String Query = "Select * from ChiTietHoaDon  where " + id +  " = " + value;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

}
