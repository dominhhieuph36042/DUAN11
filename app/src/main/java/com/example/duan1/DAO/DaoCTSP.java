package com.example.duan1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.DbHelper;
import com.example.duan1.Model.CTSanPham;
import com.example.duan1.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DaoCTSP {

    private SQLiteDatabase db;

    public DaoCTSP(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    private List<CTSanPham> getData(String sql, String ... selectionArgs) {
        List<CTSanPham> lstCTSP = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstCTSP.add(new CTSanPham(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5)
            ));
        }
       return lstCTSP;
    }



    public List<CTSanPham> getAll() {
        String sql = "SELECT * FROM CTSP";
        return getData(sql);
    }
}
