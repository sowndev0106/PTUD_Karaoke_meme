package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.HoaDonDao;
import dao.PhongDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import view.util.FormatCustom;

public class DialogChuyenPhong extends JDialog implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfMaPhong;
	private JLabel lblSoPhong;
	private JButton btnQuayLai;
	private JButton btnChuyen;
	private JButton btnTim;
	private JLabel lblSoPhongChuyen;
	private JTable table;
	private Phong phong;
	private PhongDao phongDao;
	private List<Phong> dsPhong;
	private DefaultTableModel tableModel;
	private JButton btnLamMoi;
	private HoaDonDao hoaDonDao;
	private boolean tinhTrang = false;
	private String getPhongChuyen;

	public DialogChuyenPhong() {
		setModal(true);
		setSize(683, 488);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 727, 53);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Chuy\u1EC3n ph\u00F2ng\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 727, 53);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ph\u00F2ng hi\u1EC7n t\u1EA1i: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 77, 142, 30);
		getContentPane().add(lblNewLabel_1);

		lblSoPhong = new JLabel("P009");
		lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSoPhong.setBounds(151, 77, 65, 30);
		getContentPane().add(lblSoPhong);

		tfMaPhong = new JTextField();
		tfMaPhong.setBounds(442, 77, 96, 30);
		getContentPane().add(tfMaPhong);
		tfMaPhong.setColumns(10);

		btnTim = new JButton("T\u00ECm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.setBackground(new Color(0, 0, 128));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBounds(548, 77, 111, 30);
		getContentPane().add(btnTim);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 131, 649, 270);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã phòng", "Trạng thái", "Loại phòng", "Số người", "Giá tiền mỗi giờ" }));
		scrollPane.setViewportView(table);
		btnChuyen = new JButton("Chuy\u1EC3n");
		btnChuyen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChuyen.setForeground(Color.WHITE);
		btnChuyen.setBackground(new Color(50, 205, 50));
		btnChuyen.setBounds(548, 411, 111, 30);
		getContentPane().add(btnChuyen);

		btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuayLai.setBackground(new Color(0, 0, 255));
		btnQuayLai.setBounds(10, 411, 111, 30);
		getContentPane().add(btnQuayLai);

		JLabel lblNewLabel_1_1 = new JLabel("Phòng số");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(336, 77, 96, 30);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(DialogChuyenPhong.class.getResource("/icon/forward-button.png")));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(218, 77, 26, 30);
		getContentPane().add(lblNewLabel_2_1);

		lblSoPhongChuyen = new JLabel("P009");
		lblSoPhongChuyen.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSoPhongChuyen.setBounds(243, 77, 65, 30);
		getContentPane().add(lblSoPhongChuyen);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBackground(new Color(0, 128, 128));
		btnLamMoi.setBounds(133, 411, 111, 30);
		getContentPane().add(btnLamMoi);
		phongDao = new PhongDao(MainFrame.sessionFactory);
		hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
		btnQuayLai.addActionListener(this);
		btnChuyen.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(35);
	}

	public void xoaTatCaHang() {
		for (int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(0);
		}
	}

	public void themDuLieuBang() {
		String maPhong = tfMaPhong.getText().trim();
		xoaTatCaHang();
		if (!maPhong.equals("")) {
			Phong phong = phongDao.layThongTinPhongQuaMa("P" + maPhong);
			if (phong == null) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy phòng nào có mã phòng: " + maPhong);
				return;
			}
			dsPhong = Arrays.asList(phong);

		} else {
			dsPhong = phongDao.layDanhSachPhongDuocPhepDat();
		}

		// Them Du Lieu
		dsPhong.forEach(e -> {
			tableModel.addRow(new String[] { e.getMaPhong().substring(1), e.getTrangThaiPhong().getTenTrangThaiPhong(),
					e.getLoaiPhong().getTenLoaiPhong(), Integer.toString(e.getSoNguoi()),
					FormatCustom.chuyenDoiTienTe(e.getLoaiPhong().getGiaTien()) });
		});

	}

	public void khoiTao(Phong phong) {
		btnLamMoi.doClick();
		tinhTrang = false;
		this.phong = phong;
		lblSoPhong.setText(phong.getMaPhong().substring(1));
		lblSoPhongChuyen.setText("");
		themDuLieuBang();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == btnChuyen) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng chuyển");
				return;
			}
			Phong phongChuyen = dsPhong.get(row);
			int xacnhan = JOptionPane.showConfirmDialog(this,
					"Bạn có chắc chuyển từ phòng " + phong.getMaPhong() + " Sang phòng " + phongChuyen.getMaPhong(),
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (xacnhan != JOptionPane.YES_OPTION) {
				return;
			}
			HoaDon hoaDon = hoaDonDao.layHoaDonMoiNhatTheoPhong(phong.getMaPhong());
			@SuppressWarnings("unused")
			int thoiLuongTong = hoaDon.capNhatThoiLuong(phong.getMaPhong());

			ChiTietHoaDon chiTietHoaDonChuyen = new ChiTietHoaDon(phongChuyen, hoaDon, 0);
			ChiTietHoaDon chiTietHoaDonHienTai = null;
			boolean constan = false;
			for (ChiTietHoaDon chiTietHoaDon : hoaDon.getChiTietHoaDon()) {
				System.err.println(chiTietHoaDon.getPhong().getMaPhong() + chiTietHoaDon.getThoiLuong());
				if (chiTietHoaDon.getPhong().getMaPhong().equals(chiTietHoaDonChuyen.getPhong().getMaPhong())) {
					constan = true;
				}
				if (chiTietHoaDon.getPhong().getMaPhong().equals(phong.getMaPhong())) {
					chiTietHoaDonHienTai = chiTietHoaDon;
				}
			}
			if (!constan) {
				hoaDonDao.themHoacCapNhatChiTietHoaDon(chiTietHoaDonChuyen);
				hoaDon.getChiTietHoaDon().add(chiTietHoaDonChuyen);
			}
			hoaDonDao.themHoacCapNhatChiTietHoaDon(chiTietHoaDonHienTai);

			if (phongChuyen.getTrangThaiPhong().getMaTTP().equalsIgnoreCase(MainFrame.maPhongCho)) {
				phongDao.capNhatTrangThaiPhong(phongChuyen.getMaPhong(), MainFrame.maPhongTam);
			} else {
				phongDao.capNhatTrangThaiPhong(phongChuyen.getMaPhong(), MainFrame.maPhongBan);
			}

			if (phong.getTrangThaiPhong().getMaTTP().equalsIgnoreCase(MainFrame.maPhongTam)) {
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongCho);
			} else {
				phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongTrong);
			}
			JOptionPane.showMessageDialog(this, "Chuyển phòng thành công");
			getPhongChuyen = phongChuyen.getMaPhong().substring(1);
			tinhTrang = true;
			setVisible(false);
			return;
		}
		if (object == btnLamMoi) {
			tfMaPhong.setText("");
			themDuLieuBang();
			return;
		}
		if (object == btnTim) {
			themDuLieuBang();
			return;
		}
		if (object == btnQuayLai) {
			setVisible(false);
			return;
		}

	}

	public boolean getTinhTrang() {
		return tinhTrang;
	}

	public String getPhongChuyen() {
		return getPhongChuyen;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		lblSoPhongChuyen.setText(tableModel.getValueAt(table.getSelectedRow(), 0).toString());

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
