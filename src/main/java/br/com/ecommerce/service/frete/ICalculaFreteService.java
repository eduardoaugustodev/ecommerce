package br.com.ecommerce.service.frete;

import br.com.ecommerce.model.vo.frete.ServicoResponse;

public interface ICalculaFreteService {

	
	ServicoResponse calculaFrete(String sCepDestino, String nCdServico);
}
