/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ASUS
 */
import dao.SanPhamDAO;
import dao.SanPhamDAOImpl;
import dao.CTHDDAO;
import dao.CTHDDAOImpl;
import dao.HoaDonDAO;
import dao.HoaDonDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import model.CTHD;
import model.HoaDon;
import model.KhachHang;
import model.SanPham;
import quanlyoto.QuanLyOTo;
import view.*;
import utility.ClassTableModel;

public class QuanLyBanHangController {

    private JPanel showTable;
    private JPanel showTableCart, root;
    private JTextField jTMaSP;
    private JTextField jTTenSP;
    private JTextField jTGia, jTfSearch, jTfLoaiSP;
    private JLabel jLbAnh;
    private JButton jBAddToCart, jBDelete, jBtnXoaGioHang, jBUpdate, jBtnReLoad;
    private JSpinner jSoLuong;
    private JButton jBtnXuatHoaDon;
    private SanPhamDAO sanPhamDAO = null;
    private HoaDonDAO hoaDonDAO = null;
    private CTHDDAO cthdDAO = null;
    private List<SanPham> listCart = null;
    private String[] listColumn = {"STT", "Mã SP", "Loại SP", "Tên SP", "GIÁ", "SỐ LƯỢNG"};
    private TableRowSorter<TableModel> rowSorter = null;
    private TableRowSorter<TableModel> rowSorter1 = null;
    private JTable table, tableCart;
    File fileAnhSP;
    Integer tongTien = 0;

    public QuanLyBanHangController(JPanel showTable, JPanel showTableCart, JTextField jTMaSP, JTextField jTTenSP, JTextField jTGia, JSpinner jSoLuong, JTextField jTfSearch, JTextField jTfLoaiSP, JButton jBAddToCart, JButton jBDelete, JLabel jLbAnh, JButton jBtnXoaGioHang, JButton jBtnXuatHoaDon, JButton jBUpdate,JButton jBtnReLoad, JPanel root) {
        this.showTable = showTable;
        this.showTableCart = showTableCart;
        this.jTMaSP = jTMaSP;
        this.jTTenSP = jTTenSP;
        this.jTGia = jTGia;
        this.jLbAnh = jLbAnh;
        this.sanPhamDAO = new SanPhamDAOImpl();
        this.hoaDonDAO = new HoaDonDAOImpl();
        this.cthdDAO = new CTHDDAOImpl();
        this.jSoLuong = jSoLuong;
        this.jBDelete = jBDelete;
        this.listCart = new ArrayList<>();
        this.jBtnXoaGioHang = jBtnXoaGioHang;
        this.jBAddToCart = jBAddToCart;
        this.jBtnXuatHoaDon = jBtnXuatHoaDon;
        this.jBUpdate = jBUpdate;
        this.jBtnReLoad=jBtnReLoad;
        this.root = root;
        this.jTfSearch = jTfSearch;
        this.jTfLoaiSP = jTfLoaiSP;
    }

    public void setDataToTable() {
        jBAddToCart.setEnabled(false);
        jTfLoaiSP.setText("");
        jTMaSP.setText("");
        jTTenSP.setText("");
        jTGia.setText("");
        jSoLuong.setValue(0);
        loadAnh("");
        List<SanPham> listItem = sanPhamDAO.getListCanBuy();
        DefaultTableModel model = new ClassTableModel().setTableBanHang(listItem, listColumn);
        table = new JTable(model);
        rowSorter1 = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter1);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRowIndex = table.getSelectedRow();
                    if (tableCart.getRowCount() != 0) {
                        tableCart.removeRowSelectionInterval(0, tableCart.getRowCount() - 1);
                    }
                    jTMaSP.setText(table.getValueAt(selectedRowIndex, 1).toString());
                    jTfLoaiSP.setText(table.getValueAt(selectedRowIndex, 2).toString());
                    jTTenSP.setText(table.getValueAt(selectedRowIndex, 3).toString());
                    jTGia.setText(table.getValueAt(selectedRowIndex, 4).toString());
//                    SpinnerNumberModel md = new SpinnerNumberModel(1, 1, Integer.parseInt(table.getValueAt(selectedRowIndex, 5).toString()), 1);
//                    jSoLuong.setModel(md);
                    for (SanPham SP : listItem) {
                        if ((table.getValueAt(selectedRowIndex, 1)).equals(SP.getMaSanPham())) {
                            loadAnh("src/images/SanPham/" + SP.getAnh());
                        }
                    }
                    jBAddToCart.setEnabled(true);
                    jBDelete.setEnabled(false);
                    jBUpdate.setEnabled(false);
                    jSoLuong.setValue(1);
//                    setDataToCart();
                }
            }
        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));

        showTable.removeAll();
        showTable.setLayout(new BorderLayout());
        showTable.add(scrollPane);
        showTable.validate();
        showTable.repaint();
        jTfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jTfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    System.out.println("alo");
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jTfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public void setDataToCart() {
        List<SanPham> listItemA = sanPhamDAO.getList();

        if (listCart.size() != 0) {
            jBtnXuatHoaDon.setEnabled(true);
            jBtnXoaGioHang.setEnabled(true);
        } else {
            jBtnXuatHoaDon.setEnabled(false);
            jBtnXoaGioHang.setEnabled(false);
        }
        DefaultTableModel model = new ClassTableModel().setTableBanHang(listCart, listColumn);
        tableCart = new JTable(model);

        rowSorter = new TableRowSorter<>(tableCart.getModel());

        tableCart.setRowSorter(rowSorter);
        tableCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tableCart.getSelectedRow() != -1) {
                    int selectedRowIndex = tableCart.getSelectedRow();
                    table.removeRowSelectionInterval(0, table.getRowCount() - 1);

                    jTMaSP.setText(tableCart.getValueAt(selectedRowIndex, 1).toString());
                    jTfLoaiSP.setText(table.getValueAt(selectedRowIndex, 2).toString());
                    jTTenSP.setText(tableCart.getValueAt(selectedRowIndex, 3).toString());
                    jTGia.setText(tableCart.getValueAt(selectedRowIndex, 4).toString());
                    jSoLuong.setValue(tableCart.getValueAt(selectedRowIndex, 5));

                    for (SanPham SP : listItemA) {
                        if ((tableCart.getValueAt(selectedRowIndex, 1)).equals(SP.getMaSanPham())) {
//                            SpinnerNumberModel md = new SpinnerNumberModel(1, 1, SP.getSoLuong(), 1);
//                            jSoLuong.setModel(md);
                            loadAnh("src/images/SanPham/" + SP.getAnh());
                        }
                    }
                    jBUpdate.setEnabled(true);
                    jBDelete.setEnabled(true);
                    jBAddToCart.setEnabled(false);
//                    setDataToTable();
                }
            }
        });

        tableCart.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableCart.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableCart.setRowHeight(50);
        tableCart.validate();
        tableCart.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableCart);
        scrollPane.setPreferredSize(new Dimension(1300, 400));

        showTableCart.removeAll();
        showTableCart.setLayout(new BorderLayout());
        showTableCart.add(scrollPane);
        showTableCart.validate();
        showTableCart.repaint();
    }

    public void setEvent(QuanLyBanHangController testController) {
        jBAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Boolean isInCart = false;
                    List<SanPham> SanPhamTrongKho = sanPhamDAO.getListCanBuy();
                    SanPham sanPham = new SanPham();
                    Integer MaSP = Integer.parseInt(jTMaSP.getText());
                    Integer soLuong = Integer.parseInt(jSoLuong.getValue().toString());
                    sanPham.setMaSanPham(Integer.parseInt(jTMaSP.getText()));
                    sanPham.setLoai(jTfLoaiSP.getText());
                    sanPham.setTen(jTTenSP.getText());
                    sanPham.setGia(Integer.parseInt(jTGia.getText()));
                    sanPham.setSoLuong(soLuong);
                    //Validate số lượng nhập vào
                    if (soLuong<1) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    for (SanPham SPTrongKho:SanPhamTrongKho){
                                if (SPTrongKho.getMaSanPham()== MaSP){
                                    if (soLuong>SPTrongKho.getSoLuong())
                                    {
                                        JOptionPane.showMessageDialog(null, "Số lượng của mỗi sản phẩm không được lớn hơn trong kho", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    break;
                                }
                    }
                    for (SanPham SPham : listCart) {
                        if (SPham.getMaSanPham() == MaSP) {
                            for (SanPham SPTrongKho1:SanPhamTrongKho){
                                if (SPTrongKho1.getMaSanPham()== MaSP){
                                    Integer soLuongMoi = SPham.getSoLuong() + soLuong;
                                    isInCart = true;
                                    if (soLuongMoi>SPTrongKho1.getSoLuong()) 
                                        JOptionPane.showMessageDialog(null, "Số lượng của mỗi sản phẩm ở trong giỏ không được lớn hơn trong kho", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                    else SPham.setSoLuong(soLuongMoi);
                                    break;
                                }
                            }
                    //
//                            Integer soLuongMoi = SPham.getSoLuong() + soLuong;
//                            isInCart = true;
//                            SPham.setSoLuong(soLuongMoi);
//                            break;
                        }
                    }
                    if (!isInCart) {
                        listCart.add(sanPham);
                    }
                    setDataToCart();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm trước khi thêm", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        jBDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer MaSP = Integer.parseInt(jTMaSP.getText());
                
                for (SanPham SP : listCart) {
                    if (SP.getMaSanPham() == MaSP) {
                        listCart.remove(SP);
                        break;
                    }
                }
                setDataToCart();
                jBUpdate.setEnabled(false);
                jBDelete.setEnabled(false);
                jTfLoaiSP.setText("");
                jTMaSP.setText("");
                jTTenSP.setText("");
                jTGia.setText("");
                jSoLuong.setValue(0);
                loadAnh("");
            }
        });
        jBtnReLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDataToTable();
            }
        });
        jBtnXuatHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableCart.removeRowSelectionInterval(0, tableCart.getRowCount() - 1);
                table.removeRowSelectionInterval(0, table.getRowCount() - 1);
                jTfLoaiSP.setText("");
                jTMaSP.setText("");
                jTTenSP.setText("");
                jTGia.setText("");
                jSoLuong.setValue(0);
                loadAnh("");
                jBAddToCart.setEnabled(false);
                jBUpdate.setEnabled(false);
                jBDelete.setEnabled(false);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                String ngayLap = sdf.format(date);
                HoaDon hoaDon = new HoaDon();
                hoaDon.setNgayLap(ngayLap);
                hoaDon.setGhiChu("");

                DangNhapController controllerDangNhap = new DangNhapController();
                
                AddHoaDon frameAddHoaDon;
                frameAddHoaDon = new AddHoaDon(hoaDon, listCart, controllerDangNhap.taiKhoanLogin.getHoTen(), showTableCart, jBtnXuatHoaDon, jBtnXoaGioHang);
                frameAddHoaDon.setVisible(true);
                frameAddHoaDon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }

        });
        jBUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer MaSP = Integer.parseInt(jTMaSP.getText());
                List<SanPham> SanPhamTrongKho = sanPhamDAO.getListCanBuy();
                Integer soLuong=Integer.parseInt(jSoLuong.getValue().toString());
                if (soLuong<1){ 
                    JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (SanPham SP : listCart) {
                    if (SP.getMaSanPham() == MaSP) {
                        for (SanPham SPTrongKho:SanPhamTrongKho){
                                if (SPTrongKho.getMaSanPham()== MaSP){
                                    System.out.println(soLuong);
                                    if (soLuong>SPTrongKho.getSoLuong()){
                                        JOptionPane.showMessageDialog(null, "Số lượng của mỗi sản phẩm trong giỏ không được lớn hơn trong kho ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    else {
                                        SP.setSoLuong(soLuong);
                                        break;
                                    }
                                    
                                }
                        
                        
                        }
                    }
                }
                setDataToCart();
                jBUpdate.setEnabled(false);
                jBDelete.setEnabled(false);
                jTMaSP.setText("");
                jTTenSP.setText("");
                jTGia.setText("");
                jSoLuong.setValue(0);
            }

        });

        jBtnXoaGioHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listCart = new ArrayList<>();
                    setDataToCart();
                    jBUpdate.setEnabled(false);
                    jBDelete.setEnabled(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });

    }

    private void loadAnh(String anh) {
        jLbAnh.setText("");
        jLbAnh.setIcon(getAnhSP(anh));
    }

    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;

        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("src/images/SanPham/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("src/images/default.png");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

}
