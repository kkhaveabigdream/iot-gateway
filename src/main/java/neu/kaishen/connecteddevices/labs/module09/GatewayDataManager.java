package neu.kaishen.connecteddevices.labs.module09;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

import neu.kaishen.connecteddevices.common.DataUtil;
import neu.kaishen.connecteddevices.common.SensorData;

public class GatewayDataManager {
	URI uri = null;
	CoapClient client =null;
	DataUtil dataUtil = null;
	String sensorData = null;
	
	public GatewayDataManager() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Connect to the local Coap Server
	 * get the data transported from the device
	 * return Temperature value
	 */
	public String getSensorData() throws URISyntaxException {
		uri = new URI("localhost:5683/temp");
		client = new CoapClient(uri);
		dataUtil = new DataUtil();
		CoapResponse response = client.get();
		sensorData = response.getResponseText();
		//System.out.println(sensorData.curValue);
		return sensorData;
	}
}
