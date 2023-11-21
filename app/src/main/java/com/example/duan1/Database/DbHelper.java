package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



    public class DbHelper extends SQLiteOpenHelper {
        public static final String DB_NAME = "MobileManager";
        public static final int VER_SION = 13;

        public DbHelper(Context context) {
            super(context, DB_NAME, null, VER_SION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        //QuanTriVien
            String createTableQuanTriVien =
                    "Create table QuanTriVien (" +
                            "maQTV TEXT PRIMARY KEY, " +
                            "hoTen TEXT NOT NULL, " +
                            "matKhau TEXT NOT NULL)";
            db.execSQL(createTableQuanTriVien);
            //Tạo bảng Nhân Viên

            //Tạo bảng Khách hàng
            String createTableKhachHang = "CREATE TABLE KhachHang(" +
                    "maKH TEXT NOT NULL UNIQUE PRIMARY KEY," +
                    "hoTen TEXT NOT NULL ," +
                    "dienThoai TEXT NOT NULL," +
                    "diaChi TEXT NOT NULL," +
                    "matKhau TEXT NOT NULL)";
            db.execSQL(createTableKhachHang);


            //Tạo bảng Hãng
            String createTableHang = "CREATE TABLE Hang(" +
                    "maHang TEXT NOT NULL UNIQUE PRIMARY KEY," +
                    "tenHang TEXT NOT NULL)" ;

            db.execSQL(createTableHang);
            //Tạo bảng Sản Phẩm
            String createTableSanPham = "CREATE TABLE SanPham(" +
                    "maSP INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSP TEXT NOT NULL," +
                    "tenHang TEXT NOT NULL," +
                    "moTa TEXT NOT NULL," +
                    "giaTien INTEGER NOT NULL," +
                    "images TEXT NOT NULL )" ;
            db.execSQL(createTableSanPham);



            String createTableCTSP = "CREATE TABLE CTSP(" +
                    "maCTSP TEXT PRIMARY KEY," +
                    "maSP INTEGER REFERENCES SanPham(maSP)," +
                    "tenCTSP TEXT NOT NULL," +
                    "hangCTSP TEXT NOT NULL," +
                    "giaTien INTEGER NOT NULL,"+
                    "moTaCTSP TEXT NOT NULL)";
            db.execSQL(createTableCTSP);




            //giỏ hàng
            String createTableGioHang = "CREATE TABLE GioHang(" +
                    "maGioHang INTEGER PRIMARY KEY," +
                    "maSP INTEGER REFERENCES SanPham(maSP)," +
                    "tenSP TEXT NOT NULL," +
                    "hangSP TEXT NOT NULL," +
                    "giaSP INTEGER NOT NULL," +
                    "soLuong INTEGER NOT NULL)";
            db.execSQL(createTableGioHang);


            //Tạo bảng hóa đơn
            String createTableHoaDon = "CREATE TABLE HoaDon(" +
                    "maHD TEXT NOT NULL UNIQUE PRIMARY KEY," +
                    "maNV TEXT NOT NULL REFERENCES NhanVien(maNV) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "maKH TEXT REFERENCES KhachHang(maKH) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "phanLoai INTEGER NOT NULL," +
                    "trangThai TEXT NOT NULL," +
                    "ngay TEXT NOT NULL)";
            db.execSQL(createTableHoaDon);
            //Tạo bảng Chi tiết HĐ
            String createTableChiTietHoaDon = "CREATE TABLE ChiTietHoaDon(" +
                    "maCTHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "maHD NOT NULL REFERENCES HoaDon(maHD) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "maSP NOT NULL REFERENCES SanPham(maSP) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "soLuong INTEGER NOT NULL," +
                    "giamGia INTEGER," +
                    "donGia TEXT NOT NULL," +
                    "baoHanh INTEGER)";
            db.execSQL(createTableChiTietHoaDon);

            String add_QuanTriVien = "INSERT INTO QuanTriVien VALUES" +
                    "('admin','Dung','admin')";
            db.execSQL(add_QuanTriVien);





        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Xóa bảng khi update VERSION
            String dropTableLoaiQuanTriVien = "Drop table if exists QuanTriVien";
            String dropTableLoaiKhachHang = "Drop table if exists KhachHang";
            String dropTableLoaiHang = "Drop table if exists Hang";
            String dropTableLoaiSanPham = "Drop table if exists SanPham";
            String dropTableLoaiHoaDon = "Drop table if exists HoaDon";
            String dropTableLoaiChiTietHoaDon = "Drop table if exists ChiTietHoaDon";

            String dropTableCTSanPham = "Drop table if exists CTSP";
            String dropTableGioHang = "Drop table if exists GioHang";

            if (oldVersion != newVersion) {
                db.execSQL(dropTableLoaiQuanTriVien);
               db.execSQL(dropTableLoaiKhachHang);
                db.execSQL(dropTableLoaiHang);
                db.execSQL(dropTableLoaiSanPham);
                db.execSQL(dropTableLoaiHoaDon);
                db.execSQL(dropTableLoaiChiTietHoaDon);
                db.execSQL(dropTableCTSanPham);
                db.execSQL(dropTableGioHang);
                onCreate(db);
            }
        }


    }


