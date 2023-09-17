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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
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

public class DialogThemKhachHang extends JDialog implements ActionListener, ItemListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textTenkH;
	private JTextField textSDT;
	private JButton btnHuy;
	private JButton btnLamMoi;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPhuongXa;
	private JButton btnLmMoi;
	private JButton btnThem;
	private JDateChooser dateNgaySinh;
	private JComboBox<String> cbTinhTP;
	private JComboBox<String> cbQuanHuyen;
	private JComboBox<String> cbGioiTinh;
	@SuppressWarnings("unused")
	private java.sql.Date ngay;
	KhachHangService khangService = new KhachHangDao(MainFrame.sessionFactory);
	DiaChiService chiService = new DiaChiDao(MainFrame.sessionFactory);
	@SuppressWarnings("unused")
	private List<String> listTinhTP = new ArrayList<String>();
	private List<String> listQuanHuyen = new ArrayList<String>();
	private List<String> listPhuongXa = new ArrayList<String>();
	public static  KhachHang khachHang;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DialogThemKhachHang dialog = new DialogThemKhachHang();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param sdt 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DialogThemKhachHang(String sdt) {
		setModal(true);
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

		JLabel lblThmKhchHng = new JLabel("Thêm khách hàng ");
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
		lblNewLabel_2.setBounds(33, 107, 131, 35);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Địa chỉ:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(28, 272, 115, 35);
		contentPanel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_3 = new JLabel("SĐT:");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(33, 166, 44, 35);
		contentPanel.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Giới tính:");
		lblNewLabel_2_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4.setBounds(493, 219, 100, 35);
		contentPanel.add(lblNewLabel_2_4);

		cbGioiTinh = new JComboBox<String>();
		cbGioiTinh.setBackground(new Color(255, 255, 204));
		cbGioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
		cbGioiTinh.setBounds(589, 220, 213, 35);
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
		contentPanel.add(cbGioiTinh);

		textTenkH = new JTextField();
		textTenkH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTenkH.setColumns(10);
		textTenkH.setBounds(175, 109, 626, 35);
		contentPanel.add(textTenkH);

		textSDT = new JTextField();
		textSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSDT.setColumns(10);
		textSDT.setBounds(175, 163, 626, 35);
		if(sdt != null) {
			textSDT.setText(sdt);
		}
		contentPanel.add(textSDT);

		btnHuy = new JButton("Hủy");
		btnHuy.setBackground(new Color(255, 99, 71));
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setIconTextGap(20);
		btnHuy.setToolTipText("20");
		btnHuy.setIcon(new ImageIcon(DialogThemKhachHang.class.getResource("/icon/cancel (2).png")));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setBounds(25, 353, 131, 40);
		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPanel.add(btnHuy);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(510, 367, 131, 40);
		btnLamMoi.setBounds(26, 353, 131, 40);
		contentPanel.add(btnLamMoi);

		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setIconTextGap(20);
		btnThem.setIcon(new ImageIcon(DialogThemKhachHang.class.getResource("/icon/add.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThem.setBounds(670, 353, 131, 40);
		contentPanel.add(btnThem);

		JLabel lblNewLabel_2_4_1 = new JLabel("Ngày sinh:");
		lblNewLabel_2_4_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4_1.setBounds(34, 219, 110, 35);
		contentPanel.add(lblNewLabel_2_4_1);

		dateNgaySinh = new JDateChooser();
		dateNgaySinh.setBounds(176, 219, 269, 35);
		dateNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateNgaySinh.setDateFormatString("dd/MM/yyyy");
		contentPanel.add(dateNgaySinh);

		cbTinhTP = new JComboBox<String>();
		cbTinhTP.setBackground(new Color(255, 255, 204));
//		cbTinhTP.setModel(new DefaultComboBoxModel(new String[] { "Tỉnh/TP"}));
		cbTinhTP.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTinhTP.setBounds(168, 272, 189, 35);
		contentPanel.add(cbTinhTP);

		cbQuanHuyen = new JComboBox<String>();
		cbQuanHuyen.setBackground(new Color(255, 255, 204));
//		cbQuanHuyen.setModel(new DefaultComboBoxModel(new String[] { "Quận/Huyện" }));
		cbQuanHuyen.setFont(new Font("Arial", Font.PLAIN, 16));
		cbQuanHuyen.setBounds(390, 272, 189, 35);
		contentPanel.add(cbQuanHuyen);

		cbPhuongXa = new JComboBox();
		cbPhuongXa.setBackground(new Color(255, 255, 204));
//		cbPhuongXa.setModel(new DefaultComboBoxModel(new String[] { "Xã/Phường"}));
		cbPhuongXa.setFont(new Font("Arial", Font.PLAIN, 16));
		cbPhuongXa.setBounds(616, 272, 189, 35);
		contentPanel.add(cbPhuongXa);

		cbTinhTP.addItem("Tỉnh");
		cbQuanHuyen.addItem("Quận");
		cbPhuongXa.addItem("Xã");
		List<String> listTinh = chiService.layDanhSachCacTinh();

		for (String dc : listTinh) {
			cbTinhTP.addItem(dc);
		}

		btnLmMoi = new JButton("Làm mới");
		btnLmMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLmMoi.setMargin(new Insets(2, 0, 2, 0));
		btnLmMoi.setIcon(new ImageIcon(DialogThemKhachHang.class.getResource("/icon/synchronize.png")));
		btnLmMoi.setIconTextGap(5);
		btnLmMoi.setForeground(Color.WHITE);
		btnLmMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLmMoi.setBackground(new Color(102, 153, 51));
		btnLmMoi.setBounds(531, 353, 131, 40);
		contentPanel.add(btnLmMoi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		dateNgaySinh.setDateFormatString("dd/MM/yyyy");
		textTenkH.addActionListener(this);
		textSDT.addActionListener(this);
		cbGioiTinh.addActionListener(this);
		btnThem.addActionListener(this);
		btnLmMoi.addActionListener(this);
		cbTinhTP.addItemListener(this);
		cbQuanHuyen.addItemListener(this);
		cbPhuongXa.addItemListener(this);
	}

	@SuppressWarnings("unused")
	public boolean themKH() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String tenKH = textTenkH.getText().toString();
		String sdt = textSDT.getText().toString();
		String tinhTP = cbTinhTP.getSelectedItem().toString();
		String quanHuyen = cbQuanHuyen.getSelectedItem().toString();
		String phuongXa = cbPhuongXa.getSelectedItem().toString();

		if (tenKH.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên không được rỗng!");
			textTenkH.selectAll();
			textTenkH.requestFocus();
			return false;
		}
		if (!tenKH.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không có ký tự đặc biệt!");
			textTenkH.selectAll();
			textTenkH.requestFocus();
			return false;
		}
		if (sdt.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại");
			textSDT.selectAll();
			textSDT.requestFocus();
			return false;
		}
		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại là dãy số!");
			textSDT.selectAll();
			textSDT.requestFocus();

			return false;
		}
		if (kiemtraSDT()) {
			JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
			return false;
		}
		if (dateNgaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh!");
			return false;
		}
		
		if (cbGioiTinh.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tỉnh giới tính!");
			return false;
		}
		if (cbTinhTP.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn Tỉnh/Thành Phố!");
			return false;
		}
		if (cbQuanHuyen.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn Quận/Huyện!");
			return false;
		}
		if (cbPhuongXa.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn Phường/Xã!");
			return false;
		}

		Date ngay = dateNgaySinh.getDate();
		KhachHang kh = new KhachHang(khangService.phatSinhMaTuDong(), chiService.layDiaChi(phuongXa, quanHuyen, tinhTP),
				tenKH, sdt, ngay, isgioiTinh());
		boolean k = khangService.themKhachHang(kh);
		if(k) {
			khachHang = kh;
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(btnThem)) {
			Boolean kh = themKH();
//			System.out.println(kh);
			if (kh) {
				
				JOptionPane.showMessageDialog(this, "Thêm  khách hàng thành công!");
				dispose();
			}
		} else if (object.equals(btnLmMoi)) {
			textTenkH.setText("");
			textSDT.setText("");
			dateNgaySinh.setDate(null);
			cbGioiTinh.setSelectedIndex(0);
			cbPhuongXa.setSelectedIndex(0);
			cbQuanHuyen.setSelectedIndex(0);
			cbTinhTP.setSelectedIndex(0);
		}

	}
	public boolean kiemtraSDT()  {
		// TODO Auto-generated method stub
		KhachHang khachang = null;
		String sdt = textSDT.getText().trim().toString();
		khachang = khangService.layKhachHangTheoSDT(sdt);
		if (khachang != null) {
			String sdT = khachang.getSoDienThoai().trim().toString();
			if (sdT.equals(sdT))
				return true;
		}
		return false;
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

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent e) {
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
