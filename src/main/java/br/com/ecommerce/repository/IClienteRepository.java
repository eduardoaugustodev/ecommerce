package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.model.entity.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}
