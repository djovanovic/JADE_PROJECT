package examples.MarketSimulator.marketInstance;

import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntry;
import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntryType;

public class MarketControler {
	
	private OrderBookEntry orderBookEntry;
	
	/*
	 * 		Constructor books trade onto the Order Book and Market Book List. Only handles BIDS and ASKS.
	 */
	
	public MarketControler(String orderID, OrderBookEntryType type, int quantity, int price) {
		
		this.orderBookEntry = new OrderBookEntry(orderID, type, quantity, price);
		MarketAgent.agent.marketOrderList.add(new MarketOrder(orderID, type, Integer.toString(quantity), Integer.toString(price)));
	}
	
	/*
	 *		Constructor books trade onto the Order Book List. Only handles BUYS and SELLS.
	 */
	
	public MarketControler(String orderID, OrderBookEntryType type, int quantity) {

		this.orderBookEntry = new OrderBookEntry(orderID, type, quantity);
		MarketAgent.agent.marketOrderList.add(new MarketOrder(orderID, type, Integer.toString(quantity), "-"));
	}
	
	public void handle() {
		
		this.orderBookEntry.execute();
	}
	
	/*
	public static void main(String args[]) {
		
		MarketAgent marketAgent = new MarketAgent();
		MarketGui marketGui = (MarketGui) MarketAgent.agent.agentGui;
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new MarketMockSetupOffers();

		MarketAgent.agent.orderBook.orderBookOffers.printTree();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
