/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.DangNhapController;
import utility.MyDialog;
import dao.CTHDDAO;
import dao.CTHDDAOImpl;
import dao.HoaDonDAO;
import dao.HoaDonDAOImpl;
import dao.KhachHangDAO;
import dao.KhachHangDAOImpl;
import dao.SanPhamDAO;
import dao.SanPhamDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.CTHD;
import model.HoaDon;
import model.KhachHang;
import model.SanPham;
import utility.ClassTableModel;
import view.AddHoaDon;
import view.AddKhachHang;
import view.BanHangJPanel;

/**
 *
 * @author ASUS
 */
public class ThemHoaDonController {
    private DangNhapController controllerDangNhap;
    private JEditorPane jEditHoaDon;
    private JTextField jTTenNV, jTfTongTien;
    private JComboBox jCbMaKH;
    private JButton jBAddHD, jBtnIn,jBtnXuatHoaDon,jBtnXoaHoaDon;
    private JFrame root;
    private JButton jBtnAddKH;
    private JPanel showTableCart,showTable;

    public ThemHoaDonController(JTextField jTTenNV, JComboBox jCbMaKH,JTextField jTfTongTien, JButton jBAddHD, JFrame root, JButton jBtnAddKH, JEditorPane jEditHoaDon, JButton jBtnIn,JPanel showTableCart, JButton jBtnXuatHoaDon,JButton jBtnXoaHoaDon) {
        this.jTTenNV = jTTenNV;
        this.jCbMaKH = jCbMaKH;
        this.jBAddHD = jBAddHD;
        this.root = root;
        this.jBtnAddKH = jBtnAddKH; 
        this.jEditHoaDon=jEditHoaDon;
        this.jBtnIn=jBtnIn;
        this.showTableCart=showTableCart;
        this.jTfTongTien=jTfTongTien;
        this.jBtnXoaHoaDon=jBtnXoaHoaDon;
        this.jBtnXuatHoaDon=jBtnXuatHoaDon;
    }

    public void setEvent(HoaDon hoaDon, List<SanPham> listCart, JFrame k) {
        long tongTien = 0;
        for (SanPham sanPham : listCart) {
            tongTien = tongTien + sanPham.getGia() * sanPham.getSoLuong();
        }
        jTfTongTien.setText(tongTien + "");
        jBAddHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] container = (jCbMaKH.getSelectedItem().toString()).split("-");
                    DangNhapController controllerDangNhap = new DangNhapController();
                    hoaDon.setMaKH(Integer.parseInt(container[0]));
                    hoaDon.setMaNV(controllerDangNhap.taiKhoanLogin.getMaNV());

                    HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();
                    long tongTien = 0;
                    CTHDDAO cthdDAO = new CTHDDAOImpl();

                    for (SanPham sanPham : listCart) {
                        tongTien = tongTien + sanPham.getGia() * sanPham.getSoLuong();
                    }
                    hoaDon.setTongTien(tongTien);

                    int check = hoaDonDAO.createHoaDon(hoaDon);
                    List<HoaDon> listHoaDon = hoaDonDAO.getList();
                    System.out.println(listHoaDon);
                    if (check == 0) {
                        System.out.println(false);
                    }
                    SanPhamDAO sanPhamDAO = new SanPhamDAOImpl();
                    List<SanPham> listA = sanPhamDAO.getList();

                    for (SanPham sanPham : listCart) {
                        for (SanPham a : listA) {
                            if (a.getMaSanPham() == sanPham.getMaSanPham()) {
                                a.setSoLuong(a.getSoLuong() - sanPham.getSoLuong());
                                System.out.println(a.getSoLuong() - sanPham.getSoLuong());
                            }
                            sanPhamDAO.updateSanPham(a);
                        }
                        CTHD cthd = new CTHD();
                        cthd.setMaHD(listHoaDon.get(listHoaDon.size() - 1).getMaHD());
                        cthd.setMaSP(sanPham.getMaSanPham());
                        cthd.setSoLuong(sanPham.getSoLuong());
                        cthd.setGia(sanPham.getGia());
                        cthd.setTien(sanPham.getGia() * sanPham.getSoLuong());
                        int checkb = cthdDAO.createCTHD(cthd);

                    }
                    showPreviewHoaDon(listCart);
                    jBAddHD.setEnabled(false);
                    jBtnAddKH.setEnabled(false);
                    jCbMaKH.setEnabled(false);
                    jBtnIn.setEnabled(true);
                    jBtnXuatHoaDon.setEnabled(false);
                    jBtnXoaHoaDon.setEnabled(false);
                    if (check != 0) {
                        //set lại giỏ hàng
                        listCart.removeAll(listCart);
                        String[] listColumn = {"STT", "Mã SP","Loại SP", "Tên SP", "GIÁ", "SỐ LƯỢNG"};
                        DefaultTableModel modelCart = new ClassTableModel().setTableBanHang(listCart, listColumn);
                        JTable tableCart=new JTable(modelCart);
                        tableCart.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
                        tableCart.getTableHeader().setPreferredSize(new Dimension(100, 50));
                        tableCart.setRowHeight(50);
                        tableCart.validate();
                        tableCart.repaint();
                        JScrollPane scrollPaneCart = new JScrollPane();
                        scrollPaneCart.getViewport().add(tableCart);
                        scrollPaneCart.setPreferredSize(new Dimension(1300, 400));

                        showTableCart.removeAll();
                        showTableCart.setLayout(new BorderLayout());
                        showTableCart.add(scrollPaneCart);
                        showTableCart.validate();
                        showTableCart.repaint();
                        JOptionPane.showMessageDialog(null, "Thanh toán và thêm hóa đơn thành công", "Hóa đơn", JOptionPane.INFORMATION_MESSAGE);
                        root.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại", "Hóa đơn", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        jBtnAddKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AddKhachHang a = new AddKhachHang(k, hoaDon, listCart, jCbMaKH);
                    a.setVisible(true);
                    a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        jBtnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!jEditHoaDon.getText().equals("")) {
                        jEditHoaDon.print();
                        root.dispose();
                    }
                } catch (PrinterException ex) {
            }
        }
        });

    }

    public void loadKHToComboBox() {
        KhachHangDAO khachHangDAO = new KhachHangDAOImpl();
        List<KhachHang> listKH = khachHangDAO.getList();
        for (KhachHang KH : listKH) {
            jCbMaKH.addItem(KH.getMaKhachHang() + "-" + KH.getHoTen());
        }
    }
    public void showPreviewHoaDon(List<SanPham> listCart) {
        jEditHoaDon.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VND");
        String[] container = (jCbMaKH.getSelectedItem().toString()).split("-");
        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:16pt"
                + "}"
                + "</style>";
        hd += "<h1 style='text-align:center;'>HOÁ ĐƠN THANH TOÁN</h1>";
        hd += "Nhân viên: " + jTTenNV.getText() + "<br/>";
        hd += "Ngày lập: " + dtf.format(now) + "<br/>";
        hd += "Khách hàng: " + container[1].toString() + "<br/>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%'>";
        hd += "<tr>"
                + "<th>Mã SP</th>"
                + "<th>Tên SP</th>"
                + "<th>Số lượng</th>"
                + "<th>Đơn giá</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        long tongTien=0;
        for (SanPham sanPham : listCart) {
                        tongTien = tongTien + sanPham.getGia() * sanPham.getSoLuong();
                    }
        for (SanPham SP : listCart) {
            hd += "<tr>";
            hd += "<td style='text-align:center;'>" + SP.getMaSanPham() + "</td>";
            hd += "<td style='text-align:left;'>" + SP.getTen() + "</td>";
            hd += "<td style='text-align:center;'>" + SP.getSoLuong() + "</td>";
            hd += "<td style='text-align:center;'>" + SP.getGia() + "</td>";
            hd += "<td style='text-align:center;'>" + SP.getSoLuong()*SP.getGia() + "</td>";
            hd += "</tr>";
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Tổng cộng</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";

        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        jEditHoaDon.setText(hd);
    }
    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (!jEditHoaDon.getText().equals("")) {
                jEditHoaDon.print();
                root.dispose();
            }
        } catch (PrinterException ex) {
        }
    }
    
}
