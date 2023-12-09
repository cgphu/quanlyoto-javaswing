/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.DanhMucBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.BanHangJPanel;
import view.KhachHangJPanel;
import view.NhaCungCapJPanel;
import view.NhanVienJPanel;
import view.NhapHangJPanel;
import view.SanPhamJPanel;
import view.ThongKeJPanel;

/**
 *
 * @author ASUS
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel root) {
        this.root = root;
    }
    
    public void setView(JPanel jPnItem, JLabel jLbItem) {
        kindSelected = "BanHang";
        
        jPnItem.setBackground(new Color(33,46,52));
        jLbItem.setBackground(new Color(33,46,52));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new BanHangJPanel());
        root.validate();
        root.repaint();
    }
    
    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for(DanhMucBean item: listItem) {
            item.getjLb().addMouseListener(new LabelEvent(item.getKind(), item.getjPn(), item.getjLb()));
        }
    }

    class LabelEvent implements MouseListener {
        private JPanel node;
        
        private String kind;
        private JPanel jPnItem;
        private JLabel jLbItem;

        public LabelEvent(String kind, JPanel jPnItem, JLabel jLbItem) {
            this.kind = kind;
            this.jPnItem = jPnItem;
            this.jLbItem = jLbItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "BanHang":
                    node = new BanHangJPanel();
                    break;
                case "SanPham":
                    node = new SanPhamJPanel();
                    break;
                case "NhanVien":
                    node = new NhanVienJPanel();
                    break;
                case "KhachHang":
                    node = new KhachHangJPanel();
                    break;
                case "NhapHang":
                    node = new NhapHangJPanel();
                    break;
                case "NhaCungCap":
                    node = new NhaCungCapJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jPnItem.setBackground(new Color(61, 86, 98));            
            jLbItem.setBackground(new Color(61, 86, 98));

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jPnItem.setBackground(new Color(61, 86, 98));            
            jLbItem.setBackground(new Color(61, 86, 98));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jPnItem.setBackground(new Color(33,46,52));            
                jLbItem.setBackground(new Color(33,46,52));
            }
        }
        
    }
    
    private void setChangeBackground(String kind) {
        for(DanhMucBean item: listItem) {
            if(item.getKind().equalsIgnoreCase(kind)) {
                item.getjPn().setBackground(new Color(80, 99, 126));
                item.getjLb().setBackground(new Color(80, 99, 126));
            } else {
                item.getjPn().setBackground(new Color(33,46,52));            
                item.getjLb().setBackground(new Color(33,46,52));
            }
        }
    }
}
