package examples.MarketSimulator.traderInstance;

import examples.MarketSimulator.AgentStarter;

public class TraderAgent extends AgentStarter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static TraderAgent agent;
	public TraderOrderList traderOrderList;
	public int orderCount = 0;
	
	public TraderAgent() {

		super(new TraderGui("Market"));
		TraderAgent.agent = this;
		traderOrderList = new TraderOrderList(this);
	}
}