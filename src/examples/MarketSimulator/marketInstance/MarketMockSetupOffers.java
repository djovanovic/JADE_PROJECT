package examples.MarketSimulator.marketInstance;

import java.util.Random;

import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntryType;

public class MarketMockSetupOffers extends Thread {
	
	private static Random generator = new Random();
	private static Random generator1 = new Random();

	public void setup() {

		int y = 0;
		for (int r = 0 ; r <= 500;) {
			
		    int price = generator.nextInt(420) - 315;
		    int quantity = generator1.nextInt(50) + 1;
		    if (price > 0) {
		    	y=y+1;
		    	new MarketControler("Offer" + y, OrderBookEntryType.OFFER, quantity, 420-price).handle();
		    	r++;
		    }
		}
		
		for (int r = 0 ; r <= 300;) {
			
		    int price = generator1.nextInt(500) - 420;
		    int quantity = generator1.nextInt(50) + 1;
		    if (price > 0) {
		    	y=y+1;
		    	new MarketControler("Offer" + y, OrderBookEntryType.OFFER, quantity, 500-price).handle();
		    	r++;
		    }
		}
		
		for (int r = 0 ; r <= 150;) {
			
		    int price = generator1.nextInt(600) - 500;
		    int quantity = generator1.nextInt(50) + 1;
		    if (price > 0) {
		    	y=y+1;
		    	new MarketControler("Offer" + y, OrderBookEntryType.OFFER, quantity, 600-price).handle();
		    	r++;
		    }
		}
	}
	
	public void run() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setup();
	}
}
