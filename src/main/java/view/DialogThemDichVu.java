package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.DichVuDao;
import entity.DichVu;
import service.DichVuService;

public class DialogThemDichVu extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnHuy;
	private JButton btnLuu;
	private JComboBox<String> cbTrangThai;
	private JTextField txtTen;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;
	private DichVuService dichVuDao;
	/**
	 * Create the dialog.
	 */
	private AbstractButton btnLmMi;
	private JLabel lblnV;
	private JComboBox<String> cbTrangThai_1;
	public DialogThemDichVu() {
		
		dichVuDao = new DichVuDao(MainFrame.sessionFactory);
		
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
		
		JLabel lblThmKhchHng = new JLabel("Tạo Mới Dịch Vụ");
		lblThmKhchHng.setOpaque(true);
		lblThmKhchHng.setHorizontalTextPosition(SwingConstants.CENTER);
		lblThmKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmKhchHng.setForeground(Color.WHITE);
		lblThmKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblThmKhchHng.setBackground(new Color(72, 209, 204));
		lblThmKhchHng.setBounds(0, 0, 836, 70);
		contentPanel.add(lblThmKhchHng);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tên dịch vụ:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(28, 96, 131, 35);
		contentPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Số lượng:");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(28, 177, 115, 35);
		contentPanel.add(lblNewLabel_2_3);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setBounds(170, 177, 224, 35);
		contentPanel.add(txtSoLuong);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGia.setBounds(170, 259, 224, 35);
		contentPanel.add(txtDonGia);
		
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
		
		 btnLuu = new JButton("Tạo mới");
		btnLuu.setMargin(new Insets(2, 0, 2, 0));
		btnLuu.setBackground(new Color(60, 179, 113));
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setIconTextGap(10);
		btnLuu.setIcon(new ImageIcon(DialogSuaKhuyenMai.class.getResource("/icon/update (2).png")));
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLuu.setBounds(670, 353, 131, 40);
		contentPanel.add(btnLuu);
		btnLuu.addActionListener(this);
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTen.setBounds(170, 97, 629, 35);
		contentPanel.add(txtTen);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Đơn giá:");
		lblNewLabel_2_4_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4_1.setBounds(28, 259, 110, 35);
		contentPanel.add(lblNewLabel_2_4_1);
		
		cbTrangThai = new JComboBox<String>();
		cbTrangThai.setToolTipText("");
		cbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTrangThai.setBackground(Color.WHITE);
		cbTrangThai.setBounds(575, 258, 224, 36);
		cbTrangThai.addItem("Hoạt động");
		cbTrangThai.addItem("Tạm dừng");
		contentPanel.add(cbTrangThai);
		
		JLabel lblTrngThi = new JLabel("Trạng thái:");
		lblTrngThi.setForeground(Color.BLACK);
		lblTrngThi.setFont(new Font("Arial", Font.BOLD, 16));
		lblTrngThi.setBounds(431, 259, 147, 35);
		contentPanel.add(lblTrngThi);
		
		 btnLmMi = new JButton("Làm mới");
		btnLmMi.setIcon(new ImageIcon(DialogThemDichVu.class.getResource("/icon/xoa.png")));
		btnLmMi.setMargin(new Insets(2, 0, 2, 0));
		btnLmMi.setIconTextGap(10);
		btnLmMi.setForeground(Color.WHITE);
		btnLmMi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLmMi.setBackground(Color.GRAY);
		btnLmMi.setBounds(499, 353, 131, 40);
		btnLmMi.addActionListener(this);
		contentPanel.add(btnLmMi);
		
		lblnV = new JLabel("Đơn vị:");
		lblnV.setForeground(Color.BLACK);
		lblnV.setFont(new Font("Arial", Font.BOLD, 16));
		lblnV.setBounds(431, 177, 147, 35);
		contentPanel.add(lblnV);
		
		cbTrangThai_1 = new JComboBox<String>();
		cbTrangThai_1.setToolTipText("");
		cbTrangThai_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTrangThai_1.setBackground(Color.WHITE);
		cbTrangThai_1.setBounds(575, 176, 224, 36);
		cbTrangThai_1.addItem("Chai");
		cbTrangThai_1.addItem("Lon");
		cbTrangThai_1.addItem("Bịch");
		cbTrangThai_1.addItem("Thùng");
		cbTrangThai_1.addItem("Phần");
		cbTrangThai_1.addItem("Khác");
		contentPanel.add(cbTrangThai_1);
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
		if(btnHuy == o) {
			setVisible(false);
		} else if(btnLuu == o) {
			if(kiemTraDuLieu()) {
			
				 double donGia = Double.parseDouble(txtDonGia.getText());
				String ten = txtTen.getText();
				 int soLuong = Integer.parseInt(txtSoLuong.getText());
				int trangThai = cbTrangThai.getSelectedIndex();
				String donVi = cbTrangThai_1.getSelectedItem().toString();
				DichVu dichVu = new DichVu(ten, soLuong, donVi,donGia,  isTrangThai(trangThai));
				if(dichVuDao.themDichVu(dichVu)) {
					int n = JOptionPane.showConfirmDialog(null, "Tạo mới dịch vụ thành công, bạn có muốn tạo thêm dịch vụ?", "Thêm thành công", JOptionPane.YES_NO_OPTION);
					if(n == JOptionPane.YES_OPTION) {
						clear();
					} else {
						setVisible(false);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Thêm dịch vụ thất bại");
				}
			}
		} else if(btnLmMi == o) {
			clear();
		}
	}
	private void clear() {
		txtTen.setText("");
		txtSoLuong.setText("");
		txtDonGia.setText("");
		cbTrangThai.setSelectedIndex(0);
		cbTrangThai_1.setSelectedIndex(0);
	}

	private boolean isTrangThai(int selected) {
		//index: 0  la true
		return selected==0?true:false;
	}
	public void foCus(JTextField txt) {
		txt.selectAll();
		txt.requestFocus();
	}

	private boolean kiemTraDuLieu() {
		if (txtTen.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên không được trống");
			foCus(txtTen);
			return false;
		}
		if (!txtTen.getText()
				.matches("[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
						+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
						+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9 ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không có kí tự đặc biệt");
			foCus(txtTen);
			return false;
		}
		if (txtSoLuong.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng không được trống");
			foCus(txtSoLuong);
			return false;
		}
		try {
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			if(soLuong < 0) {
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
		if (txtDonGia.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Đơn giá không được trống");
			foCus(txtDonGia);
			return false;
		}
		try {
			double donGia = Double.parseDouble(txtDonGia.getText());
			if(donGia < 0) {
				JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
				foCus(txtDonGia);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Đơn giá là chử số");
			foCus(txtDonGia);
			return false;
		}
		return true;
	}
}
