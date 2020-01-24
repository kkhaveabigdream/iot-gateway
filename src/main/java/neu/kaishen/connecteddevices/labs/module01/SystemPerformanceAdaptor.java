package neu.kaishen.connecteddevices.labs.module01;

import java.util.logging.Logger;

public class SystemPerformanceAdaptor extends Thread {

	public SystemPerformanceAdaptor() {
	
	}

/*Create SystemCpuUtilTask and SystemMemUtilTask instance
 * call getDataFromSensor() to get the CPU Utilization and Memory Utilization data
 * display the output
*/
	public void info() {
		SystemCpuUtilTask a = new SystemCpuUtilTask();
		SystemMemUtilTask b = new SystemMemUtilTask();
		while (true) {
		Logger log1 = Logger.getLogger("main");
		log1.info("CPU Utilization=" + a.getDataFromSensor());
		Logger log2 = Logger.getLogger("main");		
		log2.info("Memory Utilization=" + b.getDataFromSensor());
		
//initiate the thread to retrieve the function every 5 second	
		try {
			Thread.sleep(5000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
}
