package neu.kaishen.connecteddevices.common;


import java.util.logging.Logger;

import redis.clients.jedis.JedisPubSub;

public class SensorDataListener extends JedisPubSub{
	ActuatorData actuatorData = null;
	SensorData sensorData = null;
	DataUtil dataUtil = null;
	PersistenceUtil persistenceUtil = null;
	//private Logger log1 = Logger.getLogger("loginfo");
	private Logger log2 = Logger.getLogger("loginfo");
	//private Logger log3 = Logger.getLogger("loginfo");
	//private Logger log4 = Logger.getLogger("loginfo");

	public SensorDataListener() {
		actuatorData = new ActuatorData();
		dataUtil	 = new DataUtil();
		persistenceUtil = new PersistenceUtil();
	}

	@Override
	public void onMessage(String channel, String message) {
		log2.info("Listening on Redis, jsonSensorData retrieved from Redis...");
		System.out.println(channel + "=" + message);
		//log1.info(channel + "=" + message);
		//	log1.info(message);
		sensorData = dataUtil.jsonToSensorData(message);
		if (sensorData.curValue >20) {
			actuatorData.setName("Temperature");
			actuatorData.setCommand("Increasing");
			actuatorData.curValue = sensorData.curValue;
			System.out.println("Current temperature exceeds nonminalTemp by > 20. Triggering alert...");
		}else if(sensorData.curValue <20) {
			actuatorData.setName("Temperature");
			actuatorData.setCommand("Decreasing");
			actuatorData.curValue = sensorData.curValue;
			System.out.println("Current temperature falls below nonminalTemp by < 20. Triggering alert...");
		}
		//System.out.println(actuatorData.curValue + actuatorData.getCommand());
		persistenceUtil.writeActuatorDataToRedis(actuatorData);
		System.out.println("ActuatorData: " + actuatorData.curValue +" " + actuatorData.getCommand());
		System.out.println("------------------------");
		
	}
}
