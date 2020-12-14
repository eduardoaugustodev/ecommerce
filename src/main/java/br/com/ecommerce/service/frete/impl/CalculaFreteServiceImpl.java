package br.com.ecommerce.service.frete.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.ecommerce.model.vo.frete.ServicoResponse;
import br.com.ecommerce.service.frete.ICalculaFreteService;
import br.com.ecommerce.utils.Utils;
import reactor.core.publisher.Mono;

@Service
public class CalculaFreteServiceImpl implements ICalculaFreteService {

	@Value("{app.calcula.frete.url}")
	private String baseURL;
	
	@Override
	public ServicoResponse calculaFrete(String sCepDestino, String nCdServico) {
		
		WebClient client = Utils.buildWebClient(this.baseURL);
		
		Mono<ServicoResponse> monoServico =	client.get()
							.uri(uriBuilder -> uriBuilder
								.path("/calcular")
								.queryParam("sCepDestino", "{sCepDestino}")
								.queryParam("nCdServico", "{nCdServico}")
								.build(sCepDestino, nCdServico))
							.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
			                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
							.retrieve()
							.bodyToMono(ServicoResponse.class);
		
		return monoServico.block();
		
	}

	
}
