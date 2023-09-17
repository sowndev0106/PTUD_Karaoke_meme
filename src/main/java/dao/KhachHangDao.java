package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.KhachHang;
import service.KhachHangService;

public class KhachHangDao implements KhachHangService {
	private SessionFactory sessionFactory;

	public KhachHangDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	

	@Override
	public boolean themKhachHang(KhachHang kh) {
		String maKH = phatSinhMaTuDong();
		kh.setMaKH(maKH);
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.beginTransaction();

		try {
//			tr.begin();
			session.save(kh);
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
	public List<KhachHang> layDanhSachKhacHang() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<KhachHang> listNhanVien;
		String sql = "select * from KhachHang";
		try {
			tr.begin();
			listNhanVien = session.createNativeQuery(sql, KhachHang.class).getResultList();
			tr.commit();
			return listNhanVien;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();

		}
		session.close();
		return null;
	}

	@Override
	public KhachHang layKhachHangTheoMa(String ma) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		String sql = "Select * from KhachHang where maKH like N'%"+ma+"%'";
		try {
			tr.begin();
			KhachHang kh = session.createNativeQuery(sql, KhachHang.class).getSingleResult();
			tr.commit();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<KhachHang> layDanhSachKhachHangTheoTen(String ten) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<KhachHang> kh;
		String sql = "Select * from KhachHang where hoTen like N'%"+ten+"%'";
		try {
			tr.begin();
			kh = session.createNativeQuery(sql,KhachHang.class).getResultList();
			tr.commit();
			
			System.out.println(kh);
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public String phatSinhMaTuDong() {

		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		String maKhachH = null;
		String sql = "Select max(maKH) from KhachHang";
		try {
			tr.begin();
			maKhachH = (String) session.createNativeQuery(sql).getSingleResult();
			tr.commit();
			System.out.println(maKhachH);
			maKhachH = new MaTuDong().fomatAA000(maKhachH);
//			System.out.println(maKhachH);
			return maKhachH;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	public List<KhachHang> layDanhSachKhachHangTheoGioiTinh(boolean  gt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		String sql = "select * from KhachHang where gioiTinh  ='"+gt+"'";
		List<KhachHang> gioitinh;
		try {
			tr.begin();
			gioitinh =  session.createNativeQuery(sql,KhachHang.class).getResultList();
			tr.commit();
//			System.out.println(gioitinh);
			return gioitinh;

		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		session.close();
		return null;
	}

	@Override
	public boolean suaKhachHang(KhachHang kh) {
		
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.beginTransaction();

		try {
//			tr.begin();
			session.update(kh);
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
	public KhachHang layKhachHangTheoSDT(String sdt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		String sql = "Select * from KhachHang where soDienThoai  like N'%"+sdt+"%'";
		try {
			tr.begin();
			KhachHang kh = session.createNativeQuery(sql,KhachHang.class).getSingleResult();
			tr.commit();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}



	@Override
	public List<KhachHang> layDanhSachKhachHang(int page,String tenKH, String gioiTinh, int limit) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (tenKH == null)
			tenKH = "";
		if (gioiTinh == null)
			gioiTinh = "";
		if (limit <= 0) {
			limit = 20;
		}

		int offset = page * limit;// lay du lieu bat dau tu vi tri page*20
//		System.out.println(offset+" - "+ page);
try {
	tr.begin();
//	String query ="SELECT *FROM KhachHang where  hoTen like '%" + tenKH + " %' and gioiTinh like '%" + gioiTinh + " %' order by maKH desc OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY;";
	String sql= "SELECT *FROM KhachHang  where hoTen like '%" + tenKH + "%' and gioiTinh like '%" + gioiTinh + "%' order by maKH desc OFFSET "+ offset +"  ROWS FETCH NEXT "+ limit +"  ROWS ONLY;";
	List<KhachHang> dsKH = session.createNativeQuery(sql,KhachHang.class).getResultList();
	tr.commit();
	return dsKH;
} catch (Exception e) {
	tr.rollback();
	e.printStackTrace();
} finally {
	session.close();
}
return null;
	}


	@Override
	public int tongTrang(String txtSearch, String gioiTinh, int limit) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (txtSearch == null)
			txtSearch = "";
		if (gioiTinh == null)
			gioiTinh = "";
		
		if (limit <= 0) {
			limit = 20;
		}
		try {
			tr.begin();
			String sql = "select count(*) from KhachHang where hoTen like N'%" + txtSearch + "%'   and gioiTinh like '%" + gioiTinh + "%' ";
			int result = Integer.parseInt(session.createNativeQuery(sql).uniqueResult().toString());
System.out.println("Tongtrang");
System.out.println(result);
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

}
