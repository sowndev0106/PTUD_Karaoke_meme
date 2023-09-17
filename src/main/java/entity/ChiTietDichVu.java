package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Nguyễn Thanh Sơn, Phan Thành Công, Nguyễn Thị Minh Châu
 * @version 1.0
 * @created 25-Oct-2021 9:25:37 PM
 */
@Entity
@IdClass(ChiTietDichVuPK.class)
public class ChiTietDichVu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "maDV",columnDefinition = "varchar(7)")
	private DichVu dichVu;//
	@Id
	@ManyToOne
	@JoinColumn(name = "maHD",columnDefinition = "varchar(7)")
	private HoaDon hoaDon;//
	private int soLuong;//

	public ChiTietDichVu(){

	}

	public ChiTietDichVu(DichVu dichVu, HoaDon hoaDon, int soLuong) {
		super();
		this.dichVu = dichVu;
		this.hoaDon = hoaDon;
		this.soLuong = soLuong;
	}

	public void finalize() throws Throwable {

	}
	
	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public Double thanhTien(){
		return soLuong*dichVu.getDonGia();
	}

	@Override
	public String toString() {
		return "ChiTietDichVu [dichVu=" + dichVu + ", hoaDon=" + hoaDon + ", soLuong=" + soLuong + "]";
	}
	
}//end ChiTietDichVu