package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.KhuyenMaiDao;
import dao.PhongDao;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.Phong;
import entity.TrangThaiPhong;
import view.util.FormatCustom;

public class DialogTinhTien extends JDialog implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JTable tableChiTiet = new JTable();
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JTextField tfMaGiamGia;
	private JTextField tfTienNhan;
	private JLabel lblGioNhanPhong;
	private JLabel lblGioTraPhong;
	private JLabel lblTenKhach;
	private JLabel lblThue;
	private JLabel lblChietKhau;
	private JButton btnKiemTraMaGiamGia;
	private JLabel lblTongCong;
	private JLabel lblTienPhong;
	private JLabel lblTienDichVu;
//	private HoaDonDao hoaDonDao;
	private HoaDon hoaDon;
	private double tongTienDichVu;
	private double tongTienPhong;
	private JButton btnQuayLai;
	private JButton btnThanhToan;
	private KhuyenMaiDao khuyenMaiDao;
	private HoaDonDao hoaDonDao;
	private JLabel lblIconMaGiamGia;
	private double tienThanhToan;
	private JLabel lblIconTienNhan;
	private JLabel lblTienThua;
	private JLabel lblTienThanhToan;
	private Phong phong;
	static boolean trangThai;
	private JLabel lblSDTKhach;
	private PhongDao phongDao;
	private JCheckBox checkboxInHoaDon;
	private JLabel lblTenNhanVien;
	private JLabel lblTongThoiLuong;
	private JLabel lblThoiLuong;

	public DialogTinhTien(HoaDon hoaDon, Phong phong) {
		this.hoaDon = hoaDon;
		this.phong = phong;
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 750, 735);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(SystemColor.textHighlightText);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 167, 736, 300);

		contentPanel.add(scrollPane);

		tableChiTiet.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Số thứ tự", "Tên", "Số lượng / Thời gian", "Đơn giá", "Đơn vị tính", "Thành tiền" }));
		scrollPane.setViewportView(tableChiTiet);
		tableChiTiet.setEnabled(false);
		tableChiTiet.setRowHeight(40);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(0, 0, 746, 50);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("TÍNH TIỀN");
		lblNewLabel_3.setBounds(293, 0, 159, 50);
		lblNewLabel_3.setBackground(Color.CYAN);
		lblNewLabel_3.setForeground(Color.WHITE);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 30));

		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBackground(SystemColor.textHighlightText);
		panel_6_1.setBounds(10, 63, 367, 26);
		contentPanel.add(panel_6_1);

		JLabel lblStKhch = new JLabel("SĐT Khách");
		lblStKhch.setBounds(0, 0, 84, 25);
		lblStKhch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_6_1.setLayout(null);
		panel_6_1.add(lblStKhch);

		lblSDTKhach = new JLabel("");
		lblSDTKhach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDTKhach.setBounds(119, 0, 167, 25);
		panel_6_1.add(lblSDTKhach);

		JPanel panel_10_2 = new JPanel();
		panel_10_2.setBackground(SystemColor.textHighlightText);
		panel_10_2.setBounds(10, 99, 367, 26);
		contentPanel.add(panel_10_2);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Tên Khách");
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(0, 0, 85, 25);
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTenKhach = new JLabel("");
		lblTenKhach.setBounds(120, 0, 167, 25);
		lblTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_10_2.setLayout(null);
		panel_10_2.add(lblNewLabel_1_1_1_1_1_1_1_1);
		panel_10_2.add(lblTenKhach);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.textHighlightText);
		panel_4.setBounds(435, 99, 300, 25);
		contentPanel.add(panel_4);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Giờ trả phòng:");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(0, 0, 108, 25);
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblGioTraPhong = new JLabel("23:20 - 11/02/2021");
		lblGioTraPhong.setBounds(132, 0, 142, 25);
		lblGioTraPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.setLayout(null);
		panel_4.add(lblNewLabel_1_1_1_1_1_1_1);
		panel_4.add(lblGioTraPhong);

		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(SystemColor.textHighlightText);
		panel_4_1.setBounds(435, 63, 300, 25);
		contentPanel.add(panel_4_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1_2 = new JLabel("Giờ nhận phòng:");
		lblNewLabel_1_1_1_1_1_1_1_2.setBounds(0, 0, 122, 25);
		lblNewLabel_1_1_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblGioNhanPhong = new JLabel("23:20 - 11/02/2021");
		lblGioNhanPhong.setBounds(132, 0, 142, 25);
		lblGioNhanPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4_1.setLayout(null);
		panel_4_1.add(lblNewLabel_1_1_1_1_1_1_1_2);
		panel_4_1.add(lblGioNhanPhong);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.textHighlightText);
		panel_6.setBounds(10, 477, 280, 26);
		contentPanel.add(panel_6);

		JLabel lblLeftMGG = new JLabel("Mã giám giá");
		lblLeftMGG.setBackground(SystemColor.textHighlightText);
		lblLeftMGG.setBounds(0, 0, 100, 25);
		lblLeftMGG.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblIconMaGiamGia = new JLabel("");
		lblIconMaGiamGia.setIcon(new ImageIcon(DialogTinhTien.class.getResource("/icon/check.png")));
		lblIconMaGiamGia.setBackground(SystemColor.textHighlightText);
		lblIconMaGiamGia.setBounds(95, 0, 16, 25);

		tfMaGiamGia = new JTextField();
		tfMaGiamGia.setBackground(SystemColor.control);
		tfMaGiamGia.setBounds(120, 0, 150, 25);
		tfMaGiamGia.setColumns(10);
		panel_6.setLayout(null);
		panel_6.add(lblLeftMGG);
		panel_6.add(lblIconMaGiamGia);
		panel_6.add(tfMaGiamGia);

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(SystemColor.textHighlightText);
		panel_13.setBounds(10, 512, 280, 25);
		contentPanel.add(panel_13);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1 = new JLabel("Chiết khấu");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setBounds(0, 0, 100, 20);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblChietKhau = new JLabel("0%");
		lblChietKhau.setBackground(SystemColor.textHighlightText);
		lblChietKhau.setBounds(120, 0, 150, 25);
		lblChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_13.setLayout(null);
		panel_13.add(lblNewLabel_1_1_1_1_1_1_1_1_1_1);
		panel_13.add(lblChietKhau);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(SystemColor.textHighlightText);
		panel_10.setBounds(435, 477, 300, 25);
		contentPanel.add(panel_10);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_2 = new JLabel("Tiền dịch vụ");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2.setBounds(0, 2, 110, 25);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTienDichVu = new JLabel("4.000.000 VND");
		lblTienDichVu.setBackground(SystemColor.textHighlightText);
		lblTienDichVu.setBounds(130, 2, 159, 25);
		lblTienDichVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_10.setLayout(null);
		panel_10.add(lblNewLabel_1_1_1_1_1_1_1_1_1_1_2);
		panel_10.add(lblTienDichVu);

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(SystemColor.textHighlightText);
		panel_14.setBounds(435, 582, 300, 25);
		contentPanel.add(panel_14);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_1 = new JLabel("Thuế VAT");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1.setBounds(1, 0, 100, 25);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblThue = new JLabel("15%");
		lblThue.setBackground(SystemColor.textHighlightText);
		lblThue.setBounds(133, 0, 157, 25);
		lblThue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_14.setLayout(null);
		panel_14.add(lblNewLabel_1_1_1_1_1_1_1_1_1_1_1);
		panel_14.add(lblThue);

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(SystemColor.textHighlightText);
		panel_12.setBounds(435, 512, 300, 25);
		contentPanel.add(panel_12);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1 = new JLabel("Tiền phòng");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1.setBounds(0, 2, 110, 25);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTienPhong = new JLabel("5.000.000 VND");
		lblTienPhong.setBackground(SystemColor.textHighlightText);
		lblTienPhong.setBounds(130, 2, 159, 25);
		lblTienPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_12.setLayout(null);
		panel_12.add(lblNewLabel_1_1_1_1_1_1_1_1_1_1_2_1);
		panel_12.add(lblTienPhong);

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(SystemColor.textHighlightText);
		panel_15.setBounds(436, 617, 300, 25);
		contentPanel.add(panel_15);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1 = new JLabel("Tiền thanh toán:");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1.setBounds(0, 0, 119, 25);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTienThanhToan = new JLabel("8.550.000 VND");
		lblTienThanhToan.setBackground(SystemColor.textHighlightText);
		lblTienThanhToan.setBounds(129, 0, 160, 25);
		lblTienThanhToan.setForeground(Color.RED);
		lblTienThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.setLayout(null);
		panel_15.add(lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_1);
		panel_15.add(lblTienThanhToan);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(SystemColor.textHighlightText);
		panel_9.setBounds(435, 547, 300, 25);
		contentPanel.add(panel_9);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("Tổng cộng");
		lblNewLabel_1_1_1_1_1_1_1_1_1.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1_1_1_1_1_1_1_1_1.setBounds(2, 0, 110, 25);
		lblNewLabel_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTongCong = new JLabel("9.000.000 VND");
		lblTongCong.setBackground(SystemColor.textHighlightText);
		lblTongCong.setBounds(130, 0, 159, 25);
		lblTongCong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_9.setLayout(null);
		panel_9.add(lblNewLabel_1_1_1_1_1_1_1_1_1);
		panel_9.add(lblTongCong);

		btnKiemTraMaGiamGia = new JButton("Kiểm tra");
		btnKiemTraMaGiamGia.setBounds(292, 478, 75, 25);
		contentPanel.add(btnKiemTraMaGiamGia);

		JPanel panel_6_2 = new JPanel();
		panel_6_2.setBackground(SystemColor.textHighlightText);
		panel_6_2.setBounds(10, 547, 280, 26);
		contentPanel.add(panel_6_2);

		JLabel lblTinNhn = new JLabel("Tiền nhận");
		lblTinNhn.setBackground(SystemColor.textHighlightText);
		lblTinNhn.setBounds(0, 0, 80, 25);
		lblTinNhn.setFont(new Font("Tahoma", Font.PLAIN, 16));

		tfTienNhan = new JTextField();
		tfTienNhan.setBackground(SystemColor.control);
		tfTienNhan.setBounds(120, 0, 150, 25);
		tfTienNhan.setColumns(10);
		panel_6_2.setLayout(null);
		panel_6_2.add(lblTinNhn);
		panel_6_2.add(tfTienNhan);

		lblIconTienNhan = new JLabel("");
		lblIconTienNhan.setBounds(95, 0, 16, 25);
		panel_6_2.add(lblIconTienNhan);
		lblIconTienNhan.setIcon(new ImageIcon(DialogTinhTien.class.getResource("/icon/check.png")));
		lblIconTienNhan.setBackground(SystemColor.textHighlightText);

		btnThanhToan = new JButton("Xác nhận thanh toán");

		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThanhToan.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnThanhToan.setBackground(Color.RED);
		btnThanhToan.setBounds(555, 652, 170, 40);
		contentPanel.add(btnThanhToan);

		JPanel panel_14_1 = new JPanel();
		panel_14_1.setBackground(SystemColor.textHighlightText);
		panel_14_1.setBounds(10, 582, 280, 25);
		contentPanel.add(panel_14_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_2 = new JLabel("Tiền thừa");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_2.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_2.setBounds(2, 0, 100, 25);
		lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTienThua = new JLabel("");
		lblTienThua.setBackground(SystemColor.textHighlightText);
		lblTienThua.setBounds(120, 0, 146, 25);
		lblTienThua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_14_1.setLayout(null);
		panel_14_1.add(lblNewLabel_1_1_1_1_1_1_1_1_1_1_1_2);
		panel_14_1.add(lblTienThua);

		checkboxInHoaDon = new JCheckBox("Xuất hóa đơn");
		checkboxInHoaDon.setBackground(SystemColor.textHighlightText);
		checkboxInHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkboxInHoaDon.setBounds(435, 660, 121, 25);
		contentPanel.add(checkboxInHoaDon);

		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuayLai.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnQuayLai.setBackground(new Color(30, 144, 255));
		btnQuayLai.setBounds(10, 652, 100, 40);
		contentPanel.add(btnQuayLai);

		JPanel panel_10_2_1 = new JPanel();
		panel_10_2_1.setLayout(null);
		panel_10_2_1.setBackground(Color.WHITE);
		panel_10_2_1.setBounds(11, 131, 367, 26);
		contentPanel.add(panel_10_2_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_2 = new JLabel("Tên nhân viên");
		lblNewLabel_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1_1_1_2.setBounds(0, 0, 110, 25);
		panel_10_2_1.add(lblNewLabel_1_1_1_1_1_1_1_1_2);

		lblTenNhanVien = new JLabel("");
		lblTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenNhanVien.setBounds(120, 0, 167, 25);
		panel_10_2_1.add(lblTenNhanVien);

		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBackground(Color.WHITE);
		panel_4_2.setBounds(436, 131, 300, 25);
		contentPanel.add(panel_4_2);

		lblTongThoiLuong = new JLabel("Tổng thời lượng");
		lblTongThoiLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongThoiLuong.setBounds(0, 0, 122, 25);
		panel_4_2.add(lblTongThoiLuong);

		lblThoiLuong = new JLabel("");
		lblThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThoiLuong.setBounds(132, 0, 142, 25);
		panel_4_2.add(lblThoiLuong);

		hienThiChiTiet();

		btnThanhToan.addActionListener(this);
		btnQuayLai.addActionListener(this);
		btnKiemTraMaGiamGia.addActionListener(this);
		tfTienNhan.addKeyListener(this);
		khuyenMaiDao = new KhuyenMaiDao(MainFrame.sessionFactory);
		hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
		new KhachHangDao(MainFrame.sessionFactory);
		phongDao = new PhongDao(MainFrame.sessionFactory);
		new PhongDao(MainFrame.sessionFactory);

		// khong cho sua table
		tableChiTiet.setDefaultEditor(Object.class, null);
		tfMaGiamGia.addKeyListener(this);

	}

	public void hienThiChiTiet() {
		for (int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(0);
		}
		int i = 1;
		this.tongTienDichVu = 0;
		this.tongTienPhong = 0;
		int thoiLuong = 0;
		// *** need call function the first
		hoaDon.setGioKetThuc(new Date());
		thoiLuong = hoaDon.capNhatThoiLuong(phong.getMaPhong());
		tienThanhToan = hoaDon.thanhTien();
		lblTienThanhToan.setText(FormatCustom.chuyenDoiTienTe(tienThanhToan));

		for (ChiTietHoaDon chiTietHoaDon : hoaDon.getChiTietHoaDon()) {
			tableModel.addRow(new String[] { Integer.toString(i++),
					"Phòng số: " + chiTietHoaDon.getPhong().getMaPhong().substring(1),
					FormatCustom.dinhDangGio(chiTietHoaDon.getThoiLuong()),
					FormatCustom.chuyenDoiTienTe(chiTietHoaDon.getPhong().getLoaiPhong().getGiaTien()), " ",
					FormatCustom.chuyenDoiTienTe(chiTietHoaDon.thanhTien()) });
			tongTienPhong += chiTietHoaDon.thanhTien();
		}

		if (thoiLuong < 60) {
			double sum = hoaDon.getChiTietHoaDon().get(0).getPhong().getLoaiPhong().getGiaTien()
					* ((60 - thoiLuong) / 60.0);
			tableModel.setValueAt(FormatCustom.chuyenDoiTienTe(sum + hoaDon.getChiTietHoaDon().get(0).thanhTien()), 0,
					5);
			tongTienPhong = tongTienPhong + sum;
		}
		if (hoaDon.getChiTietDichVu() != null) {
			for (ChiTietDichVu chiTietDichVu : hoaDon.getChiTietDichVu()) {
				tableModel.addRow(new String[] { Integer.toString(i++), chiTietDichVu.getDichVu().getTenDichVu(),
						Integer.toString(chiTietDichVu.getSoLuong()),
						FormatCustom.chuyenDoiTienTe(chiTietDichVu.getDichVu().getDonGia()),
						chiTietDichVu.getDichVu().getDonViTinh(),
						FormatCustom.chuyenDoiTienTe(chiTietDichVu.thanhTien()) });
				tongTienDichVu += chiTietDichVu.thanhTien();
			}
		}

		lblTienPhong.setText(FormatCustom.chuyenDoiTienTe(tongTienPhong));
		lblTienDichVu.setText(FormatCustom.chuyenDoiTienTe(tongTienDichVu));
		lblTongCong.setText(FormatCustom.chuyenDoiTienTe(tongTienDichVu + tongTienPhong));
		lblThoiLuong.setText(thoiLuong + " Phút");
		lblTenNhanVien.setText(hoaDon.getNhanVienLap().getHoTen());
		lblThue.setText(hoaDon.getThue() + "%");
		lblGioNhanPhong.setText(FormatCustom.dinhDanhThoiGian(hoaDon.getGioNhanPhong()));

		lblGioTraPhong.setText(FormatCustom.dinhDanhThoiGian(hoaDon.getGioKetThuc()));

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

	public boolean kiemTraTienNhan() {
		String txtTienNhan = tfTienNhan.getText().toString();
		if (!txtTienNhan.matches("[0-9]+")) {
			lblIconTienNhan.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove1.png")));
//			JOptionPane.showMessageDialog(this, "Tiền nhận phải là số và lớn hơn 0");
//			JOptionPane.showMessageDialog(this, "Tiền nhận phải là số và không âm");
			lblTienThua.setText("");
			return false;
		}
		double tienThua = Long.parseLong(txtTienNhan) - tienThanhToan;
		if (tienThua < 0 && tienThua != 0) {
			lblIconTienNhan.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove1.png")));
			lblTienThua.setText("");
			return false;
		}
		lblIconTienNhan.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/true.png")));
		lblTienThua.setText(FormatCustom.chuyenDoiTienTe(tienThua));
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean kiemTraMaGiamGia() {
		String maGiamGia = tfMaGiamGia.getText().toString().trim();
		if (maGiamGia.equals("")) {
			hoaDon.setKhuyenMai(MainFrame.khuyenMaiMacDinh);
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
			tfMaGiamGia.selectAll();
			tfMaGiamGia.setFocusable(true);
			return false;
		}
		if (khuyenMai.getDaSuDung() >= khuyenMai.getTongSoLuong()) {
			JOptionPane.showMessageDialog(this, "Mã giảm giá này đã được sử dụng hết");
			tfMaGiamGia.selectAll();
			tfMaGiamGia.setFocusable(true);
			return false;
		}
		if (!khuyenMai.isTrangThai()) {
			JOptionPane.showMessageDialog(this, "Mã giảm giá này đã hết hiệu lực");
			tfMaGiamGia.selectAll();
			tfMaGiamGia.setFocusable(true);
			return false;
		}
		if (!khuyenMai.getNgayBatDau().before(new Date()) || !khuyenMai.getNgayHetHan().after(new Date())) {
			JOptionPane.showMessageDialog(this, String.format("Mã giảm giá chỉ áp dụng từ ngày %s Đến %s",
					khuyenMai.getNgayBatDau().toLocaleString(), khuyenMai.getNgayHetHan().toLocaleString()));
			tfMaGiamGia.selectAll();
			tfMaGiamGia.setFocusable(true);
			return false;
		}
		lblIconMaGiamGia.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/true.png")));
		hoaDon.setKhuyenMai(khuyenMai);
		lblChietKhau.setText(khuyenMai.getChietKhau() + "%");
		tienThanhToan = hoaDon.thanhTien();
		lblTienThanhToan.setText(FormatCustom.chuyenDoiTienTe(tienThanhToan));
		tfTienNhan.setFocusable(true);
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnQuayLai)) {
			setVisible(false);
			dispose();
		}

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
		if (object.equals(btnThanhToan)) {

			if (!kiemTraMaGiamGia()) {
				return;
			}
			if (!kiemTraTienNhan()) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập tiền nhận hoặc số tiền nhận không hợp lệ");
				return;
			}
			int xacnhan = JOptionPane.showConfirmDialog(this,
					"Xác nhận tính tiền phòng: " + phong.getMaPhong().substring(1), "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacnhan != JOptionPane.YES_OPTION) {
				return;
			}
			// suptraction quantity "voucher discount(Mã Giảm giá)"
			if (!hoaDon.getKhuyenMai().equals(MainFrame.khuyenMaiMacDinh)) {
				// it differen default voucher discount
				if (!khuyenMaiDao.apDungMaGiamGia(hoaDon.getKhuyenMai().getMaGiamGia())) {
					JOptionPane.showMessageDialog(this, "Không thể áp dụng mã giảm giá này");
					return;
				}
			}

			hoaDon.setTienKhachTra(Long.parseLong(tfTienNhan.getText().trim()));
			hoaDon.setTongTien(tienThanhToan);
			hoaDon.setTrangThai(true);
			hoaDonDao.capNhatHoaDon(hoaDon);

			if (phong.getTrangThaiPhong().getMaTTP().equalsIgnoreCase(MainFrame.maPhongTam)) {
				phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongCho, "Phòng chờ"));
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongCho);
			} else {
				phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongTrong, "Phòng trống"));
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongTrong);
			}
			JOptionPane.showMessageDialog(this, "Tính tiền thành công");

			if (checkboxInHoaDon.isSelected()) {
				DialogChiTietHoaDon dialogChiTietHoaDon = new DialogChiTietHoaDon();
				dialogChiTietHoaDon.khoiTao(hoaDon);
				dialogChiTietHoaDon.btnXuatPDF.doClick();
			}
			dispose();
			setVisible(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		Object object = e.getSource();
		if (object.equals(tfTienNhan)) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnThanhToan.doClick();
			}
		}
		if (object.equals(tfMaGiamGia)) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnKiemTraMaGiamGia.doClick();
			}
		}

	}

	public Phong getPhong() {
		return this.phong;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object object = e.getSource();
		if (object.equals(tfTienNhan)) {
			kiemTraTienNhan();
		}
	}
}