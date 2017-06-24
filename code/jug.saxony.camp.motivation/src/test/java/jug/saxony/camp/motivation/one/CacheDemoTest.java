package jug.saxony.camp.motivation.one;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CacheDemoTest {
	
	private CacheDemo businessLogik = new CacheDemo();
	
	@Test
	public void valueShouldBeCached(){
		int result = businessLogik.someExpensiveCalculation(1234, "Test");
		int cachedResult = businessLogik.someExpensiveCalculation(1234, "Test");
		assertEquals(result, cachedResult);
	}
	
	@Test
	public void valueShouldNotBeCached(){
		int result = businessLogik.someExpensiveCalculation(1234, "Test");
		int cachedResult = businessLogik.someExpensiveCalculation(4321, "Test");
		assert(result != cachedResult);
	}
}
