package neu.kaishen.connecteddevices.labs.module09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import neu.kaishen.connecteddevices.common.DataUtil;
import neu.kaishen.connecteddevices.common.SensorData;

public class TempResourceHandler extends CoapResource{
	private static final Logger logger = Logger.getLogger(TempResourceHandler.class.getName());
	DataUtil dataUtil = null;
	SensorData sensorData = null;

	public TempResourceHandler() {
		super("temp",false);
	}

	/*
	 * Handle the get request
	 * read the data from the file and return it to the client
	 */
	public void handleGET(CoapExchange ce) {
		File file = new File("D:\\NEU Material\\Connected Devices\\Lab M07\\tempData.txt");
		dataUtil = new DataUtil();
		
		try {
			FileReader reader = new FileReader(file);
		    BufferedReader reader2 = new BufferedReader(reader);
			String data = reader2.readLine();
			
			ce.respond(ResponseCode.VALID, data);
			//System.out.println("------>");
			//System.out.println("Json After Receiving...");
			//logger.info(data);
			sensorData = dataUtil.jsonToSensorData(data);
			//String msg = "Temperature\n" + "\tTime: " +(String)(sensorData.getTimeStamp()) + "\n\tCurrent: " + String.valueOf(sensorData.curValue) + "\n\tAverage: "  +String.valueOf(sensorData.avgValue) + "\n\tSamples: " + String.valueOf(sensorData.sampleCount) + "\n\tMin: " + String.valueOf(sensorData.minValue) + "\n\tMax: " + String.valueOf(sensorData.maxValue);
			//System.out.println("Sensor Data...");
			//logger.info(msg);
			//String jsonData = dataUtil.sensorDataToJson(sensorData);
			//System.out.println("Final Json Data");
			//logger.info(jsonData);
									
			//ce.respond(ResponseCode.VALID, "hello");
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Handle the post request
	 * Write the post data to the file
	 */
	public void handlePOST(CoapExchange ce) {
		ce.accept();
		File file = new File("D:\\NEU Material\\Connected Devices\\Lab M07\\tempData.txt");
		FileOutputStream fos = null;
		
		try {
			
			if(!file.exists())
				file.createNewFile();
			
			fos =new FileOutputStream(file);		
		
			byte[] data = ce.getRequestPayload();
				
			fos.write(data);
			fos.flush();
			fos.close();
						
			ce.respond(ResponseCode.VALID,"Success");
			logger.info("Successfully Received the payload...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void handlePUT(CoapExchange ce) {
		ce.accept();
		File file = new File("D:\\NEU Material\\Connected Devices\\Lab M07\\tempData.txt");
		FileOutputStream fos = null;
		
		try {
			fos =new FileOutputStream(file);		
		
			byte[] data = ce.getRequestPayload();
				
			fos.write(data);
			fos.flush();
			fos.close();
						
			ce.respond(ResponseCode.VALID,"Success");
			logger.info("Update Success...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void handleDELETE(CoapExchange ce) {
		File file = new File("D:\\NEU Material\\Connected Devices\\Lab M07\\tempData.txt");
		
		file.delete();
		ce.respond(ResponseCode.VALID,"Success");
		logger.info("Delete Success...");
	}
}
