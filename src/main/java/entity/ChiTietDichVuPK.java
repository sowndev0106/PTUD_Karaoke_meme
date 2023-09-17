package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author  Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 25-Oct-2021 9:25:37 PM
 */
@Embeddable
public class ChiTietDichVuPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dichVu;
	private String hoaDon;
	
	public ChiTietDichVuPK(){

	}

	

	public ChiTietDichVuPK(String dichVu, String hoaDon) {
		super();
		this.dichVu = dichVu;
		this.hoaDon = hoaDon;
	}



	public String getDichVu() {
		return dichVu;
	}



	public void setDichVu(String dichVu) {
		this.dichVu = dichVu;
	}



	public String getHoaDon() {
		return hoaDon;
	}



	public void setHoaDon(String hoaDon) {
		this.hoaDon = hoaDon;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "ChiTietDichVuPK [dichVu=" + dichVu + ", hoaDon=" + hoaDon + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dichVu == null) ? 0 : dichVu.hashCode());
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
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
		ChiTietDichVuPK other = (ChiTietDichVuPK) obj;
		if (dichVu == null) {
			if (other.dichVu != null)
				return false;
		} else if (!dichVu.equals(other.dichVu))
			return false;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		return true;
	}



	public void finalize() throws Throwable {

	}
}//end ChiTietDichVu