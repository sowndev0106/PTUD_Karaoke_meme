package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dao.DiaChiDao;
import dao.LoaiNhanVienDao;
import dao.NhanVienDao;
import entity.NhanVien;
import service.DiaChiService;
import service.LoaiNhanVienService;
import service.NhanVienService;

public class DialogSuaNhanVien extends JDialog implements ActionListener, ItemListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCMND;
	private JPasswordField txtMatKhau;
	private JTextField txtSDT;
	private JComboBox<String> cbTinhTP;
	@SuppressWarnings("unused")
	private DefaultComboBoxModel<String> modelTinhTp;
	private DefaultComboBoxModel<String> modelPhuongXa;
	private DefaultComboBoxModel<String> modelQuanHuyen;
	private JComboBox<String> cbQuanHuyen;
	private JComboBox<String> cbPhuongXa;
	private JButton btnCapNhat;
	private JComboBox<String> cbGioiTinh;
	private final JPanel contentPanel = new JPanel();
	private JTextField textHoTen;
	private JButton btnHuy;
	private JTextField textMa;
	private JLabel lblQuanHuyen;
	private JLabel lblLoaiNhanVien;
	@SuppressWarnings("rawtypes")
	private JComboBox cbLoaiNhanVien;
	NhanVienService nhanVienService = new NhanVienDao(MainFrame.sessionFactory);
	private NhanVien nhanVien;
	LoaiNhanVienService loaiNhanVienService = new LoaiNhanVienDao(MainFrame.sessionFactory);
	DiaChiService chiService = new DiaChiDao(MainFrame.sessionFactory);
	@SuppressWarnings("unused")
	private List<String> listTinhTP = new ArrayList<String>();
	private List<String> listQuanHuyen = new ArrayList<String>();
	private List<String> listPhuongXa = new ArrayList<String>();
	private JDateChooser ngaySinh;
	private JButton btnKhoiPhuc;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DialogSuaNhanVien(String maNV) {
		setModal(true);
		nhanVien = nhanVienService.layNhanVienTheoMa(maNV);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setTitle("Sửa Nhân Viên");

		setBounds(100, 100, 850, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel_2 = new JLabel("Họ tên:");
			lblNewLabel_2.setBounds(419, 99, 88, 35);
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblGioiTinh = new JLabel("Giới tính:");
			lblGioiTinh.setBounds(28, 148, 81, 35);
			lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblGioiTinh);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("CMND:");
			lblNewLabel_2.setBounds(28, 197, 90, 35);
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblNewLabel_2);
		}
		{
			cbGioiTinh = new JComboBox();
			cbGioiTinh.setBackground(new Color(255, 255, 204));
			cbGioiTinh.setBounds(125, 148, 240, 35);
			cbGioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
			cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
			contentPanel.add(cbGioiTinh);

		}
		{
			JLabel lblNewLabel_2 = new JLabel("Ngày sinh:");
			lblNewLabel_2.setBounds(28, 247, 115, 35);
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblTinhTP = new JLabel("Tỉnh/TP:");
			lblTinhTP.setBounds(28, 302, 74, 35);
			lblTinhTP.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblTinhTP);
		}
		{
			JLabel lblSDT = new JLabel("SDT:");
			lblSDT.setBounds(419, 197, 76, 35);
			lblSDT.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblSDT);
		}
		{
			btnHuy = new JButton("Hủy");
			btnHuy.setIconTextGap(20);
			btnHuy.setIcon(new ImageIcon(DialogSuaNhanVien.class.getResource("/icon/cancel (2).png")));
			btnHuy.addActionListener(this);
			btnHuy.setForeground(new Color(255, 255, 255));
			btnHuy.setBackground(new Color(250, 128, 114));
			btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
			btnHuy.setBounds(24, 367, 131, 40);
			
			contentPanel.add(btnHuy);
		}
		{
			btnCapNhat = new JButton("Cập nhật");
			btnCapNhat.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnCapNhat.setMargin(new Insets(2, 0, 2, 0));
			btnCapNhat.setIconTextGap(10);
			btnCapNhat.setIcon(new ImageIcon(DialogSuaNhanVien.class.getResource("/icon/update (2).png")));
			btnCapNhat.setForeground(new Color(255, 255, 255));
			btnCapNhat.setBackground(new Color(60, 179, 113));
			btnCapNhat.setBounds(677, 367, 131, 40);

			btnCapNhat.setFont(new Font("Arial", Font.BOLD, 16));

			contentPanel.add(btnCapNhat);
		}
		{
			JLabel lblMatKau = new JLabel("Mật khẩu:");
			lblMatKau.setBounds(424, 247, 76, 35);
			lblMatKau.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblMatKau);
		}
		{
			txtMatKhau = new JPasswordField();
			txtMatKhau.setBounds(565, 247, 240, 35);
			txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(txtMatKhau);
			txtMatKhau.setColumns(10);
		}
		{
			txtSDT = new JTextField();
			txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtSDT.setBounds(565, 197, 240, 35);
			contentPanel.add(txtSDT);
			txtSDT.setColumns(10);
		}

		cbTinhTP = new JComboBox<String>();
		cbTinhTP.setBackground(new Color(255, 255, 204));
		cbTinhTP.setBounds(124, 302, 135, 35);
		modelTinhTp = new DefaultComboBoxModel<String>(new String[] { "Tỉnh/TP" });
		cbTinhTP.setModel(new DefaultComboBoxModel(new String[] { "Tỉnh/TP" }));
		cbTinhTP.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTinhTP.addActionListener(this);
		contentPanel.add(cbTinhTP);

		List<String> listTinh = chiService.layDanhSachCacTinh();

		for (String dc : listTinh) {
			cbTinhTP.addItem(dc);
		}

		cbPhuongXa = new JComboBox();
		cbPhuongXa.setBackground(new Color(255, 255, 204));
		cbPhuongXa.setBounds(669, 302, 135, 35);
		modelPhuongXa = new DefaultComboBoxModel(new String[] { "Phường/Xã" });
		cbPhuongXa.setModel(modelPhuongXa);
		cbPhuongXa.setFont(new Font("Arial", Font.PLAIN, 16));
		cbPhuongXa.addActionListener(this);
		contentPanel.add(cbPhuongXa);

		cbQuanHuyen = new JComboBox<String>();
		cbQuanHuyen.setBackground(new Color(255, 255, 204));
		cbQuanHuyen.setBounds(394, 302, 135, 35);
		modelQuanHuyen = new DefaultComboBoxModel<String>(new String[] { "Quận/Huyện" });
		cbQuanHuyen.setModel(modelQuanHuyen);
		cbQuanHuyen.setFont(new Font("Arial", Font.PLAIN, 16));
		cbQuanHuyen.addActionListener(this);
		contentPanel.add(cbQuanHuyen);

		textHoTen = new JTextField();
		textHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textHoTen.setBounds(565, 99, 240, 35);
		contentPanel.add(textHoTen);
		textHoTen.setColumns(10);

		lblQuanHuyen = new JLabel("Quận/Huyện:");
		lblQuanHuyen.setFont(new Font("Arial", Font.BOLD, 16));
		lblQuanHuyen.setBounds(282, 302, 107, 37);
		contentPanel.add(lblQuanHuyen);

		JLabel lblPhuongXa = new JLabel("Phường/Xã:");
		lblPhuongXa.setFont(new Font("Arial", Font.BOLD, 16));
		lblPhuongXa.setBounds(561, 302, 101, 35);
		contentPanel.add(lblPhuongXa);
		{
			txtCMND = new JTextField();
			txtCMND.setBounds(124, 197, 240, 35);
			txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(txtCMND);
			txtCMND.setColumns(10);
		}

		JLabel lblNewLabel = new JLabel("Sửa thông tin nhân viên");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(72, 209, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(0, 0, 836, 70);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblNewLabel_2 = new JLabel("Mã NV:");
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
			lblNewLabel_2.setBounds(28, 97, 115, 35);
			contentPanel.add(lblNewLabel_2);
		}
		{
			textMa = new JTextField();
			textMa.setEnabled(false);
			textMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textMa.setColumns(10);
			textMa.setBounds(125, 99, 240, 35);
			contentPanel.add(textMa);
		}
		{
			lblLoaiNhanVien = new JLabel("Loại Nhân Viên:");
			lblLoaiNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
			lblLoaiNhanVien.setBounds(419, 148, 119, 35);
			contentPanel.add(lblLoaiNhanVien);
		}
		{
			cbLoaiNhanVien = new JComboBox();
			cbLoaiNhanVien.setModel(new DefaultComboBoxModel(new String[] { "Loại Nhân Viên" }));
			cbLoaiNhanVien.setFont(new Font("Arial", Font.PLAIN, 16));
			cbLoaiNhanVien.setBackground(new Color(255, 255, 204));
			cbLoaiNhanVien.setBounds(565, 148, 240, 35);
			contentPanel.add(cbLoaiNhanVien);
		}
		List<String> list = loaiNhanVienService.layDanhSachLoaiNhanVien();
		for (String loaiNhanVien : list) {
			cbLoaiNhanVien.addItem(loaiNhanVien);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.window);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}

		txtCMND.addActionListener(this);
		txtMatKhau.addActionListener(this);
		txtSDT.addActionListener(this);
		ngaySinh = new JDateChooser();
		ngaySinh.setBounds(125, 247, 240, 35);
		ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ngaySinh.setDateFormatString("dd/MM/yyyy");
		contentPanel.add(ngaySinh);

		btnKhoiPhuc = new JButton("Khôi Phục");

		btnKhoiPhuc.setMargin(new Insets(2, 0, 2, 0));
		btnKhoiPhuc.setIcon(new ImageIcon(DialogSuaNhanVien.class.getResource("/icon/synchronize.png")));
		btnKhoiPhuc.setIconTextGap(5);
		btnKhoiPhuc.setForeground(Color.WHITE);
		btnKhoiPhuc.setFont(new Font("Arial", Font.BOLD, 16));
		btnKhoiPhuc.setBackground(Color.DARK_GRAY);
		btnKhoiPhuc.setBounds(529, 367, 138, 40);
		contentPanel.add(btnKhoiPhuc);

		textMa.addActionListener(this);
		txtMatKhau.addActionListener(this);
		cbGioiTinh.addItemListener(this);
		cbPhuongXa.addItemListener(this);
		cbTinhTP.addItemListener(this);
		cbQuanHuyen.addItemListener(this);
		cbLoaiNhanVien.addItemListener(this);
		btnHuy.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnKhoiPhuc.addActionListener(this);
		loadDuLieu(nhanVien);
	}

	private void loadDuLieu(NhanVien nv) {
		textMa.setText(nhanVien.getMaNV().toString().trim());
		textHoTen.setText(nhanVien.getHoTen().trim());
		txtCMND.setText(nhanVien.getSoCMND().toString());
		textHoTen.setText(nhanVien.getHoTen());
		txtMatKhau.setText(nhanVien.getPassword());
		txtMatKhau.setEchoChar('*');
		txtSDT.setText(nhanVien.getSoDienThoai());
		ngaySinh.setDate(nhanVien.getNgaySinh());
		cbTinhTP.setSelectedItem(nhanVien.getDiaChi().getTinhTP());
		cbQuanHuyen.setSelectedItem(nhanVien.getDiaChi().getQuanHuyen());
		cbPhuongXa.setSelectedItem(nhanVien.getDiaChi().getPhuongXa());
		cbLoaiNhanVien.setSelectedItem(nhanVien.getLoaiNhanVien().getTenLoaiNhanVien());
		cbGioiTinh.setSelectedItem(nhanVien.isGioiTinh()? "Nam" : "Nữ");
	}

	public boolean suaNhanVien() {
		String hoTen = textHoTen.getText().toString();
		String sdt = txtSDT.getText().toString();
		@SuppressWarnings("deprecation")
		String mk = txtMatKhau.getText().toString();
		String cMND = txtCMND.getText().toString();
		String tinhTP = cbTinhTP.getSelectedItem().toString();
		String quanHuyen = cbQuanHuyen.getSelectedItem().toString();
		String phuongXa = cbPhuongXa.getSelectedItem().toString();
		String loai = cbLoaiNhanVien.getSelectedItem().toString();
		if (hoTen.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên không được trống");
			textHoTen.selectAll();
			textHoTen.requestFocus();
			return false;
		}
		if (!hoTen.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không có kí tự đặc biệt");
			textHoTen.selectAll();
			textHoTen.requestFocus();
			return false;
		}
		if (sdt.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		}
		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại là dãy s!");
			txtSDT.selectAll();
			txtSDT.requestFocus();

			return false;
		}
		if (cbLoaiNhanVien.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn loại nhân viên!");
			return false;
		}
		if (cMND.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập chứng minh nhân dân");
			txtCMND.selectAll();
			txtCMND.requestFocus();
			return false;
		}
		if (!cMND.matches("^[0-9]{9,}")) {
			JOptionPane.showMessageDialog(this, "Chứng minh nhân dân chứa ít nhât 9 số!");
			txtCMND.selectAll();
			txtCMND.requestFocus();

			return false;
		}
		if (cbGioiTinh.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tỉnh giới tính");
			return false;
		}
		if (ngaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh");
			return false;
		}

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String[] s = df.format(ngaySinh.getDate()).split("/");
		LocalDate ng = LocalDate.of(Integer.parseInt(s[2]), Integer.parseInt(s[1]), Integer.parseInt(s[0]));

		if (Period.between(ng, LocalDate.now()).getYears() < 18) {
			JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi");
			return false;
		}
		if (mk.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mật khẩu!");
			txtMatKhau.selectAll();
			txtMatKhau.requestFocus();
			return false;
		}
		if (!mk.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
			JOptionPane.showMessageDialog(this, "Gồm 8 kí tự, ít nhất một chữ thường, chữ hoa, số và kí tự đặt biệt!");
			txtMatKhau.selectAll();
			txtMatKhau.requestFocus();
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
		NhanVien nv;
		nv = nhanVienService.layNhanVienTheoMa(textMa.getText().toString().trim());
		NhanVien nhanVien = new NhanVien(nv.getMaNV(), isgioiTinh(), hoTen, ngay, mk, MainFrame.nhanVien, cMND, sdt,
				isTrangThai(), chiService.layDiaChi(phuongXa, quanHuyen, tinhTP),
				loaiNhanVienService.layLoaiNhanVien(loai));
		boolean k = nhanVienService.suaThongTinNhanVien(nhanVien);
		System.out.println(nhanVien);
		System.out.println(k);
		return k;
	}

	public boolean isTrangThai() {
		if (cbGioiTinh.getSelectedIndex() == 1) {
			return true;
		}
		if (cbGioiTinh.getSelectedIndex() == 2) {
			return false;
		}
		return false;
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

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnCapNhat)) {
			if (suaNhanVien()) {
				JOptionPane.showMessageDialog(this, "Thông tin nhân viên cập nhật thành công!");
				dispose();
			}
		} else if (object.equals(btnHuy)) {
			dispose();

		} else if(object.equals(btnKhoiPhuc)) {
			loadDuLieu(nhanVien);
			JOptionPane.showMessageDialog(this, "Dữ liệu đã được khôi phục!");
		}
	}

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
