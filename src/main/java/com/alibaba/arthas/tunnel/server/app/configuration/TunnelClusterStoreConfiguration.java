package com.alibaba.arthas.tunnel.server.app.configuration;

import com.alibaba.arthas.tunnel.server.app.configuration.TunnelClusterStoreConfiguration.RedisTunnelClusterStoreConfiguration;
import com.alibaba.arthas.tunnel.server.cluster.InMemoryClusterStore;
import com.alibaba.arthas.tunnel.server.cluster.RedisTunnelClusterStore;
import com.alibaba.arthas.tunnel.server.cluster.TunnelClusterStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author hengyunabc 2020-10-29
 */
@Configuration
@AutoConfigureAfter(value = {RedisAutoConfiguration.class, CacheAutoConfiguration.class})
@Import(RedisTunnelClusterStoreConfiguration.class)
public class TunnelClusterStoreConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "spring.cache.type", havingValue = "caffeine")
    public TunnelClusterStore tunnelClusterStore() {
        return new InMemoryClusterStore();
    }

    static class RedisTunnelClusterStoreConfiguration {
        @Bean
        // @ConditionalOnBean(StringRedisTemplate.class)
        @ConditionalOnClass(StringRedisTemplate.class)
        @ConditionalOnProperty("spring.redis.host")
        @ConditionalOnMissingBean
        public TunnelClusterStore tunnelClusterStore(@Autowired StringRedisTemplate redisTemplate) {
            RedisTunnelClusterStore redisTunnelClusterStore = new RedisTunnelClusterStore();
            redisTunnelClusterStore.setRedisTemplate(redisTemplate);
            return redisTunnelClusterStore;
        }
    }

}
