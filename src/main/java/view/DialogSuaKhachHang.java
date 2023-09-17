package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dao.DiaChiDao;
import dao.KhachHangDao;
import entity.KhachHang;
import service.DiaChiService;
import service.KhachHangService;

public class DialogSuaKhachHang extends JDialog implements ActionListener, ItemListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textTenKH;
	private JTextField textSDT;
	private JTextField textMaKH;
	private JButton btnHuy;
	KhachHangService khachHangService = new KhachHangDao(MainFrame.sessionFactory);
	DiaChiService chiService = new DiaChiDao(MainFrame.sessionFactory);
	@SuppressWarnings("unused")
	private List<String> listTinhTP = new ArrayList<String>();
	private List<String> listQuanHuyen = new ArrayList<String>();
	private List<String> listPhuongXa = new ArrayList<String>();
	private JDateChooser ngaySinh;
	private JComboBox<String> cbTinhTP;
	private JComboBox<String> cbQuanHuyen;
	private JComboBox<String> cbPhuongXa;
	private JComboBox<String> cbGioiTinh;
	@SuppressWarnings("unused")
	private JButton btnLamMoi;
	private JButton btnCapNhat;
	private KhachHang khachHang;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DialogSuaKhachHang dialog = new DialogSuaKhachHang();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DialogSuaKhachHang(String ma) {
		setResizable(false);
//		System.out.println(ma);

		khachHang = khachHangService.layKhachHangTheoMa(ma);
//		System.out.println(khachHang);
//
//		System.out.println(khachHang.getMaKH().toString().trim());

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Sửa Thông Tin Khách Hàng");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);

		setBounds(100, 100, 850, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);

		JLabel lblThmKhchHng = new JLabel("Sửa thông tin khách hàng ");
		lblThmKhchHng.setOpaque(true);
		lblThmKhchHng.setHorizontalTextPosition(SwingConstants.CENTER);
		lblThmKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmKhchHng.setForeground(Color.WHITE);
		lblThmKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblThmKhchHng.setBackground(new Color(72, 209, 204));
		lblThmKhchHng.setBounds(0, 0, 836, 70);
		contentPanel.add(lblThmKhchHng);

		JLabel lblNewLabel_2 = new JLabel("Tên khách hàng:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(28, 153, 131, 35);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Địa chỉ:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(28, 272, 115, 35);
		contentPanel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Mã Khách hàng:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(28, 96, 131, 35);
		contentPanel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("SĐT:");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(28, 215, 44, 35);
		contentPanel.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Giới tính:");
		lblNewLabel_2_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4.setBounds(520, 96, 110, 35);
		contentPanel.add(lblNewLabel_2_4);

		cbGioiTinh = new JComboBox<String>();
		cbGioiTinh.setBackground(new Color(255, 255, 204));
		cbGioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
		cbGioiTinh.setBounds(616, 97, 189, 35);
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
		contentPanel.add(cbGioiTinh);

		textTenKH = new JTextField();
		textTenKH.setColumns(10);
		textTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTenKH.setBounds(170, 155, 635, 35);
		contentPanel.add(textTenKH);

		textSDT = new JTextField();
		textSDT.setColumns(10);
		textSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSDT.setBounds(170, 212, 323, 35);
		contentPanel.add(textSDT);

		btnHuy = new JButton("Hủy");
		btnHuy.setMargin(new Insets(2, 0, 2, 0));
		btnHuy.setBackground(new Color(255, 99, 71));
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setIconTextGap(20);
		btnHuy.setToolTipText("20");
		btnHuy.setIcon(new ImageIcon(DialogSuaKhachHang.class.getResource("/icon/cancel (2).png")));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setBounds(25, 353, 131, 40);

		contentPanel.add(btnHuy);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setMargin(new Insets(2, 0, 2, 0));
		btnCapNhat.setBackground(new Color(60, 179, 113));
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setIconTextGap(2);
		btnCapNhat.setIcon(new ImageIcon(DialogSuaKhachHang.class.getResource("/icon/update (2).png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCapNhat.setBounds(670, 353, 131, 40);
		contentPanel.add(btnCapNhat);

		textMaKH = new JTextField();
		textMaKH.setEnabled(false);
		textMaKH.setColumns(10);
		textMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textMaKH.setBounds(170, 97, 323, 35);
		contentPanel.add(textMaKH);

		JLabel lblNewLabel_2_4_1 = new JLabel("Ngày sinh:");
		lblNewLabel_2_4_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4_1.setBounds(520, 215, 110, 35);
		contentPanel.add(lblNewLabel_2_4_1);

		ngaySinh = new JDateChooser();
		ngaySinh.setBounds(616, 212, 189, 35);
		ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ngaySinh.setForeground(Color.black);
		contentPanel.add(ngaySinh);

		cbTinhTP = new JComboBox<String>();
		cbTinhTP.setBackground(new Color(255, 255, 204));
		cbTinhTP.setModel(new DefaultComboBoxModel(new String[] { "Tỉnh/TP" }));
		cbTinhTP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTinhTP.setBounds(168, 272, 189, 35);
		contentPanel.add(cbTinhTP);
		List<String> listTinh = chiService.layDanhSachCacTinh();

		for (String dc : listTinh) {
			cbTinhTP.addItem(dc);
		}
		cbQuanHuyen = new JComboBox<String>();
		cbQuanHuyen.setBackground(new Color(255, 255, 204));
		cbQuanHuyen.setModel(new DefaultComboBoxModel(new String[] { "Quận/Huyện" }));
		cbQuanHuyen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbQuanHuyen.setBounds(390, 272, 189, 35);
		contentPanel.add(cbQuanHuyen);

		cbPhuongXa = new JComboBox();
		cbPhuongXa.setBackground(new Color(255, 255, 204));
		cbPhuongXa.setModel(new DefaultComboBoxModel(new String[] { "Xã/Phường" }));
		cbPhuongXa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbPhuongXa.setBounds(616, 272, 189, 35);
		contentPanel.add(cbPhuongXa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}

		cbGioiTinh.addItemListener(this);
		cbTinhTP.addItemListener(this);
		cbQuanHuyen.addItemListener(this);
		cbPhuongXa.addItemListener(this);

		btnCapNhat.addActionListener(this);
		btnHuy.addActionListener(this);
		textMaKH.setText(khachHang.getMaKH().toString().trim());
		textTenKH.addActionListener(this);
		textSDT.addActionListener(this);
		loadDuLieu(khachHang);

	}
	private void loadDuLieu(KhachHang khachHang) {
		textMaKH.setText(khachHang.getMaKH().toString().trim());
		textTenKH.setText(khachHang.getHoTen());
		textSDT.setText(khachHang.getSoDienThoai());
		cbGioiTinh.setSelectedItem(khachHang.isGioiTinh() ? "Nam" : "Nữ");
		cbTinhTP.setSelectedItem(khachHang.getDiaChi().getTinhTP());
		cbQuanHuyen.setSelectedItem(khachHang.getDiaChi().getQuanHuyen());
		cbPhuongXa.setSelectedItem(khachHang.getDiaChi().getPhuongXa());
		ngaySinh.setDate(khachHang.getNgaySinh());
	}

	public boolean themKH() {

		String tenKH = textTenKH.getText().toString();
		String sdt = textSDT.getText().toString();
		String tinhTP = cbTinhTP.getSelectedItem().toString();
		String quanHuyen = cbQuanHuyen.getSelectedItem().toString();
		String phuongXa = cbPhuongXa.getSelectedItem().toString();

		if (tenKH.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên không được trống");
			textTenKH.selectAll();
			textTenKH.requestFocus();
			return false;
		}
		if (!tenKH.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không có kí tự đặc biệt");
			textTenKH.selectAll();
			textTenKH.requestFocus();
			return false;
		}
		if (sdt.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại");
			textSDT.selectAll();
			textSDT.requestFocus();
			return false;
		}
		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại là dãy s!");
			textSDT.selectAll();
			textSDT.requestFocus();

			return false;
		}
		if (ngaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh");
			return false;
		}
		if (ngaySinh.getDate()==null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh!");
			return false;
		}
		if (cbGioiTinh.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tỉnh giới tính");
			return false;
		}
		if (cbTinhTP.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tỉnh thành phố");
			return false;
		}
		if (cbQuanHuyen.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn quận huyện");
			return false;
		}
		if (cbPhuongXa.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phường xã");
			return false;
		}
		Date ngay = ngaySinh.getDate();
		KhachHang h2;
		h2 = khachHangService.layKhachHangTheoMa(textMaKH.getText().toString().trim());
		KhachHang kh = new KhachHang(h2.getMaKH().toString().trim(), chiService.layDiaChi(phuongXa, quanHuyen, tinhTP),
				tenKH, sdt, ngay, isgioiTinh());
		boolean k = khachHangService.suaKhachHang(kh);
//		System.out.println(kh);
		return k;
	}

	public boolean isgioiTinh() {

		if (cbGioiTinh.getSelectedIndex() == 1) {
			return true;
		}
		if (cbGioiTinh.getSelectedIndex() == 2) {
			return false;
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnCapNhat)) {
			if (themKH()) {
				JOptionPane.showMessageDialog(this, "Thông tin khách hàng cập nhật thành công!");
				dispose();
			}
		} else if (object.equals(btnHuy)) {
			dispose();

		}
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
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED)
			return;
		if (o.equals(cbTinhTP)) {
			cbQuanHuyen.removeAllItems();
			cbQuanHuyen.addItem("Quận/Huyện");
			if (cbTinhTP.getSelectedIndex() > 0) {
				String tinh = cbTinhTP.getSelectedItem().toString();
				cbQuanHuyen.setEnabled(true);
				listQuanHuyen = chiService.layDanhSachHuyenTrongTinhTP(tinh);
				for (String b : listQuanHuyen) {
					cbQuanHuyen.addItem(b);
				}
			} else {
				cbQuanHuyen.setEnabled(false);
			}
		}
		if (o.equals(cbQuanHuyen)) {
			cbPhuongXa.removeAllItems();
			cbPhuongXa.addItem("Phường/Xã");
			if (cbQuanHuyen.getSelectedIndex() > 0) {
				String tinh = cbTinhTP.getSelectedItem().toString();
				String huyen = cbQuanHuyen.getSelectedItem().toString();
				cbPhuongXa.setEnabled(true);
				listPhuongXa = chiService.layDanhSachPhuongXaTrongHuyenTinh(tinh, huyen);
				for (String c : listPhuongXa) {
					cbPhuongXa.addItem(c);
				}
			} else {
				cbPhuongXa.setEnabled(false);
			}
		}

	}

}
