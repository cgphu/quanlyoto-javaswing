/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.PhieuNhap;

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
public class PhieuNhapDAOImpl implements PhieuNhapDAO {

    
    @Override
    public List<PhieuNhap> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM phieunhap";
            List<PhieuNhap> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PhieuNhap phieuNhap = new PhieuNhap();
                phieuNhap.setMaPhieuNhap(rs.getInt("mapn"));                
                phieuNhap.setMaNhaCungCap(rs.getInt("mancc"));
                phieuNhap.setMaNhanVien(rs.getInt("manv"));
                phieuNhap.setNgayLap(rs.getString("ngaylap"));
                phieuNhap.setTongTien(rs.getLong("tongtien"));
                list.add(phieuNhap);
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
    
    @Override
    public List<PhieuNhap> getListFilterPrice(long min, long max) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM phieunhap where tongtien BETWEEN ? and ?";
            List<PhieuNhap> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ps.setLong(1, min);
            ps.setLong(2, max);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PhieuNhap phieuNhap = new PhieuNhap();
                phieuNhap.setMaPhieuNhap(rs.getInt("mapn"));                
                phieuNhap.setMaNhaCungCap(rs.getInt("mancc"));
                phieuNhap.setMaNhanVien(rs.getInt("manv"));
                phieuNhap.setNgayLap(rs.getString("ngaylap"));
                phieuNhap.setTongTien(rs.getLong("tongtien"));
                list.add(phieuNhap);
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
    }
    
    @Override
    public int create(PhieuNhap phieNhap) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO phieunhap(mancc, manv, ngaylap, tongtien) VALUES(?,?,?,?)";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setInt(1, phieNhap.getMaNhaCungCap());
            prep.setInt(2, phieNhap.getMaNhanVien());
            prep.setString(3, phieNhap.getNgayLap());            
            prep.setLong(4, phieNhap.getTongTien());

            prep.executeUpdate(); 
            ResultSet rs = prep.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            
            return generatedKey;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }
   
    
}
