package neu.kaishen.connecteddevices.common;

import java.io.Serializable;

public class ActuatorData implements Serializable{
	private String timeStamp	= null;
	private String name			= "Not Set";
	private String command		= "Not Set";
	public  float  curValue		= 0.0f;
	public  float  value		= 0.0f;
	

	public ActuatorData() {
		this.timeStamp = getTimeStamp();
	}
	
	public void setCommand(String command) {
		this.command = command;		
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getTimeStamp() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
		return sqlTimestamp.toString();
	}

}
