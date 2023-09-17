
//=============  Nhóm 11 =========
// 169507571 - Nguyễn Thanh Sơn
// 19520401 - Phan Thành Công 
// 19468371 - Nguyễn Thị Minh Châu

































package view;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.hibernate.SessionFactory;

import dao.MySessionFactory;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;

public class MainFrame extends JMenu implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	public static DanhSachPhongPanel danhSachPhongPanel;
	private static ThongKeTheoNgayPanel thongKeTheoNgayPanel;
	private JMenu mnThongKe;
	private JMenuItem mnTKDoanhThuNgay;
	private JMenuItem mnTKDoanhThuThang;
	private JMenuItem mnTKDoanhThuNam;
	private JMenuItem mnNhanVien;
	private JMenuItem mnKhachHang;
	private static DSKhuyenMaiPanel dsKhuyenMaiPanel;
	private static DSDichVuPanel dsDichVu;
	private JMenuItem mntmThoat;
	private JMenuItem mntmDangXuat;
	private JMenu mnQuanLy;
	private JMenuItem mnHoaDon;
	private JMenuItem mnDichVu;
	private JMenuItem mnKhuyenMai;
	private JMenuItem mnTTNhanVien;
	private static HoaDonPanel hoaDonPanel;
	private static GiaoDienChinh giaoDienChinh;
	private static ThongKeTheoThangPanel thongKeTheoThangPanel;
	private static ThongKeTheoNamPanel thongKeTheoNamPanel;
	private static NhanVienPanel nhanVienPanel;
	private static KhachHangPanel khachHangPanel;
	public static ChiTietPhongPanel chiTietPhongPanel;
	public static String maPhongCho = "TTP002";
	public static String maPhongBan = "TTP001";
	public static String maPhongTrong = "TTP003";
	public static String maPhongTam = "TTP004";
	public static KhachHang khachHangMacDinh = new KhachHang("KHAA001");
	public static KhuyenMai khuyenMaiMacDinh = new KhuyenMai("KMAA001");
	public static String maQuanLy = "LNV001";

	public static int screenWidth;
	public static int screenHeight;
	public static SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	public static NhanVien nhanVien;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JMenuItem mnDSPhong;
	private JMenuItem mntmPDP;
	private DialogTimPhieuDatPhongCho dialogTimPhieuDatPhongCho;
	private JMenuItem mnTrangChu;
	private JMenuItem mnTroGiup;
	private static ThongKeKhachHangPanel thongKeKhachHangPanel;
	private JMenuItem mntnTKKhachHang;

	@SuppressWarnings("static-access")
	public MainFrame(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 setLocationRelativeTo(null);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		for (int i = 0; i < gs.length; i++) {
			DisplayMode dm = gs[i].getDisplayMode();
			screenWidth = dm.getWidth();
			screenHeight = dm.getHeight();
		}
		mainFrame = new JFrame("Quản lý karaoke MeMe");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/icon/karaoke.png")));
		mainFrame.setSize(1295, 720);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		mainFrame.setUndecorated(true);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "none");

		JMenu mnHeThong = new JMenu("Hệ Thống");
		mnHeThong.setBackground(Color.WHITE);
		mnHeThong.setForeground(Color.BLACK);
		mnHeThong.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnHeThong.setMnemonic(KeyEvent.VK_H);
		mnHeThong.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/system-update.png")));
		menuBar.add(mnHeThong);

		mnTrangChu = new JMenuItem("Trang chủ");
		mnTrangChu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnTrangChu.setMnemonic(KeyEvent.VK_T);
		mnHeThong.add(mnTrangChu);
		mnTrangChu.addActionListener(this);

		mnTTNhanVien = new JMenuItem("Tài khoản");
		mnTTNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnTTNhanVien.setMnemonic(KeyEvent.VK_T);
		mnHeThong.add(mnTTNhanVien);
		mnTTNhanVien.addActionListener(this);

		mnTroGiup = new JMenuItem("Trợ giúp");
		mnTroGiup.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnTroGiup.setMnemonic(KeyEvent.VK_G);
		mnHeThong.add(mnTroGiup);
		mnTroGiup.addActionListener(this);

		mntmDangXuat = new JMenuItem("Đăng Xuất");
		mntmDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mntmDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmDangXuat.setMnemonic(KeyEvent.VK_X);
		mnHeThong.add(mntmDangXuat);

		mntmThoat = new JMenuItem("Thoát");
		mntmThoat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		mntmThoat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmThoat.setMnemonic(KeyEvent.VK_O);
		mnHeThong.add(mntmThoat);

		mnQuanLy = new JMenu("Danh Mục ");
		mnQuanLy.setBackground(Color.WHITE);
		mnQuanLy.setMnemonic(KeyEvent.VK_D);
		mnQuanLy.setForeground(Color.BLACK);
		mnQuanLy.setFont(new Font("Times New Roman", Font.BOLD, 18));
		menuBar.add(mnQuanLy);

		mnHoaDon = new JMenuItem("Hoá đơn");
		mnHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnQuanLy.add(mnHoaDon);

		mnKhachHang = new JMenuItem("Khách Hàng");
		mnKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnQuanLy.add(mnKhachHang);
		mnNhanVien = new JMenuItem("Nhân Viên");
		mnNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		if (nhanVien.getLoaiNhanVien().getMaLNV().equalsIgnoreCase("LNV001")) {
			mnQuanLy.add(mnNhanVien);
		}

		mnDichVu = new JMenuItem("Dịch Vụ");
		mnDichVu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnQuanLy.add(mnDichVu);

		mnKhuyenMai = new JMenuItem("Khuyến mại");
		mnKhuyenMai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnQuanLy.add(mnKhuyenMai);

		JMenuItem mnPhong = new JMenuItem("Phòng");
		mnPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		mnQuanLy.add(mnPhong);

		JMenu mnXuLy = new JMenu("Xử Lý ");
		mnXuLy.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/wine-menu.png")));
		mnXuLy.setBackground(Color.WHITE);
		mnXuLy.setForeground(Color.BLACK);
		mnXuLy.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnXuLy.setMnemonic(KeyEvent.VK_X);
		menuBar.add(mnXuLy);

		mnDSPhong = new JMenuItem("Phòng hát");
		mnDSPhong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_DOWN_MASK));
		mnDSPhong.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/iconTrong.png")));
		mnDSPhong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnDSPhong.addActionListener(this);
		mnDSPhong.setMnemonic(KeyEvent.VK_P);
		mnXuLy.add(mnDSPhong);

		JMenu mnTimKiem = new JMenu("Tìm Kiếm  ");
		mnTimKiem.setBackground(Color.WHITE);
		mnTimKiem.addActionListener(this);
		mnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		menuBar.add(mnTimKiem);

		mntmPDP = new JMenuItem("Phiếu đặt phòng");
		mntmPDP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnDSPhong.setMnemonic(KeyEvent.VK_W);
		mnTimKiem.add(mntmPDP);

		mnThongKe = new JMenu("Thống Kê  ");
		mnThongKe.setBackground(Color.WHITE);
		mnThongKe.setForeground(Color.BLACK);
		mnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		menuBar.add(mnThongKe);

		JMenu mnDoanhThu = new JMenu("Doanh thu");
		mnDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnThongKe.add(mnDoanhThu);

		mnTKDoanhThuNgay = new JMenuItem("Theo ngày");
		mnTKDoanhThuNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnDoanhThu.add(mnTKDoanhThuNgay);

		mnTKDoanhThuThang = new JMenuItem("Theo tháng");
		mnTKDoanhThuThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnDoanhThu.add(mnTKDoanhThuThang);

		mnTKDoanhThuNam = new JMenuItem("Theo năm");
		mnTKDoanhThuNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnDoanhThu.add(mnTKDoanhThuNam);

		mntnTKKhachHang = new JMenuItem("Khách hàng");
		mntnTKKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		mnThongKe.add(mntnTKKhachHang);

		JMenuItem mntmDoanhThu = new JMenuItem("Doanh Thu");
		mntmDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		mnThongKe.add(mntmDoanhThu);

		JMenu mnKhchHng = new JMenu("Khách Hàng");
		mnKhchHng.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		mnThongKe.add(mnKhchHng);

		JMenuItem mntmThueNhieu = new JMenuItem("Thuê Nhiều Nhất");
		mntmThueNhieu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnKhchHng.add(mntmThueNhieu);

		JMenuItem mntmKHQuaHan = new JMenuItem("Quá Hạn Trả");
		mntmKHQuaHan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnKhchHng.add(mntmKHQuaHan);

		mnTTNhanVien.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/user (6).png")));
		mnTroGiup.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/customer.png")));
		mntmDangXuat.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/log-out.png")));
		mntmThoat.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/log-out (1).png")));
		mnQuanLy.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/operating-system.png")));
		mnHoaDon.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/bill (1).png")));
		mnKhachHang.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/customer-service.png")));
		mnNhanVien.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/emPage.png")));
		mnDichVu.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/24-hours.png")));
		mnKhuyenMai.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/promotion.png")));
		mnPhong.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/room (1).png")));
		mnTimKiem.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/search (2).png")));
		mntmPDP.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/budget.png")));
		mnThongKe.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/bar-chart.png")));
		mnDoanhThu.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/stats.png")));
		mnTKDoanhThuNgay.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/trend.png")));
		mnTKDoanhThuThang.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/trend.png")));
		mnTKDoanhThuNam.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/trend.png")));
		mnTrangChu.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/home.png")));
		mntnTKKhachHang.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/development.png")));

		mainFrame.setVisible(true);
		mnTKDoanhThuNgay.addActionListener(this);
		mnTKDoanhThuThang.addActionListener(this);
		mnTKDoanhThuNam.addActionListener(this);
		mnNhanVien.addActionListener(this);
		mnKhachHang.addActionListener(this);
		mnKhuyenMai.addActionListener(this);
		mnDichVu.addActionListener(this);
		mntmDangXuat.addActionListener(this);
		mntmThoat.addActionListener(this);
		mnHoaDon.addActionListener(this);
		mntmPDP.addActionListener(this);
		mntnTKKhachHang.addActionListener(this);

		danhSachPhongPanel = new DanhSachPhongPanel();

		chiTietPhongPanel = new ChiTietPhongPanel();
		thongKeTheoNgayPanel = new ThongKeTheoNgayPanel();
		thongKeTheoThangPanel = new ThongKeTheoThangPanel();
		thongKeTheoNamPanel = new ThongKeTheoNamPanel();
		khachHangPanel = new KhachHangPanel();
		thongKeKhachHangPanel = new ThongKeKhachHangPanel();
		nhanVienPanel = new NhanVienPanel();
		dsKhuyenMaiPanel = new DSKhuyenMaiPanel();
		dsDichVu = new DSDichVuPanel();
		giaoDienChinh = new GiaoDienChinh();
		dialogTimPhieuDatPhongCho = new DialogTimPhieuDatPhongCho();

		hoaDonPanel = new HoaDonPanel();

		disableAllPanel();
		GroupLayout gl_dsKhuyenMaiPanel = new GroupLayout(dsKhuyenMaiPanel);
		gl_dsKhuyenMaiPanel.setHorizontalGroup(
				gl_dsKhuyenMaiPanel.createParallelGroup(Alignment.LEADING).addGap(0, 1620, Short.MAX_VALUE));
		gl_dsKhuyenMaiPanel.setVerticalGroup(
				gl_dsKhuyenMaiPanel.createParallelGroup(Alignment.LEADING).addGap(0, 983, Short.MAX_VALUE));
		dsKhuyenMaiPanel.setLayout(gl_dsKhuyenMaiPanel);

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setOpaque(true);
		menuBar.add(panel);

		lblNewLabel = new JLabel("NV: Phan Thành Công ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		if (nhanVien.getLoaiNhanVien().getMaLNV().trim().equalsIgnoreCase("LNV001")) {
			lblNewLabel.setText("QL: " + nhanVien.getHoTen());
		} else {
			lblNewLabel.setText("NV: " + nhanVien.getHoTen());
		}
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/adminLogin.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(355, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addGap(19)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))));
		panel.setLayout(gl_panel);
		mainFrame.getContentPane().add(thongKeKhachHangPanel);
		GroupLayout groupLayout = new GroupLayout(mainFrame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 1289, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(chiTietPhongPanel, GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE).addGap(6))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(nhanVienPanel, GroupLayout.DEFAULT_SIZE, 1275, Short.MAX_VALUE).addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(dsKhuyenMaiPanel, GroupLayout.DEFAULT_SIZE, 1275, Short.MAX_VALUE).addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(giaoDienChinh, GroupLayout.PREFERRED_SIZE, 1275, Short.MAX_VALUE).addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(danhSachPhongPanel, GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE).addGap(6))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(khachHangPanel, GroupLayout.PREFERRED_SIZE, 1275, Short.MAX_VALUE).addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(thongKeKhachHangPanel, GroupLayout.PREFERRED_SIZE, 1285, Short.MAX_VALUE)
						.addGap(4))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(hoaDonPanel, GroupLayout.PREFERRED_SIZE, 1279, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(thongKeTheoNamPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												1275, Short.MAX_VALUE)
										.addComponent(thongKeTheoNgayPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												1275, Short.MAX_VALUE)
										.addComponent(thongKeTheoThangPanel, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(dsDichVu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(14)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(5)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(chiTietPhongPanel,
								GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(nhanVienPanel,
								GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(dsKhuyenMaiPanel,
								GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(giaoDienChinh,
								GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(danhSachPhongPanel,
								GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(khachHangPanel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(dsDichVu,
								GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
						.addComponent(thongKeKhachHangPanel, GroupLayout.PREFERRED_SIZE, 651, Short.MAX_VALUE)))
				.addGroup(groupLayout.createSequentialGroup().addGap(51)
						.addComponent(thongKeTheoThangPanel, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE).addGap(6))
				.addGroup(groupLayout.createSequentialGroup().addGap(51)
						.addComponent(thongKeTheoNgayPanel, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE).addGap(6))
				.addGroup(groupLayout.createSequentialGroup().addGap(51)
						.addComponent(hoaDonPanel, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE).addGap(6))
				.addGroup(groupLayout.createSequentialGroup().addGap(51)
						.addComponent(thongKeTheoNamPanel, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE).addGap(6)));
		mainFrame.getContentPane().setLayout(groupLayout);
		giaoDienChinh.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();

		// Dialog o day
		// khong disable
		if (object.equals(mntmPDP)) {
			dialogTimPhieuDatPhongCho.khoiTao();
			if (chiTietPhongPanel.isVisible()) {
				chiTietPhongPanel.lamMoi();
			}
			if (danhSachPhongPanel.isVisible()) {
				danhSachPhongPanel.lamMoi("");
			}
			return;
		}
		if (object.equals(mnTroGiup)) {
			try {
				Desktop.getDesktop().open(new File("document\\HuongDanSuDung.pdf"));
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (object.equals(mnTTNhanVien)) {
			new TTDangNhap().setVisible(true);
			return;
		}

		disableAllPanel();
		if (object.equals(mnDSPhong)) {
			danhSachPhongPanel.setVisible(true);
			danhSachPhongPanel.khoiTao();
			return;
		}
		if (object.equals(mnTKDoanhThuNgay)) {
			thongKeTheoNgayPanel.setVisible(true);
			return;
		}
		if (object.equals(mnTKDoanhThuThang)) {
			thongKeTheoThangPanel.setVisible(true);
			thongKeTheoThangPanel.khoiTao();
			return;
		}
		if (object.equals(mnTKDoanhThuNam)) {
			thongKeTheoNamPanel.setVisible(true);
			thongKeTheoNamPanel.khoiTao();
			return;
		}
		if (object.equals(mnNhanVien)) {
			nhanVienPanel.setVisible(true);
			nhanVienPanel.khoiTaoDuLieu();
			return;
		}
		if (object.equals(mnKhachHang)) {
			khachHangPanel.setVisible(true);
			khachHangPanel.khoiTao();
			return;
		}
		if (object.equals(mnKhuyenMai)) {
			dsKhuyenMaiPanel.setVisible(true);
			dsKhuyenMaiPanel.khoiTao();
			return;
		}
		if (object.equals(mnDichVu)) {
			dsDichVu.setVisible(true);
			dsDichVu.khoiTao();
			return;
		}
		if (object.equals(mnHoaDon)) {
			hoaDonPanel.setVisible(true);
			hoaDonPanel.khoiTaoDuLieu();
			return;
		}
		if (object.equals(mntmDangXuat)) {
			disableAllPanel();
			mainFrame.setVisible(false);
			new DangNhap().setVisible(true);
			return;
		}
		if (object.equals(mntmThoat)) {
			System.exit(0);
			return;

		}
		if (object.equals(mnTrangChu)) {
			giaoDienChinh.setVisible(true);
			return;
		}
		if (object.equals(mntnTKKhachHang)) {
			thongKeKhachHangPanel.setVisible(true);
			thongKeKhachHangPanel.khoiTao();
			return;
		}
	}

	public static void disableAllPanel() {
		danhSachPhongPanel.setVisible(false);
		chiTietPhongPanel.setVisible(false);
		khachHangPanel.setVisible(false);
		nhanVienPanel.setVisible(false);
		dsDichVu.setVisible(false);
		dsKhuyenMaiPanel.setVisible(false);
		thongKeTheoNamPanel.setVisible(false);
		thongKeTheoNgayPanel.setVisible(false);
		thongKeTheoThangPanel.setVisible(false);
		giaoDienChinh.setVisible(false);
		hoaDonPanel.setVisible(false);
		thongKeKhachHangPanel.setVisible(false);
	}
}
