package dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;

public class HoaDonDao {

	private SessionFactory sessionFactory;

	public HoaDonDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("deprecation")
	public List<HoaDon> layDanhSachHoaDon(String maHD, String tenKH, String sdtKH, Date ngayLapHD, Date filterThoiGian,
			int page, int limit, String maNhanVien) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		String sql = " select * from HoaDon join KhachHang on HoaDon.maKH =  KhachHang.maKH where trangThai = 1 ";

		if (maHD != null && !maHD.equals("")) {
			sql += " and maHD = '" + maHD + "' ";
		} else {

			if (tenKH != null && !tenKH.equals(""))
				sql += " and hoTen like N'%" + tenKH + "%'";
			if (sdtKH != null && !sdtKH.equals(""))
				sql += " and soDienThoai like '%" + sdtKH + "%'";
		}
		if (ngayLapHD != null) {
			sql += " and ngayLap like '"
					+ String.format("%d-%02d-%02d", ngayLapHD.getYear(), ngayLapHD.getMonth() == 0 ? 12:ngayLapHD.getMonth(), ngayLapHD.getDate());
			sql += "%' ";
		} else if (filterThoiGian != null) {
			sql += " and ngayLap >=  " + String.format("'%d-%02d-%02d'", filterThoiGian.getYear() + 1900,
					filterThoiGian.getMonth() + 1, filterThoiGian.getDate());
		}
		if (limit <= 0) {
			limit = 20;
		}
		if (maNhanVien != null && !maNhanVien.equals("")) {
			sql += " and maNV = '" + maNhanVien + "'";
		}

		int offset = page * limit;// lay du lieu bat dau tu vi tri page*20
		try {
			tr.begin();
			sql += " order by maHD desc " + " OFFSET " + offset + " ROWS FETCH NEXT 20 ROWS ONLY";
			System.out.println(sql);
			List<HoaDon> dsHoaDon = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return dsHoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public int layTongTrang(String maHD, String tenKH, String sdtKH, Date ngayLapHD, Date filterThoiGian, int limit,
			String maNhanVien) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		String sql = "select count(*) from HoaDon join KhachHang on HoaDon.maKH = KhachHang.maKH where trangThai = 1 ";

		if (maHD != null && !maHD.equals("")) {
			sql += " and maHD = '" + maHD + "' ";
		} else {

			if (tenKH != null && !tenKH.equals(""))
				sql += " and hoTen like N'%" + tenKH + "%'";
			if (sdtKH != null && !sdtKH.equals(""))
				sql += " and soDienThoai like '%" + sdtKH + "%'";
		}
		if (ngayLapHD != null) {
			sql += " and ngayLap like '" + String.format("%d-%02d-%02d", ngayLapHD.getYear() + 1900,
					ngayLapHD.getMonth() + 1, ngayLapHD.getDate()) + "%'";
		} else if (filterThoiGian != null) {
			sql += " and ngayLap >=  " + String.format("'%d-%02d-%02d'", filterThoiGian.getYear() + 1900,
					filterThoiGian.getMonth() + 1, filterThoiGian.getDate());
		}
		if (limit <= 0) {
			limit = 20;
		}
		if (maNhanVien != null && !maNhanVien.equals("")) {
			sql += " and maNV = '" + maNhanVien + "'";
		}

		try {
			tr.begin();
			int result = Integer.parseInt(session.createNativeQuery(sql).uniqueResult().toString());
			tr.commit();
			int index = result % limit == 0 ? result / limit : (result / limit) + 1;
			return index == 0 ? 1 : index;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return 1;
	}

	/**
	 * 
	 * @return
	 */
	public String phatSinhMaTuDong() {
		String maHoaDon = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select max(maHD) from HoaDon";
			maHoaDon = (String) session.createNativeQuery(sql).getSingleResult();
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
		} finally {
			session.close();
		}
		if (maHoaDon == null || maHoaDon.equalsIgnoreCase(""))
			maHoaDon = "HDAA000";
		return new MaTuDong().fomatAA000(maHoaDon);
	}

	public boolean themHoaDon(HoaDon hoaDon) {

		hoaDon.setMaHD(phatSinhMaTuDong());
		System.out.println(hoaDon.getMaHD());
		if (hoaDon.getKhachHang() == null) {
			// default customer
			hoaDon.setKhachHang(new KhachHang("KHAA001"));
		}

		if (hoaDon.getKhuyenMai() == null) {
			// default Voucher
			hoaDon.setKhuyenMai(new KhuyenMai("KMAA001"));
		}
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.save(hoaDon);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return false;
	}

	public boolean capNhatHoaDon(HoaDon hoaDon) {
		if (hoaDon.getKhachHang() == null) {
			// default customer
			hoaDon.setKhachHang(new KhachHang("KHAA001"));
		}

		if (hoaDon.getKhuyenMai() == null) {
			// default Voucher
			hoaDon.setKhuyenMai(new KhuyenMai("KMAA001"));
		}
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			System.err.println("update");
			session.update(hoaDon);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return false;
	}

	public HoaDon layHoaDonTheoMa(String maHD) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			HoaDon hoaDon = session.find(HoaDon.class, maHD);
			tr.commit();
			return hoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public HoaDon layHoaDonMoiNhatTheoPhong(String maPhong) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select * from HoaDon where trangThai = 0 and maHD = (select top 1 maHD  from ChiTietHoaDon where maPhong = '"
					+ maPhong + "' order by maHD desc)";
			HoaDon hoaDon = session.createNativeQuery(sql, HoaDon.class).getSingleResult();
			tr.commit();
			hoaDon.setChiTietHoaDon(layChiTietHoaDon(hoaDon.getMaHD()));
			hoaDon.setChiTietDichVu(layChiTietDichVu(hoaDon.getMaHD()));
			System.out.println(sql);
			return hoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public List<ChiTietDichVu> layChiTietDichVu(String maHD) {

		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select * from ChiTietDichVu where maHD = '" + maHD + "'";
			List<ChiTietDichVu> listCTDV = session.createNativeQuery(sql, ChiTietDichVu.class).getResultList();
			tr.commit();
			return listCTDV;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;

	}

	public List<ChiTietHoaDon> layChiTietHoaDon(String maHD) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select * from ChiTietHoaDon where maHD = '" + maHD + "'";
			List<ChiTietHoaDon> dsCTHoaDon = session.createNativeQuery(sql, ChiTietHoaDon.class).getResultList();
			tr.commit();
			return dsCTHoaDon;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	public boolean themChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.saveOrUpdate(chiTietDichVu);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return false;
	}

	public boolean themHoacCapNhatChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.saveOrUpdate(chiTietHoaDon);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return false;
	}

	public boolean xoaChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.delete(chiTietDichVu);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return false;
	}

//	Chau
	@SuppressWarnings("unchecked")
	public List<Integer> layNamTuHoaDon() {
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();
		List<Integer> ngayLap;
		String sql = "select year(ngayLap) from HoaDon group by  year(ngayLap) ";

		try {
			tr.begin();
			ngayLap = session.createNativeQuery(sql).getResultList();
			tr.commit();

			return ngayLap;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return null;
	}

	public List<HoaDon> layHoaDonTheoNgay(int ngay, int thang, int nam, String maNhanVien) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select * from HoaDon where day(ngayLap) =" + ngay + " and month(ngayLap) =" + thang
					+ " and year(ngaylap)=" + nam + " and trangThai =1 and maNV like '%" + maNhanVien + "%'";
			List<HoaDon> list = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return null;

	}

	public List<HoaDon> layHoaDonTheoThang(int thang, int nam, String maNhanVien) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select * from HoaDon where month(ngayLap) =" + nam + " and year(ngaylap)=" + thang
					+ " and trangThai =1 and maNV like '%" + maNhanVien + "%'";
			List<HoaDon> list = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return null;

	}

	public List<HoaDon> layHoaDonTheoNam(int nam, String maNhanVien) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select * from HoaDon where year(ngayLap) =" + nam + " and trangThai =1 and maNV like '%"
					+ maNhanVien + "%'";
			List<HoaDon> list = session.createNativeQuery(sql, HoaDon.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return null;

	}

	
	//Thong Ke Khach Hang
	
	//SELECT TOP 10 KH.maKH ,SUM(HD.tongTien) AS tong FROM HoaDon HD JOIN KhachHang KH ON HD.maKH = KH.maKH WHERE year(ngayLap)  like '2021%' and trangThai =1 GROUP BY KH.maKH ORDER BY tong desc
	public Map<KhachHang, Double> topKhachHangTheoNam(int nam) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "SELECT TOP 10 KH.maKH ,SUM(HD.tongTien) AS tong FROM HoaDon HD JOIN KhachHang KH ON HD.maKH = KH.maKH WHERE year(ngayLap)  = "+nam+" and trangThai =1 GROUP BY KH.maKH ORDER BY tong desc";
			List<?> list = session.createNativeQuery(sql).getResultList();
			Map<KhachHang, Double> map= new HashMap<KhachHang, Double>();
			for (Object o : list) {
				Object[] oo=(Object[]) o;
				KhachHang kh = session.find(KhachHang.class, oo[0]);
				map.put(kh,(Double) oo[1]);
			}
			tr.commit();
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return null;
	}
	
	//SELECT TOP 10 KH.maKH ,SUM(HD.tongTien) AS tong FROM HoaDon HD JOIN KhachHang KH ON HD.maKH = KH.maKH WHERE  year(ngayLap)  = 2021 and month(ngayLap) =11 and trangThai =1 GROUP BY KH.maKH ORDER BY tong desc
	public Map<KhachHang, Double> topKhachHangTheoThang(int thang, int nam) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "SELECT TOP 20 KH.maKH ,SUM(HD.tongTien) AS tong FROM HoaDon HD JOIN KhachHang KH ON HD.maKH = KH.maKH WHERE year(ngayLap)  = "+nam+" and month(ngayLap)  = "+thang+"  and trangThai =1 GROUP BY KH.maKH ORDER BY tong desc";
			List<?> list = session.createNativeQuery(sql).getResultList();
			Map<KhachHang, Double> map= new HashMap<KhachHang, Double>();
			for (Object o : list) {
				Object[] oo=(Object[]) o;
				KhachHang kh = session.find(KhachHang.class, oo[0]);
				map.put(kh,(Double) oo[1]);
			}
			tr.commit();
			System.out.println(list);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return null;
	}
	//SELECT TOP 10 KH.maKH ,SUM(HD.tongTien) AS tong FROM HoaDon HD JOIN KhachHang KH ON HD.maKH = KH.maKH WHERE day(ngayLap) =1 and  year(ngayLap)  = 2021 and month(ngayLap) =12 and trangThai =1 GROUP BY KH.maKH ORDER BY tong desc
	public Map<KhachHang, Double> topKhachHangTheoNgay(int ngay, int thang, int nam) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "SELECT TOP 10 KH.maKH ,SUM(HD.tongTien) AS tong FROM HoaDon HD JOIN KhachHang KH ON HD.maKH = KH.maKH WHERE day(ngayLap) = "+ngay+" and  year(ngayLap)  = "+nam+" and month(ngayLap)  = "+thang+"  and trangThai =1 GROUP BY KH.maKH ORDER BY tong desc";
			List<?> list = session.createNativeQuery(sql).getResultList();
			Map<KhachHang, Double> map= new HashMap<KhachHang, Double>();
			for (Object o : list) {
				Object[] oo=(Object[]) o;
				KhachHang kh = session.find(KhachHang.class, oo[0]);
				map.put(kh,(Double) oo[1]);
			}
			tr.commit();
			System.out.println(list);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		session.close();
		return null;
	}
}
