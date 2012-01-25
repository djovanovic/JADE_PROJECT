package examples.MarketSimulator.marketInstance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class MarketPairTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static DefaultTableModel model = new DefaultTableModel();
	
	private ArrayList<Color> rowProperties = new ArrayList<Color>();
	
	public MarketPairTable() {	

		super(model);

		this.setShowGrid(true);
		this.setGridColor(Color.gray);
		
		
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(false);
		this.setCellSelectionEnabled(false);
		this.setForeground(Color.BLACK);
		
		
        this.addMouseListener(new MouseListener(this));

		model.addColumn("PairID");
		model.addColumn("Quantity");
		model.addColumn("Price");	
	}
	
    @Override
	public boolean isCellEditable(int row, int column) {
        return false;
    }
	
	public Component prepareRenderer(TableCellRenderer tableCellRenderer,int row, int column) {
		
			Component c = super.prepareRenderer(tableCellRenderer, row, column);
			
			// Pushes scroll bad to the bottom.
	 		this.scrollRectToVisible(new Rectangle(this.getCellRect(this.getRowCount(), 0, true)));
	 		
	 		if (isRowSelected(row)) {
	 			  c.setBackground(Color.LIGHT_GRAY);
	 			  c.setForeground(Color.BLACK);
	 		}
	 		
			return c;
	}
	
	public void addRow(Object[] data) {
		
 		model.addRow(data);
 		rowProperties.add(Color.white);
	}
	
	public static enum UpdateType { FILL, PARTIAL_FILL, REJECT, PART_REJECT, NONE }
}
