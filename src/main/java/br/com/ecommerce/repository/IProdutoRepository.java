package br.com.ecommerce.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.model.entity.Produto;

@Transactional
public interface IProdutoRepository extends JpaRepository<Produto, Integer> {

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Produto produto set produto.valorDesconto =:valorDesconto where produto.isPromocao=true")
	public void aplicarDesconto(BigDecimal valorDesconto);
}
