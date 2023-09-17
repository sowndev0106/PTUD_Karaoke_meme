package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author  Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 25-Oct-2021 9:18:52 PM
 */
@Entity
public class LoaiNhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "varchar(6)")
	private String maLNV;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String tenLoaiNhanVien;

	public LoaiNhanVien(){

	}

	public LoaiNhanVien(String maLNV, String tenLoaiNhanVien) {
		super();
		this.maLNV = maLNV;
		this.tenLoaiNhanVien = tenLoaiNhanVien;
	}

	public String getMaLNV() {
		return maLNV;
	}

	public void setMaLNV(String maLNV) {
		this.maLNV = maLNV;
	}

	public String getTenLoaiNhanVien() {
		return tenLoaiNhanVien;
	}

	public void setTenLoaiNhanVien(String tenLoaiNhanVien) {
		this.tenLoaiNhanVien = tenLoaiNhanVien;
	}

	@Override
	public String toString() {
		return "LoaiNhanVien [maLNV=" + maLNV + ", tenLoaiNhanVien=" + tenLoaiNhanVien + "]";
	}


	
}//end LoaiNhanVien