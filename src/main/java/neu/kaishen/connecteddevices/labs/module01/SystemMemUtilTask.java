package neu.kaishen.connecteddevices.labs.module01;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;



public class SystemMemUtilTask {

	public SystemMemUtilTask() {
		// TODO Auto-generated constructor stub
	}

public float getDataFromSensor() {
		
		MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap = memBean.getHeapMemoryUsage();
		
		
		double heapUtil = ((double) heap.getUsed()/heap.getMax())*100;
		
		return(float)heapUtil;
	}
}
