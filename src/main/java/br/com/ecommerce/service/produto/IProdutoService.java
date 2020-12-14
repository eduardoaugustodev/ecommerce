package br.com.ecommerce.service.produto;

import java.util.List;

import br.com.ecommerce.model.entity.Produto;
import br.com.ecommerce.model.vo.produto.ProdutoResponse;

public interface IProdutoService {

	public List<Produto> buscarTodos();
	
	public ProdutoResponse buscarProdutoByID(Integer idProduto);
}
