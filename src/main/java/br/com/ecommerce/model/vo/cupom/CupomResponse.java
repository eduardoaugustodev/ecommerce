package br.com.ecommerce.model.vo.cupom;

import java.math.BigDecimal;

public class CupomResponse {

	private String mensagem;

	private BigDecimal desconto;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

}
