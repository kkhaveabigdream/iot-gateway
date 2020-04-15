package neu.kaishen.connecteddevices.project;

import java.net.URISyntaxException;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPubSub {

    public static final String TOPIC1 = "/v1.6/devices/raspberrypi/soil_Moisture";  
    public static final String TOPIC2 = "/v1.6/devices/raspberrypi/actuator";
    public static final String TOPIC3 = "/v1.6/devices/raspberrypi/temperature";

    GatewayDataManager dataManager = null;
    
	public MqttPubSub() throws Exception {

	}
	
	public void pubsub(MqttClientConnector client) throws Exception {
		
		dataManager = new GatewayDataManager();
		float tempData = dataManager.getSensorData();	
		float soilMoisture = dataManager.getSoilMoistureData();
		
    
        client.publish(soilMoisture,TOPIC1);
        client.subscribe(TOPIC2);
        
        client.publish(tempData, TOPIC3);

		
	}

}
