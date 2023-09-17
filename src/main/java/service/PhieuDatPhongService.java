package service;

import entity.PhieuDatPhong;

public interface PhieuDatPhongService {
	public String phatSinhMaTuDong();

	public boolean  themPhieuDatPhong(PhieuDatPhong phieuDatPhong);

	public boolean huyPhieuDatPhong(String maPDP);
	
	public PhieuDatPhong layPhieuDatPhongMoiNhatTheoPhong( String maPhong);
}
