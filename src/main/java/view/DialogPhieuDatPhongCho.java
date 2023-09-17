package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import entity.PhieuDatPhong;
import view.util.FormatCustom;

public class DialogPhieuDatPhongCho extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSDTKhach;
	private JLabel lblGioLap;
	private JLabel lblTenNhanVien;
	private JLabel lblMaPhieuDat;
	private JLabel lblTenKhachHang;
	private JLabel lblGioNhanPhong;
	private JLabel lblSoPhong;
	private PhieuDatPhong phieuDatPhong;

	public DialogPhieuDatPhongCho() {
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBounds(0, 0, 600, 650);
		getContentPane().add(contentPanel);

		JLabel lblNewLabel = new JLabel("Tên quán");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(37, 10, 118, 13);
		contentPanel.add(lblNewLabel);

		JLabel lblaCh = new JLabel("Địa chỉ: ");
		lblaCh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblaCh.setBounds(37, 33, 110, 13);
		contentPanel.add(lblaCh);

		JLabel lblNewLabel_1 = new JLabel("Karaoke MeMe");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(151, 10, 191, 13);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số 2 Nguyễn văn bảo Phường 4 Gò vấp Tp Hồ Chí Minh");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblNewLabel_2.setBounds(149, 33, 395, 13);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PHIẾU ĐẶT PHÒNG CHỜ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_3.setBounds(0, 68, 591, 53);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNhnVin = new JLabel("Nhân viên lập:");
		lblNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNhnVin.setBounds(37, 234, 200, 30);
		contentPanel.add(lblNhnVin);

		JLabel lblKhchHng = new JLabel("Khách hàng:\r\n");
		lblKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblKhchHng.setBounds(37, 285, 200, 30);
		contentPanel.add(lblKhchHng);

		lblTenNhanVien = new JLabel("Nguyễn Thị Thanh Sơn");
		lblTenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTenNhanVien.setBounds(320, 234, 271, 30);
		contentPanel.add(lblTenNhanVien);

		lblTenKhachHang = new JLabel("Nguyễn Hồng Ngân");
		lblTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTenKhachHang.setBounds(320, 285, 271, 30);
		contentPanel.add(lblTenKhachHang);

		JLabel lbl = new JLabel("Thời gian nhận phòng");
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl.setBounds(37, 442, 244, 30);
		contentPanel.add(lbl);

		JLabel lblaCh_1_1 = new JLabel("Thời gian lập phiếu");
		lblaCh_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblaCh_1_1.setBounds(37, 389, 200, 30);
		contentPanel.add(lblaCh_1_1);

		lblGioLap = new JLabel("18:20 20/11/2021");
		lblGioLap.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblGioLap.setBounds(320, 389, 271, 30);
		contentPanel.add(lblGioLap);

		lblGioNhanPhong = new JLabel("20:20 20/11/2021");
		lblGioNhanPhong.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblGioNhanPhong.setBounds(320, 442, 271, 30);
		contentPanel.add(lblGioNhanPhong);

		lblMaPhieuDat = new JLabel("HDAA001");
		lblMaPhieuDat.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblMaPhieuDat.setBounds(320, 141, 271, 30);
		contentPanel.add(lblMaPhieuDat);

		JLabel lblNewLabel_5 = new JLabel("Mã phiếu đặt:\r\n");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(37, 141, 200, 30);
		contentPanel.add(lblNewLabel_5);

		lblSDTKhach = new JLabel("0394566461");
		lblSDTKhach.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSDTKhach.setBounds(320, 338, 271, 30);
		contentPanel.add(lblSDTKhach);

		JLabel lblSinThoi = new JLabel("Số điện thoại\n");
		lblSinThoi.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSinThoi.setBounds(37, 338, 200, 30);
		contentPanel.add(lblSinThoi);

		JLabel lblNewLabel_5_1 = new JLabel("Số phòng");
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_5_1.setBounds(37, 187, 200, 30);
		contentPanel.add(lblNewLabel_5_1);

		lblSoPhong = new JLabel("027");
		lblSoPhong.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblSoPhong.setBounds(320, 187, 271, 30);
		contentPanel.add(lblSoPhong);
		setBackground(Color.WHITE);
		setBounds(0, 0, 600, 650);
	}

	public void lamMoi() {
		lblMaPhieuDat.setText("");
		lblTenNhanVien.setText("");
		lblTenKhachHang.setText("");
		lblSDTKhach.setText("");
		lblGioLap.setText("");
		lblGioNhanPhong.setText("");
	}

	public void khoiTao(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
//		setVisible(true);
		lamMoi();
		lblMaPhieuDat.setText(phieuDatPhong.getMaPDP());
		lblTenNhanVien.setText(phieuDatPhong.getNhanVienLap().getHoTen());
		lblTenKhachHang.setText(phieuDatPhong.getKhachHang().getHoTen());
		lblSDTKhach.setText(phieuDatPhong.getKhachHang().getSoDienThoai());
		lblGioLap.setText(FormatCustom.dinhDanhThoiGian(phieuDatPhong.getThoiGianDangKyDatPhong()));
		lblGioNhanPhong.setText(FormatCustom.dinhDanhThoiGian(phieuDatPhong.getThoiGianNhanPhong()));
	}

	public void xuatFile() {
		int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xem phiếu đặt phòng (PDF)", "Thông báo",

				JOptionPane.YES_NO_OPTION);

		String path = phieuDatPhong.getMaPDP();
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
				JOptionPane.showMessageDialog(this, "Xuất phiếu đặt phòng " + phieuDatPhong.getMaPDP() + " Thành công");
			}
		} catch (IOException | DocumentException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Không thành công");
		}

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
