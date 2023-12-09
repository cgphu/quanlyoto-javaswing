/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.ChiTietPhieuNhap;

/**
 *
 * @author Home
 */
public interface ChiTietPhieuNhapDAO {
    
    public List<ChiTietPhieuNhap> getListByMaPN(int maPN);
    public boolean create(ChiTietPhieuNhap ctPhieuNhap);
    
}
