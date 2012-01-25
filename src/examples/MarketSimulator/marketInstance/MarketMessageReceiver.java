package examples.MarketSimulator.marketInstance;

import java.util.Iterator;
import java.util.StringTokenizer;

import examples.MarketSimulator.orderBookDataStructure.orderBookEntry.OrderBookEntryType;
import examples.MarketSimulator.orderBookGui.OrderBookGui;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MarketMessageReceiver extends CyclicBehaviour{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MarketControler marketControler;
	String tradeID, type;
	int quantity, price;
	OrderBookGui orderBookGui;
	
	public MarketMessageReceiver() {
		
	}

	public OrderBookEntryType orderType(int type) {
		
		switch (type) {

			case(0): return OrderBookEntryType.BUY;
			case(1): return OrderBookEntryType.SELL;
			case(2): return OrderBookEntryType.BID;
			case(3): return OrderBookEntryType.OFFER;
			//case(5): return CANCEL;
		}
		return null;
	}
	
	public void action() {
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage reply = MarketAgent.agent.receive(mt);
		
		if (reply!=null && receiver(reply)) {

			StringTokenizer st1 = new StringTokenizer(reply.getContent(), ",");
			tradeID = st1.nextToken();
			type = st1.nextToken();
			quantity = Integer.parseInt(st1.nextToken());

			if (st1.hasMoreTokens()) {
				price = Integer.parseInt(st1.nextToken());
				new MarketControler(tradeID,orderType(Integer.parseInt(type)),quantity,price).handle();
			} else new MarketControler(tradeID,orderType(Integer.parseInt(type)),quantity).handle();
			
			reply = null;
		}
	}
	
	public boolean receiver(ACLMessage reply) {
		
	
		for (@SuppressWarnings("unchecked")
		Iterator<AID> i =  reply.getAllReceiver(); i.hasNext() ; ) {
			String s = i.next().getLocalName();
			//System.out.println(s+"CHECK" + i.hasNext());
			if (s.equals(MarketAgent.agent.getLocalName())) return true;
		}
		
		return false;
	}
}
