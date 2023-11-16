package com.example.duan1.Model;

public class ChiTietHoaDon {

    private int CTHD;
    private String maHD;
    private String maSP;
    private int soLuong;
    private int donGia;
    private int giamGia;
    private int baoHanh;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int CTHD, String maHD, String maSP, int soLuong, int donGia, int giamGia, int baoHanh) {
        this.CTHD = CTHD;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.giamGia = giamGia;
        this.baoHanh = baoHanh;
    }

    public int getCTHD() {
        return CTHD;
    }

    public void setCTHD(int CTHD) {
        this.CTHD = CTHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }
}
