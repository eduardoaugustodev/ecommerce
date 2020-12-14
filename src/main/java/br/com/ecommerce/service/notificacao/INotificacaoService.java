package br.com.ecommerce.service.notificacao;

import br.com.ecommerce.model.vo.notification.NotificationRequest;
import br.com.ecommerce.model.vo.notification.NotificationResponse;

public interface INotificacaoService {

	public NotificationResponse notificar(NotificationRequest request);
}
