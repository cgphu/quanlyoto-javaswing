/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;

/**
 *
 * @author ASUS
 */
public class HoaDonDAOImpl implements HoaDonDAO {
    @Override
    public List<HoaDon> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM hoadon";
            List<HoaDon> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("mahd"));
                hoaDon.setMaKH(rs.getInt("makh"));
                hoaDon.setMaNV(rs.getInt("manv"));
                hoaDon.setNgayLap(rs.getString("ngaylap"));
                hoaDon.setTongTien(rs.getLong("tongtien"));
                hoaDon.setGhiChu(rs.getString("ghichu"));
                list.add(hoaDon);
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
        HoaDonDAO hoaDon = new HoaDonDAOImpl();
        
        System.out.println(hoaDon.getList());
    }
    @Override
    public int createHoaDon(HoaDon hoaDon) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO hoadon(makh, manv , ngaylap, tongtien, ghichu) VALUES(?, ?, ?,?,?) ";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, hoaDon.getMaKH());
            ps.setInt(2, hoaDon.getMaNV());
            ps.setString(3, hoaDon.getNgayLap());
            ps.setLong(4, hoaDon.getTongTien());
            ps.setString(5,hoaDon.getGhiChu());
            
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public void updateHoaDon(HoaDon hoaDon) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE hoadon "
                    + "SET makh = ?, "
                    + "manv = ?, "
                    + "ngaylap = ? "
                    + "tongtien = ? "
                    + "ghichu = ? "
                    + "WHERE makh = " + hoaDon.getMaHD() + "";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setInt(1, hoaDon.getMaKH());
            ps.setInt(2, hoaDon.getMaNV());
            ps.setString(3, hoaDon.getNgayLap());
            ps.setLong(4, hoaDon.getTongTien());
            ps.setString(5,hoaDon.getGhiChu());
            
            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
            ps.close();
            cons.close();
//            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        return 0;
    }
    
    public void deleteHoaDon(int maHD) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "DELETE FROM hoadon "
                    + "WHERE maHD = " + maHD + "";
            PreparedStatement ps = cons.prepareStatement(sql);
            
            ps.execute();
            ps.close();
            cons.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
