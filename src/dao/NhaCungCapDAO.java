/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.NhaCungCap;

/**
 *
 * @author Home
 */
public interface NhaCungCapDAO {
    
    public List<NhaCungCap> getList();
    public NhaCungCap getByMaNCC(int maNCC);
    public boolean create(NhaCungCap nhaCungCap);
    public boolean update(NhaCungCap nhaCungCap);
    public boolean delete(NhaCungCap nhaCungCap);

    
}
