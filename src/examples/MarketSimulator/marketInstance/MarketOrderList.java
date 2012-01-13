package examples.MarketSimulator.marketInstance;

import java.util.ArrayList;

public class MarketOrderList {
	
	public ArrayList<MarketOrder> marketOrders;
	private MarketLogTableHandler marketLogTableHandler;
	
	public MarketOrderList(MarketAgent marketAgent){
		
		marketOrders = new ArrayList<MarketOrder>();
		marketLogTableHandler = new MarketLogTableHandler(marketAgent);
	}
	
	public void add(MarketOrder marketOrder) {

		marketOrders.add(marketOrder);
		marketLogTableHandler.log(marketOrder);
	}
}
