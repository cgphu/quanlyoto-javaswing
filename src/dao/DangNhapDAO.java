package dao;

import java.sql.Connection;
import model.NhanVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangNhapDAO {

    public NhanVien dangNhap(NhanVien tk) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM nhanvien WHERE taikhoan=? AND matkhau=?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, tk.getTaiKhoan());
            ps.setString(2, tk.getMatKhau());
            ResultSet rs = ps.executeQuery();
            NhanVien tkLogin = null;
            if (rs.next()) {
                tkLogin = tk;
                tkLogin.setMaNV(rs.getInt("manv"));
                tkLogin.setHoTen(rs.getString("hoten"));
                tkLogin.setChucVu(rs.getString("chucvu"));
            }
            return tkLogin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
}