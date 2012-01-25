package examples.MarketSimulator.traderInstance;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class TraderMessageSender extends OneShotBehaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AID market = new AID();
	String request;
	
		public TraderMessageSender(String market, String request) {
			
			this.market.setLocalName(market);
			this.request = request;
		}
		
		public void action() {
			
			ACLMessage inf = new ACLMessage(ACLMessage.REQUEST);
			inf.setContent(request);
			inf.addReceiver(market);
			myAgent.send(inf);
		}
}
