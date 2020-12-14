package br.com.ecommerce.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.model.entity.Produto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoResourceTest {

	@LocalServerPort
	int randomServerPort;

	private RestTemplate restTemplate;

	private String urlBase;

	@BeforeEach
	void setUp() {
		this.restTemplate = new RestTemplate();
		this.urlBase = "http://localhost:" + randomServerPort + "/ecommerce/produtos";
	}

	@Test
	void deveBuscarTodosOsProdutosComSucesso(){
		ResponseEntity<List<Produto>> response = restTemplate.exchange(urlBase, HttpMethod.GET, null, new ParameterizedTypeReference<List<Produto>>(){});
		List<Produto> lista = response.getBody();
		assertEquals(OK, response.getStatusCode());
		assertTrue(lista.size() > 1);
	}
}
