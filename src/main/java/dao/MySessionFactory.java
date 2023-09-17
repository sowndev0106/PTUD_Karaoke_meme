package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import entity.ChiTietDichVu;
import entity.ChiTietDichVuPK;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonPK;
import entity.DiaChi;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoaiNhanVien;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;

public class MySessionFactory {
	private SessionFactory sessionFactory;

	public MySessionFactory() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure() // hibernate.cfg.xml
				.build();
		Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(Phong.class)
				.addAnnotatedClass(LoaiPhong.class).addAnnotatedClass(TrangThaiPhong.class)
				.addAnnotatedClass(DiaChi.class).addAnnotatedClass(KhachHang.class)
				.addAnnotatedClass(LoaiNhanVien.class).addAnnotatedClass(NhanVien.class)
				.addAnnotatedClass(ChiTietDichVuPK.class).addAnnotatedClass(ChiTietDichVu.class)
				.addAnnotatedClass(ChiTietHoaDonPK.class).addAnnotatedClass(ChiTietHoaDon.class)
				.addAnnotatedClass(DichVu.class).addAnnotatedClass(KhuyenMai.class).addAnnotatedClass(HoaDon.class)
				.addAnnotatedClass(PhieuDatPhong.class).getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void close() {
		sessionFactory.close();
	}

}
