package dao;

import java.rmi.RemoteException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.LoaiNhanVien;
import entity.NhanVien;
import service.NhanVienService;

public class NhanVienDao implements NhanVienService {
	private SessionFactory sessionFactory;

	public NhanVienDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

//	@Override
//	public List<NhanVien> findNhanVien(String maNV, String tenNV, String sdt, String CMND) {
//		Session session = sessionFactory.getCurrentSession();
//		Transaction tr = session.getTransaction();
//		List<NhanVien> listNhanVien;
//		String sql = "select * from NhanVien where maNV like N'%" + maNV + "%' and hoTen like N'%" + tenNV
//				+ "%' and soDienThoai like N'%" + sdt + "%' and soCMND like N'%" + CMND + "%'";
//		try {
//			tr.begin();
//			listNhanVien = session.createNativeQuery(sql, NhanVien.class).getResultList();
//			tr.commit();
//			return listNhanVien;
//		} catch (Exception e) {
//			tr.rollback();
//			e.printStackTrace();
//			return null;
//		}
//	}

	@Override
	public boolean themNhanVien(NhanVien nv) {
		String maKH = phatSinhMaTuDong();
		nv.setMaNV(maKH);
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.beginTransaction();
		try {
//			tr.begin();
			session.save(nv);
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
	public boolean suaThongTinNhanVien(NhanVien nv) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.update(nv);
			tr.commit();
			System.out.println("them");
			System.out.println(nv);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			return false;
		}
	}

	@Override
	public String phatSinhMaTuDong() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		String maNV = null;
		String sql = "Select max(maNV) from NhanVien";
		try {
			tr.begin();
			maNV = (String) session.createNativeQuery(sql).getSingleResult();
			tr.commit();
			maNV = new MaTuDong().fomatAA000(maNV);
//			System.out.println(maKhachH);
			return maNV;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<NhanVien> layDanhSachNhanVien() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> listNhanVien;
		String sql = "select * from NhanVien";
		try {
			tr.begin();
			listNhanVien = session.createNativeQuery(sql, NhanVien.class).getResultList();
			tr.commit();
			return listNhanVien;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public NhanVien layNhanVienTheoMa(String ma) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		String sql = "Select * from NhanVien where maNV = '" + ma + "'";
		try {
			tr.begin();
			NhanVien nv = session.createNativeQuery(sql, NhanVien.class).getSingleResult();
			tr.commit();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<LoaiNhanVien> DanhSachLoaiNhanVien() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

//		System.out.println(offset+" - "+ page);
		try {
			tr.begin();
			String sql = "select * from  LoaiNhanVien ";
			List<LoaiNhanVien> dsLoaiNhanVien = session.createNativeQuery(sql, LoaiNhanVien.class).getResultList();
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
	public List<NhanVien> DanhSachNhanVien(int page, String tenNhanVien, String gioiTinh, String trangThaiLamViec,
			String loaiNhanVien, int limit) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (tenNhanVien == null)
			tenNhanVien = "";
		if (gioiTinh == null)
			gioiTinh = "";
		if (trangThaiLamViec == null) {
			trangThaiLamViec = "1";
		}
		if (loaiNhanVien == null)
			loaiNhanVien = "";

		if (limit <= 0) {
			limit = 20;
		}

		int offset = page * limit;// lay du lieu bat dau tu vi tri page*20
//	System.out.println(offset+" - "+ page);

		try {
			tr.begin();

			String sql = "select * from NhanVien inner join DiaChi on  NhanVien.maDC = DiaChi.maDC "
					+ "where  hoTen like '%" + tenNhanVien + " %' and gioiTinh like '%" + gioiTinh
					+ "%' and trangThaiLamViec like '%" + trangThaiLamViec + "%' " + " and maLNV like '%" + loaiNhanVien
					+ "%' " + " order by maLNV desc" + " OFFSET " + offset + " ROWS FETCH NEXT " + limit
					+ " ROWS ONLY;";
//	"from ModelClassname where ClassVariableId= :ClassVariableId"
//	Query q = session.createQuery(sql);
//	q.setParameter("ClassVariableId", 001);

			List<NhanVien> dsNhanVien = session.createNativeQuery(sql, NhanVien.class).getResultList();
			System.out.println(dsNhanVien);
			tr.commit();

			return dsNhanVien;
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int tongTrang(String txtSearch, String gioiTinh, String trangThaiLamViec, String loaiNhanVien, int limit) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		if (txtSearch == null)
			txtSearch = "";
		if (gioiTinh == null)
			gioiTinh = "";
		if (loaiNhanVien == null)
			loaiNhanVien = "";
		String ttlv = "";
		if (trangThaiLamViec != null) {
			ttlv = trangThaiLamViec;
		}
		if (limit <= 0) {
			limit = 20;
		}
		try {
			tr.begin();
			String sql = "select count(*) from NhanVien where hoTen like N'%" + txtSearch
					+ "%'  and trangThaiLamViec like '%" + ttlv + "%'  and gioiTinh like '%" + gioiTinh
					+ "%' and maLNV like '%" + loaiNhanVien + "%'";
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
	public NhanVien layThongTinNhanVienQuaSDT(String sdt) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from nhanVien where soDienThoai = '" + sdt + "'";

			NhanVien nhanVien = session.createNativeQuery(sql, NhanVien.class).getSingleResult();

			tr.commit();

			return nhanVien;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean suaTrangThaiLamViecQuaSoDienThoai(String soDienThoai, boolean trangThaiLamViec)
			throws RemoteException {
		String sql = "UPDATE NhanVien set trangThaiLamViec = :trangThaiLamViec WHERE soDienThoai = :sdt ";
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(sql);
			query.setParameter("trangThaiLamViec", trangThaiLamViec);
			query.setParameter("sdt", soDienThoai);
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
	public NhanVien layThongTinNhanVienQuaCMND(String cmnd) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from NhanVien where soCMND = '" + cmnd + "'";

			NhanVien nhanVien = session.createNativeQuery(sql, NhanVien.class).getSingleResult();

			tr.commit();

			return nhanVien;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<NhanVien> layDanhSachNhanVienTheoTen(String ten) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> nv;
		String sql = "Select * from NhanVien where hoTen like N'%" + ten + "%'";
		try {
			tr.begin();
			nv = session.createNativeQuery(sql, NhanVien.class).getResultList();
			tr.commit();

			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public NhanVien layDanhSachNhanVienQuaLoaiNhanVien(String loaiNV) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from NhanVien join LoaiNhanVien on NhanVien.maLNV= LoaiNhanVien.maLNV where tenLNV = N'"
					+ loaiNV + "'";

			NhanVien nhanVien = session.createNativeQuery(sql, NhanVien.class).getSingleResult();
			System.out.println(nhanVien);
			tr.commit();

			return nhanVien;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean doiMatKhau(String sdt, String oldPassword, String newPassword) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select soDienThoai from NhanVien u where soDienThoai = '%"+ sdt +"%' and password = '%" +oldPassword+"%'";
			
			NhanVien nv= session.createQuery(sql, NhanVien.class).getSingleResult();
			System.out.println("Find");
			System.out.println(nv);
			if(nv==null) {
				System.out.println("khÃ´ng tÃ¬m tháº¥y");
				throw new Exception("SÄ�T hoáº·c máº­ kháº©u khÃ´ng Ä‘Ãºng");
				
			}
			nv.setPassword(newPassword);
			session.merge(nv);
			tr.commit();
			System.out.println("Chnge");
			System.out.println(nv);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			tr.rollback();

		} finally {
			session.close();
		}
		
		return false;
	}

}
