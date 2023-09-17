package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import view.util.FormatCustom;

public class ThongKeTheoNamPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChartPanel chartPanel;
	private ChartPanel chartPanel_1;
	private JLabel lblTongDoanhThu;
	private JLabel lblTongSoGio;
	private JLabel lblTongTienPhong;
	private JLabel lblTongTienDichVu;
	private JLabel lbDoanhThuPhongVip;
	private JLabel lblDoanhthuTrungBinh;
	@SuppressWarnings("rawtypes")
	private static JComboBox combChonNam;
//	private HoaDonDao hoaDonDao;
	private CategoryDataset categoryDataset;
	private static HoaDonDao hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
	private static List<HoaDon> ds;
	private JLabel lblSoHoaDon;
	private JLabel lbldoanhThuPhongTruong;
	private int nam;
	private static String maNhanVien = MainFrame.nhanVien.getLoaiNhanVien().getMaLNV().equals("LNV001") ? ""
			: MainFrame.nhanVien.getMaNV();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ThongKeTheoNamPanel() {
		setForeground(new Color(255, 255, 255));
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.GRAY));
		setSize(1284, 648);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 51));
		separator.setFocusTraversalKeysEnabled(false);

		JLabel lblTngDoanhThu = new JLabel("T\u1ED5ng doanh thu:");
		lblTngDoanhThu.setBackground(new Color(255, 255, 255));
		lblTngDoanhThu.setForeground(new Color(0, 0, 51));
		lblTngDoanhThu.setOpaque(true);
		lblTngDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblTongDoanhThu = new JLabel(" VND");
		lblTongDoanhThu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongDoanhThu.setForeground(new Color(0, 0, 51));
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblTngSGi = new JLabel("T\u1ED5ng s\u1ED1 gi\u1EDD h\u00E1t:");
		lblTngSGi.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTongSoGio = new JLabel("250 Gi\u1EDD");
		lblTongSoGio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongSoGio.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTongTienPhong = new JLabel(" VND");
		lblTongTienPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTienPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblTngTinPhng = new JLabel("T\u1ED5ng ti\u1EC1n ph\u00F2ng:");
		lblTngTinPhng.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTongTienDichVu = new JLabel(" VND");
		lblTongTienDichVu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTienDichVu.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblTngTinDch = new JLabel("T\u1ED5ng ti\u1EC1n d\u1ECBch v\u1EE5:");
		lblTngTinDch.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbDoanhThuPhongVip = new JLabel(" VND");
		lbDoanhThuPhongVip.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDoanhThuPhongVip.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblSHan = new JLabel("Doanh thu ph\u00F2ng VIP:");
		lblSHan.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblDoanhthuTrungBinh = new JLabel(" VND");
		lblDoanhthuTrungBinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoanhthuTrungBinh.setForeground(new Color(0, 0, 51));
		lblDoanhthuTrungBinh.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblDoanhThu_1 = new JLabel("Doanh thu trung bình:");
		lblDoanhThu_1.setForeground(new Color(0, 0, 51));
		lblDoanhThu_1.setToolTipText("");
		lblDoanhThu_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblDoanhThu_2 = new JLabel("Doanh thu ph\u00F2ng th\u01B0\u1EDDng:");
		lblDoanhThu_2.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbldoanhThuPhongTruong = new JLabel(" VND");
		lbldoanhThuPhongTruong.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldoanhThuPhongTruong.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblChnThng = new JLabel("Ch\u1ECDn n\u0103m");
		lblChnThng.setFont(new Font("Tahoma", Font.BOLD, 18));

		@SuppressWarnings("unused")
		JSeparator separator_2 = new JSeparator();

		combChonNam = new JComboBox();
		combChonNam.setBackground(SystemColor.inactiveCaptionBorder);
//		combChonNam.setModel(new DefaultComboBoxModel(new String[] { "2020", "2021", "2022" }));
		combChonNam.setFont(new Font("Tahoma", Font.PLAIN, 14));

		List<Integer> dsNam = hoaDonDao.layNamTuHoaDon();
		for (Integer string : dsNam) {
			combChonNam.addItem(string);
		}
		lblSoHoaDon = new JLabel("159");
		lblSoHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoHoaDon.setForeground(new Color(0, 0, 51));
		lblSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblTngSHa = new JLabel("Tổng số hóa đơn:");
		lblTngSHa.setBackground(new Color(255, 255, 255));
		lblTngSHa.setForeground(new Color(0, 0, 51));
		lblTngSHa.setOpaque(true);
		lblTngSHa.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblDoanhThu_1_1 = new JLabel("(doanh thu/ hóa đơn)");
		lblDoanhThu_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoanhThu_1_1.setForeground(new Color(0, 0, 51));
		lblDoanhThu_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(55)
						.addComponent(lblChnThng, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addGap(68).addComponent(combChonNam, 0, 181, Short.MAX_VALUE).addGap(30))
				.addGroup(gl_panel_1.createSequentialGroup().addGap(9).addComponent(separator,
						GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup().addGap(22).addGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(1).addComponent(lblDoanhThu_2,
										GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSHan, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTngSGi, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(1).addComponent(lblTngTinPhng,
										GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(1).addComponent(lblTngTinDch,
										GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))
								.addGap(2)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup().addGap(1).addComponent(
												lbDoanhThuPhongVip, GroupLayout.PREFERRED_SIZE, 185,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(lblTongSoGio, GroupLayout.PREFERRED_SIZE, 185,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTongTienPhong, GroupLayout.PREFERRED_SIZE, 185,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTongTienDichVu, GroupLayout.PREFERRED_SIZE, 185,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
										.addComponent(lblTngDoanhThu, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblTongDoanhThu,
												GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(
												lblTngSHa, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
										.addGap(32)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
												.addComponent(lbldoanhThuPhongTruong, GroupLayout.PREFERRED_SIZE, 185,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSoHoaDon, GroupLayout.PREFERRED_SIZE, 206,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDoanhThu_1_1, GroupLayout.PREFERRED_SIZE, 201,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblDoanhThu_1, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblDoanhthuTrungBinh,
												GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(19)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(combChonNam, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblChnThng, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(20)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(30)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTngDoanhThu, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTongDoanhThu, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTngSHa, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSoHoaDon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
								.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDoanhThu_2, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbldoanhThuPhongTruong, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addComponent(lblSHan, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblTngSGi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblTngTinPhng, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblTngTinDch, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(50)
										.addComponent(lbDoanhThuPhongVip, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(lblTongSoGio, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(lblTongTienPhong, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10).addComponent(lblTongTienDichVu, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(
								gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDoanhThu_1, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDoanhthuTrungBinh, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblDoanhThu_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(34, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thống kê doanh thu cửa hàng");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBackground(new Color(72, 209, 204));

		chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setLayout(null);
		chartPanel_1 = new ChartPanel(null);

		chartPanel_1.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		chartPanel_1.setPreferredSize(new java.awt.Dimension(560, 367));
		chartPanel_1.setLayout(null);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(469)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(panel_2, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup().addGap(5)
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 461,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(chartPanel_1, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
										.addGap(13))
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1281, Short.MAX_VALUE))
						.addGap(1)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(chartPanel_1,
										GroupLayout.PREFERRED_SIZE, 155, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(panel_1,
										GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))))
				.addGap(8)));
		setLayout(groupLayout);
		nam = LocalDate.now().getYear();
		categoryDataset = createDataset(nam);
		combChonNam.addActionListener(e -> {
			nam = (int) combChonNam.getSelectedItem();
			categoryDataset = createDataset(nam);
			setThongTin(ds);
			chartPanel_1.setChart(createChart(categoryDataset));

		});
	}
	public  void khoiTao() {
		nam = (int) combChonNam.getSelectedItem();
		categoryDataset = createDataset(nam);
		setThongTin(ds);
		chartPanel_1.setChart(createChart(categoryDataset));
	}

	public static JFreeChart createChart(CategoryDataset categoryDataset) {
		int nam = (int) combChonNam.getSelectedItem();
		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU THEO NĂM" + " " + nam, "Tháng",
				"Doanh thu", categoryDataset, PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	private static CategoryDataset createDataset(int nam) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ds = hoaDonDao.layHoaDonTheoNam(nam, maNhanVien);
		for (int i = 1; i < 13; i++) {
			double tong = 0;
			for (HoaDon hoaDon : ds) {
				if (hoaDon.getGioKetThuc().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.getMonthValue() == i) {
					tong += hoaDon.getTongTien();
				}
				dataset.addValue(tong, "Tháng", "" + i);
			}
		}
		return dataset;
	}

	public void setThongTin(List<HoaDon> dsHoaDon) {
		lblSoHoaDon.setText(dsHoaDon.size() + "");
		double tongTienHoaDon = 0;
		double doanhThuThuong = 0;
		double doanhThuVip = 0;
		double tongTienPhong = 0;
		@SuppressWarnings("unused")
		int gioNhanPhong = 0;
		@SuppressWarnings("unused")
		int gioTra = 0;
		@SuppressWarnings("unused")
		int tongGio = 0;
		int tongSoGio = 0;
		double doanhThuTrungBinh = 0, doanhThu, chietKhau;

		for (HoaDon hoaDon : dsHoaDon) {
			int thoiLuongTam = 0;
			List<ChiTietHoaDon> dsChiTiet = hoaDonDao.layChiTietHoaDon(hoaDon.getMaHD());
			for (ChiTietHoaDon c : dsChiTiet) {
				chietKhau = ((hoaDon.getThue() - hoaDon.getKhuyenMai().getChietKhau()) / 100) * c.thanhTien();
//				System.out.println("CK ---" + FormatCustom.chuyenDoiTienTe(chietKhau) + "   " + hoaDon.getMaHD());
				doanhThuThuong += c.getPhong().getLoaiPhong().getMaLP().equals("LP001") ? c.thanhTien() + chietKhau : 0;
				doanhThuVip += c.getPhong().getLoaiPhong().getMaLP().equals("LP002") ? c.thanhTien() + chietKhau : 0;

				thoiLuongTam += c.getThoiLuong();
				tongSoGio += c.getThoiLuong() / 60.0;
			}
			if (thoiLuongTam < 60) {
				ChiTietHoaDon chiTietHoaDonTam = dsChiTiet.get(0);

				if (chiTietHoaDonTam.getPhong().getLoaiPhong().getMaLP().equals("LP001")) {
					doanhThu = ((60 - thoiLuongTam) / 60) * chiTietHoaDonTam.getPhong().getLoaiPhong().getGiaTien();
					doanhThuThuong += ((hoaDon.getThue() - hoaDon.getKhuyenMai().getChietKhau()) / 100) * doanhThu;
//					System.out.println(
//							"CK: " + ((hoaDon.getThue() - hoaDon.getKhuyenMai().getChietKhau()) / 100) * doanhThu + " "
//									+ hoaDon.getMaHD());
					doanhThuThuong += doanhThu;
				} else {
					doanhThu = ((60 - thoiLuongTam) / 60) * chiTietHoaDonTam.getPhong().getLoaiPhong().getGiaTien();
					doanhThuVip += ((hoaDon.getThue() - hoaDon.getKhuyenMai().getChietKhau()) / 100) * doanhThu;
					doanhThuVip += doanhThu;
//					System.out.println(
//							"CK: " + ((hoaDon.getThue() - hoaDon.getKhuyenMai().getChietKhau()) / 100) * doanhThu + " "
//									+ hoaDon.getMaHD());
				}
			}
			double tongTienTam = hoaDon.getTongTien();
//			List<ChiTietDichVu> dsChiTietDV = hoaDonDao.layChiTietDichVu(hoaDon.getMaHD());
//			for (ChiTietDichVu ctDV : dsChiTietDV) {
//				chietKhau = ((hoaDon.getThue() - hoaDon.getKhuyenMai().getChietKhau()) / 100) * ctDV.thanhTien();
//				tongTienDichVu += ctDV.getDichVu().getDonGia() * ctDV.getSoLuong() + chietKhau;
//			}
			tongTienHoaDon += tongTienTam;
		}

		tongTienPhong += doanhThuThuong + doanhThuVip;
		doanhThuTrungBinh = tongTienHoaDon / dsHoaDon.size();
		lblTongDoanhThu.setText(FormatCustom.chuyenDoiTienTe(tongTienHoaDon) + " ");
		lblTongTienPhong.setText(FormatCustom.chuyenDoiTienTe(tongTienPhong) + " ");
		lblTongTienDichVu.setText(FormatCustom.chuyenDoiTienTe(tongTienHoaDon - tongTienPhong) + " ");
		lbDoanhThuPhongVip.setText(FormatCustom.chuyenDoiTienTe(doanhThuVip) + " ");
		lbldoanhThuPhongTruong.setText(FormatCustom.chuyenDoiTienTe(doanhThuThuong) + " ");
		lblDoanhthuTrungBinh.setText(FormatCustom.chuyenDoiTienTe(doanhThuTrungBinh) + " ");
		lblTongSoGio.setText((Math.round(tongSoGio * 10) / 10) + " " + "Giờ");

	}
}
