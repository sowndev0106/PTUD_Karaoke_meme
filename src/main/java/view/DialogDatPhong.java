package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.PhieuDatPhongDao;
import dao.PhongDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import view.util.FormatCustom;

public class DialogDatPhong extends JDialog implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private JTextField tfSDTKhach;
	private Phong phong;
	private JLabel lblLoaiPhong;
	private JButton btnKiemTraSDTKKhach;
	private JLabel lblIconKiemTraSDT;
	private JLabel lblTenKhach;
	private JButton btnXacNhan;
	private HoaDonDao hoaDonDao;
	private KhachHangDao khachHangDao;
	private KhachHang khachHang;
	private HoaDon hoaDon;
	private PhieuDatPhongDao phieuDatPhongDao;
	private PhongDao phongDao;
	private PhieuDatPhong phieuDatPhong;
	private JLabel lblSoPhong;
	private JLabel lblSoNguoi;
	private JLabel lblGiaTien;
	private JLabel lblTrangThaiPhong;

	public DialogDatPhong() {
		setModal(true);

		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 209, 204));
		panel.setBounds(0, 0, 612, 50);
		setSize(626, 412);
		getContentPane().add(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Đặt phòng");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 611, 50);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);

		lblLoaiPhong = new JLabel("Phòng VIP");
		lblLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoaiPhong.setBounds(183, 158, 136, 30);
		getContentPane().add(lblLoaiPhong);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Loại phòng:");
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(41, 158, 128, 30);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("SĐT Khách:");
		lblNewLabel_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1_1_1_1.setBounds(41, 215, 112, 30);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1);

		lblIconKiemTraSDT = new JLabel("");
		lblIconKiemTraSDT.setIcon(new ImageIcon(DialogDatPhong.class.getResource("/icon/check.png")));
		lblIconKiemTraSDT.setBounds(163, 215, 16, 30);
		getContentPane().add(lblIconKiemTraSDT);

		tfSDTKhach = new JTextField();
		tfSDTKhach.setColumns(10);
		tfSDTKhach.setBounds(182, 215, 267, 30);
		getContentPane().add(tfSDTKhach);

		btnKiemTraSDTKKhach = new JButton("Kiểm tra");
		btnKiemTraSDTKKhach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnKiemTraSDTKKhach.setBounds(459, 215, 116, 30);
		getContentPane().add(btnKiemTraSDTKKhach);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1_1 = new JLabel("Tên khách:");
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1_1_1_1_1.setBounds(41, 269, 99, 25);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1_1);

		btnXacNhan = new JButton("Đặt phòng");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXacNhan.setBackground(new Color(0, 128, 0));
		btnXacNhan.setForeground(new Color(255, 255, 255));
		btnXacNhan.setBounds(468, 319, 107, 35);
		getContentPane().add(btnXacNhan);

		lblTenKhach = new JLabel("Nguyễn Hoàng Khánh");
		lblTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenKhach.setBounds(183, 269, 283, 25);
		getContentPane().add(lblTenKhach);

		lblSoNguoi = new JLabel("5 Người");
		lblSoNguoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoNguoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSoNguoi.setBounds(447, 113, 128, 25);
		getContentPane().add(lblSoNguoi);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_2 = new JLabel("Số người:");
		lblNewLabel_1_1_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1_1_1_2.setBounds(349, 113, 99, 25);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_2);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_2_1 = new JLabel("Số phòng: ");
		lblNewLabel_1_1_1_1_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_1_1_1_1_1_1_1_1_2_1.setBounds(228, 73, 116, 25);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_2_1);

		lblSoPhong = new JLabel("001");
		lblSoPhong.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblSoPhong.setBounds(354, 73, 59, 25);
		getContentPane().add(lblSoPhong);

		lblGiaTien = new JLabel("100.000 VND");
		lblGiaTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGiaTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGiaTien.setBounds(447, 158, 128, 30);
		getContentPane().add(lblGiaTien);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_3 = new JLabel("Giá tiền:");
		lblNewLabel_1_1_1_1_1_1_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1_1_1_3.setBounds(349, 158, 94, 30);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_3);

		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnQuayLi.setForeground(Color.WHITE);
		btnQuayLi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuayLi.setBackground(SystemColor.textHighlight);
		btnQuayLi.setBounds(41, 319, 99, 35);
		getContentPane().add(btnQuayLi);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1_2_1_1 = new JLabel("Trạng thái:");
		lblNewLabel_1_1_1_1_1_1_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1_1_1_2_1_1.setBounds(41, 113, 99, 25);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_2_1_1);

		lblTrangThaiPhong = new JLabel("Phòng trống");
		lblTrangThaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrangThaiPhong.setBounds(183, 113, 136, 25);
		getContentPane().add(lblTrangThaiPhong);

		JLabel lblNewLabel_1 = new JLabel("|");
		lblNewLabel_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(315, 95, 45, 93);
		getContentPane().add(lblNewLabel_1);

		hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
		khachHangDao = new KhachHangDao(MainFrame.sessionFactory);
		phieuDatPhongDao = new PhieuDatPhongDao(MainFrame.sessionFactory);
		phongDao = new PhongDao(MainFrame.sessionFactory);

		btnKiemTraSDTKKhach.addActionListener(this);
		btnXacNhan.addActionListener(this);
		tfSDTKhach.addKeyListener(this);

	}

	@SuppressWarnings("deprecation")
	public void khoiTao(Phong phong) {
		this.phong = phong;
		phieuDatPhong = null;
		hoaDon = null;
		lblTenKhach.setText("");
		tfSDTKhach.setText("");
		lblSoPhong.setText(phong.getMaPhong().substring(1));
		lblSoNguoi.setText(phong.getSoNguoi() + " người");
		lblGiaTien.setText(FormatCustom.chuyenDoiTienTe(phong.getLoaiPhong().getGiaTien()));
		lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
		lblTrangThaiPhong.setText(phong.getTrangThaiPhong().getTenTrangThaiPhong());
		lblIconKiemTraSDT.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/check.png")));
		if (phong.getTrangThaiPhong().getMaTTP().equals(MainFrame.maPhongCho)) {
			Date now = new Date();
			phieuDatPhong = phieuDatPhongDao.layPhieuDatPhongMoiNhatTheoPhong(phong.getMaPhong());
			if (now.getDay() == phieuDatPhong.getThoiGianNhanPhong().getDay()
					&& now.getHours() >= phieuDatPhong.getThoiGianNhanPhong().getHours() - 2) {
				JOptionPane.showMessageDialog(this,
						"Hiện tại bạn KHÔNG được phép đặt phòng này do có khách sắp tới nhận phòng");
				this.setVisible(false);
				hoaDon = null;
				phieuDatPhong = null;
				phong = null;
				return;
			}
		}
		this.setVisible(true);
	}

	public boolean kiemTraSDTKhach() {
		String sdt = tfSDTKhach.getText().toString();
		if (sdt.trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại Khách");
			tfSDTKhach.selectAll();
			tfSDTKhach.requestFocus();
			return false;
		}
		if (!sdt.matches(
				"(^(03)[2-9]\\d{7})|(^(07)[06-9]\\d{7})|(^(08)[1-5]\\d{7})|(^(056)\\d{7})|(^(058)\\d{7})|(^(059)\\d{7})|(^(09)[0-46-9]\\d{7})")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không đúng địng dạng");
			tfSDTKhach.selectAll();
			tfSDTKhach.requestFocus();
			return false;
		}
		khachHang = khachHangDao.layKhachHangTheoSDT(sdt);
		if (khachHang == null) {
			int xacNhan = JOptionPane.showConfirmDialog(this,
					"Khách hàng không có trong hệ thống, Bạn có muốn thêm khách hàng không", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (xacNhan == JOptionPane.YES_OPTION) {
				DialogThemKhachHang dialogThemKhachHang = new DialogThemKhachHang(sdt);
				dialogThemKhachHang.setVisible(true);
				if (DialogThemKhachHang.khachHang == null) {
					dialogThemKhachHang.dispose();
					return false;
				}
				dialogThemKhachHang.dispose();
				khachHang = DialogThemKhachHang.khachHang;
			}
		}
		lblTenKhach.setText(khachHang.getHoTen());
		lblIconKiemTraSDT.setIcon(new ImageIcon(ChiTietPhongPanel.class.getResource("/icon/true.png")));
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(btnKiemTraSDTKKhach)) {
			kiemTraSDTKhach();
			return;
		}
		if (object.equals(btnXacNhan)) {
			if (!kiemTraSDTKhach()) {
				return;
			}
			int n = JOptionPane.showConfirmDialog(null, "Xác nhận đặt phòng " + phong.getMaPhong().substring(1),
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				// đặt phòng tạm
				if (phieuDatPhong != null) {
					@SuppressWarnings("unused")
					Date now = new Date();
					JOptionPane.showMessageDialog(this, "Vui lòng thông báo cho khách TRẢ PHÒNG trước "
							+ FormatCustom.dinhDanhThoiGian(phieuDatPhong.getThoiGianNhanPhong()));
					phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongTam, "Phòng tạm"));
					phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongTam);
				} else {
					phong.setTrangThaiPhong(new TrangThaiPhong(MainFrame.maPhongBan, "Phòng Đang sử dụng"));
					System.out.println(phong.getTrangThaiPhong());
					phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), MainFrame.maPhongBan);
				}

				hoaDon = new HoaDon(MainFrame.nhanVien, MainFrame.khuyenMaiMacDinh, khachHang);
				hoaDon.setChiTietHoaDon(Arrays.asList(new ChiTietHoaDon(phong, hoaDon, 0)));
				if (hoaDonDao.themHoaDon(hoaDon)) {
					System.out.println("add Bill success");
					setVisible(false);
					dispose();
				} else {
					System.out.println("add Bill fail");
					hoaDon = null;
				}
			}
		}
	}

	public HoaDon getHoaDon() {

		return hoaDon;
	}

	public Phong getPhong() {
		return phong;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(tfSDTKhach)) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnKiemTraSDTKKhach.doClick();
				System.out.println(lblIconKiemTraSDT.getIcon().toString());
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
