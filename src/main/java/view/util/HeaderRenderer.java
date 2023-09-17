package view.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class HeaderRenderer  extends JLabel implements TableCellRenderer{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color fgColor = UIManager.getColor("TableHeader.foreground");
	  private Color bgColor = UIManager.getColor("TableHeader.background");

	 public HeaderRenderer() {
	    setOpaque(true);
	 }

	     public void setForegroundColor(Color fgColor) {
	       this.fgColor=fgColor;
	     }
	     public Color getForegroundColor() {
	       return fgColor;
	     }
	     public void setBackgroundColor(Color bgColor) {
	       this.bgColor=bgColor;
	     }
	     public Color getBackgroundColor() {
	       return bgColor;
	     }

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			 JLabel label = this;
             label.setText((value ==null) ? "" : (" "+value.toString()));
             label.setForeground(getForegroundColor());
            label.setBackground(getBackgroundColor());
                return label;
		}

	     
}
