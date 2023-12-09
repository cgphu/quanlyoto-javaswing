/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ChiTietPhieuNhapDAO;
import dao.ChiTietPhieuNhapDAOImpl;
import dao.NhaCungCapDAO;
import dao.NhaCungCapDAOImpl;
import dao.PhieuNhapDAO;
import dao.PhieuNhapDAOImpl;
import dao.SanPhamDAO;
import dao.SanPhamDAOImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.ChiTietPhieuNhap;
import model.NhaCungCap;
import model.PhieuNhap;
import model.SanPham;
import utility.ClassTableModel;
import view.AddNhaCungCap;

/**
 *
 * @author Home
 */
public class QuanLyNhapHangController {
    
    private JPanel jPnTabNhapHang;
    private JPanel jPnTabPhieuNhap;

    private JScrollPane jScrollPaneKhoHang;
    private JScrollPane jScrollPaneHangChoNhap;
    private JScrollPane jScrollPaneDSPhieuNhap;
    private JScrollPane jScrollPaneChiTietPN;

    private JTable jTableKhoHang;
    private JTable jTableHangChoNhap;
    private JTable jTableDSPhieuNhap;
    private JTable jTableChiTietPN;

    private JTextField jTfSearch;
    
    private JTextField jTfMaSP;
    private JTextField jTfTenSP;
    private JTextField jTfDonGia;
    private JSpinner jSpSoLuong;
    
    private JTextField jTfNhanVien;
    
    private JTextField jTfPNMaPN;
    private JTextField jTfPNMaNhaCC;
    private JTextField jTfPNNhanVien;
    private JTextField jTfPNNgayLap;
    private JTextField jTfPNTongTien;
    
    private JTextField jTfPriceFrom;
    private JTextField jTfPriceTo;
    
    
    private JButton jBtnNhapHang;
    private JButton jBtnXoaHang;
    private JButton jBtnXacNhan;
    private JButton jBtnThayDoiSoLuong;
    private JButton jBtnFilter;
    private JButton jBtnAdd;
    private JButton jBtnPreviewPN;
    
    private JButton jBtnUpdate;

    private JComboBox jComboBoxNhaCC;
    
    private JTabbedPane jTabbedPane;

    
    private List<ChiTietPhieuNhap> danhSachSanPhamNhap;    

    private List<PhieuNhap> danhSachPhieuNhap;
    
    private boolean isFiltering = false;
    
    
//    private TableRowSorter<TableModel> rowSorter = null;
    
    DecimalFormat dcf = new DecimalFormat("###,### VND");


    public QuanLyNhapHangController(JTabbedPane jTabbedPane,
            JPanel jPnTabNhapHang, JPanel jPnTabPhieuNhap,
            JScrollPane jScrollPaneKhoHang, JScrollPane jScrollPaneHangChoNhap, 
            JScrollPane jScrollPaneDSPhieuNhap, JScrollPane jScrollPaneChiTietPN,
            JTable jTableKhoHang, JTable jTableHangChoNhap, JTable jTableDSPhieuNhap, JTable jTableChiTietPN,
            JTextField jTfSearch, JTextField jTfMaSP, JTextField jTfTenSP, JTextField jTfDonGia,
            JSpinner jSpSoLuong, JTextField jTfNhanVien,
            JTextField jTfPNMaPN, JTextField jTfPNMaNhaCC, JTextField jTfPNNhanVien,
            JTextField jTfPNNgayLap, JTextField jTfPNTongTien,
            JTextField jTfPriceFrom, JTextField jTfPriceTo,
            JButton jBtnNhapHang, JButton jBtnXoaHang, JButton jBtnXacNhan, JButton jBtnThayDoiSoLuong, 
            JButton jBtnFilter,JButton jBtnAdd, JButton jBtnPreviewPN, JButton jBtnUpdate,
            JComboBox jComboBoxNhaCC) {
        
        this.jTabbedPane = jTabbedPane;
        
        this.jPnTabNhapHang = jPnTabNhapHang;
        this.jPnTabPhieuNhap = jPnTabPhieuNhap;
        
        this.jScrollPaneKhoHang = jScrollPaneKhoHang;
        this.jScrollPaneHangChoNhap = jScrollPaneHangChoNhap;
        this.jScrollPaneDSPhieuNhap = jScrollPaneDSPhieuNhap;
        this.jScrollPaneChiTietPN = jScrollPaneChiTietPN;

        this.jTableKhoHang = jTableKhoHang;
        this.jTableHangChoNhap = jTableHangChoNhap;
        this.jTableDSPhieuNhap = jTableDSPhieuNhap;
        this.jTableChiTietPN = jTableChiTietPN;
        
        this.jTfSearch = jTfSearch;
        
        this.jTfMaSP = jTfMaSP;
        this.jTfTenSP = jTfTenSP;
        this.jTfDonGia = jTfDonGia;
        this.jSpSoLuong = jSpSoLuong;
        this.jTfNhanVien = jTfNhanVien;
        
        this.jTfPNMaPN = jTfPNMaPN;
        this.jTfPNMaNhaCC = jTfPNMaNhaCC;
        this.jTfPNNhanVien = jTfPNNhanVien;
        this.jTfPNNgayLap = jTfPNNgayLap;
        this.jTfPNTongTien = jTfPNTongTien;
        
        this.jTfPriceFrom = jTfPriceFrom;
        this.jTfPriceTo = jTfPriceTo;
        
        this.jBtnNhapHang = jBtnNhapHang;
        this.jBtnXoaHang = jBtnXoaHang;
        this.jBtnXacNhan = jBtnXacNhan;
        this.jBtnThayDoiSoLuong = jBtnThayDoiSoLuong;
        this.jBtnFilter = jBtnFilter;

        this.jBtnAdd = jBtnAdd;
        this.jBtnPreviewPN = jBtnPreviewPN;

        this.jBtnUpdate=jBtnUpdate;
        
        DangNhapController controllerDangNhap = new DangNhapController();
        
        if (controllerDangNhap.taiKhoanLogin.getChucVu().equals("Quản trị")) {
            jBtnAdd.setVisible(true);
        } else {
            jBtnAdd.setVisible(false);
        }
        
        this.jTfNhanVien.setText(DangNhapController.taiKhoanLogin.getMaNV() + "-" +
                DangNhapController.taiKhoanLogin.getHoTen());

        this.jComboBoxNhaCC = jComboBoxNhaCC;
        
        this.danhSachSanPhamNhap = new ArrayList<>();
        this.danhSachPhieuNhap = new ArrayList<>();
        
        loadSanPhamToTable();
        loadNhaCungCapToComboBox();

        resetUI();
               
         
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < jTableKhoHang.getColumnCount(); i++) {
            jTableKhoHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        for (int i = 0; i < jTableChiTietPN.getColumnCount(); i++) {
            jTableChiTietPN.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        for (int i = 0; i < jTableDSPhieuNhap.getColumnCount(); i++) {
            jTableDSPhieuNhap.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        for (int i = 0; i < jTableHangChoNhap.getColumnCount(); i++) {
            jTableHangChoNhap.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
    }
    
    public void loadSanPhamToTable() {
        SanPhamDAO sanPhamDAO = new SanPhamDAOImpl();
        
        List<SanPham> listSanPham = sanPhamDAO.getListNhapHang();
        
        DefaultTableModel model = (DefaultTableModel)jTableKhoHang.getModel();
        jTableKhoHang.setDefaultEditor(Object.class, null);
        
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);

        jTableKhoHang.setRowSorter(rowSorter);

        model.setRowCount(0);
        for (int i = 0; i < listSanPham.size(); i++) {
            model.addRow(new Object[]{
                listSanPham.get(i).getMaSanPham(), 
                listSanPham.get(i).getTen(), 
                listSanPham.get(i).getSoLuong(),

            });
        }
        
        jTableKhoHang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTableKhoHang.getTableHeader().setPreferredSize(new Dimension(50, 30));
        jTableKhoHang.setRowHeight(30);
        
        jScrollPaneKhoHang.getViewport().add(jTableKhoHang);
        
        jTfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jTfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jTfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        
        jTableKhoHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jTableKhoHang.getSelectedRow() != -1) {
                    jBtnUpdate.setEnabled(false);
                    jBtnNhapHang.setEnabled(true);
                    if (jTableHangChoNhap.getRowCount()!=0)
                        jTableHangChoNhap.removeRowSelectionInterval(0,jTableHangChoNhap.getRowCount()-1);
                    int selectedRowIndex = jTableKhoHang.getSelectedRow();
                    SanPham sanPham = new SanPham();
                    sanPham.setMaSanPham((int) jTableKhoHang.getValueAt(selectedRowIndex, 0));
                    sanPham.setTen(jTableKhoHang.getValueAt(selectedRowIndex, 1).toString());
                    sanPham.setSoLuong((int) jTableKhoHang.getValueAt(selectedRowIndex, 2));
                    
                    jTfMaSP.setText((sanPham.getMaSanPham()) + "");
                    jTfTenSP.setText(sanPham.getTen());
                    
                    jBtnNhapHang.setEnabled(true);
                }
            }
        });
        jBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNhaCungCap(jComboBoxNhaCC).setVisible(true);
            }
        });
    }
    
    
    public void loadNhaCungCapToComboBox() {
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAOImpl();
        
        List<NhaCungCap> listNhaCC = nhaCungCapDAO.getList();
        for (int i = 0; i < listNhaCC.size(); i++) {
            jComboBoxNhaCC.addItem(listNhaCC.get(i));
        }
    }
    
    public void loadPhieuNhapToTable() {
        
        if (!isFiltering) {
            PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAOImpl();
            danhSachPhieuNhap = phieuNhapDAO.getList();
        }
       
        DefaultTableModel model = (DefaultTableModel)jTableDSPhieuNhap.getModel();
        jTableDSPhieuNhap.setDefaultEditor(Object.class, null);
        
        model.setRowCount(0);
        if (danhSachPhieuNhap.isEmpty()) {
            JLabel JLbMessage = new JLabel();
            JLbMessage.setText("Không tìm thấy phiếu nhập phù hợp!");
            jScrollPaneDSPhieuNhap.getViewport().add(JLbMessage);
            return;
        }
        
        for (int i = 0; i < danhSachPhieuNhap.size(); i++) {
            model.addRow(new Object[]{
                danhSachPhieuNhap.get(i).getMaPhieuNhap(), 
                danhSachPhieuNhap.get(i).getMaNhaCungCap(), 
                danhSachPhieuNhap.get(i).getMaNhanVien(),                
                danhSachPhieuNhap.get(i).getNgayLap(),
                danhSachPhieuNhap.get(i).getTongTien(),

            });
        }
        jTableDSPhieuNhap.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTableDSPhieuNhap.getTableHeader().setPreferredSize(new Dimension(50, 30));
        jTableDSPhieuNhap.setRowHeight(30);
        
        jScrollPaneDSPhieuNhap.getViewport().add(jTableDSPhieuNhap);
        
        jTableDSPhieuNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jTableDSPhieuNhap.getSelectedRow() != -1) {
                    
                    int selectedRowIndex = jTableDSPhieuNhap.getSelectedRow();
                    
                    PhieuNhap phieuNhap = new PhieuNhap();
                    phieuNhap.setMaPhieuNhap((int) jTableDSPhieuNhap.getValueAt(selectedRowIndex, 0));
                    phieuNhap.setMaNhaCungCap((int) jTableDSPhieuNhap.getValueAt(selectedRowIndex, 1));
                    phieuNhap.setMaNhanVien((int) jTableDSPhieuNhap.getValueAt(selectedRowIndex, 2));
                    phieuNhap.setNgayLap(jTableDSPhieuNhap.getValueAt(selectedRowIndex, 3).toString());
                    phieuNhap.setTongTien((long) jTableDSPhieuNhap.getValueAt(selectedRowIndex, 4));
                    
                    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAOImpl();
                    NhaCungCap nhaCungCap = nhaCungCapDAO.getByMaNCC(phieuNhap.getMaNhaCungCap());
                    
                    jTfPNMaPN.setText(phieuNhap.getMaPhieuNhap() + "");
                    jTfPNMaNhaCC.setText(nhaCungCap.toString());
                    jTfPNNhanVien.setText(phieuNhap.getMaNhanVien() + "");
                    jTfPNNgayLap.setText(phieuNhap.getNgayLap());
                    jTfPNTongTien.setText(dcf.format(phieuNhap.getTongTien()) + "");
                    
                    loadChiTietPhieuNhap(phieuNhap.getMaPhieuNhap());
                  
                }
            }
            
        });
    
    }
    
    public void loadChiTietPhieuNhap(int maPN) {
        ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAOImpl();
        
        List<ChiTietPhieuNhap> listCTPhieuNhap = ctPhieuNhapDAO.getListByMaPN(maPN);
        
        DefaultTableModel model = (DefaultTableModel)jTableChiTietPN.getModel();
        jTableChiTietPN.setDefaultEditor(Object.class, null);

        model.setRowCount(0);
        for (int i = 0; i < listCTPhieuNhap.size(); i++) {
            model.addRow(new Object[]{
                listCTPhieuNhap.get(i).getMaSanPham(), 
                listCTPhieuNhap.get(i).getTenSanPham(), 
                dcf.format(listCTPhieuNhap.get(i).getDonGia()),                
                listCTPhieuNhap.get(i).getSoLuong(),
                dcf.format(listCTPhieuNhap.get(i).getThanhTien()),

            });
        }
        jTableChiTietPN.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTableChiTietPN.getTableHeader().setPreferredSize(new Dimension(50, 30));
        jTableChiTietPN.setRowHeight(30);
        
        jScrollPaneChiTietPN.getViewport().add(jTableChiTietPN);
        
    
    }
    
    
    public void showDanhSachSanPhamNhap() {
        resetUI();
        if (danhSachSanPhamNhap.size() > 0) {
            jBtnThayDoiSoLuong.setEnabled(true);
            jBtnXoaHang.setEnabled(true);
            jBtnXacNhan.setEnabled(true);
            jBtnPreviewPN.setEnabled(true);
        }
        
        DefaultTableModel model = (DefaultTableModel)jTableHangChoNhap.getModel();
        jTableHangChoNhap.setDefaultEditor(Object.class, null);

        model.setRowCount(0);
        for (int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            model.addRow(new Object[]{
                danhSachSanPhamNhap.get(i).getMaSanPham(), 
                danhSachSanPhamNhap.get(i).getTenSanPham(), 
                danhSachSanPhamNhap.get(i).getDonGia(),                
                danhSachSanPhamNhap.get(i).getSoLuong(),
                danhSachSanPhamNhap.get(i).getThanhTien(),
            });
        }
        jTableHangChoNhap.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTableHangChoNhap.getTableHeader().setPreferredSize(new Dimension(50, 30));
        jTableHangChoNhap.setRowHeight(30);

        jScrollPaneHangChoNhap.getViewport().add(jTableHangChoNhap);
        
        jTableHangChoNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jTableHangChoNhap.getSelectedRow() != -1) {
                    jBtnUpdate.setEnabled(true);
                    jTableKhoHang.removeRowSelectionInterval(0,jTableKhoHang.getRowCount()-1);
                    int selectedRowIndex = jTableHangChoNhap.getSelectedRow();
                    SanPham sanPham = new SanPham();
                    sanPham.setMaSanPham((int) jTableHangChoNhap.getValueAt(selectedRowIndex, 0));
                    sanPham.setTen(jTableHangChoNhap.getValueAt(selectedRowIndex, 1).toString());
                    sanPham.setGia((long) jTableHangChoNhap.getValueAt(selectedRowIndex, 2));
                    sanPham.setSoLuong((int) jTableHangChoNhap.getValueAt(selectedRowIndex, 3));
                    
                    jTfMaSP.setText((sanPham.getMaSanPham()) + "");
                    jTfTenSP.setText(sanPham.getTen());
                    jTfDonGia.setText(jTableHangChoNhap.getValueAt(selectedRowIndex, 2).toString());
                    jSpSoLuong.setValue(jTableHangChoNhap.getValueAt(selectedRowIndex, 3));
                    
                    jBtnNhapHang.setEnabled(false);
                }
            }
        });
        
    }
    
    
    public void themSanPhamVaoDanhSachNhap(ChiTietPhieuNhap ctPhieuNhap) {
        boolean isAdded = false;
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            if (ctPhieuNhap.getMaSanPham() == danhSachSanPhamNhap.get(i).getMaSanPham()) {
                int ghiDe = JOptionPane.showConfirmDialog(null, 
                        "Sản phẩm " + ctPhieuNhap.getMaSanPham() + "-" + ctPhieuNhap.getTenSanPham() 
                        + " đã tồn tại. Bạn có tiếp tục và tăng số lượng không?",
                        "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ghiDe != JOptionPane.YES_OPTION) {
                    return;
                }
                if (ctPhieuNhap.getDonGia() != danhSachSanPhamNhap.get(i).getDonGia()) {
                    Object[] options = {"Dùng đơn giá trước", "Dùng đơn giá mới"};
                    int luaChon = JOptionPane.showOptionDialog(null, 
                        "Đơn giá không giống nhau. Hạy chọn một lựa chọn dưới đây:", "Đơn giá không giống nhau",
                         JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                         null, options, options[0]);
                    if (luaChon == 0) {
                        ctPhieuNhap.setDonGia(danhSachSanPhamNhap.get(i).getDonGia());
                    } else {
                        danhSachSanPhamNhap.get(i).setDonGia(ctPhieuNhap.getDonGia());
                    }
                    
                }
                
                isAdded = true;
               
                int sl = danhSachSanPhamNhap.get(i).getSoLuong();
                int newSL = sl + ctPhieuNhap.getSoLuong();
                danhSachSanPhamNhap.get(i).setSoLuong(newSL > 0 ? newSL : 1);
                danhSachSanPhamNhap.get(i).setThanhTien(newSL * ctPhieuNhap.getDonGia());
                break;
            }
        }
        // Truong hop: sp chua nam trong "Hang Cho Nhap"
        if (!isAdded) {
            danhSachSanPhamNhap.add(ctPhieuNhap);
            return;
        }        
    }
    
    public void xoaSanPhamKhoiDanhSachNhap(int maSanPham) {
        int index = -1;
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            if (danhSachSanPhamNhap.get(i).getMaSanPham() == maSanPham) {
                index = i;
                break;
            }
        }
        
        if (index != -1) {
            danhSachSanPhamNhap.remove(index);
        }
    
    }
    
    public void capNhatSoLuongSanPham(int maSanPham, int newSL) {
    
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            if (danhSachSanPhamNhap.get(i).getMaSanPham() == maSanPham) {
                danhSachSanPhamNhap.get(i).setSoLuong(newSL);
                danhSachSanPhamNhap.get(i).setThanhTien(newSL * danhSachSanPhamNhap.get(i).getDonGia());
                break;
            }
        }
        
    }
    
    public long tinhTongHangChoNhap() {
        long tong = 0;
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            tong += danhSachSanPhamNhap.get(i).getThanhTien();
        }
        return tong;
    }
    
     private void resetUI() {
        jTfMaSP.setText("");
        jTfTenSP.setText("");
        jTfDonGia.setText("");
        jSpSoLuong.setValue(0);
        
        jBtnNhapHang.setEnabled(false);
        jBtnThayDoiSoLuong.setEnabled(false);
        jBtnXoaHang.setEnabled(false);
        jBtnXacNhan.setEnabled(false);
        jBtnPreviewPN.setEnabled(false);
    }
    
    public void setEvent() {
        jBtnNhapHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jTfMaSP.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để nhập", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (jTfDonGia.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Đơn giá không được để trống", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    int maSanPham = Integer.valueOf(jTfMaSP.getText());
                    String tenSanPham = jTfTenSP.getText();
                    


                    long donGia = Long.valueOf(jTfDonGia.getText());
                    int soLuongNhap = Integer.parseInt(jSpSoLuong.getValue().toString());
                    
                    if (donGia <= 0 || soLuongNhap <= 0) {
                        JOptionPane.showMessageDialog(null, "Đơn giá và số lượng phải là số nguyên dương", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    ChiTietPhieuNhap ctPhieuNhap = new ChiTietPhieuNhap();
                    ctPhieuNhap.setMaSanPham(maSanPham);
                    ctPhieuNhap.setTenSanPham(tenSanPham);
                    ctPhieuNhap.setDonGia(donGia);
                    ctPhieuNhap.setSoLuong(soLuongNhap);
                    ctPhieuNhap.setThanhTien(donGia * soLuongNhap);
                    
                    themSanPhamVaoDanhSachNhap(ctPhieuNhap);
                    showDanhSachSanPhamNhap();
                    
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng", "Lối", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jBtnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {                   
                    if (jTfDonGia.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Đơn giá không được để trống!!");
                        return;
                    }
                    
                    int maSanPham = Integer.valueOf(jTfMaSP.getText());
                    String tenSanPham = jTfTenSP.getText();
                    
                    long donGia = Integer.valueOf(jTfDonGia.getText());
                    int soLuongNhap = Integer.parseInt(jSpSoLuong.getValue().toString());
                    
                    if (donGia <= 0 || soLuongNhap <= 0) {
                        JOptionPane.showMessageDialog(null, "Đơn giá và số lượng phải là số nguyên dương!!");
                        return;
                    }
                    for (ChiTietPhieuNhap PN:danhSachSanPhamNhap){
                        if (PN.getMaSanPham()==maSanPham){ 
                            PN.setDonGia(donGia);
                            PN.setSoLuong(soLuongNhap);
                            PN.setThanhTien(donGia*soLuongNhap);
                            break;
                        }
                    }

                    showDanhSachSanPhamNhap();

                    
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
        jBtnXoaHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jTableHangChoNhap.getSelectedRow() != -1) {
                        DefaultTableModel model = (DefaultTableModel) jTableHangChoNhap.getModel();
                        int selectedRowIndex = jTableHangChoNhap.getSelectedRow();

                        int maSanPham = (int) model.getValueAt(selectedRowIndex, 0);
                        
                        xoaSanPhamKhoiDanhSachNhap(maSanPham);
                        showDanhSachSanPhamNhap();
                        return;
                    }
                    
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng để xóa!");
                    
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        jBtnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    if (danhSachSanPhamNhap.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để nhập!");
                        return;
                    }
                    
                    NhaCungCap nhaCC = (NhaCungCap) jComboBoxNhaCC.getSelectedItem();
                    
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    
                    String ngayLap = sdf.format(date);
                    
                    long tongTien = tinhTongHangChoNhap();
                    
                    PhieuNhap phieuNhap = new PhieuNhap();
                    phieuNhap.setMaNhaCungCap(nhaCC.getMancc());
                    phieuNhap.setMaNhanVien(DangNhapController.taiKhoanLogin.getMaNV());
                    phieuNhap.setNgayLap(ngayLap);
                    phieuNhap.setTongTien(tongTien);
                    
                    PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAOImpl();
                    ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAOImpl();
                    SanPhamDAO sanPhamDAO =  new SanPhamDAOImpl();
                    
                    int maPhieuNhap = phieuNhapDAO.create(phieuNhap);
                    if (maPhieuNhap !=0) {
                        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
                            danhSachSanPhamNhap.get(i).setMaPhieuNhap(maPhieuNhap);
                            ctPhieuNhapDAO.create(danhSachSanPhamNhap.get(i));
                            sanPhamDAO.updateSoLuong(
                                danhSachSanPhamNhap.get(i).getMaSanPham(), 
                                danhSachSanPhamNhap.get(i).getSoLuong()
                            );
                        }
                        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");
                        loadSanPhamToTable();
                        danhSachSanPhamNhap.clear();
                        showDanhSachSanPhamNhap();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                    }

                    
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        jBtnThayDoiSoLuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    if (jTableHangChoNhap.getSelectedRow() != -1) {
                        DefaultTableModel model = (DefaultTableModel) jTableHangChoNhap.getModel();
                        int selectedRowIndex = jTableHangChoNhap.getSelectedRow();

                        int maSanPham = (int) model.getValueAt(selectedRowIndex, 0);
                        int sl = (int) model.getValueAt(selectedRowIndex, 3);
                        
                        String response = (String)JOptionPane.showInputDialog(
                            null,
                            "Vui lòng nhập số lượng mới!!",
                            "Cập nhật số lượng cho sản phẩm " + maSanPham,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            sl);
                        if (response == null) {
                            return;
                        }
                        if (response.equals("")) {
                            JOptionPane.showMessageDialog(null, "Không để trống!");
                            return;
                        }
                        try {
                            int newSL = Integer.parseInt(response);
                            if (newSL <= 0) {
                                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương!!");
                                return;
                            }
                            capNhatSoLuongSanPham(maSanPham, newSL);
                            showDanhSachSanPhamNhap();
                        } catch (NumberFormatException  nfe) {
                            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương!!");
                        }
                        
                      
                        return;
                    }
                    
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng để cập nhật!");
                    
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        
        jBtnPreviewPN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String nhanVien = DangNhapController.taiKhoanLogin.getHoTen();
                    NhaCungCap nhaCC = (NhaCungCap) jComboBoxNhaCC.getSelectedItem();
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
                    hd += "<h1 style='text-align:center;'>PHIẾU NHẬP</h1>";
                    hd += "<p style='text-align:center;'>Nhân viên: " + nhanVien + "</p>";
                    hd += "<p style='text-align:center;'>Ngày lập: " + dtf.format(now) + "</p>";                    
                    hd += "<p style='text-align:center;'>Nhà cung cấp: " +  nhaCC.toString() + "</p>";

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
                    long tongTien = tinhTongHangChoNhap();
                    for (ChiTietPhieuNhap ctPhieuNhap : danhSachSanPhamNhap) {
                        hd += "<tr>";
                        hd += "<td style='text-align:center;'>" + ctPhieuNhap.getMaSanPham()+ "</td>";
                        hd += "<td style='text-align:left;'>" + ctPhieuNhap.getTenSanPham()+ "</td>";
                        hd += "<td style='text-align:center;'>" + ctPhieuNhap.getSoLuong() + "</td>";
                        hd += "<td style='text-align:center;'>" + dcf.format(ctPhieuNhap.getDonGia())+ "</td>";
                        hd += "<td style='text-align:center;'>" + dcf.format(ctPhieuNhap.getThanhTien()) + "</td>";
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
                    JDialog dialog = new JDialog(new Frame(), "Thông tin phiếu nhập", true);
                    dialog.setLocationRelativeTo(jPnTabPhieuNhap);
                    JEditorPane editorPane = new JEditorPane();
                    editorPane.setContentType("text/html");
                    editorPane.setText(hd);
                    dialog.add(editorPane);
                    dialog.pack();
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Dimension screenSize = toolkit.getScreenSize();
                    int x = (screenSize.width - dialog.getWidth()) / 2;
                    int y = (screenSize.height - dialog.getHeight()) / 2;
                    dialog.setLocation(x, y);
                                        dialog.setVisible(true);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = jTabbedPane.getSelectedIndex();
                if (index == 1) {
                    loadPhieuNhapToTable();
                }
            }
        });
        
        jBtnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jTfPriceFrom.getText().equals("") && jTfPriceTo.getText().equals("")) {
                        isFiltering = false;
                        loadPhieuNhapToTable();
                        return;
                    }
                    if (jTfPriceFrom.getText().equals("") || jTfPriceTo.getText().equals("")) {
                        isFiltering = false;
                        return;
                    }
                    isFiltering = true;
                    long priceFrom = Long.valueOf(jTfPriceFrom.getText());
                    long priceTo = Long.valueOf(jTfPriceTo.getText());
                  
                    PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAOImpl();
                    danhSachPhieuNhap = phieuNhapDAO.getListFilterPrice(priceFrom, priceTo);
                    loadPhieuNhapToTable();
                    
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
    }
}

