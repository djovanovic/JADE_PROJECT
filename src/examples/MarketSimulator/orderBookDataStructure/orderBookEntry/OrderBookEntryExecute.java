package examples.MarketSimulator.orderBookDataStructure.orderBookEntry;

import examples.MarketSimulator.marketInstance.MarketLogTable;
import examples.MarketSimulator.orderBookDataStructure.OrderBookList;

public class OrderBookEntryExecute extends OrderBookEntryType {

	private int price = 0;
	private OrderBookList orderBookListExecute, orderBookListBook;

	public OrderBookEntryExecute(OrderBookList orderBookListExecute, OrderBookList orderBookListBook) {

		this.orderBookListExecute = orderBookListExecute;
		this.orderBookListBook = orderBookListBook;	
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
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					// Fill
					bestOrder.ammendQuantity(bestOrder.getQuantity() - quantity);
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					break;
				
				} else if (quantity == bestOrder.getQuantity()) {

					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					// Fill
					bestOrder.ammendQuantity(bestOrder.getQuantity() - quantity);
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					break;
					
				} else if ((quantity > bestOrder.getQuantity())) {
					
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "Partial Fill (" + (bestOrder.getQuantity()) + ")",
							MarketLogTable.UpdateType.PARTIAL_FILL);
					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					// Partial Fill
					quantity -= bestOrder.getQuantity();
					orderBookListExecute.removeBestOrder();
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					
				}
				
				if (orderBookListExecute.size() == 0) {
					
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "PART REJECTED (" + quantity + ")",
							MarketLogTable.UpdateType.PART_REJECT);
					break;
				}
			}
			
		} else {
		
			// reject order
			
			orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "REJECTED",
					MarketLogTable.UpdateType.REJECT);
			System.out.println("Rejected");
		}
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
