package br.com.ecommerce.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.OK;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.model.entity.Produto;
import br.com.ecommerce.model.enums.FormaEnvio;
import br.com.ecommerce.model.vo.checkout.CheckoutRequest;
import br.com.ecommerce.model.vo.checkout.CheckoutResponse;
import br.com.ecommerce.model.vo.cupom.CupomRequest;
import br.com.ecommerce.model.vo.cupom.CupomResponse;
import br.com.ecommerce.model.vo.frete.ServicoResponse;
import br.com.ecommerce.model.vo.produto.ProdutoResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckoutResourceTest {

	@LocalServerPort
	int randomServerPort;

	private RestTemplate restTemplate;

	private String urlBase;

	@BeforeEach
	void setUp() {
		this.restTemplate = new RestTemplate();
		this.urlBase = "http://localhost:" + randomServerPort + "/ecommerce";
	}
	
	@Test
	void deveAplicarUmCupomComSucesso() {
		CupomRequest request = new CupomRequest();
		request.setCupom("0.10");
		ResponseEntity<CupomResponse> response = restTemplate.postForEntity(urlBase + "/checkout/aplicar-cupom", request, CupomResponse.class);
		assertEquals(OK, response.getStatusCode());
		assertTrue("Desconto aplicado com sucesso".equalsIgnoreCase(response.getBody().getMensagem()));
	}
	
	@Test
	void deveNaoAplicarUmCupomComSucesso() {
		CupomRequest request = new CupomRequest();
		request.setCupom("");
		ResponseEntity<CupomResponse> response = restTemplate.postForEntity(urlBase + "/checkout/aplicar-cupom", request, CupomResponse.class);
		assertEquals(OK, response.getStatusCode());
		assertTrue("Desconto não aplicado".equalsIgnoreCase(response.getBody().getMensagem()));
	}
	
	@Test
	void deveRealizarUmaVendaComDesconto(){
		
		CupomRequest request = new CupomRequest();
		request.setCupom("CUPOMTEST");
		ResponseEntity<CupomResponse> cupomResponse = restTemplate.postForEntity(urlBase + "/checkout/aplicar-cupom", request, CupomResponse.class);
		assertTrue(cupomResponse.getBody().getDesconto().equals(new BigDecimal("0.10")));
		
		ResponseEntity<ProdutoResponse> produtoResponse = restTemplate.getForEntity(urlBase + "/produtos/1", ProdutoResponse.class);
		Produto produto = produtoResponse.getBody().getProduto();
		assertNotNull(produto);
		
		CheckoutRequest checkoutRequest = new CheckoutRequest();
		checkoutRequest.setIdCliente(1);
		checkoutRequest.setIdProduto(produto.getId());
		checkoutRequest.setFormaEnvio(FormaEnvio.FISICO);
		BigDecimal valorVenda = produto.getValorVenda().subtract(produto.getValorVenda().multiply(cupomResponse.getBody().getDesconto()));
		checkoutRequest.setValorTotal(valorVenda.setScale(2, BigDecimal.ROUND_HALF_EVEN));		
		
		ResponseEntity<CheckoutResponse> checkoutResponse = restTemplate.postForEntity(urlBase + "/checkout/finalizar", checkoutRequest, CheckoutResponse.class);
		assertEquals(OK, checkoutResponse.getStatusCode());
		assertEquals("Checkout Finalizado Com Sucesso !", checkoutResponse.getBody().getMensagem());
	}
	
	@Test
	void deveRealizarUmaVendaComDescontoEFrete(){
		
		CupomRequest request = new CupomRequest();
		request.setCupom("CUPOMTEST");
		ResponseEntity<CupomResponse> cupomResponse = restTemplate.postForEntity(urlBase + "/checkout/aplicar-cupom", request, CupomResponse.class);
		assertTrue(cupomResponse.getBody().getDesconto().equals(new BigDecimal("0.10")));
		
		ResponseEntity<ProdutoResponse> produtoResponse = restTemplate.getForEntity(urlBase + "/produtos/1", ProdutoResponse.class);
		Produto produto = produtoResponse.getBody().getProduto();
		assertNotNull(produto);
		
		
		ResponseEntity<ServicoResponse> response = restTemplate.getForEntity("http://localhost:8081/calcula-frete?sCepDestino=90619900&nCdServico=04510", ServicoResponse.class);
		assertEquals(OK, response.getStatusCode());
		assertNotNull(response.getBody().getcServico().getValor());
		BigDecimal valorFrete = new BigDecimal(response.getBody().getcServico().getValor().replace(",", "."));
		
		CheckoutRequest checkoutRequest = new CheckoutRequest();
		checkoutRequest.setIdCliente(1);
		checkoutRequest.setIdProduto(produto.getId());
		checkoutRequest.setFormaEnvio(FormaEnvio.FISICO);
		BigDecimal valorVenda = produto.getValorVenda().subtract(produto.getValorVenda().multiply(cupomResponse.getBody().getDesconto()));
		checkoutRequest.setValorTotal(valorFrete.add(valorVenda).setScale(2, BigDecimal.ROUND_HALF_EVEN));		
		
		ResponseEntity<CheckoutResponse> checkoutResponse = restTemplate.postForEntity(urlBase + "/checkout/finalizar", checkoutRequest, CheckoutResponse.class);
		assertEquals(OK, checkoutResponse.getStatusCode());
		assertEquals("Checkout Finalizado Com Sucesso !", checkoutResponse.getBody().getMensagem());
	}
	
}
