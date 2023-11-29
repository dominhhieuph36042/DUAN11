package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.ChiTietHoaDon;
import com.example.duan1.Model.HoaDon;
import com.example.duan1.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DaoHoaDon {

    private DbHelper dbHelper;
    ChiTietHoaDon cthdlDAO;
    public DaoHoaDon(Context context){
        this.dbHelper = new DbHelper(context);
        cthdlDAO = new ChiTietHoaDon();
    }

    public long insertHoaDon(HoaDon obj){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idHoaDon", obj.getIdDonHang());
        values.put("maKH", obj.getMaKH());
        values.put("tongTien", obj.getTongTien());
        values.put("ngayDat", obj.getNgayDat());

        return db.insert("HoaDon",null,values);
    }

    public List<HoaDon> getAll(){
        String sql ="SELECT * FROM HoaDon";
        return getData(sql);
    }

    public HoaDon getID (String idHoaDon) {
        String sql = "SELECT * FROM HoaDon WHERE idHoaDon = ?";
        List<HoaDon> lstTT = getData(sql,idHoaDon);
        return lstTT.get(0);
    }

    public List<HoaDon> getData(String sql, String... selectionArgs){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<HoaDon> list =  new ArrayList<>();

        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            list.add(new HoaDon(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getString(3)
            ));
        }
        if (c != null){
            c.close();
        }
        if (db != null){
            db.close();
        }
        return list;
    }

}
