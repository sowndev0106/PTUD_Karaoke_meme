package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



public class DialogThemKhuyenMai extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnHuy;
	private JTextField txtSoLuong;
	private JTextField txtMaGiamGia;
	private JTextField txtChietKhau;
	private JTextField txtMoTa;
	private JTextField textField_2;
	private JButton btnLamMoi;
	private JButton btnThem;
	private JDateChooser dateNgayBatDau;
	private JDateChooser dateNgayHetHan;
	private KhuyenMaiDao khuyenMaiDao;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DialogThemKhuyenMai() {
		khuyenMaiDao = new KhuyenMaiDao(MainFrame.sessionFactory);
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
		
		JLabel lblThmKhchHng = new JLabel("Tạo Mới Khuyến Mãi");
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
		lblNewLabel_2.setBounds(28, 155, 131, 35);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Mã giảm giá");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(28, 96, 131, 35);
		contentPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Chiết khấu");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(28, 215, 115, 35);
		contentPanel.add(lblNewLabel_2_3);
		
		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMoTa.setBounds(170, 155, 631, 35);
		contentPanel.add(txtMoTa);
		
		txtChietKhau = new JTextField();
		txtChietKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		txtChietKhau.setColumns(10);
		txtChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtChietKhau.setBounds(170, 212, 195, 35);
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
		
		 btnThem = new JButton("Tạo mới");
		btnThem.setMargin(new Insets(2, 0, 2, 0));
		btnThem.setBackground(new Color(60, 179, 113));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setIconTextGap(2);
		btnThem.setIcon(new ImageIcon(DialogSuaKhuyenMai.class.getResource("/icon/update (2).png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThem.setBounds(670, 353, 131, 40);
		btnThem.addActionListener(this);
		contentPanel.add(btnThem);
		
		txtMaGiamGia = new JTextField();
		txtMaGiamGia.setColumns(10);
		txtMaGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaGiamGia.setBounds(170, 97, 629, 35);
		contentPanel.add(txtMaGiamGia);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Số lượng:");
		lblNewLabel_2_4_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4_1.setBounds(431, 215, 110, 35);
		contentPanel.add(lblNewLabel_2_4_1);
		
		 dateNgayBatDau = new JDateChooser();
		dateNgayBatDau.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 14));
		dateNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dateNgayBatDau.setBounds(170, 278, 224, 35);
		dateNgayBatDau.setDate(new Date());
		contentPanel.add(dateNgayBatDau);
		
		JLabel lblCm = new JLabel("Ngày bắt đầu:");
		lblCm.setForeground(Color.BLACK);
		lblCm.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCm.setBounds(28, 278, 122, 35);
		contentPanel.add(lblCm);
		
		 dateNgayHetHan = new JDateChooser();
		dateNgayHetHan.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		dateNgayHetHan.setFont(new Font("Arial", Font.PLAIN, 14));
		dateNgayHetHan.setDateFormatString("dd/MM/yyyy");
		dateNgayHetHan.setBounds(575, 278, 224, 35);
		contentPanel.add(dateNgayHetHan);
		
		JLabel lblNgyHtHn = new JLabel("Ngày hết hạn:");
		lblNgyHtHn.setForeground(Color.BLACK);
		lblNgyHtHn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgyHtHn.setBounds(431, 278, 332, 35);
		contentPanel.add(lblNgyHtHn);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(575, 215, 224, 35);
		contentPanel.add(txtSoLuong);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(DialogThemKhuyenMai.class.getResource("/icon/xoa.png")));
		btnLamMoi.setMargin(new Insets(2, 0, 2, 0));
		btnLamMoi.setIconTextGap(10);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLamMoi.setBackground(Color.GRAY);
		btnLamMoi.setBounds(498, 353, 131, 40);
		contentPanel.add(btnLamMoi);
		
		textField_2 = new JTextField("%");
		textField_2.setEnabled(false);
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(350, 212, 44, 35);
		contentPanel.add(textField_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (btnHuy == o) {
			setVisible(false);
		} else if (btnLamMoi == o) {
				clear();
		} else if (btnThem == o) {
			if (kiemTraDuLieu()) {
				KhuyenMai khuyenMai = new KhuyenMai();
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
				khuyenMai.setTrangThai(true);
				if (khuyenMaiDao.themKhuyenMai(khuyenMai)) {
					int n = JOptionPane.showConfirmDialog(null, "Tạo mới dịch vụ thành công, bạn có muốn tạo thêm dịch vụ?", "Thêm thành công", JOptionPane.YES_NO_OPTION);
					if(n == JOptionPane.YES_OPTION) {
						clear();
					} else {
						setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thất bại");
				}
			}
		}
	}

	private void clear() {
		txtMoTa.setText("");
		txtChietKhau.setText("");
		txtMaGiamGia.setText("");
		txtSoLuong.setText("");
		dateNgayBatDau.setDate(new Date());
		dateNgayHetHan.setDate(null);
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
				.matches("[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
						+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
						+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9 ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không có kí tự đặc biệt");
			foCus(txtMoTa);
			return false;
		}
		if (txtSoLuong.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng không được trống");
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
			JOptionPane.showMessageDialog(this, "Số lượng là chử số");
			foCus(txtSoLuong);
			return false;
		}
		if (txtChietKhau.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Chiếu khấu không được trống");
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
			JOptionPane.showMessageDialog(this, "Chiết khấu là chử số");
			foCus(txtChietKhau);
			return false;
		}
		if(!kiemTraNgay()) return false;
		return true;
	}
	@SuppressWarnings("deprecation")
	private boolean kiemTraNgay() {
		if (dateNgayBatDau.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày hết hạn");
			return false;
		}
		
		Date d1 = dateNgayBatDau.getDate();
		Date d2ht = new Date();
		System.out.println(d1.compareTo(d2ht));

		if (d1.compareTo(d2ht) == -1) {
			if(d1.getDay() != d2ht.getDay()) {
				JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải từ ngày hiện tại trở đi");
				return false;
			}
		}
		
		if (dateNgayHetHan.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày nhập");
			return false;
		}
		
		if (dateNgayHetHan.getDate().compareTo(dateNgayBatDau.getDate()) < 0) {
			JOptionPane.showMessageDialog(this, "Ngày hết hạn phải sau ngày bắt đầu");
			return false;
		}
		return true;
	}
}
