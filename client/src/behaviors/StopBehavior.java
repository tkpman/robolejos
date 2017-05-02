package behaviors;

import client.RobotControlCenter;
import lejos.robotics.subsumption.Behavior;
import messageservice.MessageService;
import messageservice.MessageService.IMessageListener;

public class StopBehavior implements Behavior {

	private final RobotControlCenter rcc;
	private final MessageService messageService;
	private String message;
	
	public StopBehavior(RobotControlCenter rcc, MessageService messageService) {
		this.rcc = rcc;
		this.messageService = messageService;
		this.subscribe();
	}
	
	public void subscribe() {
		messageService.addMessageListener(new IMessageListener() {
			@Override
			public void onMessageReceived(String msg) {
				if (msg.equals("stop"))
					message = msg;
			}
		});
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return message != null;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		this.rcc.stop();
		message = null;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
	}
	
}