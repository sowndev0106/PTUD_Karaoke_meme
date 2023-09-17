package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.NhanVien;

public class DangNhapDao {
	SessionFactory sessionFactory;
	public DangNhapDao(SessionFactory sessionFactory)  {
		super();
		this.sessionFactory = sessionFactory;
	}
	public NhanVien getNhanVienDangNhap(String sdt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			NhanVien result = session.createNativeQuery("select * from nhanvien where soDienThoai = '"+sdt+"'", NhanVien.class).getSingleResult();
			tr.commit();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return null;
	}
	public boolean suaTaiKhoan(NhanVien taiKhoan)  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(taiKhoan);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean kiemTraSDT(String sdt) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			NhanVien rs = session.createNativeQuery("select * from nhanvien where soDienThoai = '"+sdt+"'", NhanVien.class).getSingleResult();
			tr.commit();
			System.out.println(rs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return false;
	}
}
