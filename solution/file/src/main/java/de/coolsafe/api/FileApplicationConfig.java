package de.coolsafe.api;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class FileApplicationConfig {

	@Bean
	public LettuceConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
		return new LettuceConnectionFactory(
				new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort()));
	}

	@Bean
	@Resource(name = "redisTemplate")
	public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
		RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		return template;
	}

//	@Bean
//	public RepositoryRestConfigurer repositoryRestConfigurer() {
//
//		return new RepositoryRestConfigurer() {
//
//			@Override
//			public void configureHttpMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
//				messageConverters.add(0, new FormHttpMessageConverter());
//				messageConverters.add(0, new MappingJackson2HttpMessageConverter());
//			}
//
//		};
//	}
//
//	@Bean
//	public WebMvcConfigurer webMvcConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
//				messageConverters.add(0, new FormHttpMessageConverter());
//				messageConverters.add(0, new MappingJackson2HttpMessageConverter());
//			}
//		};
//	}

//	@Bean
//	public CustomConversions redisCustomConversions(){
//	    return new CustomConversions(
//	        Arrays.asList(new UUIDToStringConverter(), new StringToUUIDConverter()))))
//	}

}