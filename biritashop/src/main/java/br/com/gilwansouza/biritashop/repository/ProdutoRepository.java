package br.com.gilwansouza.biritashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gilwansouza.biritashop.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}