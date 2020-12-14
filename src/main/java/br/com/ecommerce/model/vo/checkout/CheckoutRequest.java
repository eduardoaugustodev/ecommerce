package br.com.ecommerce.model.vo.checkout;

import java.math.BigDecimal;

import br.com.ecommerce.model.enums.FormaEnvio;

public class CheckoutRequest {

	private Integer idCliente;

	private Integer idProduto;

	private FormaEnvio formaEnvio;

	private BigDecimal valorTotal;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public FormaEnvio getFormaEnvio() {
		return formaEnvio;
	}

	public void setFormaEnvio(FormaEnvio formaEnvio) {
		this.formaEnvio = formaEnvio;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
