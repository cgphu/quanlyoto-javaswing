/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.SanPham;

/**
 *
 * @author ASUS
 */
public interface SanPhamDAO {
    public List<SanPham> getList();
    
    public List<SanPham> getListCanBuy();
    
    public List<SanPham> getListNhapHang();
    
    public int createSanPham(SanPham sanPham);
    
    public int updateSanPham(SanPham sanPham);
    
    public boolean updateSoLuong(int maSP, int newSL);
    
    public int deleteSanPham(int makh);
}
