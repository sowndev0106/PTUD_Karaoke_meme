package view;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import dao.DichVuDao;
import entity.DichVu;
import view.util.HeaderRenderer;

@SuppressWarnings("serial")
public class DSDichVuPanel extends JPanel implements ActionListener, KeyListener, MouseListener, ItemListener {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JButton btnLamMoi;
	private JButton btnCapNhat;
	private JButton btnThem;
	private DefaultTableModel tableModle;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtTenDV;
	private JTextField txtMaDV;
	private JLabel lblNewLabel_1;
	private JLabel lblChitKhu;
	private JComboBox<String> cbTrangthai;
	private DichVuDao dichVuDao;
	private List<DichVu> danhSachDichVu;
	private JButton btnTim;
	@SuppressWarnings("unused")
	private Date dateNgayHetHan;
	private int page = 1;
	private int limit = 20;
	private int totalPage;
	private JButton btnDau;
	private AbstractButton btnTru1;
	private JButton btnCong1;
	private JButton btnCuoi;
	private JLabel txtPage;
	private JLabel txtTotolPage;

	/**
	 * Create the frame.
	 */
	public DSDichVuPanel() {
		dichVuDao = new DichVuDao(MainFrame.sessionFactory);
		setBounds(0, 0, 1295, 638);
		// setResizable(false);
//		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		contentPane.setLayout(null);
		contentPane_1 = new JPanel();
		contentPane_1.setBackground(Color.WHITE);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(1275, 646);

		JLabel lblMaNV = new JLabel("Mã dịch vụ:");
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setMargin(new Insets(0, 0, 0, 0));
		btnLamMoi.setBackground(SystemColor.activeCaption);
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/user.png")));
		btnLamMoi.addActionListener(this);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(this);
		btnCapNhat.setMargin(new Insets(2, 0, 2, 0));
		btnCapNhat.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setBackground(new Color(255, 99, 71));
		btnCapNhat.setIconTextGap(10);
		btnCapNhat.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/unemployment.png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnThem = new JButton("Tạo mới");
		btnThem.setMargin(new Insets(0, 0, 0, 0));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/wine-menu.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThem.addActionListener(this);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		table = new JTable();
		table.setSelectionBackground(SystemColor.activeCaption);
		scrollPane.setColumnHeaderView(table);
		tableModle = new DefaultTableModel(new Object[][] {}, new String[] { "Mã dịch vụ", "Tên dịch vụ", "Số lượng",
				"Đơn vị", "Đơn giá", "Trạng thái" });
		table.setRowHeight(40);
		scrollPane.setViewportView(table);
		table.setModel(tableModle);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.setDefaultEditor(Object.class, null);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.addMouseListener(this);

		txtTenDV = new JTextField();
		txtTenDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenDV.setColumns(10);
		txtTenDV.addKeyListener(this);

		txtMaDV = new JTextField();
		txtMaDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaDV.setColumns(10);

		lblNewLabel_1 = new JLabel("Quản Lý Dịch Vụ");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBackground(new Color(72, 209, 204));

		JLabel lblNgyHtHn = new JLabel("Trạng thái:");
		lblNgyHtHn.setForeground(Color.BLACK);
		lblNgyHtHn.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblChitKhu = new JLabel("Tên dịch vụ:");
		lblChitKhu.setForeground(Color.BLACK);
		lblChitKhu.setFont(new Font("Tahoma", Font.BOLD, 16));

		cbTrangthai = new JComboBox<String>();
		cbTrangthai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTrangthai.setBackground(Color.WHITE);
		cbTrangthai.addItem("Tất cả");
		cbTrangthai.addItem("Hoạt động");
		cbTrangthai.addItem("Tạm dừng");
		cbTrangthai.setSelectedIndex(1);
		cbTrangthai.addItemListener(this);

		JTableHeader h = table.getTableHeader();
		h.setPreferredSize(new Dimension(40, 40));
		((DefaultTableCellRenderer) h.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		HeaderRenderer renderer = new HeaderRenderer();
		// center
		JLabel headerLabel = (JLabel) renderer;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		// set color Header Table
		@SuppressWarnings("unused")
		TableColumnModel columnmodel;
		columnmodel = table.getColumnModel();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
			renderer.setBackgroundColor(new Color(185, 219, 215));
			renderer.setForegroundColor(Color.black);
			renderer.setBorder(new LineBorder(new Color(0, 206, 209)));
		}

		btnTim = new JButton("Tìm kiếm");
		btnTim.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/search.png")));
		btnTim.setMargin(new Insets(0, 0, 0, 0));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim.setBackground(new Color(0, 128, 0));

		btnDau = new JButton("");
		btnDau.addActionListener(this);
		btnDau.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/previousEnd.png")));
		btnDau.setFont(new Font("Arial", Font.PLAIN, 16));

		btnTru1 = new JButton("");
		btnTru1.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/rewind-button.png")));
		btnTru1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTru1.addActionListener(this);

		txtPage = new JLabel("1");
		txtPage.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPage.setForeground(SystemColor.controlDkShadow);
		txtPage.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnCong1 = new JButton("");
		btnCong1.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/nextbutton.png")));
		btnCong1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCong1.addActionListener(this);

		btnCuoi = new JButton("");
		btnCuoi.setIcon(new ImageIcon(DSDichVuPanel.class.getResource("/icon/nextEnd.png")));
		btnCuoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCuoi.addActionListener(this);
		btnTim.addActionListener(this);

		txtMaDV.addKeyListener(this);

		danhSachDichVu = dichVuDao.layDanhSachDichVuTheoTenNgayTrangThai(0, limit,  null, 1);

		totalPage = dichVuDao.tongTrang("", page,  limit);

		txtTotolPage = new JLabel(totalPage + "");
		txtTotolPage.setHorizontalAlignment(SwingConstants.LEFT);
		txtTotolPage.setFont(new Font("Tahoma", Font.BOLD, 17));

		JLabel txtPage_1_1 = new JLabel("/");
		txtPage_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPage_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GroupLayout gl_contentPane_1 = new GroupLayout(contentPane_1);
		gl_contentPane_1.setHorizontalGroup(
			gl_contentPane_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addGap(10)
					.addComponent(lblMaNV, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(txtMaDV, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addGap(77)
					.addComponent(lblNgyHtHn, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(cbTrangthai, 0, 220, Short.MAX_VALUE)
					.addGap(106)
					.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addGap(10)
					.addComponent(lblChitKhu, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtTenDV, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
					.addGap(106)
					.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 1245, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1244, Short.MAX_VALUE)
					.addGap(16))
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addGap(243)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(btnDau, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
							.addComponent(btnCong1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addComponent(btnTru1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 179, Short.MAX_VALUE))
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(92)
							.addComponent(txtPage_1_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE))
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(138)
							.addComponent(txtTotolPage, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(78)
							.addComponent(txtPage, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addComponent(btnCuoi, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addGap(321))
		);
		gl_contentPane_1.setVerticalGroup(
			gl_contentPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_1.createSequentialGroup()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(1)
							.addComponent(lblMaNV, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(1)
							.addComponent(txtMaDV, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNgyHtHn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbTrangthai, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(1)
							.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(1)
							.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(2)
							.addComponent(lblChitKhu, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(1)
							.addComponent(txtTenDV, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnTim, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
					.addGap(8)
					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCong1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTru1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPage_1_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane_1.createSequentialGroup()
							.addGap(1)
							.addComponent(txtTotolPage, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPage, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDau, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCuoi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		contentPane_1.setLayout(gl_contentPane_1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPane_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(contentPane_1, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
					.addGap(0))
		);
		setLayout(groupLayout);
		
	}
public void khoiTao() {
	xoaDuLieuTable();
	loadDuLieuVaoTable(null,  1);
}

	private void loadDuLieuVaoTable() {
		page = 1;
		txtPage.setText(page + "");
		xoaDuLieuTable();
		@SuppressWarnings("unused")
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (danhSachDichVu.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy dịch vụ nào có mã là " + txtMaDV.getText());
			return;
		}
		danhSachDichVu.forEach(dv -> {
			tableModle.addRow(new Object[] { dv.getMaDV(), dv.getTenDichVu(), dv.getSoLuong(), dv.getDonViTinh(),
					dv.getDonGia(),
					taoTrangThai(dv.isTrangThaiDichVu()) });
		});
		disabledButtonPage();

	}

	public void xoaDuLieuTable() {
		int maxRow = table.getRowCount();
		for (int i = maxRow - 1; i >= 0; i--) {
			tableModle.removeRow(i);
		}
	}

	public String taoTrangThai(boolean tt) {
		return tt ? "Hoạt động" : "Tạm dừng";
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object == txtMaDV) {
			txtTenDV.setText("");
		}
		if (object == txtMaDV && KeyEvent.VK_ENTER == e.getKeyCode()) {
			String maDV = txtMaDV.getText().trim();
			if (!Pattern.matches("(DV|Dv|dv|dV)[a-zA-Z]{2}\\d{3}", maDV)) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng mã dịch vụ (VD: DVAA001)");
				txtMaDV.selectAll();
				txtMaDV.requestFocus();
				return;
			}
			page = 1;
			cbTrangthai.setSelectedIndex(0);
			danhSachDichVu = Arrays.asList(dichVuDao.layDichVuTheoMa(maDV));
			loadDuLieuVaoTable();
		}
		if (object == txtTenDV) {
			page = 1;
			loadDuLieuVaoTable(txtTenDV.getText(), cbTrangthai.getSelectedIndex());
		}
	}

	private void loadDuLieuVaoTable(String text,int selectedIndex) {
		txtMaDV.setText("");
		txtPage.setText(page + "");
		xoaDuLieuTable();
		int realPage = page - 1;

		danhSachDichVu = dichVuDao.layDanhSachDichVuTheoTenNgayTrangThai(realPage, limit, text, selectedIndex);
		System.out.println(danhSachDichVu);
		totalPage = dichVuDao.tongTrang(txtTenDV.getText(), selectedIndex,  limit);
		txtTotolPage.setText(totalPage + "");
		@SuppressWarnings("unused")
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		danhSachDichVu.forEach(dv -> {
			tableModle.addRow(new Object[] { dv.getMaDV(), dv.getTenDichVu(), dv.getSoLuong(), dv.getDonViTinh(),
					dv.getDonGia(),
					taoTrangThai(dv.isTrangThaiDichVu()) });
		});
		disabledButtonPage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnCapNhat)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ");
				return;
			}
			new DialogSuaDichVu(table.getValueAt(row, 0).toString()).setVisible(true);
			loadDuLieuVaoTable(txtTenDV.getText(), cbTrangthai.getSelectedIndex());
		}
		if (object.equals(btnThem)) {
			new DialogThemDichVu().setVisible(true);
			clear();
			page = 1;
			loadDuLieuVaoTable(null, cbTrangthai.getSelectedIndex());
		}
		if (object == btnLamMoi) {
			clear();
			loadDuLieuVaoTable(null, cbTrangthai.getSelectedIndex());
		}
		if (object == btnTim) {
			if (!Pattern.matches("(DV|Dv|dv|dV|DV)[a-zA-Z]{2}\\d{3}", txtMaDV.getText())) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng mã dịch vụ (VD: DVAA001)");
				txtMaDV.selectAll();
				txtMaDV.requestFocus();
				return;
			}
			cbTrangthai.setSelectedIndex(0);
			danhSachDichVu = Arrays.asList(dichVuDao.layDichVuTheoMa(txtMaDV.getText()));

			loadDuLieuVaoTable();
		}
		if (object == btnCong1) {
			if (page + 1 <= totalPage) {
				page++;
				loadDuLieuVaoTable(txtTenDV.getText(),cbTrangthai.getSelectedIndex());
			}
		}
		if (object == btnTru1) {
			if (page - 1 >= 1) {
				page--;
				loadDuLieuVaoTable(txtTenDV.getText(),cbTrangthai.getSelectedIndex());
			}
		}
		if (object == btnCuoi) {
			if (page != totalPage) {
				page = totalPage;
				loadDuLieuVaoTable(txtTenDV.getText(),cbTrangthai.getSelectedIndex());
			}

		}
		if (object == btnDau) {
			if (page != 1) {
				page = 1;
				loadDuLieuVaoTable(txtTenDV.getText(), cbTrangthai.getSelectedIndex());
			}
		}
	}

	private void disabledButtonPage() {
		btnDau.setEnabled(true);
		btnTru1.setEnabled(true);
		btnCuoi.setEnabled(true);
		btnCong1.setEnabled(true);
		if (page == 1) {
			btnDau.setEnabled(false);
			btnTru1.setEnabled(false);
		}
		if (page == totalPage) {
			btnCuoi.setEnabled(false);
			btnCong1.setEnabled(false);
		}
	}

	private void clear() {
		table.clearSelection();
		txtMaDV.setText("");
		txtTenDV.setText("");
		cbTrangthai.setSelectedIndex(1);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if (o == cbTrangthai) {
			page = 1;
			loadDuLieuVaoTable(txtTenDV.getText(), cbTrangthai.getSelectedIndex());
		}
	}
}
