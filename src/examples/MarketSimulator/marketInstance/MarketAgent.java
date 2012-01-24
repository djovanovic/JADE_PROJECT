package examples.MarketSimulator.marketInstance;

import examples.MarketSimulator.AgentStarter;
import examples.MarketSimulator.orderBookDataStructure.OrderBook;

public class MarketAgent extends AgentStarter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static MarketAgent agent;
	public MarketOrderList marketOrderList;
	public OrderBook orderBook;
	public MarketMockSetupOffers marketMockSetupOffers = new MarketMockSetupOffers();
	public MarketMockSetupBids marketMockSetupBids = new MarketMockSetupBids();
	
	
	public MarketAgent() {
		
		super(new MarketGui());
		MarketAgent.agent = this;
		marketOrderList = new MarketOrderList(this);
		
		orderBook = new OrderBook();
		
		addBehaviour(new MarketMessageClient());

		//marketMockSetupOffers.start();
		//marketMockSetupBids.start();
	}
}
