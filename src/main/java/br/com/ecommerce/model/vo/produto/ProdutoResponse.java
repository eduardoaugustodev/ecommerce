package br.com.ecommerce.model.vo.produto;

import br.com.ecommerce.model.entity.Produto;

public class ProdutoResponse {

	private String mensagem;
	
	private Produto produto;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
}
