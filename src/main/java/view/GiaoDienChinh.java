package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GiaoDienChinh extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GiaoDienChinh() {
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(GiaoDienChinh.class.getResource("/icon/bg7.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 1262, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
	}

	

}
