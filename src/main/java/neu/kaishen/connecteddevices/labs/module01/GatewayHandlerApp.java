package neu.kaishen.connecteddevices.labs.module01;

public class GatewayHandlerApp extends Thread{

	public GatewayHandlerApp() {
		
	}

//start the thread class
	public void run() {
		while (true) {
			SystemPerformanceAdaptor a = new SystemPerformanceAdaptor();
			a.start();
			a.info();
			
		}
	}
	
	public static void main(String[] args) {
		GatewayHandlerApp g = new GatewayHandlerApp();
		g.run();
	}

}
