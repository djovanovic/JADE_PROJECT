package examples.MarketSimulator.orderBookDataStructure.orderBookEntry;


public class OrderBookEntry {
	
	private OrderBookEntryType orderBookEntryType;
	private String orderID;
	private int price, quantity;

	public OrderBookEntry(String orderID, OrderBookEntryType type, int quantity, int price) {
		
		this.orderID = orderID;
		this.orderBookEntryType = type;
		this.quantity = quantity;
		this.price = price;
	}
	
	public OrderBookEntry(String orderID, OrderBookEntryType type, int quantity) {
		
		this.orderID = orderID;
		this.orderBookEntryType = type;
		this.price = type.getPrice();
		this.quantity = quantity;
	}
	
	public String getOrderID() {
		
		return orderID;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public int getQuantity() {
		
		return quantity;
	}
	
	public OrderBookEntryType getType() {
		
		return orderBookEntryType;
	}
	
	public void setPrice(int price) {
		
		this.price = price;
	}
	
	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}
	
	public void ammendQuantity(int quantity) {
		
		this.quantity = quantity;
	}
	
	public void execute() {
		
		orderBookEntryType.execute(this);
	}
}
