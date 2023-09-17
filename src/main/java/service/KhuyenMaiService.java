package service;

import java.util.List;

import entity.KhuyenMai;

public interface KhuyenMaiService {
	boolean themKhuyenMai(KhuyenMai KhuyenMai);

	boolean suaKhuyenMai(KhuyenMai KhuyenMai);

	List<KhuyenMai> layDanhSachKhuyenMai();
	List<KhuyenMai> layDanhSachKhuyenMaiTheoTenNgayTrangThai(int page, int limit, String ten, String ngay, int selected);
	KhuyenMai layKhuyenMaiTheoMa(String maDV);

	List<KhuyenMai> layDanhSachKhuyenMaiTheoMaVaTen(String maDV, String tenDV);

	List<KhuyenMai> layDanhSachKhuyenMaiTheoTrangThai(boolean trangThai);

	boolean capNhatKhuyenMai(KhuyenMai KhuyenMai);

	List<KhuyenMai> layDanhSachKhuyenMaiTheoNgayNhap(String ngayNhap);
	List<KhuyenMai> layDanhSachKhuyenMaiTheoNgayTen(String ngayNhap);
	KhuyenMai layMaKhuyenMai(String maGiamGia);
}
