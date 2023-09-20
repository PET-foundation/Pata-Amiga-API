package com.pet.foundation.pataamiga.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
public class Beans {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        Duration expiration = Duration.ofSeconds(60);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(expiration)
                .disableCachingNullValues();

        return RedisCacheManager.builder(factory).cacheDefaults(config).build();
    }


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Pata Amiga")
                        .description("API for to take care of homeless animals")
                        .version("1.0").contact(new Contact().name("Matheus Victor")
                                .email("matheusvictorhenrique@gmail.com")
                                .url("https://github.com/PET-foundation/Pata-Amiga-API"))
                        .license(new License().name("License of API")
                                .url("https://opensource.org/license/mit/")));
    }
}
