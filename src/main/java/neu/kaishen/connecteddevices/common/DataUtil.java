package neu.kaishen.connecteddevices.common;

import com.google.gson.Gson;

public class DataUtil {

	public DataUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public String sensorDataToJson(SensorData sensorData) {
		String jsonData = null;
		
		if (sensorData !=null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(sensorData);
		}
		return jsonData;
	}
	
	public SensorData jsonToSensorData(String jsonData) {
		SensorData sensorData = null;
		
		if (jsonData !=null && jsonData.trim().length() >0) {
			Gson gson = new Gson();
			sensorData = gson.fromJson(jsonData, SensorData.class);
			
		}
		return sensorData;
	}
	
	public String actuatorDataToJson(ActuatorData actuatorData) {
		String jsonData = null;
		
		if(actuatorData !=null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(actuatorData);
			
		}
		return jsonData;
	}
	
	public ActuatorData jsonToActuatorData(String jsonData) {
		ActuatorData actuatorData = null;
		
		if (jsonData != null && jsonData.trim().length()>0) {
			Gson gson = new Gson();
			actuatorData = gson.fromJson(jsonData, ActuatorData.class);
		}
		return actuatorData;
	}
	 

}
