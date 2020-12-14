package br.com.ecommerce.model.vo.notification;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class NotificationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5050855675945239494L;
	
	@NotNull
	private String recipient;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
