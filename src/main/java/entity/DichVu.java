package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author  Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 25-Oct-2021 9:22:16 PM
 */
@Entity
public class DichVu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "varchar(7)")
	private String maDV;//
	private Double donGia; //
	@Column(columnDefinition = "nvarchar(50)")
	private String donViTinh;//
	@Column(columnDefinition = "nvarchar(MAX)")
	private String tenDichVu;//
	private boolean trangThaiDichVu;//
	private int soLuong; //
	public DichVu(String maDV, Double donGia, String donViTinh, String tenDichVu, boolean trangThaiDichVu,
			int soLuong) {
		super();
		this.maDV = maDV;
		this.donGia = donGia;
		this.donViTinh = donViTinh;
		this.tenDichVu = tenDichVu;
		this.trangThaiDichVu = trangThaiDichVu;
		this.soLuong = soLuong;
	}
	public DichVu( String tenDichVu, int soLuong , String donViTinh,Double donGia, boolean trangThaiDichVu) {
		super();
		this.donGia = donGia;
		this.donViTinh = donViTinh;
		this.tenDichVu = tenDichVu;
		this.trangThaiDichVu = trangThaiDichVu;
		this.soLuong = soLuong;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maDV == null) ? 0 : maDV.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		if (maDV == null) {
			if (other.maDV != null)
				return false;
		} else if (!maDV.equals(other.maDV))
			return false;
		return true;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public boolean isTrangThaiDichVu() {
		return trangThaiDichVu;
	}

	public void setTrangThaiDichVu(boolean trangThaiDichVu) {
		this.trangThaiDichVu = trangThaiDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public DichVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

}// end DichVu