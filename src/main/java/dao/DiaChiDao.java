package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DiaChi;
import service.DiaChiService;

public class DiaChiDao implements DiaChiService{
private SessionFactory sessionFactory;


	public DiaChiDao(SessionFactory sessionFactory) {
	super();
	this.sessionFactory = sessionFactory;
}
//
//	@Override
//	public List<DiaChi> layDanhSachDiaChi() {
//		Session session = sessionFactory.getCurrentSession();
//		Transaction tr = session.getTransaction();
//		List<DiaChi> chis;
//		String sql = "select *from DiaChi";
//		try {
//			tr.begin();
//			chis= session.createNativeQuery(sql, DiaChi.class).getResultList();
//			tr.commit();
//			return chis;
//		} catch (Exception e) {
//			tr.rollback();
//			e.printStackTrace();
//		}
//		return null;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> layDanhSachCacTinh() {
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<String> tinhTP;
		String sql= "select tinhTP from DiaChi group by tinhTP";
		
	try {
		tr.begin();
		tinhTP= session.createNativeQuery(sql).getResultList();
		tr.commit();
		return tinhTP;
	} catch (Exception e) {
		tr.rollback();
		e.printStackTrace();
	}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> layDanhSachHuyenTrongTinhTP(String tinh) {
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<String> huyen;
		String sql="select quanHuyen from DiaChi where tinhTP like N'%"+tinh+"%' group by quanHuyen";
		
		try {
			tr.begin();
			huyen = session.createNativeQuery(sql).getResultList();
			tr.commit();
			return huyen;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
			
			
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> layDanhSachPhuongXaTrongHuyenTinh(String tinh, String huyen) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<String> xa;
		String sql = "select phuongXa from DiaChi where tinhTP like N'%"+tinh+"%'and quanHuyen like  N'%"+huyen+"%'";
		try {
			tr.begin();
			xa = session.createNativeQuery(sql).getResultList();
			tr.commit();
			return xa;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DiaChi layDiaChi(String xa, String huyen, String tinh) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		DiaChi dc = new DiaChi();
		String sql = "select * from DiaChi where tinhTP like N'%"+tinh+"%'and quanHuyen like  N'%"+huyen+"%' and phuongXa like N'%"+xa+"%'";
		try {
			tr.begin();
			dc = session.createNativeQuery(sql,DiaChi.class).getSingleResult();
			tr.commit();
			return dc;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			e.printStackTrace();
		}
		return null;
	}

}
