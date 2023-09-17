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
import java.rmi.RemoteException;
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

import org.hibernate.SessionFactory;

import com.toedter.calendar.JDateChooser;

import dao.DiaChiDao;
import dao.LoaiNhanVienDao;
import dao.MySessionFactory;
import dao.NhanVienDao;
import entity.NhanVien;
import service.DiaChiService;
import service.LoaiNhanVienService;
import service.NhanVienService;

public class DialogThemNhanVien extends JDialog implements ActionListener, ItemListener, MouseListener, KeyListener {

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
	private JButton btnLuu;
	private JComboBox<String> cbGioiTinh;
	private final JPanel contentPanel = new JPanel();
	private JTextField textHoTen;
	private JButton btnHuy;
	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	DiaChiService chiService = new DiaChiDao(sessionFactory);
	@SuppressWarnings("unused")
	private List<String> listTinhTP = new ArrayList<String>();
	private List<String> listQuanHuyen = new ArrayList<String>();
	private List<String> listPhuongXa = new ArrayList<String>();
	private JButton btnLamMoi;
	private JDateChooser ngaySinh;
	NhanVienService nhanVienService = new NhanVienDao(sessionFactory);
	LoaiNhanVienService loaiNhanVienService = new LoaiNhanVienDao(sessionFactory);
	@SuppressWarnings("rawtypes")
	private JComboBox cbLoaiNhanVien;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DialogThemNhanVien dialog = new DialogThemNhanVien();
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
	public DialogThemNhanVien() {
		setModal(true);
//		getContentPane().setEnabled(false);
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Thêm Nhân Viên");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);

		setBounds(100, 100, 849, 463);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);

		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel_2 = new JLabel("Họ tên:");
			lblNewLabel_2.setBounds(30, 91, 115, 35);
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblGioiTinh = new JLabel("Giới tính:");
			lblGioiTinh.setBounds(414, 191, 102, 35);
			lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblGioiTinh);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("CMND:");
			lblNewLabel_2.setBounds(30, 193, 115, 35);
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblNewLabel_2);
		}
		{
			cbGioiTinh = new JComboBox();
			cbGioiTinh.setBackground(new Color(255, 255, 204));
			cbGioiTinh.setBounds(548, 193, 256, 35);
			cbGioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
			cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
			contentPanel.add(cbGioiTinh);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Ngày sinh:");
			lblNewLabel_2.setBounds(30, 246, 115, 35);
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblTinhTP = new JLabel("Tỉnh/TP:");
			lblTinhTP.setBounds(25, 295, 115, 35);
			lblTinhTP.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblTinhTP);
		}
		{
			JLabel lblSDT = new JLabel("SDT:");
			lblSDT.setBounds(30, 147, 76, 35);
			lblSDT.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblSDT);
		}
		{
			btnHuy = new JButton("Hủy");
			btnHuy.setIconTextGap(20);
			btnHuy.setIcon(new ImageIcon(DialogThemNhanVien.class.getResource("/icon/cancel (2).png")));
			btnHuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnHuy.setForeground(new Color(255, 255, 255));
			btnHuy.setBackground(new Color(250, 128, 114));
			btnHuy.setFont(new Font("Arial", Font.BOLD, 16));

			btnHuy.setBounds(24, 367, 131, 40);

			contentPanel.add(btnHuy);
		}
		{
			btnLuu = new JButton("Thêm");
			btnLuu.setIconTextGap(15);
			btnLuu.setIcon(new ImageIcon(DialogThemNhanVien.class.getResource("/icon/add.png")));
			btnLuu.setForeground(new Color(255, 255, 255));
			btnLuu.setBackground(new Color(0, 102, 255));
			btnLuu.setBounds(677, 368, 131, 40);

			btnLuu.setFont(new Font("Arial", Font.BOLD, 16));

			contentPanel.add(btnLuu);
		}
		{
			JLabel lblMatKau = new JLabel("Mật khẩu:");
			lblMatKau.setBounds(414, 246, 97, 35);
			lblMatKau.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblMatKau);
		}
		{
			txtMatKhau = new JPasswordField();
			txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtMatKhau.setBounds(549, 248, 257, 35);
			contentPanel.add(txtMatKhau);
			txtMatKhau.setColumns(10);
		}
		{
			txtSDT = new JTextField();
			txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtSDT.setBounds(127, 146, 256, 35);
			contentPanel.add(txtSDT);
			txtSDT.setColumns(10);
		}

		cbTinhTP = new JComboBox<String>();
		cbTinhTP.setBackground(new Color(255, 255, 204));
		cbTinhTP.setBounds(112, 295, 141, 35);
		modelTinhTp = new DefaultComboBoxModel<String>(new String[] { "Tỉnh/TP" });
		cbTinhTP.setModel(new DefaultComboBoxModel(new String[] { "Tỉnh/TP" }));
		cbTinhTP.setFont(new Font("Arial", Font.PLAIN, 16));
		List<String> listTinh = chiService.layDanhSachCacTinh();

		for (String dc : listTinh) {
			cbTinhTP.addItem(dc);
		}
		contentPanel.add(cbTinhTP);

		cbPhuongXa = new JComboBox();
		cbPhuongXa.setBackground(new Color(255, 255, 204));
		cbPhuongXa.setBounds(656, 297, 149, 35);
		modelPhuongXa = new DefaultComboBoxModel(new String[] { "Phường/Xã" });
		cbPhuongXa.setModel(modelPhuongXa);
		cbPhuongXa.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPanel.add(cbPhuongXa);

		cbQuanHuyen = new JComboBox<String>();
		cbQuanHuyen.setBackground(new Color(255, 255, 204));
		cbQuanHuyen.setBounds(388, 298, 149, 35);
		modelQuanHuyen = new DefaultComboBoxModel<String>(new String[] { "Quận/Huyện" });
		cbQuanHuyen.setModel(modelQuanHuyen);
		cbQuanHuyen.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPanel.add(cbQuanHuyen);

		textHoTen = new JTextField();
		textHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textHoTen.setBounds(127, 93, 677, 35);
		contentPanel.add(textHoTen);
		textHoTen.setColumns(10);

		JLabel lblQuanHuyen = new JLabel("Quận/Huyện:");
		lblQuanHuyen.setFont(new Font("Arial", Font.BOLD, 16));
		lblQuanHuyen.setBounds(270, 295, 114, 37);
		contentPanel.add(lblQuanHuyen);

		JLabel lblPhuongXa = new JLabel("Phường/Xã:");
		lblPhuongXa.setFont(new Font("Arial", Font.BOLD, 16));
		lblPhuongXa.setBounds(548, 298, 101, 35);
		contentPanel.add(lblPhuongXa);
		{
			txtCMND = new JTextField();
			txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtCMND.setBounds(127, 195, 259, 35);
			contentPanel.add(txtCMND);
			txtCMND.setColumns(10);
		}

		JLabel lblNewLabel = new JLabel("Thêm nhân viên");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(72, 209, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(0, 0, 836, 70);
		contentPanel.add(lblNewLabel);
		{
			btnLamMoi = new JButton("Làm mới");
			btnLamMoi.setMargin(new Insets(2, 0, 2, 0));
			btnLamMoi.setIcon(new ImageIcon(DialogThemNhanVien.class.getResource("/icon/synchronize.png")));
			btnLamMoi.setIconTextGap(2);
			btnLamMoi.setForeground(Color.WHITE);
			btnLamMoi.setFont(new Font("Arial", Font.BOLD, 16));
			btnLamMoi.setBackground(new Color(102, 153, 51));
			btnLamMoi.setBounds(534, 368, 131, 40);
			contentPanel.add(btnLamMoi);
		}

		ngaySinh = new JDateChooser();
		ngaySinh.setBounds(127, 246, 257, 35);
		ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(ngaySinh);

		JLabel lbLoaiNV = new JLabel("Loại Nhân Viên:");
		lbLoaiNV.setFont(new Font("Arial", Font.BOLD, 16));
		lbLoaiNV.setBounds(412, 143, 127, 35);
		contentPanel.add(lbLoaiNV);

		cbLoaiNhanVien = new JComboBox();
		cbLoaiNhanVien.setBackground(new Color(255, 255, 204));
		cbLoaiNhanVien.setModel(new DefaultComboBoxModel(new String[] { "Loại Nhân Viên" }));
		cbPhuongXa.setFont(new Font("Arial", Font.PLAIN, 16));
		cbLoaiNhanVien.setBounds(547, 146, 256, 35);
		cbLoaiNhanVien.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPanel.add(cbLoaiNhanVien);
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
		ngaySinh.setDateFormatString("dd/MM/yyyy");
		textHoTen.addActionListener(this);
		txtSDT.addActionListener(this);
		txtMatKhau.addActionListener(this);
		txtCMND.addActionListener(this);
		cbGioiTinh.addActionListener(this);
		btnLuu.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnHuy.addActionListener(this);
		cbTinhTP.addItemListener(this);
		cbQuanHuyen.addItemListener(this);
		cbPhuongXa.addItemListener(this);
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

	public boolean themNhanVien() {
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
			JOptionPane.showMessageDialog(this, "Tên không được rỗng!");
			textHoTen.selectAll();
			textHoTen.requestFocus();
			return false;
		}
		if (!hoTen.matches("" + "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
				+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu"
				+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không có ký tự đặc biệt!");
			textHoTen.selectAll();
			textHoTen.requestFocus();
			return false;
		}
		if (sdt.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		}

		if (!sdt.matches("^0[1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại là dãy số!");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		}
		try {
			if (kiemtraSDT()) {
				JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
				return false;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cbLoaiNhanVien.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn loại nhân viên!");
			return false;
		}
		if (cMND.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập chứng minh nhân dân!");
			txtCMND.selectAll();
			txtCMND.requestFocus();
			return false;
		}
		try {
			if (kiemtraCMD()) {
				JOptionPane.showMessageDialog(this, "Số CMND đã tồn tại!");
				return false;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!cMND.matches("^[0-9]{9,12}")) {
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
			JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi!");
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
		NhanVien nV = new NhanVien(nhanVienService.phatSinhMaTuDong(), isgioiTinh(), hoTen, ngay, mk,
				MainFrame.nhanVien, cMND, sdt, isTrangThai(), chiService.layDiaChi(phuongXa, quanHuyen, tinhTP),
				loaiNhanVienService.layLoaiNhanVien(loai));
		boolean k = nhanVienService.themNhanVien(nV);
		System.out.println(nV);
		return k;
	}

	public boolean isTrangThai() {

		if (cbGioiTinh.getSelectedIndex() == 1) {
			return true;
		}
		if (cbGioiTinh.getSelectedIndex() == 2) {
			return false;
		}
		return true;
	}

	public boolean isgioiTinh() {

		if (cbGioiTinh.getSelectedIndex() == 1) {
			return true;
		}
		if (cbGioiTinh.getSelectedIndex() == 2) {
			return false;
		}
		return true;
	}

	public boolean kiemtraSDT() throws RemoteException {
		// TODO Auto-generated method stub
		NhanVien nhanvien = null;
		String sdt = txtSDT.getText().trim().toString();
		nhanvien = nhanVienService.layThongTinNhanVienQuaSDT(sdt);
		if (nhanvien != null) {

			String sdt1 = nhanvien.getSoDienThoai().trim().toString();
			if (sdt.equals(sdt1))
				return true;
		}
		return false;
	}

	public boolean kiemtraCMD() throws RemoteException {
		// TODO Auto-generated method stub
		NhanVien nhanvien = null;
		String cmnd = txtCMND.getText().trim().toString();
		nhanvien = nhanVienService.layThongTinNhanVienQuaCMND(cmnd);
		if (nhanvien != null) {

			String cmnD = nhanvien.getSoCMND().trim().toString();
			if (cmnd.equals(cmnD))
				return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(btnLuu)) {
			Boolean k = themNhanVien();
			if (k) {
				JOptionPane.showMessageDialog(this, "Thêm  khách hàng thành công!");
				dispose();
			}

		} else if (object.equals(btnHuy)) {
			dispose();
		} else if (object.equals(btnLamMoi)) {
			textHoTen.setText("");
			txtCMND.setText("");
			txtMatKhau.setText("");
			txtSDT.setText("");
			ngaySinh.setDate(null);
			cbGioiTinh.setSelectedIndex(0);
			cbPhuongXa.setSelectedIndex(0);
			cbQuanHuyen.setSelectedIndex(0);
			cbTinhTP.setSelectedIndex(0);
		}
	}
}
