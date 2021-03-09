package com.catalogodeprodutos.catalogodeprodutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogodeprodutos.catalogodeprodutos.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>, ProdutosRepositoryQuery {

	
}
	