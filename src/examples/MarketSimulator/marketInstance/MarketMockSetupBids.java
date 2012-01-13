package examples.MarketSimulator.marketInstance;

import java.util.Random;

import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntryType;

public class MarketMockSetupBids extends Thread {
	
	private static Random generator = new Random();
	private static Random generator1 = new Random();

	public void setup() {

		int y = 0;
		for (int r = 0 ; r <= 500;) {
			
		    int price = generator.nextInt(285) - 180;
		    int quantity = generator1.nextInt(50) + 1;
		    if (price > 0) {
		    	y=y+1;
		    	new MarketControler("Bid" + y, OrderBookEntryType.BID, quantity, -(285-price)).handle();
		    	r++;
		    }
		}
		
		for (int r = 0 ; r <= 300;) {
			
		    int price = generator1.nextInt(180) - 100;
		    int quantity = generator1.nextInt(50) + 1;
		    if (price > 0) {
		    	y=y+1;
		    	new MarketControler("Bid" + y, OrderBookEntryType.BID, quantity, -(180-price)).handle();
		    	r++;
		    }
		}
		
		for (int r = 0 ; r <= 150;) {
			
		    int price = generator1.nextInt(100);
		    int quantity = generator1.nextInt(50) + 1;
		    if (price > 0) {
		    	y=y+1;
		    	new MarketControler("Bid" + y, OrderBookEntryType.BID, quantity, -(100-price)).handle();
		    	r++;
		    }
		}
	}
	
	public void run() {
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setup();
	}
}
