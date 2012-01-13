package examples.MarketSimulator.marketInstance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView.TableRow;


public class MarketLogTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static DefaultTableModel model = new DefaultTableModel();
	
	private ArrayList<Color> rowProperties = new ArrayList<Color>();
	
	public MarketLogTable() {	

		super(model);
		this.setShowHorizontalLines( true );
		this.setRowSelectionAllowed( true );
		this.setColumnSelectionAllowed( true );
		
		this.setSelectionForeground( Color.white );

		model.addColumn("OrderID");
		model.addColumn("Type");
		model.addColumn("Quantity");
		model.addColumn("Price");
		model.addColumn("Comment");


		//this.setDefaultRenderer(null, new YourTableCellRenderer());
		
		
		// Add the table to a scrolling pane
		
	}
	
	public Component prepareRenderer(TableCellRenderer tableCellRenderer,int row, int column) {
		
			Component c = super.prepareRenderer(tableCellRenderer, row, column);
			
			c.setBackground(rowProperties.get(row));
			return c;
	}
	
	public JScrollPane getScrollPane() {
		
		return new JScrollPane(this);
	}
	
	public void addRow(Object[] data) {
		
 		model.addRow(data);
 		rowProperties.add(Color.white);
	}
	
	public void updateTable(String orderID, String comment, MarketLogTable.UpdateType updateType) {
		
		for (int i = 0 ; i < this.getRowCount() ; i ++) {
			
			if (this.getValueAt(i, 0).equals(orderID)) {
				
				System.out.println(i);
				switch (updateType) {

					case FILL: rowProperties.set(i, Color.green);
					break;
					case PARTIAL_FILL: rowProperties.set(i, Color.yellow);
					break;
					case REJECT: rowProperties.set(i, Color.red);
					break;
					case PART_REJECT: rowProperties.set(i, Color.pink);
					break;
					case NONE: rowProperties.set(i, Color.white);
					break;
				}
				
				this.setValueAt(comment, i, 4);
			}
			
			
		}
	}
	
	public static enum UpdateType { FILL, PARTIAL_FILL, REJECT, PART_REJECT, NONE }
}
