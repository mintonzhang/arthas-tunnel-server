package com.alibaba.arthas.tunnel.server.endpoint;

import com.alibaba.arthas.tunnel.server.app.configuration.ArthasProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(ArthasProperties.class)
@Configuration
public class ArthasEndPointAutoconfiguration {

    @ConditionalOnMissingBean
    @Bean
    @ConditionalOnAvailableEndpoint
    public ArthasEndpoint arthasEndPoint() {
        return new ArthasEndpoint();
    }
}
