package examples.MarketSimulator.traderInstance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class TraderAgentGuiActionListener {
	
	private JTextField quantity, price;
	private String market;
	private int orderType;
	private TraderOrder traderOrder;
	
	public TraderAgentGuiActionListener(String market, int orderType) {
		
		this.market = market;
		this.orderType = orderType;
	}
	
	public void addTraderActionSell(JButton jButton, JTextField jTextField) {
		
		quantity = jTextField;
		
		jButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	
            	traderOrder = new TraderOrder(orderType, quantity.getText());
            	TraderAgent.agent.traderOrderList.add(traderOrder);
            	TraderAgent.agent.addBehaviour(new TraderMessageClient(market, traderOrder.tokenize()));
            	
            }
        }); 
	}
	
	public void addTraderActionBuy(TraderGui traderGui, JButton jButton, JTextField jTextField) {
		
		quantity = jTextField;
		
		jButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	
            	traderOrder = new TraderOrder(orderType, quantity.getText());
            	TraderAgent.agent.traderOrderList.add(traderOrder);
            	TraderAgent.agent.addBehaviour(new TraderMessageClient(market, traderOrder.tokenize()));
            	
            }
        }); 
	}
	
	public void addTraderActionOffer(JButton jButton, JTextField jTextField, JTextField jTextField1) {
		
		quantity = jTextField;
		price = jTextField1;

		jButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	traderOrder = new TraderOrder(orderType, quantity.getText(), price.getText());
            	TraderAgent.agent.traderOrderList.add(traderOrder);
            	TraderAgent.agent.addBehaviour(new TraderMessageClient(market, traderOrder.tokenize()));
            }
        }); 
	}
	
	public void addTraderActionBid(JButton jButton, JTextField jTextField, JTextField jTextField1) {
		
		quantity = jTextField;
		price = jTextField1;

		jButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	traderOrder = new TraderOrder(orderType, quantity.getText(), "-" + price.getText());
            	TraderAgent.agent.traderOrderList.add(traderOrder);
            	TraderAgent.agent.addBehaviour(new TraderMessageClient(market, traderOrder.tokenize()));
            }
        }); 
	}
}
