package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dao.KhuyenMaiDao;
import entity.KhuyenMai;

public class DialogSuaKhuyenMai extends JDialog implements ActionListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMoTa;
	private JTextField txtChietKhau;
	private JTextField txtMaGiamGia;
	private JTextField txtSoLuong;
	private JButton btnHuy;
	private KhuyenMaiDao khuyenMaiDao;
	private KhuyenMai khuyenMai;
	private JTextField textField;
	private JDateChooser dateNgayBatDau;
	private JDateChooser dateNgayHetHan;
	private JButton btnThuHi;
	private JButton btnLamMoi;
	private JButton btnCapNhat;

	/**
	 * Create the dialog.
	 */
	public DialogSuaKhuyenMai(String maGiamGia) {
		khuyenMaiDao = new KhuyenMaiDao(MainFrame.sessionFactory);
		khuyenMai = khuyenMaiDao.layKhuyenMaiTheoMa(maGiamGia);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setModal(true);
		setBounds(100, 100, 850, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		JLabel lblThmKhchHng = new JLabel("Cập nhập khuyến mãi");
		lblThmKhchHng.setOpaque(true);
		lblThmKhchHng.setHorizontalTextPosition(SwingConstants.CENTER);
		lblThmKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmKhchHng.setForeground(Color.WHITE);
		lblThmKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblThmKhchHng.setBackground(new Color(72, 209, 204));
		lblThmKhchHng.setBounds(0, 0, 836, 70);
		contentPanel.add(lblThmKhchHng);

		JLabel lblNewLabel_2 = new JLabel("Mô tả:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(30, 100, 131, 35);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_2 = new JLabel("MÃ giảm giá");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(28, 158, 131, 34);
		contentPanel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Chiết khấu");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(30, 219, 115, 35);
		contentPanel.add(lblNewLabel_2_3);

		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMoTa.setBounds(172, 100, 629, 35);
		contentPanel.add(txtMoTa);

		txtChietKhau = new JTextField();
		txtChietKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		txtChietKhau.setColumns(10);
		txtChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtChietKhau.setBounds(172, 219, 191, 35);
		contentPanel.add(txtChietKhau);

		btnHuy = new JButton("Hủy");
		btnHuy.setMargin(new Insets(2, 0, 2, 0));
		btnHuy.setBackground(new Color(255, 99, 71));
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setIconTextGap(20);
		btnHuy.setToolTipText("20");
		btnHuy.setIcon(new ImageIcon(DialogSuaKhuyenMai.class.getResource("/icon/cancel (2).png")));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setBounds(25, 353, 131, 40);
		contentPanel.add(btnHuy);
		btnHuy.addActionListener(this);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(510, 367, 131, 40);
		btnLamMoi.setBounds(26, 353, 131, 40);
		contentPanel.add(btnLamMoi);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setMargin(new Insets(2, 0, 2, 0));
		btnCapNhat.setBackground(new Color(60, 179, 113));
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setIconTextGap(2);
		btnCapNhat.setIcon(new ImageIcon(DialogSuaKhuyenMai.class.getResource("/icon/update (2).png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCapNhat.setBounds(670, 353, 131, 40);
		btnCapNhat.addActionListener(this);
		contentPanel.add(btnCapNhat);

		txtMaGiamGia = new JTextField();
		txtMaGiamGia.setColumns(10);
		txtMaGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaGiamGia.setBounds(170, 158, 631, 35);
		contentPanel.add(txtMaGiamGia);

		JLabel lblNewLabel_2_4_1 = new JLabel("Số lượng:");
		lblNewLabel_2_4_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4_1.setBounds(433, 222, 110, 35);
		contentPanel.add(lblNewLabel_2_4_1);

		dateNgayBatDau = new JDateChooser();
		dateNgayBatDau.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 14));
		dateNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dateNgayBatDau.setBounds(172, 283, 224, 35);


		contentPanel.add(dateNgayBatDau);

		JLabel lblCm = new JLabel("Ngày bắt đầu:");
		lblCm.setForeground(Color.BLACK);
		lblCm.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCm.setBounds(30, 283, 122, 35);
		contentPanel.add(lblCm);

		dateNgayHetHan = new JDateChooser();
		dateNgayHetHan.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateNgayHetHan.setFont(new Font("Arial", Font.PLAIN, 14));
		dateNgayHetHan.setDateFormatString("dd/MM/yyyy");
		dateNgayHetHan.setBounds(577, 283, 224, 35);
		contentPanel.add(dateNgayHetHan);

		JLabel lblNgyHtHn = new JLabel("Ngày hết hạn:");
		lblNgyHtHn.setForeground(Color.BLACK);
		lblNgyHtHn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgyHtHn.setBounds(433, 283, 332, 35);
		contentPanel.add(lblNgyHtHn);

		txtSoLuong = new JTextField();
		txtSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(577, 222, 224, 35);
		contentPanel.add(txtSoLuong);

		textField = new JTextField("%");
		textField.setEnabled(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(350, 219, 44, 35);
		contentPanel.add(textField);

		btnThuHi = new JButton("Thu hồi");
		btnThuHi.setMargin(new Insets(2, 0, 2, 0));
		btnThuHi.setIconTextGap(2);
		btnThuHi.setForeground(Color.WHITE);
		btnThuHi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThuHi.setBackground(Color.GRAY);
		btnThuHi.setBounds(509, 353, 131, 40);
		contentPanel.add(btnThuHi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		loadDuLieu(khuyenMai);

	}

	private void loadDuLieu(KhuyenMai km) {
		txtMoTa.setText(km.getMoTa());
		txtMaGiamGia.setText(km.getMaGiamGia());
		txtChietKhau.setText(km.getChietKhau() + "");
		dateNgayBatDau.setDate(km.getNgayBatDau());
		dateNgayHetHan.setDate(km.getNgayHetHan());
		txtSoLuong.setText(km.getTongSoLuong() + "");
	}

	@SuppressWarnings("unused")
	private String getTrangThaiTheoNgay() {
		if(dateNgayBatDau.getDate() == null || dateNgayHetHan.getDate() == null)
			 return"";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String[] s = df.format(dateNgayBatDau.getDate()).split("/");
		LocalDate ld1 = LocalDate.of(Integer.parseInt(s[2]), Integer.parseInt(s[1]), Integer.parseInt(s[0]));
	
		String[] s2 = df.format(dateNgayHetHan.getDate()).split("/");
		LocalDate ld2 = LocalDate.of(Integer.parseInt(s2[2]), Integer.parseInt(s2[1]), Integer.parseInt(s2[0]));
		
		if(Period.between(ld1, LocalDate.now()).getDays() < 0) {
			return "Chờ";
		}else if(Period.between(ld1, LocalDate.now()).getDays() >= 0 && Period.between(ld2, LocalDate.now()).getDays() <= 0) {
			return "Hoạt động";
		}else  if(Period.between(ld2, LocalDate.now()).getDays() > 0) {
			return "Hết hạn";
		}
		return "";
	}
	@SuppressWarnings("unused")
	private boolean isTrangThai(int selected) {
		// 0 : true
		return selected == 0 ? true : false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (btnHuy == o) {
			setVisible(false);
		} else if (btnThuHi == o) {
			loadDuLieu(khuyenMai);
		} else if (btnCapNhat == o) {
			if (kiemTraDuLieu()) {
				String moTa = txtMoTa.getText();
				int soLuong = Integer.parseInt(txtSoLuong.getText());
				Date ngayNhap = dateNgayBatDau.getDate();
				Date ngayHetHan = dateNgayHetHan.getDate();
				khuyenMai.setMaGiamGia(txtMaGiamGia.getText());
				khuyenMai.setNgayHetHan(ngayHetHan);
				khuyenMai.setTongSoLuong(soLuong);
				khuyenMai.setChietKhau(Float.parseFloat(txtChietKhau.getText()));
				khuyenMai.setNgayBatDau(ngayNhap);
				khuyenMai.setMoTa(moTa);
				if (khuyenMaiDao.capNhatKhuyenMai(khuyenMai)) {
					JOptionPane.showMessageDialog(null, "Cập nhật danh sách thành công");
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật danh sách thất bại");
				}
			}
		}
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void foCus(JTextField txt) {
		txt.selectAll();
		txt.requestFocus();
	}

	private boolean kiemTraDuLieu() {
		if (txtMoTa.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên không được trống");
			foCus(txtMoTa);
			return false;
		}
		if (!txtMoTa.getText()
				.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
						+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
						+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")){
			JOptionPane.showMessageDialog(this, "Tên không chứa kí tự đặc biệt");
			foCus(txtMoTa);
			return false;
		}
		if (txtSoLuong.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng không rỗng");
			foCus(txtSoLuong);
			return false;
		}
		try {
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			if (soLuong < 0) {
				JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
				foCus(txtSoLuong);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Số lượng là chữ số");
			foCus(txtSoLuong);
			return false;
		}
		if (txtChietKhau.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Chiết khấu không được rỗng");
			foCus(txtChietKhau);
			return false;
		}
		try {
			float chietKhau = Float.parseFloat(txtChietKhau.getText());
			if (chietKhau < 0) {
				JOptionPane.showMessageDialog(this, "Chiết khấu phải lớn hơn 0");
				foCus(txtChietKhau);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Chiếu khấu là chữ số");
			foCus(txtChietKhau);
			return false;
		}
		if(!kiemTraNgay()) return false;
		return true;
	}
	public boolean soSachNgay(Date d1, Date d2) {
		if(d1 == null || d2 == null) return false;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String[] s1 = df.format(d1).split("/");
		LocalDate ld1 = LocalDate.of(Integer.parseInt(s1[2]), Integer.parseInt(s1[1]), Integer.parseInt(s1[0]));
		
		String[] s2 = df.format(d2).split("/");
		LocalDate ld2 = LocalDate.of(Integer.parseInt(s2[2]), Integer.parseInt(s2[1]), Integer.parseInt(s2[0]));
		if (Period.between(ld1, ld2).getYears() <= 0 &&
			Period.between(ld1, ld2).getMonths() <= 0 &&
			Period.between(ld1, ld2).getDays() <= 0) {
			return false;
		}
		return true;
	}
	private boolean kiemTraNgay() {
		if (dateNgayBatDau.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày bắt đầu");
			return false;
		}
		if (soSachNgay(dateNgayBatDau.getDate(), new Date())) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày hiện tại");
			return false;
		}
		
		if (dateNgayHetHan.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày hết hạn");
			return false;
		}
		if (!soSachNgay(dateNgayBatDau.getDate(),dateNgayHetHan.getDate())) {
			JOptionPane.showMessageDialog(this, "Ngày hết hạn phải sau ngày bắt đầu"
					+ "");
			return false;
		}
		return true;
	}
}
