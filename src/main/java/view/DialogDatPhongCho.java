package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.PhieuDatPhongDao;
import dao.PhongDao;
import entity.KhachHang;
import entity.PhieuDatPhong;
import entity.Phong;

public class DialogDatPhongCho extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnDatPhong;
	private JButton btnHuy;
	private JTextField tfSDTKhach;
	@SuppressWarnings("unused")
	private HoaDonDao hoaDonDao;
	private KhachHangDao khachHangDao;
	private JLabel lblTenKhach;
	private JLabel lblIconKiemTraSDT;
	private JLabel lblsoPhong;
	private JButton btnKiemTraSDTKKhach;
	private JComboBox<String> cbPhut;
	private JComboBox<String> cbGio;
	private JRadioButton radioNgayMai;
	private JRadioButton radioHomNay;
	private PhongDao phongDao;
	private PhieuDatPhongDao phieuDatPhongDao;
	private Phong phong;
	boolean trangThai = false;
	private DefaultComboBoxModel<String> gioModel;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel phutModel;
	private DialogPhieuDatPhongCho dialogPhieuDatPhongCho;
	private JCheckBox cbInPhieuDat;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DialogDatPhongCho(Phong phong) {
		setModal(true);
		this.phong = phong;
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 486, 59);
		setSize(500, 425);
		getContentPane().add(panel);
		setLocationRelativeTo(null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u0110\u1EB7t ph\u00F2ng ch\u1EDD");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 476, 59);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ngày nhận phòng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(33, 217, 157, 35);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Giờ nhận phòng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(33, 267, 157, 35);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Giờ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(264, 267, 57, 35);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Phút");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(383, 267, 55, 35);
		getContentPane().add(lblNewLabel_1_1_1_1);

		cbGio = new JComboBox<String>();
		cbGio.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbGio.setModel(gioModel = new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
		cbGio.setBounds(200, 267, 65, 35);
		getContentPane().add(cbGio);

		cbPhut = new JComboBox<String>();
		cbPhut.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbPhut.setModel(phutModel = new DefaultComboBoxModel(
				new String[] { "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
		cbPhut.setBounds(319, 267, 65, 35);
		getContentPane().add(cbPhut);

		btnDatPhong = new JButton("Đặt phòng");
		btnDatPhong.setBackground(new Color(65, 105, 225));
		btnDatPhong.setForeground(new Color(255, 255, 255));
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDatPhong.setBounds(314, 326, 135, 35);
		getContentPane().add(btnDatPhong);

		btnHuy = new JButton("Hủy");
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBackground(new Color(128, 128, 128));
		btnHuy.setBounds(33, 326, 78, 35);
		getContentPane().add(btnHuy);

		cbInPhieuDat = new JCheckBox("In phiếu đặt");
		cbInPhieuDat.setSelected(true);
		cbInPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbInPhieuDat.setBounds(200, 331, 106, 21);
		getContentPane().add(cbInPhieuDat);

		tfSDTKhach = new JTextField();
		tfSDTKhach.setColumns(10);
		tfSDTKhach.setBounds(200, 128, 150, 35);
		getContentPane().add(tfSDTKhach);

		lblIconKiemTraSDT = new JLabel("");
		lblIconKiemTraSDT.setBounds(177, 128, 22, 33);
		getContentPane().add(lblIconKiemTraSDT);

		JLabel lblStKhach = new JLabel("SĐT Khách");
		lblStKhach.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStKhach.setBounds(33, 128, 157, 35);
		getContentPane().add(lblStKhach);

		btnKiemTraSDTKKhach = new JButton("Kiểm tra");
		btnKiemTraSDTKKhach.setBounds(358, 128, 90, 35);
		getContentPane().add(btnKiemTraSDTKKhach);

		lblTenKhach = new JLabel("");
		lblTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenKhach.setBounds(200, 170, 220, 35);
		getContentPane().add(lblTenKhach);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Tên Khách");
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(33, 172, 157, 35);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("Phòng số");
		lblNewLabel_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_1_1_1_1_1.setBounds(33, 83, 157, 35);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1);

		lblsoPhong = new JLabel("001");
		lblsoPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblsoPhong.setBounds(200, 81, 220, 35);
		getContentPane().add(lblsoPhong);

		radioHomNay = new JRadioButton("Hôm nay");
		radioHomNay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioHomNay.setSelected(true);
		radioHomNay.setBounds(203, 227, 103, 21);
		getContentPane().add(radioHomNay);

		radioNgayMai = new JRadioButton("Ngày mai");
		radioNgayMai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioNgayMai.setBounds(346, 227, 103, 21);
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioNgayMai);
		bg.add(radioHomNay);
		getContentPane().add(radioNgayMai);
		try {
			khachHangDao = new KhachHangDao(MainFrame.sessionFactory);
			phongDao = new PhongDao(MainFrame.sessionFactory);
			phieuDatPhongDao = new PhieuDatPhongDao(MainFrame.sessionFactory);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		btnKiemTraSDTKKhach.addActionListener(this);
		btnDatPhong.addActionListener(this);
		btnHuy.addActionListener(this);
		lblsoPhong.setText(phong.getMaPhong().substring(1));
		radioNgayMai.addActionListener(this);
		radioHomNay.addActionListener(this);
		thietLapGio();
		dialogPhieuDatPhongCho = new DialogPhieuDatPhongCho();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void thietLapGio() {
		int gio = 1;
		int phut = 0;
		if (radioHomNay.isSelected()) {
			Date date = new Date();
			gio = date.getHours();
			phut = date.getMinutes() % 5 == 0 ? date.getMinutes() : ((date.getMinutes() / 5) * 5) + 5;
			if (phut == 60) {
				gio += 1;
				phut = 5;
			}
		}
		if (gio < 8) {
			gio = 8;
		}
		gioModel.removeAllElements();
		phutModel.removeAllElements();
		for (int i = gio; i < 23; i++) {
			gioModel.addElement(i + "");
		}
		for (int i = phut; i < 60; i += 5) {
			phutModel.addElement(i + "");
		}

	}

	public KhachHang kiemTraSDTKhach() {
		String sdt = tfSDTKhach.getText().toString();
		if (sdt.trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại Khách");
			lblIconKiemTraSDT.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove.png")));
			tfSDTKhach.selectAll();
			tfSDTKhach.requestFocus();
			return null;
		}
		if (!sdt.matches(
				"(^(03)[2-9]\\d{7})|(^(07)[06-9]\\d{7})|(^(08)[1-5]\\d{7})|(^(056)\\d{7})|(^(058)\\d{7})|(^(059)\\d{7})|(^(09)[0-46-9]\\d{7})")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không đúng địng dạng");
			tfSDTKhach.selectAll();
			lblIconKiemTraSDT.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove1.png")));
			tfSDTKhach.requestFocus();
			return null;
		}
		KhachHang KhachHang = khachHangDao.layKhachHangTheoSDT(sdt);
		if (KhachHang == null) {
			int xacNhan = JOptionPane.showConfirmDialog(this,
					"Khách hàng không có trong hệ thống, Bạn có muốn thêm khách hàng không", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			lblIconKiemTraSDT.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove1.png")));
			if (xacNhan == JOptionPane.YES_OPTION) {
				DialogThemKhachHang dialogThemKhachHang = new DialogThemKhachHang(sdt);
				dialogThemKhachHang.setVisible(true);
				if (DialogThemKhachHang.khachHang == null) {
					dialogThemKhachHang.dispose();
					return null;
				}
				dialogThemKhachHang.dispose();
				KhachHang = DialogThemKhachHang.khachHang;
			}
		}
		lblTenKhach.setText(KhachHang.getHoTen());
		lblIconKiemTraSDT.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/true.png")));
		return KhachHang;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnKiemTraSDTKKhach)) {
			if (kiemTraSDTKhach() == null) {
				lblIconKiemTraSDT.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/remove1.png")));
			}
			return;
		}
		if (object.equals(btnDatPhong)) {
			KhachHang khachHang = kiemTraSDTKhach();
			int gio = Integer.parseInt(cbGio.getSelectedItem().toString());
			int phut = Integer.parseInt(cbPhut.getSelectedItem().toString());
			Date date = new Date();
			if (radioHomNay.isSelected()) {
				if (gio < date.getDay() || (gio == date.getHours() && phut < date.getMinutes())) {
					JOptionPane.showMessageDialog(this, "Thời gian phải trước thời gian hiện tại");
					return;
				}
			} else {
				// add one day
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DATE, 1);
				date = c.getTime();
			}
			date.setMinutes(phut);
			date.setHours(gio);
			if (khachHang == null)
				return;
			int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn đặt phòng chờ không ", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacNhan != JOptionPane.YES_OPTION) {
				return;
			}
			PhieuDatPhong phieuDatPhong = new PhieuDatPhong(khachHang, MainFrame.nhanVien, phong, date);
			if (!phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongCho)
					|| !phieuDatPhongDao.themPhieuDatPhong(phieuDatPhong)) {
				JOptionPane.showMessageDialog(this, "Đặt phòng KHÔNG thành công");
				setVisible(false);
				dispose();
				return;
			}
			if (cbInPhieuDat.isSelected()) {
				dialogPhieuDatPhongCho.khoiTao(phieuDatPhong);
				dialogPhieuDatPhongCho.xuatFile();

			} else
				JOptionPane.showMessageDialog(this, "Đặt phòng thành công");

			trangThai = true;
			setVisible(false);
			dispose();
		}
		if (object.equals(btnHuy)) {
			setVisible(false);
			dispose();
		}
		if (object.equals(radioHomNay) || object.equals(radioNgayMai)) {
			thietLapGio();
		}
	}
}
