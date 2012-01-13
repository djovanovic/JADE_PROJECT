package examples.MarketSimulator.orderBookGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import examples.MarketSimulator.marketInstance.MarketGui;
import examples.MarketSimulator.marketInstance.MarketGuiFrame;

public class OrderBookGuiYAxis {

	private Graphics2D g2d;
	private Line2D.Double line;
	
	public OrderBookGuiYAxis(Graphics g) {
		
		g2d = (Graphics2D) g;
		line = new Line2D.Double(MarketGuiFrame.FRAMEWIDTH/2, 0,
				MarketGuiFrame.FRAMEWIDTH/2, MarketGuiFrame.FRAMEHEIGHT);
		draw();
	}
	
	public void draw() {

		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle2D.Double(0,
				0, MarketGui.FRAMEWIDTH, MarketGuiFrame.FRAMEHEIGHT));

		g2d.setColor(Color.BLACK);
		g2d.draw(line);
		
		Integer y = MarketGuiFrame.FRAMEHEIGHT;
		for (int i = 10; i < MarketGuiFrame.FRAMEHEIGHT; i += 10) {
			y -= 10;
			g2d.setColor(Color.black);
			g2d.draw(new Line2D.Double(MarketGuiFrame.FRAMEWIDTH/2-3, i, MarketGuiFrame.FRAMEWIDTH/2+3, i));
        
			if (i%6 == 0) {
    		
				g2d.setFont(new Font("LucidaBrightDemiBold", Font.BOLD, 10));
				g2d.setColor(Color.GRAY);
				g2d.drawString(y.toString(), MarketGuiFrame.FRAMEWIDTH/2-10, i+5);
			}
		}
	}
}
