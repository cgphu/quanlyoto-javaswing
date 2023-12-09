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
import model.CTHD;

/**
 *
 * @author ASUS
 */
public class CTHDDAOImpl implements CTHDDAO{
    @Override
    public List<CTHD> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM cthd ";
            List<CTHD> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                CTHD cthd = new CTHD();
                cthd.setMaHD(rs.getInt("mahd"));
                cthd.setMaSP(rs.getInt("masp"));
                cthd.setSoLuong(rs.getInt("soluong"));
                cthd.setGia(rs.getLong("dongia"));
                cthd.setTien(rs.getLong("thanhtien"));
                list.add(cthd);
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
        CTHDDAO cthd = new CTHDDAOImpl();
        
        System.out.println(cthd.getList());
    }
    @Override
    public int createCTHD(CTHD cthd) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO cthd(mahd, masp , soluong, dongia, thanhtien) VALUES(?, ?, ?,?,?) ";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cthd.getMaHD());
            ps.setInt(2, cthd.getMaSP());
            ps.setInt(3, cthd.getSoLuong());
            ps.setLong(4, cthd.getGia());
            ps.setLong(5,cthd.getTien());
            
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

    public void deleteCTHD(int maHD) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "DELETE FROM cthd "
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
