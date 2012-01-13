package examples.MarketSimulator.orderBookDataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import examples.MarketSimulator.marketInstance.MarketAgent;
import examples.MarketSimulator.marketInstance.MarketGui;
import examples.MarketSimulator.marketInstance.MarketLogTable;
import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntry;
import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntryType;
import examples.MarketSimulator.orderBookGui.OrderBookGuiAddOrder;


public abstract class OrderBookList extends OrderBookingEngine {
	
	public OrderBookList() {
		
		super(new LinkedList<ArrayList<OrderBookEntry>>());
	}

	public void updateTable(String orderID, String comment, MarketLogTable.UpdateType updateType) {
		
		MarketGui marketGui = (MarketGui) MarketAgent.agent.agentGui;
		marketGui.marketLogTable.updateTable(orderID, comment, updateType);
	}
	
	public void renderOrder(int price) {
		
		MarketGui marketGui = (MarketGui) MarketAgent.agent.agentGui;
		new OrderBookGuiAddOrder(marketGui.orderBookGui.getGraphics()).clearPrice(price);
		
		master:
		for (ArrayList<OrderBookEntry> a : this.orderBookList) {
			
			for (OrderBookEntry b : a) {
				
				if (price == b.getPrice()) {
					
					new OrderBookGuiAddOrder(marketGui.orderBookGui.getGraphics()).renderOrderStack(a);
					break master;
				}
			}
		}
	}

	public boolean bookOrder(String orderID, OrderBookEntryType type, int price, int quantity) {
		
		if (book(orderID, type, price, quantity)) {
			
			renderOrder(price);
			return true;
		} else return false;
	}
	
	public boolean bookOrder(OrderBookEntry orderBookEntry) {
		
		if (book(orderBookEntry.getOrderID(), orderBookEntry.getType(), orderBookEntry.getQuantity(), orderBookEntry.getPrice())) {
			
			renderOrder(orderBookEntry.getPrice());
			return true;
		} else return false;
	}
	
	public void remove(int i) {
		
		orderBookList.remove(i);
	}
	
	public ArrayList<OrderBookEntry> getBestPrice() {
		
		return orderBookList.get(0);
	}
	
	public void removeBestOrder() {
		
		if (getBestPrice().size() > 1) getBestPrice().remove(0);
		else orderBookList.remove(0);
	}
	
	public ArrayList<OrderBookEntry> get(int i) {
		
		return orderBookList.get(i);
	}
	
	public int size() {
		
		return orderBookList.size();
	}
	
	public void printTree() {
		
		ListIterator<ArrayList<OrderBookEntry>> iterator = orderBookList.listIterator();

		//iterator.next();
		while (iterator.hasNext()) {
			
			for (OrderBookEntry i : iterator.next()) {
				System.out.print(String.format("%s, %s, %s; ", i.getOrderID(), i.getPrice(), i.getQuantity()));
			}
		System.out.println();
		}
	}
}
