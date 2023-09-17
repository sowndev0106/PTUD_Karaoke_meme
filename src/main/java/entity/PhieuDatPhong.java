package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author  Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 25-Oct-2021 9:18:53 PM
 */
@Entity
public class PhieuDatPhong implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "varchar(8)")
	private String maPDP;//

	@ManyToOne
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;//
	@ManyToOne
	@JoinColumn(name = "maNV")
	private NhanVien nhanVienLap;//
	@ManyToOne
	@JoinColumn(name = "maPhong")
	private Phong phong;//
	private Date thoiGianDangKyDatPhong;//
	private Date thoiGianNhanPhong;//
	private boolean tinhTrang;//

	public PhieuDatPhong() {

	}

	public PhieuDatPhong(String maPDP, KhachHang khachHang, NhanVien nhanVienLap, Phong phong,
			Date thoiGianDangKyDatPhong, Date thoiGianNhanPhong, boolean tinhTrang) {
		super();
		this.maPDP = maPDP;
		this.khachHang = khachHang;
		this.nhanVienLap = nhanVienLap;
		this.phong = phong;
		this.thoiGianDangKyDatPhong = thoiGianDangKyDatPhong;
		this.thoiGianNhanPhong = thoiGianNhanPhong;
		this.tinhTrang = tinhTrang;
	}

	public PhieuDatPhong(KhachHang khachHang, NhanVien nhanVienLap, Phong phong, Date thoiGianNhanPhong) {
		super();
		this.khachHang = khachHang;
		this.nhanVienLap = nhanVienLap;
		this.phong = phong;
		this.thoiGianDangKyDatPhong = new Date();
		this.thoiGianNhanPhong = thoiGianNhanPhong;
		this.tinhTrang = true;
	}

	public void finalize() throws Throwable {

	}

	public String getMaPDP() {
		return maPDP;
	}

	public void setMaPDP(String maPDP) {
		this.maPDP = maPDP;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVienLap() {
		return nhanVienLap;
	}

	public void setNhanVienLap(NhanVien nhanVienLap) {
		this.nhanVienLap = nhanVienLap;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public Date getThoiGianDangKyDatPhong() {
		return thoiGianDangKyDatPhong;
	}

	public void setThoiGianDangKyDatPhong(Date thoiGianDangKyDatPhong) {
		this.thoiGianDangKyDatPhong = thoiGianDangKyDatPhong;
	}

	public Date getThoiGianNhanPhong() {
		return thoiGianNhanPhong;
	}

	public void setThoiGianNhanPhong(Date thoiGianNhanPhong) {
		this.thoiGianNhanPhong = thoiGianNhanPhong;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maPDP=" + maPDP + ", khachHang=" + khachHang + ", nhanVienLap=" + nhanVienLap
				+ ", phong=" + phong + ", thoiGianDangKyDatPhong=" + thoiGianDangKyDatPhong + ", thoiGianNhanPhong="
				+ thoiGianNhanPhong + ", tinhTrang=" + tinhTrang + "]";
	}
}// end PhieuDatPhong