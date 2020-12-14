package br.com.ecommerce.model.vo.frete;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Servicos")
public class ServicoResponse {
	
	private DadosServico cServico;

	public DadosServico getcServico() {
		return cServico;
	}

	public void setcServico(DadosServico cServico) {
		this.cServico = cServico;
	}

}
