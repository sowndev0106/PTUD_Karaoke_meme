package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import view.util.FormatCustom;
import view.util.HeaderRenderer;

public class ThongKeTheoNgayPanel extends JPanel implements ActionListener, PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static List<HoaDon> dsHoaDonTrongThang;
	private ChartPanel chartPanel;

	private JLabel lblSoHoaDon;
	private JLabel lblTongDoanhThu;
	private JLabel lbldoanhThuPhongTruong;
	private JLabel lbDoanhThuPhongVip;
	private JLabel lblTongSoGio;
	private JLabel lblTongTienPhong;
	private JLabel lblTongTienDichVu;
	private JLabel lblDoanhthuTrungBinh;
	private JDateChooser combChonNam;
	private DefaultTableModel tableModel;
	@SuppressWarnings("unused")
	private CategoryDataset categoryDataset;
	private static HoaDonDao hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
	private static List<HoaDon> ds;
	@SuppressWarnings("unused")
	private static HoaDon hoaDon;
	private JTable table;
	private int thang;
	private int ngay;
	private int nam;
	private String maNhanVien = MainFrame.nhanVien.getLoaiNhanVien().getMaLNV().equals("LNV001") ? ""
			: MainFrame.nhanVien.getMaNV();

	public ThongKeTheoNgayPanel() {
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setSize(1285, 651);

		JLabel lblNewLabel_1 = new JLabel("Thống kê doanh thu theo ngày");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBackground(new Color(72, 209, 204));

		chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBackground(Color.WHITE);

		JSeparator separator = new JSeparator();
		separator.setFocusTraversalKeysEnabled(false);
		separator.setBackground(new Color(0, 0, 51));

		JLabel lblDoanhThu_2 = new JLabel("Doanh thu phòng thường:");
		lblDoanhThu_2.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblSHan = new JLabel("Doanh thu phòng VIP:");
		lblSHan.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblTngSGi = new JLabel("Tổng số giờ hát:");
		lblTngSGi.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblTngTinPhng = new JLabel("Tổng tiền phòng:");
		lblTngTinPhng.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblTngTinDch = new JLabel("Tổng tiền dịch vụ:");
		lblTngTinDch.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbDoanhThuPhongVip = new JLabel(" VND");
		lbDoanhThuPhongVip.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDoanhThuPhongVip.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTongSoGio = new JLabel(" Giờ");
		lblTongSoGio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongSoGio.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTongTienPhong = new JLabel(" VND");
		lblTongTienPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTienPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTongTienDichVu = new JLabel(" VND");
		lblTongTienDichVu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTienDichVu.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblDoanhThu_1_1 = new JLabel("(doanh thu/ hóa đơn)");
		lblDoanhThu_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoanhThu_1_1.setForeground(new Color(0, 0, 51));
		lblDoanhThu_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblDoanhThu_1 = new JLabel("Doanh thu trung bình:");
		lblDoanhThu_1.setToolTipText("");
		lblDoanhThu_1.setForeground(new Color(0, 0, 51));
		lblDoanhThu_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblDoanhthuTrungBinh = new JLabel(" VND");
		lblDoanhthuTrungBinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoanhthuTrungBinh.setForeground(new Color(0, 0, 51));
		lblDoanhthuTrungBinh.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblTngDoanhThu = new JLabel("Tổng doanh thu:");
		lblTngDoanhThu.setOpaque(true);
		lblTngDoanhThu.setForeground(new Color(0, 0, 51));
		lblTngDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTngDoanhThu.setBackground(Color.WHITE);

		lblTongDoanhThu = new JLabel(" VND");
		lblTongDoanhThu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongDoanhThu.setForeground(new Color(0, 0, 51));
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblTngSHa = new JLabel("Tổng số hóa đơn:");
		lblTngSHa.setOpaque(true);
		lblTngSHa.setForeground(new Color(0, 0, 51));
		lblTngSHa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTngSHa.setBackground(Color.WHITE);

		lbldoanhThuPhongTruong = new JLabel(" VND");
		lbldoanhThuPhongTruong.setHorizontalAlignment(SwingConstants.RIGHT);
		lbldoanhThuPhongTruong.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblSoHoaDon = new JLabel("0");
		lblSoHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoHoaDon.setForeground(new Color(0, 0, 51));
		lblSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblChnThng_1_1 = new JLabel("Chọn tháng và năm");
		lblChnThng_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		combChonNam = new JDateChooser((Date) null, "dd/MM/yyyy");
		combChonNam.setDate(new Date());
		combChonNam.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
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
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblTngDoanhThu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTngSHa, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 170,
												Short.MAX_VALUE))
								.addGap(32)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(lbldoanhThuPhongTruong, GroupLayout.PREFERRED_SIZE, 185,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSoHoaDon, GroupLayout.PREFERRED_SIZE, 206,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTongDoanhThu, GroupLayout.PREFERRED_SIZE, 206,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDoanhThu_1_1, GroupLayout.PREFERRED_SIZE, 201,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblDoanhThu_1, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblDoanhthuTrungBinh,
												GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))))
				.addGroup(gl_panel_1.createSequentialGroup().addGap(24)
						.addComponent(lblChnThng_1_1, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
						.addGap(51)
						.addComponent(combChonNam, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(24)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChnThng_1_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(combChonNam, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addComponent(
						separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(30)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTngDoanhThu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTongDoanhThu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTngSHa, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSoHoaDon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
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
								.addComponent(lblTongSoGio, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblTongTienPhong, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(lblTongTienDichVu, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDoanhThu_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDoanhthuTrungBinh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblDoanhThu_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(36, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(7)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 453, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE).addGap(10))
				.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblNewLabel_1,
						GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(2)
				.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(7)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));

		table = new JTable();
		table.setRowHeight(35);
		scrollPane.setColumnHeaderView(table);
		setLayout(groupLayout);
		table.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã hóa đơn", "Ngày lập", "Tên khách hàng", "SĐT Khách", "Chiết khấu", "Tổng tiền" }));
		if(maNhanVien.equals("")) {
			table.setModel(tableModel = new DefaultTableModel(new Object[][] {},
					new String[] { "Mã hóa đơn", "Ngày lập", "Tên khách hàng", "SĐT Khách", "Chiết khấu", "Tổng tiền", "Nhân viên" }));
		}
		scrollPane.setViewportView(table);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		JTableHeader h = table.getTableHeader();
		h.setPreferredSize(new Dimension(40, 40));
		((DefaultTableCellRenderer) h.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		HeaderRenderer renderer = new HeaderRenderer();
		// center
		JLabel headerLabel = (JLabel) renderer;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		// set color Header Table
		@SuppressWarnings("unused")
		TableColumnModel columnmodel;
		columnmodel = table.getColumnModel();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
			renderer.setBackgroundColor(new Color(185, 219, 215));
			renderer.setForegroundColor(Color.black);
			renderer.setBorder(new LineBorder(new Color(0, 206, 209)));
		}

		combChonNam.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if (combChonNam.getDate() == null) {
					xoaBang();
					LocalDate now = LocalDate.now();
					ngay = now.getDayOfMonth();
					thang = now.getMonthValue();
					nam = now.getYear();
					docuLieuVaoBang(ngay, thang, nam);
				}

				if (combChonNam.getDate() != null) {
					xoaBang();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = combChonNam.getDate();
					String[] s = dateFormat.format(date).split("/");
					System.out.println(s[0]);
					docuLieuVaoBang(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
				}
			}
		});
		// khong cho sua table
		table.setDefaultEditor(Object.class, null);
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

	public void docuLieuVaoBang(int ngay, int thang, int nam) {
		ds = hoaDonDao.layHoaDonTheoNgay(ngay, thang, nam, maNhanVien);
		setThongTin(ds);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if(maNhanVien.equals("")) {
			for (HoaDon e : ds) {
				tableModel.addRow(new String[] { e.getMaHD(), df.format(e.getNgayLap()), e.getKhachHang().getHoTen(),
						e.getKhachHang().getSoDienThoai(), e.getChietKhau() + " %",
						FormatCustom.chuyenDoiTienTe(e.getTongTien()), e.getNhanVienLap().getHoTen()});
			}
		} else {
			for (HoaDon e : ds) {
				tableModel.addRow(new String[] { e.getMaHD(), df.format(e.getNgayLap()), e.getKhachHang().getHoTen(),
						e.getKhachHang().getSoDienThoai(), e.getChietKhau() + " %",
						FormatCustom.chuyenDoiTienTe(e.getTongTien()) });
			}
		}
	}

	public void xoaBang() {
		for (int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

}
