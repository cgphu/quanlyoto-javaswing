/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Statement;
import model.Quyen;

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
public class PhanQuyenDAOImpl implements PhanQuyenDAO {

    @Override
    public List<Quyen> getListQuyen() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM quyen";
            List<Quyen> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quyen phanQuyen = new Quyen();
                phanQuyen.setChucVu(rs.getString("chucvu"));
                phanQuyen.setTenLoaiQuanLy(rs.getString("tenloaiquanly"));
                phanQuyen.setCreate(rs.getInt("them"));
                phanQuyen.setRead(rs.getInt("xem"));

                phanQuyen.setUpdate(rs.getInt("sua"));
                phanQuyen.setDelete(rs.getInt("xoa"));

                list.add(phanQuyen);
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
    public Quyen getQuyen(String chucVu, String tenLoaiQuanLy) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM quyen where chucvu = '" + chucVu + "' AND tenloaiquanly='" + tenLoaiQuanLy + "'";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            Quyen phanQuyen = new Quyen();
            while (rs.next()) {
                phanQuyen.setChucVu(rs.getString("chucvu"));
                phanQuyen.setTenLoaiQuanLy(rs.getString("tenloaiquanly"));
                phanQuyen.setCreate(rs.getInt("them"));
                phanQuyen.setRead(rs.getInt("xem"));
                phanQuyen.setUpdate(rs.getInt("sua"));
                phanQuyen.setDelete(rs.getInt("xoa"));
            }
            rs.close();
            ps.close();
            cons.close();

            return phanQuyen;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        PhanQuyenDAO phanQuyen = new PhanQuyenDAOImpl();
//        System.out.println(phanQuyen.getQuyen(14));
    }

    @Override
    public boolean suaQuyen(Quyen phanQuyen) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE quyen SET them = ?, xem = ?, sua= ?, xoa = ? where chucvu = ? and tenloaiquanly = ?";
            PreparedStatement pre = cons.prepareCall(sql);
            System.out.println(phanQuyen);
            System.out.println(sql);

            pre.setInt(1, phanQuyen.getCreate());
            pre.setInt(2, phanQuyen.getRead());
            pre.setInt(3, phanQuyen.getUpdate());
            pre.setInt(4, phanQuyen.getDelete());
            pre.setString(5, phanQuyen.getChucVu());
            pre.setString(6, phanQuyen.getTenLoaiQuanLy());
            return pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

}
