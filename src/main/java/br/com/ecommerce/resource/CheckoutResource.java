package br.com.ecommerce.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.model.vo.checkout.CheckoutRequest;
import br.com.ecommerce.model.vo.checkout.CheckoutResponse;
import br.com.ecommerce.model.vo.cupom.CupomRequest;
import br.com.ecommerce.model.vo.cupom.CupomResponse;
import br.com.ecommerce.service.checkout.ICheckoutService;

@RestController
@RequestMapping("/checkout")
public class CheckoutResource {

	@Autowired
	private ICheckoutService service;
	
	@PostMapping("/aplicar-cupom")
	public ResponseEntity<CupomResponse> aplicarCumpom(@RequestBody CupomRequest cupomRequest) {
		return ResponseEntity.ok(this.service.aplicarCupom(cupomRequest));
	}
	
	@PostMapping("/finalizar")
	public ResponseEntity<CheckoutResponse> finalizar(@RequestBody CheckoutRequest checkout){
		CheckoutResponse response = this.service.checkout(checkout);	
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
