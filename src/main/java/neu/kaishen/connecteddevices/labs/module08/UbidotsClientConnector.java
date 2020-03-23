package neu.kaishen.connecteddevices.labs.module08;

import java.net.URISyntaxException;
import java.util.logging.Logger;

import com.ubidots.*;
public class UbidotsClientConnector {
	ApiClient api = new ApiClient("BBFF-e051d5d55e46d64eebf3c2a351d8b4aff09");
	GatewayDataManager dataManager = null;
	Logger log = Logger.getLogger("main");
	
	public UbidotsClientConnector() {
		// TODO Auto-generated constructor stub
	}

public void start() throws URISyntaxException {
	//DataSource dataSource = api.getDataSource("5e75268c1d847233ad0e3aae");
	Variable variable = api.getVariable("5e781b641d84726c4890647c");
	//Variable variable = api.getVariable("tempsensor");
	dataManager = new GatewayDataManager();
	float data = dataManager.getSensorData();
	variable.saveValue(data);
	log.info("Successfully Published a Temperature data to Ubidots via API...");
	}
}

