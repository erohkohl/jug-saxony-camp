JUG SAXONY CAMP 2017
======================================

Caching-Verfahren für Java-Enterprise Anwendungen gegen das JCache API
----------------------------------------------------------------------
```java
public class CacheDemo {
    @CacheResult
	public int someExpensiveCalculation(int argOne, String argTwo) {
	   return new Random().nextInt();
  }
}
```

**Starte WildFly:**
```bash
./JUG_SAXONY_CAMP_2017/wildfly/wildfly-10.1.0.Final/bin/standalone.sh
```

**Caching gegen JCache API und Infinispan-Interceptor**
- code/jug.saxony.camp.jcache
- http://localhost:8080//jug/saxony/camp/jcache/one/Servlet

**Caching gegen JCache API, embedded Infinispan und Custom-Interceptor:**
- code/jug.saxony.camp.infinispan.embedded
- http://localhost:8080//jug/saxony/camp/infinispan/embedded/one/Servlet