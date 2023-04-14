package br.com.gilwansouza.biritashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gilwansouza.biritashop.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}