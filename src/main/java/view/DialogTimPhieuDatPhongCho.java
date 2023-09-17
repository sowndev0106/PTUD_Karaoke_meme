package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import dao.HoaDonDao;
import dao.PhieuDatPhongDao;
import dao.PhongDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.PhieuDatPhong;
import view.util.FormatCustom;

public class DialogTimPhieuDatPhongCho extends JDialog implements ActionListener, MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfSDTKhach;
	private JButton btnTim;
	private DefaultTableModel tableModel;
	private JButton btnXuatPDF;
	private JButton btnQuayLai;
	private JButton btnLamMoi;
	private PhongDao phongDao;
	private HoaDonDao hoaDonDao;
	private JButton btnHuyPhong;
	private JButton btnNhanPhong;
	private JTextField tfMaPhieuDat;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBoxTrangThai;
	private PhieuDatPhongDao phieuDatPhongDao;
	private List<PhieuDatPhong> dsPhieuDatPhong;
	private JButton btnXemPhong;
	private DialogPhieuDatPhongCho dialogPhieuDatPhongCho;

	public DialogTimPhieuDatPhongCho() {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setModal(true);
		setSize(930, 488);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 923, 53);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm phiếu đặt phòng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 913, 53);
		panel.add(lblNewLabel);

		tfSDTKhach = new JTextField();
		tfSDTKhach.setBounds(390, 75, 130, 30);
		getContentPane().add(tfSDTKhach);
		tfSDTKhach.setColumns(10);

		btnTim = new JButton("T\u00ECm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.setBackground(new Color(0, 0, 128));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBounds(545, 75, 100, 30);
		getContentPane().add(btnTim);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 896, 274);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Mã phiếu đặt", "Mã phòng",
				"SĐT Khách", "Thời gian lập phiếu", "Thời gian nhận phòng", "Trạng thái" }));
		scrollPane.setViewportView(table);
		btnXuatPDF = new JButton("Xuất PDF");
		btnXuatPDF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXuatPDF.setForeground(Color.WHITE);
		btnXuatPDF.setBackground(new Color(210, 105, 30));
		btnXuatPDF.setBounds(507, 411, 125, 30);
		getContentPane().add(btnXuatPDF);

		btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuayLai.setBackground(new Color(0, 0, 255));
		btnQuayLai.setBounds(10, 411, 111, 30);
		getContentPane().add(btnQuayLai);

		JLabel lblNewLabel_1_1 = new JLabel("SĐT Khách");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(280, 75, 115, 30);
		getContentPane().add(lblNewLabel_1_1);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBackground(new Color(0, 128, 128));
		btnLamMoi.setBounds(133, 411, 111, 30);
		getContentPane().add(btnLamMoi);

		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(35);

		comboBoxTrangThai = new JComboBox<String>();
		comboBoxTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxTrangThai
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả", "Còn hiệu lực", "Hết Hiệu lực" }));
		comboBoxTrangThai.setBounds(763, 75, 143, 30);
		comboBoxTrangThai.setSelectedIndex(1);
		getContentPane().add(comboBoxTrangThai);

		btnNhanPhong = new JButton("Nhận phòng");
		btnNhanPhong.setForeground(Color.WHITE);
		btnNhanPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNhanPhong.setBackground(new Color(34, 139, 34));
		btnNhanPhong.setBounds(777, 411, 125, 30);
		getContentPane().add(btnNhanPhong);

		btnHuyPhong = new JButton("Hủy phòng");
		btnHuyPhong.setForeground(Color.WHITE);
		btnHuyPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuyPhong.setBackground(new Color(220, 20, 60));
		btnHuyPhong.setBounds(642, 411, 125, 30);
		getContentPane().add(btnHuyPhong);

		JLabel lblNewLabel_1_1_1 = new JLabel("Trạng thái");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(662, 75, 105, 30);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Mã phiếu đặt");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_2.setBounds(10, 75, 120, 30);
		getContentPane().add(lblNewLabel_1_1_2);

		tfMaPhieuDat = new JTextField();
		tfMaPhieuDat.setColumns(10);
		tfMaPhieuDat.setBounds(135, 75, 130, 30);
		getContentPane().add(tfMaPhieuDat);

		btnXemPhong = new JButton("Xem phòng");
		btnXemPhong.setForeground(Color.WHITE);
		btnXemPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXemPhong.setBackground(new Color(30, 144, 255));
		btnXemPhong.setBounds(372, 411, 125, 30);
		getContentPane().add(btnXemPhong);

		btnTim.addActionListener(this);
		btnHuyPhong.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnQuayLai.addActionListener(this);
		btnXuatPDF.addActionListener(this);
		btnTim.addActionListener(this);
		comboBoxTrangThai.addActionListener(this);
		btnXemPhong.addActionListener(this);
		tfMaPhieuDat.addKeyListener(this);
		tfSDTKhach.addKeyListener(this);

		phongDao = new PhongDao(MainFrame.sessionFactory);
		hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
		dialogPhieuDatPhongCho = new DialogPhieuDatPhongCho();
		phieuDatPhongDao = new PhieuDatPhongDao(MainFrame.sessionFactory);

	}

	private void xoaBang() {
		for (int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(0);
		}
	}

	private void themDuLieuVaoBang() {
		String maPhieuDat = tfMaPhieuDat.getText().trim();
		String sdtKhach = tfSDTKhach.getText().trim();
		int tinhTrang = comboBoxTrangThai.getSelectedIndex();
		dsPhieuDatPhong = phieuDatPhongDao.layDanhSachPhieuDatPhong(maPhieuDat.length() == 0 ? "" : "PDP" + maPhieuDat,
				sdtKhach, tinhTrang);
		xoaBang();
		if (dsPhieuDatPhong == null || dsPhieuDatPhong.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không có phiếu đặt phòng");
			return;
		}

		for (PhieuDatPhong p : dsPhieuDatPhong) {
			tableModel.addRow(new String[] { p.getMaPDP().substring(3), p.getPhong().getMaPhong(),
					p.getKhachHang().getSoDienThoai(), FormatCustom.dinhDanhThoiGian(p.getThoiGianDangKyDatPhong()),
					FormatCustom.dinhDanhThoiGian(p.getThoiGianNhanPhong()),
					!p.isTinhTrang() ? "Hết hiệu lực" : "Còn hiệu lực" });
		}

//		{ "Mã phiếu đặt", "Mã phòng",
//			"SĐT Khách", "Thời gian lập phiếu", "Thời gian nhận phòng", "Trạng thái" }));
	}

	public void khoiTao() {

		tfMaPhieuDat.setText("");
		tfSDTKhach.setText("");
		comboBoxTrangThai.setSelectedIndex(1);
		themDuLieuVaoBang();
		setVisible(true);
	}

	public static void main(String[] args) {

		new DialogTimPhieuDatPhongCho().setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object == btnLamMoi) {
			khoiTao();
			return;
		}
		if (object == comboBoxTrangThai) {
			themDuLieuVaoBang();
			return;
		}
		if (object == btnTim) {
			if (!tfMaPhieuDat.getText().trim().equals("") || !tfSDTKhach.getText().trim().equals(""))
				comboBoxTrangThai.setSelectedIndex(0);
			return;
		}
		if (object == btnQuayLai) {
			setVisible(false);
			return;
		}

		// below need select table
		int indexRow = table.getSelectedRow();
		if (indexRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu đặt phòng");
			return;
		}
		PhieuDatPhong phieuDatPhong = dsPhieuDatPhong.get(indexRow);
		if (object == btnHuyPhong) {
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
			if (phieuDatPhong.getPhong().getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongTam)) {
				phongDao.capNhatTrangThaiPhong(phieuDatPhong.getPhong().getMaPhong(), MainFrame.maPhongBan);
			} else {
				phongDao.capNhatTrangThaiPhong(phieuDatPhong.getPhong().getMaPhong(), MainFrame.maPhongTrong);
			}
			JOptionPane.showMessageDialog(this,
					"Hủy phiếu đặt phòng " + phieuDatPhong.getMaPDP().substring(3) + " thành công ");
			themDuLieuVaoBang();
			return;
		}
		if (object == btnNhanPhong) {
			int xacnhan = JOptionPane.showConfirmDialog(this, "Xác nhận nhận phòng "
					+ phieuDatPhong.getPhong().getMaPhong() + " của: " + phieuDatPhong.getKhachHang().getHoTen(),
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (xacnhan != JOptionPane.YES_OPTION) {
				return;
			}
			HoaDon hoaDon = new HoaDon(MainFrame.nhanVien, MainFrame.khuyenMaiMacDinh, phieuDatPhong.getKhachHang());
			hoaDon.setKhachHang(phieuDatPhong.getKhachHang());
			hoaDon.setChiTietHoaDon(Arrays.asList(new ChiTietHoaDon(phieuDatPhong.getPhong(), hoaDon, 0)));
			phongDao.capNhatTrangThaiPhong(phieuDatPhong.getPhong().getMaPhong(), MainFrame.maPhongBan);
			phieuDatPhongDao.huyPhieuDatPhong(phieuDatPhong.getMaPDP());
			if (hoaDonDao.themHoaDon(hoaDon)) {
				System.out.println("add Bill success");
			} else {
				System.out.println("add Bill fail");
			}
			JOptionPane.showMessageDialog(this, "Nhận phòng " + phieuDatPhong.getPhong().getMaPhong() + " thành công");
			themDuLieuVaoBang();
			return;
		}
		System.out.println("o");
		if (object == btnXuatPDF) {
			System.out.println("Xuat");
			dialogPhieuDatPhongCho.khoiTao(phieuDatPhong);
			dialogPhieuDatPhongCho.xuatFile();

			return;
		}
		if (object == btnXemPhong) {
			setVisible(false);
			MainFrame.disableAllPanel();
			MainFrame.chiTietPhongPanel.khoiTao(phieuDatPhong.getPhong().getMaPhong());
			return;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().equals(tfMaPhieuDat) || e.getSource().equals(tfSDTKhach)) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnTim.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}