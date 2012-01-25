import javax.swing.event.TableModelEvent;

import examples.MarketSimulator.marketInstance.MarketLogTable;


public class TableListener extends MarketLogTable {

    @Override
	public void tableChanged(TableModelEvent e) {
		
		System.out.println("Changed");
	}
}
