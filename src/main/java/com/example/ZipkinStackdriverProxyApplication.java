package com.example;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.trace.zipkin.EnableStackdriverCollector;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@SpringBootApplication
@EnableStackdriverCollector
public class ZipkinStackdriverProxyApplication {

	// cf cs gcp-service-broker google-stackdriver-trace default stackdriver-trace-zipkin
	// cf bind-service zipkin-proxy stackdriver-trace-zipkin

	public static void main(String[] args) {
		System.setProperty("stackdriver.trace.zipkin.agent", "zipkin-java-collector");
		SpringApplication.run(ZipkinStackdriverProxyApplication.class, args);
	}

	@Bean
	Credentials credential(@Value("${pkd}") String privateKeyData) throws IOException {
		LogFactory.getLog(getClass()).info("credential: " +  privateKeyData);
		InputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(privateKeyData));
		return GoogleCredentials.fromStream(stream);
	}

}
