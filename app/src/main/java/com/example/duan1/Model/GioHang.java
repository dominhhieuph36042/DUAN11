package com.example.duan1.Model;

public class GioHang {

    private int maGioHang;

    private int maSP;
    private String tenSP, hangSP;
    private int gia, soluong;

    public GioHang() {
    }

    public GioHang(int maGioHang, int maSP, String tenSP, String hangSP, int gia, int soluong) {
        this.maGioHang = maGioHang;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hangSP = hangSP;
        this.gia = gia;
        this.soluong = soluong;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHangSP() {
        return hangSP;
    }

    public void setHangSP(String hangSP) {
        this.hangSP = hangSP;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
