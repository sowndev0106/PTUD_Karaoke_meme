package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author  Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 25-Oct-2021 9:18:52 PM
 */
@Entity
@IdClass(ChiTietHoaDonPK.class)
public class ChiTietHoaDon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "maPhong" ,columnDefinition = "varchar(4)")
	private Phong phong;	//
	@Id
	@ManyToOne
	@JoinColumn(name = "maHD",columnDefinition = "varchar(7)")
	private HoaDon hoaDon;//
	private int thoiLuong;//

	public ChiTietHoaDon(){

	}
	public void finalize() throws Throwable {

	}
	public double thanhTien(){
		return (getThoiLuong()/60.0)*phong.getLoaiPhong().getGiaTien();
	}
	public ChiTietHoaDon(Phong phong, HoaDon hoaDon, int thoiLuong) {
		super();
		this.phong = phong;
		this.hoaDon = hoaDon;
		this.thoiLuong = thoiLuong;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [phong=" + phong + ", hoaDon=" + hoaDon + ", thoiLuong=" + thoiLuong + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((phong == null) ? 0 : phong.hashCode());
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
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (phong == null) {
			if (other.phong != null)
				return false;
		} else if (!phong.equals(other.phong))
			return false;
		return true;
	}
	
}//end ChiTietHoaDon