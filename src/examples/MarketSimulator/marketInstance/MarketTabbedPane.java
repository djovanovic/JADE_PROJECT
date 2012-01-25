package examples.MarketSimulator.marketInstance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class MarketTabbedPane extends JPanel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarketLogTable marketLogTable;
	public MarketPairTable marketPairTable;
	private JScrollPane logTablePanel, pairTablePanel;
	private JTabbedPane jTabbedPane;
	//private JButton jButton;
	
	public MarketTabbedPane() {
		
		//jButton = new JButton();
		
		jTabbedPane = new JTabbedPane();
		marketLogTable = new MarketLogTable();
		marketPairTable = new MarketPairTable();
		
		render();
	}

	public void render() {	

		logTablePanel = new JScrollPane(marketLogTable);
		pairTablePanel = new JScrollPane(marketPairTable);
		
		//jButton.addActionListener(showHide()); 

		logTablePanel.setPreferredSize(new Dimension(690, 200));
		pairTablePanel.setPreferredSize(new Dimension(690, 200));

		jTabbedPane.addTab("Order List", logTablePanel);
		jTabbedPane.addTab("Pair List", pairTablePanel);

		//this.add(jButton, BorderLayout.NORTH);
		this.add(jTabbedPane,BorderLayout.CENTER);
	}
	
	public ActionListener showHide() {
		
		return new ActionListener() {
			 
            public void actionPerformed(ActionEvent e) {
            	
            	if (jTabbedPane.isVisible()) jTabbedPane.setVisible(false);
            	else jTabbedPane.setVisible(true);
            }
		};
	}
}
