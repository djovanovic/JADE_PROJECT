package examples.MarketSimulator.traderInstance;

import jade.core.Agent;
import examples.MarketSimulator.AgentOrder;

public class TraderOrder extends AgentOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderID;
	private String tradeTokens;
	private TraderAgent agent;

	public TraderOrder(int type, String quantity, Agent agent) {
		
		super(Integer.toString(type), quantity);
		this.agent = (TraderAgent) agent;
		orderID =  agent.getAID().getLocalName()+":"+this.agent.orderCount++;
		this.tradeTokens = String.format("%s,%s,%s", orderID, type, quantity);
	}
	
	public TraderOrder(int type, String quantity, String price, Agent agent) {

		super(Integer.toString(type), quantity, price);
		this.agent = (TraderAgent) agent;
		orderID =  agent.getAID().getLocalName()+":"+this.agent.orderCount++;
		this.tradeTokens = String.format("%s,%s,%s,%s", orderID, type, quantity, price);
	}
	
	public String getOrderID() {
		
		return orderID;
	}
	
	public String tokenize() {
		
		return tradeTokens;
	}
}
