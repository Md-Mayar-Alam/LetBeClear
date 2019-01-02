package com.letbeclear.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="image")
public class ImageConfig {
private String path;

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
	
}

}
