/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.KhachHang;

/**
 *
 * @author ASUS
 */
public interface KhachHangDAO {

    public List<KhachHang> getList();

    public int createKhachHang(KhachHang khachHang);

    public int updateKhachHang(KhachHang khachHang);

    public int deleteKhachHang(int makh);
}
