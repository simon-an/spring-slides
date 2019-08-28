package de.coolsafe.api.file;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

@TestConfiguration
//@Profile("local")
@Slf4j
public class TestRedisConfiguration {

	private RedisServer redisServer;

	public TestRedisConfiguration(RedisProperties redisProperties) {
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