package com.github.imloama.stellar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.imloama.stellar.config.StellarConfiguration;
import com.github.imloama.stellar.service.StellarService;

@Configuration
@ConditionalOnClass(StellarService.class)
@EnableConfigurationProperties(StellarConfiguration.class)
public class StellarAutoConfigure {

	@Autowired
	private StellarConfiguration config;

	@Bean
	StellarService stellarServiceBean() {
		return new StellarService(config);
	}
}
