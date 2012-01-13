package examples.MarketSimulator.orderBookDataStructure.orderBookEntry;

import examples.MarketSimulator.marketInstance.MarketLogTable;
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
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					bestOrder.ammendQuantity(
							bestOrder.getQuantity() - quantity);
					
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					break;
					
				//	If order is at better Price and Bigger Quantity than the NEXT BEST ORDER
				} else if (quantity > bestOrder.getQuantity() && price <= bestOrder.getPrice()*-1) {
					
					// Partial Fill
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "Partial Fill (" + (bestOrder.getQuantity()-quantity) + ")",
							MarketLogTable.UpdateType.PARTIAL_FILL);
					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					quantity -= bestOrder.getQuantity();
					orderBookEntry.setQuantity(quantity);
					orderBookListExecute.removeBestOrder();
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					
				//	If order is at better Price and Equal Quantity than the NEXT BEST ORDER
				} else if (quantity == bestOrder.getQuantity() && price <= bestOrder.getPrice()*-1) {
					
					// Fill
					orderBookListExecute.updateTable(bestOrder.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "FILLED",
							MarketLogTable.UpdateType.FILL);
					
					quantity -= bestOrder.getQuantity();
					orderBookEntry.setQuantity(quantity);
					orderBookListExecute.removeBestOrder();
					orderBookListExecute.renderOrder(bestOrder.getPrice());
					break;
					
				//	If order is at a worse Price and Smaller Quantity than the NEXT BEST ORDER
				} else {

					bookOrder(orderBookEntry);
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "Booked at " + Math.abs(orderBookEntry.getPrice()),
							MarketLogTable.UpdateType.NONE);
					break;
				}
				
				if (orderBookListExecute.size() == 0) {
					
					bookOrder(orderBookEntry);
					orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "Booked at " + Math.abs(orderBookEntry.getPrice()),
							MarketLogTable.UpdateType.NONE);
					break;
				}
			}
			
		} else {

			bookOrder(orderBookEntry);
			orderBookListExecute.updateTable(orderBookEntry.getOrderID(), "Booked at " + Math.abs(orderBookEntry.getPrice()),
					MarketLogTable.UpdateType.NONE);
		}
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
