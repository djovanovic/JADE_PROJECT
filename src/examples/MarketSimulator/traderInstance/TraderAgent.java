package examples.MarketSimulator.traderInstance;

import examples.MarketSimulator.AgentRegistration;
import jade.core.Agent;
//import examples.MarketSimulator.AgentStarter;

public class TraderAgent extends Agent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TraderOrderList traderOrderList;
	public TraderGui traderGui;
	public int orderCount = 0;
	private AgentRegistration traderRegistration;
	
	protected void setup() {

		traderRegistration = new AgentRegistration(this);
		traderRegistration.register(getAID());
		traderGui = new TraderGui("Market", this);
		traderGui.setup();
		traderOrderList = new TraderOrderList(this);
		
		addBehaviour(new TraderMessageReceiver(this));
		
		System.out.println("Thanks for starting me up! I'm: " + getAID().getLocalName());

	}
	
	protected void takeDown() {
		
		// Printout a dismissal message
		System.out.println("Buyer-agent "+getAID().getName()+" terminating.");
	}
}