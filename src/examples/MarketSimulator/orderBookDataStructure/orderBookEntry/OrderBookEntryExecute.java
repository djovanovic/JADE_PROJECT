package examples.MarketSimulator.orderBookDataStructure.orderBookEntry;

import examples.MarketSimulator.marketInstance.MarketAgent;
import examples.MarketSimulator.marketInstance.MarketLogTable;
import examples.MarketSimulator.marketInstance.MarketMessageSender;
import examples.MarketSimulator.orderBookDataStructure.OrderBookList;

public class OrderBookEntryExecute extends OrderBookEntryType {

	private int price = 0;
	private OrderBookList orderBookListExecute;

	public OrderBookEntryExecute(OrderBookList orderBookListExecute, OrderBookList orderBookListBook) {

		this.orderBookListExecute = orderBookListExecute;
	}
	
	public int getPrice() {
		
		if (orderBookListExecute.size() != 0) {
			
			price = orderBookListExecute.get(0).get(0).getPrice();
		} else {
			this.price = 0;
		}

	return price;
	}
	
	public void execute(OrderBookEntry orderBookEntry) {
		
		int quantity = orderBookEntry.getQuantity();
		OrderBookEntry bestOrder;

		if (orderBookEntry.getPrice() != 0) {
			
			while (quantity > 0) {
				
				bestOrder = orderBookListExecute.getBestPrice().get(0); 
				 
				if (quantity < bestOrder.getQuantity()) {

					orderBookListExecute.updateTable(bestOrder.getOrderID(), "Partial Fill (" + (bestOrder.getQuantity()-quantity) + ")",
							MarketLogTable.UpdateType.PARTIAL_FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							bestOrder.getOrderID().substring(0,7),
							bestOrder.getOrderID(),
							"PARTIAL_FILL",
							"Partial Fill (" + (bestOrder.getQuantity()-quantity) + ")"));
					
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							orderBookEntry.getOrderID().substring(0,7),
							orderBookEntry.getOrderID(),
							"FILLED",
							"FILLED"));
					
					orderBookListExecute.updatePairTable(bestOrder.getOrderID() + "-" + orderBookEntry.getOrderID(),
							bestOrder.getPrice(), quantity);
					
					// Fill
					bestOrder.ammendQuantity(bestOrder.getQuantity() - quantity);
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					break;
				
				} else if (quantity == bestOrder.getQuantity()) {

					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							bestOrder.getOrderID().substring(0,7),
							bestOrder.getOrderID(),
							"FILLED",
							"FILLED"));
					
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
				
					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							orderBookEntry.getOrderID().substring(0,7),
							orderBookEntry.getOrderID(),
							"FILLED",
							"FILLED"));

					orderBookListExecute.updatePairTable(bestOrder.getOrderID() + "-" + orderBookEntry.getOrderID(),
							bestOrder.getPrice(), quantity);

					// Fill
					bestOrder.ammendQuantity(bestOrder.getQuantity() - quantity);
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					break;
					
				} else if ((quantity > bestOrder.getQuantity())) {
					
					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);

					MarketAgent.agent.addBehaviour(new MarketMessageSender(
							bestOrder.getOrderID().substring(0,7),
							bestOrder.getOrderID(),
							"FILLED",
							"FILLED"));
					
					orderBookListExecute.updatePairTable(bestOrder.getOrderID() + "-" + orderBookEntry.getOrderID(),
							bestOrder.getPrice(), bestOrder.getQuantity());
					
					// Partial Fill
					quantity -= bestOrder.getQuantity();
					orderBookListExecute.removeBestOrder();
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					
					if (orderBookListExecute.size() == 0) {

						orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "PART REJECTED (" + quantity + ")",
								MarketLogTable.UpdateType.PART_REJECT);

						MarketAgent.agent.addBehaviour(new MarketMessageSender(
								orderBookEntry.getOrderID().substring(0,7),
								orderBookEntry.getOrderID(),
								"PART_REJECTED",
								"PART REJECTED (" + quantity + ")"));

						break;
					}
				}
			}
			
		} else {
		
			// reject order
			
			orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "REJECTED",
					MarketLogTable.UpdateType.REJECT);
			System.out.println("Rejected");
			
			MarketAgent.agent.addBehaviour(new MarketMessageSender(
					orderBookEntry.getOrderID().substring(0,7),
					orderBookEntry.getOrderID(),
					"REJECTED",
					"REJECTED"));
		}
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
