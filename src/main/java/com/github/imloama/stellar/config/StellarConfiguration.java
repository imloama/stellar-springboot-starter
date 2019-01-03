package com.github.imloama.stellar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("stellar")
@Data
public class StellarConfiguration{

	private String network;
	private String horizon;



}