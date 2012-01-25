 package examples.MarketSimulator.traderInstance;

import jade.core.Agent;
import examples.MarketSimulator.AgentGui;

public class TraderGui extends TraderGuiFrame implements AgentGui {
	
	private String market;
	private TraderAgent agent;
	
	

	public TraderGui(String market, Agent agent) {
		
		super();
		this.agent = (TraderAgent) agent;
		this.market = market;
	}

	public void setup() {
		
		show();

		// Initialize Button Actions
		TraderAgentGuiActionListener marketOrderBuy = 
				new TraderAgentGuiActionListener(market, 0, agent);
		marketOrderBuy.addTraderActionBuy(this, marketBuy, marketQuantity);
		
		TraderAgentGuiActionListener marketOrderSell = 
				new TraderAgentGuiActionListener(market, 1, agent);
		marketOrderSell.addTraderActionSell(marketSell, marketQuantity);

		TraderAgentGuiActionListener limitOrderBid = 
				new TraderAgentGuiActionListener(market, 2, agent);
		limitOrderBid.addTraderActionBid(limitBid, limitQuantity, limitPrice);

		TraderAgentGuiActionListener limitOrderOffer = 
				new TraderAgentGuiActionListener(market, 3, agent);
		limitOrderOffer.addTraderActionOffer(limitOffer, limitQuantity, limitPrice);
	}
}
