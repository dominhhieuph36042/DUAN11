package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.CTSanPham;
import com.example.duan1.Model.GioHang;
import com.example.duan1.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DaoGioHang {
    private SQLiteDatabase db;
    private Context context;

    public DaoGioHang(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(GioHang obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSP",obj.getMaSP());
        contentValues.put("tenSP",obj.getTenSP());
        contentValues.put("tenHang",obj.getHangSP());
        contentValues.put("giaTien",obj.getGia());
        contentValues.put("soLuong",obj.getSoluong());


        return db.insert("GioHang",null,contentValues);
    }

    public int delete(String id) {
        return db.delete("GioHang","maSP = ?",new String[]{String.valueOf(id)});
    }

    private List<GioHang> getData(String sql, String ... selectionArgs) {
        List<GioHang> lstGioHang = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstGioHang.add(new GioHang(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5))
            ));
        }
        return lstGioHang;
    }

    public GioHang getID (String id) {
        String sql = "SELECT * FROM GioHang WHERE maSP = ?";
        List<GioHang> lsGioHang = getData(sql,id);
        return lsGioHang.get(0);
    }

    public List<GioHang> getAll() {
        String sql = "SELECT * FROM GioHang";
        return getData(sql);
    }

//    public List<GioHang> getGio(){
//       String sqlGio ="SELECT maSP, tenSP, giaSP FROM CTSP";
//       List<GioHang> lstGio = new ArrayList<>();
//       DaoSanPham dapCt = new DaoSanPham(context);
//        Cursor cursor = db.rawQuery(sqlGio,null);
//        while ((cursor.moveToNext())){
//            SanPham sach = dapCt.getID(cursor.getString(0));
//            lstGio.add(new GioHang(
//                    sach.getMaSP(),
//                    sach.getTenSP(),
//                    sach.getTenHang(),
//                    sach.getGiaTien(),
//                    Integer.parseInt(cursor.getString(1))
//            ));
//        }
//        return lstGio;
//    }
}
