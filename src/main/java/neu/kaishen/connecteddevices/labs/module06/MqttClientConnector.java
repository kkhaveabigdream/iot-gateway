package neu.kaishen.connecteddevices.labs.module06;

import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientConnector {
    public static final String HOST = "tcp://mqtt.eclipse.org:1883";  
    public static final String TOPIC1 = "kai_test";  
    private static final String clientid = "kai";  
    private MqttClient client;  
    private MqttConnectOptions options;  
    //private String userName = "admin";  
    //private String passWord = "password";  
    private ScheduledExecutorService scheduler;  
    //private static final Logger logger = Logger.getLogger(MqttClientConnector.class);
    Logger log = Logger.getLogger("main");


	public MqttClientConnector() {
		// TODO Auto-generated constructor stub
	}
	
	//Connect to a remote mqtt broker
	//subscribe the message
	
	public void start() {
		try {
			client = new MqttClient(HOST,clientid, new MemoryPersistence());
			options = new MqttConnectOptions();
			options.setCleanSession(false);
			//options.setUserName(userName);
			//options.setPassword(password);
			options.setConnectionTimeout(10);
			options.setKeepAliveInterval(20);
			client.setCallback(new PushCallback());
			MqttTopic topic = client.getTopic(TOPIC1);
			
			client.connect(options);
			log.info("Connect to broker: mqtt.eclipse.org");
			
			//int[] Qos	= {2};
			//String[] topic1 = {TOPIC1};
			client.subscribe(TOPIC1,1);
			
			client.unsubscribe(TOPIC1);
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to connect broker: mqtt.eclipse.org");
		}
	}

}

