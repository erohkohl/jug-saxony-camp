JUG Saxony Camp 2017 [![Build Status](https://travis-ci.org/erohkohl/jug-saxony-camp.svg?branch=master)](https://travis-ci.org/erohkohl/jug-saxony-camp)
======================================
The JUG Saxony Camp 2017 was organized by the [JUG Saxony e.V.](https://jugsaxony.org/) and took place at the HTWK Leipzig University of Applied Sciences.

**Prerequisites:**
- [WildFly AS](http://wildfly.org/downloads/)
- Maven


Caching for Java EE applications with JCache API
----------------------------------------------------------------------
```java
public class CacheDemo {

  @CacheResult
  public int someExpensiveCalculation(int argOne, String argTwo) {
    return new Random().nextInt();
  }
}
```

Our aim is to temporary store the return value of our business logic. Therefore
we annotate the method with *@CacheResult*, which is part of the JCache API ([JSR-107](https://www.jcp.org/en/jsr/detail?id=107)).
The *WildFly* AS provides an out of the box JCache implementation called [*Infinispan*](http://infinispan.org/).

#### Caching with JCache API and Infinispan

In the directory [*code/jug.saxony.camp.jcache*](https://github.com/erohkohl/jug-saxony-camp/tree/master/code/jug.saxony.camp.jcache) you can find a prototype, which uses
Infinispan's *CacheResultInterceptor*. The JAR contains the business logic of our
application, the WAR a servlet, and the EAR unites both for deployment.


```bash
$ cd code/jug.saxony.camp.jcache
$ mvn clean install
```

Now you are able to deploy the EAR on WildFly AS.

This servlet demonstrates, that our cache works as expected.
- http://localhost:8080/jug/saxony/camp/jcache/one/Servlet


But this solution brings some **problems**:

1. This cache isn't resistent against redeployment of your EAR, in case you might
edit your view component.

2. The storage of this cache is not under control of WildFly, so two applications aren't able to scale their performance by using a common cache.

The following caching technique will show you, how to solve this problems.

#### Caching against JCache API, embedded Infinispan and custom CacheResultInterceptor

Under [*code/jug.saxony.camp.infinispan.embedded*](https://github.com/erohkohl/jug-saxony-camp/tree/master/code/jug.saxony.camp.infinispan.embedded) you find a prototype, that has a custom implementation of JCache's CacheResultInterceptor. This [interceptor](https://github.com/erohkohl/jug-saxony-camp/blob/master/code/jug.saxony.camp.infinispan.embedded/jug.saxony.camp.infinispan.embedded.jar/src/main/java/jug/saxony/camp/infinispan/embedded/jar/interceptor/CacheResultInterceptorCustom.java) uses Infinispan's embedded caching framework, which is independent from JCache. Thus this technique is able to reference WildFly's preconfigured caches.

```bash
$ cd code/jug.saxony.camp.infinispan.embedded
$ mvn clean install
```

Deploy the EAR to your WildFly instance and again access it's servlet. Redeploy this application and you will see, that the returned values remain the same. When you
deploy a second instance of the application with a customized servlet, you will observe, that both share the common cache instance.

- http://localhost:8080/jug/saxony/camp/infinispan/embedded/one/Servlet
