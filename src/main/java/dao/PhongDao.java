package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Phong;
import service.PhongService;

public class PhongDao implements PhongService{
	private SessionFactory sessionFactory;

	public PhongDao(SessionFactory sessionFactory) {
		super();
		// TODO Auto-generated constructor stub
		this.sessionFactory = sessionFactory;
	}

	public List<Phong> layDanhSachPhong()  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			List<Phong> dsPhong = session.createNativeQuery("select * from Phong", Phong.class).getResultList();
			tr.commit();
			return dsPhong;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	public List<Phong> layDanhSachPhongDuocPhepDat() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		String sql = "select * from Phong where maTTP = 'TTP003' or (maTTP = 'TTP002' and maPhong in (select maPhong from PhieuDatPhong where thoiGianNhanPhong >  convert(datetime, dateadd(hour, 2, GETDATE()), 100) group by maPhong ))\r\n";
		try {
			tr.begin();
			List<Phong> dsPhong = session.createNativeQuery(sql, Phong.class).getResultList();
			tr.commit();
			return dsPhong;
		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public Phong layThongTinPhongQuaMa(String maPhong)  {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Phong phong = session.find(Phong.class, maPhong);
			tr.commit();
			return phong;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public List<Phong> layDanhSachPhongTheoTrangThaiPhong(String maTTP) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			List<Phong> dsPhong = session
					.createNativeQuery("select * from phong where maTTP = '" + maTTP + "'", Phong.class)
					.getResultList();
			tr.commit();
			return dsPhong;
		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public boolean capNhatTrangThaiPhong(String maPhong, String maTTP) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Phong set maTTP = :maTTP WHERE maPhong = :maPhong ";
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(sql);
			query.setParameter("maTTP", maTTP);
			query.setParameter("maPhong", maPhong);
			int result = query.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return false;
	}
	@Override
	public List<Phong> layDanhSachPhongTheoTrangThaiLoaiPhongSoNguoi(int tt, int LoaiPhong, int soNguoi) {
		String mattp = "";
			if(tt == 1) {
				mattp = "(mattp like '%2' or mattp like '%3') and";
			}
			if(tt == 2 ) {
				mattp = "(mattp like '%1' or mattp like '%4') and";
			}
		String lp = "";
		if (LoaiPhong != 0) {
			lp = "LP00" + LoaiPhong;
		}
		String sn = "";
		if (soNguoi != 0) {
			sn = soNguoi == 1 ? "5" : soNguoi == 2 ? "10" : "20";
		}
		String sql = "SELECT * FROM Phong where "+mattp+"  malp like '%" + lp
				+ "%' and soNguoi like '%" + sn + "'";
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			List<Phong> list = session.createNativeQuery(sql, Phong.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int laySoLuongPhongTheoTrangThai(int tt) {
		String ttp = "";
		if (tt != 0) {
			ttp = "TTP00" + (tt == 1 ? "3" : tt == 2 ? "2" : tt == 3 ? "4" : "1");
		}
		String sql = "SELECT count(*) FROM Phong where mattp like '%" +ttp+"%'";
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			int result = Integer.parseInt(session.createNativeQuery(sql).uniqueResult().toString());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return 0;
	}

}
