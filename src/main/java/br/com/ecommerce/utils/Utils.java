package br.com.ecommerce.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class Utils {

	public static WebClient buildWebClient(String baseURL) {
		return WebClient.builder()
							.baseUrl("http://localhost:8082/")
							.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
							.build();
	}
}
