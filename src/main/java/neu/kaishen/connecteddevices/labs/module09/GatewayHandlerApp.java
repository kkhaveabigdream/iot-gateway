package neu.kaishen.connecteddevices.labs.module09;


import com.amazonaws.services.iot.client.AWSIotMqttClient;

import java.net.URISyntaxException;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTimeoutException;
import com.amazonaws.services.iot.client.AWSIotTopic;
import neu.kaishen.connecteddevices.labs.module09.CommandArguments;
import neu.kaishen.connecteddevices.labs.module09.SampleUtil;
import neu.kaishen.connecteddevices.labs.module09.SampleUtil.KeyStorePasswordPair;

/**
 * Receiving the TemperatureData from an Iot device by CoAP protocol
 * using {@link AWSIotMqttClient} to subscribe to a topic and
 * publish TemperatureData messages to it. 
 */
public class GatewayHandlerApp {

    private static final String TestTopic = "TemperatureData";
    private static final AWSIotQos TestTopicQos = AWSIotQos.QOS0;

    private static AWSIotMqttClient awsIotClient;

    public static void setClient(AWSIotMqttClient client) {
        awsIotClient = client;
    }

    public static class BlockingPublisher implements Runnable {
        private final AWSIotMqttClient awsIotClient;

        public BlockingPublisher(AWSIotMqttClient awsIotClient) {
            this.awsIotClient = awsIotClient;
        }

        @Override
        public void run() {
            long counter = 1;

            while (true) {

            	GatewayDataManager gdm = new GatewayDataManager();
            	String sensorData="";
            	try {
					sensorData = gdm.getSensorData();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
                String payload = sensorData + (counter++);
                try {
                    awsIotClient.publish(TestTopic, payload);
                } catch (AWSIotException e) {
                    System.out.println(System.currentTimeMillis() + ": publish failed for " + payload);
                }
                //System.out.println(System.currentTimeMillis() + ": >>> " + payload);

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(System.currentTimeMillis() + ": BlockingPublisher was interrupted");
                    return;
                }
            }
        }
    }
    /*
     * Connecting to a Amazon AwsMQTT Broker
     */

    private static void initClient(CommandArguments arguments) {
        String clientEndpoint = "a26ds2kmfvni47-ats.iot.us-east-2.amazonaws.com";
        String clientId = "kai";

        String certificateFile = "D:/NEU Material/Connected Devices/Lab M09/7628d15f43-certificate.pem.crt";
        String privateKeyFile = "D:/NEU Material/Connected Devices/Lab M09/7628d15f43-private.pem.key";
        if (awsIotClient == null && certificateFile != null && privateKeyFile != null) {
            String algorithm = arguments.get("keyAlgorithm", SampleUtil.getConfig("keyAlgorithm"));

            KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile, algorithm);

            awsIotClient = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);
        }

        if (awsIotClient == null) {
            String awsAccessKeyId = arguments.get("awsAccessKeyId", SampleUtil.getConfig("awsAccessKeyId"));
            String awsSecretAccessKey = arguments.get("awsSecretAccessKey", SampleUtil.getConfig("awsSecretAccessKey"));
            String sessionToken = arguments.get("sessionToken", SampleUtil.getConfig("sessionToken"));

            if (awsAccessKeyId != null && awsSecretAccessKey != null) {
                awsIotClient = new AWSIotMqttClient(clientEndpoint, clientId, awsAccessKeyId, awsSecretAccessKey,
                        sessionToken);
            }
        }

        if (awsIotClient == null) {
            throw new IllegalArgumentException("Failed to construct client due to missing certificate or credentials.");
        }
    }

    public static void main(String args[]) throws InterruptedException, AWSIotException, AWSIotTimeoutException {
        CommandArguments arguments = CommandArguments.parse(args);
        initClient(arguments);

        awsIotClient.connect();
        
    	CoapServerConnector coapServer = new CoapServerConnector();
    	coapServer.start();

        AWSIotTopic topic = new TopicListener(TestTopic, TestTopicQos);
        awsIotClient.subscribe(topic, true);

        Thread blockingPublishThread = new Thread(new BlockingPublisher(awsIotClient));
       
        blockingPublishThread.start();
        blockingPublishThread.join();

        
        
    }

}
