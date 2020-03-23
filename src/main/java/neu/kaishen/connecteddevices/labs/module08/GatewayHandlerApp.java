package neu.kaishen.connecteddevices.labs.module08;

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
		coapServer.start();
		client.isSecure = true;
        client.connect();  

        while (true) {       
        client.publish();
        client.subscribe();
       
        uClient.start();
        
        Thread.sleep(10000);
		}              
    }  
}
