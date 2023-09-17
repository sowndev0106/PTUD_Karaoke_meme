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
 * @created 25-Oct-2021 9:18:52 PM
 */
@Entity
public class KhachHang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "varchar(7)")
	private String maKH;//
	@ManyToOne
	@JoinColumn(name = "maDC",columnDefinition = "varchar(7)")
	private DiaChi diaChi;//
	@Column(columnDefinition = "nvarchar(MAX)")
	private String hoTen;//
	
	@Column(columnDefinition = "varchar(11)")
	private String soDienThoai;//
	private Date ngaySinh;//
	private boolean gioiTinh;//

	public KhachHang(String maKH, DiaChi diaChi, String hoTen, String soDienThoai, Date ngaySinh, boolean gioiTinh) {
		super();
		this.maKH = maKH;
		this.diaChi = diaChi;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHang(){

	}

	public void finalize() throws Throwable {

	}
	
	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public DiaChi getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", diaChi=" + diaChi + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai
				+ ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + "]";
	}
}