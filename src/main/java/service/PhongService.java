package service;

import java.util.List;

import entity.Phong;

public interface PhongService  {
	List<Phong> layDanhSachPhong();
	List<Phong> layDanhSachPhongTheoTrangThaiLoaiPhongSoNguoi(int trangThaiPhong, int LoaiPhong, int soNguoi);
	Phong layThongTinPhongQuaMa(String maPhong);
	boolean capNhatTrangThaiPhong(String maPhong, String maTTP);
	int laySoLuongPhongTheoTrangThai(int soNguoi);
}
