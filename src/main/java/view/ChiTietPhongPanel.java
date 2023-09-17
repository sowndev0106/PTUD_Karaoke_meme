package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.KhuyenMaiDao;
import dao.PhieuDatPhongDao;
import dao.PhongDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import view.util.FormatCustom;
import view.util.GroupableTableHeader;
import view.util.HeaderRenderer;
import view.util.ImagePanel;

public class ChiTietPhongPanel extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTable tableDichVu;
	private JLabel lblThoiLuong;
	private JLabel lblTongCong;
	private JLabel lblThue;
	private JLabel lblTamTinh;
	private JButton btnChuyenPhong;
	private PhongDao phongDao;
	private Phong phong;
	private JLabel lbTenKhachPhieuDatPhong;
	private JLabel lbGioNhanPhongPhieuDatPhong;
	private JLabel lbSoPhong;
	private DefaultTableModel modelDichVu;
	private ImagePanel pnImagePhong;
	private JButton btnTinhGio;
	private JButton btnTinhTien;
	private JButton btnDatPhongCho;
	private JButton btnNhanPhongCho;
	private JButton btnHuyPhongCho;
	private JButton btnQuayLai;
	private JTextField tfMaGiamGia;
	private HoaDonDao hoaDonDao;
	@SuppressWarnings("unused")
	private KhachHangDao khachHangDao;
	@SuppressWarnings("unused")
	private KhachHang khachHangHoaDon;
	private JLabel lblLeftMGG;
	private JLabel lblTenKhach;
	private KhuyenMaiDao khuyenMaiDao;
	private HoaDon hoaDon;
	private JButton btnKiemTraMaGiamGia;
	private JLabel lblIconMaGiamGia;
	private JButton btnCapNhatDichVu;
	private double tongTien;
	private JLabel lblTienDichVu;
	private JLabel lblTienPhong;
	private JComboBox<String> cbDSPhongChuyen;
	private JLabel lblGioNhanPhong;
	private JLabel lblChietKhau;
	private List<Phong> dsPhongChuyen;
	private PhieuDatPhong phieuDatPhong;
	private PhieuDatPhongDao phieuDatPhongDao;
	private JLabel lbGiaTien;
	private JLabel lbSoNguoi;
	private JLabel lbLoaiPhong;
	private JLabel lbTrangThaiPhong;
	private JLabel lblSDTKhach;
	private JLabel lblTenNhanVien;
	private JLabel lblMaHD;

	public ChiTietPhongPanel() {
		khachHangDao = new KhachHangDao(MainFrame.sessionFactory);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBackground(SystemColor.control);
		setBounds(100, 100, 1295, 673);
		JPanel btnKiemTraSDTKhach = new JPanel();
		btnKiemTraSDTKhach.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		pnImagePhong = new ImagePanel(
				new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/PhongChoThuong.png")).getImage());
		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chuy\u1EC3n ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		btnChuyenPhong = new JButton("Chuy\u1EC3n");
		btnChuyenPhong.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/exchange2.png")));
		btnChuyenPhong.setForeground(Color.WHITE);
		btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChuyenPhong.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnChuyenPhong.setBackground(new Color(60, 179, 113));

		JLabel lblPhngS = new JLabel("Phòng số:");
		lblPhngS.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("Phiếu Đặt Phòng");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(new Color(0, 100, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tên Khách hàng");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbTenKhachPhieuDatPhong = new JLabel("Nguyễn Thanh Sơn");
		lbTenKhachPhieuDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lbGioNhanPhongPhieuDatPhong = new JLabel("23:20 - 11/02/2021");
		lbGioNhanPhongPhieuDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Giờ nhận phòng");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(192, 192, 192));
		separator_1.setForeground(SystemColor.inactiveCaptionBorder);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.inactiveCaptionBorder);
		separator_1_1.setBackground(Color.LIGHT_GRAY);

		JPanel panelRight = new JPanel();
		panelRight.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		panelRight.setForeground(new Color(0, 206, 209));

		JLabel lblNewLabel_2_1 = new JLabel("Hóa đơn tạm");
		lblNewLabel_2_1.setOpaque(true);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2_1.setBackground(SystemColor.controlHighlight);

		modelDichVu = new DefaultTableModel();
		modelDichVu.setDataVector(new Object[][] {},
				new Object[] { "Tên dịch vụ", "Đơn giá", "Đã thêm ", "Thành tiền" });
//		g_lang.add(cm.getColumn(2));
//		g_lang.add(cm.getColumn(3));
////		header.setBackground(Color.black);
////	     header.setForeground(Color.yellow);
//		header.addColumnGroup(g_lang);
//
//		// set center column
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//		h.setPreferredSize(new Dimension(40, 40));
//		((DefaultTableCellRenderer) h.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		HeaderRenderer renderer = new HeaderRenderer();
		// center
		JLabel headerLabel = (JLabel) renderer;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(180, 180, 180), 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 206, 209));

		JLabel lblNewLabel_11 = new JLabel("Chi tiết phòng");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_11.setForeground(Color.WHITE);

		lbSoPhong = new JLabel("01");
		lbSoPhong.setBackground(SystemColor.textText);
		lbSoPhong.setBounds(0, 35, 130, 70);
		lbSoPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lbSoPhong.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lbSoPhong.setForeground(SystemColor.controlLtHighlight);

		cbDSPhongChuyen = new JComboBox<>();
		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup().addGap(49)
						.addComponent(lblPhngS, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cbDSPhongChuyen, 0, 248, Short.MAX_VALUE).addGap(48)
						.addComponent(btnChuyenPhong, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addGap(4)));
		gl_panel1.setVerticalGroup(gl_panel1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel1
				.createSequentialGroup().addGap(5)
				.addGroup(gl_panel1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(cbDSPhongChuyen, Alignment.LEADING)
						.addComponent(lblPhngS, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btnChuyenPhong, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
				.addGap(8)));
		panel1.setLayout(gl_panel1);
		pnImagePhong.setLayout(null);
		pnImagePhong.add(lbSoPhong);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_11,
				GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);

		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuayLai.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnQuayLai.setBackground(new Color(0, 127, 255));

		btnDatPhongCho = new JButton("Đặt phòng chờ");
		btnDatPhongCho.setForeground(Color.WHITE);
		btnDatPhongCho.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDatPhongCho.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnDatPhongCho.setBackground(Color.ORANGE);

		btnTinhGio = new JButton("Đặt phòng ngay");
		btnTinhGio.setForeground(Color.WHITE);
		btnTinhGio.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTinhGio.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnTinhGio.setBackground(Color.GREEN);

		JLabel lblNewLabel_3 = new JLabel("");

		btnNhanPhongCho = new JButton("Nhận phòng chờ");
		btnNhanPhongCho.setForeground(Color.WHITE);
		btnNhanPhongCho.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNhanPhongCho.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnNhanPhongCho.setBackground(Color.ORANGE);

		btnTinhTien = new JButton("Tính tiền");
		btnTinhTien.setForeground(Color.WHITE);
		btnTinhTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTinhTien.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnTinhTien.setBackground(Color.GREEN);

		btnHuyPhongCho = new JButton("Hủy phòng chờ");
		btnHuyPhongCho.setForeground(Color.WHITE);
		btnHuyPhongCho.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuyPhongCho.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnHuyPhongCho.setBackground(Color.ORANGE);

		JPanel panel_4 = new JPanel();

		lblGioNhanPhong = new JLabel("23:20 - 11/02/2021");
		lblGioNhanPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Giờ vào");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panelRight = new GroupLayout(panelRight);
		gl_panelRight
				.setHorizontalGroup(gl_panelRight.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRight.createSequentialGroup().addGap(20)
								.addGroup(gl_panelRight.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelRight.createSequentialGroup()
												.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 589,
														Short.MAX_VALUE)
												.addGap(12))
										.addComponent(panel_11, 0, 0, Short.MAX_VALUE))
								.addContainerGap()));
		gl_panelRight.setVerticalGroup(gl_panelRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRight.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addGap(5).addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE).addGap(0)));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addComponent(lblNewLabel_1_1_1_1_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(50)
						.addComponent(lblGioNhanPhong, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
						.addGap(26)));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1_1_1_1_1).addComponent(lblGioNhanPhong))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_4.setLayout(gl_panel_4);

		JPanel panel_5 = new JPanel();

		lblThoiLuong = new JLabel(" 90 phút");
		lblThoiLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblMGimGi = new JLabel("Thời lượng");
		lblMGimGi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_5
				.createSequentialGroup().addComponent(lblMGimGi, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblThoiLuong, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE).addGap(51)));
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMGimGi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblThoiLuong, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panel_5.setLayout(gl_panel_5);

		JPanel panel_6 = new JPanel();

		lblLeftMGG = new JLabel("Mã giám giá");
		lblLeftMGG.setFont(new Font("Tahoma", Font.PLAIN, 16));

		tfMaGiamGia = new JTextField();
		tfMaGiamGia.setColumns(10);

		lblIconMaGiamGia = new JLabel("");
		lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/check.png")));

		btnKiemTraMaGiamGia = new JButton("Kiểm tra");
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
						.addComponent(lblLeftMGG, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE).addGap(4)
						.addComponent(lblIconMaGiamGia, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tfMaGiamGia, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnKiemTraMaGiamGia, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addGap(1)));
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
						.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
										.addComponent(tfMaGiamGia, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnKiemTraMaGiamGia, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(lblLeftMGG, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIconMaGiamGia, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_6.setLayout(gl_panel_6);

		JPanel panel_9 = new JPanel();

		lblTongCong = new JLabel("9.000.000 VND");
		lblTongCong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("Tổng cộng");
		lblNewLabel_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup().addGap(2)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
						.addGap(15).addComponent(lblTongCong, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_9.setVerticalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 25,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTongCong, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)));
		panel_9.setLayout(gl_panel_9);

		JPanel panel_10 = new JPanel();

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_2 = new JLabel("Tiền dịch vụ");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTienDichVu = new JLabel("4.000.000 VND");
		lblTienDichVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_2, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addGap(16).addComponent(lblTienDichVu, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_10.setVerticalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
						.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_2).addComponent(lblTienDichVu,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_10.setLayout(gl_panel_10);

		JPanel panel_12 = new JPanel();

		lblTienPhong = new JLabel("5.000.000 VND");
		lblTienPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1 = new JLabel("Tiền phòng");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_12 = new GroupLayout(panel_12);
		gl_panel_12.setHorizontalGroup(gl_panel_12.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_12
				.createSequentialGroup()
				.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
				.addGap(1).addComponent(lblTienPhong, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
				.addContainerGap()));
		gl_panel_12.setVerticalGroup(gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_12.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTienPhong, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1)));
		panel_12.setLayout(gl_panel_12);

		JPanel panel_13 = new JPanel();

		JPanel panel_14 = new JPanel();

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_1 = new JLabel("Thuế VAT");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblThue = new JLabel("15%");
		lblThue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_14 = new GroupLayout(panel_14);
		gl_panel_14.setHorizontalGroup(gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createSequentialGroup().addGap(2)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 114,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblThue, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(38, Short.MAX_VALUE)));
		gl_panel_14.setVerticalGroup(gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 25,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblThue, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)));
		panel_14.setLayout(gl_panel_14);

		JPanel panel_15 = new JPanel();

		lblTamTinh = new JLabel("8.550.000 VND");
		lblTamTinh.setForeground(Color.RED);
		lblTamTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1 = new JLabel("Tạm tính");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_15 = new GroupLayout(panel_15);
		gl_panel_15.setHorizontalGroup(gl_panel_15.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_15.createSequentialGroup()
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 118,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTamTinh, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)));
		gl_panel_15.setVerticalGroup(gl_panel_15.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_15.createSequentialGroup()
						.addGroup(gl_panel_15.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1).addComponent(lblTamTinh))
						.addGap(0)));
		panel_15.setLayout(gl_panel_15);

		JScrollPane scrollPane = new JScrollPane();

		// table.setModel(defaultTableModel);
		tableDichVu = new JTable(modelDichVu) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected JTableHeader createDefaultTableHeader() {
				return new GroupableTableHeader(columnModel);
			}
		};

//		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		
//		table.getColumnModel().getColumn(0).setPreferredWidth(80);
//		table.getColumnModel().getColumn(1).setPreferredWidth(150);
//		table.getColumnModel().getColumn(2).setPreferredWidth(80);
//		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
//		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
//		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
//		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

		tableDichVu.setRowHeight(40);

		scrollPane.setViewportView(tableDichVu);

		lblChietKhau = new JLabel("10%");
		lblChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1 = new JLabel("Chiết khấu");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_13 = new GroupLayout(panel_13);
		gl_panel_13.setHorizontalGroup(gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 118,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblChietKhau, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)));
		gl_panel_13.setVerticalGroup(gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblChietKhau, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_1_1)));
		panel_13.setLayout(gl_panel_13);

		btnCapNhatDichVu = new JButton("Cập nhật dịch vụ");
		btnCapNhatDichVu.setForeground(Color.WHITE);
		btnCapNhatDichVu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCapNhatDichVu.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnCapNhatDichVu.setBackground(new Color(60, 179, 113));

		JPanel panel_10_2 = new JPanel();

		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Tên Khách");
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTenKhach = new JLabel("Nguyễn Hoàng Khánh");
		lblTenKhach.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_10_2 = new GroupLayout(panel_10_2);
		gl_panel_10_2.setHorizontalGroup(gl_panel_10_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10_2.createSequentialGroup()
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 85,
								GroupLayout.PREFERRED_SIZE)
						.addGap(38).addComponent(lblTenKhach, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)));
		gl_panel_10_2.setVerticalGroup(gl_panel_10_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10_2.createSequentialGroup()
						.addGroup(gl_panel_10_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTenKhach, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_10_2.setLayout(gl_panel_10_2);

		JPanel panel_6_1 = new JPanel();

		JLabel lblStKhch = new JLabel("SĐT Khách");
		lblStKhch.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblSDTKhach = new JLabel("Nguyễn Hoàng Khánh");
		lblSDTKhach.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDTKhach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_6_1 = new GroupLayout(panel_6_1);
		gl_panel_6_1.setHorizontalGroup(gl_panel_6_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6_1.createSequentialGroup()
						.addComponent(lblStKhch, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE).addGap(26)
						.addComponent(lblSDTKhach, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)));
		gl_panel_6_1.setVerticalGroup(gl_panel_6_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6_1.createSequentialGroup()
						.addGroup(gl_panel_6_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStKhch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSDTKhach, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_6_1.setLayout(gl_panel_6_1);
		
		JPanel panel_10_2_1 = new JPanel();
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1_2 = new JLabel("Tên nhân viên");
		lblNewLabel_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblTenNhanVien = new JLabel("Nguyễn Hoàng Khánh");
		lblTenNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_10_2_1 = new GroupLayout(panel_10_2_1);
		gl_panel_10_2_1.setHorizontalGroup(
			gl_panel_10_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10_2_1.createSequentialGroup()
					.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_2)
					.addGap(22)
					.addComponent(lblTenNhanVien, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
		);
		gl_panel_10_2_1.setVerticalGroup(
			gl_panel_10_2_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 26, Short.MAX_VALUE)
				.addGroup(gl_panel_10_2_1.createSequentialGroup()
					.addGroup(gl_panel_10_2_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_1_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTenNhanVien, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_10_2_1.setLayout(gl_panel_10_2_1);
		
		JPanel panel_4_1 = new JPanel();
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_2 = new JLabel("Mã hóa đơn");
		lblNewLabel_1_1_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblMaHD = new JLabel("");
		lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_4_1 = new GroupLayout(panel_4_1);
		gl_panel_4_1.setHorizontalGroup(
			gl_panel_4_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1.createSequentialGroup()
					.addComponent(lblNewLabel_1_1_1_1_1_1_1_2, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(50)
					.addComponent(lblMaHD, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_panel_4_1.setVerticalGroup(
			gl_panel_4_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4_1.createSequentialGroup()
					.addGroup(gl_panel_4_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1_1_1_2)
						.addComponent(lblMaHD))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4_1.setLayout(gl_panel_4_1);
		GroupLayout gl_panel_11 = new GroupLayout(panel_11);
		gl_panel_11.setHorizontalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_11.createSequentialGroup()
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
					.addGap(84)
					.addComponent(btnCapNhatDichVu, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
					.addGap(65))
				.addGroup(gl_panel_11.createSequentialGroup()
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 262, Short.MAX_VALUE)
						.addComponent(panel_15, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
						.addComponent(panel_13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(40)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
						.addComponent(panel_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
						.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_panel_11.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
					.addGap(14))
				.addGroup(gl_panel_11.createSequentialGroup()
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_10_2, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
							.addComponent(panel_6_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panel_10_2_1, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(panel_5, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 272, Short.MAX_VALUE)))
						.addGroup(gl_panel_11.createSequentialGroup()
							.addGap(22)
							.addComponent(panel_4_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_11.setVerticalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_11.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_4_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addComponent(panel_10_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_10_2_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_11.createSequentialGroup()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addGap(6)
							.addComponent(btnCapNhatDichVu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_11.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_15, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		panel_11.setLayout(gl_panel_11);
		panelRight.setLayout(gl_panelRight);

		JLabel lblNewLabel_2 = new JLabel("");

		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.RAISED, new Color(255, 255, 255), new Color(191, 205, 219)), "",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblNewLabel_2_1_1 = new JLabel("Thông tin phòng");
		lblNewLabel_2_1_1.setOpaque(true);
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setForeground(Color.BLACK);
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2_1_1.setBackground(SystemColor.controlHighlight);
		GroupLayout gl_btnKiemTraSDTKhach = new GroupLayout(btnKiemTraSDTKhach);
		gl_btnKiemTraSDTKhach.setHorizontalGroup(gl_btnKiemTraSDTKhach.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGroup(gl_btnKiemTraSDTKhach
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(10)
								.addGroup(gl_btnKiemTraSDTKhach.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(248).addComponent(
														lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 98, Short.MAX_VALUE))
										.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup()
												.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 250,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)))
								.addGap(1)
								.addComponent(separator_1_1, GroupLayout.PREFERRED_SIZE, 250,
										GroupLayout.PREFERRED_SIZE)
								.addGap(13))
						.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(71)
								.addComponent(lblNewLabel_1_1_1_1, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
								.addGap(95)
								.addComponent(lbTenKhachPhieuDatPhong, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
								.addGap(20))
						.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(71)
								.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
								.addGap(95)
								.addComponent(lbGioNhanPhongPhieuDatPhong, GroupLayout.DEFAULT_SIZE, 238,
										Short.MAX_VALUE)
								.addGap(20))
						.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addContainerGap().addComponent(panel1,
								GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
						.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(25)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE).addGap(185)
								.addComponent(pnImagePhong, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGap(252)))
						.addGap(3))
				.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel_2_1_1, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE).addGap(21))
				.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(60)
						.addComponent(panel_17, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE).addGap(54)));
		gl_btnKiemTraSDTKhach.setVerticalGroup(gl_btnKiemTraSDTKhach.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_btnKiemTraSDTKhach.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addComponent(lblNewLabel_2)
										.addGap(41))
								.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup()
										.addComponent(pnImagePhong, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(8)))
						.addComponent(panel_17, GroupLayout.PREFERRED_SIZE, 170, Short.MAX_VALUE).addGap(1)
						.addGroup(gl_btnKiemTraSDTKhach.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(20).addComponent(
										separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_btnKiemTraSDTKhach.createSequentialGroup().addGap(20).addComponent(
										separator_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(10)
						.addGroup(gl_btnKiemTraSDTKhach.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1_1_1_1).addComponent(lbTenKhachPhieuDatPhong))
						.addGap(18)
						.addGroup(gl_btnKiemTraSDTKhach.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1_1_1_1_1)
								.addComponent(lbGioNhanPhongPhieuDatPhong, Alignment.TRAILING))
						.addGap(8).addComponent(panel1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addGap(9)));

		JPanel panel_16 = new JPanel();

		JLabel lblNewLabel = new JLabel("Trạng thái phòng:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));

		lbTrangThaiPhong = new JLabel("Phòng bận");
		lbTrangThaiPhong.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_16 = new GroupLayout(panel_16);
		gl_panel_16.setHorizontalGroup(gl_panel_16.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_16.createSequentialGroup().addGap(75)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE).addGap(5)
						.addComponent(lbTrangThaiPhong, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
						.addGap(73)));
		gl_panel_16
				.setVerticalGroup(
						gl_panel_16.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_panel_16.createSequentialGroup().addGap(0)
												.addGroup(gl_panel_16.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNewLabel).addComponent(lbTrangThaiPhong))
												.addGap(1)));
		panel_16.setLayout(gl_panel_16);

		JPanel panel_8 = new JPanel();

		JLabel lblLoiPhng = new JLabel("loại phòng:");
		lblLoiPhng.setFont(new Font("Arial", Font.BOLD, 20));

		lbLoaiPhong = new JLabel("Phòng thường");
		lbLoaiPhong.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8
				.setHorizontalGroup(
						gl_panel_8.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_8.createSequentialGroup().addGap(79)
										.addComponent(lblLoiPhng, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lbLoaiPhong,
												GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
										.addGap(69)));
		gl_panel_8.setVerticalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
						.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLoiPhng, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbLoaiPhong, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_8.setLayout(gl_panel_8);

		JPanel panel_7 = new JPanel();

		JLabel lblNewLabel_1_1 = new JLabel("Số lượng người:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));

		lbSoNguoi = new JLabel("5 người");
		lbSoNguoi.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7
				.setHorizontalGroup(
						gl_panel_7.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								gl_panel_7.createSequentialGroup().addGap(83)
										.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lbSoNguoi,
												GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
										.addGap(64)));
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_7
				.createSequentialGroup().addGap(0)
				.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE).addComponent(lbSoNguoi)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGap(5)));
		panel_7.setLayout(gl_panel_7);

		JPanel panel_2 = new JPanel();

		JLabel lblNewLabel_1_1_1 = new JLabel("Giá tiền mỗi giờ:");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));

		lbGiaTien = new JLabel("100. 000 VND");
		lbGiaTien.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addGap(78)
				.addComponent(lblNewLabel_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lbGiaTien, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE).addGap(73)));
		gl_panel_2
				.setVerticalGroup(
						gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup().addGap(1)
										.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 35,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lbGiaTien))
										.addGap(3)));
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel_17 = new GroupLayout(panel_17);
		gl_panel_17.setHorizontalGroup(gl_panel_17.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_17
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_17.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
						.addComponent(panel_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
						.addComponent(panel_8, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
						.addComponent(panel_16, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
				.addContainerGap()));
		gl_panel_17.setVerticalGroup(gl_panel_17.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_17.createSequentialGroup().addGap(14)
						.addComponent(panel_16, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE).addGap(10)
						.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)));
		panel_17.setLayout(gl_panel_17);
		btnKiemTraSDTKhach.setLayout(gl_btnKiemTraSDTKhach);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE).addGap(0)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addComponent(btnQuayLai, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
				.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnHuyPhongCho, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnNhanPhongCho, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE).addGap(5)
				.addComponent(btnDatPhongCho, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE).addGap(6)
				.addComponent(btnTinhTien, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE).addGap(6)
				.addComponent(btnTinhGio, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE).addGap(16))
				.addComponent(panel_3, 0, 0, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(btnKiemTraSDTKhach, GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelRight, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE).addGap(4)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnKiemTraSDTKhach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(panelRight, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(8).addComponent(lblNewLabel_3))
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNhanPhongCho, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnHuyPhongCho, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnQuayLai, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(btnDatPhongCho, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTinhTien, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTinhGio, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(8)));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

		try {
			phongDao = new PhongDao(MainFrame.sessionFactory);
//			dichVuDao = new DichVuDao(MainFrame.sessionFactory);
			phieuDatPhongDao = new PhieuDatPhongDao(MainFrame.sessionFactory);
			hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
			khuyenMaiDao = new KhuyenMaiDao(MainFrame.sessionFactory);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		btnQuayLai.addActionListener(this);
		btnTinhGio.addActionListener(this);
		btnKiemTraMaGiamGia.addActionListener(this);
		btnCapNhatDichVu.addActionListener(this);
		btnTinhTien.addActionListener(this);
		cbDSPhongChuyen.addMouseListener(this);
		btnChuyenPhong.addActionListener(this);
		btnDatPhongCho.addActionListener(this);
		btnHuyPhongCho.addActionListener(this);
		btnNhanPhongCho.addActionListener(this);
		// setting default
		btnKiemTraMaGiamGia.setEnabled(false);
		tfMaGiamGia.setEnabled(false);
		// khong cho sua table
		tableDichVu.setDefaultEditor(Object.class, null);

	}

	public void hienThiDichVuTrongHoaDon() {
		xoaBanDichVu();
		if (hoaDon == null || hoaDon.getChiTietDichVu() == null)
			return;
		tongTien = 0;
		hoaDon.getChiTietDichVu().forEach(e -> {
			tongTien += e.thanhTien();
			modelDichVu.addRow(new String[] { e.getDichVu().getTenDichVu(),
					FormatCustom.chuyenDoiTienTe(e.getDichVu().getDonGia()), Integer.toString(e.getSoLuong()),
					FormatCustom.chuyenDoiTienTe(e.thanhTien()) });
		});
		lblTienDichVu.setText(FormatCustom.chuyenDoiTienTe(tongTien));

	}

	public void khoiTao(String maPhong) {
		this.setVisible(true);
		xoaBanDichVu();
		// cap nhat trang thai phong theo phieu dat phong

//		System.err.println(phongDao.capNhatTrangThaiPhongTuDongTheoPhieuDatPhong());
		hoaDon = null;
		tfMaGiamGia.setText("");
		phong = phongDao.layThongTinPhongQuaMa(maPhong);
		lbSoPhong.setText(phong.getMaPhong().substring(1));
		lbTrangThaiPhong.setText(phong.getTrangThaiPhong().getTenTrangThaiPhong());
		lbLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
		lbSoNguoi.setText(phong.getSoNguoi() + "");
		lbGiaTien.setText(FormatCustom.chuyenDoiTienTe(phong.getLoaiPhong().getGiaTien()));
		lblSDTKhach.setText("");
		lblGioNhanPhong.setText(FormatCustom.dinhDanhThoiGian(new Date()));
		lbTenKhachPhieuDatPhong.setText("");
		lbGioNhanPhongPhieuDatPhong.setText("");
		lblTenKhach.setText("");
		lblChietKhau.setText("0.0%");
		lblThue.setText("0.0%");
		lblTamTinh.setText("0 VND");
		lblThoiLuong.setText("0");
		lblTienDichVu.setText("0 VND");
		lblTienPhong.setText("0 VND");
		lblTongCong.setText("0 VND");
		lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/check.png")));
		caiDatTrangThaiPhong(phong.getTrangThaiPhong().getMaTTP());
	}

	@SuppressWarnings("deprecation")
	public void caiDatTrangThaiPhong(String maTTP) {
//		System.out.println("MaTTP " + maTTP);
		String icon = DanhSachPhongPanel.layDiaChiIconTheoLoaiVaTrangThaiPhong(phong.getLoaiPhong().getMaLP(), maTTP);
		if (maTTP.equalsIgnoreCase(MainFrame.maPhongCho)) {
			// Phòng chờ
			lblTenNhanVien.setText("");
			lblMaHD.setText("");
			lbTrangThaiPhong.setText("Phòng chờ");
			pnImagePhong.setImage(new ImageIcon(ChiTietPhongPanel.class.getResource(icon)).getImage());
			btnDatPhongCho.setVisible(false);
			btnHuyPhongCho.setVisible(true);
			btnNhanPhongCho.setVisible(true);
			btnTinhTien.setVisible(false);
			btnTinhGio.setVisible(true);
			cbDSPhongChuyen.setEnabled(false);
			btnCapNhatDichVu.setEnabled(false);
			btnChuyenPhong.setEnabled(false);
			phieuDatPhong = phieuDatPhongDao.layPhieuDatPhongMoiNhatTheoPhong(phong.getMaPhong());
			lbTenKhachPhieuDatPhong.setText(phieuDatPhong.getKhachHang().getHoTen());
			lbGioNhanPhongPhieuDatPhong.setText(FormatCustom.dinhDanhThoiGian(phieuDatPhong.getThoiGianNhanPhong()));
			Date now = new Date();
//			System.out.println(now.getDay() == phieuDatPhong.getThoiGianNhanPhong().getDay());
//			System.out.println(now.getHours() <= phieuDatPhong.getThoiGianNhanPhong().getHours() - 2);
//			System.out.println(now.getHours());
//			System.out.println(phieuDatPhong.getThoiGianNhanPhong().getHours());
			if (now.getDay() == phieuDatPhong.getThoiGianNhanPhong().getDay()
					&& now.getHours() >= phieuDatPhong.getThoiGianNhanPhong().getHours() - 2) {
				btnTinhGio.setBackground(new Color(169, 169, 169));
			}
			return;
		}
		if (maTTP.equalsIgnoreCase(MainFrame.maPhongBan)) {
			// Phòng bận
			lbTrangThaiPhong.setText("Phòng bận");
			pnImagePhong.setImage(new ImageIcon(ChiTietPhongPanel.class.getResource(icon)).getImage());
			btnDatPhongCho.setVisible(false);
			btnHuyPhongCho.setVisible(false);
			btnNhanPhongCho.setVisible(false);
			btnTinhTien.setVisible(true);
			btnTinhGio.setVisible(false);
			btnKiemTraMaGiamGia.setEnabled(true);
			tfMaGiamGia.setEnabled(true);
			cbDSPhongChuyen.setEnabled(true);
			btnCapNhatDichVu.setEnabled(true);

			btnChuyenPhong.setEnabled(true);
			hienThiDanhSachPhongChuyen();
			if (hoaDon == null) {
				hoaDon = hoaDonDao.layHoaDonMoiNhatTheoPhong(phong.getMaPhong());
			}
			lbTenKhachPhieuDatPhong.setText("");
			lbGioNhanPhongPhieuDatPhong.setText("");

			hienThiDichVuTrongHoaDon();
			hienThiThongTinHoaDon();
			return;
		}
		if (maTTP.equalsIgnoreCase(MainFrame.maPhongTrong)) {
			// Phòng trống
			lblTenNhanVien.setText("");
			lblMaHD.setText("");
			lbTrangThaiPhong.setText("Phòng trống");
			pnImagePhong.setImage(new ImageIcon(ChiTietPhongPanel.class.getResource(icon)).getImage());
			btnDatPhongCho.setVisible(true);
			btnHuyPhongCho.setVisible(false);
			btnNhanPhongCho.setVisible(false);
			btnTinhTien.setVisible(false);
			btnTinhGio.setVisible(true);
			cbDSPhongChuyen.setEnabled(false);
			btnKiemTraMaGiamGia.setEnabled(false);
			btnCapNhatDichVu.setEnabled(false);

			btnChuyenPhong.setEnabled(false);
			return;
		}
		if (maTTP.equalsIgnoreCase(MainFrame.maPhongTam)) {
			// Phòng tạm
			lbTrangThaiPhong.setText("Phòng tạm");
			pnImagePhong.setImage(new ImageIcon(ChiTietPhongPanel.class.getResource(icon)).getImage());
			btnDatPhongCho.setVisible(false);
			btnHuyPhongCho.setVisible(true);
			btnNhanPhongCho.setVisible(false);
			btnTinhTien.setVisible(true);
			btnTinhGio.setVisible(false);
			btnKiemTraMaGiamGia.setEnabled(true);
			tfMaGiamGia.setEnabled(true);
			cbDSPhongChuyen.setEnabled(true);
			btnCapNhatDichVu.setEnabled(true);
			phieuDatPhong = phieuDatPhongDao.layPhieuDatPhongMoiNhatTheoPhong(phong.getMaPhong());
			lbTenKhachPhieuDatPhong.setText(phieuDatPhong.getKhachHang().getHoTen());
			lbGioNhanPhongPhieuDatPhong.setText(FormatCustom.dinhDanhThoiGian(phieuDatPhong.getThoiGianNhanPhong()));
			btnChuyenPhong.setEnabled(true);
			if (hoaDon == null) {
				hoaDon = hoaDonDao.layHoaDonMoiNhatTheoPhong(phong.getMaPhong());
			}
			hienThiDanhSachPhongChuyen();
			hienThiThongTinHoaDon();
			hienThiDichVuTrongHoaDon();
			return;
		}

	}

	public void hienThiThongTinHoaDon() {
		if (hoaDon == null)
			return;
		// *** need call function the first
		hoaDon.setGioKetThuc(new Date());
		int phut = hoaDon.capNhatThoiLuong(phong.getMaPhong());
		double tienThanhToan = hoaDon.thanhTien();
		double tienPhong = hoaDon.tinhTienPhong();
		double tienDichVu = hoaDon.tinhTienDichVu();

		lblTamTinh.setText(FormatCustom.chuyenDoiTienTe(tienThanhToan));
		lblTienDichVu.setText(FormatCustom.chuyenDoiTienTe(tienDichVu));
		lblTenNhanVien.setText(hoaDon.getNhanVienLap().getHoTen());
		lblThue.setText(hoaDon.getThue() + "%");
		lblGioNhanPhong.setText(FormatCustom.dinhDanhThoiGian(hoaDon.getGioNhanPhong()));
		lblThoiLuong.setText(FormatCustom.dinhDangGio(phut));
		lblMaHD.setText(hoaDon.getMaHD());
		if (phut < 60) {
			// low 1 hours then price += 1 hours - thoiGian
			// Room the first will be add money
			tienPhong += hoaDon.getChiTietHoaDon().get(0).getPhong().getLoaiPhong().getGiaTien() * ((60 - phut) / 60.0);

		}
		lblTongCong.setText(FormatCustom.chuyenDoiTienTe(tienPhong + tienDichVu));
		lblTienPhong.setText(FormatCustom.chuyenDoiTienTe(tienPhong));

		if (hoaDon.getKhachHang() != null
				&& !hoaDon.getKhachHang().getMaKH().equals(MainFrame.khachHangMacDinh.getMaKH())) {
			lblSDTKhach.setText(hoaDon.getKhachHang().getSoDienThoai());
			lblTenKhach.setText(hoaDon.getKhachHang().getHoTen());
		}

		if (hoaDon.getKhuyenMai() != null
				&& !hoaDon.getKhuyenMai().getMaKM().equals((MainFrame.khuyenMaiMacDinh.getMaKM()))) {
			tfMaGiamGia.setText(hoaDon.getKhuyenMai().getMaGiamGia());
			lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/true.png")));
			lblChietKhau.setText(hoaDon.getKhuyenMai().getChietKhau() + "%");
		}

	}

	public void xoaBanDichVu() {
		for (int i = modelDichVu.getRowCount(); i > 0; i--) {
			modelDichVu.removeRow(0);
		}
	}

	public void hienThiDanhSachPhongChuyen() {
		dsPhongChuyen = phongDao.layDanhSachPhongTheoTrangThaiPhong(MainFrame.maPhongTrong);
		cbDSPhongChuyen.removeAllItems();
		dsPhongChuyen.forEach(e -> {
			String s = e.getLoaiPhong().getMaLP().equalsIgnoreCase("LP001") ? "" : " - VIP ";
			cbDSPhongChuyen.addItem(e.getMaPhong().substring(1) + s + " - Số người " + e.getSoNguoi());
		});
	}

	public boolean kiemTraMaGiamGia() {
		String maGiamGia = tfMaGiamGia.getText().toString().trim();
		if (maGiamGia.equals("")) {
			hoaDon.setKhuyenMai(MainFrame.khuyenMaiMacDinh);
			lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/true.png")));
			lblChietKhau.setText("0.0%");
			double tienThanhToan = hoaDon.thanhTien();
			lblTamTinh.setText(FormatCustom.chuyenDoiTienTe(tienThanhToan));
			return true;
		}
		KhuyenMai khuyenMai = null;
		try {
			khuyenMai = khuyenMaiDao.layKhuyenMaiTheoMa(maGiamGia);
		} catch (Exception e) {

		}
		if (khuyenMai == null) {
			JOptionPane.showMessageDialog(this, "Mã khuyến mãi không tồn tại. Bạn có thể nhập lại hoặc bỏ trống");
			lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove1.png")));
			hoaDon.setKhuyenMai(MainFrame.khuyenMaiMacDinh);
			tfMaGiamGia.setFocusable(true);
			return false;
		}

		lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/true.png")));
		hoaDon.setKhuyenMai(khuyenMai);
		lblChietKhau.setText(khuyenMai.getChietKhau() + "%");
		double tienThanhToan = hoaDon.thanhTien();
		lblTamTinh.setText(FormatCustom.chuyenDoiTienTe(tienThanhToan));
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnQuayLai)) {
			setVisible(false);
			MainFrame.danhSachPhongPanel.lamMoi("Vừa vào phòng " + phong.getMaPhong().substring(1));
			MainFrame.danhSachPhongPanel.setVisible(true);
			if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongBan)
					|| phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongTam)) {
				MainFrame.danhSachPhongPanel.btnDangHat.doClick();
			} else {
				MainFrame.danhSachPhongPanel.btnDangCho.doClick();
			}
			return;
		}

		if (object.equals(btnTinhGio)) {
			int xacnhan = -1;
			if (phieuDatPhong == null) {
				xacnhan = JOptionPane.showConfirmDialog(this, "Xác nhận đặt phòng ", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (xacnhan != JOptionPane.YES_OPTION) {
					return;
				}
			} else {
				xacnhan = JOptionPane.showConfirmDialog(this,
						"Xác nhận đặt phòng PHÒNG TẠM của khách " + phieuDatPhong.getKhachHang().getHoTen() + " ",
						"Thông báo", JOptionPane.YES_NO_OPTION);
				if (xacnhan != JOptionPane.YES_OPTION) {
					return;
				}
			}

			hoaDon = null;
			DialogDatPhong dialogDatPhong = new DialogDatPhong();
			dialogDatPhong.khoiTao(phong);
//			dialogDatPhong.setVisible(true);
			hoaDon = dialogDatPhong.getHoaDon();
			if (hoaDon == null) {
				return;
			}
			phong = dialogDatPhong.getPhong();
			caiDatTrangThaiPhong(phong.getTrangThaiPhong().getMaTTP());
			return;
		}
//		if (object.equals(btnKiemTraSDTKKhach)) {
//			kiemTraSDTKhach();
//			hoaDonDao.capNhatHoaDon(hoaDon);
//			return;
//		}
		if (object.equals(btnKiemTraMaGiamGia)) {
			if (tfMaGiamGia.getText().toString().trim().equalsIgnoreCase("")) {
				lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/check.png")));
				hoaDon.setKhuyenMai(MainFrame.khuyenMaiMacDinh);
			} else if (!kiemTraMaGiamGia()) {
				lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove1.png")));
				tfMaGiamGia.setFocusable(true);
			}
			hoaDonDao.capNhatHoaDon(hoaDon);
			return;
		}
		if (object.equals(btnCapNhatDichVu)) {
			DialogCapNhatDichVuTrongHoaDon capNhatDichVuTrongHoaDon = new DialogCapNhatDichVuTrongHoaDon(hoaDon, phong);
			capNhatDichVuTrongHoaDon.setVisible(true);
			hoaDon.setChiTietDichVu(hoaDonDao.layChiTietDichVu(hoaDon.getMaHD()));

			khoiTao(phong.getMaPhong());

			return;
		}
		if (object.equals(btnTinhTien)) {
			hoaDon.setGioKetThuc(new Date());
			DialogTinhTien dialogTinhTien = new DialogTinhTien(hoaDon, phong);
			dialogTinhTien.setVisible(true);

			khoiTao(phong.getMaPhong());

			return;
		}
		if (object.equals(btnChuyenPhong)) {
			int indexDSPhong = cbDSPhongChuyen.getSelectedIndex();
			Phong phongChuyen = dsPhongChuyen.get(indexDSPhong);
			int xacnhan = JOptionPane.showConfirmDialog(this,
					"Bạn có chắc chắn muốn đỗi phòng sang phòng " + phongChuyen.getMaPhong(), "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacnhan != JOptionPane.YES_OPTION) {
				return;
			}
			ChiTietHoaDon chiTietHoaDonChuyen = new ChiTietHoaDon(phongChuyen, hoaDon, 0);
			int thoiLuong = 0;
			hoaDon.capNhatThoiLuong(phong.getMaPhong());
			boolean constan = false;
			for (ChiTietHoaDon chiTietHoaDon : hoaDon.getChiTietHoaDon()) {
				System.err.println(chiTietHoaDon.getPhong().getMaPhong() + chiTietHoaDon.getThoiLuong());
				if (chiTietHoaDon.getPhong().getMaPhong().equals(chiTietHoaDonChuyen.getPhong().getMaPhong())) {
					constan = true;
				}
				if (chiTietHoaDon.getPhong().getMaPhong().equals(phong.getMaPhong())) {
					thoiLuong = chiTietHoaDon.getThoiLuong();
				}
			}
			if (!constan) {
				hoaDon.getChiTietHoaDon().add(chiTietHoaDonChuyen);
				hoaDonDao.themHoacCapNhatChiTietHoaDon(chiTietHoaDonChuyen);
			}

			hoaDonDao.themHoacCapNhatChiTietHoaDon(new ChiTietHoaDon(phong, hoaDon, thoiLuong));
			phongDao.capNhatTrangThaiPhong(phongChuyen.getMaPhong(), MainFrame.maPhongBan);

			if (phong.getTrangThaiPhong().getMaTTP().equalsIgnoreCase(MainFrame.maPhongTam)) {
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongCho);
			} else {
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongTrong);
			}
			khoiTao(phongChuyen.getMaPhong());
			JOptionPane.showMessageDialog(this, "Chuyển phòng thành công");
			return;
		}
		if (object.equals(btnDatPhongCho)) {
			DialogDatPhongCho phieuDatPhongCho = new DialogDatPhongCho(phong);
			phieuDatPhongCho.setVisible(true);
			if (phieuDatPhongCho.trangThai) {
				phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongCho, "Phòng chờ"));
				caiDatTrangThaiPhong(MainFrame.maPhongCho);
			}
			return;
		}
		if (object.equals(btnHuyPhongCho)) {
			int xacnhan = JOptionPane.showConfirmDialog(this,
					"Bạn có chắc chắn HUỶ phòng chờ của :" + phieuDatPhong.getKhachHang().getHoTen(), "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacnhan != JOptionPane.YES_OPTION) {
				return;
			}
			if (!phieuDatPhongDao.huyPhieuDatPhong(phieuDatPhong.getMaPDP())) {
				JOptionPane.showMessageDialog(this, "Hủy phòng chờ THẤT BẠI");
				return;
			}
			if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongTam)) {
				phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongBan, "Phòng bận"));
				caiDatTrangThaiPhong(MainFrame.maPhongBan);
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongBan);
			} else {
				phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongTrong, "Phòng trống"));
				caiDatTrangThaiPhong(MainFrame.maPhongTrong);
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongTrong);
			}

			JOptionPane.showMessageDialog(this, "Hủy phòng chờ thành công");
			return;
		}
		if (object.equals(btnNhanPhongCho)) {
			int xacnhan = JOptionPane.showConfirmDialog(this,
					"Xác nhận nhận phòng của: " + phieuDatPhong.getKhachHang().getHoTen(), "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacnhan != JOptionPane.YES_OPTION) {
				return;
			}
			hoaDon = null;
			hoaDon = new HoaDon(MainFrame.nhanVien, MainFrame.khuyenMaiMacDinh, phieuDatPhong.getKhachHang());
			hoaDon.setChiTietHoaDon(Arrays.asList(new ChiTietHoaDon(phong, hoaDon, 0)));
			phieuDatPhongDao.huyPhieuDatPhong(phieuDatPhong.getMaPDP());
			phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongBan);
			if (hoaDonDao.themHoaDon(hoaDon)) {
				System.out.println("add Bill success");
			} else {
				System.out.println("add Bill fail");
			}

			phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongBan, "Phòng bận"));
			caiDatTrangThaiPhong(MainFrame.maPhongBan);

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void lamMoi() {
		// TODO Auto-generated method stub
		if (this.phong != null) {
			khoiTao(phong.getMaPhong());
		}

	}
}
