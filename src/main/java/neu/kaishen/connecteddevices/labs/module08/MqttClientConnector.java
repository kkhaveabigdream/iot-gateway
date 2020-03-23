package neu.kaishen.connecteddevices.labs.module08;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientConnector {
    public static final String HOST = "tcp://industrial.api.ubidots.com:1883"; 
    public static final String HOST2 = "ssl://industrial.api.ubidots.com:8883"; 
    public static final String TOPIC1 = "/v1.6/devices/ig/tempsensor";  
    private static final String clientid = "kai"; 
    public boolean isSecure = false;
    public String pemFile = "D:\\NEU Material\\Connected Devices\\Lab M08\\ubidots_cert.pem";
    public String userName = "BBFF-Yki4C1Rnh0JlLaY9ahQxNTkf7xMUlt";
    public String passWord = "";
    private MqttClient client;  
    private MqttConnectOptions options; 
    GatewayDataManager dataManager = null;
    //private String userName = "admin";  
    //private String passWord = "password";  
    //private ScheduledExecutorService scheduler;  
    //private static final Logger logger = Logger.getLogger(MqttClientConnector.class);
    Logger log = Logger.getLogger("main");


	public MqttClientConnector() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Connect to a remote mqtt broker
	 * If isSecure is true, java client will use 'ssl' to communicate with 
	 * Ubidots broker via TLS
	 * If isSecure is false, java client will implement normal connection	
	 */
		
	public void connect() throws Exception{
		if (isSecure) {
			try {
				client = new MqttClient(HOST2,clientid, new MemoryPersistence());
				options = new MqttConnectOptions();
				
				SSLContext sslContext = SSLContext.getInstance("SSL");
				KeyStore keystore = readCertificate();			
				TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				trustManagerFactory.init(keystore);
				sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
				options.setSocketFactory(sslContext.getSocketFactory());
				
				options.setCleanSession(false);
				options.setConnectionTimeout(10);
				options.setKeepAliveInterval(20);
				options.setUserName(userName);
				options.setPassword(passWord.toCharArray());
				client.setCallback(new PushCallback());
				MqttTopic topic = client.getTopic(TOPIC1);
				
				client.connect(options);
				log.info("Secure Client Connection...");
				log.info("Connect to broker: industrial.api.ubidots.com");
				
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Failed to connect broker: industrial.api.ubidots.com");
			}
		}else {		
		try {
			client = new MqttClient(HOST,clientid, new MemoryPersistence());
			options = new MqttConnectOptions();
			options.setCleanSession(false);
			//options.setUserName(userName);
			//options.setPassword(password);
			options.setConnectionTimeout(10);
			options.setKeepAliveInterval(20);
			options.setUserName(userName);
			options.setPassword(passWord.toCharArray());
			client.setCallback(new PushCallback());
			MqttTopic topic = client.getTopic(TOPIC1);
			
			client.connect(options);
			log.info("Connect to broker: industrial.api.ubidots.com");
			
			//int[] Qos	= {2};
			//String[] topic1 = {TOPIC1};
			
			//msg.setPayload("{\"TempSensor\": 27}".getBytes());
			//msg.setPayload("20".getBytes());
			
//			MqttMessage msg = new MqttMessage();
//			msg.setPayload("{\"TempSensor\": {\"value\": 18, \"context\": {\"text\": \"test1\"}}}".getBytes());
//			client.publish(TOPIC1,msg);
			
			//client.subscribe(TOPIC1,1);
			
			//client.unsubscribe(TOPIC1);
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Failed to connect broker: industrial.api.ubidots.com");
		}
		}
	}
		

	private KeyStore readCertificate() throws Exception
	{
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream fis = new FileInputStream(pemFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		ks.load(null);
		
		while(bis.available()>0)
		{
			Certificate cert = cf.generateCertificate(bis);
			ks.setCertificateEntry("asm_store"+bis.available(), cert);
		}
		
		return ks;
	}
	
	/*
	 * Publish a Temperature data received from device to Ubidots
	 */
	
	public void publish() throws MqttException, URISyntaxException {
		MqttMessage msg = new MqttMessage();
		dataManager = new GatewayDataManager();
		float data = dataManager.getSensorData();	
		//msg.setPayload("{\"TempSensor\": {\"value\": data, \"context\": {\"text\": \"test1\"}}}".getBytes());
			
		msg.setPayload(((""+data+"").getBytes()));
		client.publish(TOPIC1,msg);
		log.info("Successfully Published a Temperature Data to Ubidots via mqtt...");
	}
	
	/*
	 * Subscribe to tempactuator variable
	 */
	public void subscribe() throws MqttException {
		client.subscribe("/v1.6/devices/ig/tempactuator");
	}

}

