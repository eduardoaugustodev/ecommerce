package br.com.ecommerce.service.notificacao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.ecommerce.model.vo.notification.NotificationRequest;
import br.com.ecommerce.model.vo.notification.NotificationResponse;
import br.com.ecommerce.service.notificacao.INotificacaoService;
import br.com.ecommerce.utils.Utils;
import reactor.core.publisher.Mono;

@Service
public class NotificacaoServiceImpl implements INotificacaoService {

	@Value("{app.notification.url}")
	private String baseURL;
	
	@Override
	public NotificationResponse notificar(NotificationRequest request) {
		
		WebClient client =	Utils.buildWebClient(this.baseURL);
		
		Mono<NotificationResponse> monoServico = client.post()
				.uri("/notificar")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(request), NotificationRequest.class)
		        .retrieve()
		        .bodyToMono(NotificationResponse.class);
		
		return monoServico.block();		
	}


}
