package neu.kaishen.connecteddevices.project;


import java.net.URISyntaxException;

//import org.apache.log4j.Logger;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import neu.kaishen.connecteddevices.common.ActuatorData;
import neu.kaishen.connecteddevices.common.DataUtil;
import neu.kaishen.connecteddevices.project.GatewayDataManager;


public class PushCallback implements MqttCallback{

	DataUtil dataUtil = null;
	ActuatorData actuatorData = null;
	String test = "";
	GatewayDataManager dataManager = null;
	
	
	public PushCallback() throws URISyntaxException {
		// TODO Auto-generated constructor stub
		
		dataUtil = new DataUtil();
		dataManager = new GatewayDataManager();
	}

	public void messageArrived(String topic, MqttMessage message) {
		Logger log = Logger.getLogger("main");
		String msg = new String(message.getPayload());
		actuatorData = dataUtil.jsonToActuatorData(msg);
//		System.out.println("-----------------");
		
		if(actuatorData!=null) {
			//test = msg;
			//System.out.println(msg);
			log.info("actuatorData");
			dataManager.publishActuatorData(String.valueOf(actuatorData.value));
		}

//		System.out.println(actuatorData.value);
		log.info("Subscribe to topic " + topic + " succeffully");

		log.info("ActuatorData Updated...\n"+"Subscribed ActuatorData: " + msg);
	}

	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost, please reconnect");
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete--- " + token.isComplete());
		
	}
}
