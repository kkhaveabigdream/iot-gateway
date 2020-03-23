package neu.kaishen.connecteddevices.labs.module07;

import java.net.URISyntaxException;

public class CoapServerApp {

	public CoapServerApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		CoapServerConnector coapServer = new CoapServerConnector();
		coapServer.start();
		CoapClientConnector coapClient = new CoapClientConnector();
		coapClient.start();;
	}

}
