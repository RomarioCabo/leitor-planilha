package br.com.planilha.leitorapp.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_FINAL;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@EnableCaching
@Configuration
public class RedisConfig implements CachingConfigurer {
    @Bean
    @ConfigurationProperties(prefix = "spring.data.redis")
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {

        var serializer = new GenericJackson2JsonRedisSerializer(customObjectMapper());

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(serializer);
        template.setDefaultSerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        return template;
    }

    @Bean
    @Primary
    public RedisCacheManager getCitiesCacheManager(JedisConnectionFactory jedisConnectionFactory, @Value("${cache.ttl_seconds.getCities}") int cacheTtl) {

        return buildRedisCacheManager(jedisConnectionFactory, cacheTtl);
    }

    @Bean
    public RedisCacheManager getRegionsCacheManager(JedisConnectionFactory jedisConnectionFactory, @Value("${cache.ttl_seconds.getRegions}") int cacheTtl) {

        return buildRedisCacheManager(jedisConnectionFactory, cacheTtl);
    }

    @Bean
    public RedisCacheManager getStatesCacheManager(JedisConnectionFactory jedisConnectionFactory, @Value("${cache.ttl_seconds.getStates}") int cacheTtl) {

        return buildRedisCacheManager(jedisConnectionFactory, cacheTtl);
    }

    private RedisCacheManager buildRedisCacheManager(JedisConnectionFactory jedisConnectionFactory, int cacheTtl) {
        var serializer = new GenericJackson2JsonRedisSerializer(customObjectMapper());

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(cacheTtl)).serializeValuesWith(fromSerializer(serializer)).serializeKeysWith(fromSerializer(new StringRedisSerializer()));

        redisCacheConfiguration.usePrefix();

        return RedisCacheManager.builder(jedisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
    }

    private ObjectMapper customObjectMapper() {
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build();

        ObjectMapper mapper = new ObjectMapper();

        mapper.activateDefaultTyping(ptv, NON_FINAL, PROPERTY);
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(FAIL_ON_EMPTY_BEANS, false);
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(NON_NULL);
        mapper.disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        return mapper;
    }
}
