package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.hibernate.SessionFactory;

import dao.DiaChiDao;
import dao.KhachHangDao;
import dao.MySessionFactory;
import entity.KhachHang;
import service.DiaChiService;
import service.KhachHangService;
//import app.KhachHangPanel;
//import app.KhachHangPanel;
import view.util.HeaderRenderer;

public class KhachHangPanel extends JPanel implements ActionListener, KeyListener, MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**/
	private JPanel contentPane;
	private JPanel contentPane_1;
	private JButton btnLamMoi;
	private JButton btnSua;
	private JButton btnThem;
	private DefaultTableModel tableModle;
	private JScrollPane scrollPane;
	private JTable table;
	@SuppressWarnings("unused")
	private JTextField txtChietKhau;
	private JLabel lblCm;
	@SuppressWarnings("unused")
	private JTextField txtMaGiamGia;
	private JLabel lblNewLabel_1;
	@SuppressWarnings("unused")
	private JLabel lblChitKhu;
	@SuppressWarnings({ "unused", "rawtypes" })
	private JComboBox cbHoatDong;
	@SuppressWarnings("unused")
	private JButton btnTrV;
	private Component lblNewLabel;
	private JLabel lblTn;
	private JTextField textTen;
	private JTextField textSDT;
	private Component lblNhpSdt;
	private AbstractButton btnTim;
	private JTextField textMa;
	private int limit = 20;
	@SuppressWarnings("rawtypes")
	private JComboBox cbioiTinh;
	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	KhachHangService khachHangdao = new KhachHangDao(sessionFactory);
	DiaChiService diaChiDao = new DiaChiDao(sessionFactory);
	@SuppressWarnings("unused")
	private ArrayList<KhachHang> litsKhachHangs;
	private List<KhachHang> khachHang;
	private JLabel txtPage;
	private JButton btnDau;
	private JButton btnTru1;
	private JButton btnCong1;
	private JButton btnCuoi;
	private JLabel txtTongTrang;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public KhachHangPanel() {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
//		setLayout(null);
//		setAlwaysOnTop(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1295, 638);
		// setResizable(false);
//		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 1295, 700);
		contentPane.setLayout(null);
		contentPane_1 = new JPanel();
		contentPane_1.setBackground(Color.WHITE);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(1277, 660);

		btnSua = new JButton("Sửa");
		btnSua.setMargin(new Insets(2, 9, 2, 9));
		btnSua.setBackground(new Color(169, 169, 169));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setToolTipText("");
		btnSua.setIconTextGap(20);
		btnSua.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/user.png")));
		btnSua.addActionListener(this);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnThem = new JButton("Thêm");
		btnThem.setMargin(new Insets(2, 9, 2, 9));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setIconTextGap(15);
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));

		cbioiTinh = new JComboBox();
		cbioiTinh.setBackground(new Color(255, 248, 220));
		cbioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới Tính", "Nam", "Nữ" }));
		cbioiTinh.setToolTipText("");
		cbioiTinh.setName("Loại");
		cbioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/synchronize.png")));
		btnLamMoi.setMargin(new Insets(2, 2, 2, 2));
		btnLamMoi.setIconTextGap(10);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLamMoi.setBackground(new Color(107, 142, 35));

		lblNewLabel = new JLabel("Mã KH: ");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblTn = new JLabel("Tên: ");
		lblTn.setForeground(Color.BLACK);
		lblTn.setFont(new Font("Tahoma", Font.BOLD, 16));

		textTen = new JTextField();
		textTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTen.setColumns(10);

		textSDT = new JTextField();
		textSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSDT.setColumns(10);

		lblCm = new JLabel("SDT: ");
		lblCm.setForeground(Color.BLACK);
		lblCm.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblNhpSdt = new JLabel("Giới tính: ");
		lblNhpSdt.setForeground(Color.BLACK);
		lblNhpSdt.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/search.png")));
		btnTim.setIconTextGap(20);
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim.setBackground(new Color(60, 179, 113));

		textMa = new JTextField();
		textMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textMa.setColumns(10);

		lblNewLabel_1 = new JLabel("Danh Sách Khách Hàng");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBackground(new Color(72, 209, 204));

		scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setColumnHeaderView(table);

		tableModle = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã KH ", "Họ tên", "SĐT", "Ngày sinh", "Giới tính", "Địa chỉ" });

		table.setRowHeight(50);
		scrollPane.setViewportView(table);
		table.setModel(tableModle);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

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

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);

		btnDau = new JButton("");
		btnDau.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/previousEnd.png")));
		btnDau.setFont(new Font("Arial", Font.PLAIN, 16));

		btnTru1 = new JButton("");
		btnTru1.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/rewind-button.png")));
		btnTru1.setFont(new Font("Arial", Font.PLAIN, 16));

		txtPage = new JLabel("1");
		txtPage.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnCong1 = new JButton("");
		btnCong1.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/forward-button.png")));
		btnCong1.setFont(new Font("Arial", Font.PLAIN, 16));

		btnCuoi = new JButton("");
		btnCuoi.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/icon/next.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));

		txtTongTrang = new JLabel("1");
		txtTongTrang.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtTongTrang.setAlignmentY(0.0f);

		JLabel txtPage_1 = new JLabel("/");
		txtPage_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPage_1.setAlignmentY(0.0f);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addGap(463)
				.addComponent(btnDau, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(18)
				.addComponent(btnTru1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(26)
				.addComponent(txtPage, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtPage_1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE).addGap(9)
				.addComponent(txtTongTrang, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(btnCong1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(28)
				.addComponent(btnCuoi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGap(465)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup().addGap(1)
										.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnTru1).addComponent(btnDau))
												.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
														.addComponent(txtTongTrang, GroupLayout.PREFERRED_SIZE, 20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(txtPage))))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addComponent(btnCuoi)
										.addComponent(btnCong1))
								.addComponent(txtPage_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_contentPane_1 = new GroupLayout(contentPane_1);
		gl_contentPane_1.setHorizontalGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_1.createSequentialGroup().addGap(26)
						.addComponent(lblTn, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE).addGap(6)
						.addComponent(textTen, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE).addGap(21)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNhpSdt, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(81).addComponent(cbioiTinh, 0,
										370, Short.MAX_VALUE)))
						.addGap(26).addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
						.addGap(15).addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
						.addGap(32))
				.addGroup(gl_contentPane_1.createSequentialGroup().addGap(26)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(70).addComponent(textMa,
										GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)))
						.addGap(23).addComponent(lblCm, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addGap(19).addComponent(textSDT, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE).addGap(24)
						.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE).addGap(32))
				.addGroup(gl_contentPane_1.createSequentialGroup()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 1275, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING,
						gl_contentPane_1.createSequentialGroup()
								.addGroup(gl_contentPane_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1265,
												Short.MAX_VALUE)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1265, Short.MAX_VALUE))
								.addGap(20)));
		gl_contentPane_1.setVerticalGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_1.createSequentialGroup().addComponent(lblNewLabel_1).addGap(10)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(3).addComponent(textTen,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(1)
										.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNhpSdt, GroupLayout.PREFERRED_SIZE, 41,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane_1.createSequentialGroup().addGap(2)
														.addComponent(cbioiTinh, GroupLayout.PREFERRED_SIZE, 35,
																GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(1).addComponent(btnLamMoi,
										GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(1).addComponent(btnThem,
										GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
						.addGap(6)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(4).addComponent(textMa,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(1).addComponent(lblCm,
										GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(4).addComponent(textSDT,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(1).addComponent(btnTim,
										GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(1).addComponent(btnSua,
										GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
						.addGap(23).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE).addGap(13)));
		contentPane_1.setLayout(gl_contentPane_1);
		cbioiTinh.addItemListener(this);

		btnCong1.addActionListener(this);
		btnTru1.addActionListener(this);
		btnDau.addActionListener(this);
		btnCuoi.addActionListener(this);
		btnLamMoi.addActionListener(this);
		cbioiTinh.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		textMa.addActionListener(this);
		textSDT.addActionListener(this);
		textTen.addKeyListener(this);
		table.addMouseListener(this);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPane_1, GroupLayout.PREFERRED_SIZE, 1275, Short.MAX_VALUE).addGap(2)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(5)
						.addComponent(contentPane_1, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE).addGap(1)));
		setLayout(groupLayout);
		// khong cho sua table
		table.setDefaultEditor(Object.class, null);

	}

	private void xoaALLDuLieuTable() {
		for (int i = tableModle.getRowCount(); i > 0; i--) {
			tableModle.removeRow(0);
		}

	}

	private void xoaChon() {
		textMa.setText("");
		textTen.setText("");
		textSDT.setText("");
		txtPage.setText("1");
		textSDT.setEnabled(true);
		textMa.setEnabled(true);
		cbioiTinh.setSelectedIndex(0);
		xoaSachModel();
		docDuLieuVaoBang();
	}

	public void khoiTao() {
		xoaSachModel();
		docDuLieuVaoBang();
	}

	public void xoaSachModel() {
		try {
			for (int i = khachHang.size() - 1; i >= 0; i--)
				tableModle.removeRow(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mouseClicked(MouseEvent e) {
//		int row = table.getSelectedRow();
//		textMa.setText(tableModle.getValueAt(row, 0).toString());
//		textTen.setText(tableModle.getValueAt(row, 1).toString());
//		textSDT.setText(tableModle.getValueAt(row, 2).toString());
//		try {
//			Date ngay = new SimpleDateFormat("dd/MM/yyyy").parse(tableModle.getValueAt(row, 3).toString());
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		cbioiTinh.setSelectedItem(tableModle.getValueAt(row, 4));
	}

	public String getGioiTinh(boolean gt) {
		return gt ? "Nam" : "Nữ";
	}

	public void docDuLieuVaoBang() {

		int page = Integer.parseInt(txtPage.getText());
		String gioiTinh = "";
		System.out.println(page);
		if (cbioiTinh.getSelectedIndex() != 0) {
			gioiTinh = cbioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam") ? "1" : "0";
		}
		System.out.println(gioiTinh);
		List<KhachHang> dsKH = null;
		dsKH = khachHangdao.layDanhSachKhachHang(page - 1, textTen.getText().trim(), gioiTinh, limit);

		if (dsKH == null) {
			JOptionPane.showMessageDialog(this, "rong");
			txtPage.setText("1");
			return;
		}
//		khachHang = khachHangdao.layDanhSachKhacHang();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		xoaALLDuLieuTable();
		for (KhachHang khachHang2 : dsKH) {
			tableModle.addRow(new Object[] { khachHang2.getMaKH(), khachHang2.getHoTen(), khachHang2.getSoDienThoai(),
					df.format(khachHang2.getNgaySinh()), khachHang2.isGioiTinh() ? "Nam" : "Nữ",
					khachHang2.getDiaChi().getPhuongXa() + "-" + khachHang2.getDiaChi().getQuanHuyen() + "-"
							+ khachHang2.getDiaChi().getTinhTP() });
		}

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		System.err.println("Page " + txtPage.getText());
		int tongPage = 1;
		String gioiTinh = "";
		if (cbioiTinh.getSelectedIndex() != 0) {
			gioiTinh = cbioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam") ? "1" : "0";
		}
		try {
			tongPage = khachHangdao.tongTrang(textTen.getText().trim(), gioiTinh, limit);
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
				txtTongTrang.setText(Integer.toString(tongPage));
				txtPage.setText(Integer.toString(page));
				xoaALLDuLieuTable();

				docDuLieuVaoBang();
			}
		} else if (object.equals(btnTru1)) { // Lui page table

			int page = Integer.parseInt(txtPage.getText()) - 1;

			if (page >= 1) {
				txtTongTrang.setText(Integer.toString(tongPage));
				txtPage.setText(Integer.toString(page));
				xoaALLDuLieuTable();
				docDuLieuVaoBang();
			}

		} else if (object.equals(btnCuoi)) { // Cuoi page table
			int page = Integer.parseInt(txtPage.getText());
			System.err.println(tongPage);
			if (page <= tongPage) {
				txtTongTrang.setText(Integer.toString(tongPage));
				txtPage.setText(Integer.toString(tongPage));
				xoaALLDuLieuTable();
				docDuLieuVaoBang();
			}

		} else if (object.equals(btnDau)) { // Dau page table

			int page = Integer.parseInt(txtPage.getText());

			if (page != 0) {
				txtTongTrang.setText(Integer.toString(tongPage));
				txtPage.setText("1");
				xoaALLDuLieuTable();
				docDuLieuVaoBang();
			}

		} else if (object.equals(btnThem)) {
			new DialogThemKhachHang("").setVisible(true);
		} else if (object.equals(btnSua)) {
			int selectRow = table.getSelectedRow();
			if (selectRow == -1) {
				JOptionPane.showMessageDialog(this, "Ban chua chon khach hang");
				return;
			}
			String ma = (String) table.getValueAt(table.getSelectedRow(), 0);
			DialogSuaKhachHang dialogSuaKhachHang = new DialogSuaKhachHang(ma);
			dialogSuaKhachHang.setVisible(true);
			xoaChon();

		} else if (object.equals(btnLamMoi)) {
			xoaChon();
			xoaALLDuLieuTable();
			docDuLieuVaoBang();
		} else if (object.equals(btnTim)) {
			String maNV = textMa.getText();
			String sdt = textSDT.getText();
			KhachHang kh = null;
			if (!maNV.trim().equals("")) {

				kh = khachHangdao.layKhachHangTheoMa(maNV);
				if (kh != null) {
					xoaALLDuLieuTable();
					textSDT.setText("");
					textSDT.setEnabled(false);
					tableModle.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getSoDienThoai(),
							df.format(kh.getNgaySinh()), getGioiTinh(kh.isGioiTinh()), kh.getDiaChi().getPhuongXa()
									+ "-" + kh.getDiaChi().getQuanHuyen() + "-" + kh.getDiaChi().getTinhTP() });
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy!!");
					return;

				}
			}
			if (!sdt.trim().equals("")) {

				kh = khachHangdao.layKhachHangTheoSDT(sdt);
				if (kh != null) {
					xoaALLDuLieuTable();
					textMa.setText("");
					textMa.setEnabled(false);
					tableModle.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getSoDienThoai(),
							df.format(kh.getNgaySinh()), getGioiTinh(kh.isGioiTinh()), kh.getDiaChi().getPhuongXa()
									+ "-" + kh.getDiaChi().getQuanHuyen() + "-" + kh.getDiaChi().getTinhTP() });
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy!!");
					return;

				}
			}

		}
		if (object.equals(cbioiTinh)) {
			txtPage.setText("1");
			xoaALLDuLieuTable();
			docDuLieuVaoBang();
		}
//		if (object.equals(cbioiTinh)) {
//
//			if (cbioiTinh.getSelectedItem().toString().trim() == "Nữ") {
//				khachHang = khachHangdao.layDanhSachKhachHangTheoGioiTinh(false);
//
//				for (KhachHang khachHang2 : khachHang) {
//					tableModle.addRow(
//							new Object[] { khachHang2.getMaKH(), khachHang2.getHoTen(), khachHang2.getSoDienThoai(),
//									df.format(khachHang2.getNgaySinh()), khachHang2.isGioiTinh() ? "Nam" : "Nữ",
//									khachHang2.getDiaChi().getPhuongXa() + "-" + khachHang2.getDiaChi().getQuanHuyen()
//											+ "-" + khachHang2.getDiaChi().getTinhTP() });
//				}
//			}
//
//			if (cbioiTinh.getSelectedItem().toString().trim() == "Nam") {
//
//				khachHang = khachHangdao.layDanhSachKhachHangTheoGioiTinh(true);
//				for (KhachHang khachHang2 : khachHang) {
//					tableModle.addRow(
//							new Object[] { khachHang2.getMaKH(), khachHang2.getHoTen(), khachHang2.getSoDienThoai(),
//									df.format(khachHang2.getNgaySinh()), khachHang2.isGioiTinh() ? "Nam" : "Nữ",
//									khachHang2.getDiaChi().getPhuongXa() + "-" + khachHang2.getDiaChi().getQuanHuyen()
//											+ "-" + khachHang2.getDiaChi().getTinhTP() });
//					System.out.println(khachHang);
//				}
//
//			}
//		}
	}

	public boolean ktgioiTinh() {

		if (cbioiTinh.getSelectedIndex() == 1) {
			return true;
		}
		if (cbioiTinh.getSelectedIndex() == 2) {
			return false;
		}
		return false;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

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
		txtPage.setText("1");
		docDuLieuVaoBang();

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Object object = e.getSource();
		if (object.equals(textTen)) {
			String ten = textTen.getText().toString();
			if (ten.equalsIgnoreCase("")) {
				xoaALLDuLieuTable();
				docDuLieuVaoBang();
			} else {

				xoaALLDuLieuTable();
				table.clearSelection();
				cbioiTinh.setSelectedIndex(0);
				khachHang = khachHangdao.layDanhSachKhachHangTheoTen(textTen.getText());
				for (KhachHang khachHang2 : khachHang) {

					tableModle.addRow(
							new Object[] { khachHang2.getMaKH(), khachHang2.getHoTen(), khachHang2.getSoDienThoai(),
									df.format(khachHang2.getNgaySinh()), getGioiTinh(khachHang2.isGioiTinh()),
									khachHang2.getDiaChi().getPhuongXa() + "-" + khachHang2.getDiaChi().getQuanHuyen()
											+ "-" + khachHang2.getDiaChi().getTinhTP() });

				}
			}
		}

	}
}
