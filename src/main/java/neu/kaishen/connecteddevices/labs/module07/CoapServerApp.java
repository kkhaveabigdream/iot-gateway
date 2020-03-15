package neu.kaishen.connecteddevices.labs.module07;

public class CoapServerApp {

	public CoapServerApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CoapServerConnector coapServer = new CoapServerConnector();
		coapServer.start();
	}

}
