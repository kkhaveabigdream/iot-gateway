package neu.kaishen.connecteddevices.labs.module09;

import java.util.logging.Logger;

import org.eclipse.californium.core.CoapServer;

public class CoapServerConnector {

	private static final Logger logger = Logger.getLogger(CoapServerConnector.class.getName());
	//Logger log = Logger.getLogger("main");
	public CoapServer server;
	public TempResourceHandler tempHandler;
	
	public CoapServerConnector() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Create the CoapServer
	 * Using default host(127.0.0.0) & port(5683)
	 * Instance the TempResouceHandler and add it to the server
	 */
	public void start() {
		server = new CoapServer();
		tempHandler = new TempResourceHandler();
		server.add(tempHandler);
		
		logger.info("Starting the Coap server...");

		server.start();
	}
	
}
