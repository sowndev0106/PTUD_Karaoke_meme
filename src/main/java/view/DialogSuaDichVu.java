package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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



public class DialogSuaDichVu extends JDialog implements ActionListener, KeyListener, MouseListener{
	

	private static final long serialVersionUID = 1L;
	private JButton btnLuu;
	private final JPanel contentPanel = new JPanel();
	private JButton btnHuy;
	private JTextField txtTen;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JComboBox<String> cbTrangThai;
	private DichVuDao dichVuDao;
	private DichVu dichVu;
	private JButton btnThuHi;
	private JComboBox<String> cbDonVi;

	/**
	 * Create the dialog.
	 */
	public DialogSuaDichVu(String maDV) {
		dichVuDao = new DichVuDao(MainFrame.sessionFactory);
		dichVu = dichVuDao.layDichVuTheoMa(maDV);
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
		
		JLabel lblThmKhchHng = new JLabel("Cập Nhật Dịch Vụ");
		lblThmKhchHng.setOpaque(true);
		lblThmKhchHng.setHorizontalTextPosition(SwingConstants.CENTER);
		lblThmKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmKhchHng.setForeground(Color.WHITE);
		lblThmKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblThmKhchHng.setBackground(new Color(72, 209, 204));
		lblThmKhchHng.setBounds(0, 0, 836, 70);
		contentPanel.add(lblThmKhchHng);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tên dịch vụ");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(28, 96, 131, 35);
		contentPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Số lượng:");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(30, 183, 115, 35);
		contentPanel.add(lblNewLabel_2_3);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setBounds(172, 183, 224, 35);
		contentPanel.add(txtSoLuong);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonGia.setBounds(170, 272, 224, 35);
		contentPanel.add(txtDonGia);
		
		 btnHuy = new JButton("Huỷ");
		btnHuy.setMargin(new Insets(2, 0, 2, 0));
		btnHuy.setBackground(new Color(255, 99, 71));
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setIconTextGap(20);
		btnHuy.setIcon(new ImageIcon(DialogSuaKhuyenMai.class.getResource("/icon/cancel (2).png")));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setBounds(25, 353, 131, 40);
		contentPanel.add(btnHuy);
		btnHuy.addActionListener(this);
		
		btnLuu = new JButton("Cập nhật");
		btnLuu.setMargin(new Insets(2, 0, 2, 0));
		btnLuu.setBackground(new Color(60, 179, 113));
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setIconTextGap(2);
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
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Đơn vị");
		lblNewLabel_2_4_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2_4_1.setBounds(28, 272, 110, 35);
		contentPanel.add(lblNewLabel_2_4_1);
		
		cbTrangThai = new JComboBox<String>();
		cbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTrangThai.setBackground(Color.WHITE);
		cbTrangThai.setBounds(575, 271, 224, 36);
		cbTrangThai.addItem("Hoạt động");
		cbTrangThai.addItem("Tạm dừng");
		contentPanel.add(cbTrangThai);
		
		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setForeground(Color.BLACK);
		lblTrngThi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTrngThi.setBounds(431, 272, 147, 35);
		contentPanel.add(lblTrngThi);
		
		btnThuHi = new JButton("Thu hồi");
		btnThuHi.setMargin(new Insets(2, 0, 2, 0));
		btnThuHi.setIconTextGap(2);
		btnThuHi.setForeground(Color.WHITE);
		btnThuHi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThuHi.setBackground(Color.GRAY);
		btnThuHi.setBounds(512, 353, 131, 40);
		btnThuHi.addActionListener(this);
		contentPanel.add(btnThuHi);
		
		JLabel lblnV = new JLabel("Đơn vị");
		lblnV.setForeground(Color.BLACK);
		lblnV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblnV.setBounds(433, 183, 147, 35);
		contentPanel.add(lblnV);
		
		cbDonVi = new JComboBox<String>();
		cbDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbDonVi.setBackground(Color.WHITE);
		cbDonVi.setBounds(577, 182, 224, 36);
		cbDonVi.addItem("Chai");
		cbDonVi.addItem("Lon");
		cbDonVi.addItem("Thùng");
		cbDonVi.addItem("Bịch");
//		cbDonVi.addItem("P");
		cbDonVi.addItem("Khác");
		cbDonVi.setSelectedIndex(0);

		contentPanel.add(cbDonVi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		loadDuLieu(dichVu);
	}

	private void loadDuLieu(DichVu dv) {
		txtTen.setText(dv.getTenDichVu());
		txtDonGia.setText(dv.getDonGia() + "");
		txtSoLuong.setText(dv.getSoLuong() + "");
		cbTrangThai.setSelectedIndex(dv.isTrangThaiDichVu()?0:1);
		cbDonVi.setSelectedItem(dv.getDonViTinh());
	}
	private boolean isTrangThai(int selected) {
		//0 : true
		return selected==0?true:false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(btnHuy == o) {
			setVisible(false);
		} else 
			if(btnThuHi == o) {
				loadDuLieu(dichVu);
		} else if(btnLuu == o){
			if(kiemTraDuLieu()) {
				
				 double donGia = Double.parseDouble(txtDonGia.getText());
				String ten = txtTen.getText();
				 int soLuong = Integer.parseInt(txtSoLuong.getText());
				int trangThai = cbTrangThai.getSelectedIndex();
				dichVu.setDonViTinh(cbDonVi.getSelectedItem().toString());
				dichVu.setDonGia(donGia);
				dichVu.setSoLuong(soLuong);
				dichVu.setTenDichVu(ten);
				dichVu.setTrangThaiDichVu(isTrangThai(trangThai));
				if(dichVuDao.capNhatDichVu(dichVu)) {
					JOptionPane.showMessageDialog(null, "Cáº­p nháº­t dá»‹ch vá»¥ thÃ nh cÃ´ng");
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Cáº­p nháº­t dá»‹ch vá»¥ tháº¥t báº¡i");
				}
			}
		}
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
	public void foCus(JTextField txt) {
		txt.selectAll();
		txt.requestFocus();
	}

	private boolean kiemTraDuLieu() {
		if (txtTen.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "TÃªn khÃ´ng Ä‘Æ°á»£c trá»‘ng");
			foCus(txtTen);
			return false;
		}
		if (!txtTen.getText()
				.matches("[aAÃ Ã€áº£áº¢Ã£ÃƒÃ¡Ã�áº¡áº ÄƒÄ‚áº±áº°áº³áº²áºµáº´áº¯áº®áº·áº¶Ã¢Ã‚áº§áº¦áº©áº¨áº«áºªáº¥áº¤áº­áº¬bBcCdDÄ‘Ä�eEÃ¨Ãˆáº»áººáº½áº¼Ã©Ã‰áº¹áº¸ÃªÃŠá»�á»€á»ƒá»‚á»…á»„áº¿áº¾á»‡á»†"
						+ "fFgGhHiIÃ¬ÃŒá»‰á»ˆÄ©Ä¨Ã­Ã�á»‹á»ŠjJkKlLmMnNoOÃ²Ã’á»�á»ŽÃµÃ•Ã³Ã“á»�á»ŒÃ´Ã”á»“á»’á»•á»”á»—á»–á»‘á»�á»™á»˜Æ¡Æ á»�á»œá»Ÿá»žá»¡á» á»›á»šá»£á»¢pPqQrRsStTu"
						+ "UÃ¹Ã™á»§á»¦Å©Å¨ÃºÃšá»¥á»¤Æ°Æ¯á»«á»ªá»­á»¬á»¯á»®á»©á»¨á»±á»°vVwWxXyYá»³á»²á»·á»¶á»¹á»¸Ã½Ã�á»µá»´zZ0-9 ]+")) {
			JOptionPane.showMessageDialog(this, "TÃªn khÃ´ng cÃ³ kÃ­ tá»± Ä‘áº·c biá»‡t");
			foCus(txtTen);
			return false;
		}
		if (txtSoLuong.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Sá»‘ lÆ°á»£ng khÃ´ng Ä‘Æ°á»£c trá»‘ng");
			foCus(txtSoLuong);
			return false;
		}
		try {
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			if(soLuong < 0) {
				JOptionPane.showMessageDialog(this, "Sá»‘ lÆ°á»£ng pháº£i lá»›n hÆ¡n 0");
				foCus(txtSoLuong);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Sá»‘ lÆ°á»£ng lÃ  chá»­ sá»‘");
			foCus(txtSoLuong);
			return false;
		}
		if (txtDonGia.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Ä�Æ¡n giÃ¡ khÃ´ng Ä‘Æ°á»£c trá»‘ng");
			foCus(txtDonGia);
			return false;
		}
		try {
			double donGia = Double.parseDouble(txtDonGia.getText());
			if(donGia < 0) {
				JOptionPane.showMessageDialog(this, "Ä�Æ¡n giÃ¡ pháº£i lá»›n hÆ¡n 0");
				foCus(txtDonGia);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Ä�Æ¡n giÃ¡ lÃ  chá»­ sá»‘");
			foCus(txtDonGia);
			return false;
		}
		return true;
	}
}
