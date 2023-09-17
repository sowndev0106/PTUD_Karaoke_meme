package service;

import java.util.List;

import entity.LoaiNhanVien;


public interface LoaiNhanVienService {
	public  List<String> layDanhSachLoaiNhanVien();
	public  String layMaNhanVienTheoTenLoai(String loai);
	public LoaiNhanVien layLoaiNhanVien(String ten);
}
