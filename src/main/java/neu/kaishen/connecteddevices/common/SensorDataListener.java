package neu.kaishen.connecteddevices.common;

import redis.clients.jedis.JedisPubSub;

public class SensorDataListener extends JedisPubSub{
	ActuatorData actuatorData = null;
	SensorData sensorData = null;
	DataUtil dataUtil = null;
	PersistenceUtil persistenceUtil = null;
	

	public SensorDataListener() {
		actuatorData = new ActuatorData();
		dataUtil	 = new DataUtil();
		persistenceUtil = new PersistenceUtil();
	}

	@Override
	public void onMessage(String channel, String message) {
		System.out.println(channel + "=" + message);
		sensorData = dataUtil.jsonToSensorData(message);
		if (sensorData.curValue >20) {
			actuatorData.setName("Temperature");
			actuatorData.setCommand("Increasing");
			actuatorData.curValue = sensorData.curValue;
		}else if(sensorData.curValue <20) {
			actuatorData.setName("Temperature");
			actuatorData.setCommand("Decreasing");
			actuatorData.curValue = sensorData.curValue;
		}
		//System.out.println(actuatorData.curValue + actuatorData.getCommand());
		persistenceUtil.writeActuatorDataToRedis(actuatorData);
		
	}
}
