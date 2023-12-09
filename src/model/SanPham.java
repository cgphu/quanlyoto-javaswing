/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class SanPham {
    private int maSanPham;
    private String loai;
    private String ten;
    private int soLuong;
    private long gia;
    private int hanBaoHanh;
    private String anh;

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getHanBaoHanh() {
        return hanBaoHanh;
    }

    public void setHanBaoHanh(int hanBaoHanh) {
        this.hanBaoHanh = hanBaoHanh;
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSanPham=" + maSanPham + ", loai=" + loai + ", ten=" + ten + ", soLuong=" + soLuong + ", gia=" + gia + ", hanBaoHanh=" + hanBaoHanh + ", anh=" + anh + '}';
    }

    
}
