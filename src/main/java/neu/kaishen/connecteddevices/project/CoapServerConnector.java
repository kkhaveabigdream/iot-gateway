package neu.kaishen.connecteddevices.project;

import java.util.logging.Logger;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;

public class CoapServerConnector {

	private static final Logger logger = Logger.getLogger(CoapServerConnector.class.getName());
	//Logger log = Logger.getLogger("main");
	public CoapServer server;
	public TempResourceHandler tempHandler;
	public SoilMoistureResourceHandler soilMoistureHandler;
	public CoapEndpoint endpoint;
	
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
		soilMoistureHandler = new SoilMoistureResourceHandler();
		server.add(tempHandler);
		server.add(soilMoistureHandler);
		
		logger.info("Starting the Coap server...");

		server.start();
	}
	
}
