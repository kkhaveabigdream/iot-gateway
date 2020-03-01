package neu.kaishen.connecteddevices.common;

import java.io.Serializable;

public class SensorData implements Serializable
{
	private String 	timeStamp	= null;
	private String 	name		="Not Set";
	public  float  	curValue	=0.0f;
	public  float	avgValue	=0.0f;
	public  float	minValue	=0.0f;
	public  float	maxValue	=0.0f;
	public  float	totValue	=0.0f;
	public  int		sampleCount	=0;
	
	

	public SensorData() {
		this.timeStamp = getTimeStamp();
				
	}
	
	public void addValue(float val) {
		++this.sampleCount;
		
		this.timeStamp  = getTimeStamp();
		this.curValue	= val;
		this.totValue  += val;
		
		if (this.curValue < this.minValue) {
			this.minValue = this.curValue;
		}
		
		if (this.curValue > this.maxValue) {
			this.maxValue = this.curValue;
		}
		
		if (this.totValue !=0 && this.sampleCount >0) {
			this.avgValue = this.totValue / this.sampleCount;
		}
	}
	
	public float getCurrentValue() {
		return this.curValue;
	}
	

	public String getTimeStamp() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
		return sqlTimestamp.toString();
	}
}
