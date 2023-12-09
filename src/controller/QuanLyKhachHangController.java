/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KhachHangDAO;
import dao.KhachHangDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
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
import model.KhachHang;
import utility.ClassTableModel;
import utility.XuatExcel;

/**
 *
 * @author ASUS
 */
public class QuanLyKhachHangController {

    private JPanel jPnView;
    private JTextField jTfSearch;
    private JButton jBtnAdd;
    private JButton jBtnUpdate;
    private JButton jBtnDelete;
    private JButton jBtnReset;
    private JButton jBtnXuatExcel;

    private JTextField jTfMaKH;
    private JTextField jTfHoTen;
    private JTextField jTfSDT;
    private JTextArea jTaDiaChi;

    private KhachHang khachHang = null;
    private KhachHangDAO khachHangDAO = null;

    private String[] listColumn = {"STT", "Mã KH", "Họ tên", "Số điện thoại", "Địa chỉ"};

    private TableRowSorter<TableModel> rowSorter = null;
    
    private JTable table;

    public QuanLyKhachHangController(
            JPanel jPnView, 
            JTextField jTfSearch, 
            JButton jBtnAdd, 
            JButton jBtnUpdate,
            JButton jBtnDelete, 
            JButton jBtnReset, 
            JButton jBtnXuatExcel,
            JTextField jTfMaKhachHang, 
            JTextField jTfHoTen, 
            JTextField jTfDienThoai, 
            JTextArea jTaDiaChi
    ) {
        this.jPnView = jPnView;
        this.jTfSearch = jTfSearch;
        this.jBtnAdd = jBtnAdd;
        this.jBtnUpdate = jBtnUpdate;
        this.jBtnDelete = jBtnDelete;
        this.jBtnReset = jBtnReset;
        this.jBtnXuatExcel = jBtnXuatExcel;
        
        this.jTfMaKH = jTfMaKhachHang;
        this.jTfHoTen = jTfHoTen;
        this.jTfSDT = jTfDienThoai;
        this.jTaDiaChi = jTaDiaChi;

        this.khachHangDAO = new KhachHangDAOImpl();
        this.khachHang = new KhachHang();
    }

    public void setDataToTable() {
        resetData();
        List<KhachHang> listItem = khachHangDAO.getList();

        DefaultTableModel model = new ClassTableModel().setTableKhachHang(listItem, listColumn);
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

                    KhachHang khachHang = new KhachHang();
                    khachHang.setMaKhachHang((int) table.getValueAt(selectedRowIndex, 1));
                    khachHang.setHoTen(table.getValueAt(selectedRowIndex, 2).toString());
                    khachHang.setDienThoai(table.getValueAt(selectedRowIndex, 3).toString());
                    khachHang.setDiaChi(table.getValueAt(selectedRowIndex, 4).toString());

                    jTfMaKH.setText(Integer.toString(khachHang.getMaKhachHang()));
                    jTfHoTen.setText(khachHang.getHoTen());
                    jTfSDT.setText(khachHang.getDienThoai());
                    jTaDiaChi.setText(khachHang.getDiaChi());

                    jBtnAdd.setEnabled(false);
                    jBtnUpdate.setEnabled(true);
                    jBtnDelete.setEnabled(true);
                    jBtnReset.setEnabled(true);
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
                    if (validate() == false) {
                        return;
                    }

                    khachHang.setHoTen(jTfHoTen.getText().trim());
                    khachHang.setDienThoai(jTfSDT.getText().trim());
                    khachHang.setDiaChi(jTaDiaChi.getText().trim());

                    int lastId = khachHangDAO.createKhachHang(khachHang);
                    if (lastId != 0) {
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                    }

                    setDataToTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        jBtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validate() == false) {
                        return;
                    }
                    khachHang.setMaKhachHang(Integer.parseInt(jTfMaKH.getText().trim()));
                    khachHang.setHoTen(jTfHoTen.getText().trim());
                    khachHang.setDienThoai(jTfSDT.getText().trim());
                    khachHang.setDiaChi(jTaDiaChi.getText().trim());

                    int result = khachHangDAO.updateKhachHang(khachHang);

                    if (result != 0) {
                        JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                    }

                    setDataToTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        jBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int makh = Integer.parseInt(jTfMaKH.getText().trim());
                    int result = khachHangDAO.deleteKhachHang(makh);

                    if (result != 0) {
                        JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                    }

                    setDataToTable();

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

    private boolean validate() {
        String title = "Thông báo";
        String pattern;
        String ten = jTfHoTen.getText();
        String sdt = jTfSDT.getText();
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

    private void resetData() {
        jTfMaKH.setText("");
        jTfHoTen.setText("");
        jTfSDT.setText("");
        jTaDiaChi.setText("");

        jBtnAdd.setEnabled(true);
        jBtnUpdate.setEnabled(false);
        jBtnDelete.setEnabled(false);
    }
}
