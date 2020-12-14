package br.com.ecommerce.service.checkout;

import br.com.ecommerce.model.vo.checkout.CheckoutRequest;
import br.com.ecommerce.model.vo.checkout.CheckoutResponse;
import br.com.ecommerce.model.vo.cupom.CupomRequest;
import br.com.ecommerce.model.vo.cupom.CupomResponse;

public interface ICheckoutService {

	public CheckoutResponse checkout(CheckoutRequest checkout);
	
	public CupomResponse aplicarCupom(CupomRequest cupomRequest);	
	
}
