package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.KhuyenMai;
import service.KhuyenMaiService;

public class KhuyenMaiDao implements KhuyenMaiService {

	SessionFactory sessionFactory;

	public KhuyenMaiDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean themKhuyenMai(KhuyenMai KhuyenMai) {
		KhuyenMai.setMaKM(getMaCuoi());
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(KhuyenMai);
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
			String ma = (String) session.createNativeQuery("select  max(maKM) from KhuyenMai").getSingleResult();
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
	public boolean suaKhuyenMai(KhuyenMai KhuyenMai) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<KhuyenMai> layDanhSachKhuyenMai() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			String sql = "select * from KhuyenMai";
			tr.begin();
			List<KhuyenMai> KhuyenMais = session.createNativeQuery(sql, KhuyenMai.class).getResultList();
			tr.commit();
			return KhuyenMais;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<KhuyenMai> layDanhSachKhuyenMaiTheoMaVaTen(String maKM, String tenKM) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhuyenMai> layDanhSachKhuyenMaiTheoTrangThai(boolean trangThai) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		int tt = trangThai ? 1 : 0;
		try {
			String sql = "select * from KhuyenMai where trangThai =" + tt;
			tr.begin();
			List<KhuyenMai> KhuyenMais = session.createNativeQuery(sql, KhuyenMai.class).getResultList();
			tr.commit();
			return KhuyenMais;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public KhuyenMai layKhuyenMaiTheoMa(String maGG) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			String sql = "select * from KhuyenMai where maGiamGia = '" + maGG + "'";
			tr.begin();
			KhuyenMai khuyenMai = session.createNativeQuery(sql, KhuyenMai.class).getSingleResult();
			tr.commit();
			return khuyenMai;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean capNhatKhuyenMai(KhuyenMai khuyenMai) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {

			tr.begin();
			session.update(khuyenMai);
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
	public List<KhuyenMai> layDanhSachKhuyenMaiTheoNgayNhap(String ngayNhap) {
		// where ngayNhap like '%2021-01-06%' and ngayHetHan like '%2021-12-12%'
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			String sql = "select * from KhuyenMai where ngayBatDau like '%" + ngayNhap + "%'";
			tr.begin();
			List<KhuyenMai> khuyenMais = session.createNativeQuery(sql, KhuyenMai.class).getResultList();
			tr.commit();
			return khuyenMais;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<KhuyenMai> layDanhSachKhuyenMaiTheoNgayTen(String ten) {
		// where ngayNhap like '%2021-01-06%' and ngayHetHan like '%2021-12-12%'
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			String sql = "select * from KhuyenMai where moTa like N'%" + ten + "%'";
			tr.begin();
			List<KhuyenMai> khuyenMais = session.createNativeQuery(sql, KhuyenMai.class).getResultList();
			tr.commit();
			return khuyenMais;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<KhuyenMai> layDanhSachKhuyenMaiTheoTenNgayTrangThai(int page, int limit, String ten, String ngay,
			int selected) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		String tt = "";
		if (ten == null) {
			ten = "";
		}
		if (ngay == null) {
			ngay = "";
		}
		if (selected == 1) {
			// dang cho
			tt = "and ngayBatdau > getdate() ";
		}
		if (selected == 2) {
			// dang hoat dong
			tt = "and ngayBatdau <= getdate() and ngayHetHan >= getdate() ";
		}

		if (selected == 3) {
			// da het han
			tt = "and ngayHetHan < getdate() ";
		}
		int offset = page * limit;// lay du lieu bat dau tu vi tri page*20

		try {
			String sql = "select * from KhuyenMai where moTa like N'%" + ten + "%' and ngayBatDau like '%" + ngay
					+ "%' " + tt + "" + "order by maKM desc OFFSET " + offset + " ROWS FETCH NEXT " + limit
					+ " ROWS ONLY";
			tr.begin();
			List<KhuyenMai> khuyenMais = session.createNativeQuery(sql, KhuyenMai.class).getResultList();
			tr.commit();
			return khuyenMais;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	public int tongTrang(String txtSearch, int selected, String ngay, int limit) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (txtSearch == null)
			txtSearch = "";

		String tt = "";

		if (selected == 1) {
			// dang cho
			tt = "and ngayBatdau > getdate() ";
		}
		if (selected == 2) {
			// dang hoat dong
			tt = "and ngayBatdau <= getdate() and ngayHetHan >= getdate() ";
		}
		if (selected == 3) {
			// da het han
			tt = "and ngayHetHan < getdate() ";
		}

		if (limit <= 0) {
			limit = 20;
		}
		if (ngay == null) {
			ngay = "";
		}

		try {
			tr.begin();
			String sql = "select count(*) from Khuyenmai where moTa like N'%" + txtSearch + "%' and ngayBatDau like '%"
					+ ngay + "%' " + tt + "";
			int result = Integer.parseInt(session.createNativeQuery(sql).uniqueResult().toString());
			tr.commit();
			return result % limit == 0 ? result / limit : (result / limit) + 1;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println("tongHang: " + e.getMessage());
			tr.rollback();

		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public KhuyenMai layMaKhuyenMai(String maGiamGia) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean apDungMaGiamGia(String maGiamGia) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "update KhuyenMai set daSuDung =  daSuDung + 1 where  daSuDung < tongSoLuong and maGiamGia = '"
					+ maGiamGia + "' and ngayBatDau <= GETDATE() and ngayHetHan > GETDATE()" + "";
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
}
