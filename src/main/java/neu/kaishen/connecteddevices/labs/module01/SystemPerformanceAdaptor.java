package neu.kaishen.connecteddevices.labs.module01;

import java.util.logging.Logger;

public class SystemPerformanceAdaptor extends Thread {

	public SystemPerformanceAdaptor() {
		// TODO Auto-generated constructor stub
	}

	public void info() {
		SystemCpuUtilTask a = new SystemCpuUtilTask();
		SystemMemUtilTask b = new SystemMemUtilTask();
		while (true) {
		Logger log1 = Logger.getLogger("main");
		log1.info("CPU Utilization=" + a.getDataFromSensor());
		Logger log2 = Logger.getLogger("main");		
		log2.info("Memory Utilization=" + b.getDataFromSensor());
		
		
		try {
			Thread.sleep(5000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
}
