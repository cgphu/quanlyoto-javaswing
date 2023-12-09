/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class DanhMucBean {
    private String kind;    
    private JPanel jPn;
    private JLabel jLb;

    public DanhMucBean(String kind, JPanel jPn, JLabel jLb) {
        this.kind = kind;
        this.jPn = jPn;
        this.jLb = jLb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getjPn() {
        return jPn;
    }

    public void setjPn(JPanel jPn) {
        this.jPn = jPn;
    }

    public JLabel getjLb() {
        return jLb;
    }

    public void setjLb(JLabel jLb) {
        this.jLb = jLb;
    }
}
