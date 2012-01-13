package examples.MarketSimulator.traderInstance;


public class TraderLogTableHandler {

	private TraderAgent traderAgent;

	public TraderLogTableHandler(TraderAgent traderAgent) {
		
		this.traderAgent = traderAgent;
	}
	
	public void log(TraderOrder traderOrder) {
		
		TraderGui traderGui = (TraderGui) traderAgent.agentGui;
		
		if (Integer.parseInt(traderOrder.getType()) == 0) {
			
			Object[] temp = {traderOrder.getOrderID(), "Buy", traderOrder.getQuantity(), "-", "None"};
			traderGui.traderLogTable.addRow(temp);
		} else if (Integer.parseInt(traderOrder.getType()) == 1) {

			Object[] temp = {traderOrder.getOrderID(), "Sell", traderOrder.getQuantity(), "-", "None"};
			traderGui.traderLogTable.addRow(temp);
		} else if (Integer.parseInt(traderOrder.getType()) == 2) {

			Object[] temp = {traderOrder.getOrderID(), "Bid", traderOrder.getQuantity(), traderOrder.getPrice(), "None"};
			traderGui.traderLogTable.addRow(temp);
		} else if (Integer.parseInt(traderOrder.getType()) == 3) {

			Object[] temp = {traderOrder.getOrderID(), "Offer", traderOrder.getQuantity(), traderOrder.getPrice(), "None"};
			traderGui.traderLogTable.addRow(temp);
		}
		
	}
}
