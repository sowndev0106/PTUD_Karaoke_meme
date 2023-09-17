package service;

import java.util.List;

import entity.KhachHang;

public interface KhachHangService {
	public String phatSinhMaTuDong();
	public List<KhachHang> layDanhSachKhacHang();
	
	public boolean themKhachHang(KhachHang kh);
	public boolean suaKhachHang(KhachHang kh);
	public KhachHang layKhachHangTheoMa(String ma) ;
	public KhachHang layKhachHangTheoSDT(String sdt);
	public List<KhachHang> layDanhSachKhachHangTheoTen(String ten);
	public List<KhachHang> layDanhSachKhachHang(int page,String tenKH, String gioiTinh, int limit);
	public int tongTrang(String txtSearch, String gioiTinh, int limit);
}
