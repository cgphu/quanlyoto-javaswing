/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KhachHangDAO;
import dao.KhachHangDAOImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.HoaDon;
import model.KhachHang;
import model.SanPham;
import view.AddHoaDon;

/**
 *
 * @author ASUS
 */
public class ThemKhachHangController {
    private JTextField jTfHoTen;
    private JTextField jTfDienThoai;
    private JTextArea jTaDiaChi;
    private JButton jBtnAdd;
    private JFrame root;
    private JFrame AddHoaDon;
    private HoaDon hoaDon;
    private List<SanPham> listCart;
    private JComboBox jCbMaKH;
    public ThemKhachHangController(JTextField jTfHoTen, JTextField jTfDienThoai, JTextArea jTaDiaChi, JButton jBtnAdd,JFrame root, JFrame AddHoaDon,HoaDon hoaDon,List<SanPham> listCart,JComboBox jCbMaKH) {
        this.jTfHoTen = jTfHoTen;
        this.jTfDienThoai = jTfDienThoai;
        this.jTaDiaChi = jTaDiaChi;
        this.jBtnAdd = jBtnAdd;
        this.root=root;
        this.AddHoaDon=AddHoaDon;
        this.hoaDon=hoaDon;
        this.listCart=listCart;
        this.jCbMaKH=jCbMaKH;
    }
    public void setEvent() {
        jBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validate() == false) {
                        return;
                    }
                    KhachHang khachHang=new KhachHang();
                    KhachHangDAO khachHangDAO=new KhachHangDAOImpl();
                    khachHang.setHoTen(jTfHoTen.getText().trim());
                    khachHang.setDienThoai(jTfDienThoai.getText().trim());
                    khachHang.setDiaChi(jTaDiaChi.getText().trim());

                    int lastId = khachHangDAO.createKhachHang(khachHang);
                    if (lastId != 0) {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
                        root.dispose();
                        List<KhachHang> listKH = khachHangDAO.getList();
                        for (KhachHang KH : listKH) {
                            jCbMaKH.addItem(KH.getMaKhachHang() + "-" + KH.getHoTen());
                        }
//                        AddHoaDon.dispose();
//                        DangNhapController controllerDangNhap = new DangNhapController();
//                        AddHoaDon addHoaDon =new AddHoaDon(hoaDon,listCart,controllerDangNhap.taiKhoanLogin.getHoTen(),null);
//                        addHoaDon.setVisible(true);
//                        addHoaDon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                    }
                   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
    }

    private boolean validate() {
        String title = "Thông báo";
        String pattern;
        String ten = jTfHoTen.getText();
        String sdt = jTfDienThoai.getText();
        String diaChi = jTaDiaChi.getText();

        if (ten.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập họ tên khách hàng!", title, JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            pattern = "^[A-EG-IK-VXYÀÁẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬẸẺẼÈÉÊỀẾỂỄỆÌÍỈỊĨÒÓỌỎÕÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦĐƯỨỪỬỮỰỲỴÝỶỸ][a-eg-ik-vxyàáảãạăắằẳẵặâấầẩẫậẹẻẽèéêềếểễệìíỉịĩòóọỏõôốồổỗộơớờởỡợùúũụủđưứừửữựỳỵýỷỹ]{0,6}(?: [A-EG-IK-VXYÀÁẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬẸẺẼÈÉÊỀẾỂỄỆÌÍỈỊĨÒÓỌỎÕÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦĐƯỨỪỬỮỰỲỴÝỶỸ][a-eg-ik-vxyàáảãạăắằẳẵặâấầẩẫậẹẻẽèéêềếểễệìíỉịĩòóọỏõôốồổỗộơớờởỡợùúũụủđưứừửữựỳỵýỷỹ]{0,6}){0,8}$";
            boolean matchFound = ten.matches(pattern);
            if (!matchFound) {
                JOptionPane.showMessageDialog(null, "Họ tên chưa đúng", title, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (sdt.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!", title, JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            pattern = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
            boolean matchFound = sdt.matches(pattern);
            if (!matchFound) {
                JOptionPane.showMessageDialog(null, "Số điện thoại nhập không đúng! ", title, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (diaChi.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ!", title, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
