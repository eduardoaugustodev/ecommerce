package br.com.ecommerce.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.model.vo.frete.ServicoResponse;
import br.com.ecommerce.service.frete.ICalculaFreteService;

@RestController
@RequestMapping("/calcular-frete")
public class CalculaFreteResource {

	@Autowired
	private ICalculaFreteService service;
	
	@GetMapping
	public ResponseEntity<ServicoResponse> calcularFrete(@RequestParam String sCepDestino, @RequestParam String nCdServico){
		return ResponseEntity.ok(this.service.calculaFrete(sCepDestino, nCdServico));
	}
		
}
