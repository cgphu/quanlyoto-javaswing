/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ChiTietPhieuNhap;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ChiTietPhieuNhapDAOImpl implements ChiTietPhieuNhapDAO {

    @Override
    public List<ChiTietPhieuNhap> getListByMaPN(int maPN) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM ctpn, sanpham where mapn = ? and ctpn.masp = sanpham.masp";
            List<ChiTietPhieuNhap> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ps.setInt(1, maPN);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ChiTietPhieuNhap ctPhieuNhap = new ChiTietPhieuNhap();
                ctPhieuNhap.setMaPhieuNhap(rs.getInt("mapn"));
                ctPhieuNhap.setMaSanPham(rs.getInt("masp"));                
                ctPhieuNhap.setDonGia(rs.getLong("dongia"));                
                ctPhieuNhap.setSoLuong(rs.getInt("soluong"));   
                ctPhieuNhap.setThanhTien(rs.getLong("thanhtien"));      
                ctPhieuNhap.setTenSanPham(rs.getString("ten"));                
                list.add(ctPhieuNhap);
            }
            rs.close();
            ps.close();
            cons.close();

            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        
        ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAOImpl();
        System.out.println(ctPhieuNhapDAO.getListByMaPN(8));
    }
    
    @Override
    public boolean create(ChiTietPhieuNhap ctPhieNhap) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO ctpn(mapn, masp, soluong, dongia, thanhtien) VALUES(?,?,?,?, ?)";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setInt(1, ctPhieNhap.getMaPhieuNhap());
            prep.setInt(2, ctPhieNhap.getMaSanPham());
            prep.setInt(3, ctPhieNhap.getSoLuong());            
            prep.setLong(4, ctPhieNhap.getDonGia());
            prep.setLong(5, ctPhieNhap.getThanhTien());
            
            int result = prep.executeUpdate();   
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
   
    
}
