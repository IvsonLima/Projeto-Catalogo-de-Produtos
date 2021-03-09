package com.catalogodeprodutos.catalogodeprodutos.repository;

import java.util.List;

import com.catalogodeprodutos.catalogodeprodutos.model.Produtos;

public interface ProdutosRepositoryQuery {

	public List<Produtos> filtrar(String q, String minPrice, String maxPrice);

}