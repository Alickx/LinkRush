package com.alickx.linkrush.api.config;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.support.spring6.data.redis.FastJsonRedisSerializer;
import com.alickx.linkrush.api.serializer.CustomFastJsonRedisSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.ReflectionUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CacheConfig implements CachingConfigurer {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        // 使用 fastjson替换默认系列化
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(
                        // 设置使用 fastjson系列化
                        RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
                //设置为2小时redis值过期
                .entryTtl(Duration.ofHours(2));
        return configuration;
    }

    @Override
    public KeyGenerator keyGenerator() {

        return (target, method, params) -> {
            Map<String, Object> container = new HashMap<>(8);
            Class<?> targetClass = target.getClass();
            // 包名称
            container.put("package", targetClass.getPackage());
            // 类地址
            container.put("class", targetClass.toGenericString());
            // 方法名
            container.put("methodName", method.getName());
            // 参数列表
            for (int i = 0; i < params.length; i++) {
                container.put(String.valueOf(i), params[i]);
            }
            // 转为JSON字符串
            String jsonString = JSON.toJSONString(container);
            // 做 SHA256 Hash计算，得到SHA256值作为Key
            return DigestUtil.sha256Hex(jsonString);
        };
    }

    @Override
    public CacheErrorHandler errorHandler() {
        // 异常处理，当redis异常时，只打印日志
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError:key -> [{}]", key, exception);
            }
            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError:key -> [key:{},value:{}]", key, value, exception);
            }
            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError:key ->[{}]", key, exception);

            }
            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.error("Redis occur handleCacheClearError:", exception);

            }
        };
    }
}
