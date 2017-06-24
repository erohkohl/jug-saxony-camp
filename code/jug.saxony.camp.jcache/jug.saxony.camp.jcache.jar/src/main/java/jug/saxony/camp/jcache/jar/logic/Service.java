package jug.saxony.camp.jcache.jar.logic;

import javax.cache.annotation.CacheResult;

public class Service {
	
	@CacheResult
	public int execute(int a, int b){
		return (int)(Math.random()*1000);
	}
}
