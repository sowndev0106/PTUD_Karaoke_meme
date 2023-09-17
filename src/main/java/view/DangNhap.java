package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import dao.DangNhapDao;
import entity.NhanVien;

public class DangNhap extends JFrame implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaQuanLy;
	private JButton btnThoat;
	private JButton btnDangNhap;
	private DangNhapDao dangNhap_Dao = new DangNhapDao(MainFrame.sessionFactory);
	private JPasswordField txtMatKhau;
	private JCheckBox cbHienMatKhau;
	private JButton btnqmk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DangNhap.class.getResource("/icon/dangnhap.png")));
		setTitle("ĐĂNG NHẬP HỆ THỐNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(400, 200, 672, 394);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 204, 204));
		panel.setBounds(0, 0, 658, 74);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCngTyXy = new JLabel("Đăng nhập");
		lblCngTyXy.setForeground(Color.WHITE);
		lblCngTyXy.setHorizontalAlignment(SwingConstants.CENTER);
		lblCngTyXy.setBounds(10, 10, 648, 54);
		panel.add(lblCngTyXy);
		lblCngTyXy.setFont(new Font("Tahoma", Font.BOLD, 26));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(DangNhap.class.getResource("/icon/key_h.png")));
		lblNewLabel.setBounds(29, 74, 272, 278);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(348, 96, 124, 30);
		contentPane.add(lblNewLabel_2);

		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMtKhu.setBounds(348, 170, 94, 30);
		contentPane.add(lblMtKhu);

		txtMaQuanLy = new JTextField();
		txtMaQuanLy.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMaQuanLy.setBounds(328, 130, 272, 35);
		contentPane.add(txtMaQuanLy);
		txtMaQuanLy.setColumns(10);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setBackground(new Color(51, 153, 255));
		btnDangNhap.setIcon(new ImageIcon(DangNhap.class.getResource("/icon/login.png")));
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDangNhap.setBounds(469, 291, 143, 35);
		contentPane.add(btnDangNhap);

		btnThoat = new JButton("Thoát");
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBackground(new Color(178, 34, 34));
		btnThoat.setIcon(new ImageIcon(DangNhap.class.getResource("/icon/thoat.png")));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThoat.setBounds(328, 291, 107, 35);
		contentPane.add(btnThoat);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMatKhau.setBounds(328, 205, 272, 35);
		contentPane.add(txtMatKhau);

		txtMaQuanLy.setText("0368795645");
		txtMatKhau.setText("Cong85010@");
		txtMaQuanLy.selectAll();
		txtMaQuanLy.requestFocus();
		txtMatKhau.setEchoChar('*');

		cbHienMatKhau = new JCheckBox("");
		cbHienMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbHienMatKhau.setForeground(Color.BLACK);
		cbHienMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		cbHienMatKhau.setBackground(Color.WHITE);
		cbHienMatKhau.setBounds(542, 181, 58, 31);
		contentPane.add(cbHienMatKhau);
		cbHienMatKhau.setIcon(new ImageIcon(DangNhap.class.getResource("/icon/view.png")));

		 btnqmk = new JButton("Quên mật khẩu?");
		 btnqmk.setBackground(Color.WHITE);
		btnqmk.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnqmk.setBounds(445, 250, 153, 30);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnqmk.setBorder(emptyBorder);
		contentPane.add(btnqmk);
		
		
		btnDangNhap.addActionListener(this);
		btnqmk.addActionListener(this);
		btnThoat.addActionListener(this);
		cbHienMatKhau.addItemListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnThoat) {
			System.exit(0);
		}
		if(o == btnqmk) {
			new QuenMatKhauJDialog().setVisible(true);
			System.out.println("SD");
		}
		if (o == btnDangNhap) {
			if (txtMaQuanLy.getText().length() == 10) {
				NhanVien quanLy = dangNhap_Dao.getNhanVienDangNhap(txtMaQuanLy.getText());
				if (quanLy != null && txtMatKhau.getText().equals(quanLy.getPassword())) {
					if (quanLy.isTrangThaiLamViec() && (quanLy.getLoaiNhanVien().getMaLNV().equals("LNV001")
							|| quanLy.getLoaiNhanVien().getMaLNV().equals("LNV002"))) {
						setVisible(false);
						new MainFrame(quanLy).setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Bạn không còn quyền truy cập");
					}
				} else
					JOptionPane.showMessageDialog(null, "Số điện thoại hoặc mật khẩu không chính xác");
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại thật");
			}
		}

	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (cbHienMatKhau.isSelected()) {
			txtMatKhau.setEchoChar((char) 0);
		} else {
			txtMatKhau.setEchoChar('*');
		}

	}
}
