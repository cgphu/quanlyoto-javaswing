/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NhanVienDAO;
import dao.NhanVienDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.NhanVien;
import utility.ClassTableModel;

import controller.DangNhapController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utility.XuatExcel;

/**
 *
 * @author Home
 */
public class QuanLyNhanVienController {

    private final JPanel jPnView;
    private final JButton jBtnAdd;
    private final JButton jBtnUpdate;
    private final JButton jBtnDelete;

    private final JButton jBtnReset;
    private final JButton jBtnXuatExcel;

    private final JTextField jTfSearch;
    private final JTextField jTfMaNv;
    private final JTextField jTfHoTen;
    private final JComboBox jCbChucVu1;

    private final JTextField jTfTaiKhoan;
    private final JTextField jTfMatKhau;

//    private final JTextField jTfChucVu;
    private NhanVien nhanVien = null;
    private NhanVienDAO nhanVienDAO = null;

    private DangNhapController controllerDangNhap;

    private final String[] listColumn = {"STT", "Mã NV", "Tên NV", "Chức vụ", "Tài Khoản", "Mật khẩu"};

    private TableRowSorter<TableModel> rowSorter = null;

    JTable table;

    public QuanLyNhanVienController(
            JPanel jPnView,
            JButton jBtnAdd,
            JButton jBtnUpdate,
            JButton jBtnDelete,
            JButton jBtnReset,
            JButton jBtnXuatExcel,
            JTextField jTfMaNv,
            JTextField jTfHoTen,
            JComboBox jCbChucVu1,
            JTextField jTfTaiKhoan,
            JTextField jTfMatKhau,
            JTextField jTfSeach) {
        this.jPnView = jPnView;
        this.jBtnAdd = jBtnAdd;
        this.jBtnUpdate = jBtnUpdate;
        this.jBtnDelete = jBtnDelete;
        this.jBtnReset = jBtnReset;
        this.jBtnXuatExcel = jBtnXuatExcel;
        this.jTfHoTen = jTfHoTen;
        this.jTfMaNv = jTfMaNv;
        this.jTfTaiKhoan = jTfTaiKhoan;
        this.jTfMatKhau = jTfMatKhau;

        this.jCbChucVu1 = jCbChucVu1;
        this.jTfSearch = jTfSeach;

        this.nhanVienDAO = new NhanVienDAOImpl();
        this.nhanVien = new NhanVien();

        controllerDangNhap = new DangNhapController();

        if (controllerDangNhap.taiKhoanLogin.getChucVu().equals("Quản trị")) {
            jCbChucVu1.addItem("Quản lý");
            jCbChucVu1.addItem("Quản trị");
        }
    }

    public void setDataToTable() {
        resetData();
        String chucVu = controllerDangNhap.taiKhoanLogin.getChucVu();

        List<NhanVien> listItem = nhanVienDAO.getList(chucVu);

        DefaultTableModel model = new ClassTableModel().setTableNhanVien(listItem, listColumn);
        table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(rowSorter);

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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRowIndex = table.getSelectedRow();

                    NhanVien nhanVien = new NhanVien();
                    int manv = (int) (table.getValueAt(selectedRowIndex, 1));
                    nhanVien.setMaNV(manv);
                    nhanVien.setHoTen(table.getValueAt(selectedRowIndex, 2).toString());
                    nhanVien.setChucVu(table.getValueAt(selectedRowIndex, 3).toString());
                    nhanVien.setTaiKhoan(table.getValueAt(selectedRowIndex, 4).toString());
                    nhanVien.setMatKhau(table.getValueAt(selectedRowIndex, 5).toString());

                    jTfMaNv.setText((nhanVien.getMaNV()) + "");
                    jTfHoTen.setText(nhanVien.getHoTen());
                    jCbChucVu1.setSelectedItem(nhanVien.getChucVu());
                    jTfTaiKhoan.setText(nhanVien.getTaiKhoan());
                    jTfMatKhau.setText(nhanVien.getMatKhau());

                    jBtnAdd.setEnabled(false);
                    jBtnUpdate.setEnabled(true);
                    if (nhanVien.getChucVu().equals("Quản trị") == false) {
                        jBtnDelete.setEnabled(true);
                    }
                    jBtnReset.setEnabled(true);
                }
            }

        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(30);
        table.validate();
        table.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 280));

        jPnView.removeAll();
        jPnView.setLayout(new BorderLayout());
        jPnView.add(scrollPane);
        jPnView.validate();
        jPnView.repaint();
    }

    public void setEvent() {
        jBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String tennv = jTfHoTen.getText();

                    String chucVu = jCbChucVu1.getSelectedItem().toString();
                    String taikhoan = jTfTaiKhoan.getText();

                    String matkhau = jTfMatKhau.getText();
                    if (tennv.equals("") || chucVu.equals("") || taikhoan.equals("") || matkhau.equals("")) {
                        JOptionPane.showMessageDialog(null, "Du lieu khong duoc de trong!");
                    } else {
                        String pattern, pattern1;
                        String title = "Thông báo";
                        pattern = "^([A-Z]){1}([\\w_\\.!@#$%^&*()]+){5,31}$";

                        pattern1 = "^[A-Za-z0-9]{6,32}$";
                        boolean matchFound1 = taikhoan.matches(pattern1);
                        if (!matchFound1) {
                            JOptionPane.showMessageDialog(null, "Tai Khoan sai định dạng can do dai tu 6 ki tu", title, JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        boolean matchFound = matkhau.matches(pattern);
                        if (!matchFound) {
                            JOptionPane.showMessageDialog(null, "Mat khau sai định dạng can do dai tu 6 ki tu va ki tu dau viet hoa", title, JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        nhanVien.setHoTen(tennv.trim());
                        nhanVien.setChucVu(chucVu.trim());
                        nhanVien.setTaiKhoan(taikhoan.trim());
                        nhanVien.setMatKhau(matkhau.trim());

//                        System.out.println(nhanVien);
                        int result = nhanVienDAO.create(nhanVien);
                        if (result != 0) {
                            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");

                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

        jBtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String manv = jTfMaNv.getText();
                    String tennv = jTfHoTen.getText();

                    String chucVu = jCbChucVu1.getSelectedItem().toString();
                    String taikhoan = jTfTaiKhoan.getText();

                    String matkhau = jTfMatKhau.getText();
                    if (manv.equals("") || tennv.equals("") || chucVu.equals("")) {
                        JOptionPane.showMessageDialog(null, "Ma NV, Ten NV, chuc vu khong duoc de trong!");
                    } else {
                        nhanVien.setMaNV(Integer.parseInt(manv));
                        nhanVien.setHoTen(tennv.trim());

                        nhanVien.setChucVu(chucVu.trim());
                        nhanVien.setTaiKhoan(taikhoan.trim());
                        nhanVien.setMatKhau(matkhau.trim());

                        boolean result = nhanVienDAO.update(nhanVien);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công");
                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });

        jBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String manv = jTfMaNv.getText();
                    String tennv = jTfHoTen.getText();

                    String chucVu = jCbChucVu1.getSelectedItem().toString();

                    String taikhoan = jTfTaiKhoan.getText();

                    String matkhau = jTfMatKhau.getText();
                    if (manv.equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui long chon NV!");
                    } else {
                        nhanVien.setMaNV(Integer.parseInt(manv));
                        nhanVien.setHoTen(tennv.trim());
                        nhanVien.setChucVu(chucVu.trim());

                        nhanVien.setTaiKhoan(taikhoan.trim());
                        nhanVien.setMatKhau(matkhau.trim());
//                        System.out.println(nhaCungCap);

                        boolean result = nhanVienDAO.delete(nhanVien);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });

        jBtnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetData();
            }
        });

        jBtnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new XuatExcel(table);
            }
        });
    }

    public void resetData() {
        jTfMaNv.setText("");
        jTfHoTen.setText("");
        jCbChucVu1.setSelectedItem("Nhân viên");
        jTfTaiKhoan.setText("");
        jTfMatKhau.setText("");

        jBtnAdd.setEnabled(true);
        jBtnUpdate.setEnabled(false);
        jBtnDelete.setEnabled(false);
        jBtnReset.setEnabled(false);
    }
}
