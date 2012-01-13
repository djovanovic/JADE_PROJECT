package examples.MarketSimulator.orderBookGui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import examples.MarketSimulator.marketInstance.MarketGui;
import examples.MarketSimulator.marketInstance.MarketGuiFrame;
import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntry;

public class OrderBookGuiAddOrder {

	private Graphics2D g2d;
	private int widthOffset, heightOffset, offset;
	private static final int sepWidth = 2;
	
	public OrderBookGuiAddOrder(Graphics g) {

		
		this.g2d = (Graphics2D) g;
	}
	
	public void addOrder(int quantity, int price) {
	  
		
		if (price > 0) {

			heightOffset = MarketGuiFrame.FRAMEHEIGHT-price;
			widthOffset = MarketGuiFrame.FRAMEWIDTH/2+3;
			
			g2d.setColor(Color.green);
			g2d.fill(new Rectangle2D.Double(widthOffset+offset,
					heightOffset, quantity, 1));
			
			// SEPARATOR
			g2d.setColor(Color.black);
			g2d.fill(new Rectangle2D.Double(widthOffset+quantity+offset,
					heightOffset, sepWidth, 1));
			
		} else {

			heightOffset = MarketGuiFrame.FRAMEHEIGHT+price;
			widthOffset = MarketGuiFrame.FRAMEWIDTH/2-3;
			
			g2d.setColor(Color.pink);
			g2d.fill(new Rectangle2D.Double(widthOffset-quantity-offset,
					heightOffset, quantity, 1));			
			
			// SEPARATOR
			g2d.setColor(Color.black);
			g2d.fill(new Rectangle2D.Double(widthOffset-quantity-sepWidth-offset,
					heightOffset, sepWidth, 1));
			
		}

		offset += quantity+sepWidth;
	}
	
	public void clearPrice(int price) {
		
		g2d.setColor(Color.WHITE);
		if (price < 0) {
			
			g2d.fill(new Rectangle2D.Double(0,
					MarketGuiFrame.FRAMEHEIGHT+price, MarketGui.FRAMEWIDTH/2-3, 1));
		} else {

			g2d.fill(new Rectangle2D.Double(MarketGui.FRAMEWIDTH/2+3,
					MarketGuiFrame.FRAMEHEIGHT-price, MarketGui.FRAMEWIDTH/2-3, 1));
		}
	}
	
	public void renderOrderStack(ArrayList<OrderBookEntry> priceListOrders) {
		
		offset = 0;
		for (OrderBookEntry o : priceListOrders) {
			
			addOrder(o.getQuantity(), o.getPrice());
		}
	}
}
