package neu.kaishen.connecteddevices.common;

import redis.clients.jedis.Jedis;

public class PersistenceUtil {
	Jedis jedis = null;
	DataUtil dataUtil = null;
	

	public PersistenceUtil() {
		jedis = new Jedis("localhost",6379);
		dataUtil = new DataUtil();
	}
	
	public void writeSensorDataToRedis(SensorData sensorData) {
		String jsonData = dataUtil.sensorDataToJson(sensorData);
		jedis.set("SensorData", jsonData);			
	}
	
	public void writeActuatorDataToRedis(ActuatorData actuatorData) {
		String jsonData = dataUtil.actuatorDataToJson(actuatorData);
		jedis.set("ActuatorData",jsonData);
	}

}
