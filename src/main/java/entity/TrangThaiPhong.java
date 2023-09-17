package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



/**
 * @author  Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 25-Oct-2021 9:18:53 PM
 */
@Entity
public class TrangThaiPhong implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "varchar(6)")
	private String maTTP;//
	@Column(columnDefinition = "nvarchar(MAX)")
	private String tenTrangThaiPhong;//
//	@OneToMany(mappedBy = "trangThaiPhong")
//	private List<Phong> dsPhong;
	public TrangThaiPhong(){

	}
	public TrangThaiPhong(String maTTP, String tenTrangThaiPhong) {
		super();
		this.maTTP = maTTP;
		this.tenTrangThaiPhong = tenTrangThaiPhong;
	}
	public String getMaTTP() {
		return maTTP;
	}
	public void setMaTTP(String maTTP) {
		this.maTTP = maTTP;
	}
	public String getTenTrangThaiPhong() {
		return tenTrangThaiPhong;
	}
	public void setTenTrangThaiPhong(String tenTrangThaiPhong) {
		this.tenTrangThaiPhong = tenTrangThaiPhong;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void finalize() throws Throwable {

	}
	@Override
	public String toString() {
		return "TrangThaiPhong [maTTP=" + maTTP + ", tenTrangThaiPhong=" + tenTrangThaiPhong + "]";
	}
}//end TrangThaiPhong