package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import dao.HoaDonDao;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import view.util.FormatCustom;

public class DialogChiTietHoaDon extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JTable table = new JTable();
	private DefaultTableModel tableModel;
	private JLabel lblTongThanhTien;
	private JLabel lblTienThanhToan;
	private JLabel lblThue;
	private JScrollPane scrollPane;
	private JLabel lblGioBatDau;
	private JLabel lblGioKetThuc;
	private JLabel lblChietKhau;
	private JLabel lblTienNhan;
	private JLabel lblTienThua;
	private JLabel lblTenNhanVien;
	private JLabel lblTenKhachHang;
//	private HoaDonDao hoaDonDao;
	private JLabel lblMaHoaDon;
	public JButton btnXuatPDF;
	private JButton btnQuayLai;
	private int xacNhan;
	private HoaDon hoaDon;

	public DialogChiTietHoaDon() {
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 750, 729);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên quán");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 0, 118, 13);
		contentPanel.add(lblNewLabel);

		JLabel lblaCh = new JLabel("Địa chỉ: ");
		lblaCh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblaCh.setBounds(10, 23, 110, 13);
		contentPanel.add(lblaCh);

		JLabel lblNewLabel_1 = new JLabel("Karaoke MeMe");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(112, 0, 191, 13);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số 2 Nguyễn văn bảo Phường 4 Gò vấp Tp Hồ Chí Minh");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblNewLabel_2.setBounds(112, 23, 546, 13);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("HÓA ĐƠN TÍNH TIỀN");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_3.setBounds(193, 69, 380, 53);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNhnVin = new JLabel("Nhân viên:");
		lblNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNhnVin.setBounds(43, 132, 110, 13);
		contentPanel.add(lblNhnVin);

		JLabel lblKhchHng = new JLabel("Khách hàng:\r\n");
		lblKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblKhchHng.setBounds(43, 169, 110, 13);
		contentPanel.add(lblKhchHng);

//		JLabel lblNewLabel_4 = new JLabel(hoaDon.getKhachHang().getTenKhachHang().toUpperCase());
		lblTenNhanVien = new JLabel("Tên Khách Hàng");
		lblTenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenNhanVien.setBounds(145, 132, 179, 13);
		contentPanel.add(lblTenNhanVien);

//		JLabel lblNewLabel_4_1 = new JLabel(hoaDon.getNhanVien().getTenNhanVien().toUpperCase());

		lblTenKhachHang = new JLabel("Tên nhân viên");
		lblTenKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTenKhachHang.setBounds(145, 169, 215, 13);
		contentPanel.add(lblTenKhachHang);

		JLabel lblaCh_1 = new JLabel("Giờ kết thúc");
		lblaCh_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblaCh_1.setBounds(451, 169, 110, 13);
		contentPanel.add(lblaCh_1);

		JLabel lblaCh_1_1 = new JLabel("Giờ bắt đầu");
		lblaCh_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblaCh_1_1.setBounds(451, 132, 110, 13);
		contentPanel.add(lblaCh_1_1);

		JLabel lblNewLabel_5 = new JLabel("Mã HD:\r\n");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 46, 70, 13);
		contentPanel.add(lblNewLabel_5);

//		hoaDon.getMaHoaDonBan()
		lblMaHoaDon = new JLabel("maHoaDon");
		lblMaHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaHoaDon.setBounds(112, 46, 98, 13);
		contentPanel.add(lblMaHoaDon);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 192, 736, 350);

		contentPanel.add(scrollPane);

		table.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Số thứ tự", "Tên", "Số lượng / Số giờ", "Đơn giá", "Đơn vị tính", "Thành tiền" }));
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		JLabel lblNewLabel_8 = new JLabel("Tổng thành tiền:");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(451, 555, 122, 20);
		contentPanel.add(lblNewLabel_8);

		JLabel lblNewLabel_8_1 = new JLabel("Thuế VAT");
		lblNewLabel_8_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1.setBounds(451, 585, 122, 20);
		contentPanel.add(lblNewLabel_8_1);

		// format.chuyenDoiTienTe(tong)
		lblTongThanhTien = new JLabel("000");
		lblTongThanhTien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTongThanhTien.setBounds(571, 555, 156, 20);
		contentPanel.add(lblTongThanhTien);

//		format.chuyenDoiTienTe(theuVat)
		lblThue = new JLabel("000");

		lblThue.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThue.setBounds(571, 585, 156, 20);
		contentPanel.add(lblThue);

		JLabel lblNewLabel_8_1_1 = new JLabel("Tiền thanh toán");
		lblNewLabel_8_1_1.setForeground(Color.RED);
		lblNewLabel_8_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1_1.setBounds(451, 615, 122, 20);
		contentPanel.add(lblNewLabel_8_1_1);

//		format.chuyenDoiTienTe(theuVat+tong)
		lblTienThanhToan = new JLabel("000");
		lblTienThanhToan.setForeground(Color.RED);
		lblTienThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTienThanhToan.setBounds(571, 615, 156, 20);
		contentPanel.add(lblTienThanhToan);

		JLabel lblNewLabel_8_1_1_1 = new JLabel("Tiền nhận:");
		lblNewLabel_8_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1_1_1.setBounds(43, 582, 86, 20);
		contentPanel.add(lblNewLabel_8_1_1_1);

		JLabel lblNewLabel_8_1_1_1_1 = new JLabel("Tiền thừa");
		lblNewLabel_8_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1_1_1_1.setBounds(43, 612, 78, 20);
		contentPanel.add(lblNewLabel_8_1_1_1_1);

		// format.chuyenDoiTienTe(hoaDon.getTienNhan())
		lblTienNhan = new JLabel("000");
		lblTienNhan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTienNhan.setBounds(135, 582, 216, 20);
		contentPanel.add(lblTienNhan);
// format.chuyenDoiTienTe(hoaDon.getTienNhan() - hoaDon.getTongTien())
		JLabel lblNewLabel_9_2_1 = new JLabel();
		lblNewLabel_9_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_9_2_1.setBounds(125, 582, 226, 20);
		contentPanel.add(lblNewLabel_9_2_1);

		lblGioBatDau = new JLabel("18:20 20/11/2021");
		lblGioBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGioBatDau.setBounds(571, 132, 165, 13);
		contentPanel.add(lblGioBatDau);

		lblGioKetThuc = new JLabel("20:20 20/11/2021");
		lblGioKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGioKetThuc.setBounds(571, 169, 165, 13);
		contentPanel.add(lblGioKetThuc);

		JLabel lblNewLabel_8_1_1_1_2 = new JLabel("Chiết khấu");
		lblNewLabel_8_1_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_8_1_1_1_2.setBounds(43, 552, 86, 20);
		contentPanel.add(lblNewLabel_8_1_1_1_2);

		lblChietKhau = new JLabel("10%");
		lblChietKhau.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblChietKhau.setBounds(135, 552, 216, 20);
		contentPanel.add(lblChietKhau);

		lblTienThua = new JLabel("000");
		lblTienThua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTienThua.setBounds(135, 612, 216, 20);
		contentPanel.add(lblTienThua);

		btnXuatPDF = new JButton("Xuất PDF");
		btnXuatPDF.setForeground(new Color(255, 255, 255));
		btnXuatPDF.setBackground(new Color(255, 165, 0));
		btnXuatPDF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXuatPDF.setBounds(581, 651, 104, 35);
		contentPanel.add(btnXuatPDF);

		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setForeground(new Color(255, 255, 255));
		btnQuayLai.setBackground(SystemColor.textHighlight);
		btnQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuayLai.setBounds(43, 651, 110, 35);
		contentPanel.add(btnQuayLai);
		btnQuayLai.addActionListener(this);
		btnXuatPDF.addActionListener(this);
		table.setDefaultEditor(Object.class, null);

		// set select only one row

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
	}

	public void xoaBang() {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.removeRow(0);
		}
	}

	public void khoiTao(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
		xoaBang();
		if (hoaDon == null || hoaDon.getChiTietHoaDon() == null) {
			return;
		}
		double tienPhaiTra = hoaDon.getTongTien();
		double tienDichVu = hoaDon.tinhTienDichVu();
		double tienPhong = hoaDon.tinhTienPhong();

		lblMaHoaDon.setText(hoaDon.getMaHD());
		lblTenNhanVien.setText(hoaDon.getNhanVienLap().getHoTen());
		lblTenKhachHang.setText(hoaDon.getKhachHang().getHoTen());
		lblGioBatDau.setText(FormatCustom.dinhDanhThoiGian(hoaDon.getGioNhanPhong()));
		lblGioKetThuc.setText(FormatCustom.dinhDanhThoiGian(hoaDon.getGioKetThuc()));
		lblChietKhau.setText(hoaDon.getChietKhau() + "%");
		lblTienNhan.setText(FormatCustom.chuyenDoiTienTe(hoaDon.getTienKhachTra()));
		lblTienThua.setText(FormatCustom.chuyenDoiTienTe(hoaDon.getTienKhachTra() - tienPhaiTra));
		lblTongThanhTien.setText(FormatCustom.chuyenDoiTienTe(tienDichVu + tienPhong));
		lblThue.setText(hoaDon.getThue() + "%");
		lblTienThanhToan.setText(FormatCustom.chuyenDoiTienTe(tienPhaiTra));
		int i = 1;
		int thoiLuong = 0;
		for (ChiTietHoaDon chiTietHoaDon : hoaDon.getChiTietHoaDon()) {
			tableModel
					.addRow(new String[] { Integer.toString(i++), "Phòng số: " + chiTietHoaDon.getPhong().getMaPhong(),
							FormatCustom.dinhDangGio(chiTietHoaDon.getThoiLuong()),
							FormatCustom.chuyenDoiTienTe(chiTietHoaDon.getPhong().getLoaiPhong().getGiaTien()), "Giờ",
							FormatCustom.chuyenDoiTienTe(chiTietHoaDon.thanhTien()) });
			thoiLuong += chiTietHoaDon.getThoiLuong();
		}

		if (thoiLuong < 60) {
			double sum = hoaDon.getChiTietHoaDon().get(0).getPhong().getLoaiPhong().getGiaTien()
					* ((60 - thoiLuong) / 60.0);
			tableModel.setValueAt(FormatCustom.chuyenDoiTienTe(sum + hoaDon.getChiTietHoaDon().get(0).thanhTien()), 0,
					5);
		}
		if (hoaDon.getChiTietDichVu() != null) {
			for (ChiTietDichVu chiTietDichVu : hoaDon.getChiTietDichVu()) {
				tableModel.addRow(new String[] { Integer.toString(i++), chiTietDichVu.getDichVu().getTenDichVu(),
						Integer.toString(chiTietDichVu.getSoLuong()),
						FormatCustom.chuyenDoiTienTe(chiTietDichVu.getDichVu().getDonGia()),
						chiTietDichVu.getDichVu().getDonViTinh(),
						FormatCustom.chuyenDoiTienTe(chiTietDichVu.thanhTien()) });
			}
		}
		HoaDonDao hoaDonDao = new HoaDonDao(MainFrame.sessionFactory);
		hoaDon.setTongTien(tienPhaiTra);
		hoaDonDao.capNhatHoaDon(hoaDon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnQuayLai)) {
			setVisible(false);
			return;
		}
		if (object.equals(btnXuatPDF)) {
			xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xem file", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			xuatFile(hoaDon.getMaHD());
			setVisible(false);
			return;
		}

	}

	public void xuatFile(String path) {
		this.btnXuatPDF.setVisible(false);
		btnQuayLai.setVisible(false);
		path = "hoaDon\\" + path + ".pdf";
		if (!path.matches("(.)+(\\.pdf)$")) {
			path += ".pdf";
		}
		Container content = this.getContentPane();
		int height = content.getHeight();
		int width = content.getHeight();
		BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		content.printAll(g2d);
		g2d.dispose();
		try {
			Document d = new Document();
			PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(path));
			d.open();

			PdfContentByte contentByte = writer.getDirectContent();
			Image image = Image.getInstance(contentByte, scaleImage(595, height, img), 1);

			PdfTemplate template = contentByte.createTemplate(width, height);
			image.setAbsolutePosition(0, 0);
			template.addImage(image);
			contentByte.addTemplate(template, 0, 100);
			d.close();

			if (xacNhan == JOptionPane.YES_OPTION)
				Desktop.getDesktop().open(new File(path));
			else {
				JOptionPane.showMessageDialog(this, "Xuất hóa đơn " + hoaDon.getMaHD() + " Thành công");
			}
		} catch (IOException | DocumentException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không thành công");
		}
		btnXuatPDF.setVisible(true);
		btnQuayLai.setVisible(true);
		setVisible(false);
		dispose();
	}

	public BufferedImage scaleImage(int WIDTH, int HEIGHT, BufferedImage img) {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(img);
			bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;
	}

}