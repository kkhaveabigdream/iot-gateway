package neu.kaishen.connecteddevices.common;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

public class SensorDataListener extends JedisPubSub{
	ActuatorData actuatorData = null;
	SensorData sensorData = null;
	DataUtil dataUtil = null;
	PersistenceUtil persistenceUtil = null;
	//public Logger log1 = LoggerFactory.getLogger(SensorDataListener.class);
	

	public SensorDataListener() {
		actuatorData = new ActuatorData();
		dataUtil	 = new DataUtil();
		persistenceUtil = new PersistenceUtil();
	}

	@Override
	public void onMessage(String channel, String message) {
		System.out.println("Listening on Redis, jsonSensorData retrieved from Redis...");
		System.out.println(channel + "=" + message);
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
		
	}
}
