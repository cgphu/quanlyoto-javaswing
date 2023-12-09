/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.CTHD;

/**
 *
 * @author ASUS
 */
public interface CTHDDAO {
    public List<CTHD> getList();
    
    public int createCTHD(CTHD cthd);
    
//    public void updateCTHD(CTHD cthd);
//    
    public void deleteCTHD(int maHD);
}
