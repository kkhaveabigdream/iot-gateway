package neu.kaishen.connecteddevices.labs.module05;

import neu.kaishen.connecteddevices.common.SensorDataListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class GatewayDataManager {

	public GatewayDataManager() {
		// TODO Auto-generated constructor stub
	}
	
//	SensorDataListener sensorDataListener;
	
	public void run() {
		
		JedisPool pool = new JedisPool(new JedisPoolConfig(),"localhost",6379);
		final Jedis jedis = pool.getResource();
		
		new Thread(new Runnable() {
			public void run() {
				jedis.subscribe(new SensorDataListener(),"SensorData");
			}
		}).start();
	}

}
