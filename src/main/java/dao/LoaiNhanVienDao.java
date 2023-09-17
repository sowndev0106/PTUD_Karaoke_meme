package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.LoaiNhanVien;
import service.LoaiNhanVienService;

public class LoaiNhanVienDao implements LoaiNhanVienService{
	private SessionFactory sessionFactory;
	
	public LoaiNhanVienDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<String> layDanhSachLoaiNhanVien() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();																				
			String sql = "select tenLoaiNhanVien from LoaiNhanVien group by tenLoaiNhanVien";
			@SuppressWarnings("unchecked")
			List<String> dsLoaiNhanVien = session.createNativeQuery(sql).getResultList();
			tr.commit();
			return dsLoaiNhanVien;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public LoaiNhanVien layLoaiNhanVien(String ten) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		LoaiNhanVien nv = new LoaiNhanVien();
		String sql = "select * from LoaiNhanVien where tenLoaiNhanVien like N'%"+ten+"%'";
		try {
			tr.begin();
			nv = session.createNativeQuery(sql,LoaiNhanVien.class).getSingleResult();
			tr.commit();
			return nv;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String layMaNhanVienTheoTenLoai(String loaiNV) {
Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		String loai;
		
		String sql="select maLNV from LoaiNhanVien where tenLoaiNhanVien like N'%"+loaiNV+"%' group by maLNV";
		
		try {
			tr.begin();
	
			loai= (String) session.createNativeQuery(sql).getSingleResult();
//			loai = session.createNativeQuery(sql).getSingleResult();
			tr.commit();
			System.out.println(loai);
			return loai;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
			
			
		return null;
	}
	

}
