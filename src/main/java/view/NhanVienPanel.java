package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.hibernate.SessionFactory;

import dao.LoaiNhanVienDao;
import dao.MySessionFactory;
import dao.NhanVienDao;
import entity.LoaiNhanVien;
import entity.NhanVien;
import service.LoaiNhanVienService;
import service.NhanVienService;
import view.util.HeaderRenderer;

public class NhanVienPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JPanel panel;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTrangThaiLamViec;
	private JComboBox<String> cbLoaiNV;
	private DefaultTableModel tableModle;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtTimTen;
	private JTextField txtCMND;
	private JLabel lblCm;
	@SuppressWarnings("rawtypes")
	private JComboBox cbGioiTinh;
	private JTextField txtSDT;
	private JButton btnSua;
	private JButton btnTrangThaiLamViec;
	private JButton btnThem;
	private JButton btnTim;
	private JButton btnDau;
	private JButton btnTru1;
	private JButton btnCong1;
	private JButton btnCuoi;
	private JLabel txtPage;
	private int limit = 20;
	private JButton btnLamMoi;
	private JPanel panel_2;
	@SuppressWarnings("unused")
	private List<LoaiNhanVien> dsLoaiNhanVien;
	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	NhanVienService nhanVienService = new NhanVienDao(sessionFactory);
	LoaiNhanVienService loaiNhanVienService = new LoaiNhanVienDao(sessionFactory);
	private JLabel txtPage_1;
	private JLabel txtTongTrang;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NhanVienPanel() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		setAlwaysOnTop(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setSize(1263, 659);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

		JLabel lblNhpSdt = new JLabel("SDT: ");
		lblNhpSdt.setForeground(Color.BLACK);
		lblNhpSdt.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);

		btnSua = new JButton("Sửa");
		btnSua.setMargin(new Insets(2, 9, 2, 9));
		btnSua.setBackground(new Color(128, 128, 128));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setToolTipText("");
		btnSua.setIconTextGap(30);
		btnSua.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/user.png")));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnTrangThaiLamViec = new JButton("Nghỉ việc");

		btnTrangThaiLamViec.setMargin(new Insets(2, 0, 2, 0));
		btnTrangThaiLamViec.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnTrangThaiLamViec.setForeground(new Color(255, 255, 255));
		btnTrangThaiLamViec.setBackground(new Color(255, 99, 71));
		btnTrangThaiLamViec.setIconTextGap(10);
		btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/unemployment.png")));
		btnTrangThaiLamViec.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnThem = new JButton("Thêm");
		btnThem.setMargin(new Insets(2, 9, 2, 9));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setIconTextGap(20);
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		

		btnTim = new JButton("Tìm");
		btnTim.setIconTextGap(30);
		btnTim.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/search.png")));
		btnTim.setBackground(new Color(60, 179, 113));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		

		cbTrangThaiLamViec = new JComboBox();
		cbTrangThaiLamViec.setBackground(new Color(255, 248, 220));
		cbTrangThaiLamViec.setModel(new DefaultComboBoxModel(new String[] { "Trạng Thái", "Đang làm", "Nghỉ việc" }));
		cbTrangThaiLamViec.setToolTipText("");
		cbTrangThaiLamViec.setName("Loại");
		cbTrangThaiLamViec.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cbLoaiNV = new JComboBox();
		cbLoaiNV.setModel(new DefaultComboBoxModel(new String[] {"Loại NV"}));
		cbLoaiNV.setToolTipText("");
		cbLoaiNV.setBackground(new Color(255, 255, 255));
		cbLoaiNV.setFont(new Font("Tahoma", Font.PLAIN, 18));

		scrollPane = new JScrollPane();

		table = new JTable();
		table.setSelectionBackground(SystemColor.activeCaption);
		scrollPane.setColumnHeaderView(table);

		tableModle = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã NV", "Họ tên", "Số CMND", "SĐT", "Giới tính", "Ngày sinh", "Địa chỉ",
				"Trạng thái làm việc", "Loại nhân viên" });
//		table.addMouseListener(this);
		table.setRowHeight(50);
		scrollPane.setViewportView(table);

		table.setModel(tableModle);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		table.getColumnModel().getColumn(6).setPreferredWidth(300);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(130);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JLabel lblTn = new JLabel("Tên: ");
		lblTn.setForeground(Color.BLACK);
		lblTn.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtTimTen = new JTextField();
		txtTimTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimTen.setColumns(10);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCMND.setColumns(10);

		lblCm = new JLabel("CMND: ");
		lblCm.setForeground(Color.BLACK);
		lblCm.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel = new JLabel("Quản lý Nhân Viên\r\n");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBackground(new Color(72, 209, 204));

		btnLamMoi = new JButton("làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLamMoi.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/lam_moi.png")));
		btnLamMoi.setMargin(new Insets(2, 9, 2, 9));
		btnLamMoi.setIconTextGap(10);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLamMoi.setBackground(new Color(50, 205, 50));

		cbGioiTinh = new JComboBox();
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
		cbGioiTinh.setToolTipText("");
		cbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbGioiTinh.setBackground(Color.WHITE);

		panel_2 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1258, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 1255, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblCm, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(txtCMND, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNhpSdt, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtSDT, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblTn, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(txtTimTen, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(btnTrangThaiLamViec, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(cbTrangThaiLamViec, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(cbLoaiNV, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(cbGioiTinh, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))))
					.addGap(81))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTimTen, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtCMND, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNhpSdt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblCm, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTrangThaiLamViec, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbTrangThaiLamViec, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbLoaiNV, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbGioiTinh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
		);

		btnDau = new JButton("");
		btnDau.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/previousEnd.png")));
		btnDau.setFont(new Font("Arial", Font.PLAIN, 16));

		btnTru1 = new JButton("");

		btnTru1.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/rewind-button.png")));
		btnTru1.setFont(new Font("Arial", Font.PLAIN, 16));

		txtPage = new JLabel("1");
		txtPage.setAlignmentY(0.0f);
		txtPage.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnCong1 = new JButton("");

		btnCong1.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/forward-button.png")));
		btnCong1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCuoi = new JButton("");
		btnCuoi.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/nextEnd.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));
		
		txtPage_1 = new JLabel("/");
		txtPage_1.setAlignmentY(0.0f);
		txtPage_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtTongTrang = new JLabel("1");
		txtTongTrang.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtTongTrang.setAlignmentY(0.0f);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(461)
					.addComponent(btnDau, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(21)
					.addComponent(btnTru1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(25)
					.addComponent(txtPage, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPage_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtTongTrang, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnCong1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(btnCuoi, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(459))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDau)
						.addComponent(btnTru1)
						.addComponent(btnCong1)
						.addComponent(btnCuoi)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPage)
							.addComponent(txtPage_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtTongTrang, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		btnCuoi.addActionListener(this);
		panel_1.setLayout(gl_panel_1);

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
		
		List<String> list = loaiNhanVienService.layDanhSachLoaiNhanVien();
		for (String loaiNhanVien : list) {
			cbLoaiNV.addItem(loaiNhanVien);
		}

		btnCong1.addActionListener(this);
		btnTru1.addActionListener(this);
		btnDau.addActionListener(this);
		btnCuoi.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnTrangThaiLamViec.addActionListener(this);
		cbTrangThaiLamViec.addActionListener(this);
		cbGioiTinh.addActionListener(this);
		cbLoaiNV.addActionListener(this);
		txtTimTen.addKeyListener(this);
		table.addMouseListener(this);
		cbTrangThaiLamViec.setSelectedIndex(1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 1263, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(3))
		);
		setLayout(groupLayout);
		// khong cho sua table
		table.setDefaultEditor(Object.class, null);

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		txtPage.setText("1");
		themDuLieuVaoTable();

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		@SuppressWarnings("unused")
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Object object = e.getSource();
		if (object.equals(txtTimTen)) {
			String ten = txtTimTen.getText().toString().trim();
			if (ten.equalsIgnoreCase("")) {
				xoaToanBoBang();
				themDuLieuVaoTable();
			} else {
				List<NhanVien> list = null;
				xoaToanBoBang();
				list = nhanVienService.layDanhSachNhanVienTheoTen(ten);
				for (NhanVien nhanVien : list) {
					String[] s = { nhanVien.getMaNV(), nhanVien.getHoTen().toUpperCase(), nhanVien.getSoCMND(),
							nhanVien.getSoDienThoai(), nhanVien.isGioiTinh() ? "Nam" : "Nũ",
							nhanVien.getNgaySinh().toString(),
							nhanVien.getDiaChi().getTinhTP() + " " + nhanVien.getDiaChi().getQuanHuyen() + " "
									+ nhanVien.getDiaChi().getPhuongXa(),
							nhanVien.isTrangThaiLamViec() ? "Đang làm" : "Đã nghĩ",
							nhanVien.getLoaiNhanVien().getTenLoaiNhanVien() };
					tableModle.addRow(s);
				}
			}
		}
	}


	public void themDuLieuVaoTable() {
		int page = Integer.parseInt(txtPage.getText());
		String trangThaiLamViec = "";
		String gioiTinh = "";
		String loaiNhanVien = "";
		if (cbTrangThaiLamViec.getSelectedIndex() != 0) {
			trangThaiLamViec = cbTrangThaiLamViec.getSelectedItem().toString().equalsIgnoreCase("Đang làm") ? "1" : "0";
		}

		if (cbLoaiNV.getSelectedIndex() > 0) {
			String loai = cbLoaiNV.getSelectedItem().toString().trim();
			loaiNhanVien = loaiNhanVienService.layMaNhanVienTheoTenLoai(loai);
		}

		if (cbGioiTinh.getSelectedIndex() != 0) {
			gioiTinh = cbGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam") ? "1" : "0";
		}

		List<NhanVien> list = null;
		try {
			list = nhanVienService.DanhSachNhanVien(page - 1, txtTimTen.getText().trim(), gioiTinh, trangThaiLamViec,
					loaiNhanVien, limit);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (list == null) {
			JOptionPane.showMessageDialog(this, "rong");
			txtPage.setText("1");
		
			return;
		}
		xoaToanBoBang();
		for (NhanVien nhanVien : list) {
			String[] s = { nhanVien.getMaNV(), nhanVien.getHoTen().toUpperCase(), nhanVien.getSoCMND(),
					nhanVien.getSoDienThoai(), nhanVien.isGioiTinh() ? "Nam" : "Nữ", nhanVien.getNgaySinh().toString(),
					nhanVien.getDiaChi().getTinhTP() + " " + nhanVien.getDiaChi().getQuanHuyen() + " "
							+ nhanVien.getDiaChi().getPhuongXa(),
					nhanVien.isTrangThaiLamViec() ? "Đang làm" : "Đã nghĩ", nhanVien.getLoaiNhanVien().getTenLoaiNhanVien() };
			tableModle.addRow(s);
		}

	}

	public void xoaToanBoBang() {
		for (int i = tableModle.getRowCount(); i > 0; i--) {
			tableModle.removeRow(0);
		}
	}

	public void khoiTaoDuLieu() {
		xoaToanBoBang();
		themDuLieuVaoTable();
	}

	public void lamMoi() {
//		btnTrangThaiLamViec = new JButton("TTLV");
		cbTrangThaiLamViec.setSelectedIndex(0);
		cbGioiTinh.setSelectedIndex(0);
		txtTimTen.setText("");
		txtPage.setText("1");
		txtSDT.setText("");
		xoaToanBoBang();
		themDuLieuVaoTable();
		txtSDT.setEnabled(true);
		txtCMND.setEnabled(true);
		btnTrangThaiLamViec.setText("Nghĩ việc");
		btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/unemployment.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		System.err.println("Page " + txtPage.getText());
		int tongPage = 1;
		String trangThaiLamViec = "", gioiTinh = "", loaiNhanVien = "";
		if (cbTrangThaiLamViec.getSelectedIndex() != 0) {
			trangThaiLamViec = cbTrangThaiLamViec.getSelectedItem().toString().equalsIgnoreCase("Đang làm") ? "1" : "0";
		}
		try {
			tongPage = nhanVienService.tongTrang(txtTimTen.getText().trim(), gioiTinh, trangThaiLamViec, loaiNhanVien,
					limit);

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (object.equals(btnCong1)) { // next page table
			System.out.println("Cong 1 -----------------------------");
			// next table
			int page = Integer.parseInt(txtPage.getText()) + 1;
			System.err.println("Page" + page);
//			System.err.println(page);
			if (page <= tongPage) {

				txtPage.setText(Integer.toString(page));
//				int tongPage = 1;
//				tongPage = nhanVienService.tongTrang(txtTimTen.getText().trim(), gioiTinh, trangThaiLamViec, loaiNhanVien,
//						limit);
				txtTongTrang.setText(Integer.toString(tongPage));
				xoaToanBoBang();
				themDuLieuVaoTable();
			}
		} else if (object.equals(btnTru1)) { // Lui page table

			int page = Integer.parseInt(txtPage.getText()) - 1;

			if (page >= 1) {
				txtTongTrang.setText(Integer.toString(tongPage));
				txtPage.setText(Integer.toString(page));
				xoaToanBoBang();
				themDuLieuVaoTable();
			}

		} else if (object.equals(btnCuoi)) { // Cuoi page table
			int page = Integer.parseInt(txtPage.getText());
			System.err.println(tongPage);
			if (page <= tongPage) {
				txtTongTrang.setText(Integer.toString(tongPage));
				txtPage.setText(Integer.toString(tongPage));
				xoaToanBoBang();
				themDuLieuVaoTable();
			}

		} else if (object.equals(btnDau)) { // Dau page table

			int page = Integer.parseInt(txtPage.getText());

			if (page != 0) {
				txtTongTrang.setText(Integer.toString(tongPage));
				txtPage.setText("1");
				xoaToanBoBang();
				themDuLieuVaoTable();
			}

		} else if (object.equals(btnLamMoi)) { // lam moi
			lamMoi();
		} else if (object.equals(btnTim)) { // tim SDT

			String sdt = txtSDT.getText();
			String cmnd = txtCMND.getText();
			NhanVien nhanVien = null;
			if (!sdt.trim().equals("")) {
				
				try {
					nhanVien = nhanVienService.layThongTinNhanVienQuaSDT(sdt);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (nhanVien != null) {
					xoaToanBoBang();
					txtPage.setText("1");
					txtCMND.setEnabled(false);
					txtSDT.setEnabled(true);
					txtTimTen.setText("");
					String[] s = { nhanVien.getMaNV(), nhanVien.getHoTen().toUpperCase(), nhanVien.getSoCMND(),
							nhanVien.getSoDienThoai(), nhanVien.isGioiTinh() ? "Nam" : "Nữ",
							nhanVien.getNgaySinh().toString(),
							nhanVien.getDiaChi().getTinhTP() + " " + nhanVien.getDiaChi().getQuanHuyen() + " "
									+ nhanVien.getDiaChi().getPhuongXa(),
							nhanVien.isTrangThaiLamViec() ? "Đang làm" : "Đã nghĩ",
							nhanVien.getLoaiNhanVien().getTenLoaiNhanVien() };
					tableModle.addRow(s);

				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			}
			if (!cmnd.trim().equals("")) {
				try {
					nhanVien = nhanVienService.layThongTinNhanVienQuaCMND(cmnd);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (nhanVien != null) {
					xoaToanBoBang();
					txtPage.setText("1");
					txtSDT.setEnabled(false);
					txtCMND.setEnabled(true);
					txtSDT.setText("");
					String[] s = { nhanVien.getMaNV(), nhanVien.getHoTen().toUpperCase(), nhanVien.getSoCMND(),
							nhanVien.getSoDienThoai(), nhanVien.isGioiTinh() ? "Nam" : "Nữ",
							nhanVien.getNgaySinh().toString(),
							nhanVien.getDiaChi().getTinhTP() + " " + nhanVien.getDiaChi().getQuanHuyen() + " "
									+ nhanVien.getDiaChi().getPhuongXa(),
							nhanVien.isTrangThaiLamViec() ? "Đang làm" : "Đã nghĩ",
							nhanVien.getLoaiNhanVien().getTenLoaiNhanVien() };
					tableModle.addRow(s);

				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy ");
				}
			}
		} else if (object.equals(btnSua)) {
//			JOptionPane.showMessageDialog(this, "NOoooooooooo");
			int selectRow = table.getSelectedRow();
			if (selectRow == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên!");
				return;
			}
			String ma = (String) table.getValueAt(table.getSelectedRow(), 0);
			DialogSuaNhanVien dialogSuaNhanVien = new DialogSuaNhanVien(ma);
			dialogSuaNhanVien.setVisible(true);
			lamMoi();			
		} else if (object.equals(btnThem)) {// them nhan vien
			new DialogThemNhanVien().setVisible(true);
			lamMoi();
		} else if (object.equals(cbTrangThaiLamViec) || object.equals(cbLoaiNV) || object.equals(cbGioiTinh)) {

			txtPage.setText("1");
			xoaToanBoBang();
			themDuLieuVaoTable();
		}
		else if (object.equals(btnTrangThaiLamViec)) { // thay doi trang thai lam viec

			int hang = table.getSelectedRow();
			if (hang == -1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn Nhân viên");
				return;
			}
			int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thay đổi trạng thái làm việc không",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (xacnhan == JOptionPane.NO_OPTION) {
				return;
			}
			try {
				nhanVienService.suaTrangThaiLamViecQuaSoDienThoai(tableModle.getValueAt(hang, 3).toString(),
						tableModle.getValueAt(hang, 7).toString().equalsIgnoreCase("Đang Làm") ? false : true);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tableModle.setValueAt(
					tableModle.getValueAt(hang, 7).toString().equalsIgnoreCase("Đang Làm") ? "Đã nghĩ" : "Đang làm",
					hang, 7);
			String trangThaiLamViec2 = tableModle.getValueAt(table.getSelectedRow(), 6).toString();
			if (trangThaiLamViec2.equalsIgnoreCase("Đã nghĩ")) {
				btnTrangThaiLamViec.setText("Làm lại");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/lam_moi.png")));
			} else {
				btnTrangThaiLamViec.setText("Nghĩ việc");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/xoa.png")));
			}
			JOptionPane.showMessageDialog(this, "Thay đổi thành công");
		}

////		 dong ket noi

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// set trang thai lam viec
		Object object = e.getSource();
		if (object.equals(table)) {
			String trangThaiLamViec = tableModle.getValueAt(table.getSelectedRow(), 7).toString();
			System.out.println(trangThaiLamViec);
			if (trangThaiLamViec.equalsIgnoreCase("Đã nghĩ")) {
				btnTrangThaiLamViec.setText("Làm lại");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/lam_moi.png")));
			} else {
				btnTrangThaiLamViec.setText("Nghĩ việc");
				btnTrangThaiLamViec.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/icon/xoa.png")));
			}
		}
	}
}
