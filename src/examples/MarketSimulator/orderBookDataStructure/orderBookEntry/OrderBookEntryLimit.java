package examples.MarketSimulator.orderBookDataStructure.orderBookEntry;

import examples.MarketSimulator.marketInstance.MarketAgent;
import examples.MarketSimulator.marketInstance.MarketLogTable;
import examples.MarketSimulator.marketInstance.MarketMessageSender;
import examples.MarketSimulator.orderBookDataStructure.OrderBookList;

public class OrderBookEntryLimit extends OrderBookEntryType {

	private OrderBookList orderBookListExecute, orderBookListBook;
	private int price, quantity;
	private OrderBookEntry orderBookEntry;

	public OrderBookEntryLimit(OrderBookList orderBookListExecute, OrderBookList orderBookListBook) {

		this.orderBookListExecute = orderBookListExecute;
		this.orderBookListBook = orderBookListBook;	
	}
	
	public void bookOrder(OrderBookEntry orderBookEntry) {
		
		orderBookListBook.bookOrder(orderBookEntry);
	}
	
	@Override
	public int getPrice() {
	
		if (orderBookListExecute.size() != 0) {
			
			return this.orderBookEntry.getPrice();
		} else {
			
			return 0;
		}
	}
	
	public void execute(OrderBookEntry orderBookEntry) {
		
		this.orderBookEntry = orderBookEntry;
		quantity = orderBookEntry.getQuantity();
		price = getPrice();
		OrderBookEntry bestOrder;
		
		if (price != 0) {
			
			while (quantity > 0) {
				
				bestOrder = orderBookListExecute.getBestPrice().get(0); 
				
				//	If order is at better Price and Smaller Quantity than the NEXT BEST ORDER
				if (quantity < bestOrder.getQuantity() && price <= bestOrder.getPrice()*-1) {

					// Fill
					orderBookListExecute.updateTable(bestOrder.getOrderID(), "Partial Fill (" + (bestOrder.getQuantity()-quantity) + ")",
							MarketLogTable.UpdateType.PARTIAL_FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							bestOrder.getOrderID().substring(0,7),
							bestOrder.getOrderID(),
							"PARTIAL_FILL",
							"Partial Fill (" + (bestOrder.getQuantity()-quantity) + ")"));
					
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED (@"+Math.abs(bestOrder.getPrice())+")",
							MarketLogTable.UpdateType.FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							orderBookEntry.getOrderID().substring(0,7),
							orderBookEntry.getOrderID(),
							"FILLED",
							"FILLED (@"+Math.abs(bestOrder.getPrice())+")"));
					
					orderBookListExecute.updatePairTable(bestOrder.getOrderID() + "-" + orderBookEntry.getOrderID(),
							bestOrder.getPrice(), quantity);
					
					bestOrder.ammendQuantity(
							bestOrder.getQuantity() - quantity);
					
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					
					break;
					
				//	If order is at better Price and Bigger Quantity than the NEXT BEST ORDER
				} else if (quantity > bestOrder.getQuantity() && price <= bestOrder.getPrice()*-1) {
					
					// Partial Fill
					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							bestOrder.getOrderID().substring(0,7),
							bestOrder.getOrderID(),
							"FILLED",
							"FILLED"));
					
					orderBookListExecute.updatePairTable(bestOrder.getOrderID() + "-" + orderBookEntry.getOrderID(),
							bestOrder.getPrice(), bestOrder.getQuantity());
					
					quantity -= bestOrder.getQuantity();
					orderBookEntry.setQuantity(quantity);
					orderBookListExecute.removeBestOrder();
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					
					if (orderBookListExecute.size() == 0) {

						bookOrder(orderBookEntry);
						orderBookListExecute.updateTable(orderBookEntry.getOrderID(), quantity, "Booked at " + Math.abs(orderBookEntry.getPrice()),
								MarketLogTable.UpdateType.NONE);
						
						MarketAgent.agent.addBehaviour(new MarketMessageSender(
								orderBookEntry.getOrderID().substring(0,7),
								orderBookEntry.getOrderID(),
								"NONE",
								"Booked at " + Math.abs(orderBookEntry.getPrice())));

						break;
					}

				//	If order is at better Price and Equal Quantity than the NEXT BEST ORDER
				} else if (quantity == bestOrder.getQuantity() && price <= bestOrder.getPrice()*-1) {
					
					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							bestOrder.getOrderID().substring(0,7),
							bestOrder.getOrderID(),
							"FILLED",
							"FILLED"));
					
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED (@"+Math.abs(bestOrder.getPrice())+")",
							MarketLogTable.UpdateType.FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							orderBookEntry.getOrderID().substring(0,7),
							orderBookEntry.getOrderID(),
							"FILLED",
							"FILLED (@"+Math.abs(bestOrder.getPrice())+")"));
					
					orderBookListExecute.updatePairTable(bestOrder.getOrderID() + "-" + orderBookEntry.getOrderID(),
							bestOrder.getPrice(), quantity);
					
					quantity -= bestOrder.getQuantity();
					orderBookEntry.setQuantity(quantity);
					orderBookListExecute.removeBestOrder();
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					
					break;
					
				//	If order is at a worse Price and Smaller Quantity than the NEXT BEST ORDER
				} else {

					bookOrder(orderBookEntry);
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), quantity, "Booked at " + Math.abs(orderBookEntry.getPrice()),
							MarketLogTable.UpdateType.NONE);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							orderBookEntry.getOrderID().substring(0,7),
							orderBookEntry.getOrderID(),
							"NONE",
							"Booked at " + Math.abs(orderBookEntry.getPrice())));
					
					break;
				}
			}
			
		} else {

			bookOrder(orderBookEntry);
			orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "Booked at " + Math.abs(orderBookEntry.getPrice()),
					MarketLogTable.UpdateType.NONE);
			
			MarketAgent.agent.addBehaviour(new MarketMessageSender(
					orderBookEntry.getOrderID().substring(0,7),
					orderBookEntry.getOrderID(),
					"NONE",
					"Booked at " + Math.abs(orderBookEntry.getPrice())));
		}
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
