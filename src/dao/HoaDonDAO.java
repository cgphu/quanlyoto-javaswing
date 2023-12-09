/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.HoaDon;

/**
 *
 * @author ASUS
 */
public interface HoaDonDAO {
    public List<HoaDon> getList();
    
    public int createHoaDon(HoaDon hoaDon);
    
    public void updateHoaDon(HoaDon hoaDon);
    
    public void deleteHoaDon(int maHD);
}
