package neu.kaishen.connecteddevices.project;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.paho.client.mqttv3.MqttException;

import neu.kaishen.connecteddevices.labs.module01.SystemCpuUtilTask;
import neu.kaishen.connecteddevices.labs.module01.SystemMemUtilTask;

public class GatewayHandlerApp extends Thread{
	
	
	public GatewayHandlerApp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {  
	    MqttClientConnector client = new MqttClientConnector(); 
        CoapServerConnector coapServer = new CoapServerConnector();
        //UbidotsClientConnector uClient = new UbidotsClientConnector();  
        MqttPubSub mqtt = new MqttPubSub();
		SystemCpuUtilTask a = new SystemCpuUtilTask();
		SystemMemUtilTask b = new SystemMemUtilTask();
		FileHandler fh = new FileHandler("project-java.log");
		
		coapServer.start();
		client.isSecure = true;
        client.connect(); 
        


        while (true) {  
        
        mqtt.pubsub(client);
        //uClient.start();
        
		Logger log = Logger.getLogger("main");
		log.info("CPU Utilization=" + a.getDataFromSensor());
		log.addHandler(fh);
	
		log.info("Memory Utilization=" + b.getDataFromSensor());

        
        Thread.sleep(40000);
		}              
    }  
}
