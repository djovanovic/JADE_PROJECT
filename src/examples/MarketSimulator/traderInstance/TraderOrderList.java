package examples.MarketSimulator.traderInstance;

import java.util.ArrayList;

public class TraderOrderList {
	
	public ArrayList<TraderOrder> traderOrders;
	private TraderLogTableHandler traderLogTableHandler;
	
	public TraderOrderList(TraderAgent traderAgent){
		
		traderOrders = new ArrayList<TraderOrder>();
		traderLogTableHandler = new TraderLogTableHandler(traderAgent);
	}
	
	public void add(TraderOrder traderOrder) {
		
		traderOrders.add(traderOrder);
		traderLogTableHandler.log(traderOrder);
	}
}
