package examples.MarketSimulator.marketInstance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import examples.MarketSimulator.AgentGui;
import examples.MarketSimulator.StackLayout;
import examples.MarketSimulator.orderBookGui.OrderBookGui;
import examples.MarketSimulator.traderInstance.TraderLogTable;

public class MarketGuiFrame {

	public static final int FRAMEWIDTH = 700;
	public static final int FRAMEHEIGHT = 600;
	
	private JFrame marketFrame;
	public OrderBookGui orderBookGui;
	public MarketLogTable marketLogTable;
	
	public MarketGuiFrame() {
		
		marketFrame = new JFrame();
		marketFrame.setLayout(new StackLayout(1));
		
		orderBookGui = new OrderBookGui();
		orderBookGui.setPreferredSize(new Dimension(700, 600));
		
		render();
	}
	
	private void render() {
		
		marketFrame.add(orderBookGui);
		
		// Add LogTable
		JPanel marketLogTablePanel = new JPanel();		
		marketLogTable = new MarketLogTable();		

		JScrollPane pane = marketLogTable.getScrollPane();
		pane.setPreferredSize(new Dimension(690, 230));
		marketLogTablePanel.add(pane, BorderLayout.CENTER);
		
		marketFrame.add(marketLogTablePanel);

	}

	public void show() {
		
		GraphicsConfiguration gc = marketFrame.getGraphicsConfiguration();  
		Rectangle bounds = gc.getBounds();
		   
		// Set the Location and Activate  
		marketFrame.setLocation((int) ((bounds.width / 2) - (FRAMEWIDTH) - 50), (int) ((bounds.height / 2) - (FRAMEHEIGHT / 2))); 

		marketFrame.setSize(FRAMEWIDTH, FRAMEHEIGHT+330);
		marketFrame.pack();
		marketFrame.setVisible(true);
	}
}