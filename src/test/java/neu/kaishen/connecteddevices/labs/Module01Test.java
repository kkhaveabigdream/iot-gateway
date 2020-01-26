/**
 * 
 */
package neu.kaishen.connecteddevices.labs;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import neu.kaishen.connecteddevices.labs.module01.SystemCpuUtilTask;
import neu.kaishen.connecteddevices.labs.module01.SystemMemUtilTask;


/**
 * Test class for all requisite Module01 functionality.
 * 
 * Instructions:
 * 1) Rename 'testSomething()' method such that 'Something' is specific to your needs; add others as needed, beginning each method with 'test...()'.
 * 2) Add the '@Test' annotation to each new 'test...()' method you add.
 * 3) Import the relevant modules and classes to support your tests.
 * 4) Run this class as unit test app.
 * 5) Include a screen shot of the report when you submit your assignment.
 * 
 * Please note: While some example test cases may be provided, you must write your own for the class.
 */
public class Module01Test
{
	// setup methods
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}
	
	// test methods
	
	/**
	 * getDataFromSensor() function returns a float value representing the on-demand CPU utilization
	 * passing values will be anything between 0.0 and 100.0	
	 */
	@Test
	public void testSystemCpuUtil()
	{
		SystemCpuUtilTask c = new SystemCpuUtilTask();
		assertTrue(c.getDataFromSensor()>=0 & c.getDataFromSensor()<100 );
	}
	
	/**
	 * getDataFromSensor() function returns a float value representing the on-demand Memory utilization
	 * passing values will be anything between 0.0 and 100.0	
	 */
	@Test
	public void testSystemMemUtil()
	{
		SystemMemUtilTask m = new SystemMemUtilTask();
		assertTrue(m.getDataFromSensor()>0 & m.getDataFromSensor()<100 );
	}
	
	
}
