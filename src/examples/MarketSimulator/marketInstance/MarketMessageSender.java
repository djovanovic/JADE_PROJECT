package examples.MarketSimulator.marketInstance;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class MarketMessageSender extends OneShotBehaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AID trader = new AID();
	String request;
	
		public MarketMessageSender(String trader, String orderID, String updateType, String comment) {
			
			this.trader.setLocalName(trader);
			this.request = String.format("%s,%s,%s", orderID, updateType, comment); ;
		}
		
		public void action() {
			
			ACLMessage inf = new ACLMessage(ACLMessage.INFORM);
			inf.setContent(request);
			inf.addReceiver(trader);
			myAgent.send(inf);
		}
}
