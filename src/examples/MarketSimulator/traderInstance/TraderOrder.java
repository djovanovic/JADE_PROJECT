package examples.MarketSimulator.traderInstance;

import examples.MarketSimulator.AgentOrder;

public class TraderOrder extends AgentOrder {

	private String orderID;
	private String tradeTokens;

	public TraderOrder(int type, String quantity) {
		
		super(Integer.toString(type), quantity);
		orderID =  TraderAgent.agent.getAID().getLocalName()+"-"+TraderAgent.agent.orderCount++;
		this.tradeTokens = String.format("%s,%s,%s", orderID, type, quantity);
	}
	
	public TraderOrder(int type, String quantity, String price) {

		super(Integer.toString(type), quantity, price);
		orderID =  TraderAgent.agent.getAID().getLocalName()+"-"+TraderAgent.agent.orderCount++;
		this.tradeTokens = String.format("%s,%s,%s,%s", orderID, type, quantity, price);
	}
	
	public String getOrderID() {
		
		return orderID;
	}
	
	public String tokenize() {
		
		return tradeTokens;
	}
}
