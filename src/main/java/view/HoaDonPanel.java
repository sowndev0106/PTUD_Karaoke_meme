package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.HoaDonDao;
import entity.HoaDon;
import view.util.FormatCustom;

public class HoaDonPanel extends JPanel implements ActionListener, KeyListener, FocusListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfTenKhach;
	private JTextField tfMaHoaDon;
	private DefaultTableModel tableModel;
	private JComboBox<String> cbThoiGian;
	private JButton btnChiTiet;
	private JButton btnLamMoi;
	private JButton btnDau;
	private JButton btntru1;
	private JLabel lblPage;
	private JButton btnCong1;
	private JButton btnCuoi;
//	private HoaDonDao hoaDonDao = null;
//	private List<HoaDon> dsHoaDon;
	private int limit = 20;
	private JDateChooser dateChooser_NgayLap;
	@SuppressWarnings("unused")
	private JPanel panel;
	private JTextField tfSDTKhach;
	private JButton btnTimKiem;
	private HoaDonDao hoaDonDao;
	private JLabel lblTongTrang;
	private int tongTrang;
	private DialogChiTietHoaDon dialogChiTietHoaDon;
	private List<HoaDon> dsHoaDon;
	private String maNhanVien = MainFrame.nhanVien.getLoaiNhanVien().getMaLNV().equals("LNV001") ? ""
			: MainFrame.nhanVien.getMaNV();
	private Date date = new Date();
	private boolean isChangeDateChoise = false;

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public HoaDonPanel() {
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setSize(1275, 659);
		JPanel panel = new JPanel();

		JLabel lblNewLabel = new JLabel("Tên khách:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);

		cbThoiGian = new JComboBox();
		cbThoiGian.setFont(new Font("Arial", Font.PLAIN, 16));
		cbThoiGian.setModel(
				new DefaultComboBoxModel(new String[] { "(Tất cả)", "Hôm nay", "Trong tháng ", "Trong năm" }));

		JLabel lblNewLabel_1 = new JLabel("Thời gian");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		cbThoiGian.setSelectedIndex(1);

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowHeight(35);
		table.setModel(tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Mã hóa đơn", "Ngày lập",
				"Tên khách hàng", "SĐT Khách", "Nhân viên", "Chiết khấu", "Tổng tiền" }));

		btnDau = new JButton("");
		btnDau.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/icon/previousEnd.png")));
		btnDau.setFont(new Font("Arial", Font.PLAIN, 16));

		btntru1 = new JButton("");
		btntru1.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/icon/rewind-button.png")));
		btntru1.setFont(new Font("Arial", Font.PLAIN, 16));

		btnCong1 = new JButton("");
		btnCong1.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/icon/forward-button.png")));
		btnCong1.setFont(new Font("Arial", Font.PLAIN, 16));

		tfTenKhach = new JTextField();
		tfTenKhach.setColumns(10);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(SystemColor.menu);
		btnLamMoi.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/icon/lam_moi.png")));
		btnLamMoi.setFont(new Font("Arial", Font.PLAIN, 16));

		btnCuoi = new JButton("");
		btnCuoi.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/icon/nextEnd.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));

		lblPage = new JLabel("1");
		lblPage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPage.setForeground(new Color(0, 0, 0));
		lblPage.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblSdtKh = new JLabel("SĐT khách:");
		lblSdtKh.setForeground(Color.BLACK);
		lblSdtKh.setFont(new Font("Arial", Font.BOLD, 16));

		tfMaHoaDon = new JTextField();
		tfMaHoaDon.setColumns(10);

		btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.setForeground(Color.WHITE);

		btnChiTiet.setBackground(SystemColor.menu);
		btnChiTiet.setForeground(SystemColor.desktop);

		btnChiTiet.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/icon/detail.png")));
		btnChiTiet.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblNewLabel_1_1 = new JLabel("Ngày lập");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));

		dateChooser_NgayLap = new JDateChooser();
		dateChooser_NgayLap.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateChooser_NgayLap.setDateFormatString("dd/MM/yyyy");
		dateChooser_NgayLap.setFont(new Font("Arial", Font.PLAIN, 14));
		LocalDate now = LocalDate.now();
		int ngay = now.getDayOfMonth();
		int thang = now.getMonthValue();
		int nam = now.getYear() - 1900;
		date.setDate(ngay);
		date.setMonth(thang);
		date.setYear(nam);
		dateChooser_NgayLap.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if (dateChooser_NgayLap.getDate() != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = dateChooser_NgayLap.getDate();
					String[] s = dateFormat.format(date).split("/");
					date.setDate(Integer.parseInt(s[0]));
					date.setMonth(Integer.parseInt(s[1]));
					date.setYear(Integer.parseInt(s[2]));
					themDuLieuVaoBang(date);
					isChangeDateChoise = true;
					cbThoiGian.disable();

				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 206, 209));

		JLabel lblNewLabel_11 = new JLabel("Quản lý hóa đơn");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_11.setForeground(Color.WHITE);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_11,
				GroupLayout.DEFAULT_SIZE, 1271, Short.MAX_VALUE));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_11,
				GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE));
		panel_2.setLayout(gl_panel_2);

		JLabel lblNewLabel_1_2 = new JLabel("Mã hóa đơn:");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 16));

		tfSDTKhach = new JTextField();
		tfSDTKhach.setColumns(10);

		btnTimKiem = new JButton("Tìm kiếm");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(HoaDonPanel.class.getResource("/icon/search.png")).getImage()
				.getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		btnTimKiem.setIcon(imageIcon);
		btnTimKiem.setForeground(SystemColor.desktop);
		btnTimKiem.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTimKiem.setBackground(SystemColor.window);

		lblTongTrang = new JLabel("/1");
		lblTongTrang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTrang.setForeground(Color.BLACK);
		lblTongTrang.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addGap(10)
				.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(tfMaHoaDon, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE).addGap(10)
				.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(btnChiTiet, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE).addGap(15)
				.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE).addGap(15))
				.addGroup(gl_panel.createSequentialGroup().addGap(10)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1255, Short.MAX_VALUE).addGap(26))
				.addGroup(gl_panel.createSequentialGroup().addGap(425)
						.addComponent(btnDau, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE).addGap(15)
						.addComponent(btntru1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
						.addComponent(lblPage, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE).addGap(1)
						.addComponent(lblTongTrang, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE).addGap(18)
						.addComponent(btnCong1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE).addGap(15)
						.addComponent(btnCuoi, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE).addGap(417))
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1271, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup().addGap(10)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(tfTenKhach, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
								.addGap(45)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSdtKh, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup().addGap(108).addComponent(tfSDTKhach,
												GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
								.addGap(10)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addComponent(cbThoiGian, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 80,
										GroupLayout.PREFERRED_SIZE)
								.addGap(2).addComponent(dateChooser_NgayLap, GroupLayout.PREFERRED_SIZE, 140,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(20)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(1)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(36)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addGap(2).addComponent(tfTenKhach,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSdtKh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addGap(2).addComponent(tfSDTKhach,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(lblNewLabel_1,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(cbThoiGian,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(dateChooser_NgayLap,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
				.addGap(8)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(lblNewLabel_1_2,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(tfMaHoaDon,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(btnTimKiem,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnChiTiet, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGap(13).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE).addGap(7)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(btnDau,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(btntru1,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblPage,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblTongTrang,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(btnCong1,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(btnCuoi,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
				.addGap(2)));
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.PREFERRED_SIZE, 1275, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE).addGap(7)));
		setLayout(groupLayout);
		hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);

		cbThoiGian.addActionListener(this);
		tfTenKhach.addKeyListener(this);
		btnChiTiet.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);

		btnCong1.addActionListener(this);
		btnDau.addActionListener(this);
		btntru1.addActionListener(this);
		btnCuoi.addActionListener(this);
		tfSDTKhach.addKeyListener(this);

		table.addMouseListener(this);
		// khong cho sua table
		table.setDefaultEditor(Object.class, null);
		dialogChiTietHoaDon = new DialogChiTietHoaDon();

	}

	@SuppressWarnings("deprecation")
	public void themDuLieuVaoBang(Date date) {
		xoaTatCaHang();
		Date FilterThoiGian = new Date();
		String maHD = tfMaHoaDon.getText().toString().trim(), tenKH = null, sdtKhach = null;
		Date ngayLapHD = null;
		if (maHD.equals("")) {
			tenKH = tfTenKhach.getText().trim();
			sdtKhach = tfSDTKhach.getText().trim();
		}
		int indexCBthoiGian = cbThoiGian.getSelectedIndex();
		// test

		if (indexCBthoiGian == 0) {
			FilterThoiGian.setYear(0);
			FilterThoiGian.setMonth(1);
			FilterThoiGian.setDate(1);
		} else if (indexCBthoiGian == 2) {
			FilterThoiGian.setDate(1);
		} else if (indexCBthoiGian == 3) {
			FilterThoiGian.setMonth(1);
			FilterThoiGian.setDate(1);
		}
		if (date != null) {
			ngayLapHD = date;
		}

		dsHoaDon = hoaDonDao.layDanhSachHoaDon(maHD, tenKH, sdtKhach, ngayLapHD, FilterThoiGian,
				Integer.parseInt(lblPage.getText()) - 1, limit, maNhanVien);
		tongTrang = hoaDonDao.layTongTrang(maHD, tenKH, sdtKhach, ngayLapHD, FilterThoiGian, limit, maNhanVien);
		xoaTatCaHang();
		date = null;
		if (dsHoaDon == null || dsHoaDon.size() == 0) {
			if (!maHD.equals("")) {
				JOptionPane.showMessageDialog(this, "Mã hóa đơn " + maHD + " Không tồn tại");
			}
			return;
		}

		dsHoaDon.forEach(e -> {
			tableModel.addRow(new String[] { e.getMaHD(), e.getNgayLap().toGMTString(), e.getKhachHang().getHoTen(),
					e.getKhachHang().getSoDienThoai(), e.getNhanVienLap().getHoTen(), e.getChietKhau() + " %",
					FormatCustom.chuyenDoiTienTe(e.getTongTien()) });
		});

		lblTongTrang.setText("/" + tongTrang);
	}

	public void khoiTaoDuLieu() {
		xoaTatCaHang();
		themDuLieuVaoBang(null);
	}

	public void xoaTatCaHang() {
		for (int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(0);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnDau)) {
			int page = Integer.parseInt(lblPage.getText());
			if (page == 1) {
				return;
			}
			lblPage.setText(Integer.toString(1));
			themDuLieuVaoBang(null);
			return;
		}
		if (object.equals(btntru1)) {

			int page = Integer.parseInt(lblPage.getText());
			if (page == 1) {
				return;
			}
			lblPage.setText(Integer.toString(page - 1));

			themDuLieuVaoBang(null);
			return;
		}
		if (object.equals(btnCong1)) {
			int page = Integer.parseInt(lblPage.getText());
			if (page == tongTrang) {
				return;
			}
			lblPage.setText(Integer.toString(page + 1));
			themDuLieuVaoBang(null);
			return;
		}
		if (object.equals(btnCuoi)) {
			int page = Integer.parseInt(lblPage.getText());
			if (page == tongTrang) {
				return;
			}
			lblPage.setText(Integer.toString(tongTrang));
			themDuLieuVaoBang(null);
			return;
		}
		if (object.equals(cbThoiGian)) {
			lblPage.setText("1");
			themDuLieuVaoBang(null);
			if (!isChangeDateChoise) {
				dateChooser_NgayLap.setDate(null);
				isChangeDateChoise = false;
				System.out.println("vao");
			}
			return;
		}
		if (object.equals(btnTimKiem)) {
			lblPage.setText("1");
			themDuLieuVaoBang(null);
			return;
		}
		if (object.equals(btnLamMoi)) {
			lblPage.setText("1");
			tfMaHoaDon.setText("");
			tfSDTKhach.setText("");
			tfTenKhach.setText("");
			dateChooser_NgayLap.setDate(null);
			cbThoiGian.setSelectedIndex(1);
			themDuLieuVaoBang(null);
			cbThoiGian.enable();
			return;
		}
		if (object.equals(btnChiTiet)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				HoaDon hoaDon = dsHoaDon.get(row);
				hoaDon.setChiTietHoaDon(hoaDonDao.layChiTietHoaDon(hoaDon.getMaHD()));
				hoaDon.setChiTietDichVu(hoaDonDao.layChiTietDichVu(hoaDon.getMaHD()));
				dialogChiTietHoaDon.khoiTao(hoaDon);
				dialogChiTietHoaDon.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
			}
		}

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
		khoiTaoDuLieu();
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
}
