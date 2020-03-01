package neu.kaishen.connecteddevices.labs.module06;


//import org.apache.log4j.Logger;

import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import neu.kaishen.connecteddevices.common.DataUtil;
import neu.kaishen.connecteddevices.common.SensorData;


public class PushCallback implements MqttCallback{

	DataUtil dataUtil = null;
	SensorData sensorData = null;
	
	
	public PushCallback() {
		// TODO Auto-generated constructor stub
		
		dataUtil = new DataUtil();
	}

	public void messageArrived(String topic, MqttMessage message) {
		Logger log = Logger.getLogger("main");
		final Logger logger= Logger.getLogger(PushCallback.class.getName());
		//System.out.println("Message topic: " + topic);
		//System.out.println("Receive message: " + message.getQos());
		//System.out.println("Message: " + new String(message.getPayload()));
		String msg = new String(message.getPayload());
		log.info("Subscribe to topic " + topic + " succeffully");

		log.info("Subscribed SensorData: " + msg);
		sensorData = dataUtil.jsonToSensorData(msg);
		String msg2 = "Temperature\n" + "\tTime: " +(String)(sensorData.getTimeStamp()) + "\n\tCurrent: " + String.valueOf(sensorData.curValue) + "\n\tAverage: "  +String.valueOf(sensorData.avgValue) + "\n\tSamples: " + String.valueOf(sensorData.sampleCount) + "\n\tMin: " + String.valueOf(sensorData.minValue) + "\n\tMax: " + String.valueOf(sensorData.maxValue);
		//System.out.println(msg2);
		log.info("SensorData converted from json "+msg2);
		String jsonData = dataUtil.sensorDataToJson(sensorData);
		log.info("Final jsonData "+jsonData);
	}

	public void connectionLost(Throwable cause) {
		System.out.println("Connection lost, please reconnect");
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete--- " + token.isComplete());
		
	}
}
