package service;

import java.util.List;

import entity.DiaChi;

public interface DiaChiService {
	public List<String> layDanhSachCacTinh();
	public List<String> layDanhSachHuyenTrongTinhTP(String tinh);
	public List<String> layDanhSachPhuongXaTrongHuyenTinh(String tinh, String huyen);
	public DiaChi layDiaChi(String xa , String huyen, String tinh);
}
