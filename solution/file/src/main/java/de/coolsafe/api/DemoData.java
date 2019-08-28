package de.coolsafe.api;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import de.coolsafe.api.file.File;
import de.coolsafe.api.file.FileRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "coolsafe", name = "create-demo-data", havingValue = "true")
@Profile("local")
@DependsOn({ "embeddedRedisConfiguration" })
public class DemoData {

	@Autowired
	FileRepository fileRepository;

	public @PostConstruct void init() {

		File file = new File("helloWorld", "text", "helloworld".getBytes());
		File saved = fileRepository.save(file);

		log.info("File created: {}", saved.toString());

	}
}
