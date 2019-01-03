package com.github.imloama.stellar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.stellar.sdk.Network;

@ConfigurationProperties("stellar")
@Data
public class StellarConfiguration{

	private String network = "public";
	private String horizon = "https://horizon.stellar.org";
	private String networkPassphrase;



}