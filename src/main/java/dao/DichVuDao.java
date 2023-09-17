package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;
import service.DichVuService;

public class DichVuDao  implements DichVuService{
	private SessionFactory sessionFactory;

	public DichVuDao(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		this.sessionFactory = sessionFactory;
	}

	
	public List<DichVu> layDanhSachDichVu()  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select * from dichVu where trangThaiDichVu = '1'";
			List<DichVu> dsDichVu = session.createNativeQuery(sql, DichVu.class).getResultList();
			tr.commit();
			return dsDichVu;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public boolean tangSoLuongDihVu(String maDV, int soLuongTang)  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql2 = "select soLuong from DichVu  where maDV = '" + maDV + "'";
			int quantity = Integer.parseInt(session.createNativeQuery(sql2).uniqueResult().toString());
			quantity += soLuongTang;
			String sql = "update DichVu  set soLuong = " + quantity + " where trangThaiDichVu = '1' and maDV = '" + maDV
					+ "'";
			int result = session.createQuery(sql).executeUpdate();
			tr.commit();
			if (result != 0) {
				return true;
			}
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean giamSoLuongDichVu(String maDV, int soLuongGiam)  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {

			tr.begin();
			String sql2 = "select soLuong from DichVu  where maDV = '" + maDV + "'";
			int quantity = Integer.parseInt(session.createNativeQuery(sql2).uniqueResult().toString());
			quantity -= soLuongGiam;
			if (quantity < 0)
				return false;
			String sql = "update DichVu  set soLuong = " + quantity + " where trangThaiDichVu = '1' and maDV = '" + maDV
					+ "'";
			int result = session.createQuery(sql).executeUpdate();
			tr.commit();
			if (result != 0) {
				return true;
			}

		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean kiemTraSoLuongDichVu(String maDV, int soLuong)  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select soLuong from DichVu  where maDV = '" + maDV + "'";
			int result = Integer.parseInt(session.createNativeQuery(sql).uniqueResult().toString());
			tr.commit();
			if (result >= soLuong) {
				return true;
			}
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	@Override
	public boolean themDichVu(DichVu dichVu) {
		dichVu.setMaDV(getMaCuoi());
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(dichVu);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	private String getMaCuoi() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String ma = (String) session.createNativeQuery("select  max(maDV) from dichvu").getSingleResult();
			tr.commit();
			String maCuoi = new MaTuDong().fomatAA000(ma);
			return maCuoi;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean suaDichVu(DichVu dichVu) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<DichVu> layDanhSachDichVuTheoMaVaTen(String maDV, String tenDV) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DichVu> layDanhSachDichVuTheoTrangThai(boolean trangThai) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		int tt = trangThai?1:0;
		try {
			String sql = "select * from DichVu where trangThaiDichVu ="+tt;
			tr.begin();
			List<DichVu> dichVus =session.createNativeQuery(sql, DichVu.class).getResultList();
			tr.commit();
			return dichVus;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public DichVu layDichVuTheoMa(String maDV) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			String sql = "select * from DichVu where maDV = '"+maDV+"'";
			tr.begin();
				DichVu dichVu = session.createNativeQuery(sql, DichVu.class).getSingleResult();
			tr.commit();
			return dichVu;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	@Override
	public boolean capNhatDichVu(DichVu dichVu) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			
			tr.begin();
				 session.update(dichVu);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<DichVu> layDanhSachDichVuTheoNgayNhap(String ngayNhap) {
		//  where ngayNhap like '%2021-01-06%' and ngayHetHan like '%2021-12-12%'
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			String sql = "select * from DichVu where ngayNhap like '%"+ngayNhap+"%'";
			tr.begin();
			List<DichVu> dichVus =session.createNativeQuery(sql, DichVu.class).getResultList();
			tr.commit();
			return dichVus;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<DichVu> layDanhSachDichVuTheoNgayTen(String ten) {
	//  where ngayNhap like '%2021-01-06%' and ngayHetHan like '%2021-12-12%'
			Session session = sessionFactory.getCurrentSession();
			Transaction tr = session.getTransaction();
			try {
				String sql = "select * from DichVu where tenDichVu like N'%"+ten+"%'";
				tr.begin();
				List<DichVu> dichVus =session.createNativeQuery(sql, DichVu.class).getResultList();
				tr.commit();
				return dichVus;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				tr.rollback();
			}
			return null;
	}

	@Override
	public List<DichVu> layDanhSachDichVuTheoTenNgayTrangThai(int page, int limit, String ten, int selected) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		if(ten == null) {
			ten = "";
		}

		String tt = "";
		if(selected == 1) {
			tt = "1";
		}
		if(selected == 2) {
			tt = "0";
		}
		int offset = page * limit;// lay du lieu bat dau tu vi tri page*20
		try {
			String sql = "select * from DichVu where tenDichVu like N'%"+ten+"%' "
					+ "and trangThaiDichVu like '%"+tt+"%'"
					+ "order by maDV desc OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY";
			tr.begin();
			System.err.println(sql);
			List<DichVu> dichVus =session.createNativeQuery(sql, DichVu.class).getResultList();
			tr.commit();
			return dichVus;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	

	public int 	tongTrang(String txtSearch, int trangThai, int limit)  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (txtSearch == null)
			txtSearch = "";
		
		String trangThaiDV = "";
		if (trangThai == 0)
			trangThaiDV = "";
		else if (trangThai == 1)
			trangThaiDV = "1";
		else if (trangThai == 2)
			trangThaiDV = "0";

		if(limit <= 0) {
			limit = 20;
		}
		try {
			tr.begin();
			String sql = "select count(*) from DichVu where tenDichVu like N'%"+ txtSearch + "%' "
					+ "and trangThaiDichVu like '%" + trangThaiDV + "%'";
			int result = Integer.parseInt(session.createNativeQuery(sql).uniqueResult().toString());
			tr.commit();
			return result % limit == 0 ? result/limit: (result/limit)+1;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println("tongHang: "+e.getMessage());
			tr.rollback();

		} finally {
			session.close();
		}
		return 0;
	}
	

}
