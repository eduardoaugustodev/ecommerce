package br.com.ecommerce.service.produto.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.model.entity.Produto;
import br.com.ecommerce.model.vo.produto.ProdutoResponse;
import br.com.ecommerce.repository.IProdutoRepository;
import br.com.ecommerce.service.produto.IProdutoService;

@Service
public class ProdutoServiceImpl implements IProdutoService {

	@Autowired
	private IProdutoRepository repository;
	
	@Override
	public List<Produto> buscarTodos() {
		return this.repository.findAll();
	}

	@Override
	public ProdutoResponse buscarProdutoByID(Integer idProduto) {
		Optional<Produto> produto =  this.repository.findById(idProduto);
		ProdutoResponse response = new ProdutoResponse();
		if(produto.isPresent()) {
			response.setMensagem("Produto encontrato com sucesso.");
			response.setProduto(produto.get());
		}else {
			response.setMensagem("Produto não encontrato.");			
		}		
		return response;
	}
	
}
