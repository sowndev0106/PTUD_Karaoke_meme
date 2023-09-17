package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.DangNhapDao;
import dao.NhanVienDao;
import entity.NhanVien;
import service.NhanVienService;

public class TTDangNhap extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField matKhau;
	private JTextField textSDT;
	private JTextField txtThngTinng;
	private JPasswordField matKhauMoi;
	private JButton btnCapNhat;
	NhanVienService nhanVienService = new NhanVienDao(MainFrame.sessionFactory);
	private DangNhapDao dangNhapDao = new DangNhapDao(MainFrame.sessionFactory);
	private JPasswordField xacNhanMatKhau;
	private JTextField txtHoTen;
	private NhanVien nhanVien = MainFrame.nhanVien;
	private JButton btnThuHi;
	public TTDangNhap() {
		
		setBounds(100, 100, 696, 448);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		
		JLabel lblSDT = new JLabel("Số Điện Thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSDT.setBounds(91, 145, 152, 35);
		contentPanel.add(lblSDT);
		
		JLabel lblMatKhau = new JLabel("Mẫu khẩu cũ:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMatKhau.setBounds(91, 195, 131, 35);
		contentPanel.add(lblMatKhau);
		
		matKhau = new JPasswordField();
		matKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		matKhau.setColumns(10);
		matKhau.setBounds(283, 197, 283, 35);
		contentPanel.add(matKhau);
		
		textSDT = new JTextField();
		textSDT.setEditable(false);
		textSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSDT.setColumns(10);
		textSDT.setBounds(283, 147, 283, 35);
		contentPanel.add(textSDT);
		
		txtThngTinng = new JTextField();
		txtThngTinng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtThngTinng.setText("Thông Tin Đăng Nhập");
		txtThngTinng.setHorizontalAlignment(SwingConstants.CENTER);
		txtThngTinng.setForeground(Color.WHITE);
		txtThngTinng.setFont(new Font("Tahoma", Font.BOLD, 35));
		txtThngTinng.setEditable(false);
		txtThngTinng.setColumns(10);
		txtThngTinng.setBackground(new Color(0, 204, 204));
		txtThngTinng.setBounds(0, -5, 682, 70);
		contentPanel.add(txtThngTinng);
		
		JLabel lblXacNhanMK = new JLabel("Mật khẩu mới:");
		lblXacNhanMK.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblXacNhanMK.setBounds(91, 249, 172, 35);
		contentPanel.add(lblXacNhanMK);
		
		matKhauMoi = new JPasswordField();
		matKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		matKhauMoi.setColumns(10);
		matKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		matKhauMoi.setBounds(282, 250, 283, 35);
		contentPanel.add(matKhauMoi);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setIconTextGap(6);
		btnCapNhat.setIgnoreRepaint(true);
		btnCapNhat.setIcon(new ImageIcon(TTDangNhap.class.getResource("/icon/update (4).png")));
		
		btnCapNhat.setHorizontalAlignment(SwingConstants.TRAILING);
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCapNhat.setBackground(new Color(60, 179, 113));
		btnCapNhat.setBounds(429, 351, 137, 40);
		contentPanel.add(btnCapNhat);
		
		JLabel lblXcNhnMt = new JLabel("Xác nhận mật khẩu:");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblXcNhnMt.setBounds(91, 302, 172, 35);
		contentPanel.add(lblXcNhnMt);
		
		xacNhanMatKhau = new JPasswordField();
		xacNhanMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		xacNhanMatKhau.setColumns(10);
		xacNhanMatKhau.setBounds(282, 303, 283, 35);
		contentPanel.add(xacNhanMatKhau);
		
		JLabel lblHTn = new JLabel("Họ tên:");
		lblHTn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHTn.setBounds(91, 98, 152, 35);
		contentPanel.add(lblHTn);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(283, 100, 283, 35);
		txtHoTen.setEditable(false);
		contentPanel.add(txtHoTen);
		
		btnThuHi = new JButton("Thu hồi");
		btnThuHi.setIcon(new ImageIcon(TTDangNhap.class.getResource("/icon/lam_moi.png")));
		btnThuHi.setIgnoreRepaint(true);
		btnThuHi.setHorizontalAlignment(SwingConstants.TRAILING);
		btnThuHi.setForeground(Color.WHITE);
		btnThuHi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThuHi.setBackground(new Color(169, 169, 169));
		btnThuHi.setBounds(91, 351, 113, 40);
		contentPanel.add(btnThuHi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.window);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		matKhau.addActionListener(this);
		matKhau.addActionListener(this);
		textSDT.addActionListener(this);
		matKhauMoi.addActionListener(this);
		btnCapNhat.addActionListener(this);
		loadData();
	}
	

	private void loadData() {
		txtHoTen.setText(nhanVien.getHoTen());
		textSDT.setText(nhanVien.getSoDienThoai());
	}


	public boolean kiemTraMatKhauMoi() {
		@SuppressWarnings("deprecation")
		String pass=matKhauMoi.getText().trim();
		@SuppressWarnings("deprecation")
		String xn= xacNhanMatKhau.getText().trim();
		if(pass.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
			if(pass.equals(xn)) {
				return true;
			}
			JOptionPane.showMessageDialog(this,"Xác nhận mật khẩu mới thất bại");
			return false;
		}
		else {
			JOptionPane.showMessageDialog(this, "Gồm 8 kí tự, ít nhất một chữ thường, chữ hoa, số và kí tự đặt biệt!");
			return false;
		}
	}
	@SuppressWarnings("deprecation")
	public boolean kiemTraMatKhauCu() {
		NhanVien quanLy = dangNhapDao.getNhanVienDangNhap(textSDT.getText());
		if(matKhau.getText().trim().equals(quanLy.getPassword()))
			return true;
		JOptionPane.showMessageDialog(this,"Mật khẩu cũ không chính xác");
		return false;
	}


	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object= e.getSource();
		NhanVien quanLy = dangNhapDao.getNhanVienDangNhap(textSDT.getText());
		if(object.equals(btnCapNhat)) {
			if(kiemTraMatKhauCu()) {
				if(kiemTraMatKhauMoi()) {
					quanLy.setPassword(matKhauMoi.getText().trim());
					dangNhapDao.suaTaiKhoan(quanLy);
					JOptionPane.showMessageDialog(this,"Đổi mật khẩu thành công");
					setVisible(false);
				}
			}
	}
	}
}
