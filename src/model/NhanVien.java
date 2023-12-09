package model;

public class NhanVien {

    private int maNV;
    private String hoTen;
    private String taiKhoan;
    private String matKhau;
    private String chucVu;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTen, String taiKhoan,String matKhau, String chucVu) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoTen=" + hoTen + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", chucVu=" + chucVu + '}';
    }
    
    
}
