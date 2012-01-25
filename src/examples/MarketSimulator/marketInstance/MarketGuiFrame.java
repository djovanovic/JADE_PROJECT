package examples.MarketSimulator.marketInstance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import examples.MarketSimulator.StackLayout;
import examples.MarketSimulator.orderBookGui.OrderBookGui;

public class MarketGuiFrame {

	public static final int FRAMEWIDTH = 700;
	public static final int FRAMEHEIGHT = 600;
	
	private JFrame marketFrame;
	private JPanel marketFramePanel, marketLogTablePanel;
	public OrderBookGui orderBookGui;
	public MarketTabbedPane marketTabbedPane;
	
	
	public MarketGuiFrame() {
		
		marketFrame = new JFrame();
		marketFramePanel = new JPanel();
		marketFramePanel.setLayout(new StackLayout(1));
		
		orderBookGui = new OrderBookGui();
		orderBookGui.setPreferredSize(new Dimension(700, 600));
		
		render();
	}
	
	private void render() {
		
		marketFramePanel.add(orderBookGui);
		
		// Add LogTable
		marketLogTablePanel = new JPanel();		
		marketTabbedPane = new MarketTabbedPane();
		
		marketLogTablePanel.add(marketTabbedPane, BorderLayout.CENTER);
		
		marketFramePanel.add(marketLogTablePanel);

	}

	public void show() {
		
		GraphicsConfiguration gc = marketFrame.getGraphicsConfiguration();  
		Rectangle bounds = gc.getBounds();
		   
		//marketFramePanel.setSize(FRAMEWIDTH, FRAMEHEIGHT+100);
		//JScrollPane frameScrollPane = new JScrollPane(marketFramePanel);
		
		marketFrame.add(marketFramePanel);
		
		// Set the Location and Activate  
		marketFrame.setLocation((int) ((bounds.width / 2) - (FRAMEWIDTH) - 50), (int) ((bounds.height / 2) - (FRAMEHEIGHT / 2))); 

		marketFrame.setSize(FRAMEWIDTH, FRAMEHEIGHT+400);
		marketFrame.pack();
		//JScrollPane frameScrollPane = new JScrollPane(marketFrame);
		
		marketFrame.setVisible(true);
	}
}