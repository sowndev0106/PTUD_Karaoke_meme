package service;

import java.util.List;

import entity.DichVu;

public interface DichVuService {
	boolean themDichVu(DichVu dichVu);

	boolean suaDichVu(DichVu dichVu);
	
	List<DichVu> layDanhSachDichVuTheoTenNgayTrangThai(int page, int limit, String ten,  int selected);
	
	DichVu layDichVuTheoMa(String maDV);

	List<DichVu> layDanhSachDichVuTheoMaVaTen(String maDV, String tenDV);

	List<DichVu> layDanhSachDichVuTheoTrangThai(boolean trangThai);

	boolean capNhatDichVu(DichVu dichVu);

	List<DichVu> layDanhSachDichVuTheoNgayNhap(String ngayNhap);
	List<DichVu> layDanhSachDichVuTheoNgayTen(String ngayNhap);

}
