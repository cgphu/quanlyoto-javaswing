/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import utility.FileChooser;
import dao.SanPhamDAO;
import dao.SanPhamDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.SanPham;
import utility.ClassTableModel;
import utility.XuatExcel;

/**
 *
 * @author ASUS
 */
public class QuanLySanPhamController {

    private JPanel jPnView;

    private JButton jBtnAdd;
    private JButton jBtnUpdate;
    private JButton jBtnDelete;
    private JButton jBtnReset;
    private JButton jBtnXuatExcel;
    private JButton jBtnChonAnh;

    private JLabel jLbAnh;

    private JTextField jTfSearch;
    private JTextField jTfMaSP;
    private JComboBox jCbLoai;
    private JTextField jTfTen;
    private JTextField jTfSoLuong;
    private JTextField jTfGia;
    private JTextField jTfHanBaoHanh;

    private SanPham sanPham = null;
    private SanPhamDAO sanPhamDAO = null;

    private String[] listColumn = {"STT", "Mã SP", "Loại", "Tên", "Số lượng", "Giá", "Hạn bảo hành", "Ảnh"};

    private TableRowSorter<TableModel> rowSorter = null;

    File fileAnhSP;
    
    JTable table;

    public QuanLySanPhamController(
            JPanel jPnView, 
            JButton jBtnAdd, 
            JButton jBtnUpdate, 
            JButton jBtnDelete, 
            JButton jBtnReset,
            JButton jBtnXuatExcel,
            JButton jBtnChonAnh, 
            JLabel jLbAnh, 
            JTextField jTfSearch, 
            JTextField jTfMaSP, 
            JComboBox jCbLoai, 
            JTextField jTfTen, 
            JTextField jTfSoLuong, 
            JTextField jTfGia, 
            JTextField jTfHanBaoHanh
    ) {
        this.jPnView = jPnView;

        this.jBtnAdd = jBtnAdd;
        this.jBtnUpdate = jBtnUpdate;
        this.jBtnDelete = jBtnDelete;
        this.jBtnReset = jBtnReset;
        this.jBtnChonAnh = jBtnChonAnh;
        this.jBtnXuatExcel = jBtnXuatExcel;

        this.jLbAnh = jLbAnh;

        this.jTfSearch = jTfSearch;
        this.jTfMaSP = jTfMaSP;
        this.jCbLoai = jCbLoai;
        this.jTfTen = jTfTen;
        this.jTfSoLuong = jTfSoLuong;
        this.jTfGia = jTfGia;
        this.jTfHanBaoHanh = jTfHanBaoHanh;

        this.sanPhamDAO = new SanPhamDAOImpl();
        this.sanPham = new SanPham();
    }

    public void setDataToTable() {
        resetData();
        List<SanPham> listItem = sanPhamDAO.getList();

        DefaultTableModel model = new ClassTableModel().setTableSanPham(listItem, listColumn);
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

        jCbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loai = jCbLoai.getSelectedItem() + "";
                if (loai.equals("Phụ tùng")) {
                    jTfHanBaoHanh.setText("0");
                    jTfHanBaoHanh.setEditable(false);
                } else {
                    jTfHanBaoHanh.setEditable(true);
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRowIndex = table.getSelectedRow();

                    SanPham sanPham = new SanPham();
                    sanPham.setMaSanPham((int) table.getValueAt(selectedRowIndex, 1));
                    sanPham.setLoai(table.getValueAt(selectedRowIndex, 2).toString());
                    sanPham.setTen(table.getValueAt(selectedRowIndex, 3).toString());
                    sanPham.setSoLuong((int) table.getValueAt(selectedRowIndex, 4));
                    sanPham.setGia(Long.parseLong((table.getValueAt(selectedRowIndex, 5) + "").replace(",", "").replace(" VNĐ", "")));
                    sanPham.setHanBaoHanh(Integer.parseInt((table.getValueAt(selectedRowIndex, 6) + "").replace(" năm", "")));
                    sanPham.setAnh(table.getValueAt(selectedRowIndex, 7).toString());

                    jTfMaSP.setText(Integer.toString(sanPham.getMaSanPham()));
                    jCbLoai.setSelectedItem(sanPham.getLoai());
                    jTfTen.setText(sanPham.getTen());
                    jTfSoLuong.setText(sanPham.getSoLuong() + "");
                    jTfGia.setText(sanPham.getGia() + "");
                    jTfHanBaoHanh.setText(sanPham.getHanBaoHanh() + "");
                    loadAnh("src/images/SanPham/" + sanPham.getAnh());

                    jBtnAdd.setEnabled(false);
                    jBtnUpdate.setEnabled(true);
                    jBtnDelete.setEnabled(true);
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
        String title = "Thông báo";
        jBtnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });

        jBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validate() == false) {
                        return;
                    }

                    sanPham.setLoai(jCbLoai.getSelectedItem() + "");
                    sanPham.setTen(jTfTen.getText().trim());
                    sanPham.setSoLuong(0);
                    sanPham.setGia(Long.parseLong(jTfGia.getText().trim().replace(",", "").replace(" VNĐ", "")));
                    sanPham.setHanBaoHanh(Integer.parseInt(jTfHanBaoHanh.getText().trim().replace(" năm", "")));
                    sanPham.setAnh(fileAnhSP.getName());

                    int lastId = sanPhamDAO.createSanPham(sanPham);
                    if (lastId != 0) {
                        luuFileAnh();
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công", title, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại", title, JOptionPane.ERROR_MESSAGE);
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

                    sanPham.setMaSanPham(Integer.parseInt(jTfMaSP.getText()));
                    sanPham.setLoai(jCbLoai.getSelectedItem() + "");
                    sanPham.setTen(jTfTen.getText().trim());
                    sanPham.setSoLuong(0);
                    sanPham.setGia(Integer.parseInt(jTfGia.getText().trim().replace(",", "")));
                    sanPham.setHanBaoHanh(Integer.parseInt(jTfHanBaoHanh.getText().trim()));
                    sanPham.setAnh(fileAnhSP.getName());

                    int result = sanPhamDAO.updateSanPham(sanPham);
                    if (result != 0) {
                        luuFileAnh();
                        JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công", title, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại", title, JOptionPane.ERROR_MESSAGE);
                    }

                    setDataToTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(), title, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int maSP = Integer.parseInt(jTfMaSP.getText());

                    int result = sanPhamDAO.deleteSanPham(maSP);
                    if (result != 0) {
                        JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công", title, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sản phẩm đã có người mua, không được xóa!", title, JOptionPane.ERROR_MESSAGE);
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

    private void resetData() {
        jTfMaSP.setText("");
        jCbLoai.setSelectedItem("-- Chọn loại --");
        jTfTen.setText("");
        jTfSoLuong.setText("");
        jTfGia.setText("");
        jTfHanBaoHanh.setText("");
        loadAnh("");

        jBtnAdd.setEnabled(true);
        jBtnUpdate.setEnabled(false);
        jBtnDelete.setEnabled(false);
    }

    private boolean validate() {
        String title = "Thông báo";
        String pattern;
        String loai = jCbLoai.getSelectedItem().toString();
        String ten = jTfTen.getText();
        String gia = jTfGia.getText();
        String hanBaoHanh = jTfHanBaoHanh.getText();

        if (loai.equals("-- Chọn loại --")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại sản phẩm!", title, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (ten.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm!", title, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (gia.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá!", title, JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            pattern = "^[1-9][0-9]{0,2}((\\,\\d{3}){1,3})|[1-9][0-9]{5,11}$";
            boolean matchFound = gia.matches(pattern);
            if (!matchFound) {
                JOptionPane.showMessageDialog(null, "Giá sai định dạng", title, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (hanBaoHanh.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập hạn bảo hành!", title, JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            pattern = loai.equals("Ô tô") ? "^(3|4|5)$" : "^0$";
            boolean matchFound = hanBaoHanh.matches(pattern);
            if (!matchFound) {
                JOptionPane.showMessageDialog(null, "Hạn bảo hành nhập chưa đúng", title, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private void loadAnh(String anh) {
        jLbAnh.setIcon(getAnhSP(anh));
    }

    private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("src/images/SanPham/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void xuLyChonAnh() {
        JFileChooser fileChooser = new FileChooser("src/images/SanPham/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            jLbAnh.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
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
