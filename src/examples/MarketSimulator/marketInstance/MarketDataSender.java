package examples.MarketSimulator.marketInstance;

import java.util.ArrayList;

import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntry;
import jade.core.behaviours.CyclicBehaviour;

public class MarketDataSender extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private	int[][] quantity = new int[2][5];

	@Override
	public void action() {
		
		try {			
			for (int i = 0 ; i < 5 ; i++) {

				quantity[0][i] = MarketAgent.agent.orderBook.orderBookBids.get(i).get(0).getPrice();
				quantity[1][i] = 0;
				for (OrderBookEntry o : MarketAgent.agent.orderBook.orderBookBids.get(i)) {
					
					quantity[1][i] += o.getQuantity();
				}
			}
			
			for (int i = 0; i < 5; i++) {
				
				System.out.println(quantity[0][i] + ":" + quantity[1][i]);
			}
			
			System.out.println("----");
			
		} catch (IndexOutOfBoundsException ioobe) {


			for (int i = 0; i < 5; i++) {
				
				System.out.println(quantity[0][i] + ":" + quantity[1][i]);
			}
			
			System.out.println("----");

		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
