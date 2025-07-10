package com.colvir.taskDictionary.config;

//import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@EnableCaching(order = Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class AppConfig {

//    @Bean
//    CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
//        return new HazelcastCacheManager(hazelcastInstance);
//    }

}
