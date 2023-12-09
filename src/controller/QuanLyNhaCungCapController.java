/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NhaCungCapDAO;
import dao.NhaCungCapDAOImpl;
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
import model.NhaCungCap;
import utility.ClassTableModel;
import utility.XuatExcel;

/**
 *
 * @author Home
 */
public class QuanLyNhaCungCapController {
    private JPanel jPnView;
    private JButton jBtnAdd;    
    private JButton jBtnUpdate;    
    private JButton jBtnDelete;    
    
    private JButton jBtnReset;
    private JButton jBtnXuatExcel;

    
    private JTextField jTfSearch;
    private JTextField jTfMaNcc;    
    private JTextField jTfTen;

    private JTextField jTfSDT;
    private JTextField jTfDiaChi;
    
    private NhaCungCap nhaCungCap = null;
    private NhaCungCapDAO nhaCungCapDAO = null;
    
    private String[] listColumn = {"STT", "Mã NCC", "Tên NCC", "Số điện thoại", "Địa chỉ"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    JTable table;

    public QuanLyNhaCungCapController(
            JPanel jPnView, 
            JButton jBtnAdd, 
            JButton jBtnUpdate, 
            JButton jBtnDelete, 
            JButton jBtnReset, 
            JButton jBtnXuatExcel,
            JTextField jTfMaNcc, 
            JTextField jTfTen, 
            JTextField jTfSDT, 
            JTextField jTfDiaChi, 
            JTextField jTfSearch
    ) {
        this.jPnView = jPnView;
        this.jBtnAdd = jBtnAdd;
        this.jBtnUpdate = jBtnUpdate;
        this.jBtnDelete = jBtnDelete;
        this.jBtnReset = jBtnReset;
        this.jBtnXuatExcel = jBtnXuatExcel;
        this.jTfTen = jTfTen;        
        this.jTfMaNcc = jTfMaNcc;

        this.jTfSDT = jTfSDT;
        this.jTfDiaChi = jTfDiaChi;
        this.jTfSearch = jTfSearch;
        
        this.nhaCungCapDAO = new NhaCungCapDAOImpl();
        this.nhaCungCap = new NhaCungCap();
       
    }
    
    public void setDataToTable() {
        
        jTfMaNcc.setText("");
        jTfTen.setText("");        
        jTfDiaChi.setText("");
        jTfSDT.setText("");
        
        jBtnAdd.setEnabled(true);
        jBtnUpdate.setEnabled(false);
        jBtnDelete.setEnabled(false);
        jBtnReset.setEnabled(false);
        
         
        List<NhaCungCap> listItem = nhaCungCapDAO.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableNhaCungCap(listItem, listColumn);
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
                    
                    NhaCungCap nhaCungCap = new NhaCungCap();
                    int mancc = (int) (table.getValueAt(selectedRowIndex, 1));
                    nhaCungCap.setMancc(mancc);
                    nhaCungCap.setTenncc(table.getValueAt(selectedRowIndex, 2).toString());
                    nhaCungCap.setDienthoai(table.getValueAt(selectedRowIndex, 3).toString());
                    nhaCungCap.setDiachi(table.getValueAt(selectedRowIndex, 4).toString());
                    
//                    System.out.println(nhaCungCap.getMacc());
                    
                    jTfMaNcc.setText((nhaCungCap.getMancc()) + "");
                    jTfTen.setText(nhaCungCap.getTenncc());
                    jTfSDT.setText(nhaCungCap.getDienthoai() + "");
                    jTfDiaChi.setText(nhaCungCap.getDiachi());
                    
                    jBtnAdd.setEnabled(false);
                    jBtnUpdate.setEnabled(true);
                    jBtnDelete.setEnabled(true);
                    jBtnReset.setEnabled(true);
                }
            }
            
        });
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
//        JScrollPane scrollPane = new JScrollPane();
        JScrollPane scrollPane = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
                    String tenncc = jTfTen.getText();
                    String sdt = jTfSDT.getText();
                    String diachi = jTfDiaChi.getText();
                    if (tenncc.equals("") || sdt.equals("") || diachi.equals("")) {
                        JOptionPane.showMessageDialog(null, "Ten NCC, So dien thoai, Dia chi khong duoc de trong!");
                    } else {
                        nhaCungCap.setTenncc(tenncc.trim());
                        nhaCungCap.setDienthoai(sdt);
                        nhaCungCap.setDiachi(diachi.trim());
                        
//                        System.out.println(nhaCungCap);
                
                        boolean result = nhaCungCapDAO.create(nhaCungCap);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");
                            
                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        
        jBtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String mancc = jTfMaNcc.getText();
                    String tenncc = jTfTen.getText();
                    String sdt = jTfSDT.getText();
                    String diachi = jTfDiaChi.getText();
                    if (mancc.equals("") || tenncc.equals("") || sdt.equals("") || diachi.equals("")) {
                        JOptionPane.showMessageDialog(null, "Ma NCC, Ten NCC, So dien thoai, Dia chi khong duoc de trong!");
                    } else {
                        nhaCungCap.setMancc(Integer.parseInt(mancc));
                        nhaCungCap.setTenncc(tenncc.trim());
                        nhaCungCap.setDienthoai(sdt);
                        nhaCungCap.setDiachi(diachi.trim());
                        
                        boolean result = nhaCungCapDAO.update(nhaCungCap);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công");
                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        
        jBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String mancc = jTfMaNcc.getText();
                    String tenncc = jTfTen.getText();
                    String sdt = jTfSDT.getText();
                    String diachi = jTfDiaChi.getText();
                    if (mancc.equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui long chon NCC!");
                    } else {
                        nhaCungCap.setMancc(Integer.parseInt(mancc));
                        nhaCungCap.setTenncc(tenncc.trim());
                        nhaCungCap.setDienthoai(sdt);
                        nhaCungCap.setDiachi(diachi.trim());
                        
//                        System.out.println(nhaCungCap);
                
                        boolean result = nhaCungCapDAO.delete(nhaCungCap);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công");
                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        
        jBtnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDataToTable();
            }
        });
        
        jBtnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new XuatExcel(table);
            }
        });
    }
}

