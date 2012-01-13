package examples.MarketSimulator.marketInstance;


public class MarketLogTableHandler {

	private MarketAgent traderAgent;

	public MarketLogTableHandler(MarketAgent traderAgent) {
		
		this.traderAgent = traderAgent;
	}
	
	public void log(MarketOrder marketOrder) {
		
		MarketGui marketGui = (MarketGui) traderAgent.agentGui;
		
		if (marketOrder.getType().equals("BUY")) {
			
			Object[] temp = {marketOrder.getOrderID(), "Buy", marketOrder.getQuantity(), "-", "None"};
			marketGui.marketLogTable.addRow(temp);
		} else if (marketOrder.getType().equals("SELL")) {

			Object[] temp = {marketOrder.getOrderID(), "Sell", marketOrder.getQuantity(), "-", "None"};
			marketGui.marketLogTable.addRow(temp);
		} else if (marketOrder.getType().equals("BID")) {

			Object[] temp = {marketOrder.getOrderID(), "Bid", marketOrder.getQuantity(), Math.abs(Integer.parseInt(marketOrder.getPrice())), "None"};
			marketGui.marketLogTable.addRow(temp);
		} else if (marketOrder.getType().equals("OFFER")) {

			Object[] temp = {marketOrder.getOrderID(), "Offer", marketOrder.getQuantity(), Math.abs(Integer.parseInt(marketOrder.getPrice())), "None"};
			marketGui.marketLogTable.addRow(temp);
		}
		
	}
}
