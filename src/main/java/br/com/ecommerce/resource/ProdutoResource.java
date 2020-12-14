package br.com.ecommerce.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.model.entity.Produto;
import br.com.ecommerce.model.vo.produto.ProdutoResponse;
import br.com.ecommerce.service.produto.IProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private IProdutoService service;

	@GetMapping
	public ResponseEntity<List<Produto>> buscarTodos() {
		return ResponseEntity.ok(this.service.buscarTodos());
	}
	
	@GetMapping("/{idProduto}")
	public ResponseEntity<ProdutoResponse> buscarProdutoByID(@PathVariable Integer idProduto){
		return ResponseEntity.ok(this.service.buscarProdutoByID(idProduto));
	}
}
