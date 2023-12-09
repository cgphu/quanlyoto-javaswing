/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NhaCungCapDAO;
import dao.NhaCungCapDAOImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.NhaCungCap;

/**
 *
 * @author ASUS
 */
public class ThemNCCController {

    private JFrame root;
    
    private JTextField jTfTen;
    private JTextField jTfDiaChi;
    private JTextField jTfSDT;
    
    private JButton jBtnThemNCC;
    
    private JComboBox jCb;

    public ThemNCCController(JTextField jTfTen, JTextField jTfDiaChi, JTextField jTfSDT, JButton jBtnThemNCC, JFrame root,JComboBox jCb) {
        this.jTfTen = jTfTen;
        this.jTfDiaChi = jTfDiaChi;
        this.jTfSDT = jTfSDT;
        this.jBtnThemNCC=jBtnThemNCC;
        this.root=root;
        this.jCb=jCb;
    }
    public void setEvent(){
        root.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jBtnThemNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    NhaCungCap nhaCungCap=new NhaCungCap();
                    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAOImpl();
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
 
                            List<NhaCungCap> listNhaCC = nhaCungCapDAO.getList();
                            for (int i = 0; i < listNhaCC.size(); i++) {
                                jCb.addItem(listNhaCC.get(i));
                            }
                            root.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
    }
}
