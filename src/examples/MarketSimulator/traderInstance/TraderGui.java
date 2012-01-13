 package examples.MarketSimulator.traderInstance;

import examples.MarketSimulator.AgentGui;

public class TraderGui extends TraderGuiFrame implements AgentGui {
	
	private String market;

	public TraderGui(String market) {
		
		super();
		this.market = market;
	}

	public void setup() {
		
		show();

		// Initialize Button Actions
		TraderAgentGuiActionListener marketOrderBuy = 
				new TraderAgentGuiActionListener(market, 0);
		marketOrderBuy.addTraderActionBuy(this, marketBuy, marketQuantity);
		
		TraderAgentGuiActionListener marketOrderSell = 
				new TraderAgentGuiActionListener(market, 1);
		marketOrderSell.addTraderActionSell(marketSell, marketQuantity);

		TraderAgentGuiActionListener limitOrderBid = 
				new TraderAgentGuiActionListener(market, 2);
		limitOrderBid.addTraderActionBid(limitBid, limitQuantity, limitPrice);

		TraderAgentGuiActionListener limitOrderOffer = 
				new TraderAgentGuiActionListener(market, 3);
		limitOrderOffer.addTraderActionOffer(limitOffer, limitQuantity, limitPrice);
	}
}
