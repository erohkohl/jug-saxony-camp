package jug.saxony.camp.infinispan.embedded.jar.logic;

import javax.cache.annotation.CacheResult;

public class Service {
	
	@CacheResult(cacheName="myCache")
	public int execute(int a, int b){
		return (int)(Math.random()*1000);
	}
}
