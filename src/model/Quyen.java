package model;

public class Quyen {
    private String chucVu;
    private String tenLoaiQuanLy;
    private int create;
    private int read;
    private int update;
    private int delete;

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getTenLoaiQuanLy() {
        return tenLoaiQuanLy;
    }

    public void setTenLoaiQuanLy(String tenLoaiQuanLy) {
        this.tenLoaiQuanLy = tenLoaiQuanLy;
    }

    public int getCreate() {
        return create;
    }

    public void setCreate(int create) {
        this.create = create;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public Quyen() {
    }
    

    public Quyen(String chucVu, String tenLoaiQuanLy, int create, int read, int update, int delete) {
        this.chucVu = chucVu;
        this.tenLoaiQuanLy = tenLoaiQuanLy;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Quyen{" + "chucVu=" + chucVu + ", tenloaiquanly=" + tenLoaiQuanLy + ", create=" + create + ", read=" + read + ", update=" + update + ", delete=" + delete + '}';
    }
    
    
}
