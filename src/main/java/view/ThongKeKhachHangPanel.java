package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.jfree.data.category.CategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.HoaDonDao;
import entity.HoaDon;
import entity.KhachHang;
import view.util.FormatCustom;
import view.util.HeaderRenderer;

public class ThongKeKhachHangPanel extends JPanel implements ActionListener, PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static List<HoaDon> dsHoaDonTrongThang;
	private JDateChooser combChonNgay;
	private DefaultTableModel tableModel;
	@SuppressWarnings("unused")
	private CategoryDataset categoryDataset;
	private static HoaDonDao hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
	@SuppressWarnings("unused")
	private static HoaDon hoaDon;
	private JTable table;
	private int thang;
	private int ngay;
	private int nam;
	private JRadioButton rdNgay;
	private JRadioButton rdThang;
	private JDateChooser combChonThang;
	private JRadioButton rdNam;
	private JComboBox<Integer> combChonNam;
	private JLabel lbNam;
	private JLabel lbThang;
	private JLabel lblNgay;
	private JLabel lblNewLabel;

	public ThongKeKhachHangPanel() {
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setSize(1285, 651);
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

		JLabel lblNewLabel_1 = new JLabel("Thống kê doanh thu Khách hàng");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBackground(new Color(72, 209, 204));

		JPanel rd = new JPanel();
		rd.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ti\u00EAu ch\u00ED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		rd.setBackground(Color.WHITE);

		combChonNgay = new JDateChooser((Date) null, "dd/MM/yyyy");
		combChonNgay.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));
		combChonNgay.setDate(new Date());

		combChonNam = new JComboBox<Integer>();
		combChonNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		combChonNam.setBackground(SystemColor.inactiveCaptionBorder);

		rdNam = new JRadioButton("");
		List<Integer> dsNam = hoaDonDao.layNamTuHoaDon();
		for (Integer string : dsNam) {
			combChonNam.addItem(string);
		}
		lbNam = new JLabel("Năm");
		lbNam.setHorizontalAlignment(SwingConstants.CENTER);
		lbNam.setFont(new Font("Tahoma", Font.PLAIN, 18));

		rdNgay = new JRadioButton("");
		rdNgay.setSelected(true);

		lblNgay = new JLabel("Ngày");
		lblNgay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));

		combChonThang = new JDateChooser((Date) null, "MM/yyyy");
		combChonThang.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 16));

		rdThang = new JRadioButton("");

		lbThang = new JLabel("Tháng");
		lbThang.setHorizontalAlignment(SwingConstants.CENTER);
		lbThang.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNewLabel = new JLabel("Lựa chọn");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(new Color(0, 128, 128));
		GroupLayout gl_rd = new GroupLayout(rd);
		gl_rd.setHorizontalGroup(gl_rd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rd.createSequentialGroup().addGap(56)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addGap(106).addComponent(rdNgay, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGap(2).addComponent(lblNgay, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addGap(9).addComponent(combChonNgay, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE).addGap(85)
						.addComponent(rdThang, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lbThang, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addGap(13)
						.addComponent(combChonThang, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE).addGap(102)
						.addComponent(rdNam, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lbNam, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(3)
						.addComponent(combChonNam, 0, 130, Short.MAX_VALUE).addGap(51)));
		gl_rd.setVerticalGroup(gl_rd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rd.createSequentialGroup().addContainerGap(16, Short.MAX_VALUE)
						.addComponent(combChonThang, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(20))
				.addGroup(gl_rd.createSequentialGroup().addContainerGap(17, Short.MAX_VALUE)
						.addComponent(lbThang, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(20))
				.addGroup(gl_rd.createSequentialGroup().addContainerGap(22, Short.MAX_VALUE)
						.addComponent(rdThang, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(23))
				.addGroup(gl_rd.createSequentialGroup().addContainerGap(17, Short.MAX_VALUE)
						.addComponent(lbNam, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(20))
				.addGroup(gl_rd.createSequentialGroup().addContainerGap(17, Short.MAX_VALUE)
						.addComponent(combChonNam, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGap(20))
				.addGroup(gl_rd.createSequentialGroup().addContainerGap(21, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGap(22))
				.addGroup(gl_rd.createSequentialGroup().addContainerGap(15, Short.MAX_VALUE)
						.addComponent(combChonNgay, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(21))
				.addGroup(gl_rd.createSequentialGroup().addGap(21)
						.addComponent(rdNam, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(gl_rd.createSequentialGroup().addGap(16)
						.addComponent(lblNgay, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_rd.createSequentialGroup().addGap(20)
						.addComponent(rdNgay, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE)));
		rd.setLayout(gl_rd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(rd,
								GroupLayout.DEFAULT_SIZE, 1271, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 1276, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblNewLabel_1,
										GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE))))
				.addGap(9)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(2)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGap(5).addComponent(rd, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE).addGap(12)));

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdNam);
		bg.add(rdThang);
		bg.add(rdNgay);

		table = new JTable();
		table.setRowHeight(35);
		scrollPane.setColumnHeaderView(table);
		setLayout(groupLayout);
		table.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã Khách Hàng", "Tên khách hàng", "SĐT Khách", "Tổng tiền" }));
		scrollPane.setViewportView(table);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
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

		rdNgay.addActionListener(this);
		rdThang.addActionListener(this);
		rdNam.addActionListener(this);
		combChonNgay.addPropertyChangeListener(this);
		combChonThang.addPropertyChangeListener(this);

		combChonNgay.setEnabled(true);
		combChonThang.setEnabled(false);
		combChonNam.setEnabled(false);

		// year
		combChonNam.addActionListener(e -> {
			combChonNgay.setDate(null);
			combChonThang.setDate(null);
			xoaBang();
			nam = (int) combChonNam.getSelectedItem();
			docDuLieuVaoBangTheoNam(nam);
		});
		// khong cho sua table
		table.setDefaultEditor(Object.class, null);

	}

	public void khoiTao() {
		LocalDate now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();
		xoaBang();
		docDuLieuVaoBangTheoNgay(ngay, thang, nam);
	}

	private void docDuLieuVaoBangTheoNam(int nam) {
		// TODO Auto-generated method stub
		Map<KhachHang, Double> list = hoaDonDao.topKhachHangTheoNam(nam);
		Map<KhachHang, Double> students = list.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		for (Map.Entry<KhachHang, Double> entry : students.entrySet()) {
			KhachHang key = entry.getKey();
			Double val = entry.getValue();
			tableModel.addRow(new String[] { key.getMaKH(), key.getHoTen(), key.getSoDienThoai(),
					FormatCustom.chuyenDoiTienTe(val) });
		}
	}

	private void docDuLieuVaoBangTheoThang(int thang, int nam) {
		// TODO Auto-generated method stub
		Map<KhachHang, Double> list = hoaDonDao.topKhachHangTheoThang(thang, nam);
		Map<KhachHang, Double> students = list.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		for (Map.Entry<KhachHang, Double> entry : students.entrySet()) {
			KhachHang key = entry.getKey();
			Double val = entry.getValue();
			tableModel.addRow(new String[] { key.getMaKH(), key.getHoTen(), key.getSoDienThoai(),
					FormatCustom.chuyenDoiTienTe(val) });
		}
	}

	private void docDuLieuVaoBangTheoNgay(int ngay, int thang, int nam) {
		xoaBang();
		// TODO Auto-generated method stub
		Map<KhachHang, Double> list = hoaDonDao.topKhachHangTheoNgay(ngay, thang, nam);
		Map<KhachHang, Double> students = list.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		for (Map.Entry<KhachHang, Double> entry : students.entrySet()) {
			KhachHang key = entry.getKey();
			Double val = entry.getValue();
			tableModel.addRow(new String[] { key.getMaKH(), key.getHoTen(), key.getSoDienThoai(),
					FormatCustom.chuyenDoiTienTe(val) });
		}
	}

	public void xoaBang() {
		for (int i = tableModel.getRowCount(); i > 0; i--) {
			tableModel.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		xoaBang();
		if (rdNgay.isSelected()) {
			xoaBang();
			rdThang.setSelected(false);
			rdNam.setSelected(false);
			combChonNgay.setEnabled(true);
			combChonThang.setEnabled(false);
			combChonNam.setEnabled(false);
			combChonThang.setDate(null);
			combChonNgay.setDate(new Date());
		}
		if (rdThang.isSelected()) {
			xoaBang();
			rdNgay.setSelected(false);
			rdNam.setSelected(false);
			combChonThang.setEnabled(true);
			combChonNgay.setEnabled(false);
			combChonNam.setEnabled(false);
			combChonNgay.setDate(null);
			combChonThang.setDate(new Date());
		}
		if (rdNam.isSelected()) {
			xoaBang();
			combChonNam.setEnabled(true);
			rdNgay.setSelected(false);
			rdThang.setSelected(false);
			combChonNgay.setEnabled(false);
			combChonThang.setEnabled(false);
			combChonNgay.setDate(null);
			combChonThang.setDate(null);
			nam = (int) combChonNam.getSelectedItem();
			docDuLieuVaoBangTheoNam(nam);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object object = evt.getSource();
		if (object.equals(combChonNgay)) {
			if (combChonNgay.getDate() != null) {
				xoaBang();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = combChonNgay.getDate();
				String[] s = dateFormat.format(date).split("/");
				docDuLieuVaoBangTheoNgay(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			}
		}
		if (object.equals(combChonThang)) {
			xoaBang();
			if (combChonThang.getDate() == null) {
				int thang = LocalDate.now().getMonthValue();
				int nam = LocalDate.now().getYear();
				docDuLieuVaoBangTheoThang(thang, nam);
			}
			xoaBang();
			if (combChonThang.getDate() != null) {
				int thang = combChonThang.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.getMonthValue();
				int nam = combChonThang.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
				docDuLieuVaoBangTheoThang(thang, nam);
			}
		}
	}
}
