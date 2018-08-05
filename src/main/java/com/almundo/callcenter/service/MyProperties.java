package com.almundo.callcenter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Read properties from src/main/resources/application.properties
 * 
 * @author Nerly-PC
 *
 */
@Component
public class MyProperties {

	@Value("${maxDuration}")
	private Integer maxDuration;

	@Value("${minimumDuration}")
	private Integer minimumDuration;

	public Integer getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(Integer maxDuration) {
		this.maxDuration = maxDuration;
	}

	public Integer getMinimumDuration() {
		return minimumDuration;
	}

	public void setMinimumDuration(Integer minimumDuration) {
		this.minimumDuration = minimumDuration;
	}

}
