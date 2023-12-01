package com.example.duan1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.ChiTietHoaDon;

import java.util.ArrayList;
import java.util.List;

public class DaoCTHD {

    private DbHelper dbHelper;

    public DaoCTHD(Context context){
        this.dbHelper = new DbHelper(context);
    }


    public ChiTietHoaDon getID(int id) {
        String sql = "SELECT * FROM ChiTietHoaDon WHERE idCTHoaDon=?";
        List<ChiTietHoaDon> list = getData(sql, String.valueOf(id));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List<ChiTietHoaDon> getAllByIDHD(String id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ChiTietHoaDon> list = new ArrayList<>();

        String sql = "SELECT * FROM ChiTietHoaDon WHERE idHoaDon =?";

        Cursor c = db.rawQuery(sql, new String[]{id});

        while (c.moveToNext()){
            list.add(new ChiTietHoaDon(
                    c.getInt(0),
                    c.getInt(1),
                    c.getInt(2),
                    c.getInt(3),
                    c.getInt(4),
                    c.getString(5)
            ));
        }

        if (c != null) {
            c.close();
        }

        if (db != null) {
            db.close();
        }
        return list;
    }

    public boolean insert(ChiTietHoaDon object) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "INSERT INTO ChiTietHoaDon" + "( idHoaDon, maSP, soLuong, giaTien, note) " + " VALUES(?,?,?,?,?)";
        db.execSQL(sql, new String[]{String.valueOf(object.getIdHoaDon()),
                                     String.valueOf(object.getMaSP()),
                                     String.valueOf(object.getSoLuong()),
                                     String.valueOf(object.getGiaTien()),
                                     object.getNote()
        });

        Log.i("TAG", "Giá: " + object.getGiaTien());

        if (db != null) {
            db.close();
        }
        return true;
    }

    public List<ChiTietHoaDon> getAll() {
        String sql = "SELECT * FROM ChiTietHoaDon" ;
        List<ChiTietHoaDon> list = getData(sql);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }


    private List<ChiTietHoaDon> getData(String sql, String... selectionArgs){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ChiTietHoaDon> list = new ArrayList<>();

        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            list.add(new ChiTietHoaDon(
               c.getInt(0),
               c.getInt(1),
               c.getInt(2),
               c.getInt(3),
               c.getInt(4),
               c.getString(5)
            ));
        }

        if (c != null) {
            c.close();
        }
        if (db != null) {
            db.close();
        }
        return list;
    }
    }





