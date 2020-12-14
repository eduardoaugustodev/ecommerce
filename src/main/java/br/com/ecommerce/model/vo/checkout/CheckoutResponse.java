package br.com.ecommerce.model.vo.checkout;

public class CheckoutResponse {

	private String mensagem;

	public CheckoutResponse() {

	}

	public CheckoutResponse(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
