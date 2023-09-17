package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.PhieuDatPhong;
import service.PhieuDatPhongService;

public class PhieuDatPhongDao implements PhieuDatPhongService {
	private SessionFactory sessionFactory;

	public PhieuDatPhongDao(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String phatSinhMaTuDong() {
		String maPDP = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select max(maPDP) from PhieuDatPhong";
			maPDP = (String) session.createNativeQuery(sql).getSingleResult();
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		if (maPDP == null || maPDP.equalsIgnoreCase(""))
			maPDP = "PDPAA001";
		return "P" + new MaTuDong().fomatAA000(maPDP.substring(1));
	}

	@Override
	public boolean themPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		String maPDP = phatSinhMaTuDong();
		phieuDatPhong.setMaPDP(maPDP);
		System.out.println(phieuDatPhong);
		Session session = sessionFactory.getCurrentSession();
		phieuDatPhong.setTinhTrang(true);
		Transaction tr = session.beginTransaction();
		try {
//			tr.begin();
			session.save(phieuDatPhong);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return false;

	}

	@Override
	public boolean huyPhieuDatPhong(String maPDP) {
		String sql = "UPDATE PhieuDatPhong set tinhTrang = :tinhTrang WHERE maPDP = :maPDP ";
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(sql);
			query.setParameter("tinhTrang", false);
			query.setParameter("maPDP", maPDP);
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
		return false;// TODO Auto-generated method stub
	}

	public int updateTrangThaiPDP(String maPDP) {
		String sql = "update phieudatphong set tinhtrang = 0 where thoiGianNhanPhong >=  convert(datetime, dateadd(hour, -2, GETDATE()), 100);";
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(sql);
//			query.setParameter("tinhTrang", false);
//			query.setParameter("maPDP", maPDP);
			int result = query.executeUpdate();
			if (result != 0) {
				return result;
			}
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return 0;// TODO Auto-generated method stub
	}

	@Override
	public PhieuDatPhong layPhieuDatPhongMoiNhatTheoPhong(String maPhong) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			// and thoiGianNhanPhong >=  convert(datetime, dateadd(hour, -2, GETDATE()), 100) 
			String sql = "select top 1 * from PhieuDatPhong where maPhong = '" + maPhong
					+ "' and tinhTrang = '1' order by  maPDP desc ";
			tr.begin();

			PhieuDatPhong phieuDatPhong = session.createNativeQuery(sql, PhieuDatPhong.class).getSingleResult();
			tr.commit();
			return phieuDatPhong;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public List<PhieuDatPhong> layDanhSachPhieuDatPhong(String maPhieuDat, String sdtKhach, int tinhTrang) {
		// tinh trạng 0 = Tất cả; 1 = Còn hiệu lực; 2 = Hết hạn; 3 = đã nhận phòng
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		String sql = "select top 50 * from PhieuDatPhong inner join KhachHang on KhachHang.maKH = PhieuDatPhong.maKH where ";
		if (!maPhieuDat.equals("")) {
			sql += " maPDP = '" + maPhieuDat + "' and ";
		} else if (!sdtKhach.equals("")) {
			sql += " soDienThoai = '" + sdtKhach + "'  and ";
		}
		// and convert(datetime, dateadd(hour, 2, thoiGianNhanPhong), 100) >= GETDATE()
		if (tinhTrang == 1) {
			// Còn hiệu lực
			sql += " tinhTrang = 1 ";
		} else if (tinhTrang == 2) {
			// Hết hạn
			sql += " tinhTrang = 0 ";
		} else {
			// default
			sql += " tinhTrang like '%%' ";
		}
		sql += " order by maPDP desc";
		try {
			tr.begin();
			List<PhieuDatPhong> phieuDatPhong = session.createNativeQuery(sql, PhieuDatPhong.class).getResultList();
			tr.commit();
			return phieuDatPhong;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

}
