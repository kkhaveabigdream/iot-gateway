package neu.kaishen.connecteddevices.labs.module06;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.paho.client.mqttv3.MqttException;

public class GatewayHandlerApp {

	public GatewayHandlerApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws MqttException {  
        MqttClientConnector client = new MqttClientConnector();  
        //BasicConfigurator.configure();
        client.start();  
    }  
}
