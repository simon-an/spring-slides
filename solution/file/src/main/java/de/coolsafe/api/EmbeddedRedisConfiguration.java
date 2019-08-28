package de.coolsafe.api;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

@Configuration
@Profile("local")
@Slf4j
@AutoConfigureOrder(value = 0)
public class EmbeddedRedisConfiguration {

	private RedisServer redisServer;

	public EmbeddedRedisConfiguration(RedisProperties redisProperties) {
		this.redisServer = new RedisServer(redisProperties.getPort());
	}

	@PostConstruct
	public void postConstruct() {
		this.redisServer.start();
		log.info("Redis Server started: {}", redisServer.ports());
	}

	@PreDestroy
	public void preDestroy() {
		this.redisServer.stop();
	}

}