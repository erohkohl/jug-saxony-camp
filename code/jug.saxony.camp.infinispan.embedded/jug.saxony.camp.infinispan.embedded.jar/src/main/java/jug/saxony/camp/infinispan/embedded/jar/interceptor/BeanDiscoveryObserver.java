package jug.saxony.camp.infinispan.embedded.jar.interceptor;

import javax.cache.annotation.CacheResult;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

import org.kohsuke.MetaInfServices;

@MetaInfServices
public class BeanDiscoveryObserver implements Extension{

	public void registerCacheResultAnnotation(@Observes BeforeBeanDiscovery event) {
	      event.addInterceptorBinding(CacheResult.class);
	      System.out.println("CacheResult Annotation registered.");
	}
}
