package com.bookshop.core.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import redis.clients.jedis.Jedis;

import java.net.URI;

@Configuration
@Getter
@RequiredArgsConstructor
public class CacheConfig {

    @Value("${app.cache.redis-url}")
    private String redisUri;

    @Value("${app.cache.available}")
    private boolean available;

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    @SneakyThrows
    public Jedis jedis() {
        return new Jedis(new URI(redisUri));
    }

}
