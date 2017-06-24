package jug.saxony.camp.motivation.one;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CacheDemo {

	private Map<Integer, Integer> cache;

	public CacheDemo() {
		cache = new HashMap<Integer, Integer>();
	}

	public int someExpensiveCalculation(int argOne, String argTwo) {
		int cacheValue;
		int cacheKey = argOne + argTwo.hashCode();

		if (cache.containsKey(cacheKey)) {
			cacheValue = cache.get(cacheKey);
		} else {
			cacheValue = new Random().nextInt();
			cache.put(cacheKey, cacheValue);
		}
		return cacheValue;
	}
}