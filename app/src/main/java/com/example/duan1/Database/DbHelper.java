package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



    public class DbHelper extends SQLiteOpenHelper {
        public static final String DB_NAME = "MobileManager";
        public static final int VER_SION = 16;

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
            String createTableNhanVien = "CREATE TABLE NhanVien(" +
                    "maNV TEXT NOT NULL UNIQUE PRIMARY KEY," +
                    "hoTen TEXT NOT NULL," +
                    "dienThoai TEXT NOT NULL," +
                    "diaChi TEXT," +
                    "namSinh TEXT," +
                    "taiKhoan TEXT NOT NULL," +
                    "matKhau TEXT NOT NULL)" ;

            db.execSQL(createTableNhanVien);
            //Tạo bảng Khách hàng
            String createTableKhachHang = "CREATE TABLE KhachHang(" +
                    "maKH TEXT NOT NULL UNIQUE PRIMARY KEY," +
                    "hoTen TEXT NOT NULL ," +
                    "dienThoai TEXT NOT NULL," +
                    "diaChi TEXT NOT NULL)";
            db.execSQL(createTableKhachHang);
            //Tạo bảng Hãng
            String createTableHang = "CREATE TABLE Hang(" +
                    "maHang TEXT NOT NULL UNIQUE PRIMARY KEY," +
                    "tenHang TEXT NOT NULL)" ;

            db.execSQL(createTableHang);
            //Tạo bảng Sản Phẩm
            String createTableSanPham = "CREATE TABLE SanPham(" +
                    "maSP TEXT NOT NULL UNIQUE PRIMARY KEY," +
                    "maHang TEXT NOT NULL REFERENCES Hang(maHang)ON DELETE CASCADE ON UPDATE CASCADE," +
                    "tenSP TEXT NOT NULL," +
                    "phanLoai TEXT NOT NULL," +
                    "tinhTrang TEXT NOT NULL," +
                    "giaTien INTEGER NOT NULL," +
                    "trangThai TEXT NOT NULL)" ;

            db.execSQL(createTableSanPham);

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
            String dropTableLoaiNhanVien = "Drop table if exists NhanVien";
            String dropTableLoaiKhachHang = "Drop table if exists KhachHang";
            String dropTableLoaiHang = "Drop table if exists Hang";
            String dropTableLoaiSanPham = "Drop table if exists SanPham";
            String dropTableLoaiHoaDon = "Drop table if exists HoaDon";
            String dropTableLoaiChiTietHoaDon = "Drop table if exists ChiTietHoaDon";

            if (oldVersion != newVersion) {
                db.execSQL(dropTableLoaiQuanTriVien);
                db.execSQL(dropTableLoaiNhanVien);
                db.execSQL(dropTableLoaiKhachHang);
                db.execSQL(dropTableLoaiHang);
                db.execSQL(dropTableLoaiSanPham);
                db.execSQL(dropTableLoaiHoaDon);
                db.execSQL(dropTableLoaiChiTietHoaDon);
                onCreate(db);
            }
        }


    }


