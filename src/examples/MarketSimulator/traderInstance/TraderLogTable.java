package examples.MarketSimulator.traderInstance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import examples.MarketSimulator.marketInstance.MarketLogTable;
import examples.MarketSimulator.marketInstance.MouseListener;
import examples.MarketSimulator.marketInstance.MarketLogTable.UpdateType;

public class TraderLogTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DefaultTableModel model = new DefaultTableModel();
	private boolean updated = false;

	private ArrayList<Color> rowProperties = new ArrayList<Color>();

	
	public TraderLogTable() {	

		super(new DefaultTableModel());
		model = (DefaultTableModel) this.getModel();

		this.setShowGrid(true);
		this.setGridColor(Color.gray);
		
		
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(false);
		this.setCellSelectionEnabled(false);
		this.setForeground(Color.BLACK);

        this.addMouseListener(new MouseListener(this));

		model.addColumn("OrderID");
		model.addColumn("Type");
		model.addColumn("Quantity");
		model.addColumn("Price");
		model.addColumn("Comment");
		// Add the table to a scrolling pane
		
	}

    @Override
	public boolean isCellEditable(int row, int column) {
        return false;
    }
	
	public Component prepareRenderer(TableCellRenderer tableCellRenderer,int row, int column) {
		
			Component c = super.prepareRenderer(tableCellRenderer, row, column);
			
			c.setBackground(rowProperties.get(row));
			
	 		if (isRowSelected(row)) {
	 			  c.setBackground(Color.LIGHT_GRAY);
	 			  c.setForeground(Color.BLACK);
	 		}
	 		
	 		if (updated) {
	 			
				this.scrollRectToVisible(new Rectangle(this.getCellRect(model.getRowCount(), 0, true)));
				updated = false;
	 		}
	 		
			this.repaint();
			return c;
	}
	
	public void addRow(Object[] data) {
		
		model.addRow(data);
 		rowProperties.add(Color.white);
 		
 		updated = true;
	}
	
	public void updateTable(String orderID, String comment, TraderLogTable.UpdateType updateType) {
		
		for (int i = 0 ; i < this.getRowCount() ; i ++) {
			
			if (this.getValueAt(i, 0).equals(orderID)) {
				
				this.paintRow(updateType, i);
				System.out.println(updateType.toString());
				this.setValueAt(comment, i, 4);
			}
		}
	}
	
	public void paintRow(UpdateType updateType, int i) {
		
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
	}
	
	public static enum UpdateType { FILL, PARTIAL_FILL, REJECT, PART_REJECT, NONE }

}
