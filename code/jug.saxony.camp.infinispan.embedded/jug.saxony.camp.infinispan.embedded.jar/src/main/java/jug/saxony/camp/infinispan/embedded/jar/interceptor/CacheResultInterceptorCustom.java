package jug.saxony.camp.infinispan.embedded.jar.interceptor;

import java.util.Objects;

import javax.cache.annotation.CacheResult;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;

import org.infinispan.manager.EmbeddedCacheManager;

@Interceptor
@Dependent
@CacheResult
public class CacheResultInterceptorCustom {

	private EmbeddedCacheManager cacheContainer;
	private org.infinispan.Cache<Integer, Object> cache;

	@AroundInvoke
	public Object managedTransaction(InvocationContext ctx) throws Exception {

		CacheResult annotation = ctx.getMethod().getAnnotation(CacheResult.class);
		String cacheName = annotation.cacheName();
		cacheContainer = (EmbeddedCacheManager) new InitialContext()
				.lookup("java:jboss/infinispan/container/" + cacheName);

		cache = cacheContainer.getCache();
		System.out.println(cache.getName() + ": " + cache.getCacheConfiguration().eviction());

		int cacheKey = Objects.hash(ctx.getParameters());
		Object result = cache.get(cacheKey);

		if (result == null || annotation.skipGet()) {
			result = ctx.proceed();
			cache.put(cacheKey, result);
		}
		return result;
	}
}