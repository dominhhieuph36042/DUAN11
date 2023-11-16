package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class DaoNhanVien {

    private SQLiteDatabase db;

    public DaoNhanVien(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(NhanVien obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNV", obj.getMaNV());
        contentValues.put("hoten",obj.getHoTen());
        contentValues.put("dienThoai",obj.getDienThoai());
        contentValues.put("diaChi",obj.getDiaChi());
        contentValues.put("namSinh",obj.getNamSinh());
        contentValues.put("taiKhoan",obj.getTaiKhoan());
        contentValues.put("matKhau",obj.getMatKhau());

        return db.insert("NhanVien",null,contentValues);
    }

    public long update(NhanVien  obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNV", obj.getMaNV());
        contentValues.put("hoten",obj.getHoTen());
        contentValues.put("dienThoai",obj.getDienThoai());
        contentValues.put("diaChi",obj.getDiaChi());
        contentValues.put("namSinh",obj.getNamSinh());
        contentValues.put("taiKhoan",obj.getTaiKhoan());
        contentValues.put("matKhau",obj.getMatKhau());
        return db.update("NhanVien",contentValues,"maNV = ?",new String[]{String.valueOf(obj.getMaNV())});
    }

    public int delete(String id) {
        return db.delete("NhanVien","maNV = ?",new String[]{String.valueOf(id)});
    }

    private List<NhanVien> getData(String sql, String ... selectionArgs) {
        List<NhanVien> lstNV = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {

                lstNV.add(new NhanVien(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)

                ));


        }
        return lstNV;
    }

    public NhanVien getID (String id) {
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        List<NhanVien> lstNV = getData(sql,id);
        return lstNV.get(0);
    }

    public List<NhanVien> getAll() {
        String sql = "SELECT * FROM NHANVIEN";
        return getData(sql);
    }




}
