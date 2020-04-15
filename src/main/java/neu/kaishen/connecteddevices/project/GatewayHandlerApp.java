package neu.kaishen.connecteddevices.project;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.paho.client.mqttv3.MqttException;

public class GatewayHandlerApp extends Thread{

	public GatewayHandlerApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {  
	    MqttClientConnector client = new MqttClientConnector(); 
        CoapServerConnector coapServer = new CoapServerConnector();
        UbidotsClientConnector uClient = new UbidotsClientConnector();  
        MqttPubSub mqtt = new MqttPubSub();
		coapServer.start();
		client.isSecure = true;
        client.connect();  


        while (true) {  
        	
        mqtt.pubsub(client);
        uClient.start();
        
        Thread.sleep(20000);
		}              
    }  
}
