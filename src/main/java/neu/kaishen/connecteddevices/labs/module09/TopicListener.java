package neu.kaishen.connecteddevices.labs.module09;

import java.util.logging.Logger;

import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;
import com.sun.tools.sjavac.Log;

import neu.kaishen.connecteddevices.labs.module07.CoapServerConnector;

/**
 * This class extends {@link AWSIotTopic} to receive messages from a subscribed
 * topic.
 */
public class TopicListener extends AWSIotTopic {

	private static final Logger logger = Logger.getLogger(CoapServerConnector.class.getName());
	
    public TopicListener(String topic, AWSIotQos qos) {
        super(topic, qos);
    }

    @Override
    public void onMessage(AWSIotMessage message) {
    	logger.info("Listening to TemperatureData Topic...");
        System.out.println(System.currentTimeMillis() + ": <<< " + message.getStringPayload());
    }

}
