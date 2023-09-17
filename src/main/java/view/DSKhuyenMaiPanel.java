package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import dao.KhuyenMaiDao;
import entity.KhuyenMai;
import view.util.HeaderRenderer;

public class DSKhuyenMaiPanel extends JPanel implements ItemListener, ActionListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;
	private JButton btnLamMoi;
	private JButton btnCapNhat;
	private JButton btnThem;
	private DefaultTableModel tableModle;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtMoTa;
	private JLabel lblCm;
	private JTextField txtMaGiamGia;
	private JLabel lblNewLabel_1;
	private JLabel lblChitKhu;
	private JComboBox<String> cbTrangThai;
	private JButton btnTrV;
	private List<KhuyenMai> danhSachKhuyenMai;
	private KhuyenMaiDao khuyenMaiDao;
	private JDateChooser dateNgayBatDau;
	private JButton btnTim;
	private JButton btnDau;
	private JButton btnTru1;
	private JLabel txtPage;
	private JButton btnCong1;
	private JButton btnCuoi;
	private JLabel txtTotolPage;
	private JLabel txtPage_1;
	private int page = 1;
	private int limit = 20;
	private int totalPage;
	private JPanel panel;
	private JPanel panel_1;

	/**
	 * Create the frame.
	 */
	public DSKhuyenMaiPanel() {

		khuyenMaiDao = new KhuyenMaiDao(MainFrame.sessionFactory);
		setBounds(0, 0, 1525, 760);
		// setResizable(false);
//			setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 1275, 700);
		contentPane_1 = new JPanel();
		contentPane_1.setBounds(0, 0, 1525, 770);
		contentPane_1.setBackground(Color.WHITE);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblMaNV = new JLabel("Mã giảm giá: ");
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setMargin(new Insets(0, 0, 0, 0));
		btnLamMoi.setBackground(SystemColor.activeCaption);
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/user.png")));
		btnLamMoi.addActionListener(this);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setMargin(new Insets(2, 0, 2, 0));
		btnCapNhat.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setBackground(new Color(255, 99, 71));
		btnCapNhat.setIconTextGap(10);
		btnCapNhat.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/unemployment.png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThem = new JButton("Tạo mới");
		btnThem.setMargin(new Insets(0, 0, 0, 0));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		table = new JTable();
		table.setSelectionBackground(SystemColor.activeCaption);
		scrollPane.setColumnHeaderView(table);
		tableModle = new DefaultTableModel(new Object[][] {}, new String[] { "Mã giảm giá", "Mô tả", "Chiết khấu",
				"Ngày bắt đầu", "Ngày hết hạn", "Số lượng", "Đã sử dụng", "Trạng thái" });
//		table.addMouseListener(this);
		table.setRowHeight(40);
		table.setBackground(new Color(242, 242, 242));
		scrollPane.setViewportView(table);
		table.setModel(tableModle);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		;
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		txtMoTa = new JTextField();
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMoTa.setColumns(10);
		txtMoTa.addKeyListener(this);
		lblCm = new JLabel("Ngày bắt đầu:");
		lblCm.setForeground(Color.BLACK);
		lblCm.setFont(new Font("Tahoma", Font.BOLD, 16));
		table.setDefaultEditor(Object.class, null);

		txtMaGiamGia = new JTextField();
		txtMaGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaGiamGia.setColumns(10);

		lblNewLabel_1 = new JLabel("Quản Lý Khuyến Mãi");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBackground(new Color(72, 209, 204));

		dateNgayBatDau = new JDateChooser();
		dateNgayBatDau.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 14));
		dateNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dateNgayBatDau.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName()) && e.getNewValue() != null) {
					txtMaGiamGia.setText("");
					dateNgayBatDau.requestFocus();
					if (dateNgayBatDau.getDate().compareTo(new Date()) != 0) {
						cbTrangThai.setSelectedIndex(0);
					}

					loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());
				}
			}
		});

		lblChitKhu = new JLabel("Mô tả:");
		lblChitKhu.setForeground(Color.BLACK);
		lblChitKhu.setFont(new Font("Tahoma", Font.BOLD, 16));

		cbTrangThai = new JComboBox<String>();
		cbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTrangThai.setBackground(Color.WHITE);
		cbTrangThai.addItem("Tất cả");
		cbTrangThai.addItem("Chờ");
		cbTrangThai.addItem("Hoạt động");
		cbTrangThai.addItem("Hết hạn");
		cbTrangThai.addItemListener(this);

		btnTrV = new JButton(" Trở về  ");
		btnTrV.addActionListener(this);
		btnTrV.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/back.png")));
		btnTrV.setMargin(new Insets(2, 9, 2, 9));
		btnTrV.setForeground(Color.WHITE);
		btnTrV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTrV.setBackground(Color.GRAY);
		btnTrV.setIconTextGap(2);

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

		JLabel lblTrngThi = new JLabel("Trạng thái:");
		lblTrngThi.setForeground(Color.BLACK);
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnTim = new JButton("Tìm kiếm");
		btnTim.setMargin(new Insets(0, 0, 0, 0));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim.setBackground(new Color(0, 128, 0));

		btnDau = new JButton("");
		btnDau.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/previousEnd.png")));
		btnDau.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDau.setEnabled(true);

		btnTru1 = new JButton("");
		btnTru1.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/rewind-button.png")));
		btnTru1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTru1.setEnabled(true);

		txtPage = new JLabel("1");
		txtPage.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPage.setForeground(SystemColor.controlDkShadow);
		txtPage.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnCong1 = new JButton("");
		btnCong1.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/nextbutton.png")));
		btnCong1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCong1.setEnabled(true);

		btnCuoi = new JButton("");
		btnCuoi.setIcon(new ImageIcon(DSKhuyenMaiPanel.class.getResource("/icon/next.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCuoi.setEnabled(true);

		txtTotolPage = new JLabel("3");
		txtTotolPage.setHorizontalAlignment(SwingConstants.LEFT);
		txtTotolPage.setFont(new Font("Tahoma", Font.BOLD, 17));

		txtPage_1 = new JLabel("/");
		txtPage_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPage_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCapNhat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);

		setLayout(null);
		cbTrangThai.setSelectedIndex(2);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GroupLayout gl_contentPane_1 = new GroupLayout(contentPane_1);
		gl_contentPane_1.setHorizontalGroup(gl_contentPane_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane_1.createSequentialGroup().addGap(16)
						.addComponent(lblCm, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE).addGap(17)
						.addComponent(dateNgayBatDau, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE).addGap(52)
						.addComponent(lblTrngThi, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGap(15).addComponent(cbTrangThai, 0, 273, Short.MAX_VALUE).addGap(86)
						.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE).addGap(36)
						.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE).addGap(38)
						.addComponent(btnTrV, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE).addGap(38))
				.addGroup(gl_contentPane_1.createSequentialGroup().addGroup(gl_contentPane_1
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1477, Short.MAX_VALUE)
						.addGroup(gl_contentPane_1.createSequentialGroup().addGap(16)
								.addComponent(lblMaNV, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addGap(24).addComponent(txtMaGiamGia, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addGap(52)
								.addComponent(lblChitKhu, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addGap(15).addComponent(txtMoTa, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
								.addGap(36)
								.addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGap(38)
								.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
						.addGap(38))
				.addGroup(Alignment.LEADING,
						gl_contentPane_1.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1481, Short.MAX_VALUE).addGap(34))
				.addGroup(gl_contentPane_1.createSequentialGroup().addGap(258)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE).addGap(68)
						.addComponent(btnDau, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnTru1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(179).addComponent(btnCong1,
										GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(138).addComponent(
										txtTotolPage, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(92).addComponent(txtPage_1,
										GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(78).addComponent(txtPage,
										GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
						.addGap(15).addComponent(btnCuoi, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addGap(67).addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE).addGap(231)));
		gl_contentPane_1.setVerticalGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_1.createSequentialGroup()
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMaNV, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMaGiamGia, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(5).addComponent(lblChitKhu,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtMoTa, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCm, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateNgayBatDau, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTrngThi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbTrangThai, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTrV, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(9).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 529, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDau, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTru1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCong1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_1.createSequentialGroup().addGap(1).addComponent(txtTotolPage,
										GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtPage_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPage, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCuoi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(30, Short.MAX_VALUE)));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 94, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 29, Short.MAX_VALUE));
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 94, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 29, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
		contentPane_1.setLayout(gl_contentPane_1);
		add(contentPane_1);
	}

	public void khoiTao() {
		xoaDuLieuTable();
		totalPage = khuyenMaiDao.tongTrang("", 2, null, limit);
		danhSachKhuyenMai = khuyenMaiDao.layDanhSachKhuyenMaiTheoTenNgayTrangThai(page - 1, limit, null, null, 2);
		loadDuLieuVaoTable(null, null, 2);
	}

	@SuppressWarnings("unused")
	private List<KhuyenMai> getDanhSachVuSauKiemTra() {
		danhSachKhuyenMai.forEach(sp -> {
			if (sp.isTrangThai()) {
				getTrangThaiTheoNgay(sp);
				khuyenMaiDao.capNhatKhuyenMai(sp);
			}
		});
		return danhSachKhuyenMai;
	}

	private String getTrangThaiTheoNgay(KhuyenMai km) {
		if (km.getNgayBatDau().compareTo(new Date()) == 1) {
			return "Chờ";
		} else if (km.getNgayBatDau().compareTo(new Date()) <= 0 && km.getNgayHetHan().compareTo(new Date()) >= 0) {
			return "Hoạt động";
		} else if (km.getNgayHetHan().compareTo(new Date()) == -1) {
			km.setTrangThai(false);
			return "Hết hạn";
		}
		return "";
	}

	private void loadDuLieuVaoTable() {
		try {
			if (danhSachKhuyenMai.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy khuyến mãi nào");
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		xoaDuLieuTable();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		danhSachKhuyenMai.forEach(KM -> {
			tableModle.addRow(new Object[] { KM.getMaGiamGia(), KM.getMoTa(), KM.getChietKhau(),
					df.format(KM.getNgayBatDau()), df.format(KM.getNgayHetHan()), KM.getTongSoLuong(), KM.getDaSuDung(),
					getTrangThaiTheoNgay(KM) });
		});

	}

	public void xoaDuLieuTable() {
		int maxRow = table.getRowCount();
		for (int i = maxRow - 1; i >= 0; i--) {
			tableModle.removeRow(i);
		}
	}

	public String taoTrangThai(boolean tt) {
		return tt ? "Hoạt động" : "Tạm dừng";
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object == txtMaGiamGia) {
			dateNgayBatDau.setDate(null);
			txtMoTa.setText("");
		}
		if (object == txtMaGiamGia && KeyEvent.VK_ENTER == e.getKeyCode()) {
			page = 1;
			cbTrangThai.setSelectedIndex(0);
			danhSachKhuyenMai = Arrays.asList(khuyenMaiDao.layKhuyenMaiTheoMa(txtMaGiamGia.getText()));
			loadDuLieuVaoTable();
		}
		if (object == txtMoTa) {
			page = 1;
			loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());
		}
	}

	private void loadDuLieuVaoTable(String text, Date ngayNhap, int selectedIndex) {

		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = "";
		if (ngayNhap != null) {
			date = df1.format(ngayNhap);
		}
		txtMaGiamGia.setText("");
		txtPage.setText(page + "");
		xoaDuLieuTable();
		int realPage = page - 1;

		danhSachKhuyenMai = khuyenMaiDao.layDanhSachKhuyenMaiTheoTenNgayTrangThai(realPage, limit, text, date,
				selectedIndex);
		// TODO Auto-generated method stub
		try {
			if (danhSachKhuyenMai == null) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy khuyến mãi nào");
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		totalPage = khuyenMaiDao.tongTrang(txtMoTa.getText(), selectedIndex, date, limit);
		txtTotolPage.setText(totalPage + "");

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		danhSachKhuyenMai.forEach(KM -> {
			tableModle.addRow(new Object[] { KM.getMaGiamGia(), KM.getMoTa(), KM.getChietKhau(),
					df.format(KM.getNgayBatDau()), df.format(KM.getNgayHetHan()), KM.getTongSoLuong(), KM.getDaSuDung(),
					getTrangThaiTheoNgay(KM) });
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnCapNhat)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi");
				return;
			}
			new DialogSuaKhuyenMai(table.getValueAt(row, 0).toString()).setVisible(true);
			loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());

		}
		if (object.equals(btnThem)) {
			new DialogThemKhuyenMai().setVisible(true);
			clear();
			loadDuLieuVaoTable(null, null, cbTrangThai.getSelectedIndex());
		}
		if (object == btnLamMoi) {
			clear();
			loadDuLieuVaoTable(null, null, cbTrangThai.getSelectedIndex());
		}
		if (object == btnTim) {
			cbTrangThai.setSelectedIndex(0);
			danhSachKhuyenMai = Arrays.asList(khuyenMaiDao.layKhuyenMaiTheoMa(txtMaGiamGia.getText().toString()));
			loadDuLieuVaoTable();
		}
		if (object == btnCong1) {
			if (page + 1 <= totalPage) {
				page++;
				loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());
			}
		}
		if (object == btnTru1) {
			if (page - 1 >= 1) {
				page--;
				loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());
			}
		}
		if (object == btnCuoi) {
			if (page != totalPage) {
				page = totalPage;
				loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());
			}

		}
		if (object == btnDau) {
			if (page != 1) {
				page = 1;
				loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());
			}
		}
	}

	private void clear() {
		table.clearSelection();
		txtMaGiamGia.setText("");
		txtMoTa.setText("");
		dateNgayBatDau.setDate(null);
		cbTrangThai.setSelectedIndex(2);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if (o == cbTrangThai) {
			page = 1;
			loadDuLieuVaoTable(txtMoTa.getText(), dateNgayBatDau.getDate(), cbTrangThai.getSelectedIndex());
		}
	}

}