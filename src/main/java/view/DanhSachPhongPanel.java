package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dao.HoaDonDao;
import dao.PhieuDatPhongDao;
import dao.PhongDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import view.util.FormatCustom;

public class DanhSachPhongPanel extends JPanel implements ActionListener, ItemListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtPhong;
	private HashMap<JPanel, Phong> listHashMap;
	private JPanel panelDanhSachPhong;
	private PhongDao phongDao;
	private JComboBox<String> cbSoNguoi;
	private JButton btnTim;
	private JButton btnLamMoi;
	private JLabel lbPhongTrong;
	private JLabel lbPhongCho;
	private JLabel lbphongBan;
	private JLabel lbTam;
	private JLabel lbPhongVuaVao;
	public static JButton btnDangCho;
	public static JButton btnDangHat;
	private JButton btnDatPhongNgay;
	private JButton btnNhanPhong;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnPhngThng;
	private JRadioButton rdbtnPhngVip;
	private JButton btnXemChiTiet;
	private JButton btnChuyenPhong;
	private JButton btnDichVu;
	private JButton btnTinhTien;
	private JPanel panel;
	private JLabel _timeField;
	private JPanel panel_3;
	private JLabel lblNewLabel_3;
	private JPanel objPhong;
	private JButton btnDatPhongCho;
	@SuppressWarnings("unused")
	private HoaDon hoaDon;
	private Phong phong;
	private HoaDonDao hoaDonDao;
	private PhieuDatPhongDao phieuDatPhongDao;
	private DialogDatPhong dialogDatPhong;
	private JPanel objClicked;
	// Dang hat = false
	private int intLISTPHONG = 1;
	private JButton btnHuyPhongCho;
	private DialogChuyenPhong dialogChuyenPhong;
	private Date dateNow;

	/**
	 * Create the panel.
	 * 
	 */
	public DanhSachPhongPanel() {
		hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
		phongDao = new PhongDao(MainFrame.sessionFactory);
		phieuDatPhongDao = new PhieuDatPhongDao(MainFrame.sessionFactory);
		if (phongDao == null) {
			System.err.println("DanhSachPhongPanel : PhongDao erorr");
			return;
		}

		JPanel contentPane = new JPanel();
		contentPane.setBounds(0, 0, 1555, 762);
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

		panelDanhSachPhong = new JPanel();
		panelDanhSachPhong.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(panelDanhSachPhong);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//		scrollPane.setPreferredSize(new Dimension(1260, 470));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(panelDanhSachPhong);
		panelDanhSachPhong.setLayout(new GridLayout(0, 6));

		JLabel lblNewLabel_4 = new JLabel("Danh Sách Phòng");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_4.setBackground(new Color(72, 209, 204));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel_2.setBackground(Color.WHITE);

		lbPhongTrong = new JLabel("Phòng trống (9)");
		lbPhongTrong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPhongCho = new JLabel("Phòng chờ (5)");
		lbPhongCho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbphongBan = new JLabel("Phòng bận (10)");
		lbphongBan.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lbTam = new JLabel("Phòng tạm (1)");
		lbTam.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lablePhongVipIcon = new JLabel("");
		lablePhongVipIcon.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource("/icon/mh1.jpg")));

		JLabel lablePhongVipIcon_1 = new JLabel("");
		lablePhongVipIcon_1.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource("/icon/iconTrong.png")));

		JLabel lablePhongVipIcon_1_1 = new JLabel("");
		lablePhongVipIcon_1_1.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource("/icon/iconCHo.png")));

		JLabel lablePhongVipIcon_1_2 = new JLabel("");
		lablePhongVipIcon_1_2.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource("/icon/iconban.png")));

		JLabel lablePhongVipIcon_2 = new JLabel("");
		lablePhongVipIcon_2.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource("/icon/vip_32.png")));

		JLabel lblNewLabel_3_1_2_1 = new JLabel("Phòng VIP");
		lblNewLabel_3_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setLayout(null);

		lbPhongVuaVao = new JLabel("");
		lbPhongVuaVao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPhongVuaVao.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_2 = new JLabel("|");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		add(contentPane);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addComponent(lablePhongVipIcon_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lbPhongTrong, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lablePhongVipIcon_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lbPhongCho, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(lablePhongVipIcon_1_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(lbphongBan, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
				.addGap(19).addComponent(lablePhongVipIcon, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(lbTam, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lablePhongVipIcon_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(lblNewLabel_3_1_2_1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
				.addComponent(lbPhongVuaVao, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE).addGap(18)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3_1_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43,
								Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE)
						.addComponent(lbTam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(lbPhongVuaVao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(lbPhongTrong, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(lablePhongVipIcon_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43,
								Short.MAX_VALUE)
						.addComponent(lbPhongCho, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(lablePhongVipIcon_1_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43,
								Short.MAX_VALUE)
						.addComponent(lbphongBan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(lablePhongVipIcon, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43,
								Short.MAX_VALUE)
						.addComponent(lablePhongVipIcon_2, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE).addComponent(
								lablePhongVipIcon_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
						.addGap(2)));
		panel_2.setLayout(gl_panel_2);
		ButtonGroup G = new ButtonGroup();

		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel.setBackground(Color.WHITE);

		btnDatPhongNgay = new JButton("Đặt phòng ngay     (F4)");
		btnDatPhongNgay.setHorizontalAlignment(SwingConstants.LEFT);
		btnDatPhongNgay.setForeground(Color.BLACK);
		btnDatPhongNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDatPhongNgay.setBackground(new Color(60, 179, 113));

		clickOnKey(btnDatPhongNgay, "datngay", KeyEvent.VK_F4);

		btnXemChiTiet = new JButton("Xem chi tiết           (F8)");
		btnXemChiTiet.setHorizontalAlignment(SwingConstants.LEFT);
		btnXemChiTiet.setForeground(Color.BLACK);
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXemChiTiet.setBackground(new Color(60, 179, 113));
		clickOnKey(btnXemChiTiet, "xemchitiet", KeyEvent.VK_F8);

		btnNhanPhong = new JButton("Nhận phòng chờ    (F6)");
		btnNhanPhong.setHorizontalAlignment(SwingConstants.LEFT);
		btnNhanPhong.setForeground(Color.BLACK);
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNhanPhong.setBackground(new Color(60, 179, 113));
		clickOnKey(btnNhanPhong, "datphong", KeyEvent.VK_F6);

		btnChuyenPhong = new JButton("Chuyển phòng       (F9)");
		btnChuyenPhong.setHorizontalAlignment(SwingConstants.LEFT);
		btnChuyenPhong.setForeground(Color.BLACK);
		btnChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChuyenPhong.setBackground(new Color(60, 179, 113));
		clickOnKey(btnChuyenPhong, "chuyenphong", KeyEvent.VK_F9);

		btnDichVu = new JButton("Dịch vụ                (F10)");
		btnDichVu.setHorizontalAlignment(SwingConstants.LEFT);
		btnDichVu.setForeground(Color.BLACK);
		btnDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDichVu.setBackground(new Color(60, 179, 113));
		clickOnKey(btnDichVu, "dichvu", KeyEvent.VK_F10);

		btnTinhTien = new JButton("Tính tiền              (F11)");
		btnTinhTien.setHorizontalAlignment(SwingConstants.LEFT);
		btnTinhTien.setForeground(Color.BLACK);
		btnTinhTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTinhTien.setBackground(Color.ORANGE);
		clickOnKey(btnTinhTien, "play", KeyEvent.VK_F11);

		btnDatPhongCho = new JButton("Đặt phòng chờ       (F5)");
		btnDatPhongCho.setHorizontalAlignment(SwingConstants.LEFT);
		btnDatPhongCho.setForeground(Color.BLACK);
		btnDatPhongCho.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDatPhongCho.setBackground(new Color(60, 179, 113));
		clickOnKey(btnDatPhongCho, "datcho", KeyEvent.VK_F5);

		btnHuyPhongCho = new JButton("Huỷ phòng chờ      (F7)");
		btnHuyPhongCho.setHorizontalAlignment(SwingConstants.LEFT);
		btnHuyPhongCho.setForeground(Color.BLACK);
		btnHuyPhongCho.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyPhongCho.setBackground(new Color(60, 179, 113));
		clickOnKey(btnHuyPhongCho, "huycho", KeyEvent.VK_F7);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(9)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDatPhongNgay, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(btnDatPhongCho, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(btnNhanPhong, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(btnHuyPhongCho, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(btnXemChiTiet, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(btnChuyenPhong, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(btnDichVu, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(btnTinhTien, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
						.addGap(8)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(8)
						.addComponent(btnDatPhongNgay, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(17)
						.addComponent(btnDatPhongCho, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(17)
						.addComponent(btnNhanPhong, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(17)
						.addComponent(btnHuyPhongCho, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(17)
						.addComponent(btnXemChiTiet, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(17)
						.addComponent(btnChuyenPhong, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(17)
						.addComponent(btnDichVu, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(17)
						.addComponent(btnTinhTien, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(13)));
		panel.setLayout(gl_panel);
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panel_3.setBackground(Color.WHITE);

		_timeField = new JLabel("New label");
		_timeField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		_timeField.setHorizontalAlignment(SwingConstants.CENTER);
		_timeField.setText("" + h + ":" + m + ":" + s);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		lblNewLabel_3 = new JLabel(df.format(new Date()));
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(10)
						.addComponent(_timeField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE).addGap(10))
				.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(3).addComponent(_timeField)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)));
		panel_3.setLayout(gl_panel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		panel_1.setBackground(Color.WHITE);

		btnTim = new JButton("Tìm");
		btnTim.setForeground(new Color(0, 0, 0));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTim.setBackground(new Color(60, 179, 113));
		btnTim.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource("/icon/search.png")));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource("/icon/exchange (1).png")));
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLamMoi.setBackground(new Color(32, 178, 170));

		txtPhong = new JTextField();
		txtPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPhong.setColumns(10);

		JLabel lblSHng = new JLabel("Phòng số");
		lblSHng.setFont(new Font("Tahoma", Font.BOLD, 16));

		cbSoNguoi = new JComboBox<String>();
		cbSoNguoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbSoNguoi.setBackground(Color.WHITE);
		cbSoNguoi.addItemListener(this);
		cbSoNguoi.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Tất cả", "5 người", "10 người", "20 người" }));

		JLabel lblSNgi = new JLabel("Số người");
		lblSNgi.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblSNgi_1 = new JLabel("Loại phòng");
		lblSNgi_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnPhngThng = new JRadioButton("Phòng thường");
		rdbtnPhngThng.setBackground(Color.WHITE);
		rdbtnPhngThng.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnPhngThng.addActionListener(this);
		G.add(rdbtnPhngThng);
		rdbtnNewRadioButton = new JRadioButton("Tất cả");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnNewRadioButton.addActionListener(this);
		G.add(rdbtnNewRadioButton);

		rdbtnPhngVip = new JRadioButton("Phòng VIP");
		rdbtnPhngVip.setBackground(Color.WHITE);
		rdbtnPhngVip.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnPhngVip.addActionListener(this);
		G.add(rdbtnPhngVip);

		btnDangHat = new JButton("Phòng đang sử dụng");
		btnDangHat.setForeground(Color.BLACK);
		btnDangHat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDangHat.setBackground(new Color(220, 20, 60));

		btnDangCho = new JButton("Phòng đang chờ");
		btnDangCho.setForeground(Color.BLACK);
		btnDangCho.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDangCho.setBackground(new Color(0, 191, 255));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(22)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(btnDangCho, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addGap(83).addComponent(lblSNgi, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addGap(40).addComponent(cbSoNguoi, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addGap(58).addComponent(lblSHng, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addGap(10).addComponent(txtPhong, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addGap(54).addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(btnDangHat, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
								.addGap(83)
								.addComponent(lblSNgi_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGap(39)
								.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 99,
										GroupLayout.PREFERRED_SIZE)
								.addGap(58)
								.addComponent(rdbtnPhngThng, GroupLayout.PREFERRED_SIZE, 153,
										GroupLayout.PREFERRED_SIZE)
								.addGap(56)
								.addComponent(rdbtnPhngVip, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addGap(186).addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 137,
										GroupLayout.PREFERRED_SIZE)))));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(6)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(btnDangCho,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(4).addComponent(lblSNgi,
										GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(1).addComponent(cbSoNguoi,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(lblSHng,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(txtPhong,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(5)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(btnDangHat,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSNgi_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(rdbtnNewRadioButton,
										GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(rdbtnPhngThng,
										GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(rdbtnPhngVip,
										GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))));
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1291, Short.MAX_VALUE).addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup().addGap(9)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(10).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1291, Short.MAX_VALUE)
						.addContainerGap())
				.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 1553, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1533, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGap(9)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE).addGap(13)));
		contentPane.setLayout(gl_contentPane);
		btnDangCho.addActionListener(this);
		btnDangHat.addActionListener(this);

		txtPhong.addKeyListener(this);
		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);
		javax.swing.Timer t = new javax.swing.Timer(1000, new ClockListener());
		t.start();

		btnChuyenPhong.addActionListener(this);
		btnDatPhongCho.addActionListener(this);
		btnDatPhongNgay.addActionListener(this);
		btnDichVu.addActionListener(this);
		btnTinhTien.addActionListener(this);
		btnHuyPhongCho.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnXemChiTiet.addActionListener(this);

		dialogDatPhong = new DialogDatPhong();
		dialogChuyenPhong = new DialogChuyenPhong();

	}

	public void khoiTao() {
		loadGUIDanhSachPhong(null, 1, 0, 0);
		loadTrangThaiPhong();
	}

	public void disabledTheoDSPhong() {
		if (intLISTPHONG == 1) {
			btnTinhTien.setEnabled(false);
			btnChuyenPhong.setEnabled(false);
			btnDichVu.setEnabled(false);
			btnDatPhongNgay.setEnabled(true);
			btnDatPhongCho.setEnabled(true);
			btnNhanPhong.setEnabled(true);
			btnHuyPhongCho.setEnabled(true);
		} else {
			btnTinhTien.setEnabled(true);
			btnChuyenPhong.setEnabled(true);
			btnDichVu.setEnabled(true);
			btnDatPhongNgay.setEnabled(false);
			btnDatPhongCho.setEnabled(false);
			btnNhanPhong.setEnabled(false);
			btnHuyPhongCho.setEnabled(false);
		}
	}

	public static void clickOnKey(final AbstractButton button, String actionName, int key) {
		button.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0), actionName);
		button.getActionMap().put(actionName, new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				button.doClick();
			}
		});
	}

	private void loadTrangThaiPhong() {
		int pt = phongDao.laySoLuongPhongTheoTrangThai(1);
		int pc = phongDao.laySoLuongPhongTheoTrangThai(2);
		int pb = phongDao.laySoLuongPhongTheoTrangThai(4);
		int pta = phongDao.laySoLuongPhongTheoTrangThai(3);

		lbPhongTrong.setText("Phòng trống (" + pt + ")");
		lbPhongCho.setText("Phòng chờ (" + pc + ")");
		lbphongBan.setText("Phòng đang sử dụng (" + pb + ")");
		lbTam.setText("Phòng tạm (" + pta + ")");

	}

	@SuppressWarnings("deprecation")
	public void loadGUIDanhSachPhong(String maPhong, int tt, int lp, int sn) {
		panelDanhSachPhong.removeAll();
		panelDanhSachPhong.revalidate();
		panelDanhSachPhong.repaint();
		disabledTheoDSPhong();
		List<Phong> list;
		objPhong = null;
		if (maPhong != null) {
			Phong phong = phongDao.layThongTinPhongQuaMa(maPhong);
			if (phong == null) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy phòng " + maPhong.substring(1));
				txtPhong.requestFocus();
				txtPhong.selectAll();
				lamMoi(null);
				return;
			}
			list = Arrays.asList(phong);
			disabledPhongDangCho(phong);
		} else {
			list = phongDao.layDanhSachPhongTheoTrangThaiLoaiPhongSoNguoi(tt, lp, sn);
		}
		listHashMap = new HashMap<JPanel, Phong>();
		int length = list.size();
		dateNow = new Date();
		for (int i = 0; i < length; i++) {
			Phong phong = list.get(i);
			if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongCho)
					|| phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongTam)) {
				try {
					PhieuDatPhong phieuDatPhong = phieuDatPhongDao.layPhieuDatPhongMoiNhatTheoPhong(phong.getMaPhong());
					Date dateAdd2Hours = phieuDatPhong.getThoiGianNhanPhong();
					dateAdd2Hours.setHours(dateAdd2Hours.getHours() + 2);
					if (!dateAdd2Hours.after(dateNow)) {
						phieuDatPhongDao.huyPhieuDatPhong(phieuDatPhong.getMaPDP());
						if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongCho)) {
							phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongTrong);
							phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongTrong, "Phòng trống"));
						} else {
							phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongBan);
							phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongBan, "Phòng Đang sử dụng"));
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 0, 130, 150);
			panel_1.setBackground(Color.WHITE);
			panel_1.setBorder(new LineBorder(Color.WHITE, 3));
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNewLabel
					.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource(layDiaChiIconTheoLoaiVaTrangThaiPhong(
							phong.getLoaiPhong().getMaLP(), phong.getTrangThaiPhong().getMaTTP()))));
			String soPhong = "Phòng: " + phong.getMaPhong().substring(1);
			JLabel lblNewLabel_1 = new JLabel(soPhong);
			lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

			String soNguoi = phong.getSoNguoi() + " người";
			JLabel nguoi = new JLabel(soNguoi);
			nguoi.setAlignmentX(Component.CENTER_ALIGNMENT);
			nguoi.setHorizontalAlignment(SwingConstants.CENTER);
			nguoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
			panel_1.add(lblNewLabel);
			panel_1.add(nguoi);
			panel_1.add(lblNewLabel_1);
			panel_1.addMouseListener(this);
			panelDanhSachPhong.add(panel_1);
			listHashMap.put(panel_1, phong);
			if (length == 1) {
				objPhong = panel_1;
				objClicked = panel_1;
				objPhong.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
			}
		}

	}

	public void lamMoi(String thongBao) {
		disabledTheoDSPhong();
		cbSoNguoi.setSelectedIndex(0);
		txtPhong.setText("");
		loadGUIDanhSachPhong(null, 1, 0, 0);
		objPhong = null;
		rdbtnNewRadioButton.setSelected(true);
		btnDangCho.doClick();
		if (thongBao.equals("")) {
			lbPhongVuaVao.setText("");
		} else {
			lbPhongVuaVao.setText(thongBao);
		}
	}

	// update trang thai phong
	public static String layDiaChiIconTheoLoaiVaTrangThaiPhong(String maLP, String maTTP) {
		String loaiPhong = "Vip";
		if (maLP.equalsIgnoreCase("LP001")) {
			loaiPhong = "Thuong";
		}
		switch (maTTP) {
		case "TTP001":
			// Phòng bận
			return "/icon/PhongBan" + loaiPhong + ".png";
		case "TTP002":
			// Phòng chờ
			return "/icon/PhongCho" + loaiPhong + ".png";
		case "TTP003":
			// Phòng trống
			return "/icon/PhongTrong" + loaiPhong + ".png";
		default:
			// Phòng tạm
			return "/icon/PhongTam" + loaiPhong + ".png";
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnTim) {
			String sp = txtPhong.getText();
			if (!Pattern.matches("\\d+", sp)) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số phòng (VD: 1)", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String ma = String.format("P%03d", Integer.parseInt(sp));
			loadGUIDanhSachPhong(ma, 0, 0, 0);
		} else if (o == btnLamMoi) {
			lamMoi("Vừa làm mới");
		} else if (o == btnDangCho) {
			int sn = cbSoNguoi.getSelectedIndex();
			intLISTPHONG = 1;
			loadGUIDanhSachPhong(null, intLISTPHONG, getRadio(), sn);
		} else if (o == btnDangHat) {
			int sn = cbSoNguoi.getSelectedIndex();
			intLISTPHONG = 2;
			loadGUIDanhSachPhong(null, intLISTPHONG, getRadio(), sn);
		} else if (o == rdbtnNewRadioButton || o == rdbtnPhngThng || o == rdbtnPhngVip) {
			loadGUIDanhSachPhong(null, intLISTPHONG, getRadio(), cbSoNguoi.getSelectedIndex());
		}

		// phan duoi
		else if (objPhong != null) {
			phong = listHashMap.get(objPhong);
			if (o == btnDatPhongCho) {
				DialogDatPhongCho phieuDatPhongCho = new DialogDatPhongCho(phong);
				phieuDatPhongCho.setVisible(true);
				loadGUIDanhSachPhong(null, 1, 0, 0);
				lamMoi("Vừa đặt phòng chờ " + phong.getMaPhong().substring(1));
			} else if (o == btnNhanPhong) {
				PhieuDatPhong phieuDatPhong = phieuDatPhongDao.layPhieuDatPhongMoiNhatTheoPhong(phong.getMaPhong());

				int xacnhan = JOptionPane.showConfirmDialog(this,
						"Xác nhận nhận phòng của: " + phieuDatPhong.getKhachHang().getHoTen(), "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (xacnhan != JOptionPane.YES_OPTION) {
					return;
				}
				HoaDon hoaDon = new HoaDon(MainFrame.nhanVien, MainFrame.khuyenMaiMacDinh,
						phieuDatPhong.getKhachHang());
				hoaDon.setKhachHang(phieuDatPhong.getKhachHang());
				hoaDon.setChiTietHoaDon(Arrays.asList(new ChiTietHoaDon(phong, hoaDon, 0)));
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongBan);
				loadGUIDanhSachPhong(null, 1, 0, 0);
				phieuDatPhongDao.huyPhieuDatPhong(phieuDatPhong.getMaPDP());
				if (hoaDonDao.themHoaDon(hoaDon)) {
					System.out.println("add Bill success");
					lamMoi("Vừa nhận phòng chờ " + phong.getMaPhong().substring(1));
				} else {
					System.out.println("add Bill fail");
				}

			} else if (o == btnTinhTien) {
				HoaDon hoaDon = hoaDonDao.layHoaDonMoiNhatTheoPhong(phong.getMaPhong());
				DialogTinhTien dialogTinhTien = new DialogTinhTien(hoaDon, phong);
				dialogTinhTien.setVisible(true);
				intLISTPHONG = 2;
				loadGUIDanhSachPhong(null, 2, 0, 0);
				lamMoi("Vừa tính tiền phòng " + phong.getMaPhong().substring(1));
				btnDangHat.doClick();
			} else if (o == btnDatPhongNgay) {
				if (phong.getTrangThaiPhong().getMaTTP().equalsIgnoreCase(MainFrame.maPhongBan)
						|| phong.getTrangThaiPhong().getMaTTP().equalsIgnoreCase(MainFrame.maPhongTam)) {
					JOptionPane.showConfirmDialog(null, "Vui lòng chọn phòng trống hoặc phòng chờ", "Thông báo",
							JOptionPane.DEFAULT_OPTION);
					return;
				}
				dialogDatPhong.khoiTao(phong);
				HoaDon hoaDon = dialogDatPhong.getHoaDon();
				if (hoaDon != null) {
					loadGUIDanhSachPhong(null, 1, 0, 0);
					// neu xong thi hoi co can them dich vu ko
					lamMoi("Vừa đặt phòng " + phong.getMaPhong().substring(1));
					int n = JOptionPane.showConfirmDialog(this,
							"Đặt phòng thành công - Bạn có muốn thêm dịch vụ không?", "Thông báo",
							JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						DialogCapNhatDichVuTrongHoaDon capNhatDichVuTrongHoaDon = new DialogCapNhatDichVuTrongHoaDon(
								hoaDon, phong);
						capNhatDichVuTrongHoaDon.setVisible(true);
						hoaDon.setChiTietDichVu(hoaDonDao.layChiTietDichVu(hoaDon.getMaHD()));
						lamMoi("Vừa cập nhật dịch vụ phòng " + phong.getMaPhong().substring(1));
					}

				}

			} else if (o == btnHuyPhongCho) {
				PhieuDatPhong phieuDatPhong = phieuDatPhongDao.layPhieuDatPhongMoiNhatTheoPhong(phong.getMaPhong());
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
					phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongBan);
				} else {
					phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongTrong, "Phòng trống"));
					phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongTrong);
				}
				intLISTPHONG = 1;
				loadGUIDanhSachPhong(null, intLISTPHONG, 0, 0);
				lamMoi("Vừa huỷ phòng chờ " + phong.getMaPhong().substring(1));
			} else if (o == btnDichVu) {
				HoaDon hoaDon = hoaDonDao.layHoaDonMoiNhatTheoPhong(phong.getMaPhong());
				DialogCapNhatDichVuTrongHoaDon capNhatDichVuTrongHoaDon = new DialogCapNhatDichVuTrongHoaDon(hoaDon,
						phong);
				capNhatDichVuTrongHoaDon.setVisible(true);
				intLISTPHONG = 2;
				loadGUIDanhSachPhong(null, intLISTPHONG, 0, 0);
				lamMoi("Vừa cập nhật dịch vụ phòng " + phong.getMaPhong().substring(1));
				btnDangHat.doClick();
				return;
			} else if (o == btnXemChiTiet) {
				MainFrame.chiTietPhongPanel.setVisible(true);
				MainFrame.chiTietPhongPanel.khoiTao(phong.getMaPhong());
				MainFrame.danhSachPhongPanel.setVisible(false);
			} else if (o == btnChuyenPhong) {
				String pcu = phong.getMaPhong().substring(1);
				dialogChuyenPhong.khoiTao(phong);
				dialogChuyenPhong.setVisible(true);
				if (dialogChuyenPhong.getTinhTrang()) {
					intLISTPHONG = 2;
					loadGUIDanhSachPhong(null, intLISTPHONG, 0, 0);
					lamMoi("Vừa chuyển phòng " + pcu + " sang phòng " + dialogChuyenPhong.getPhongChuyen());
				}
				btnDangHat.doClick();
			}
		} else {
			JOptionPane.showConfirmDialog(null, "Vui lòng chọn phòng", "Thông báo", JOptionPane.DEFAULT_OPTION);
			return;
		}
	}

	private int getRadio() {
		if (rdbtnPhngThng.isSelected())
			return 1;
		if (rdbtnPhngVip.isSelected())
			return 2;
		return 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		if (o == txtPhong && e.getKeyCode() == KeyEvent.VK_ENTER) {
			String sp = txtPhong.getText();
			if (!Pattern.matches("\\d+", sp)) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số phòng (VD: 1)", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String ma = String.format("P%03d", Integer.parseInt(sp));
			loadGUIDanhSachPhong(ma, 0, 0, 0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel jp = (JPanel) e.getSource();
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (objPhong != null) {
				objPhong.setBorder(new LineBorder(Color.WHITE, 0));
			}
			Object o = e.getSource();
			if (listHashMap.get(o) != null) {
				objPhong = jp;
				Phong phong = listHashMap.get(o);
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (objClicked != null) {
						removePanel(objClicked);
					}
					objClicked = jp;
					disabledPhongDangCho(phong);
					setPanel(objClicked);
				}
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					MainFrame.danhSachPhongPanel.setVisible(false);
					MainFrame.chiTietPhongPanel.setVisible(true);
					MainFrame.chiTietPhongPanel.khoiTao(phong.getMaPhong());
				}

			}
		}

	}

	private void disabledPhongDangCho(Phong phong) {
		disabledTheoDSPhong();
		if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongTrong)) {
			btnNhanPhong.setEnabled(false);
			btnHuyPhongCho.setEnabled(false);
			btnDatPhongNgay.setEnabled(true);
			btnDatPhongCho.setEnabled(true);
		} else if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongCho)) {
			btnNhanPhong.setEnabled(true);
			btnHuyPhongCho.setEnabled(true);
			btnDatPhongCho.setEnabled(false);
			btnDatPhongNgay.setEnabled(true);
		} else if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongTam)) {
			btnHuyPhongCho.setEnabled(true);
		} else {
			btnTinhTien.setEnabled(true);
			btnChuyenPhong.setEnabled(true);
			btnDichVu.setEnabled(true);
			btnDatPhongNgay.setEnabled(false);
			btnDatPhongCho.setEnabled(false);
			btnNhanPhong.setEnabled(false);
			btnHuyPhongCho.setEnabled(false);
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
		JPanel parent = (JPanel) e.getSource();
		if (parent != objClicked) {
			setPanel(parent);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JPanel panel_1 = (JPanel) e.getSource();
		if (panel_1 != objClicked && panel_1 != null) {
			removePanel(panel_1);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
//		if(o == cbPhong) {
//			if(cbPhong.getSelectedIndex() == 2) {
//				cbSoNguoi.disable();
//			}
//		}
		if (o == cbSoNguoi) {
			int sn = cbSoNguoi.getSelectedIndex();
			loadGUIDanhSachPhong(null, intLISTPHONG, getRadio(), sn);
		}
	}

	class ClockListener implements ActionListener {
		protected String pad(int value) {
			StringBuilder sb = new StringBuilder(String.valueOf(value));
			while (sb.length() < 2) {
				sb.insert(0, "0");
			}
			return sb.toString();
		}

		public void actionPerformed(ActionEvent e) {
			Calendar now = Calendar.getInstance();
			int h = now.get(Calendar.HOUR_OF_DAY);
			int m = now.get(Calendar.MINUTE);
			int s = now.get(Calendar.SECOND);
			String hh = pad(h);
			String mm = pad(m);
			String ss = pad(s);

			_timeField.setText("" + hh + ":" + mm + ":" + ss);
		}
	}

	public void removePanel(JPanel panel_1) {
		panel_1.removeAll();
		panel_1.revalidate();
		panel_1.repaint();
		if (panel_1 != objClicked) {
			panel_1.setBorder(new LineBorder(Color.WHITE, 0));
			panel_1.revalidate();
		}
		Phong phong = listHashMap.get(panel_1);
		if (phong == null)
			return;
		panel_1.setBounds(0, 0, 130, 150);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(Color.WHITE, 3));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setIcon(new ImageIcon(DanhSachPhongPanel.class.getResource(layDiaChiIconTheoLoaiVaTrangThaiPhong(
				phong.getLoaiPhong().getMaLP(), phong.getTrangThaiPhong().getMaTTP()))));
		String soPhong = "Phòng: " + phong.getMaPhong().substring(1);
		JLabel lblNewLabel_1 = new JLabel(soPhong);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		String soNguoi = phong.getSoNguoi() + " người";
		JLabel nguoi = new JLabel(soNguoi);
		nguoi.setAlignmentX(Component.CENTER_ALIGNMENT);
		nguoi.setHorizontalAlignment(SwingConstants.CENTER);
		nguoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel_1.add(lblNewLabel);
		panel_1.add(nguoi);
		panel_1.add(lblNewLabel_1);
		panel_1.addMouseListener(this);
	}

	public void setPanel(JPanel parent) {
		parent.revalidate();
		Phong phong = listHashMap.get(parent);
		parent.removeAll();
		parent.revalidate();
		parent.repaint();
		parent.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));

		// JPanel parent = new JPanel();
		parent.setBounds(0, 0, 130, 150);
		parent.setBackground(Color.WHITE);
		// parent.setBorder(new LineBorder(Color.WHITE, 3));
		String soPhong = phong.getMaPhong().substring(1);
		JLabel lblNewLabel_1 = new JLabel(phong.getLoaiPhong().getTenLoaiPhong().substring(6));
		lblNewLabel_1.setBounds(126, 37, 74, 20);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		String soNguoi = phong.getSoNguoi() + " người";
		parent.setLayout(null);
		parent.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel(soPhong.substring(0));
		lblNewLabel.setBounds(97, 10, 100, 20);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setAlignmentX(0.5f);
		parent.add(lblNewLabel);

		JLabel nguoi = new JLabel(soNguoi);
		nguoi.setHorizontalAlignment(SwingConstants.CENTER);
		nguoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nguoi.setAlignmentX(0.5f);
		nguoi.setBounds(136, 67, 64, 20);
		parent.add(nguoi);

		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setHorizontalAlignment(SwingConstants.LEFT);
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSLng.setAlignmentX(0.5f);
		lblSLng.setBounds(16, 67, 72, 20);
		parent.add(lblSLng);

		JLabel lblPhng = new JLabel("Loại:");
		lblPhng.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhng.setAlignmentX(0.5f);
		lblPhng.setBounds(16, 37, 72, 20);
		parent.add(lblPhng);

		JLabel lblPhng_1 = new JLabel("Phòng:");
		lblPhng_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhng_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPhng_1.setAlignmentX(0.5f);
		lblPhng_1.setBounds(16, 10, 72, 20);
		parent.add(lblPhng_1);

		JLabel lblTt = new JLabel("Trạng thái:");
		lblTt.setHorizontalAlignment(SwingConstants.LEFT);
		lblTt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTt.setAlignmentX(0.5f);
		lblTt.setBounds(16, 97, 79, 20);
		parent.add(lblTt);
		JLabel lblNewLabel_5 = new JLabel(phong.getTrangThaiPhong().getTenTrangThaiPhong().substring(6));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setAlignmentX(0.5f);
		lblNewLabel_5.setBounds(105, 97, 100, 20);
		parent.add(lblNewLabel_5);

		JLabel lblGiPhng = new JLabel("Giá phòng:");
		lblGiPhng.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiPhng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGiPhng.setAlignmentX(0.5f);
		lblGiPhng.setBounds(15, 127, 80, 20);
		parent.add(lblGiPhng);

		JLabel lblvnd = new JLabel(FormatCustom.chuyenDoiTienTe(phong.getLoaiPhong().getGiaTien()));
		lblvnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblvnd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblvnd.setAlignmentX(0.5f);
		lblvnd.setBounds(96, 127, 104, 20);
		lblvnd.setForeground(Color.RED);
		parent.add(lblvnd);
//		if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongBan)
//				|| phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongTam)) {
//			HoaDon hoaDon = hoaDonDao.layHoaDonMoiNhatTheoPhong(phong.getMaPhong());
//			hoaDon.capNhatThoiLuong(phong.getMaPhong());
//			lblGiPhng.setText("Tổng tiền: ");			
//			lblvnd.setText(FormatCustom.chuyenDoiTienTeVer2(hoaDon.thanhTien()));
//		}
	}

}