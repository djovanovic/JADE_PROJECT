package examples.MarketSimulator.marketInstance;

import examples.MarketSimulator.AgentOrder;
import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntryType;

public class MarketOrder extends AgentOrder {

	private String orderID;
	
	public MarketOrder(String orderID, OrderBookEntryType type, String quantity) {
		
		super(type.getType(), quantity);
		this.orderID = orderID;
	}
	
	public MarketOrder(String orderID, OrderBookEntryType type, String quantity, String price) {

		super(type.getType(), quantity, price);
		this.orderID = orderID;
	}
	
	public String getOrderID() {
		
		return orderID;
	}
}
