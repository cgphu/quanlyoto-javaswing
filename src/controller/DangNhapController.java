package controller;

import dao.DangNhapDAO;
import controller.PhanQuyenController;
import model.Quyen;
import model.NhanVien;
import utility.MyDialog;

import java.io.*;

public class DangNhapController {
    

    private final static int EMPTY_ERROR = 1;
    private final static int WRONG_ERROR = 2;
    public static NhanVien taiKhoanLogin = null;

    public NhanVien getTaiKhoanDangNhap(String user, String password, boolean selected) {
        if (kiemTraDangNhap(user, password) == EMPTY_ERROR) {
            new MyDialog("Không được để trống thông tin!", MyDialog.ERROR_DIALOG);
            return null;
        }
        NhanVien tk = new NhanVien();
        tk.setTaiKhoan(user);
        tk.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        NhanVien account = dangNhapDAO.dangNhap(tk);
        taiKhoanLogin = account;

        if (account == null) {
            new MyDialog("Sai thông tin đăng nhập hoặc tài khoản đã bị khoá!", MyDialog.ERROR_DIALOG);
        } else {
//            PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
//            phanQuyenBUS.kiemTraQuyen(account.getChucVu());
            xuLyGhiNhoDangNhap(user, password, selected);
            new MyDialog("Đăng nhập thành công!", MyDialog.SUCCESS_DIALOG);

        }
        return account;
    }

    public String getTaiKhoanGhiNho() {
        try {
            FileInputStream fis = new FileInputStream("remember.dat");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            br.close();
            return line;
        }catch (Exception e) {
        }
        return "";
    }

    private void xuLyGhiNhoDangNhap(String user, String password, boolean selected) {
        try {
            if (!selected) {
                user = "";
                password = "";
            }
            FileWriter fw = new FileWriter("remember.dat");
            fw.write(user + " | " + password);
            fw.close();
        } catch (Exception e) {
        }
    }

    private int kiemTraDangNhap(String user, String password) {
        user = user.replaceAll("\\s+", "");
        password = password.replaceAll("\\s+", "");
        int result = 0;

        NhanVien tk = new NhanVien();
        tk.setTaiKhoan(user);
        tk.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        NhanVien account = dangNhapDAO.dangNhap(tk);

        if (user.length() <= 0 || password.length() <= 0) {
            result = EMPTY_ERROR;
        } else if (account == null) {
            result = WRONG_ERROR;
        }
        return result;
    }

}
