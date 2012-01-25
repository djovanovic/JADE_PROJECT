package examples.MarketSimulator.traderInstance;

import java.util.StringTokenizer;

import examples.MarketSimulator.traderInstance.TraderLogTable.UpdateType;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class TraderMessageReceiver extends CyclicBehaviour{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TraderAgent agent;
	
	public TraderMessageReceiver(Agent agent) {
	
		this.agent = (TraderAgent) agent;
	}
	
	public void action() {
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage reply = agent.receive(mt);
		
		if (reply!=null) {

			System.out.println(reply.getContent());
			StringTokenizer st1 = new StringTokenizer(reply.getContent(), ",");
			
			tokenize(st1.nextToken(), st1.nextToken(), st1.nextToken());
			
		}
	}
	public void tokenize(String orderID, String updateTypeString, String comment) {
		 
		UpdateType updateType;
		
		if (updateTypeString.equals("FILLED"))  updateType = UpdateType.FILL;
		else if (updateTypeString.equals("PARTIAL_FILL"))  updateType = UpdateType.PARTIAL_FILL;
		else if (updateTypeString.equals("REJECTED"))  updateType = UpdateType.REJECT;
		else if (updateTypeString.equals("PART_REJECT"))  updateType = UpdateType.PART_REJECT;
		else updateType = UpdateType.NONE;
		
		agent.traderGui.traderLogTable.updateTable(orderID, comment, updateType);
	}
}
