package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class ThongKePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnQuayLai;
	private JTabbedPane tabbedPane;

	public ThongKePanel() {

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

		setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setSize(1295, 669);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

		setBackground(Color.WHITE);
		setSize(1295, 700);
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(0, 206, 209));

		JLabel lblNewLabel_1 = new JLabel("Thống kê doanh thu");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setForeground(Color.WHITE);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 15));

		JPanel panelThongKeDoanhThuTheoNgay = new ThongKeTheoNgayPanel();
		tabbedPane.addTab("Thống kê theo ngày", null, panelThongKeDoanhThuTheoNgay, null);
		panelThongKeDoanhThuTheoNgay.setLayout(null);
		JButton btnQuayLai_2 = new JButton("Quay lại");
		btnQuayLai_2.setBounds(1085, 230, 91, 27);
		btnQuayLai_2.setForeground(Color.WHITE);
		btnQuayLai_2.setFont(new Font("Arial", Font.PLAIN, 16));
		btnQuayLai_2.setBackground(new Color(0, 127, 255));
		panelThongKeDoanhThuTheoNgay.add(btnQuayLai_2);

		JPanel panelThongKeDoanhThuThang = new ThongKeTheoThangPanel();
		tabbedPane.addTab("Thống kê theo tháng", null, panelThongKeDoanhThuThang, null);
		panelThongKeDoanhThuThang.setLayout(null);
		panelThongKeDoanhThuThang.setLayout(null);

		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setBounds(10, 506, 120, 30);
		panelThongKeDoanhThuThang.add(btnQuayLai);
		btnQuayLai.setForeground(Color.WHITE);

		btnQuayLai.setBackground(new Color(0, 127, 255));

		btnQuayLai.setIcon(new ImageIcon(ThongKePanel.class.getResource("/icon/back-button.png")));
		btnQuayLai.setFont(new Font("Arial", Font.PLAIN, 16));

		JPanel panelThongKeDoanhThuNam = new ThongKeTheoNamPanel();
		tabbedPane.addTab("Thống kê theo Năm", null, panelThongKeDoanhThuNam, null);

		JButton btnQuayLai_1 = new JButton("Quay lại");
		btnQuayLai_1.setForeground(Color.WHITE);
		btnQuayLai_1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnQuayLai_1.setBackground(new Color(0, 127, 255));
		btnQuayLai_1.setBounds(10, 506, 120, 30);
		panelThongKeDoanhThuNam.add(btnQuayLai_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(panelHeader, GroupLayout.DEFAULT_SIZE, 1275, Short.MAX_VALUE)
					.addGap(3))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
					.addGap(15))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panelHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane)
					.addGap(15))
		);
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelHeader.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 1255, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelHeader.setVerticalGroup(
			gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelHeader.setLayout(gl_panelHeader);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(8))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	public void selectTab(int index) {
		tabbedPane.setSelectedIndex(index);
	}
}
