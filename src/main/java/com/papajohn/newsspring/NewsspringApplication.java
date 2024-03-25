package com.papajohn.NewsSpring;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import com.github.benmanes.caffeine.cache.Caffeine;

@SpringBootApplication
@EnableCaching
public class NewsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsSpringApplication.class, args);
	}

	// Setting up an in-memory cache via caffine to cache calls to the news api service.
	// This would not work for a multi-node service deployment (in the sense that it is only a local cache),
	// you would need something more complex if you want to share cahce data across nodes.
	// Session affinity of some kind would reduce the need for cross-node cache sharing.
	// Adjust TTL to taste.
	@Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("newsCache"); // Define your cache names here
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(10));
    }
}
