package examples.MarketSimulator.marketInstance;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class MouseListener extends MouseAdapter {

	private JTable jTable;
	private int selection;
	
	public MouseListener(JTable jTable) {
		
		this.jTable = jTable;
	}
	
    @Override
    public void mousePressed(MouseEvent e) {
        this.jTable = (JTable) e.getSource();
        this.selection = jTable.getSelectedRow();
    	this.jTable.setRowSelectionInterval(selection, selection);
    	this.jTable.addRowSelectionInterval(selection, selection);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        this.jTable = (JTable) e.getSource();
        
        System.out.println(jTable.getValueAt(selection, 0));

        //now you need to select the row here check below link
        //http://www.exampledepot.com/egs/javax.swing.table/Sel.html
    }
}
