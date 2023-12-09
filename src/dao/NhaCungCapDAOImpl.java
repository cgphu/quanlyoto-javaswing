/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.NhaCungCap;

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
public class NhaCungCapDAOImpl implements NhaCungCapDAO {

    @Override
    public List<NhaCungCap> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM nhacungcap";
            List<NhaCungCap> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setMancc(rs.getInt("mancc"));                
                nhaCungCap.setTenncc(rs.getString("tenncc"));
                nhaCungCap.setDiachi(rs.getString("diachi"));
                nhaCungCap.setDienthoai(rs.getString("dienthoai"));
                list.add(nhaCungCap);
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
    public NhaCungCap getByMaNCC(int maNCC) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM nhacungcap where mancc = ?";
            PreparedStatement ps = cons.prepareCall(sql);
            ps.setInt(1, maNCC);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setMancc(rs.getInt("mancc"));                
                nhaCungCap.setTenncc(rs.getString("tenncc"));
                nhaCungCap.setDiachi(rs.getString("diachi"));
                nhaCungCap.setDienthoai(rs.getString("dienthoai"));
                return nhaCungCap;
            }
            rs.close();
            ps.close();
            cons.close();

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        NhaCungCapDAO nhaCungCap = new NhaCungCapDAOImpl();
        System.out.println(nhaCungCap.getByMaNCC(14));
    }
    
    @Override
    public boolean create(NhaCungCap nhaCungCap) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO nhacungcap(tenncc, diachi, dienthoai) VALUES(?,?,?)";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setString(1, nhaCungCap.getTenncc());
            prep.setString(2, nhaCungCap.getDiachi());
            prep.setString(3, nhaCungCap.getDienthoai());
            int result = prep.executeUpdate();   
            prep.close();
            cons.close();
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    @Override
    public boolean update(NhaCungCap nhaCungCap) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE nhacungcap SET tenncc = ?, diachi = ?, dienthoai = ? where mancc = ?";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setString(1, nhaCungCap.getTenncc());
            prep.setString(2, nhaCungCap.getDiachi());
            prep.setString(3, nhaCungCap.getDienthoai());
            prep.setInt(4, nhaCungCap.getMancc());
            int result = prep.executeUpdate();  
            prep.close();
            cons.close();
            if (result == 1) return true;
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    @Override
    public boolean delete(NhaCungCap nhaCungCap) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "DELETE FROM nhacungcap where mancc = ?";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setInt(1, nhaCungCap.getMancc());
            int result = prep.executeUpdate();  
            prep.close();
            cons.close();
            if (result == 1) return true;
            return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
}
