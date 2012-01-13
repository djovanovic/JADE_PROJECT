package examples.MarketSimulator.orderBookGui;

import javax.swing.*; // For JPanel, etc.
import java.awt.*;    // For Graphics, etc.

public class OrderBookGui extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics2D g2d;
	
	public OrderBookGui() {

	}

	public void paintComponent(Graphics g) {
		
		clear(g);
		g2d = (Graphics2D)g;
		
		new OrderBookGuiYAxis(g2d);
	}

	protected void clear(Graphics g) {
		super.paintComponent(g);
  	}
}