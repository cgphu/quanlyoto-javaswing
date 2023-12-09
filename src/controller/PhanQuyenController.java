package controller;

import dao.PhanQuyenDAOImpl;
import model.Quyen;
import utility.MyDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class PhanQuyenController {

    private JComboBox jCbChucVu;
    private JComboBox jCbQuyen;

    private JCheckBox jCheckBoxRead;
    private JCheckBox jCheckBoxCreate;
    private JCheckBox jCheckBoxUpdate;
    private JCheckBox jCheckBoxDelete;

    private JButton jBtnUpdateAll;

    public static Quyen quyenTK = null;
    private PhanQuyenDAOImpl phanQuyenDAO = new PhanQuyenDAOImpl();
    private ArrayList<Quyen> listPhanQuyen = null;

    private String chucVu;
    private String quyen;

    private String[] listQuyen = {"-- Chọn quyền --", "Quản lý sản phẩm", "Quản lý hóa đơn", "Quản lý nhân viên", "Quản lý khách hàng", "Quản lý nhà cung cấp", "Thống kê"};

    public PhanQuyenController(
            JComboBox jCbChucVu,
            JComboBox jCbQuyen,
            JCheckBox jCheckBoxRead,
            JCheckBox jCheckBoxCreate,
            JCheckBox jCheckBoxUpdate,
            JCheckBox jCheckBoxDelete,
            JButton jBtnUpdateAll
    ) {
        this.jCbChucVu = jCbChucVu;
        this.jCbQuyen = jCbQuyen;
        this.jCheckBoxRead = jCheckBoxRead;
        this.jCheckBoxCreate = jCheckBoxCreate;
        this.jCheckBoxUpdate = jCheckBoxUpdate;
        this.jCheckBoxDelete = jCheckBoxDelete;

        this.jBtnUpdateAll = jBtnUpdateAll;
    }

    public void setEvent() {
        jCbChucVu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chucVu = jCbChucVu.getSelectedItem().toString();
                if (chucVu.equals("-- Chọn chức vụ --") == false) {
                    resetData();
                    jCbQuyen.setEnabled(true);

                    jCbChucVu.setSelectedItem(chucVu);
                } else {
                    resetData();
                }
            }
        });

        jCbQuyen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quyen = jCbQuyen.getSelectedItem().toString();

                if (quyen.equals("-- Chọn quyền --") == false) {
                    jCheckBoxRead.setEnabled(true);
                    if (quyen.equals("Thống kê")) {
                        jCheckBoxCreate.setVisible(false);
                        jCheckBoxUpdate.setVisible(false);
                        jCheckBoxDelete.setVisible(false);
                    } else {
                        jCheckBoxCreate.setVisible(true);
                        jCheckBoxUpdate.setVisible(true);
                        jCheckBoxDelete.setVisible(true);
                    }

                    jBtnUpdateAll.setEnabled(true);

                    Quyen quyenDoiTuong = phanQuyenDAO.getQuyen(chucVu, quyen);

                    if (quyenDoiTuong.getRead() == 1) {
                        jCheckBoxRead.setSelected(true);

                        jCheckBoxCreate.setEnabled(true);
                        jCheckBoxUpdate.setEnabled(true);
                        jCheckBoxDelete.setEnabled(true);

                        if (quyenDoiTuong.getCreate() == 1) {
                            jCheckBoxCreate.setSelected(true);
                        } else {
                            jCheckBoxCreate.setSelected(false);
                        }

                        if (quyenDoiTuong.getUpdate() == 1) {
                            jCheckBoxUpdate.setSelected(true);
                        } else {
                            jCheckBoxUpdate.setSelected(false);
                        }

                        if (quyenDoiTuong.getDelete() == 1) {
                            jCheckBoxDelete.setSelected(true);
                        } else {
                            jCheckBoxDelete.setSelected(false);
                        }
                    } else {
                        jCheckBoxRead.setSelected(false);
                        jCheckBoxCreate.setEnabled(false);
                        jCheckBoxUpdate.setEnabled(false);
                        jCheckBoxDelete.setEnabled(false);
                    }
                }
            }
        });

        jCheckBoxRead.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    jCheckBoxCreate.setEnabled(true);
                    jCheckBoxUpdate.setEnabled(true);
                    jCheckBoxDelete.setEnabled(true);
                } else {
                    jCheckBoxCreate.setSelected(false);
                    jCheckBoxUpdate.setSelected(false);
                    jCheckBoxDelete.setSelected(false);

                    jCheckBoxCreate.setEnabled(false);
                    jCheckBoxUpdate.setEnabled(false);
                    jCheckBoxDelete.setEnabled(false);
                }
            }
        });

        jBtnUpdateAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int create = jCheckBoxCreate.isSelected() ? 1 : 0;
                int read = jCheckBoxRead.isSelected() ? 1 : 0;
                int update = jCheckBoxUpdate.isSelected() ? 1 : 0;
                int delete = jCheckBoxDelete.isSelected() ? 1 : 0;

                suaQuyen(chucVu, quyen, create, read, update, delete);
                resetData();
            }
        });
    }

    public void resetData() {
        jCheckBoxCreate.setSelected(false);
        jCheckBoxRead.setSelected(false);
        jCheckBoxUpdate.setSelected(false);
        jCheckBoxDelete.setSelected(false);

        jCheckBoxCreate.setEnabled(false);
        jCheckBoxRead.setEnabled(false);
        jCheckBoxUpdate.setEnabled(false);
        jCheckBoxDelete.setEnabled(false);

        jCbChucVu.setSelectedItem("-- Chọn chức vụ --");

        jCbQuyen.setSelectedItem("-- Chọn quyền --");
        jCbQuyen.setEnabled(false);

        jBtnUpdateAll.setEnabled(false);
    }

    public void docDanhSachQuyen() {
        this.listPhanQuyen = (ArrayList<Quyen>) phanQuyenDAO.getListQuyen();
    }

    public void kiemTraQuyen(String chucVu, String tenLoaiquanly) {
        quyenTK = phanQuyenDAO.getQuyen(chucVu, tenLoaiquanly);
    }

    public ArrayList<Quyen> getListQuyen() {
        if (listPhanQuyen == null) {
            docDanhSachQuyen();
        }
        return this.listPhanQuyen;
    }

    public boolean suaQuyen(String tenQuyen, String tenloaiquanly, int create, int read, int update, int delete) {
        Quyen phanQuyen = new Quyen(tenQuyen, tenloaiquanly, create, read, update, delete);
        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    private boolean kiemTonTaiTraQuyen(String tenQuyen) {
        docDanhSachQuyen();
        for (Quyen q : listPhanQuyen) {
            if (q.getChucVu().equalsIgnoreCase(tenQuyen)) {
                return true;
            }
        }
        return false;
    }

}
