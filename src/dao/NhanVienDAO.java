/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.NhanVien;

/**
 *
 * @author Home
 */
public interface NhanVienDAO {
    
    public List<NhanVien> getList(String chucVu);
    public NhanVien getByMaNV(int maNV);
    public int create(NhanVien nhanVien);
    public boolean update(NhanVien nhanVien);
    public boolean delete(NhanVien nhanVien);

    
}
