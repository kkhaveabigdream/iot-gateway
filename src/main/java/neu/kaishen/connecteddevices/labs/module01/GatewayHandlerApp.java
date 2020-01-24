package neu.kaishen.connecteddevices.labs.module01;

public class GatewayHandlerApp extends Thread{

	public GatewayHandlerApp() {
		// TODO Auto-generated constructor stub
	}

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
