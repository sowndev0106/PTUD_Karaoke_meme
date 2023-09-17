package entity;

import java.io.Serializable;

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
public class Phong implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "varchar(4)")
	private String maPhong;//
	private int soNguoi;//
	private boolean tinhTrangPhong;//
	@ManyToOne
	@JoinColumn(name = "maLP")
	private LoaiPhong loaiPhong;//
	@ManyToOne
	@JoinColumn(name = "maTTP")
	private TrangThaiPhong trangThaiPhong;//

	public Phong() {

	}

	public Phong(String maPhong) {
		this.maPhong = maPhong;
	}

	public void finalize() throws Throwable {

	}

	public Phong(String maPhong, String tenPhong, int soNguoi, boolean tinhTrangPhong, LoaiPhong loaiPhong,
			TrangThaiPhong trangThaiPhong) {
		super();
		this.maPhong = maPhong;
		this.soNguoi = soNguoi;
		this.tinhTrangPhong = tinhTrangPhong;
		this.loaiPhong = loaiPhong;
		this.trangThaiPhong = trangThaiPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}

	public boolean isTinhTrangPhong() {
		return tinhTrangPhong;
	}

	public void setTinhTrangPhong(boolean tinhTrangPhong) {
		this.tinhTrangPhong = tinhTrangPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public TrangThaiPhong getTrangThaiPhong() {
		return trangThaiPhong;
	}

	public void setTrangThaiPhong(TrangThaiPhong trangThaiPhong) {
		this.trangThaiPhong = trangThaiPhong;
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", soNguoi=" + soNguoi + ", tinhTrangPhong=" + tinhTrangPhong
				+ ", loaiPhong=" + loaiPhong + ", trangThaiPhong=" + trangThaiPhong + "]";
	}
}// end Phong