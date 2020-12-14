package br.com.ecommerce.service.checkout.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.model.entity.Cliente;
import br.com.ecommerce.model.entity.Produto;
import br.com.ecommerce.model.entity.Venda;
import br.com.ecommerce.model.vo.checkout.CheckoutRequest;
import br.com.ecommerce.model.vo.checkout.CheckoutResponse;
import br.com.ecommerce.model.vo.cupom.CupomRequest;
import br.com.ecommerce.model.vo.cupom.CupomResponse;
import br.com.ecommerce.model.vo.notification.NotificationRequest;
import br.com.ecommerce.repository.ICheckoutRepository;
import br.com.ecommerce.repository.IClienteRepository;
import br.com.ecommerce.repository.IProdutoRepository;
import br.com.ecommerce.service.checkout.ICheckoutService;
import br.com.ecommerce.service.notificacao.INotificacaoService;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

	@Autowired
	private INotificacaoService notificationService;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IProdutoRepository produtoRepository;
	
	@Autowired
	private ICheckoutRepository checkoutRepository;
	
	@Override
	public CheckoutResponse checkout(CheckoutRequest checkout) {
		Optional<Cliente> cliente = obterCliente(checkout.getIdCliente());
		Optional<Produto> produto = obterProduto(checkout.getIdProduto());
		CheckoutResponse response = new CheckoutResponse();
		
		if(cliente.isPresent() && produto.isPresent()) {
			Venda venda = new Venda();
			venda.setCliente(cliente.get());
			venda.setProduto(produto.get());
			venda.setFormaEnvio(checkout.getFormaEnvio());
			venda.setValorTotal(checkout.getValorTotal());			
			Venda novaVenda = this.checkoutRepository.save(venda);
			if(novaVenda.getId() != null) {
				notificarCliente(cliente.get().getEmail());
				response.setMensagem("Checkout Finalizado Com Sucesso !");
			}else {
				response.setMensagem("Não foi possível realizar o checkout !");				
			}
		}
		return response;
	}
	
	
	@Override
	public CupomResponse aplicarCupom(CupomRequest cupomRequest) {
		BigDecimal desconto = new BigDecimal("0.00");
		CupomResponse response  = new CupomResponse();
		response.setMensagem("Desconto não aplicado");
		response.setDesconto(desconto);
		if(null != cupomRequest && !"".equalsIgnoreCase(cupomRequest.getCupom())) {
			desconto = new BigDecimal("0.10");
			response.setMensagem("Desconto aplicado com sucesso");
			response.setDesconto(desconto);
		}		
		this.produtoRepository.aplicarDesconto(desconto);
		return response;
	}
	
	private Optional<Produto> obterProduto(Integer idProduto){
		return this.produtoRepository.findById(idProduto);
	}
	
	private Optional<Cliente> obterCliente(Integer idCliente){
		return this.clienteRepository.findById(idCliente);
	}
	
	private void notificarCliente(String emailDesinto) {
		NotificationRequest request = new NotificationRequest();
		request.setRecipient(emailDesinto);
		this.notificationService.notificar(request);		
	}
	
	
	
}
