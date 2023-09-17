package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.DichVuDao;
import dao.HoaDonDao;
import entity.ChiTietDichVu;
import entity.DichVu;
import entity.HoaDon;
import entity.Phong;
import view.util.FormatCustom;

public class DialogCapNhatDichVuTrongHoaDon extends JDialog implements ActionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private JTextField tfMaDV;
	private JTextField tfTenDV;
	private DefaultTableModel modelTatCaDichVu;
	private DefaultTableModel modelDichVuDaThem;
	private JTable tableTatcaDichVu;
	private JTable tableDichVuDaThem;
	private List<DichVu> dsDichVuDaThem;
	private List<DichVu> dsTatCaDichVu;
	private DichVuDao dichVuDao;
	private ImageIcon suptractionIcon;
	private ImageIcon addIcon;
	private ImageIcon deleteIcon;
	private ImageIcon rightIcon;
	private HoaDon hoaDon;
	private JLabel lblSoPhong;
	private HoaDonDao hoaDonDao;
	private double tongTien = 0;
	private JLabel lblTongTien;
	private JButton btnQuayLai;

	private int tongTrang;
	private JButton btnDau;
	private JButton btnTru1;
	private JLabel lblPage;
	private JLabel lblTongPage;
	private JButton btnCong1;
	private JButton btnCuoi;
	private int limit = 15;
	private JButton btnTim;
	private JButton btnLamMoi;

	public DialogCapNhatDichVuTrongHoaDon(HoaDon hoaDon, Phong phong) {
		this.hoaDon = hoaDon;
		tongTien = 0;
		getContentPane().setBackground(SystemColor.control);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize(1295, 720);
		setModal(true);
		// set center
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(0, 0, 1281, 56);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u1EADp nh\u1EADt d\u1ECBch v\u1EE5 ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 5, 1282, 51);
		panel.add(lblNewLabel);

		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(650, 66, 620, 561);
		getContentPane().add(rightPanel);
		rightPanel.setLayout(null);

		JScrollPane scrollPaneRight = new JScrollPane();
		scrollPaneRight.setBounds(10, 105, 600, 415);
		rightPanel.add(scrollPaneRight);

		suptractionIcon = new ImageIcon(
				new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/minus.png")).getImage()
						.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		addIcon = new ImageIcon(new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/plus.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		deleteIcon = new ImageIcon(new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/remove.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		String[] columns = { "#", "Tên", "Đơn giá", "Đã thêm", "Thành tiền", "Thêm", "Bớt", "Xóa" };
//		Object[][] rows = { { 1, 4, 4, 4, 4, addIcon, suptractionIcon, deleteIcon } };
		Object[][] rows = {};
		modelDichVuDaThem = new DefaultTableModel(rows, columns) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 5:
					return ImageIcon.class;
				case 6:
					return ImageIcon.class;
				case 7:
					return ImageIcon.class;
				default:
					return String.class;
				}
			}
		};
		tableDichVuDaThem = new JTable(modelDichVuDaThem);
		tableDichVuDaThem.setSelectionBackground(new Color(192, 192, 192));
		// khong cho sua table
		tableDichVuDaThem.setEditingColumn(4);
		tableDichVuDaThem.setDefaultEditor(Object.class, null);
		tableDichVuDaThem.setEditingColumn(4);
		tableDichVuDaThem.setSelectionForeground(Color.BLACK);
		tableDichVuDaThem.setRowHeight(35);
		scrollPaneRight.setViewportView(tableDichVuDaThem);
		tableDichVuDaThem.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableDichVuDaThem.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableDichVuDaThem.getColumnModel().getColumn(2).setPreferredWidth(125);
		tableDichVuDaThem.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableDichVuDaThem.getColumnModel().getColumn(4).setPreferredWidth(125);
		tableDichVuDaThem.getColumnModel().getColumn(5).setPreferredWidth(30);
		tableDichVuDaThem.getColumnModel().getColumn(6).setPreferredWidth(30);
		tableDichVuDaThem.getColumnModel().getColumn(7).setPreferredWidth(30);
		tableDichVuDaThem.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableDichVuDaThem.setFont(new Font("TimesRoman", Font.PLAIN, 14));

		JLabel lblNewLabel_3_1 = new JLabel("Dịch vụ đã thêm");
		lblNewLabel_3_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_3_1.setBackground(new Color(211, 211, 211));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3_1.setBounds(10, 10, 601, 35);
		rightPanel.add(lblNewLabel_3_1);

		JLabel lblNewLabel_5_1 = new JLabel("Tổng tiền");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5_1.setBounds(302, 520, 96, 41);
		rightPanel.add(lblNewLabel_5_1);

		lblTongTien = new JLabel("900 000 000 VND");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTongTien.setBounds(404, 520, 206, 41);
		rightPanel.add(lblTongTien);

		JLabel lblNewLabel_5 = new JLabel("Số phòng: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(10, 55, 96, 35);
		rightPanel.add(lblNewLabel_5);

		lblSoPhong = new JLabel(phong != null ? phong.getMaPhong().substring(1) : "");

		lblSoPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoPhong.setBounds(116, 55, 45, 35);
		rightPanel.add(lblSoPhong);

		rightIcon = new ImageIcon(
				new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/right-arrow.png")).getImage()
						.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		String[] columnsLeft = { "Mã", "Tên", "Đơn giá", "Tồn kho", "Đơn vị tính", "Thêm" };
		Object[][] rowsleft = {};

		modelTatCaDichVu = new DefaultTableModel(rowsleft, columnsLeft) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 5:
					return ImageIcon.class;

				default:
					return String.class;
				}
			}
		};

		JScrollPane scrollPaneLeft = new JScrollPane();
		scrollPaneLeft.setEnabled(false);
		scrollPaneLeft.setBounds(10, 105, 610, 415);
		tableTatcaDichVu = new JTable(modelTatCaDichVu);
		tableTatcaDichVu.setSelectionBackground(new Color(192, 192, 192));
		// khong cho sua table
		tableTatcaDichVu.setDefaultEditor(Object.class, null);
		tableTatcaDichVu.setSelectionForeground(Color.BLACK);
		tableTatcaDichVu.setRowHeight(35);
		tableTatcaDichVu.setFont(new Font("TimesRoman", Font.PLAIN, 14));

		scrollPaneLeft.setViewportView(tableTatcaDichVu);
		tableTatcaDichVu.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableTatcaDichVu.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableTatcaDichVu.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableTatcaDichVu.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableTatcaDichVu.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableTatcaDichVu.getColumnModel().getColumn(5).setPreferredWidth(50);

		tableTatcaDichVu.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBounds(10, 66, 631, 561);
		leftPanel.add(scrollPaneLeft);
		getContentPane().add(leftPanel);

		JLabel lblNewLabel_3 = new JLabel("Tất cả dịch vụ");
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 10, 601, 35);
		leftPanel.add(lblNewLabel_3);

		btnDau = new JButton("");
		btnDau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDau.setBounds(115, 527, 60, 25);
		leftPanel.add(btnDau);
		btnDau.setIcon(new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/previousEnd.png")));
		btnDau.setFont(new Font("Arial", Font.PLAIN, 16));

		btnTru1 = new JButton("");
		btnTru1.setBounds(195, 527, 60, 25);
		leftPanel.add(btnTru1);
		btnTru1.setIcon(new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/rewind-button.png")));
		btnTru1.setFont(new Font("Arial", Font.PLAIN, 16));

		lblPage = new JLabel("1");
		lblPage.setBounds(258, 520, 35, 41);
		leftPanel.add(lblPage);
		lblPage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPage.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnCong1 = new JButton("");
		btnCong1.setBounds(330, 527, 60, 25);
		leftPanel.add(btnCong1);
		btnCong1.setIcon(new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/nextbutton.png")));
		btnCong1.setFont(new Font("Arial", Font.PLAIN, 16));

		btnCuoi = new JButton("");
		btnCuoi.setBounds(410, 527, 60, 25);
		leftPanel.add(btnCuoi);
		btnCuoi.setIcon(new ImageIcon(DialogCapNhatDichVuTrongHoaDon.class.getResource("/icon/next.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));

		tfMaDV = new JTextField();
		tfMaDV.setColumns(10);
		tfMaDV.setBounds(338, 55, 102, 35);
		leftPanel.add(tfMaDV);

		btnTim = new JButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnTim.setBackground(new Color(0, 127, 255));
		btnTim.setBounds(450, 55, 70, 35);
		leftPanel.add(btnTim);

		tfTenDV = new JTextField();
		tfTenDV.setColumns(10);
		tfTenDV.setBounds(122, 55, 102, 35);
		leftPanel.add(tfTenDV);

		JLabel lblNewLabel_5_1_1 = new JLabel("Tên dịch vụ:");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5_1_1.setBounds(10, 55, 102, 35);
		leftPanel.add(lblNewLabel_5_1_1);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Mã dịch vụ:");
		lblNewLabel_5_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5_1_1_1.setBounds(234, 55, 94, 35);
		leftPanel.add(lblNewLabel_5_1_1_1);

		lblTongPage = new JLabel("/1");
		lblTongPage.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongPage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongPage.setBounds(296, 520, 35, 41);
		leftPanel.add(lblTongPage);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLamMoi.setBorder(new LineBorder(new Color(255, 255, 255), 0, true));
		btnLamMoi.setBackground(new Color(0, 250, 154));
		btnLamMoi.setBounds(530, 55, 91, 35);
		leftPanel.add(btnLamMoi);

		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuayLai.setBackground(SystemColor.textHighlight);
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setBounds(1155, 635, 100, 40);
		getContentPane().add(btnQuayLai);

		btnQuayLai.addActionListener(this);
		tableDichVuDaThem.addMouseListener(this);
		tableTatcaDichVu.addMouseListener(this);

		dichVuDao = new DichVuDao(MainFrame.sessionFactory);
		hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
		khoiTaoDichVuCu();
		khoiTaoDuLieuDichVu();

		btnCong1.addActionListener(this);
		btnTru1.addActionListener(this);
		btnCuoi.addActionListener(this);
		btnDau.addActionListener(this);
		btnTim.addActionListener(this);
		tfTenDV.addKeyListener(this);
		btnLamMoi.addActionListener(this);

	}

	int themDichVuVaoBangDichVuDaThem(ChiTietDichVu chiTietDichVu) {
		int index = modelDichVuDaThem.getRowCount();
		dsDichVuDaThem.add(chiTietDichVu.getDichVu());

		modelDichVuDaThem.addRow(new String[] { Integer.toString(index), chiTietDichVu.getDichVu().getTenDichVu(),
				FormatCustom.chuyenDoiTienTe(chiTietDichVu.getDichVu().getDonGia()),
				Integer.toString(chiTietDichVu.getSoLuong()),
				FormatCustom.chuyenDoiTienTe(chiTietDichVu.thanhTien()) });
		modelDichVuDaThem.setValueAt(addIcon, index, 5);
		modelDichVuDaThem.setValueAt(suptractionIcon, index, 6);
		modelDichVuDaThem.setValueAt(deleteIcon, index, 7);
		tongTien += chiTietDichVu.thanhTien();

		return index;
	}

	public void khoiTaoDichVuCu() {
		dsDichVuDaThem = new ArrayList<DichVu>();
		if (hoaDon.getChiTietDichVu() == null) {
			hoaDon.setChiTietDichVu(new ArrayList<ChiTietDichVu>());
			return;
		}
		for (int i = 0; i < hoaDon.getChiTietDichVu().size(); i++) {
			themDichVuVaoBangDichVuDaThem(hoaDon.getChiTietDichVu().get(i));
		}
		capNhatThanhTien();

	}

	public void khoiTaoDuLieuDichVu() {

		xoaToanBoBangDichVu();
		int row = 0;
		String tenDV = tfTenDV.getText().trim();
		String maDV = tfMaDV.getText().trim();
		System.out.println("else " + maDV);
		if (maDV.equals("")) {
			int page = Integer.parseInt(lblPage.getText().trim());
			tongTrang = dichVuDao.tongTrang(tenDV, 1, limit);
			lblTongPage.setText("/" + tongTrang);
			dsTatCaDichVu = dichVuDao.layDanhSachDichVuTheoTenNgayTrangThai(page - 1, limit, tenDV, 1);
		} else {
			System.out.println("else");
			tongTrang = 1;
			lblPage.setText("1");
			lblTongPage.setText("/1");
			DichVu dichVu = dichVuDao.layDichVuTheoMa(maDV);
			dsTatCaDichVu = Arrays.asList(dichVu);
			if (dichVu == null) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy bất cứ dịch vụ nào có mã: " + maDV);
				return;
			}
		}
		for (DichVu dichVu : dsTatCaDichVu) {
			modelTatCaDichVu.addRow(new String[] { dichVu.getMaDV(), dichVu.getTenDichVu(),
					FormatCustom.chuyenDoiTienTe(dichVu.getDonGia()), Integer.toString(dichVu.getSoLuong()),
					dichVu.getDonViTinh() });
			modelTatCaDichVu.setValueAt(rightIcon, row++, 5);
		}

	}

	public void xoaToanBoBangDichVu() {
		for (int i = modelTatCaDichVu.getRowCount(); i > 0; i--) {
			modelTatCaDichVu.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnQuayLai)) {
			setVisible(false);
		}
		if (object.equals(btnDau)) {
			int page = Integer.parseInt(lblPage.getText());
			if (page == 1) {
				return;
			}
			lblPage.setText(Integer.toString(1));
			khoiTaoDuLieuDichVu();
			return;
		}
		if (object.equals(btnTru1)) {

			int page = Integer.parseInt(lblPage.getText());
			if (page == 1) {
				return;
			}
			lblPage.setText(Integer.toString(page - 1));

			khoiTaoDuLieuDichVu();
			return;
		}
		if (object.equals(btnCong1)) {
			int page = Integer.parseInt(lblPage.getText());
			if (page == tongTrang) {
				return;
			}
			lblPage.setText(Integer.toString(page + 1));
			khoiTaoDuLieuDichVu();
			return;
		}
		if (object.equals(btnCuoi)) {
			int page = Integer.parseInt(lblPage.getText());
			if (page == tongTrang) {
				return;
			}
			lblPage.setText(Integer.toString(tongTrang));
			khoiTaoDuLieuDichVu();
			return;
		}

		if (object.equals(btnTim)) {
			lblPage.setText("1");
			khoiTaoDuLieuDichVu();
			return;
		}
		if (object.equals(btnLamMoi)) {
			lblPage.setText("1");
			tfMaDV.setText("");
			tfTenDV.setText("");
			khoiTaoDuLieuDichVu();
			return;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object object = e.getSource();
		if (object.equals(tableTatcaDichVu)) {
			int row = tableTatcaDichVu.getSelectedRow();
			int column = tableTatcaDichVu.getSelectedColumn();

			if (column == 5) {
				// thêm dịch vụ
				DichVu dichVu = dsTatCaDichVu.get(row);
				int numberSoLuongNhap = hienDialogNhapSoLuong("Nhập số lượng muốn thêm");

				if (numberSoLuongNhap == -1) {
					return;
				}

				int indexRight = dsDichVuDaThem.indexOf(dichVu);
				if (indexRight == -1) {
					// add new product service in table right // Thêm mới dịch vụ
					if (!kiemTraSoluong(dichVu.getMaDV(), numberSoLuongNhap)) {
						return;
					}

					int quantityServiceMax = Integer.parseInt(modelTatCaDichVu.getValueAt(row, 3).toString());
					modelTatCaDichVu.setValueAt(quantityServiceMax - numberSoLuongNhap, row, 3);
					ChiTietDichVu themChiTietDichVu = new ChiTietDichVu(dichVu, hoaDon, numberSoLuongNhap);
					themDichVuVaoBangDichVuDaThem(themChiTietDichVu);
					hoaDonDao.themChiTietDichVu(themChiTietDichVu);
					capNhatThanhTien();
					return;
				}
				themSoLuongDichVu(numberSoLuongNhap, row, indexRight, dichVu);
				capNhatThanhTien();

			}
			return;
		}
		if (object.equals(tableDichVuDaThem)) {
			int row = tableDichVuDaThem.getSelectedRow();
			int column = tableDichVuDaThem.getSelectedColumn();
			DichVu dichVu = dsDichVuDaThem.get(row);
			int numberSoLuongNhap = -1;
			int rowLeft = dsTatCaDichVu.indexOf(dichVu);
			switch (column) {
			case 5:
				// Thêm
				numberSoLuongNhap = hienDialogNhapSoLuong("Nhập số lượng muốn thêm");
				if (numberSoLuongNhap == -1) {
					return;
				}
				themSoLuongDichVu(numberSoLuongNhap, rowLeft, row, dichVu);
				capNhatThanhTien();
				break;
			case 6:
				// Bớt
				numberSoLuongNhap = hienDialogNhapSoLuong("Nhập số lượng muốn bớt");
				if (numberSoLuongNhap == -1) {
					return;
				}
				giamSoLuongDichVu(numberSoLuongNhap, rowLeft, row, dichVu);
				capNhatThanhTien();
				break;
			case 7:
				// Xóa
				int xacnhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa dịch vụ không", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (xacnhan != JOptionPane.YES_OPTION) {
					return;
				}
				int quantity = Integer.parseInt(modelDichVuDaThem.getValueAt(row, 3).toString());
				xoaDichVuDaThem(quantity, rowLeft, row, dichVu);
				capNhatThanhTien();
				break;

			default:
				break;
			}
			return;
		}
	}

	public int hienDialogNhapSoLuong(String mess) {
		String soLuong = JOptionPane.showInputDialog(mess);
		if (soLuong == null) {
			return -1;
		}
		if (!soLuong.matches("[1-9]+[0-9]*")) {
			JOptionPane.showMessageDialog(this, "Số lượng thêm phải là số và không âm");
			return -1;
		}
		return Integer.parseInt(soLuong);
	}

	public boolean themSoLuongDichVu(int numberSoLuongNhap, int indexRowLeft, int indexRowRight, DichVu dichVu) {
		if (!kiemTraSoluong(dichVu.getMaDV(), numberSoLuongNhap)) {
			return false;
		}
		if (indexRowLeft != -1) {
			// constan in table left
			int quantityServiceMax = Integer.parseInt(modelTatCaDichVu.getValueAt(indexRowLeft, 3).toString());
			modelTatCaDichVu.setValueAt(quantityServiceMax - numberSoLuongNhap, indexRowLeft, 3);
		}

		tongTien += dichVu.getDonGia() * numberSoLuongNhap;
		int quantity = Integer.parseInt(modelDichVuDaThem.getValueAt(indexRowRight, 3).toString());
		int sum = numberSoLuongNhap + quantity;
		// had in table right// đã có trong table phải
		modelDichVuDaThem.setValueAt(sum, indexRowRight, 3);
		modelDichVuDaThem.setValueAt(FormatCustom.chuyenDoiTienTe(sum * dichVu.getDonGia()), indexRowRight, 4);
		dsDichVuDaThem.get(indexRowRight).setSoLuong(sum);
		// cap nhat số lượng
		hoaDonDao.themChiTietDichVu(new ChiTietDichVu(dichVu, hoaDon, sum));
		capNhatThanhTien();
		return true;
	}

	public boolean giamSoLuongDichVu(int numberSoLuongNhap, int indexRowLeft, int indexRowRight, DichVu dichVu) {

		int quantity = Integer.parseInt(modelDichVuDaThem.getValueAt(indexRowRight, 3).toString());
		int sum = quantity - numberSoLuongNhap;
		if (sum == 0) {
			xoaDichVuDaThem(quantity, indexRowLeft, indexRowRight, dichVu);
			return true;
		}
		if (sum < 0) {
			JOptionPane.showMessageDialog(this, "Số lượng giảm bạn nhập nhập lớn hơn số lượng bạn đã thêm");
			return false;
		}
		tongTien -= dichVu.getDonGia() * numberSoLuongNhap;
		if (indexRowLeft != -1) {
			// constan in table left
			int quantityServiceMax = Integer.parseInt(modelTatCaDichVu.getValueAt(indexRowLeft, 3).toString());
			modelTatCaDichVu.setValueAt(quantityServiceMax + numberSoLuongNhap, indexRowLeft, 3);
		}
		if (!dichVuDao.tangSoLuongDihVu(dichVu.getMaDV(), numberSoLuongNhap)) {
			System.err.println("tangSoLuongDihVu Loi");
			return false;
		}
		// had in table right// đã có trong table phải
		modelDichVuDaThem.setValueAt(sum, indexRowRight, 3);
		modelDichVuDaThem.setValueAt(FormatCustom.chuyenDoiTienTe(sum * dichVu.getDonGia()), indexRowRight, 4);
		dsDichVuDaThem.get(indexRowRight).setSoLuong(sum);
		hoaDonDao.themChiTietDichVu(new ChiTietDichVu(dichVu, hoaDon, sum));
		capNhatThanhTien();
		return true;
	}

	public boolean kiemTraSoluong(String maDV, int soLuong) {
		try {
			if (!dichVuDao.kiemTraSoLuongDichVu(maDV, soLuong)) {
				JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ");
				return false;
			}
			if (!dichVuDao.giamSoLuongDichVu(maDV, soLuong)) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean xoaDichVuDaThem(int quantity, int indexRowLeft, int indexRowRight, DichVu dichVu) {
		modelDichVuDaThem.removeRow(indexRowRight);
		dsDichVuDaThem.remove(dichVu);
		if (indexRowLeft != -1) {
			int quatityReal = Integer.parseInt(modelTatCaDichVu.getValueAt(indexRowLeft, 3).toString());
			modelTatCaDichVu.setValueAt(quantity + quatityReal, indexRowLeft, 3);
		}
		hoaDonDao.xoaChiTietDichVu(new ChiTietDichVu(dichVu, hoaDon, 0));
		dichVuDao.tangSoLuongDihVu(dichVu.getMaDV(), quantity);
		tongTien -= dichVu.getDonGia() * quantity;
		capNhatThanhTien();
		return true;
	}

	public void capNhatThanhTien() {
		lblTongTien.setText(FormatCustom.chuyenDoiTienTe(tongTien));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		lblPage.setText("1");
		khoiTaoDuLieuDichVu();

	}
}
