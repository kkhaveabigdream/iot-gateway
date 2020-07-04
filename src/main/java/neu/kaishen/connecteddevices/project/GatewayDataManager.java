package neu.kaishen.connecteddevices.project;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

import neu.kaishen.connecteddevices.common.DataUtil;
import neu.kaishen.connecteddevices.common.SensorData;

public class GatewayDataManager {
	URI uri1 = null;
	CoapClient client1 =null;
	URI uri2 = null;
	CoapClient client2 =null;
	DataUtil dataUtil = null;
	SensorData sensorData = null;
	
	public GatewayDataManager() throws URISyntaxException {
		uri1 = new URI("localhost:5683/temp");
		client1 = new CoapClient("coap://"+uri1);
		
		uri2 = new URI("localhost:5683/soilMoisture");
		client2 = new CoapClient("coap://"+uri2);
	}

	/*
	 * Connect to the local Coap Server
	 * get the data transported from the device
	 * return Temperature value and SoilMoistureData
	 */
	public float getSensorData()  {

		dataUtil = new DataUtil();
		CoapResponse response = client1.get();
		sensorData = dataUtil.jsonToSensorData(response.getResponseText());
		//System.out.println(sensorData.curValue);
		return sensorData.curValue;
	}
	
	public float getSoilMoistureData() {
		dataUtil = new DataUtil();
		CoapResponse response = client2.get();
		sensorData = dataUtil.jsonToSensorData(response.getResponseText());
		System.out.println(sensorData.curValue);
		return sensorData.curValue;
	}
	
	public void publishActuatorData(String data) {
		System.out.println(111111);
		CoapResponse response = client2.post(data, 0);
	}
}
