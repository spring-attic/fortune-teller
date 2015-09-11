package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "fortune")
@RefreshScope
public class FortuneProperties {

	private String fallbackFortune = "Your future is unclear.";

	public String getFallbackFortune() {
		return fallbackFortune;
	}

	public void setFallbackFortune(String fallbackFortune) {
		this.fallbackFortune = fallbackFortune;
	}

}
