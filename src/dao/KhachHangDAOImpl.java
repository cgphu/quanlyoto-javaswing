/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.KhachHang;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class KhachHangDAOImpl implements KhachHangDAO {

    @Override
    public List<KhachHang> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM khachhang";
            List<KhachHang> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(rs.getInt("makh"));
                khachHang.setHoTen(rs.getString("hoten"));
                khachHang.setDienThoai(rs.getString("dienthoai"));
                khachHang.setDiaChi(rs.getString("diachi"));
                list.add(khachHang);
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
        KhachHangDAO khachHang = new KhachHangDAOImpl();

        System.out.println(khachHang.getList());
    }

    @Override
    public int createKhachHang(KhachHang khachHang) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO khachhang(hoten, dienthoai, diachi) VALUES(?, ?, ?) ";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, khachHang.getHoTen());
            ps.setString(2, khachHang.getDienThoai());
            ps.setString(3, khachHang.getDiaChi());

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

    public int updateKhachHang(KhachHang khachHang) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE khachhang "
                    + "SET hoten = ?, "
                    + "dienthoai = ?, "
                    + "diachi = ? "
                    + "WHERE makh = " + khachHang.getMaKhachHang() + "";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, khachHang.getHoTen());
            ps.setString(2, khachHang.getDienThoai());
            ps.setString(3, khachHang.getDiaChi());

            int result = ps.executeUpdate();
            ps.close();
            cons.close();
            if (result == 1) {
                return 1;
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int deleteKhachHang(int maKH) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "DELETE FROM khachhang "
                    + "WHERE makh = " + maKH + "";
            PreparedStatement ps = cons.prepareStatement(sql);

            int result = ps.executeUpdate();
            ps.close();
            cons.close();
            if (result == 1) {
                return 1;
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public static int getTotal() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) AS TOTAL FROM khachhang ";
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int total = 0;
            if (rs.next()) {
                total = rs.getInt(1);
            }

            ps.close();
            cons.close();

            return total;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
