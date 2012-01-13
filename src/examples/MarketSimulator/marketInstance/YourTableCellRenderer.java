package examples.MarketSimulator.marketInstance;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

	public class YourTableCellRenderer extends DefaultTableCellRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.
					getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			//if (row == MarketLogTable.SPECIAL_ROW) {
			//	c.setForeground(Color.green);
			//}
		return c;
	}
}