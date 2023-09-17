package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import dao.DangNhapDao;
import entity.NhanVien;

public class QuenMatKhauJDialog extends JDialog implements ActionListener {
	private JButton btnXacNhan;
	private JButton btnGuiMa;
	private JLabel lblNewLabel_2;
	private DangNhapDao dangNhap_Dao = new DangNhapDao(MainFrame.sessionFactory);
	private final JPanel contentPanel = new JPanel();

	public QuenMatKhauJDialog() {
		getContentPane().setLayout(null);
		setModal(true);
		
		setResizable(false);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		
		setBounds(100, 100, 665, 460);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 658, 74);
		panel.setBackground(new Color(0, 204, 204));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblQunMtKhu = new JLabel("Quên mật khẩu");
		lblQunMtKhu.setBounds(10, 10, 648, 54);
		lblQunMtKhu.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunMtKhu.setForeground(Color.WHITE);
		lblQunMtKhu.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lblQunMtKhu);

		 lblNewLabel_2 = new JLabel("Số điện thoại");
		 lblNewLabel_2.setBounds(365, 110, 124, 30);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(345, 144, 272, 35);
		textField.setText("0368795645");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setColumns(10);
		getContentPane().add(textField);

		 btnGuiMa = new JButton("Gửi mã");
		 btnGuiMa.setBounds(500, 268, 117, 35);
		btnGuiMa.setIcon(new ImageIcon(QuenMatKhauJDialog.class.getResource("/icon/send.png")));
		btnGuiMa.addActionListener(this);
		btnGuiMa.setForeground(new Color(0, 0, 0));
		btnGuiMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGuiMa.setBackground(SystemColor.activeCaption);
		getContentPane().add(btnGuiMa);

		txtXxxxx = new JTextField();
		txtXxxxx.setBounds(345, 310, 272, 35);
		txtXxxxx.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtXxxxx.setColumns(10);
		getContentPane().add(txtXxxxx);

		JLabel lblNewLabel_2_1 = new JLabel("Mã xác nhận");
		lblNewLabel_2_1.setBounds(365, 270, 124, 30);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewLabel_2_1);

		textField_1 = new JPasswordField();
		textField_1.setBounds(345, 223, 272, 35);
		textField_1.setText("");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setColumns(10);
		textField_1.setEchoChar('*');
		getContentPane().add(textField_1);

		JLabel lblNewLabel_2_2 = new JLabel("Mật khẩu mới");
		lblNewLabel_2_2.setBounds(365, 189, 124, 30);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewLabel_2_2);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBounds(345, 355, 272, 35);
		btnXacNhan.setIcon(new ImageIcon(QuenMatKhauJDialog.class.getResource("/icon/repair.png")));
		btnXacNhan.setForeground(new Color(0, 0, 0));
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXacNhan.setBackground(new Color(51, 153, 255));
		btnXacNhan.addActionListener(this);
		getContentPane().add(btnXacNhan);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(94, 10, 220, 423);
		lblNewLabel.setIcon(new ImageIcon(QuenMatKhauJDialog.class.getResource("/icon/password.png")));
		getContentPane().add(lblNewLabel);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "AC91506f91f3867f10501c82bc74dd3c8c";
	public static final String AUTH_TOKEN = "b380543ff468a5de0e96f793d1dc758b";
	private JTextField textField;
	private JTextField txtXxxxx;
	private JPasswordField textField_1;
	private int xxxxxx;

	public int guiMaXacNhan(String sdt) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String SDT_VN = "+84" + sdt.substring(1);
		System.out.println(SDT_VN);
		int maXN = (int) (Math.floor((0.1213123 * (999999 - 100000 + 1))) + 100000);
		String sms = "Mã xác nhận của bạn là " + maXN;
		Message message = Message.creator(new PhoneNumber(SDT_VN), new PhoneNumber("+12543472823"), sms).create();
		System.out.println(message.getSid());
		return maXN;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnGuiMa) {
			String sdt = textField.getText();
			if (Pattern.matches("0[0-9]{9}", sdt)) {
				if (dangNhap_Dao.kiemTraSDT(sdt)) {
					 xxxxxx = guiMaXacNhan(sdt);
					 JOptionPane.showMessageDialog(null, "Gửi mã thành công");
				} else
					JOptionPane.showMessageDialog(null, "Bạn không quyền truy cập");
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại thật");
			}
		}
		if (o == btnXacNhan) {
			if(!textField_1.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
				JOptionPane.showMessageDialog(null, "Mật khẩu có vẻ khá yếu. Mật khẩu phải gồm 8 kí tự, ít nhất một chữ thường, chữ hoa, số và kí tự đặt biệt!");
				return;
			}
			try {
				if(txtXxxxx.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã xác nhận");
					return;
				}
				Integer.parseInt(txtXxxxx.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Mã xác nhận bao gồm 6 chử số (XXXXXX)");
				return;
			}
			if(xxxxxx == Integer.parseInt(txtXxxxx.getText())) {
				String sdt = textField.getText();
				NhanVien nhanVien = dangNhap_Dao.getNhanVienDangNhap(sdt);
				nhanVien.setPassword(textField_1.getText());
				dangNhap_Dao.suaTaiKhoan(nhanVien);
				JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thành công");
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Bạn đã nhập sai mã xác nhận !!!. Vui lòng thử lại");
			}
		}
	}
}
