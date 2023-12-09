/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import utility.MyDialog;
import java.util.ArrayList;
import java.util.List;
import model.Quyen;

/**
 *
 * @author Home
 */
public interface PhanQuyenDAO {
    
    public List<Quyen> getListQuyen();
    public Quyen getQuyen(String quyen,String tenloaiquanly);
    
    public boolean suaQuyen(Quyen phanQuyen);
    

   
   
    
}
//package dao;
//
//import java.sql.Connection;
//import model.Quyen;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//public class PhanQuyenDAO {
//
//    public ArrayList<Quyen> getListQuyen() {
////        System.out.println("1");
//        try {
//            Connection cons = DBConnection.getConnection();
//            String sql = "SELECT * FROM quyen";
//            Statement st = cons.createStatement();
//            ResultSet rs = st.executeQuery(sql);
////            System.out.println(rs);
//            ArrayList<Quyen> dspq = new ArrayList<>();
//            while (rs.next()) {
//                
////                System.out.println(rs.getString(1));
//                Quyen phanQuyen = new Quyen();
////                                System.out.println(rs.getString(1));
//                phanQuyen.setChucVu(rs.getString(1));
//                phanQuyen.setTenloaiquanly(rs.getString(2));
//                phanQuyen.setThem(rs.getInt(3));
//                phanQuyen.setXoa(rs.getInt(4));
//                phanQuyen.setSua(rs.getInt(5));
//                phanQuyen.setXem(rs.getInt(6));
//                
////                                System.out.println(rs.getString(2));
//                dspq.add(phanQuyen);
//            }
//            return dspq;
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    public Quyen getQuyen(String quyen) {
//        try {
//                        Connection cons = DBConnection.getConnection();
//            String sql = "SELECT * FROM quyen WHERE quyen='" + quyen + "'";
//            Statement st;
//            st = cons.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                Quyen phanQuyen = new Quyen();
//                phanQuyen.setChucVu(quyen);
//                phanQuyen.setTenloaiquanly(quyen);
//                phanQuyen.setThem(rs.getInt(2));
//                phanQuyen.setThem(rs.getInt(3));
//                phanQuyen.setXoa(rs.getInt(4));
//                phanQuyen.setSua(rs.getInt(5));
//                phanQuyen.setXem(rs.getInt(6));
//                return phanQuyen;
//            }
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    public boolean suaQuyen(Quyen phanQuyen) {
//        try {
//                        Connection cons = DBConnection.getConnection();
//            String sql = "UPDATE quyen SET NhapHang=?,QLSanPham=?,QLNhanVien=?,QLKhachHang=?,ThongKe=? WHERE quyen=?";
//            PreparedStatement pre;
//            pre = cons.prepareStatement(sql);
//            pre.setInt(1, phanQuyen.getThem());
//            pre.setInt(2, phanQuyen.getXoa());
//            pre.setInt(3, phanQuyen.getSua());
//            pre.setInt(4, phanQuyen.getXem());
//           
//            pre.setString(5, phanQuyen.getTenloaiquanly());
//            pre.setString(6, phanQuyen.getChucVu());
//            return pre.executeUpdate() > 0;
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    public boolean themQuyen(Quyen phanQuyen) {
//        try {
//                        Connection cons = DBConnection.getConnection();
//            String sql = "INSERT INTO quyen VALUES (?,?,?,?,?,?)";
//            PreparedStatement pre;
//            pre = cons.prepareStatement(sql);
//            pre.setString(1, phanQuyen.getChucVu());
//            pre.setString(1, phanQuyen.getTenloaiquanly());
//            pre.setInt(2, phanQuyen.getThem());
//            pre.setInt(3, phanQuyen.getXoa());
//            pre.setInt(4, phanQuyen.getSua());
//            pre.setInt(5, phanQuyen.getXem());
//            
//            return pre.executeUpdate() > 0;
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    public boolean xoaQuyen(String phanQuyen) {
//        try {
//                        Connection cons = DBConnection.getConnection();
//            String sql1 = "UPDATE nhanvien SET quyen='Default' WHERE quyen='" + phanQuyen + "'";
//            Statement st1;
//            st1 =cons.createStatement();
//            st1.executeUpdate(sql1);
//            String sql = "DELETE FROM quyen WHERE quyen='" + phanQuyen + "'";
//            Statement st;
//            st = cons.createStatement();
//            return st.executeUpdate(sql) > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
