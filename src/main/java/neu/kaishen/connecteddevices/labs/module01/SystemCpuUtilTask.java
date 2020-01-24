package neu.kaishen.connecteddevices.labs.module01;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;


public class SystemCpuUtilTask {

	public SystemCpuUtilTask() {
		// TODO Auto-generated constructor stub
	}

/*
use the java.lang.management package, 
and ManagementFactory, getting the CPU Utilization percentage  
*/
	
	public double getDataFromSensor() {
		
		OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
	            .getOperatingSystemMXBean();
		
		return(osBean.getSystemCpuLoad());
	}
}