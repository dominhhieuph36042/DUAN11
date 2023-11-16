package com.example.duan1.Model;

public class SanPham {
    private String maSP;
    private String maHang;
    private String tenSP;

    private String phanLoai;
    private String tinhTrang;
    private int giaTien;
    private String trangThai;




    public SanPham() {
    }

    public SanPham(String maSP, String maHang, String tenSP, String phanLoai, String tinhTrang, int giaTien, String trangThai) {
        this.maSP = maSP;
        this.maHang = maHang;
        this.tenSP = tenSP;
        this.phanLoai = phanLoai;
        this.tinhTrang = tinhTrang;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
