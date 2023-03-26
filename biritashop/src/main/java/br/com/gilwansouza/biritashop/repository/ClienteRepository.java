package br.com.gilwansouza.biritashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gilwansouza.biritashop.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}