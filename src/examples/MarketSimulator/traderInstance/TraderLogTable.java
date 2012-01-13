package examples.MarketSimulator.traderInstance;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TraderLogTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static DefaultTableModel model = new DefaultTableModel();
	public TraderLogTable() {	

		super(model);
		this.setShowHorizontalLines( true );
		this.setRowSelectionAllowed( true );
		this.setColumnSelectionAllowed( true );
		
		this.setSelectionForeground( Color.white );
		this.setSelectionBackground( Color.red );

		model.addColumn("OrderID");
		model.addColumn("Type");
		model.addColumn("Quantity");
		model.addColumn("Price");
		model.addColumn("Comment");
		// Add the table to a scrolling pane
		
	}
	
	@SuppressWarnings("deprecation")
	public JScrollPane getScrollPane() {
		
		return JTable.createScrollPaneForTable( this );
	}
	
	public void addRow(Object[] data) {
		
		model.addRow(data);
	}
}
