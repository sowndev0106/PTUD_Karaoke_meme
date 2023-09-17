package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author  Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 26-Oct-2021 3:55:35 PM
 */
@Entity
public class DiaChi implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "varchar(7)")
	private String maDC;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String tinhTP;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String quanHuyen;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String phuongXa;



	public DiaChi(){

	}
	
	public DiaChi(String maDC){
		this.maDC = maDC;
	}
	

	public DiaChi(String maDC, String tinhTP, String quanHuyen, String phuongXa) {
		super();
		this.maDC = maDC;
		this.tinhTP = tinhTP;
		this.quanHuyen = quanHuyen;
		this.phuongXa = phuongXa;
	}


	public String getMaDC() {
		return maDC;
	}


	public void setMaDC(String maDC) {
		this.maDC = maDC;
	}


	public String getTinhTP() {
		return tinhTP;
	}


	public void setTinhTP(String tinhTP) {
		this.tinhTP = tinhTP;
	}


	public String getQuanHuyen() {
		return quanHuyen;
	}


	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}


	public String getPhuongXa() {
		return phuongXa;
	}


	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}


	public void finalize() throws Throwable {

	}
	@Override
	public String toString() {
		return "DiaChi [maDC=" + maDC + ", tinhTP=" + tinhTP + ", quanHuyen=" + quanHuyen + ", phuongXa=" + phuongXa
				+ "]";
	}
}//end DiaChi