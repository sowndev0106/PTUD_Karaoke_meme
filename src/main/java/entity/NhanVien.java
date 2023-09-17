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
public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "varchar(7)")
	private String maNV;//
	private boolean gioiTinh;//
	@Column(columnDefinition = "nvarchar(MAX)")
	private String hoTen;//
	private Date ngaySinh;//
	private String password;//
	@ManyToOne
	@JoinColumn(name="quanLy")
	private NhanVien quanLy;//

		
	
	
	@Column(columnDefinition = "varchar(11)")
	private String soCMND;//
	@Column(columnDefinition = "varchar(11)")
	private String soDienThoai;//
	private boolean trangThaiLamViec;//
	@ManyToOne
	@JoinColumn(name="maDC")
	private DiaChi diaChi;//
	@ManyToOne
	@JoinColumn(name="maLNV")
	private LoaiNhanVien loaiNhanVien;//
	
	public NhanVien(){
		
	}
	
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
/**
 * 
 * @param maNV
 * @param gioiTinh
 * @param hoTen
 * @param ngaySinh
 * @param password
 * @param quanLy
 * @param soCMND
 * @param soDienThoai
 * @param trangThaiLamViec
 * @param diaChi
 * @param loaiNhanVien
 */
	public NhanVien(String maNV, boolean gioiTinh, String hoTen, Date ngaySinh, String password, NhanVien quanLy,
			String soCMND, String soDienThoai, boolean trangThaiLamViec, DiaChi diaChi, LoaiNhanVien loaiNhanVien) {
		super();
		this.maNV = maNV;
		this.gioiTinh = gioiTinh;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.password = password;
		this.quanLy = quanLy;
		this.soCMND = soCMND;
		this.soDienThoai = soDienThoai;
		this.trangThaiLamViec = trangThaiLamViec;
		this.diaChi = diaChi;
		this.loaiNhanVien = loaiNhanVien;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public NhanVien getQuanLy() {
		return quanLy;
	}
	public void setQuanLy(NhanVien quanLy) {
		this.quanLy = quanLy;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public boolean isTrangThaiLamViec() {
		return trangThaiLamViec;
	}
	public void setTrangThaiLamViec(boolean trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}
	public DiaChi getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	public LoaiNhanVien getLoaiNhanVien() {
		return loaiNhanVien;
	}
	public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
		this.loaiNhanVien = loaiNhanVien;
	}

	
}//end NhanVien