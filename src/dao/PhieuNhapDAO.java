/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.PhieuNhap;

/**
 *
 * @author Home
 */
public interface PhieuNhapDAO {
    
    public List<PhieuNhap> getList();
    public List<PhieuNhap> getListFilterPrice(long min, long max);
    public int create(PhieuNhap phieuNhap);
    
}
