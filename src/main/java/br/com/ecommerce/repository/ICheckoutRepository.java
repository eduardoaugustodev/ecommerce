package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.model.entity.Venda;

public interface ICheckoutRepository extends JpaRepository<Venda, Integer> {

}
